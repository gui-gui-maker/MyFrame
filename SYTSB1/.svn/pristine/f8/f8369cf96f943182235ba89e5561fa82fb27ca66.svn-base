package demo.crud.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TEST_ONETOMP_DEMO")
public class OneToMP implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// ${columnb.remarks}

	private String name;// ${columnb.remarks}
	
	private Set<OneToMF> oneToMFs;

	public void setId(String value) {
		this.id = value;
	}

	public void setName(String value) {
		this.name = value;
	}

	public void setOneToMFs(Set<OneToMF> oneToMFs) {
		this.oneToMFs = oneToMFs;
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

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY,mappedBy="oneToMP")
	public Set<OneToMF> getOneToMFs() {
		return oneToMFs;
	}

}
