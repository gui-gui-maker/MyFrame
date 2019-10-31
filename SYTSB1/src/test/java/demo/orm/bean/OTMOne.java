package demo.orm.bean;

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

/**
 * hibernate配置示例：one-to-many，one端
 */
@Entity
@Table(name = "TEST_OTM_ONE")
public class OTMOne implements BaseEntity {
	private static final long serialVersionUID = -1240234293001523072L;

	private String id;
	private String remark;
	private Set<OTMMany> many;

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

	// onetomany配置，级联操作为所有，抓取策略为延迟加载（将使用代理机制）
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "one", fetch = FetchType.LAZY, orphanRemoval = true)
	public Set<OTMMany> getMany() {
		return many;
	}

	public void setMany(Set<OTMMany> many) {
		this.many = many;
	}
}
