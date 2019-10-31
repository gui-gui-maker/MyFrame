package com.lsts.humanresources.dao;


import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.PositionTitle;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Repository("positionTitleDao")
public class PositionTitleDao extends EntityDaoImpl<PositionTitle> {
	/**
	 * 取得合同文档
	 * 
	 * @param id
	 *            ID
	 * @return 文档Byte[]
	 */
	public byte[] getFile(String id) {
		String sqlString = "select t.document_doc from TJY2_RL_POSITION_TITLE t where t.id = ?";
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
	/**
	 * 保存合同文档
	 * 
	 * @param inputStream
	 *            监察指令文档
	 * @param order
	 *            指令记录
	 */
	public String saveO(InputStream inputStream, PositionTitle order) {
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
			this.createSQLQuery(
					"update TJY2_RL_POSITION_TITLE set document_doc=? where id=?",
					in_b,order.getId()).executeUpdate();
		/*}*/
		return order.getId();
	}
}