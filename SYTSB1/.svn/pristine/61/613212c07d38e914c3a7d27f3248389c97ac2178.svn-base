package demo.deviceClassify.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/** 
 * @author 作者 幸鑫
 * @JDK 1.6
 * @version 创建时间：2014年12月17日 上午9:21:15 
 * 类中数据分为种类（一级），类别（二级），品种（三级也是最低级）
 */

@Entity
/*@JsonIgnoreProperties(ignoreUnknown = true)*/
@Table(name = "base_device_classify")
public class DeviceClassify implements BaseEntity{
	private	String	id	;  //	ID
	private	String	pid	;  //	父级ID
	private	String	device_sort_code	;  //	设备编号
	private	String	device_sort_name	;  //	设备名称
	private	String	device_sort_remark	;  //	备注
	private	String	tree_level	;  //	层级
	private	String	tree_code	;  //	树级代码
	private	String	is_leaves_node	;  //	是否树叶节点
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDevice_sort_code() {
		return device_sort_code;
	}
	public void setDevice_sort_code(String device_sort_code) {
		this.device_sort_code = device_sort_code;
	}
	public String getDevice_sort_name() {
		return device_sort_name;
	}
	public void setDevice_sort_name(String device_sort_name) {
		this.device_sort_name = device_sort_name;
	}
	public String getDevice_sort_remark() {
		return device_sort_remark;
	}
	public void setDevice_sort_remark(String device_sort_remark) {
		this.device_sort_remark = device_sort_remark;
	}
	public String getTree_level() {
		return tree_level;
	}
	public void setTree_level(String tree_level) {
		this.tree_level = tree_level;
	}
	public String getTree_code() {
		return tree_code;
	}
	public void setTree_code(String tree_code) {
		this.tree_code = tree_code;
	}
	public String getIs_leaves_node() {
		return is_leaves_node;
	}
	public void setIs_leaves_node(String is_leaves_node) {
		this.is_leaves_node = is_leaves_node;
	}
	
	

}
