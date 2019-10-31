package com.khnt.pub.fileupload.service;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.QueryCondition;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.pub.fileupload.dao.AttachmentTsDao;
import com.khnt.utils.CompressPicture;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.dao.InspectionInfoDao;

import util.FileUtil;

/**
 * 附件管理
 */
@Service("attachmentTsManager")
@Transactional
public class AttachmentTsManager extends EntityManageImpl<Attachment, AttachmentTsDao> {

	@Autowired
	AttachmentTsDao attachmentTsDao;
	
	@Autowired
	InspectionInfoDao inspectionInfoDao;

	/**
	 * 附件的存储方式
	 */
	private static String attachmentType;

	/**
	 * 如果附件的存储方式为磁盘时,存放位置
	 */
	private static String attachmentPath;

	/**
	 * #如果附件的存储方式为磁盘时，存放位置类型
	 */
	private static String attachmentPathType;
	
	/** 如果附件的存储方式为磁盘时,校核人员手写签字压缩图片存放位置 */
	private static String mo_sign_compress_attachmentPath;
	
	/** 如果附件的存储方式为磁盘时,校核人员手写签字图片存放位置 */
	private static String mo_sign_attachmentPath;

	/**
	 * 附件存储类型：文件路径类别
	 */
	public static final String ATTACH_PATH_TYPE = "attachmentPathType";

	/**
	 * 附件存储类型：文件路径
	 */
	public static final String ATTACH_PATH = "attachmentPath";

	/**
	 * 附件存储类型：文件路径
	 */
	public static final String ATTACH_TYPE = "attachmentType";

	/**
	 * 附件存储类型：磁盘文件系统
	 */
	public static final String SAVE_TYPE_DISK = "disk";

	/**
	 * 附件存储类型：数据库
	 */
	public static final String SAVE_TYPE_DB = "db";

	/**
	 * 磁盘存储位置：相对路径
	 */
	public static final String SAVE_PATH_R = "relative";

	/**
	 * 磁盘存储位置：绝对路径
	 */
	public static final String SAVE_PATH_A = "absolute";

	public static String thumbFolder;
	
	public static String syFolder;

	public AttachmentTsManager() {
		super();
		// 系统配置参数初始化
		SysParaInf sp = Factory.getSysPara();
		attachmentType = sp.getProperty("attachmentType", SAVE_TYPE_DISK);
		attachmentPath = sp.getProperty("attachmentPath");
		mo_sign_attachmentPath = sp.getProperty("mo_sign_attachmentPath");
		mo_sign_compress_attachmentPath = sp.getProperty("mo_sign_compress_attachmentPath");
		attachmentPathType = sp.getProperty("attachmentPathType", "relative");
		thumbFolder = sp.getProperty("attachment.thumb.path", System.getProperty("java.io.tmpdir") + "appthumbs");
		syFolder = sp.getProperty("attachment.sythumb.path", System.getProperty("java.io.tmpdir") + "appsythumbs");
	}

	/**
	 * 获取系统文件存储基本路径
	 *
	 * @return
	 */
	public static String getSystemFilePath() {
		if (SAVE_PATH_R.equals(attachmentPathType))
			return Factory.getWebRoot() + File.separator + attachmentPath;
		else
			return attachmentPath;
	}

	/**
	 * 将给定的文件保存至附件管理体系
	 *
	 * @param filePath
	 * @param attachment
	 * @param saveType
	 */
	public void saveAtachedWithFile(String filePath, Attachment attachment, String saveType, String folder) {
		boolean saveDisk = true;
		if (StringUtil.isNotEmpty(saveType))
			saveDisk = SAVE_TYPE_DISK.equals(saveType);
		else
			saveDisk = SAVE_TYPE_DISK.equals(attachmentType);
		if (saveDisk) {
			if (StringUtil.isNotEmpty(attachment.getId()) && StringUtil.isNotEmpty(attachment.getFilePath())) {
				// 删除先前的文件
				File existFile = new File(getSystemFilePath() + File.separator + attachment.getFilePath());
				existFile.deleteOnExit();
			}
			String savePath;
			try {
				savePath = FileUtil.createRandomFile(filePath, getSystemFilePath());
			} catch (IOException e) {
				log.error("创建目标文件失败\n" + e.getMessage());
				throw new KhntException("保存文件失败,不能创建文件");
			}
			FileUtil.moveFile(filePath, getSystemFilePath() + File.separator + savePath);
			attachment.setFilePath(savePath);
			attachment.setSaveType(SAVE_TYPE_DISK);
			this.save(attachment);
		} else {
			try {
				this.saveAttached(new BufferedInputStream(new FileInputStream(filePath)), attachment, saveType, true,
				        folder);
			} catch (FileNotFoundException e) {
				throw new KhntException("不存在的文件:" + filePath);
			}
		}
	}

