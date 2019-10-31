package com.guido.rabbit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {
	/**
	 * 有过期时间的队列 10秒
	 * 
	 * @return
	 */
	/*
	 * @Bean public Queue createApple() { Map<String,Object> args = new
	 * HashMap<String,Object>(); args.put("x-message-ttl", 10000); return new
	 * Queue("ddlQueue", false, false, false,args); }
	 * 
	 * @Bean public Queue createBanana() { return new Queue("banana"); }
	 * 
	 * @Bean public Queue createMango() { return new Queue("mango"); }
	 * 
	 * @Bean DirectExchange directExchange() { return new DirectExchange("fruit"); }
	 * 
	 *//**
		 * 依赖本配置中的bean，方法中对象的名称需要与方法名称对应
		 * 
		 * @param createApple
		 * @param directExchange
		 * @return
		 *//*
			 * @Bean DirectExchangeRoutingKeyConfigurer bindingExchangeA(Queue createApple,
			 * DirectExchange directExchange) { return
			 * BindingBuilder.bind(createApple).to(directExchange); }
			 * 
			 * @Bean DirectExchangeRoutingKeyConfigurer bindingExchangeB(Queue createBanana,
			 * DirectExchange directExchange) { return
			 * BindingBuilder.bind(createBanana).to(directExchange); }
			 * 
			 * @Bean DirectExchangeRoutingKeyConfigurer bindingExchangeC(Queue createMango,
			 * DirectExchange directExchange) { return
			 * BindingBuilder.bind(createMango).to(directExchange); }
			 */

}
