package com.fwxm.scientific.dao;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.fwxm.scientific.bean.Tjy2ScientificFiles;
import com.fwxm.scientific.bean.Tjy2ScientificFilesUpdate;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.Uploads;
import com.lsts.qualitymanage.bean.QualityAttachment;
import com.lsts.qualitymanage.bean.QualityFiles;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName QualityUpdateFileDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2ScientificFilesUpdateDao")
public class Tjy2ScientificFilesUpdateDao extends EntityDaoImpl<Tjy2ScientificFilesUpdate> {

	/**
	 * 保存质量文件
	 * */
	public String saveO(InputStream inputStream, QualityAttachment qualityAttachment) {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] data = new byte[100]; // buff用于存放循环读取的临时数据
		int rc = 0;
		try {
			// 取得文档的内容，转换数据格式
			while ((rc = inputStream.read(data, 0, 100)) > 0) {
				swapStream.write(data, 0, rc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] in_b = swapStream.toByteArray(); // in_b为转换之后的结果
		
		// 新建记录
		this.createSQLQuery("update TJY2_QUALITY_ATTACHMENT set FILE_BODY=? where id=?",in_b,qualityAttachment.getId()).executeUpdate();
		return qualityAttachment.getId();
	}
	/**获取任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityUpdateFile> getTaskAllot() {
		String hql = "from QualityUpdateFile";
		List<QualityUpdateFile> list = createQuery(hql).list();
		return list;
	}
	/**获取任务1
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityUpdateFile> getTaskAllot1() {
		String hql = "id from QualityUpdateFile";
		List<QualityUpdateFile> list = createQuery(hql).list();
		return list;
	}
	/**获取单条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityUpdateFile> getOne(String id) {
		String hql = "from QualityUpdateFile where id=?";
		List<QualityUpdateFile> list = createQuery(hql,id).list();
		//return (List<QualityUpdateFile>) list.get(0);
		return list;

	}
	
	/**
  	 * 查询流程id
  	 * */
	@SuppressWarnings("rawtypes")
	public List getFlowId(String id){
		String sql = "select t.activity_id from TJY2_QUALITY_UPDATE_FILE b, v_pub_worktask t where b.id=t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_XGSQ%' and t.STATUS='0' and b.id=?";

		List list = this.createSQLQuery(sql,id).list();

		return list;
	}
	/**
	 * 查找文件
	 * */
	@SuppressWarnings("unchecked")
	public List<QualityFiles> getfile(String fileid,String filename) {
		String hql = "from QualityFiles where fileid=? and filename=?";
		List<QualityFiles> list = createQuery(hql,fileid,filename).list();
		return list;

	}
	/**
  	 * 获取文件的的位置
  	 * */
	@SuppressWarnings("rawtypes")
	public List getF(String ids){
		String sql = "select * from PUB_ATTACHMENT t where t.business_id=?";
		List list = this.createSQLQuery(sql,ids).list();
		return list;
	}
	/**
  	 * 获取文件的的位置
  	 * */
	@SuppressWarnings("rawtypes")
	public List getFf(String id){
		String sql = "select * from PUB_ATTACHMENT t where t.business_id=?";
		List list = this.createSQLQuery(sql,id).list();
		return list;
	}
	/**
  	 * 改为新文件地址
  	 * */
	public void setFff(String ids,String name,String type,String path,String size){
		String sql = "UPDATE PUB_ATTACHMENT set FILE_NAME='"+name+"',FILE_TYPE='"+type+"',FILE_PATH='"+path+"',FILE_SIZE='"+size+"' where business_id='"+ids+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**
  	 * 全部不启用
  	 * */
	public void setstatus(String ids){
		String sql = "UPDATE TJY2_QUALITY_ATTACHMENT set STATUS='"+0+"' where business_id='"+ids+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**
  	 * 启用选中文件
  	 * */
	public void setstatus2(String id){
		String sql = "UPDATE TJY2_QUALITY_ATTACHMENT set STATUS='"+1+"' where id='"+id+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	
}