	@Override
	public void save(Attachment entity) {
		// 检查是否存在唯一编码对应的附件信息，如果存在则删除
		if (StringUtil.isNotEmpty(entity.getBusUniqueName())) {
			Attachment pre = this.getBusUniqueAttachment(entity.getBusUniqueName());
			// 如果存在并且id不同，则删除已存在的
			if (null != pre && !pre.getId().equals(entity.getId())) {
				this.deleteAttach(pre);
			}
		}
		this.attachmentTsDao.save(entity);
	}

	/**
	 * 保存附件
	 *
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @throws Exception
	 */
	public void saveAttached(InputStream inputStream, Attachment attachment, String saveType) throws Exception {
		this.saveAttached(inputStream, attachment, saveType, true);
	}

	/**
	 * 保存附件
	 *
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @throws Exception
	 */
	public void saveAttached(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB) {
		this.saveAttached(inputStream, attachment, saveType, saveDB, null);
	}

	/**
	 * /** 保存附件
	 *
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @param folder
	 *            文件要存储的文件夹
	 * @throws Exception
	 */
	public void saveAttached(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB,
	        String folder) {
		this.saveAttached(inputStream, attachment, saveType, saveDB, folder, null);
	}
	
	/**
	 * /** 按批次保存附件
	 *
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @param folder
	 *            文件要存储的文件夹
	 * @param savedFileName
	 *            文件要存储的文件名，替代自动生成的文件名
	 * @param  flag 是否是一批中第一个文件
	 * @throws Exception
	 */
	public void saveBatchAttached(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB,
	        String folder, String savedFileName,boolean flag) {

		String attType = saveType == null ? attachmentType : (saveType.equals("1") ? SAVE_TYPE_DB : SAVE_TYPE_DISK);
		
		// 如果指定了唯一业务名称，先从数据库删除该附件
		if(flag){
			if (StringUtil.isNotEmpty(attachment.getBusUniqueName())) {
				List<Attachment> busUniqeAtts = this.getBusUniqueAttachments(attachment.getBusUniqueName());
				if(null!=busUniqeAtts&&busUniqeAtts.size()>0){
					for(Attachment busUniqeAtt : busUniqeAtts){
						String path = busUniqeAtt.getFilePath();
						if (busUniqeAtt != null && path.contains(folder) && !busUniqeAtt.getId().equals(attachment.getId()))
							this.deleteAttach(busUniqeAtt);
					}
				}
			}
		}
		attachment.setSaveType(attType);// 设置存储类别
		attachment.setUploadTime(Calendar.getInstance().getTime());// 上传时间设置
		boolean isExist = StringUtil.isNotEmpty(attachment.getId());

		// 将文件存储到磁盘
		if (SAVE_TYPE_DISK.equals(attType)) {
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + attachmentPath;
			else
				realPath = attachmentPath;// 绝对路径

			String newName;
			try {
				newName = this.createRandomFile(attachment.getFileName(), realPath, folder, savedFileName);
			} catch (IOException e) {
				log.error("尝试在磁盘上创建文件失败！" + e.getMessage());
				LogUtil.logError(log, e);
				throw new KhntException("保存文件失败！");
			}

			attachment.setFilePath(newName);

			String existPath = null;
			if (isExist) {
				Attachment currentAttach = this.attachmentTsDao.get(attachment.getId());
				existPath = currentAttach.getFilePath();
				BeanUtils.copyProperties(attachment, currentAttach);
			}

			// 根据参数saveDB检查是否需要将附件信息写入数据库
			else if (saveDB)
			    // 将附件信息持久化
			    this.attachmentTsDao.save(attachment);

			try {
				FileUtil.writeFile(realPath + File.separator + newName, inputStream);// 写入文件系统
			} catch (Exception e) {
				LogUtil.logError(log, e);
				throw new KhntException("写入磁盘文件失败！");
			}
			// 删除之前的文件,同时避免删除业务指定了存储文件名称的附件（要求修改时指定的名称和原有的文件名称一致）
			// 1.0.12版本修正，之前版本会直接删除文件，所以会出现修改附件时新的文件也被删除的问题。
			if (StringUtil.isNotEmpty(existPath) && !existPath.equals(newName))
				FileUtil.delAllFile(realPath + File.separator + existPath);
		}
		// 将文件存储到DB
		else {
			this.attachmentTsDao.saveAttachToDB(attachment, inputStream);// 写入数据库
		}
	}

