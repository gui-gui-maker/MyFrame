package com.khnt.rtdroc.components.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rtdroc.components.bean.RtdComponents;
import com.khnt.rtdroc.components.dao.RtdComponentsDao;
import com.khnt.rtdroc.tools.Base64Img;
import com.khnt.rtdroc.tools.Utils;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtdComponentsManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Service("rtdComponentsManager")
public class RtdComponentsManager extends EntityManageImpl<RtdComponents, RtdComponentsDao> {
	@Autowired
	RtdComponentsDao rtdComponentsDao;
	@Autowired
	AttachmentManager attachmentManager;

	@Override
	public void save(RtdComponents entity) throws Exception {
		entity.setHandleTime(new Date());
		entity.setIsDel("0");
		entity.setStatus("enable");
		this.rtdComponentsDao.save(entity);
	}

	public void saveComponents(RtdComponents rtdComponents) throws Exception {
		if (StringUtil.isEmpty(rtdComponents.getId())) {
			this.save(rtdComponents);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String folder = "rtdoc" + File.separator + "components" + File.separator
				+ sdf.format(rtdComponents.getHandleTime());

		if ("absolute".equals(Utils.attachmentPathType)) {
			String attachmentPath = Utils.attachmentPath;
			if (!Utils.attachmentPath.endsWith("/")) {
				attachmentPath = attachmentPath + "/";
			}
			folder = attachmentPath + folder;
		}
		System.out.println("folder:" + folder);
		// String fname = rtdComponents.getId();
		String fname = Utils.uuid();

		if (StringUtil.isNotEmpty(rtdComponents.getImgData())) {
			rtdComponents.setImgData(rtdComponents.getImgData().substring("data:image/png;base64,".length()));
			// 保存圖片
			Attachment imgAtt = new Attachment();
			if (StringUtil.isNotEmpty(rtdComponents.getFkAttIdImg())) {
				imgAtt.setId(rtdComponents.getFkAttIdImg());
			}
			InputStream imgIs = Base64Img.GenerateImage(rtdComponents.getImgData());
			imgAtt.setFileType("application/octet-stream");
			imgAtt.setFileName(rtdComponents.getComponentName() + ".png");
			imgAtt.setBusinessId(rtdComponents.getId());
			imgAtt.setWorkItem("RTD_IMG");
			this.attachmentManager.saveAttached(imgIs, imgAtt, "disk", true, folder, fname + ".png");
			rtdComponents.setFkAttIdImg(imgAtt.getId());

		}
		if (StringUtil.isNotEmpty(rtdComponents.getDrawData())) {
			// 保存圖片
			Attachment att = new Attachment();
			if (StringUtil.isNotEmpty(rtdComponents.getFkAttIdDraw())) {
				att.setId(rtdComponents.getFkAttIdDraw());
			}
			InputStream is = new ByteArrayInputStream(rtdComponents.getDrawData().getBytes());
			att.setFileType("text/plain");
			att.setFileName(rtdComponents.getComponentName() + ".txt");
			att.setBusinessId(rtdComponents.getId());
			att.setWorkItem("RTD_DRAW");
			this.attachmentManager.saveAttached(is, att, "disk", true, folder, fname + ".txt");
			rtdComponents.setFkAttIdDraw(att.getId());
		}
		this.save(rtdComponents);
	}

	@SuppressWarnings("unchecked")
	public List<RtdComponents> getComponents() throws Exception {
		List<RtdComponents> list = this.rtdComponentsDao
				.createQuery("from RtdComponents where isDel=? and status=?", "0", "enable").list();
		List<Attachment> atts = this.rtdComponentsDao.createQuery(
				"select a from Attachment a,RtdComponents b where a.businessId=b.id and a.workItem=? and b.isDel=? and b.status=?",
				"RTD_DRAW", "0", "enable").list();
		Map<String, String> map = null;
		if (atts != null && !atts.isEmpty()) {
			map = new HashMap<String, String>();
			for (Attachment attachment : atts) {
				String filePath = Utils.getWebPhysicalPath(attachment.getFilePath());
				if (StringUtil.isNotEmpty(filePath)) {
					InputStream in = null;
					try {
						in = new FileInputStream(filePath);
						String data = IOUtils.toString(in, "utf-8");
						map.put(attachment.getBusinessId(), data);
					} catch (FileNotFoundException e) {
						LogUtil.logError(log, e);
					} finally {
						try {
							if (in != null) {
								in.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (map != null) {
			for (RtdComponents c : list) {
				c.setDrawData(map.get(c.getId()));
			}
		}

		return list;
	}

	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		super.deleteBusiness(id);
		this.attachmentManager.deleteAttachByBussinessId(id.toString());
		// this.rtdComponentsDao.createSQLQuery("update RTD_COMPONENTS set
		// is_del=?
		// where id =?", 1, id.toString())
		// .executeUpdate();
	}
}
