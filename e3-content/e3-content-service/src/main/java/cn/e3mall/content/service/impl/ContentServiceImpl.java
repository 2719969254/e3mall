package cn.e3mall.content.service.impl;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.utils.E3Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: e3
 * @description: 内容管理Service
 * @author: Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/11
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public EasyUIDataGridResult getItemList(Long categoryId, Integer page, Integer rows) {
		//设置分页信息
		PageHelper.startPage(page,rows);
		//创建查询条件
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(categoryId);
		//执行查询
		List<TbContent> tbContents = contentMapper.selectByExample(example);
		//提取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
		//创建返回结果对象
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		//显示商品信息
		easyUIDataGridResult.setRows(tbContents);
		//显示分页
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		return easyUIDataGridResult;
	}

	@Override
	public E3Result addContent(TbContent content) {
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据库
		contentMapper.insert(content);
		return E3Result.ok();
	}

	@Override
	public E3Result editContent(TbContent content) {
		//设置修改时间
		content.setUpdated(new Date());
		//执行修改
		contentMapper.updateByPrimaryKey(content);
		return E3Result.ok();
	}

	@Override
	public TbContent selectByIdContent(Long id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbContent> getContentListByCid(Long cid) {
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		//添加查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询并返回
		return contentMapper.selectByExampleWithBLOBs(example);
	}
}
