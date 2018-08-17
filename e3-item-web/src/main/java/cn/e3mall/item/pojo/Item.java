package cn.e3mall.item.pojo;

import cn.e3mall.pojo.TbItem;

/**
 * TbItem的子类，用于与商品详情页对接，如传递images[]
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/17
 */
public class Item extends TbItem {
	public Item(TbItem tbItem) {
		this.setId(tbItem.getId());
		this.setTitle(tbItem.getTitle());
		this.setSellPoint(tbItem.getSellPoint());
		this.setPrice(tbItem.getPrice());
		this.setNum(tbItem.getNum());
		this.setBarcode(tbItem.getBarcode());
		this.setImage(tbItem.getImage());
		this.setCid(tbItem.getCid());
		this.setStatus(tbItem.getStatus());
		this.setCreated(tbItem.getCreated());
		this.setUpdated(tbItem.getUpdated());
	}

	public String[] getImages() {
		String image2 = this.getImage();
		if (image2 != null && !"".equals(image2)) {
			return image2.split(",");
		}
		return null;
	}
}