	/**
	 * /** 保存附件
	 *
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @param folder
	 *            文件要存储的文件夹
	 * @param savedFileName
	 *            文件要存储的文件名，替代自动生成的文件名
	 * @throws Exception
	 */
	public void saveAttached(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB,
	        String folder, String savedFileName) {

		String attType = saveType == null ? attachmentType : (saveType.equals("1") ? SAVE_TYPE_DB : SAVE_TYPE_DISK);

		// 如果指定了唯一业务名称，先从数据库删除该附件
		if (StringUtil.isNotEmpty(attachment.getBusUniqueName())) {
			Attachment busUniqeAtt = this.getBusUniqueAttachment(attachment.getBusUniqueName());
			if (busUniqeAtt != null && !busUniqeAtt.getId().equals(attachment.getId()))
				this.deleteAttach(busUniqeAtt);
		}

		attachment.setSaveType(attType);// 设置存储类别
		attachment.setUploadTime(Calendar.getInstance().getTime());// 上传时间设置
		boolean isExist = StringUtil.isNotEmpty(attachment.getId());

		// 将文件存储到磁盘
		if (SAVE_TYPE_DISK.equals(attType)) {
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + attachmentPath;
			else
				realPath = attachmentPath;// 绝对路径

			String newName;
			try {
				newName = this.createRandomFile(attachment.getFileName(), realPath, folder, savedFileName);
			} catch (IOException e) {
				log.error("尝试在磁盘上创建文件失败！" + e.getMessage());
				LogUtil.logError(log, e);
				throw new KhntException("保存文件失败！");
			}

			attachment.setFilePath(newName);

			String existPath = null;
			if (isExist) {
				Attachment currentAttach = this.attachmentTsDao.get(attachment.getId());
				existPath = currentAttach.getFilePath();
				BeanUtils.copyProperties(attachment, currentAttach);
			}

			// 根据参数saveDB检查是否需要将附件信息写入数据库
			else if (saveDB)
			    // 将附件信息持久化
			    this.attachmentTsDao.save(attachment);

			try {
				FileUtil.writeFile(realPath + File.separator + newName, inputStream);// 写入文件系统
			} catch (Exception e) {
				LogUtil.logError(log, e);
				throw new KhntException("写入磁盘文件失败！");
			}
			// 删除之前的文件,同时避免删除业务指定了存储文件名称的附件（要求修改时指定的名称和原有的文件名称一致）
			// 1.0.12版本修正，之前版本会直接删除文件，所以会出现修改附件时新的文件也被删除的问题。
			if (StringUtil.isNotEmpty(existPath) && !existPath.equals(newName))
				FileUtil.delAllFile(realPath + File.separator + existPath);
		}
		// 将文件存储到DB
		else {
			this.attachmentTsDao.saveAttachToDB(attachment, inputStream);// 写入数据库
		}
	}

	/**
	 * 复制附件
	 *
	 * @param id
	 */
	public Attachment copyAttachment(String id) {
		Attachment old = this.attachmentTsDao.get(id);
		Attachment no = new Attachment();
		BeanUtils.copyProperties(old, no, new String[] { "id" });
		if (SAVE_TYPE_DB.equals(no.getSaveType())) {
			byte[] dataArray = this.attachmentTsDao.getByteFromAttachment(id);
			this.attachmentTsDao.saveAttachToDB(no, new ByteArrayInputStream(dataArray));
		} else {
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + attachmentPath;
			else
				realPath = attachmentPath;// 绝对路径
			String newName;
			try {
				newName = this.createRandomFile(no.getFileName(), realPath, null, null);
			} catch (IOException e) {
				LogUtil.logError(log, e);
				throw new KhntException("创建磁盘文件失败！");
			}
			no.setFilePath(newName);
			String oldFilePath = realPath + File.separator + old.getFilePath();
			FileUtil.copyFile(oldFilePath, realPath + File.separator + newName);
		}

		return no;
	}

