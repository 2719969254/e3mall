package cn.e3mall.content.service.impl;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: e3
 * @description: 内容分类管理Service
 * @author: Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/10
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据父节点查询子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode的列表
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : tbContentCategories) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			//添加到列表
			nodeList.add(node);

		}

		return nodeList;
	}

	@Override
	public E3Result addContentCategory(Long parentId, String name) {
		//创建一个tb_content_category表对应的pojo
		TbContentCategory contentCategory = new TbContentCategory();
		//设置pojo属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1：正常  2：删除
		contentCategory.setStatus(1);
		//默认排序为一
		contentCategory.setSortOrder(1);
		//新添加的节点一定是叶子节点
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setIsParent(false);
		//插入到数据库
		contentCategoryMapper.insert(contentCategory);

		//判断父节点的的isParent属性，如果为false改为true
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return E3Result.ok(contentCategory);
	}

	@Override
	public E3Result editContentCategory(Long id, String name) {
		//查询节点内容
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//设置修改名称
		contentCategory.setName(name);
		//修改数据库
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		//返回状态
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContentCategory(Long id) {
		//查询节点全部信息
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//得到父节点
		Long parentId = tbContentCategory.getParentId();
		//判断是否有父节点
		if (tbContentCategory.getIsParent()) {
			return E3Result.build(1, "不能删除父节点");
		} else {
			contentCategoryMapper.deleteByPrimaryKey(id);
			//根据子节点返回的父节点查询其父节点是否为子节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			TbContentCategoryExample.Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
			//判断是否为空节点
			if (tbContentCategories.size() == 0) {
				//如果为空节点，并且判断父节点的isParent是否为true，如果是就改为false
				TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
				if (parent.getIsParent()) {
					parent.setIsParent(false);
					//更新到数据库
					contentCategoryMapper.updateByPrimaryKey(parent);
				}
			}
		}
		//返回结果，返回E3Result，包含pojo
		return E3Result.ok(tbContentCategory);
	}

}
