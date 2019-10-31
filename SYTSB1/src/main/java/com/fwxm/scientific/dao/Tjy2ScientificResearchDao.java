package com.fwxm.scientific.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fwxm.scientific.bean.Tjy2ScientificOpinion;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.inspection.bean.ReportDrawSign;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2ScientificResearchDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("tjy2ScientificResearchDao")
public class Tjy2ScientificResearchDao extends EntityDaoImpl<Tjy2ScientificResearch> {

	@SuppressWarnings("unchecked")
	public List<String> getProjectNoList(String typeTag, String nowYear) {
		String sql = "select t.project_no from TJY2_SCIENTIFIC_RESEARCH t where t.project_no like ?";
		List<String> list = this.createSQLQuery(sql, "%" + typeTag + "-" + nowYear + "%").list();
		return list;
	}

	/**
	 * 保存文档
	 * 
	 * @param inputStream
	 *            指令文档
	 * @param order
	 *            指令记录
	 */
	public String saveO(InputStream inputStream, Tjy2ScientificResearch research) {

		/*
		 * List rst = this .createSQLQuery(
		 * "SELECT F.FILE_DOC from TZSB_SUPERVISE_ORDER F WHERE F.ID=? FOR UPDATE"
		 * , order.getId()).list(); Blob blob = null; if (rst.size() > 0) { blob
		 * = (Blob) rst.get(0); try { // 取得文档，修改 byte data[] = new
		 * byte[inputStream.available()]; OutputStream os =
		 * blob.setBinaryStream(1L); inputStream.read(data); os.write(data);
		 * os.flush(); os.close(); } catch (Exception e) { } } else {
		 */
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
		if (research.getStatus() == null || research.getStatus().equals("0")) {
			this.createSQLQuery("update TJY2_SCIENTIFIC_RESEARCH set file_blob=? where id=?", in_b, research.getId())
					.executeUpdate();
		} else {
			this.createSQLQuery("update TJY2_SCIENTIFIC_RESEARCH set TASK_FILE=? where id=?", in_b, research.getId())
					.executeUpdate();
		}
		// 新建记录

		/* } */
		return research.getId();
	}

	/**
	 * 取得文档
	 * 
	 * @param id
	 *            ID
	 * @return 文档Byte[]
	 */
	public byte[] getFile(String id) {
		Tjy2ScientificResearch research = this.get(id);
		String sqlString = "";
		if (research.getStatus() == null || research.getStatus().equals("0")) {
			sqlString = "select t.file_blob from TJY2_SCIENTIFIC_RESEARCH t where t.id = ?";
		} else {
			sqlString = "select t.TASK_FILE from TJY2_SCIENTIFIC_RESEARCH t where t.id = ?";
		}

		List list = this.createSQLQuery(sqlString, id).list();
		Object obj = null;
		if (list.size() > 0) {
			obj = list.get(0);
		}
		if (obj instanceof byte[])
			return (byte[]) obj;
		if (obj instanceof Blob) {
			Blob blob = (Blob) obj;
			byte data[] = null;
			try {
				data = blob.getBytes(1L, (int) blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		} else {
			return null;
		}

	}
}