	/**
	 * 获取附件，以文件方式提供，最终的文件地址为返回对象的filePath，是一个绝对文件路径
	 *
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Attachment downloadAsFile(String id) {
		Attachment att = this.attachmentTsDao.get(id);
		Attachment result = new Attachment();
		if (att == null)
			throw new KhntException("无效的附件ID!");
		result.setFileName(att.getFileName());
		result.setId(id);
		if (SAVE_TYPE_DISK.equals(att.getSaveType())) {
			String filePath;
			if (attachmentPathType.equals(SAVE_PATH_R))
				filePath = Factory.getWebRoot() + File.separator + attachmentPath + File.separator + att.getFilePath();
			else
				filePath = attachmentPath + File.separator + att.getFilePath();
			result.setFilePath(filePath);
		} else {
			try {
				File file = this.attachmentTsDao.getFileFromAttachment(id);
				result.setFilePath(file.getPath());
			} catch (Exception e) {
				log.error("尝试写出二进制数据到临时文件失败!\n" + e.getMessage());
				throw new KhntException("下载文件失败");
			}
		}
		return result;
	}
	/**
	 * 下载附件，提供压缩功能，动态压缩
	 *
	 * @param id
	 * @return
	 */
	public File downloadCompress(File oFile,String id,Double proportion) {
		String thumbName = id + ".png";
		// 缩略图存放路径，必须是绝对的路径，默认将会使用JVM的临时目录，但是不建议这样做。
				File jthumbFolder = new File(thumbFolder);
				if (!jthumbFolder.exists())
					jthumbFolder.mkdirs();

				// ---------------- 首先寻找缩略图是否已经存在（缩略图和源文件名一样，只是存放位置不同，在原图的子文件夹中）
				String thumbImagePath = thumbFolder + File.separator + thumbName;
				File File = new File(thumbImagePath);
					Attachment fileAtt = this.downloadAsFile(id);
					File imageFile = new File(fileAtt.getFilePath());
				if (!File.exists()) {
					// 还不存在就创建一个
					// 将原图压缩，并存到缩略图文件夹
					try {
						  Image src = ImageIO.read(imageFile);  
						  int width = src.getWidth(null);  
						  int height = src.getHeight(null);  
				           /* BufferedImage image = new BufferedImage(width, height,  
				                    BufferedImage.TYPE_INT_RGB);  
				            Graphics2D g = image.createGraphics();
				            g.drawImage(src, 0, 0,,, null); 
				            g.dispose();  
				            ImageIO.write(image, "png", new File(thumbImagePath));*/
						CompressPicture.compress(oFile.getPath(), thumbImagePath, (int)(width*proportion),(int)(height*proportion), true);
						return File;
					} catch (Exception e) {
						log.error("预览图片失败！" + e.getMessage());
						return null;
					}
				}
				return File;
		
	}
	
	/**
	 * 压缩图片
	 *
	 * @param id
	 * @return
	 */
	public File compressPic(File oFile, String id, int width, int height) {
		// 缩略图存放路径，必须是绝对的路径，默认将会使用JVM的临时目录，但是不建议这样做。
		File sign_path = new File(Factory.getWebRoot() + mo_sign_compress_attachmentPath);
		if (!sign_path.exists())
			sign_path.mkdirs();

		// 首先寻找缩略图是否已经存在（缩略图和源文件名一样，只是存放位置不同，在原图的子文件夹中）
		// 原图路径
		String oFile_path = Factory.getWebRoot() + mo_sign_attachmentPath + File.separator + oFile.getPath();
		// 压缩图路径
		String sign_compress_path = Factory.getWebRoot() + mo_sign_compress_attachmentPath + File.separator + oFile.getPath();
		
		File file = new File(sign_compress_path);
		if (!file.exists()) {
			// 还不存在就创建一个
			// 将原图压缩，并存到缩略图文件夹
			try {
				CompressPicture.compress(oFile_path, sign_compress_path,
						width, height, true);
				return file;
			} catch (Exception e) {
				log.error("校核人员手写签字图片压缩失败！" + e.getMessage());
				return null;
			}
		}
		return file;

	}
	
	/**
	 * 以二进制方式下载文件，二进制数据放在返回对象的Attachment.fileBody
	 *
	 * @param id
	 * @return
	 */
	public Attachment downloadAsBytes(String id) {
		return this.download(id);
	}

