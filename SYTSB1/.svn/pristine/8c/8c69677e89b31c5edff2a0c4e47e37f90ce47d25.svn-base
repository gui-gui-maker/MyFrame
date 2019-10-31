package com.khnt.pub.fileupload.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.common.utils.FastDFSUtil;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.utils.StringUtil;

/**
 * 附件管理的DAO,采用Hibernate实现。
 * 
 * @author 邵 林 2011-06-27
 * @author 邹洪平 2012-9-20
 */
@Repository("attachmentDao")
public class AttachmentDao extends EntityDaoImpl<Attachment> {

	/**
	 * 将文件写进数据库
	 * 
	 * @param attachment
	 * @param data
	 */
	public void saveAttachToDB(final Attachment attachment, final InputStream inputStream) {
		super.getSession().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				String dbname = connection.getMetaData().getDatabaseProductName();
				boolean isOralce = dbname.toLowerCase().contains("oracle");
				boolean isExist = StringUtil.isNotEmpty(attachment.getId());
				boolean autoCommit = connection.getAutoCommit();
				connection.setAutoCommit(false);
				if (!isExist) {
					final String id = UUID.randomUUID().toString().replaceAll("-", "");
					attachment.setId(id);
					attachment.setFilePath(attachment.getId());
					PreparedStatement stmt = connection
							.prepareStatement("INSERT INTO PUB_ATTACHMENT(ID,FILE_NAME,FILE_TYPE,FILE_SIZE,BUSINESS_ID,UPLOAD_TIME,UPLOADER,UPLOADER_NAME,SAVE_TYPE,WORK_ITEM,BUS_UNIQUE_NAME,FILE_PATH,FILE_BODY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,"
									+ (isOralce ? "EMPTY_BLOB()" : "?") + ")");
					stmt.setString(1, attachment.getId());
					stmt.setString(2, attachment.getFileName());
					stmt.setString(3, attachment.getFileType());
					stmt.setLong(4, attachment.getFileSize());
					stmt.setString(5, attachment.getBusinessId());
					stmt.setTimestamp(6, new Timestamp(attachment.getUploadTime().getTime()));
					stmt.setString(7, attachment.getUploader());
					stmt.setString(8, attachment.getUploaderName());
					stmt.setString(9, attachment.getSaveType());
					stmt.setString(10, attachment.getWorkItem());
					stmt.setString(11, attachment.getBusUniqueName());
					stmt.setString(12, attachment.getId());
					if (!isOralce)
						stmt.setBinaryStream(13, inputStream);
					stmt.execute();
					stmt.close();
				}
				// 如果为oracle数据库，单独设置二进制内容
				// 或者附件已经存在，这里单独更新二进制内容
				if (isOralce || isExist) {
					PreparedStatement stmt = connection
							.prepareStatement("SELECT F.FILE_BODY FB FROM PUB_ATTACHMENT F WHERE F.ID=? FOR UPDATE");
					stmt.setString(1, attachment.getId());
					ResultSet rst = stmt.executeQuery();
					Blob blob = null;
					if (rst.next()) {
						blob = (Blob) rst.getObject(1);
						try {
							byte[] data = new byte[inputStream.available()];
							OutputStream os = blob.setBinaryStream(1);
							inputStream.read(data);
							os.write(data);
							os.flush();
							os.close();
						}
						catch (Exception e) {
							throw new SQLException(e);
						}
					}
					rst.close();
					stmt.close();
				}
				connection.commit();
				connection.setAutoCommit(autoCommit);
			}
		});
	}

	/**
	 * 从数据库取出附件二进制数据
	 * 
	 * @param id
	 * @return
	 */
	public byte[] getByteFromAttachment(final String id) {
		Object obj = super.createSQLQuery("SELECT F.FILE_BODY FROM PUB_ATTACHMENT F WHERE F.ID=?", id).uniqueResult();
		if (obj instanceof byte[])
			return (byte[]) obj;
		else if (obj instanceof Blob) {

			Blob blob = (Blob) obj;
			byte[] data = null;
			try {
				data = blob.getBytes(1, (int) blob.length());
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return data;
		}
		else
			return null;
	}

	/**
	 * 将存储于数据库的二进制附件写出到一个File
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public File getFileFromAttachment(String id) throws Exception {
		Object obj = super.createSQLQuery("SELECT F.FILE_BODY FROM PUB_ATTACHMENT F WHERE F.ID=?", id).uniqueResult();
		File file = File.createTempFile(id, ".tmp");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		if (obj instanceof byte[]) {
			byte[] bytes = (byte[]) obj;
			bos.write(bytes);
			bos.close();
		}
		else if (obj instanceof Blob) {
			Blob blob = (Blob) obj;
			InputStream is = blob.getBinaryStream();
			int len;
			byte[] tmp = new byte[10240];
			while ((len = is.read(tmp)) > 0) {
				bos.write(tmp, 0, len);
			}
			bos.close();
		}
		else {
			bos.close();
			throw new KhntException("写出文件数据到文件失败！");
		}
		return file;
	}

	public void updateBusinessId(String[] attId, String busId) {
		Query query = super.createQuery("update Attachment set businessId=:busId where id in (:id)");
		query.setString("busId", busId);
		query.setParameterList("id", attId);
		query.executeUpdate();
	}
	
	public void updateBusinessId(String[] attId, String busId, String tz_category) {
		Query query = super.createQuery("update Attachment set businessId=:busId,tz_category=:tz_category where id in (:id)");
		query.setString("busId", busId);
		query.setParameterList("id", attId);
		query.setString("tz_category", tz_category);
		query.executeUpdate();
	}
	
	/** 
	 * 获取文件流
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStreamFromAttachment(String id) throws Exception {
		Object obj = super.createSQLQuery("SELECT F.FILE_BODY FROM PUB_ATTACHMENT F WHERE F.ID=?", id).uniqueResult();
		FileInputStream inputStream = null;
		
		if(obj == null) {
			// 附件保存到数据库从文件系统中读取
			Attachment attachment = get(id);
			String attachmentPosition = Factory.getSysPara().getProperty("attachmentPosition", "local");
			if("local".equals(attachmentPosition)) {
				File file = new File(Factory.getWebRoot()+"upload"+"/"+attachment.getFilePath());
				inputStream = new FileInputStream(file);
			}else {
				obj = FastDFSUtil.downloadByte(attachment.getFilePath());
			}
		}
		
		/*if (obj instanceof byte[]) {
			byte[] bytes = (byte[]) obj;
			inputStream = new ByteArrayInputStream(bytes); 
		} else if (obj instanceof Blob) {
			Blob blob = (Blob) obj;
			inputStream = blob.getBinaryStream();
		} else {
			throw new KhntException("写出文件数据到文件失败！");
		}*/
		return inputStream;
	}
}
