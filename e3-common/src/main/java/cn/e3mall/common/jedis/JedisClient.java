package cn.e3mall.common.jedis;

import java.util.List;

/**
 * 集群的常用方法接口
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/15
 */
public interface JedisClient {
	// TODO: 2018/8/15 补全下面方法doc注释

	String set(String key, String value);

	String get(String key);

	Boolean exists(String key);

	Long expire(String key, int seconds);

	Long ttl(String key);

	Long incr(String key);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Long hdel(String key, String... field);

	Boolean hexists(String key, String field);

	List<String> hvals(String key);

	Long del(String key);
}
