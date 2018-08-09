package cn.e3mall.service;

import cn.e3mall.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author 田祚
 */
public interface ItemCatService {
    /**
     * 显示所有分类
     *
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getItemCatList(Long parentId);
}
