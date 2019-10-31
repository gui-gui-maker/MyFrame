package demo.orm.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * hibernate配置示例：one-to-many，many端
 */
@Entity
@Table(name = "TEST_MTM_C")
public class MTMC implements BaseEntity {

	private static final long serialVersionUID = -1240234293001523072L;

	private String id;
	private String remark;
	List<MTMP> mtmps;

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

	@ManyToMany(targetEntity = MTMP.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "TEST_MTM_P_C", joinColumns = { @JoinColumn(name = "fk_c") }, inverseJoinColumns = { @JoinColumn(name = "fk_p") })
	public List<MTMP> getMtmps() {
		return mtmps;
	}

	public void setMtmps(List<MTMP> mtmps) {
		this.mtmps = mtmps;
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
		if (obj instanceof MTMC) {
			MTMC no = (MTMC) obj;
			if (this.getId() == no.getId())
				return true;
		}
		return false;
	}
}
