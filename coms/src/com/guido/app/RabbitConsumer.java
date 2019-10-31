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
		//��������ӷ�ʽ�������ߵ�demo���в�ͬ��ע��������
		Connection connection = factory.newConnection(addresses);
		Channel channel = connection.createChannel();
		
		//������������Ϣ��δ��ack����Ϣ���ĸ���
		channel.basicQos(64);
		//����������
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
		//�������������ͨ��·�ɼ���
		TimeUnit.SECONDS.sleep(1000);

		channel.close();
		connection.close();
	}
}
