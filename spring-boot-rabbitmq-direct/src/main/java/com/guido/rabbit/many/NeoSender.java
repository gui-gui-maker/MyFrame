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
public class NeoSender implements RabbitTemplate.ReturnCallback/* , RabbitTemplate.ConfirmCallback,InitializingBean  */  {

	@Autowired
	//private AmqpTemplate rabbitTemplate;
	RabbitTemplate rabbitTemplate;
	
	public void send(int i) {
		String queue = "";
		Fruit msg = null; 
		if(i%3==0) 
		{
			msg = new Apple("红富士"+i,"red");
			queue = "apple";
		}	
		else if(i%3==1) 
		{
			msg = new Banana("皇帝蕉"+i,"yellow",10.5f);
			queue = "banana";
		}
		else if(i%3==2) {
			msg = new Mango("攀枝花芒果"+i,"green",0.6f);
			queue = "mango";			
		}
		System.out.println("Sender1 : " + msg.toString());
		this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("消息发送成功 ");
            }
        });
       // this.rabbitTemplate.convertAndSend("hello", context);

		this.rabbitTemplate.convertAndSend(/* "fruit", */ queue, msg);
	}
	
	// 实现ReturnCallback
    // 当消息发送出去找不到对应路由队列时，将会把消息退回
    // 如果有任何一个路由队列接收投递消息成功，则不会退回消息
	@Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息发送失败: " + message.getBody()+"==="+replyCode+"==="+replyText+"==="+exchange+"===="+routingKey);
    }

    // 实现ConfirmCallback
    // ACK=true仅仅标示消息已被Broker接收到，并不表示已成功投放至消息队列中
    // ACK=false标示消息由于Broker处理错误，消息并未处理成功
	/*
	 * @Override public void confirm(CorrelationData correlationData, boolean ack,
	 * String cause) { System.out.println("消息id: " + correlationData + "确认" + (ack ?
	 * "成功:" : "失败")); }
	 */

    // 实现InitializingBean
    // 设置消息送达、确认的方式
	/*
	 * @Override public void afterPropertiesSet() throws Exception {
	 * ((RabbitTemplate)rabbitTemplate).setConfirmCallback(this::confirm);
	 * ((RabbitTemplate)rabbitTemplate).setReturnCallback(this::returnedMessage); }
	 */

}