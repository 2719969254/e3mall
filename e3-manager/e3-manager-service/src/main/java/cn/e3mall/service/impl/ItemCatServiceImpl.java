package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理实现
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/10
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据父节点查询子节点列表
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);

		List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(tbItemCatExample);
		//创建返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<>();
		//把列表转换成EasyUITreeNode列表
		for (TbItemCat tbItemCat : tbItemCats
		) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setState(tbItemCat.getIsParent() ? "closed" : "open");
			node.setText(tbItemCat.getName());
			resultList.add(node);
		}
		return resultList;
	}
}
