package cn.e3mall.search.mapper;


import cn.e3mall.pojo.SearchItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/14
 */
@Repository
public interface ItemMapper {

	/**
	 * 符合要求商品项
	 *
	 * @return List<SearchItem>
	 */
	List<SearchItem> getItemList();
}
