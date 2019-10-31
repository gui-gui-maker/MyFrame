package com.khnt.rtdroc.files.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rtdroc.files.bean.RtdFile;
import com.khnt.rtdroc.files.service.RtdFileManager;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName RtdFileAction
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Controller
@RequestMapping("com/rtd/files")
public class RtdFileAction extends SpringSupportAction<RtdFile, RtdFileManager> {

	@Autowired
	RtdFileManager rtdFileManager;

	/*
	 * 保存文件
	 */
	@RequestMapping("saveFiles")
	@ResponseBody
	public HashMap<String, Object> saveFiles(HttpServletRequest request, RtdFile rtdFile) throws Exception {
		try {
			this.rtdFileManager.saveFiles(rtdFile);
			rtdFile.setImgData(null);// 图片数据不做回传
			rtdFile.setDrawData(null);
			return JsonWrapper.successWrapper(rtdFile);
		} catch (KhntException e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}

	}

	/*
	 * 获取文件列表
	 */
	@RequestMapping("getDefaultFolderFiles")
	@ResponseBody
	public HashMap<String, Object> getDefaultFolderFiles(HttpServletRequest request) throws Exception {
		try {

			List<RtdFile> rtdFiles = this.rtdFileManager.getDefaultFolderFiles(this.getCurrentUser().getId());

			return JsonWrapper.successWrapper(rtdFiles);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}

	/**
	 * 获取绘制数据
	 */
	@RequestMapping("getDrawDataById")
	@ResponseBody
	public HashMap<String, Object> getDrawDataById(HttpServletRequest request, String id) throws Exception {
		try {

			String drawData = this.rtdFileManager.getDrawDataById(id);

			return JsonWrapper.successWrapper(drawData);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}

	/**
	 * 获取绘制数据 根据数据附件
	 */
	@RequestMapping("getDrawDataByAttId")
	@ResponseBody
	public HashMap<String, Object> getDrawDataByAttId(HttpServletRequest request, String attId) throws Exception {
		try {

			String drawData = this.rtdFileManager.getDrawDataByAttId(attId);

			return JsonWrapper.successWrapper(drawData);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	/**
	 * 获取绘制数据 根据图片附件
	 */
	@RequestMapping("getDrawDataByImgAttId")
	@ResponseBody
	public HashMap<String, Object> getDrawDataByImgAttId(HttpServletRequest request, String attId) throws Exception {
		try {

//			String drawData = this.rtdFileManager.getDrawDataByAttId(attId);
			String drawData = this.rtdFileManager.getDrawDataByImgAttId(attId);

			return JsonWrapper.successWrapper(drawData);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	

	/**
	 * 自动保存
	 */
	@RequestMapping("saveAuto")
	@ResponseBody
	public HashMap<String, Object> saveAuto(HttpServletRequest request, RtdFile rtdFile) throws Exception {
		try {
			RtdFile newRtdFile=this.rtdFileManager.saveAuto(rtdFile);
			newRtdFile.setImgData(null);// 图片数据不做回传
			newRtdFile.setDrawData(null);
			return JsonWrapper.successWrapper(newRtdFile);
		} catch (KhntException e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
