package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收ActiveMQ消息
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/16
 */
public class MyMessageListener implements MessageListener {


	@Override
	public void onMessage(Message message) {
		//取消息内容
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			System.out.println(text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
