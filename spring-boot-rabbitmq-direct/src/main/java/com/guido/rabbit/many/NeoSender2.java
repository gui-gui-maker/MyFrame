package com.guido.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guido.model.Apple;
import com.guido.model.Banana;
import com.guido.model.Fruit;
import com.guido.model.Mango;

@Component
public class NeoSender2 {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String queue = "";
		Fruit message = null; 
		if(i%3==0) 
		{
			message = new Apple("红富士"+i,"red");
			queue = "apple";
		}	
		else if(i%3==1) 
		{
			message = new Banana("皇帝蕉"+i,"yellow",10.5f);
			queue = "bananas";
		}
		else if(i%3==2) {
			message = new Mango("攀枝花芒果"+i,"green",0.6f);
			queue = "mango";			
		}
		System.out.println("Sender2: " + message.toString());
		this.rabbitTemplate.convertAndSend(queue, message);
	}

}