package demo.crud.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_ONETOMF_DEMO")
@JsonIgnoreProperties({"oneToMP"})
public class OneToMF implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// ${columnb.remarks}

	private String name;// ${columnb.remarks}

	private OneToMP oneToMP;// ${columnb.remarks}

	public void setId(String value) {
		this.id = value;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setOneToMP(OneToMP oneToMP) {
		this.oneToMP = oneToMP;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	@ManyToOne
	@JoinColumn(name="FK_ONETOMP_ID",nullable=false)
	public OneToMP getOneToMP() {
		return oneToMP;
	}

	
}
