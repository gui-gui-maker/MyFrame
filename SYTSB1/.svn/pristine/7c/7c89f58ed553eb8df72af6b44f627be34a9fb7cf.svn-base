package demo.crud.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * <p>
 * 演示bean
 * </p>
 * <p>
 * 注解@Entity标识该bean为一个持久化实体。
 * </p>
 * <p>
 * 注解@Table标识该实体对应的数据库table
 * </p>
 */
@Entity
@Table(name = "TEST_DEMO")
public class Demo implements BaseEntity {
	private static final long serialVersionUID = -1240234293001523072L;

	// --bean 属性
	private String id;
	private String StrVal;
	private Integer intVal;
	private Date dateVal;
	private String text;

	/**
	 * <p>
	 * 注解@Id表示该属性为实体ID，相当于数据库主键
	 * </p>
	 * <p>
	 * 注解@GeneratedValue和注解@GenericGenerator定义了主键值生成策略。
	 * </p>
	 * <p>
	 * 这里定义的uuid表示使用uuid机制生成主键值。
	 * </p>
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 使用注解@Column 声明属性映射数据库表字段。
	 */
	@Column(name = "str_val")
	public String getStrVal() {
		return StrVal;
	}

	public void setStrVal(String strVal) {
		StrVal = strVal;
	}

	@Column(name = "int_val")
	public Integer getIntVal() {
		return intVal;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}

	@Column(name = "date_val")
	public Date getDateVal() {
		return dateVal;
	}

	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}

	/**
	 * Clob字段声明
	 */
	@Column(name = "text_val", columnDefinition = "clob")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
