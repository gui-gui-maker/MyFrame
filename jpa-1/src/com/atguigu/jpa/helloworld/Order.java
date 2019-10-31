package com.atguigu.jpa.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="JPA_ORDERS")
@Entity
public class Order {

	private Integer id;
	private String orderName;

	private Customer customer;

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="ORDER_NAME")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	//ӳ�䵥�� n-1 �Ĺ�����ϵ
	//ʹ�� @ManyToOne ��ӳ����һ�Ĺ�����ϵ
	//ʹ�� @JoinColumn ��ӳ�����. 
	//��ʹ�� @ManyToOne �� fetch �������޸�Ĭ�ϵĹ������Եļ��ز���
	@JoinColumn(name="CUSTOMER_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
