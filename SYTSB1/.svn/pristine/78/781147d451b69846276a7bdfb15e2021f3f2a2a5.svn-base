package demo.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * hibernate配置示例：one-to-one，受控端
 */
@Entity
@Table(name = "TEST_OTO_S")
public class OTOS implements BaseEntity {

	private static final long serialVersionUID = -3195125270022026200L;
	private String id;
	private OTOP otop;
	private String remark;

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

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// 外键在本端
	@OneToOne
	@JoinColumn(name = "fk_id")
	public OTOP getOtop() {
		return otop;
	}

	public void setOtop(OTOP otop) {
		this.otop = otop;
	}

}
