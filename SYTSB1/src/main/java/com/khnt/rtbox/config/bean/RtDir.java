package com.khnt.rtbox.config.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName RtDir
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Entity
@Table(name = "RT_DIR")
public class RtDir implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	private String rtCode;//报表代码
	private String rtDirJson;//所有目录数据
	private String rtDefaultDirJson;//默认目录数据
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="RT_CODE")
	public String getRtCode(){
		return rtCode;
	}
		
	public void setRtCode(String rtCode){
	this.rtCode=rtCode;
	}

	@Column(name="RT_DIR_JSON")
	public String getRtDirJson(){
		return rtDirJson;
	}
		
	public void setRtDirJson(String rtDirJson){
	this.rtDirJson=rtDirJson;
	}

	
	
	
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_DIR:ID="+id;

	}

	@Column(name="RT_DEFAULT_DIR_JSON")
	public String getRtDefaultDirJson() {
		return rtDefaultDirJson;
	}

	public void setRtDefaultDirJson(String rtDefaultDirJson) {
		this.rtDefaultDirJson = rtDefaultDirJson;
	}
}
