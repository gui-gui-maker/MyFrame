package demo.crud.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_ONETOONEF_DEMO")
@JsonIgnoreProperties({"oneToOneP"})
public class OneToOneF implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// 主键

	private java.util.Date birthday;// 生日
	
	private OneToOneP oneToOneP;

	public void setId(String value) {
		this.id = value;
	}

	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}

	public void setOneToOneP(OneToOneP oneToOneP) {
		this.oneToOneP = oneToOneP;
	}


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.util.Date getBirthday() {
		return this.birthday;
	}

	@OneToOne(mappedBy="oneToOneF")
	public OneToOneP getOneToOneP() {
		return oneToOneP;
	}

}
