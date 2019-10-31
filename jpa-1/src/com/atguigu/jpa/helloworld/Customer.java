package com.atguigu.jpa.helloworld;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
 
@NamedQuery(name="testNamedQuery", query="select c.* FROM Customer c WHERE c.id = ?")
@Cacheable(true)
@Table(name="JPA_CUTOMERS")
@Entity
public class Customer {

	private Integer id;
	private String lastName;

	private String email;
	private int age;
	
	private Date createdTime;
	private Date birth;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}



	private Set<Order> orders = new HashSet<>();

//	@TableGenerator(name="ID_GENERATOR",
//			table="jpa_id_generators",
//			pkColumnName="PK_NAME",
//			pkColumnValue="CUSTOMER_ID",
//			valueColumnName="PK_VALUE",
//			allocationSize=100)
//	@GeneratedValue(strategy=GenerationType.TABLE,generator="ID_GENERATOR")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="LAST_NAME",length=50,nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	//ӳ�䵥�� 1-n �Ĺ�����ϵ
	//ʹ�� @OneToMany ��ӳ�� 1-n �Ĺ�����ϵ
	//ʹ�� @JoinColumn ��ӳ������е�����
	//����ʹ�� @OneToMany �� fetch �������޸�Ĭ�ϵļ��ز���
	//����ͨ�� @OneToMany �� cascade �������޸�Ĭ�ϵ�ɾ������. 
	//ע��: ���� 1 ��һ�˵� @OneToMany ��ʹ�� mappedBy ����, �� @OneToMany �˾Ͳ�����ʹ�� @JoinColumn ������. 
//	@JoinColumn(name="CUSTOMER_ID")
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE},mappedBy="customer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	//���߷���. ����Ҫӳ��Ϊ���ݱ��һ��. 
	@Transient
	public String getInfo(){
		return "lastName: " + lastName + ", email: " + email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", age=" + age + ", createdTime=" + createdTime
				+ ", birth=" + birth + "]";
	}

}
