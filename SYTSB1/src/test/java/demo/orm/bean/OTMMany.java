package demo.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * hibernate配置示例：one-to-many，many端
 */
@Entity
@Table(name = "TEST_OTM_MANY")
public class OTMMany implements BaseEntity {
	private static final long serialVersionUID = -1240234293001523072L;

	private String id;
	private String remark;

	private OTMOne one;

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

	// manytoone配置，外键为fk_id,外键不能为空
	@ManyToOne
	@JoinColumn(name = "fk_id", nullable = false)
	public OTMOne getOne() {
		return one;
	}

	public void setOne(OTMOne one) {
		this.one = one;
	}

	@Override
	public int hashCode() {
		if (this.getId() == null)
			return super.hashCode();
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.hashCode() == obj.hashCode())
			return true;
		if (obj instanceof OTMMany) {
			OTMMany no = (OTMMany) obj;
			if (this.getId() == no.getId())
				return true;
		}
		return false;
	}
}
