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
@Table(name = "TEST_MTMP_DEMO")
@JsonIgnoreProperties({"mtMCs"})
public class MtMP implements BaseEntity {

	private String id;// ${columnb.remarks}

	private String name;// ${columnb.remarks}

	private String mtmcIds;// ${columnb.remarks}
	
	private Set<MtMC> mtMCs;

	public void setId(String value) {
		this.id = value;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setMtmcIds(String value) {
		this.mtmcIds = value;
	}

	public void setMtMCs(Set<MtMC> mtMCs) {
		this.mtMCs = mtMCs;
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

	@Column(name = "MTMC_IDS")
	public String getMtmcIds() {
		return this.mtmcIds;
	}

	@ManyToMany(targetEntity=MtMC.class)
	@JoinTable(name="TEST_MTMPC_DEMO",joinColumns={@JoinColumn(name="FK_MTMP_ID")},inverseJoinColumns={@JoinColumn(name="FK_MTMC_ID")})
	public Set<MtMC> getMtMCs() {
		return mtMCs;
	}

}