	/**
	 * 获取附件，以二进制方式提供
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Attachment download(String id) {
		Attachment att = this.attachmentTsDao.get(id);
		return download(att);
	}

	public Attachment download(Attachment att) {
		if (att == null)
			throw new KhntException("无效的附件ID!");
		if (att.getSaveType().equals(SAVE_TYPE_DB)) {
			byte[] dataArray = this.attachmentTsDao.getByteFromAttachment(att.getId());
			att.setFileBody(dataArray);
		}
		return att;
	}

	/**
	 * 根据多个业务ID=下载附件
	 *
	 * @param businessIds
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public File downloadBusAttachments(String businessIds) throws Exception {
		QueryCondition qc = new QueryCondition(Attachment.class);
		qc.addCondition("businessId", "in", businessIds.split(","));
		List<Attachment> attachments = this.query(qc);
		return this.zipFiles(attachments);
	}

	/**
	 * 根据单个业务ID下载附件，可指定workItem
	 *
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public File downloadBusAttachment(String businessId, String workItem) throws Exception {
		return this.zipFiles(this.getBusAttachment(businessId, workItem));
	}

	/**
	 * 打包文件集合
	 *
	 * @param attachments
	 * @return
	 * @throws Exception
	 */
	protected File zipFiles(List<Attachment> attachments) throws Exception {
		byte[] buf = new byte[1024];
		File zipfile = new File("package.zip");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
		SysParaInf sp = Factory.getSysPara();
		Map<String, Object> attMap = new HashMap<String, Object>();
		for (Attachment attachment : attachments) {
			// 检查是否有重复的文件名称，如果有即忽略掉
			if (!attMap.containsKey(attachment.getFileName()))
				attMap.put(attachment.getFileName(), attachment);
			else
				continue;
			InputStream inputStream;
			// 存储在磁盘系统
			if (SAVE_TYPE_DISK.equals(sp.getProperty("attachmentType", SAVE_TYPE_DISK))) {
				String realPath = "";
				if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
					realPath = Factory.getWebRoot() + attachmentPath;
				else
					realPath = attachmentPath;
				realPath += File.separator + attachment.getFilePath();
				File outPutFile = new File(realPath);
				if (!(outPutFile.exists() && outPutFile.isFile() && outPutFile.canRead())) {
					log.error("指定目录不存在: " + outPutFile.getPath());
					continue;
				}
				inputStream = new FileInputStream(outPutFile);
			}
			// 存储在数据库
			else {
				byte[] data = this.attachmentTsDao.getByteFromAttachment(attachment.getId());
				inputStream = new ByteArrayInputStream(data);
			}

			BufferedInputStream bins = new BufferedInputStream(inputStream, 512);
			out.putNextEntry(new ZipEntry(attachment.getFileName()));
			int len;
			while ((len = bins.read(buf)) != -1) {
				out.write(buf, 0, len); // 关闭输入流
			}
			bins.close();
			inputStream.close();
		}
		// 关闭输出流
		out.close();

		// 返回给调用者最终压缩的文件
		return zipfile;
	}

	/**
	 * 根据ID、文件相对路径删除文件。支持多文件删除(ids,path使用分号;分隔)
	 */
	public void deleteAttach(String ids, String path) throws Exception {
		// 按照指定ID删除文件
		if (StringUtil.isNotEmpty(ids)) {
			for (String id : ids.split(";")) {
				Attachment attachment = this.get(id);
				this.deleteAttach(attachment);
			}
		}

		// 删除指定路径的文件
		if (StringUtil.isNotEmpty(path)) {
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + attachmentPath;
			else
				realPath = attachmentPath;// 绝对路径
			for (String pstr : path.split(";")) {
				realPath += File.separator + pstr;
				FileUtil.delAllFile(realPath);
			}
		}
	}

	/**
	 * 删除附件对象，如果有文件也同时删除
	 *
	 * @param attachment
	 * @throws Exception
	 */
	public void deleteAttach(Attachment attachment) {
		if (attachment == null)
			return;
		if (attachment.getSaveType().equals(SAVE_TYPE_DISK)) {// 存储于磁盘，需要删除磁盘文件
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + attachmentPath;
			else
				realPath = attachmentPath;// 绝对路径
			realPath += File.separator + attachment.getFilePath();
			FileUtil.delAllFile(realPath);
		}

		this.attachmentTsDao.removeById(attachment.getId());// 从数据删除附件记录
	}

	/**
	 * 根据附件Id设置业务ID
	 *
	 * @param attachmentId
	 *            附件ID
	 * @param businessId
	 *            业务ID
	 * @throws IOException
	 */
	public void setAttachmentBusinessId(String attachmentId, String businessId) {
		this.attachmentTsDao.updateBusinessId(new String[] { attachmentId }, businessId);
	}
	
	/**
	 * 根据附件Id设置业务ID
	 *
	 * @param attachmentId
	 *            附件ID
	 * @param businessId
	 *            业务ID
	 * @throws IOException
	 */
	public void setAttachmentBusinessId(String attachmentId, String businessId, String tz_category) {
		this.attachmentTsDao.updateBusinessId(new String[] { attachmentId }, businessId, tz_category);
	}

	/**
	 * 设置多个附件的业务ID
	 *
	 * @param attachmentIds
	 *            附件ID,字符串数组
	 * @param businessId
	 *            业务ID
	 * @throws IOException
	 */
	public void setAttachmentBusinessId(String[] attachmentIds, String businessId) {
		if (attachmentIds.length == 0)
			return;
		this.attachmentTsDao.updateBusinessId(attachmentIds, businessId);
	}

