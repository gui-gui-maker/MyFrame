package com.scts.paper.service;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.log.service.SysLogService;
import com.scts.car.bean.CarApply;
import com.scts.car.dao.CarApplyDao;
import com.scts.paper.bean.Paper;
import com.scts.paper.dao.PaperDao;

/**
 * 论文管理逻辑对象
 * 
 * @ClassName PaperService
 * @JDK 1.8
 * @author xcb
 * @date 2018-02-27 
 */
@Service("paperManage")
public class PaperService extends EntityManageImpl<Paper, PaperDao> {
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SysLogService logService;
	
	 // 保存论文（含附件）
	public void saveBasic(Paper paper, String uploadFiles){
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		//设置创建人和创建时间、状态等信息
		paper.setCreate_man(curUser.getName());
		paper.setCreate_date(new Date());
		paper.setData_status("0");
		paperDao.save(paper);
		//附件表中插入业务ID
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, paper.getId());
				}
			}
		}
	}
	
	// 逻辑删除
	public void del(HttpServletRequest request, String id) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			try {
				
				paperDao.createSQLQuery("update TJY2_PAPER_MANAGE set data_status='99' where id = ? ", id).executeUpdate();
				
				// 写入日志
				logService.setLogs(id, "论文作废", "作废申", user.getId(), user.getName(), new Date(),
						request.getRemoteAddr());
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.toString());
			}
		}
		

		// 提交
		public void submit(HttpServletRequest request, String id) {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				try {
					
					paperDao.createSQLQuery("update TJY2_PAPER_MANAGE set data_status='1' where id = ? ", id).executeUpdate();
					
					// 写入日志
					logService.setLogs(id, "论文提交", "提交", user.getId(), user.getName(), new Date(),
							request.getRemoteAddr());
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
				}
			}
		// 提交
		public void dispose(HttpServletRequest request, String id) {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
					try {
							
						paperDao.createSQLQuery("update TJY2_PAPER_MANAGE set data_status='2',sure_man=?,sure_date=? where id = ? ", user.getName(),new Date(),id).executeUpdate();
						//确认后统计积分情况
						integralTotal(id);
						// 写入日志
						logService.setLogs(id, "论文确认", "确认", user.getId(), user.getName(), new Date(),
								request.getRemoteAddr());
					} catch (Exception e) {
						e.printStackTrace();
						log.debug(e.toString());
					}
		}
		
		public void integralTotal( String id) {
			try{
			
			Paper paper = paperDao.get(id);
			//获取作者
			String paperMan = paper.getPaper_man();
			String paperScend = paper.getPaper_scend();
			String paperThird = paper.getPaper_third();
			String otherMan = paper.getOther_man();
			String paperType = paper.getPaper_type();
			
			double totalone =0;
			double totalscend =0;
			double totalthird =0;
			double totalother =0;
			
			//判断论文类型确定分数
			if("1".equals(paperType)){
				totalone=8;
				totalscend=2;
				totalthird=1;
				totalother=0.5;
				
			}else if("2".equals(paperType)){
				totalone=6;
				totalscend=2;
				totalthird=1;
				totalother=0.5;
				
			}else if("3".equals(paperType)){
				totalone=2;
				totalscend=1;
				totalthird=0.5;
				totalother=0.2;
				
			}else if("4".equals(paperType)){
				totalone=1;
				totalscend=0.5;
				totalthird=0.2;
				totalother=0.1;
				
			}
			
			
			
			//根据作者排名计分 第一作者
			if(!"".equals(paperMan)&&paperMan!=null){
				if(paperMan.contains(",")){
					String[] idArr = paperMan.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						paperDao.createSQLQuery(sql,uuid,"论文",idArr[i],totalone,"1",new Date(),paper.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					paperDao.createSQLQuery(sql,uuid,"论文",paperMan,totalone,"1",new Date(),paper.getId()).executeUpdate();
				}
			}
			
			//根据作者排名计分 第二作者
			if(!"".equals(paperScend)&&paperScend!=null){
				if(paperScend.contains(",")){
					String[] idArr = paperScend.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						paperDao.createSQLQuery(sql,uuid,"论文",idArr[i],totalscend,"2",new Date(),paper.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					paperDao.createSQLQuery(sql,uuid,"论文",paperScend,totalscend,"2",new Date(),paper.getId()).executeUpdate();
				}
			}
			
			//根据作者排名计分 第三作者
			if(!"".equals(paperThird)&&paperThird!=null){
				if(paperThird.contains(",")){
					String[] idArr = paperThird.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						paperDao.createSQLQuery(sql,uuid,"论文",idArr[i],totalthird,"3",new Date(),paper.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					paperDao.createSQLQuery(sql,uuid,"论文",paperThird,totalthird,"3",new Date(),paper.getId()).executeUpdate();
				}
			}
			//根据作者排名计分 第四作者
			if(!"".equals(otherMan)&&otherMan!=null){
				if(otherMan.contains(",")){
					String[] idArr = otherMan.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						paperDao.createSQLQuery(sql,uuid,"论文",idArr[i],totalother,"4",new Date(),paper.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					paperDao.createSQLQuery(sql,uuid,"论文",otherMan,totalother,"4",new Date(),paper.getId()).executeUpdate();
				}
			}
			}catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
			}
			
		}
		
}
