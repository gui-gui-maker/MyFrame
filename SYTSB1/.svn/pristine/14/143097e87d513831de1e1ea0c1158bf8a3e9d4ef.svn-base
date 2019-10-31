package demo.fileupload.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;
/**
 * 
 * 上传下载测试 bean
 *
 * @author jyl
 *
 */
@Entity
@Table(name = "TEST_UPLOAD_DEMO")
public class TestUploadDemo implements BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// ${columnb.remarks}

	private String title;// 标题

	public void setId(String value) {
		this.id = value;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

}
