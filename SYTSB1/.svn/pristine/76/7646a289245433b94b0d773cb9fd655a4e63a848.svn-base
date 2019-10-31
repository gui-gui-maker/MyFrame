package demo.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_SUPER_CHILD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ChildBean extends SuperBean {
	private String name;

	@Column(name = "title")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
