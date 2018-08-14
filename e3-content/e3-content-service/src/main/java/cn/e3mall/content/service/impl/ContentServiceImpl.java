package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: e3
 * @description: 内容管理Service
 * @author: Mr.Tian
 * @version 1.0
 * @date 2018/8/11
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

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
		//缓存同步,删除缓存中对应的数据。
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public E3Result editContent(TbContent content) {
		//设置修改时间
		content.setUpdated(new Date());
		//执行修改
		contentMapper.updateByPrimaryKey(content);
		//缓存同步,删除缓存中对应的数据。
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public TbContent selectByIdContent(Long id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbContent> getContentListByCid(Long cid) {
		//查询缓存
		try{
			//如果缓存中有直接响应结果
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			if (StringUtils.isNotBlank(json)) {
				System.out.println("此时有缓存");
				return JsonUtils.jsonToList(json, TbContent.class);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		//添加查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询并返回
		List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);
		//添加缓存
		try{
			jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(tbContents));
			System.out.println("此时添加缓存");
		}catch (Exception e){
			e.printStackTrace();
		}
		return tbContents;
	}

	@Override
	public E3Result deleteBatchContent(String[] ids) {
		for (String id  :ids) {
			//执行删除操作
			contentMapper.deleteByPrimaryKey(Long.valueOf(id));
			TbContent content = contentMapper.selectByPrimaryKey(Long.valueOf(id));
			//缓存同步,删除缓存中对应的数据。
			System.out.println("content = " + content.getTitle());
			jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		}
		return E3Result.ok();
	}
}
