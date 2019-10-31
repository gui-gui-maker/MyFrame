package demo.crud.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_ONETOONEP_DEMO")
public class OneToOneP implements BaseEntity {

	private static final long serialVersionUID = 3973776331436518416L;

	private String id;// 主键

	private String name;// 姓名

	private String remark;// 备注

	private OneToOneF oneToOneF;// 关联一对一从表主键

	public void setId(String value) {
		this.id = value;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public void setOneToOneF(OneToOneF oneToOneF) {
		this.oneToOneF = oneToOneF;
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

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	@OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "FK_ONETOONEF_ID")
	public OneToOneF getOneToOneF() {
		return oneToOneF;
	}
}
