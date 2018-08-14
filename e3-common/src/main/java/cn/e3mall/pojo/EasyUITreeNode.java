package cn.e3mall.pojo;

import java.io.Serializable;

/**
 * @version 1.0
 * @description: EasyUITreeNode返回格式
 * @author: Mr.Tian
 * @date 2018/8/10
 */
public class EasyUITreeNode implements Serializable {
	private static final long serialVersionUID = 4558287260317863478L;
	private Long id;
	private String text;
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
