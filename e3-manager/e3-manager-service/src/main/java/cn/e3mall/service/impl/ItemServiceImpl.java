package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import cn.e3mall.utils.E3Result;
import cn.e3mall.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @program: e3
 * @description: 商品Service实现
 * @author: Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/9
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem getItemById(long itemId) {
		//根据主键查询
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		//补全item属性
		item.setId(itemId);
		//1：正常 2：下架 3：删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		return E3Result.ok();
	}

	@Override
	public TbItemDesc getItemDescById(long id) {
		return itemDescMapper.selectByPrimaryKey(id);
	}

	@Override
	public E3Result deleteItems(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			//分割ids
			String[] split = ids.split(",");
			for (String id : split) {
				itemMapper.deleteByPrimaryKey(Long.valueOf(id));
				itemDescMapper.deleteByPrimaryKey(Long.valueOf(id));
			}
			return E3Result.ok();
		}
		return null;
	}

	@Override
	public E3Result groundingItem(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			String[] split = ids.split(",");
			//遍历所有id,进行修改下架
			for (String id : split) {
				TbItem item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
				item.setStatus((byte) 1);
				//保存
				itemMapper.updateByPrimaryKey(item);
			}
			return E3Result.ok();
		}
		return null;
	}

	@Override
	public E3Result soldOutItem(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			String[] split = ids.split(",");
			//遍历所有id进行修改下架
			for (String id : split) {
				TbItem item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
				item.setStatus((byte) 2);
				//保存
				itemMapper.updateByPrimaryKey(item);
			}
			return E3Result.ok();
		}
		return null;
	}

}
