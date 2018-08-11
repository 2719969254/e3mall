package cn.e3mall.content.service;

import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.utils.E3Result;

import java.util.List;

/**
 * @program: e3
 * @description: 内容分类管理
 * @author:Mr.Tian
 * @Company： www.stxkfzx.com
 * @Time: 2018/8/1020:04
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
     * @param name 添加一叶子节点名字
     * @return E3Result
     */
    E3Result addContentCategory(Long parentId, String name);

}
