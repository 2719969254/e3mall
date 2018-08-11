package cn.e3mall.content.service;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.utils.E3Result;

import java.util.List;

/**
 * @program: e3
 * @description: 内容管理接口
 * @author: Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/11
 */
public interface ContentService {
	/**
	 * 显示内容列表
	 *
	 * @param categoryId 商品id
	 * @param page       页数
	 * @param rows       行数
	 * @return EasyUIDataGridResult
	 */
	EasyUIDataGridResult getItemList(Long categoryId, Integer page, Integer rows);

	/**
	 * 新增内容
	 *
	 * @param content 内容pojo
	 * @return E3Result
	 */
	E3Result addContent(TbContent content);

	/**
	 * 修改内容
	 *
	 * @param content 内容pojo
	 * @return E3Result
	 */
	E3Result editContent(TbContent content);

	/**
	 * 回写内容描述
	 * @param id 内容id
	 * @return TbContent
	 */
	TbContent selectByIdContent(Long id);
	/**
	 * 根据内容分类id查询内容列表
	 * @param cid 分类id
	 * @return List<TbContent>
	 */
	List<TbContent> getContentListByCid(Long cid);
}
