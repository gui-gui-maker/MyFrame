package com.scts.patent.service;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.log.service.SysLogService;

import com.scts.patent.bean.Patent;
import com.scts.patent.dao.PatentDao;

/**
 * 管理逻辑对象
 * 
 * @ClassName PatentService
 * @JDK 1.8
 * @author xcb
 * @date 2018-02-28
 */
@Service("patentManage")
public class PatentService extends EntityManageImpl<Patent, PatentDao> {
	@Autowired
	private PatentDao patentDao;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SysLogService logService;
	
	 // 保存（含附件）
	public void saveBasic(Patent patent, String uploadFiles){
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		//设置创建人和创建时间、状态等信息
		patent.setCreate_man(curUser.getName());
		patent.setCreate_date(new Date());
		patent.setData_status("0");
		patentDao.save(patent);
		//附件表中插入业务ID
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, patent.getId());
				}
			}
		}
	}
	
	// 逻辑删除
	public void del(HttpServletRequest request, String id) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			try {
				
				patentDao.createSQLQuery("update TJY2_PATENT_MANAGE set data_status='99' where id = ? ", id).executeUpdate();
				
				// 写入日志
				logService.setLogs(id, "作废", "作废申", user.getId(), user.getName(), new Date(),
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
					
					patentDao.createSQLQuery("update TJY2_PATENT_MANAGE set data_status='1' where id = ? ", id).executeUpdate();
					
					// 写入日志
					logService.setLogs(id, "提交", "提交", user.getId(), user.getName(), new Date(),
							request.getRemoteAddr());
				} catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
				}
			}
		// 确认
		public void dispose(HttpServletRequest request, String id) {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
					try {
							
						patentDao.createSQLQuery("update TJY2_PATENT_MANAGE set data_status='2',sure_man=?,sure_date=? where id = ? ", user.getName(),new Date(),id).executeUpdate();
						//确认后统计积分情况
						integralTotal(id);
						// 写入日志
						logService.setLogs(id, "确认", "确认", user.getId(), user.getName(), new Date(),
								request.getRemoteAddr());
					} catch (Exception e) {
						e.printStackTrace();
						log.debug(e.toString());
					}
		}
		
		public void integralTotal( String id) {
			try{
			
			Patent patent = patentDao.get(id);
			//获取作者
			String patentMan = patent.getInvent_man();
			String patentScend = patent.getInvent_scend();
			String patentThird = patent.getInvent_third();
			String otherMan = patent.getOther_man();
			String patentType = patent.getPatent_type();
			
			double totalone =0;
			double totalscend =0;
			double totalthird =0;
			double totalother =0;
			
			//判断类型确定分数
			if("1".equals(patentType)){
				totalone=6;
				totalscend=2;
				totalthird=1;
				totalother=0.5;
				
			}else if("2".equals(patentType)){
				totalone=2;
				totalscend=1;
				totalthird=0.5;
				totalother=0.2;
				
			}else if("3".equals(patentType)){
				totalone=2;
				totalscend=1;
				totalthird=0.5;
				totalother=0.2;
				
			}
			
			
			
			//根据作者排名计分 第一作者
			if(!"".equals(patentMan)&&patentMan!=null){
				if(patentMan.contains(",")){
					String[] idArr = patentMan.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						patentDao.createSQLQuery(sql,uuid,"专利",idArr[i],totalone,"1",new Date(),patent.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					patentDao.createSQLQuery(sql,uuid,"专利",patentMan,totalone,"1",new Date(),patent.getId()).executeUpdate();
				}
			}
			//根据作者排名计分 第二作者
			if(!"".equals(patentScend)&&patentScend!=null){
				if(patentScend.contains(",")){
					String[] idArr = patentScend.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						patentDao.createSQLQuery(sql,uuid,"专利",idArr[i],totalscend,"2",new Date(),patent.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					patentDao.createSQLQuery(sql,uuid,"专利",patentScend,totalscend,"2",new Date(),patent.getId()).executeUpdate();
				}
			}
			
			//根据作者排名计分 第三作者
			if(!"".equals(patentThird)&&patentThird!=null){
				if(patentThird.contains(",")){
					String[] idArr = patentThird.split(",");
					for (int i = 0; i < idArr.length; i++) {
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
								+ "values (?,?,?,?,?,?,?)";
						patentDao.createSQLQuery(sql,uuid,"专利",idArr[i],totalthird,"3",new Date(),patent.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					patentDao.createSQLQuery(sql,uuid,"专利",patentThird,totalthird,"3",new Date(),patent.getId()).executeUpdate();
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
						patentDao.createSQLQuery(sql,uuid,"专利",idArr[i],totalother,"4",new Date(),patent.getId()).executeUpdate();
					}
				}else{
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String sql="insert into TJY2_INTEGRAL_PERSONAL (id,integral_type,integral_man,integral_score,integral_level,integral_date,fk_integral_id) "
							+ "values (?,?,?,?,?,?,?)";
					patentDao.createSQLQuery(sql,uuid,"专利",otherMan,totalother,"4",new Date(),patent.getId()).executeUpdate();
				}
			}
			}catch (Exception e) {
					e.printStackTrace();
					log.debug(e.toString());
			}
			
		}
}
