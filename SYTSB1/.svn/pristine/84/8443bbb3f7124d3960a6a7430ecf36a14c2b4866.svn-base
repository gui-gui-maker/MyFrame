package com.khnt.rtdroc.files.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rtdroc.tools.Utils;
import com.khnt.rtdroc.files.bean.RtdFile;
import com.khnt.rtdroc.files.dao.RtdFileDao;
import com.khnt.rtdroc.tools.Base64Img;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtdFileManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Service("rtdFileManager")
public class RtdFileManager extends EntityManageImpl<RtdFile, RtdFileDao> {
	@Autowired
	RtdFileDao rtdFileDao;
	@Autowired
	AttachmentManager attachmentManager;

	@Override
	public void save(RtdFile entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (StringUtil.isEmpty(entity.getId())) {
			entity.setHandleTime(new Date());
			entity.setFkUserIdHandle(user.getId());
			entity.setHandleUserName(user.getUserName());
			entity.setFkOrgIdHandle(user.getDepartment().getId());
			entity.setHandleOrgName(user.getDepartment().getOrgName());
			entity.setStatus("create");
			entity.setIsDel("0");
			entity.setFileType("file");
			// 创建默认文件夹
			RtdFile defaultFolder = this.getDefaultFolder(user.getDepartment().getId());
			entity.setPid(defaultFolder.getId());
		}
		if (StringUtil.isEmpty(entity.getFileName())) {
			entity.setFileName("未命名" + new SimpleDateFormat("mmddSSS").format(entity.getHandleTime()));
		}

		this.rtdFileDao.save(entity);
	}

	@SuppressWarnings("unchecked")
	private RtdFile getDefaultFolder(String userId) throws Exception {
		List<RtdFile> list = this.rtdFileDao.createQuery("from RtdFile where pid=null and fileType=? and fkUserIdHandle=?", "folder", userId).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		RtdFile defaultFolder = new RtdFile();
		defaultFolder.setFileName("默认文件夹");
		defaultFolder.setHandleTime(new Date());
		defaultFolder.setFkUserIdHandle(user.getId());
		defaultFolder.setHandleUserName(user.getUserName());
		defaultFolder.setFkOrgIdHandle(user.getDepartment().getId());
		defaultFolder.setHandleOrgName(user.getDepartment().getOrgName());
		defaultFolder.setStatus("create");
		defaultFolder.setIsDel("0");
		defaultFolder.setFileType("folder");
		this.rtdFileDao.save(defaultFolder);
		return defaultFolder;
	}

