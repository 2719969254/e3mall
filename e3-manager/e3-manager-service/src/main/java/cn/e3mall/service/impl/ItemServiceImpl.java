package cn.e3mall.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * 商品管理实现
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/10
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;

	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;

	@Override
	public TbItem getItemById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
			if (StringUtils.isNotBlank(json)){
				//将字符串转化成对象，并返回对象格式
				return JsonUtils.jsonToPojo(json, TbItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//缓冲中没有，从数据库查询
		//根据主键查询
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			//将查询到的结果添加到缓冲中
			try {
				jedisClient.set(REDIS_ITEM_PRE+":"+itemId+":BASE",JsonUtils.objectToJson(list.get(0)));
				//设置过期时间
				jedisClient.expire(REDIS_ITEM_PRE+":"+itemId+":BASE",ITEM_CACHE_EXPIRE);
			}catch (Exception e){
				e.printStackTrace();
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id
		final long itemId = IDUtils.genItemId();
		//补全item属性
		item.setId(itemId);
		//1：正常 2：下架 3：删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//发送商品添加消息
		jmsTemplate.send(topicDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});

		return E3Result.ok();
	}

	@Override
	public TbItemDesc getItemDescById(long id) {
		//查询缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + id + ":DESC");
			if (StringUtils.isNotBlank(json)){
				return JsonUtils.jsonToPojo(json, TbItemDesc.class);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
		try {
			jedisClient.set(REDIS_ITEM_PRE + ":" + id + ":DESC", JsonUtils.objectToJson(tbItemDesc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_PRE+":"+id+":DESC",ITEM_CACHE_EXPIRE);
		}catch (Exception e){
			e.printStackTrace();
		}
		return tbItemDesc;
	}

	@Override
	public E3Result deleteItems(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			//分割ids
			String[] split = ids.split(",");
			for (String id : split) {
				itemMapper.deleteByPrimaryKey(Long.valueOf(id));
				itemDescMapper.deleteByPrimaryKey(Long.valueOf(id));
			}
			return E3Result.ok();
		}
		return null;
	}

	@Override
	public E3Result groundingItem(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			String[] split = ids.split(",");
			//遍历所有id,进行修改下架
			for (String id : split) {
				TbItem item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
				item.setStatus((byte) 1);
				//保存
				itemMapper.updateByPrimaryKey(item);
			}
			return E3Result.ok();
		}
		return null;
	}

	@Override
	public E3Result soldOutItem(String ids) {
		//判断ids不为空
		if (StringUtils.isNoneBlank(ids)) {
			String[] split = ids.split(",");
			//遍历所有id进行修改下架
			for (String id : split) {
				TbItem item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
				item.setStatus((byte) 2);
				//保存
				itemMapper.updateByPrimaryKey(item);
			}
			return E3Result.ok();
		}
		return null;
	}

}
