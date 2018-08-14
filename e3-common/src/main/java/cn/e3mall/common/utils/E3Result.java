package cn.e3mall.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 定义多种返回状态
 *
 * @version 1.0
 * @author: Mr.Tian
 * @date 2018/8/10
 */
public class E3Result implements Serializable {

	/**
	 * 定义jackson对象
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final long serialVersionUID = 7796591433094312919L;

	/**
	 * 响应业务状态
	 */
	private Integer status;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 响应中的数据
	 */
	private Object data;

	public static E3Result build(Integer status, String msg, Object data) {
		return new E3Result(status, msg, data);
	}

	public static E3Result ok(Object data) {
		return new E3Result(data);
	}

	public static E3Result ok() {
		return new E3Result(null);
	}

	public static E3Result build(Integer status, String msg) {
		return new E3Result(status, msg, null);
	}

	private E3Result(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private E3Result(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为Result对象
	 *
	 * @param jsonData json数据
	 * @param clazz    Result中的object类型
	 * @return E3Result
	 */
	public static E3Result formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, E3Result.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isObject()) {
				obj = MAPPER.readValue(data.traverse(), clazz);
			} else if (data.isTextual()) {
				obj = MAPPER.readValue(data.asText(), clazz);
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 *
	 * @param json 转化为json字符串
	 * @return E3Result
	 */
	public static E3Result format(String json) {
		try {
			return MAPPER.readValue(json, E3Result.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object是集合转化
	 *
	 * @param jsonData json数据
	 * @param clazz    集合中的类型
	 * @return E3Result
	 */
	public static E3Result formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "E3Result{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
