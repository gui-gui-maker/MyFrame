package com.lsts.archives.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesBorrowSub;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.archives.service.ArchivesBorrowSubManager;
import com.lsts.log.service.SysLogService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;


@Controller
@RequestMapping("archivesBorrowSubAction")
public class ArchivesBorrowSubAction extends SpringSupportAction<ArchivesBorrowSub, ArchivesBorrowSubManager> {

    @Autowired
    private ArchivesBorrowSubManager archivesBorrowSubManager;
    @Autowired
	private SysLogService logService;
    @Autowired
    private ArchivesBorrowManager  archivesBorrowManager;
    /**
     * 部分归还
     * @param request
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "gh")
  	@ResponseBody
  	public Map<String, Object> gh(ServletRequest request,String ids,String fk_archives_borrow_id) throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	Map<String, Object> map = new HashMap<String, Object>();
  		String[] id=ids.split(",");
  		for(int i=0;i<id.length;i++ ){
  			ArchivesBorrowSub archivesBorrowSub=archivesBorrowSubManager.get(id[i]);
  			archivesBorrowSub.setIsBack("1");//修改子表报告状态为已归还
  			archivesBorrowSubManager.save(archivesBorrowSub);
  			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  			logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId())?archivesBorrowSub.getFkReportId():"未获取到报告ID", 
		  				"档案借阅归还", 
		  				"档案借阅归还，操作结果：已归还", 
		  				user != null ? user.getId() : "未获取到操作用户编号",
						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
						request != null ? request.getRemoteAddr() : "");
  		}
  		if(archivesBorrowSubManager.checkAllBack(fk_archives_borrow_id)){
  			ArchivesBorrow archivesBorrow=archivesBorrowManager.get(fk_archives_borrow_id);
  			archivesBorrow.setFhbgr(user.getName());
  			archivesBorrow.setFhbgsj(new Date());
  			archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYGH);//修改主表归还状态为已归还
  			archivesBorrowManager.save(archivesBorrow);
  		}else{
  			ArchivesBorrow archivesBorrow=archivesBorrowManager.get(fk_archives_borrow_id);
  			archivesBorrow.setFhbgr(user.getName());
  			archivesBorrow.setFhbgsj(new Date());
  			archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYBFGH);//修改主表归还状态为部分归还
  			archivesBorrowManager.save(archivesBorrow);
  		}
  		map.put("success", true);
    return map;
    }
}