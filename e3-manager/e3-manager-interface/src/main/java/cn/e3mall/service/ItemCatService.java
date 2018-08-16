package cn.e3mall.service;

import cn.e3mall.pojo.EasyUITreeNode;

import java.util.List;

/**
 * 显示商品分类
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/10
 */
public interface ItemCatService {
	/**
	 * 显示所有分类
	 *
	 * @param parentId 父节点id
	 * @return List<EasyUITreeNode>
	 */
	List<EasyUITreeNode> getItemCatList(Long parentId);
}
