package com.lsts.office.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.office.bean.MeetingNotes;
import com.lsts.office.service.MeetingNotesManager;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName OrgAction
 * @JDK 1.6
 * @author
 * @date
 */
@Controller
@RequestMapping("oa/meetingNotes/info/")
public class MeetingNotesAction extends
		SpringSupportAction<MeetingNotes, MeetingNotesManager> {

	@Autowired
	private MeetingNotesManager meetingNotesManager;
	
	@Autowired
	private AttachmentManager attachmentManager;
	
	
	@RequestMapping(value="saveMeetingNotes")
	@ResponseBody
	public HashMap<String,Object> saveMeetingNotes(@RequestBody MeetingNotes meetingNotes,HttpServletRequest request) throws Exception{
		meetingNotesManager.save(meetingNotes,request);
		// 向附件中增加业务id
		if (!StringUtil.isEmpty(meetingNotes.getUploadIds())) {
			String[] files = meetingNotes.getUploadIds().replaceAll("/^,/", "").split(",");
			attachmentManager.setAttachmentBusinessId(files,meetingNotes.getId());
		}
		return JsonWrapper.successWrapper(meetingNotes);
	}
	
	@RequestMapping(value="detailMeetingNotes")
	@ResponseBody
	public HashMap<String,Object> getMeetingNotes(HttpServletRequest request) throws Exception{
		try{
			String notesId=request.getParameter("id");
			HashMap<String, Object> ret = meetingNotesManager.getMeetingNotesInfo(notesId);
			ret.put("files", attachmentManager.getBusAttachment(notesId));
			return ret;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//删除会议记录信息
	@RequestMapping(value="deletes")
	@ResponseBody
	public HashMap<String,Object> deleteMeetingNotes(HttpServletRequest request) throws Exception{
			String reqId=request.getParameter("reqId");
			String [] str=reqId.split(",");
			if(str.length>1){
				for(int i=0;i<str.length;i++){
					meetingNotesManager.deleteMeetingNotes(str[i]);
				}
			}else{
				meetingNotesManager.deleteMeetingNotes(reqId);
			}
			return JsonWrapper.successWrapper(reqId);
	
	}
	
}
