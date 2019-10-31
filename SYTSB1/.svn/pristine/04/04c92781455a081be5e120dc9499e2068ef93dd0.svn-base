package demo.crud.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_MTMC_DEMO")
@JsonIgnoreProperties({"mtMPs"})
public class MtMC implements BaseEntity {

	private String id;// ${columnb.remarks}

	private String name;// ${columnb.remarks}

	private Set<MtMP> mtMPs;

	public void setId(String value) {
		this.id = value;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setMtMPs(Set<MtMP> mtMPs) {
		this.mtMPs = mtMPs;
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

	@ManyToMany(targetEntity = MtMP.class)
	@JoinTable(name = "TEST_MTMPC_DEMO", joinColumns = { @JoinColumn(name = "FK_MTMC_ID") }, inverseJoinColumns = { @JoinColumn(name = "FK_MTMP_ID") })
	public Set<MtMP> getMtMPs() {
		return mtMPs;
	}

}