	/**
	 * 生成文件名
	 *
	 * @param originalName
	 * @param basePath
	 * @param folder
	 * @param fname
	 *            业务自定的存储文件名
	 * @return
	 * @throws IOException
	 */
	protected String createRandomFile(String originalName, String basePath, String folder, String fname)
	        throws IOException {
		// 获取原始文件的后缀
		String suffix = FileUtil.getSuffix(originalName);
		while (true) {
			boolean inFolder = StringUtil.isNotEmpty(folder);
			boolean busFileName = StringUtil.isNotEmpty(fname);

			String fileName;
			// 如果指定了文件夹，且指定了文件存储名，使用改名字作为文件名
			if (inFolder && busFileName)
				if ("".equals(FileUtil.getSuffix(fname))) {
					fileName = fname + suffix;
				} else {
					fileName = fname;
				}
			else {
				// 文件名 = 当前时间+随机数
				fileName = System.currentTimeMillis() + "" + new Double(899999 * Math.random() + 100000).intValue()
				        + suffix;
			}

			if (inFolder)
				basePath += File.separator + folder;

			File upload = new File(basePath);
			if (!upload.exists()) {
				upload.mkdirs();
			}
			File file = new File(basePath + File.separator + fileName);
			if (file.exists() && file.isFile()) {// 如果文件已存在则重新生成文件名
				if (inFolder && busFileName)
					file.delete();
				else
					continue;
			}
			if (!file.createNewFile()) {
				continue;
			}

			if (inFolder)
				fileName = folder + File.separator + fileName;

			return fileName;
		}
	}

	/**
	 * 通过业务ID和workItem获取附件列表
	 *
	 * @param businessId
	 *            业务Id
	 * @param workItem
	 *            附件标识
	 * @return 返回附件list集合
	 * @throws Exception
	 * @author jyl
	 * @author HP Zou 2014-08-28,将workItem改为可变长度参数
	 */
	@SuppressWarnings("unchecked")
	public List<Attachment> getBusAttachment(String businessId, String... workItem) throws Exception {
		Criteria c = this.attachmentTsDao.createCriteria(Restrictions.eq("businessId", businessId));
		if (workItem != null && workItem.length > 0)
			c.add(Restrictions.in("workItem", workItem));
		return c.list();
	}

	/**
	 * 通过一组业务ID获取附件
	 *
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Attachment> getBusAttachment(String[] businessId) throws Exception {
		if (businessId == null || businessId.length == 0)
			return new ArrayList<Attachment>(0);
		return this.attachmentTsDao.createCriteria(Restrictions.in("businessId", businessId)).list();
	}
	/**
	 * 以业务指定唯一标识获取附件信息(删除扫描上传)
	 *
	 * @param busUniqueName
	 * @return
	 */
	public List<Attachment> getBusUniqueAttachments(String busUniqueName) {
		List<Attachment> atts = attachmentTsDao.findBy("busUniqueName", busUniqueName);
		if (atts.size() > 0)
			return atts;
		else
			return null;
	}

	/**
	 * 以业务指定唯一标识获取附件信息
	 *
	 * @param busUniqueName
	 * @return
	 */
	public Attachment getBusUniqueAttachment(String busUniqueName) {
		List<Attachment> atts = attachmentTsDao.findBy("busUniqueName", busUniqueName);
		if (atts.size() == 1)
			return atts.get(0);
		else
			return null;
	}

	public Attachment getAttachmentByFilePath(String filePath) {
		return attachmentTsDao.findUniqueBy("filePath", filePath);
	}

	public File previewImage(String id) {
		String thumbName = id + ".thumb";
		if (checkThumbExist(thumbName)) {
			return new File(thumbFolder + File.separator + thumbName);
		} else {
			Attachment fileAtt = this.downloadAsFile(id);
			File thumb = new File(fileAtt.getFilePath());
			return this.writeImageFile(thumb, thumbName);
		}
	}

	public File previewImageWithPath(String fpath) {
		SysParaInf sp = Factory.getSysPara();
		boolean relative = AttachmentTsManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadFolderPath = sp.getProperty("attachmentPath");
		if (relative)
			uploadFolderPath = Factory.getWebRoot() + File.separator + uploadFolderPath;
		File f = new File(uploadFolderPath + File.separator + fpath);
		return this.writeImageFile(f, f.getName());
	}

	private boolean checkThumbExist(String fileName) {
		File imageFile = new File(thumbFolder + File.separator + fileName);
		if (imageFile.exists())
			return true;
		else
			return false;
	}

