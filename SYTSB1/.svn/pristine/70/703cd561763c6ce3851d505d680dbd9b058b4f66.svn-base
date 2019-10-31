package demo.orm.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * hibernate配置示例：one-to-one，主控端
 * 
 * one-to-one配置时，外键可配置在任意一端。
 */
@Entity
@Table(name = "TEST_OTO_P")
public class OTOP implements BaseEntity {
	private static final long serialVersionUID = 5223472871606598908L;
	private String id;
	private String remark;
	private OTOS otos;

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

	// OneToOne配置，级联操作为所有，抓取策略为延迟加载（将使用代理机制）
	// 外键在本端
	// @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	// @JoinColumn(name = "FK_ID")
	
	// 外键在另一端
	@OneToOne(mappedBy = "otop", orphanRemoval = true, cascade = CascadeType.ALL)
	public OTOS getOtos() {
		return otos;
	}

	public void setOtos(OTOS otos) {
		this.otos = otos;
	}

}
