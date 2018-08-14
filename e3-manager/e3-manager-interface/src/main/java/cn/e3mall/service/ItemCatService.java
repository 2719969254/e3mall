package cn.e3mall.service;

import cn.e3mall.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @program: e3
 * @description: 显示商品分类
 * @author: Mr.Tian
 * @version 1.0
 * @date 2018/8/10
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
