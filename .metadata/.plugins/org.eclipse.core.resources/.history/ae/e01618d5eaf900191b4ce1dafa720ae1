package com.guido.rabbit.many;

import java.util.Arrays;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guido.model.Apple;
import com.guido.model.Banana;
import com.guido.model.Fruit;
import com.guido.model.Mango;

@Component
public class DdlSender implements RabbitTemplate.ReturnCallback/* , RabbitTemplate.ConfirmCallback,InitializingBean  */  {

	@Autowired
	//private AmqpTemplate rabbitTemplate;
	RabbitTemplate rabbitTemplate;
	
	public void send(String message) {
		String queue = "banana";
		System.out.println("Sender1 send to apple: " + message.toString());
		this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("消息发送成功 ");
            }
        });

		this.rabbitTemplate.convertAndSend(queue, message);
	}
	
	// 实现ReturnCallback
    // 当消息发送出去找不到对应路由队列时，将会把消息退回
    // 如果有任何一个路由队列接收投递消息成功，则不会退回消息
	@Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息发送失败: " + message.getBody()+"==="+replyCode+"==="+replyText+"==="+exchange+"===="+routingKey);
    }


}