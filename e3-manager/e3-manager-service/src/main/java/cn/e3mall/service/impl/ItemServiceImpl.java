package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 *
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //根据主键查询
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
//		TbItemExample example = new TbItemExample();
//		Criteria criteria = example.createCriteria();
//		//设置查询条件
//		criteria.andIdEqualTo(itemId);
//		//执行查询
//		List<TbItem> list = itemMapper.selectByExample(example);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		}
        return tbItem;
    }
}
