package cn.e3mall.service;

import cn.e3mall.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatlist(Long parentId);
}
