package cn.e3mall.content.service;

import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

import java.util.List;

/**
 * 内容分类管理
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/9
 */
public interface ContentCategoryService {
	/**
	 * 查询所有内容分类
	 *
	 * @param parentId 父节点id
	 * @return list
	 */
	List<EasyUITreeNode> getContentCatList(long parentId);

	/**
	 * 添加分类节点
	 *
	 * @param parentId 添加叶子节点的父节点
	 * @param name     添加一叶子节点名字
	 * @return E3Result
	 */
	E3Result addContentCategory(Long parentId, String name);

	/**
	 * 修改分类节点
	 *
	 * @param id   节点id
	 * @param name 修改后名称
	 * @return E3Result
	 */
	E3Result editContentCategory(Long id, String name);

	/**
	 * 删除节点
	 *
	 * @param id 要删除节点id
	 * @return E3Result
	 */
	E3Result deleteContentCategory(Long id);
}
