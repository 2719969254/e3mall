package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 等待接收消息
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/16
 */
public class MessageConsumer {
	/**
	 * 等待接收消息
	 */
	@Test
	public void msgConsumer() throws IOException {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		System.in.read();

	}
}
