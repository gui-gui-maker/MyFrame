package demo.expimp.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_EXPIMP")
public class DemoBusinessBean implements BaseEntity {
	private static final long serialVersionUID = -7348479157884883434L;
	String id;
	String str;
	Boolean bool;
	Date date;
	Integer intVal;
	Long longVal;
	Float floatVal;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "T_STRING")
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Column(name = "T_BOOL")
	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	@Column(name = "T_DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "T_INT")
	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	@Column(name = "T_LONG")
	public long getLongVal() {
		return longVal;
	}

	public void setLongVal(long longVal) {
		this.longVal = longVal;
	}

	@Column(name = "T_FLOAT")
	public float getFloatVal() {
		return floatVal;
	}

	public void setFloatVal(float floatVal) {
		this.floatVal = floatVal;
	}

}
