package com.scts.cspace.file.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * @author XCB
 * @date 2016-10-24
 * @summary 物理资源属性
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tjypt_file_info")
public class TjyptFileInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;// 物理资源属性ID
	private String file_upload_user_id;// 资源的上传日期
	private Date file_upload_date;// 上传资源的相对系统路径
	private String file_path;// 该资源在线打开的次数
	private String file_open_count;// 该资源下载的次数
	private String file_download_count;// 资源的上传人ID
	private String fk_attachment_id;//  资源ID

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile_upload_user_id() {
		return file_upload_user_id;
	}

	public void setFile_upload_user_id(String file_upload_user_id) {
		this.file_upload_user_id = file_upload_user_id;
	}

	public Date getFile_upload_date() {
		return file_upload_date;
	}

	public void setFile_upload_date(Date file_upload_date) {
		this.file_upload_date = file_upload_date;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_open_count() {
		return file_open_count;
	}

	public void setFile_open_count(String file_open_count) {
		this.file_open_count = file_open_count;
	}

	public String getFile_download_count() {
		return file_download_count;
	}

	public void setFile_download_count(String file_download_count) {
		this.file_download_count = file_download_count;
	}

	public String getFk_attachment_id() {
		return fk_attachment_id;
	}

	public void setFk_attachment_id(String fk_attachment_id) {
		this.fk_attachment_id = fk_attachment_id;
	}

}
