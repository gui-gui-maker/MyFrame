package com.guido.app;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitProducer {
	private static final String IP_ADDRESS="192.168.70.129";
	private static final int PORT=5672;
	
	public static void main(String[] args) throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(IP_ADDRESS);
		factory.setPort(PORT);
		factory.setUsername("admin");
		factory.setPassword("guido1222");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		
		//����һ��type='direct' �־û��ģ����Զ�ɾ���Ľ�����
		channel.exchangeDeclare("dead", "direct",true,false,null);
		//����һ���־û����������ġ����Զ�ɾ���Ķ���
		channel.queueDeclare("dead",true,false,false,null);
		//�������������ͨ��·�ɼ���
		channel.queueBind("dead", "dead", "dead");
				
		
		//����һ��type='direct' �־û��ģ����Զ�ɾ���Ľ�����
		channel.exchangeDeclare("fluit", "direct",true,false,null);
		Map<String,Object> argss = new HashMap<>();
		argss.put("x-dead-letter-exchange","dead");
		argss.put("x-dead-letter-routing-key","dead");
		argss.put("x-message-ttl",10000);
		//����һ���־û����������ġ����Զ�ɾ���Ķ���
		channel.queueDeclare("banana",true,false,false,argss);
		
		//�������������ͨ��·�ɼ���
		channel.queueBind("banana", "fluit", "hainan-banana");
		
		//����һ���־û�����Ϣ
		String message ="hello world !";
		for(int i=0;i<100;i++) {
			message = "test hello world "+i;
			channel.basicPublish("fluit", "hainan-banana", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		}
		channel.close();
		connection.close();
	}

}