	public void saveFiles(RtdFile rtdFile) throws Exception {
		if (StringUtil.isEmpty(rtdFile.getId())) {
			this.save(rtdFile);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String folder = "rtdoc" + File.separator + "files" + File.separator + sdf.format(rtdFile.getHandleTime());

		if ("absolute".equals(Utils.attachmentPathType)) {
			String attachmentPath = Utils.attachmentPath;
			if (!Utils.attachmentPath.endsWith("/")) {
				attachmentPath = attachmentPath + "/";
			}
			folder = attachmentPath + folder;
		}
		String fname = Utils.uuid();

		if (StringUtil.isNotEmpty(rtdFile.getImgData())) {
			rtdFile.setImgData(rtdFile.getImgData().substring("data:image/png;base64,".length()));
			// 保存圖片
			Attachment imgAtt = new Attachment();
			if (StringUtil.isNotEmpty(rtdFile.getFkAttIdImg())) {
				imgAtt.setId(rtdFile.getFkAttIdImg());
			}
			InputStream imgIs = Base64Img.GenerateImage(rtdFile.getImgData());
			imgAtt.setFileType("text/plain");
			imgAtt.setFileName(rtdFile.getFileName() + ".png");
			imgAtt.setBusinessId(rtdFile.getId());
			imgAtt.setWorkItem("RTD_IMG");
			this.attachmentManager.saveAttached(imgIs, imgAtt, "disk", true, folder, fname + ".png");
			rtdFile.setFkAttIdImg(imgAtt.getId());
		}
		if (StringUtil.isNotEmpty(rtdFile.getDrawData())) {
			// 保存圖片
			Attachment att = new Attachment();
			if (StringUtil.isNotEmpty(rtdFile.getFkAttIdDraw())) {
				att.setId(rtdFile.getFkAttIdDraw());
			}
			InputStream is = new ByteArrayInputStream(rtdFile.getDrawData().getBytes());

			att.setFileType("text/plain");
			att.setFileName(rtdFile.getFileName() + ".txt");
			att.setBusinessId(rtdFile.getId());
			att.setWorkItem("RTD_DRAW");
			// this.attachmentManager.saveAttached(is, att, "disk");
			this.attachmentManager.saveAttached(is, att, "disk", true, folder, fname + ".txt");
			rtdFile.setFkAttIdDraw(att.getId());
		}
		this.save(rtdFile);

	}

	/*
	 * 获取默认文件夹下所有文件
	 */
	@SuppressWarnings("unchecked")
	public List<RtdFile> getDefaultFolderFiles(String userId) throws Exception {
		RtdFile defaultFolder = this.getDefaultFolder(userId);
		List<RtdFile> list = this.rtdFileDao.createQuery("from RtdFile where pid=? and fkUserIdHandle=? and isDel=? and fileType=? ", defaultFolder.getId(), userId, "0", "file")
				.list();
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return null;
	}

	/**
	 * 获取绘制数据
	 * 
	 * @param id
	 * @return
	 */
	public String getDrawDataById(String id) throws Exception {
		RtdFile rtdFile = this.get(id);
		if (rtdFile == null) {
			return null;
		}

		if (StringUtil.isEmpty(rtdFile.getFkAttIdDraw())) {
			return null;
		}
		return this.getDrawDataByAttId(rtdFile.getFkAttIdDraw());
	}

	public String getDrawDataByAttId(String attId) throws IOException {
		Attachment att = this.attachmentManager.get(attId);
		File file = new File(Utils.getWebPhysicalPath(att.getFilePath()));
		String drawData = FileUtils.readFileToString(file, "UTF-8");
		return drawData;
	}

	/**
	 * 
	 * @param attId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getDrawDataByImgAttId(String attId) throws Exception {
		// 先根据图片id查询绘制内容
		List<RtdFile> list = this.rtdFileDao.createQuery("from RtdFile where fkAttIdImg=? and isDel=? and fileType=? ", attId, "0", "file").list();
		if (list != null && !list.isEmpty()) {
			RtdFile rtdFile = list.get(0);
			return this.getDrawDataByAttId(rtdFile.getFkAttIdDraw());
		}
		return null;
	}

	/**
	 * 自动生成文件名文件类型
	 * 
	 * @param rtdFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public RtdFile saveAuto(RtdFile rtdFile) throws Exception {
		RtdFile newRtdFile = null;
		if ("null".equalsIgnoreCase(rtdFile.getFkAttIdImg()) || "undefined".equalsIgnoreCase(rtdFile.getFkAttIdImg())) {
			rtdFile.setFkAttIdImg(null);
		}
		if (StringUtil.isNotEmpty(rtdFile.getFkAttIdImg())) {
			List<RtdFile> list = this.rtdFileDao.createQuery("from RtdFile where fkAttIdImg=? and isDel=? and fileType=? ", rtdFile.getFkAttIdImg(), "0", "file").list();
			if (list != null && !list.isEmpty()) {
				newRtdFile = list.get(0);
				newRtdFile.setDrawData(rtdFile.getDrawData());
				newRtdFile.setImgData(rtdFile.getImgData());
			} else {
				newRtdFile = rtdFile;
				String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				newRtdFile.setFileName(fileName);
				newRtdFile.setFileType("file");
			}
		} else {
			newRtdFile = rtdFile;
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			newRtdFile.setFileName(fileName);
			newRtdFile.setFileType("file");
		}
		this.saveFiles(newRtdFile);
		return newRtdFile;
	}

}
