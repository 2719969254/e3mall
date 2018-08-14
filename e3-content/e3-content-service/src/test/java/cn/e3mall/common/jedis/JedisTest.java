package cn.e3mall.common.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: e3
 * @description: jedis测试
 * @author: Mr.Tian
 * @version 1.0
 * @date 2018/8/12
 */
public class JedisTest {
	/**
	 * 测试jedis连接
	 */
	@Test
	public void testJedis(){
		//创建一个jedis对象，参数：host post
		Jedis jedis = new Jedis("192.168.25.135",6379);
		//直接使用jedis操作redis，所有jedis命令都对应一个方法
		jedis.set("test","123");
		String test = jedis.get("test");
		System.out.println(test);
		jedis.close();
	}

	/**
	 * 测试jedis连接池
	 */
	@Test
	public void testJedisPool(){
		//创建一个jedis连接池
		JedisPool jedisPool = new JedisPool("192.168.25.135",6379);
		//从连接池获取一个连接，也就是一个jedis对象
		Jedis resource = jedisPool.getResource();

		//使用jedis操作redis
		String test = resource.get("test");
		System.out.println("test = " + test);
		//关闭连接
		resource.close();
	}

	/**
	 * 测试jedis集群
	 */
/*	@Test
	public void testJedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.135",7001));
		nodes.add(new HostAndPort("192.168.25.135",7002));
		nodes.add(new HostAndPort("192.168.25.135",7003));
		nodes.add(new HostAndPort("192.168.25.135",7004));
		nodes.add(new HostAndPort("192.168.25.135",7005));
		nodes.add(new HostAndPort("192.168.25.135",7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("test123","hhh");
		String test123 = jedisCluster.get("test123");
		System.out.println("test123 = " + test123);
		jedisCluster.close();
	}*/
}
