package cn.e3mall.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *  分页对象
 * @author MR.Tian
 */
public class EasyUIDataGridResult implements Serializable {
	private static final long serialVersionUID = -3726397466515203504L;
	private Long total;
	private List rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
