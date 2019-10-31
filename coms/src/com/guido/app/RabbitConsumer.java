package com.guido.app;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;

public class RabbitConsumer {

	private static final String QUEUE_NAME="queue_demo";
	//private static final String EXCHANGE_NAME="exchange_demo";
	//private static final String ROUTING_KEY="routingkey_demo";
	private static final String IP_ADDRESS="192.168.70.129";
	private static final int PORT=5672;
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException{
		Address[] addresses = new Address[] {new Address(IP_ADDRESS,PORT)}; 
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("admin");
		factory.setPassword("guido1222");
		//这里的连接方式与生产者的demo略有不同，注意辨别区别
		Connection connection = factory.newConnection(addresses);
		Channel channel = connection.createChannel();
		
		//设置最多就收消息（未被ack的消息）的个数
		channel.basicQos(64);
		//创建消费者
		Consumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag,Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws IOException {
				System.out.println("received tag:" +consumerTag+"received message:" + new String(body));
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(QUEUE_NAME,consumer);
		//将交换器与队列通过路由键绑定
		TimeUnit.SECONDS.sleep(1000);

		channel.close();
		connection.close();
	}
}