	private File writeImageFile(File oFile, String thumbName) {
		// 缩略图存放路径，必须是绝对的路径，默认将会使用JVM的临时目录，但是不建议这样做。
		File jthumbFolder = new File(thumbFolder);
		if (!jthumbFolder.exists())
			jthumbFolder.mkdirs();

		// ---------------- 首先寻找缩略图是否已经存在（缩略图和源文件名一样，只是存放位置不同，在原图的子文件夹中）
		String thumbImagePath = thumbFolder + File.separator + thumbName;
		File imageFile = new File(thumbImagePath);
		if (!imageFile.exists()) {
			// 还不存在就创建一个
			// 将原图压缩，并存到缩略图文件夹
			try {
				CompressPicture.compress(oFile.getPath(), thumbImagePath, 150, 150, true);
				return imageFile;
			} catch (Exception e) {
				log.error("预览图片失败！" + e.getMessage());
				return null;
			}
		}
		return imageFile;
	}
	
	public File previewSYImage(String id,String syimage,String pressText,String fontName,int fontStyle,Color color,int fontSize,float alpha,Integer x,Integer y,String style,Integer degree) {
		String thumbName = id + ".png";
		if (checkSyThumbExist(thumbName)) {
			return new File(syFolder + File.separator + thumbName);
		} else {
			Attachment fileAtt = this.downloadAsFile(id);
			File thumb = new File(fileAtt.getFilePath());
			return this.writeSYFile(thumb,syimage, thumbName, pressText, fontName, fontStyle, color, fontSize,alpha, x, y,style,degree);
		}
	}

	public File previewSyImageWithPath(String fpath,String syimage,String pressText,String fontName,int fontStyle,Color color,int fontSize,float alpha,Integer x,Integer y,String style,Integer degree) {
		SysParaInf sp = Factory.getSysPara();
		boolean relative = AttachmentTsManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadFolderPath = sp.getProperty("attachmentPath");
		if (relative)
			uploadFolderPath = Factory.getWebRoot() + File.separator + uploadFolderPath;
		File f = new File(uploadFolderPath + File.separator + fpath);
		return this.writeSYFile(f,syimage, f.getName(), pressText, fontName, fontStyle, color, fontSize,alpha, x, y,style,degree);
	}

	private boolean checkSyThumbExist(String fileName) {
		File imageFile = new File(thumbFolder + File.separator + fileName);
		if (imageFile.exists())
			return true;
		else
			return false;
	}
	
