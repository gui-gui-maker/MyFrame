package com.guido.rabbit.many;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.guido.model.Fruit;
import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = {"apple","banana"})
public class NeoReceiver1 {

    @RabbitHandler
    public void process(Fruit fruit,Channel channel,Message message) {
        try {
        	System.out.println("Receiver 1: " + fruit.getName());
        	//告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
		}
    }

}