	private File writeSYFile(File oFile,String syimage, String syName,String pressText,String fontName,int fontStyle,Color color,int fontSize,float alpha,Integer x,Integer y,String style,Integer degree) {
		// 缩略图存放路径，必须是绝对的路径，默认将会使用JVM的临时目录，但是不建议这样做。
		File jthumbFolder = new File(syFolder);
		if (!jthumbFolder.exists())
			jthumbFolder.mkdirs();

		// ---------------- 首先寻找缩略图是否已经存在（缩略图和源文件名一样，只是存放位置不同，在原图的子文件夹中）
		String syImagePath = syFolder + File.separator + syName;
		File imageFile = new File(syImagePath);
		if (!imageFile.exists()) {
			// 还不存在就创建一个
			// 将原图压缩，并存到缩略图文件夹
			try {
				if("0".equals(style)){
					pressText(oFile,syImagePath,pressText,fontName,fontStyle,color,fontSize,alpha,x,y,degree);
				}else{
					pressImage(oFile,syimage,syImagePath,alpha,x,y,degree);
				}
				return imageFile;
			} catch (Exception e) {
				log.error("水印文字图片失败！" + e.getMessage());
				return null;
			}
		}
		return imageFile;
	}
	private static void pressText( File resourseFile,String syPath, String pressText, 
            String fontName, int fontStyle,Color color, int fontSize,float alpha, Integer x,  
            Integer y,Integer degree) {  
        try {  
            Image src = ImageIO.read(resourseFile);  
            int width =src.getWidth(null);  
            int height =src.getHeight(null);  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB); 
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);  
            g.rotate(Math.toRadians(degree),     
                    (double) width / 2, (double)height / 2);   
            g.setColor(color);  
            g.setFont(new Font(fontName, fontStyle, fontSize));  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
            		alpha));
            if(x==null&&y==null){
            	g.drawString(pressText, width /2, height/2);  
            }else {
            	g.drawString(pressText, x, y);  
			}
            g.dispose();  
            ImageIO.write(image, "png", new File(syPath));
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
	private final static void pressImage(File resourseFile,String syimage, String syPath, float alpha ,
            Integer x, Integer y,Integer degree) {  
        try {  
            //目标文件  
            Image src = ImageIO.read(resourseFile);  
            int width = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);  
        	g.rotate(Math.toRadians(degree),     
                (double) image.getWidth() / 2, (double) image     
                        .getHeight() / 2);   
            //水印文件  
            File _filebiao = new File(syimage);  
            Image src_biao = ImageIO.read(_filebiao);  
            int wideth_biao = src_biao.getWidth(null);  
            int height_biao = src_biao.getHeight(null); 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
            		alpha));
            if(x==null&&y==null){
            	 g.drawImage(src_biao, (width - wideth_biao) / 2,  
                         (height - height_biao) / 2, wideth_biao, height_biao, null); 
            }else{
            	 g.drawImage(src_biao, x,  
                         y, wideth_biao, height_biao, null); 
            }
            //水印文件结束  
            g.dispose();
            ImageIO.write(image, "png", new File(syPath));
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	
	/**
	 * 根据资源ID打包下载
	 * @param request
	 * @param response
	 * @param path
	 * @param id
	 * 
	 * @throws Exception
	 * @author GaoYa
	 * @date 2017-03-24 16:47
	 */
	public File zipFiles2(String path, String id, HttpServletRequest request) throws Exception {
		//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		byte[] buf = new byte[1024];
		File zipfile = new File("package.zip");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
		SysParaInf sp = Factory.getSysPara();
		//Map<String, Object> attMap = new HashMap<String, Object>();
		if (id == null) {
			String[] paths = path.split(",");
			for (int i = 0; i < paths.length; i++) {
				// 检查是否有重复的文件名称，如果有即忽略掉
				InputStream inputStream;
				// 存储在磁盘系统
				File outPutFile = new File(paths[i]);
				if (!(outPutFile.exists() && outPutFile.isFile() && outPutFile.canRead())) {
					log.error("指定目录不存在: " + outPutFile.getPath());
					continue;
				}
				inputStream = new FileInputStream(outPutFile);
				BufferedInputStream bins = new BufferedInputStream(inputStream, 512);
				out.putNextEntry(new ZipEntry(outPutFile.getName()));
				int len;
				while ((len = bins.read(buf)) != -1) {
					out.write(buf, 0, len); // 关闭输入流
				}
				bins.close();
				inputStream.close();
			}
		} else {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				Attachment att = this.attachmentTsDao.get(ids[i]);
				String filePath = "";
				if (att.getSaveType().equals("disk")) {
					boolean relative = "relative".equals(sp.getProperty("attachmentPathType"));
					// String uploadPath = sp.getProperty("attachmentPath");
					filePath = ((relative) ? Factory.getWebRoot() + "/" : "") + "/";

					String pic_type = att.getJy_pic_category();
					String attachmentPath = sp.getProperty("mo_attachmentPath");
					if ("bhg_pic".equals(pic_type)) {
						attachmentPath = sp.getProperty("mo_bhg_attachmentPath");
					} else if ("zzjy_pic".equals(pic_type)) {
						attachmentPath = sp.getProperty("mo_zzjy_attachmentPath");
					} else if ("EXAMINE_NAME".equals(pic_type)) {
						attachmentPath = sp.getProperty("mo_sign_attachmentPath");
					}
					filePath += attachmentPath + "/" + att.getFilePath();
				}
				
				// 检查是否有重复的文件名称，如果有即忽略掉
				InputStream inputStream;

				// 存储在磁盘系统
				File outPutFile = new File("\\" + filePath);
				if (!(outPutFile.exists() && outPutFile.isFile() && outPutFile.canRead())) {
					log.error("图片库存放目录不存在: " + outPutFile.getPath());
					continue;
				}
				inputStream = new FileInputStream(outPutFile);

				BufferedInputStream bins = new BufferedInputStream(inputStream, 512);
				out.putNextEntry(new ZipEntry(att.getFileName()));
				int len;
				while ((len = bins.read(buf)) != -1) {
					out.write(buf, 0, len); // 关闭输入流
				}
				bins.close();
				inputStream.close();
			}
		}

		// 关闭输出流
		out.close();

		// 返回给调用者最终压缩的文件
		return zipfile;
	}
	
	@SuppressWarnings("unchecked")
	public List<Attachment> getAttachment(String businessId, String pic_category) throws Exception {
		List<Attachment> list = new ArrayList<Attachment>();
		String hql = "from Attachment where businessId=? and jy_pic_category like '%"+pic_category+"%'";
		list = attachmentTsDao.createQuery(hql, businessId).list();
		return list;
	}

	public void addScanUploader(String userId, String userName, String id) {
		inspectionInfoDao.createQuery("update InspectionInfo set scan_upload_by=?,scan_upload_by_name=?,"
				+ "scan_upload_time=? where id = ?",userId,userName,new Date(), 
				id).executeUpdate();
	}

	
}