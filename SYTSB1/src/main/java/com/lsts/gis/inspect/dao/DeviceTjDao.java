package com.lsts.gis.inspect.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;










import com.khnt.base.Factory;
import com.khnt.utils.StringUtil;
import com.lsts.gis.inspect.bean.DeviceTjDTO;
import com.lsts.gis.quality.bean.QualityTjDTO;
import com.lsts.gis.quality.dao.QualityTjDao;
import com.lsts.inspection.dao.InspectionInfoDao;


/**
 *统计Dao
 *
 * @author 
 *
 * @date 
 */
@Repository("deviceTjDao")
public class DeviceTjDao extends HibernateDaoSupport {
	
	@Autowired
	private InspectionInfoDao inspectionInfoDao ;
	QualityTjDao qualityTjDao;
	//全年收入来源占比分析
    public List<DeviceTjDTO> getQnMoney(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as "+
" (select t.check_unit_id, "+
"    substr(t1.device_sort_code, 0, 1) as big_class, "+
"     t.receivable "+
"   from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
"   where t.fk_tsjc_device_document_id = t1.id(+) "+
"     and t.fee_status = '2' "+
"     and t.data_status != '99' ";
    	if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
    		sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
    	}
            sql=sql+"     and t.invoice_date between trunc(sysdate, 'yyyy') and add_months(trunc(sysdate, 'yyyy'), 12) - 1) "+
              " select a.check_unit_id,"+
              " sum(case when a.big_class = '1' then a.receivable else null end) as gl_fee, "+
"           sum(case when a.big_class = '2' then a.receivable else null end) as ylrq_fee, "+
"           sum(case when a.big_class = '3' then a.receivable else null end) as dt_fee, "+
"          sum(case when a.big_class = '5' then a.receivable else null end) as cnjdc_fee, "+
"           sum(case when a.big_class = '6' then a.receivable else null end) as dxyl_fee, "+
"           sum(case when a.big_class = '8' then a.receivable else null end) as ylgd_fee "+
"            from incomeInfo a group by a.check_unit_id ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String gl = vobjs[1].toString();//锅炉
				String ylrq = vobjs[2].toString();//容器
				String dt = vobjs[3].toString();//电梯
				String cc = vobjs[4].toString();//厂车
				String yl = vobjs[5].toString();//游乐
				String ylgd = vobjs[6].toString();//管道
				 tj.setUnit_name(unit_id);
				tj.setGl(gl);
				tj.setCc(cc);
				tj.setDt(dt);
				tj.setYl(yl);
				tj.setYlgd(ylgd);
				tj.setYlrq(ylrq);
	            list.add(tj);
			}
		}
		return list;
    }
    //今年月份对比统计
    public List<DeviceTjDTO> getYfMoney(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as "+
" (select t.check_unit_id, "+
"          to_char(t.invoice_date, 'mm') as invoice_month, "+
"         t.receivable "+
"     from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
"    where t.fk_tsjc_device_document_id = t1.id(+) "+
"     and t.fee_status = '2' "+
"     and t.data_status != '99' ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
            sql=sql+"     and t.invoice_date between trunc(sysdate, 'yyyy') and "+
            		"          add_months(trunc(sysdate, 'yyyy'), 12) - 1) "+
            		" select a.check_unit_id, a.invoice_month, sum(a.receivable) as Month_fee "+
            		"   from incomeInfo a "+
            		" group by a.check_unit_id, a.invoice_month "+
            		" order by a.check_unit_id, a.invoice_month ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String month = vobjs[1].toString();//月份
				String money = vobjs[2].toString();//金额
			   tj.setUnit_name(unit_id);
               tj.setMoney(money);
               tj.setMonth(month);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //上年月份对比统计
    public List<DeviceTjDTO> getSnYfMoney(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as "+
" (select t.check_unit_id, "+
"          to_char(t.invoice_date, 'mm') as invoice_month, "+
"         t.receivable "+
"     from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
"    where t.fk_tsjc_device_document_id = t1.id(+) "+
"     and t.fee_status = '2' "+
"     and t.data_status != '99' ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
            sql=sql+"     and t.invoice_date between trunc(trunc(sysdate,'yyyy')-2,'yyyy') and "+
            		"          add_months(trunc(sysdate, 'yyyy')-1, 0)) "+
            		" select a.check_unit_id, a.invoice_month, sum(a.receivable) as Month_fee "+
            		"   from incomeInfo a "+
            		" group by a.check_unit_id, a.invoice_month "+
            		" order by a.check_unit_id, a.invoice_month ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String month = vobjs[1].toString();//月份
				String money = vobjs[2].toString();//金额
			   tj.setUnit_name(unit_id);
               tj.setMoney(money);
               tj.setMonth(month);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //每天收入统计
    public List<DeviceTjDTO> getMtMoney(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as "+
" (select t.check_unit_id, t.invoice_date, t.receivable "+
"    from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
"    where t.fk_tsjc_device_document_id = t1.id(+) "+
"     and t.fee_status = '2' "+
"     and t.data_status != '99' ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
            sql=sql+"  and t.invoice_date between trunc(sysdate, 'yyyy') and "+
            		"           add_months(trunc(sysdate, 'yyyy'), 12) - 1) "+
            		"  select a.check_unit_id, a.invoice_date, sum(a.receivable) as daily_fee "+
            		"   from incomeInfo a "+
            		"  group by a.check_unit_id, a.invoice_date "+
            		"  order by  a.invoice_date desc";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < 7; j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String dates = vobjs[1].toString();//月份
				String money = vobjs[2].toString();//金额
			   tj.setUnit_name(unit_id);
               tj.setMoney(money);
               tj.setDates(dates);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    
    //按时间查询每天收入统计
    public List<DeviceTjDTO> getDateMtMoney(String unit_id,String sdate,String edate){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as "+
" (select t.check_unit_id, t.invoice_date, t.receivable "+
"    from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
"    where t.fk_tsjc_device_document_id = t1.id(+) "+
"     and t.fee_status = '2' "+
"     and t.data_status != '99' ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
            sql=sql+"  and t.invoice_date >= to_date('"+sdate+"','yyyy-mm-dd') and "+
            		"          t.invoice_date  < to_date('"+edate+"','yyyy-mm-dd')) "+
            		"  select a.check_unit_id, a.invoice_date, sum(a.receivable) as daily_fee "+
            		"   from incomeInfo a "+
            		"  group by a.check_unit_id, a.invoice_date "+
            		"  order by a.invoice_date ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String dates = vobjs[1].toString();//月份
				String money = vobjs[2].toString();//金额
			   tj.setUnit_name(unit_id);
               tj.setMoney(money);
               tj.setDates(dates);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    
    //年度对比统计
    public List<DeviceTjDTO> getNdMoney(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with yearInfo as "+
" (select trunc(sysdate, 'yyyy') + rn - 1 date0 "+
    			"     from (select rownum rn from all_objects where rownum < 366)), "+
    			" incomeInfo1 as "+
    			"  (select t.check_unit_id, t.invoice_date, sum(t.receivable) receivable1 "+
    			"     from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
    			"   where t.fk_tsjc_device_document_id = t1.id(+) "+
    			"     and t.fee_status = '2' "+
    			"     and t.data_status != '99' ";
    	if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
    		sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
    	}
    		 
    sql=sql+" and t.invoice_date between trunc(sysdate, 'yyyy') and "+
    		"         add_months(trunc(sysdate, 'yyyy'), 12) - 1 "+
    		"   group by t.check_unit_id, t.invoice_date "+
    		"   order by t.check_unit_id, t.invoice_date), "+
    		" incomeInfo2 as "+
    		"  (select t.check_unit_id, t.invoice_date, sum(t.receivable) receivable2 "+
    		"     from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
    		"    where t.fk_tsjc_device_document_id = t1.id(+) "+
    		"      and t.fee_status = '2' "+
    		"      and t.data_status != '99' "; 
    if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
    	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
    }
  sql=sql+"    and t.invoice_date between "+
		  "       trunc(add_months(trunc(sysdate, 'YYYY'), -12)) and "+
		  "         add_months(trunc(add_months(trunc(sysdate, 'YYYY'), -12)), 12) - 1 "+
		  "   group by t.check_unit_id, t.invoice_date "+
		  "   order by t.check_unit_id, t.invoice_date), "+
		  " incomeInfo3 as "+
		  "  (select t.check_unit_id, t.invoice_date, sum(t.receivable) receivable3 "+
		  "     from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
		  "    where t.fk_tsjc_device_document_id = t1.id(+) "+
		  "      and t.fee_status = '2' "+
		  "     and t.data_status != '99' ";
  if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
		sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
	}
  sql=sql+	   "    and t.invoice_date between "+
		   "      trunc(add_months(trunc(sysdate, 'YYYY'), -24)) and "+
		   "        add_months(trunc(add_months(trunc(sysdate, 'YYYY'), -24)), 12) - 1 "+
		   "   group by t.check_unit_id, t.invoice_date "+
		   "   order by t.check_unit_id, t.invoice_date) "+
		   " select a.date0, "+
		   "       sum(a1.receivable1) as daily_fee1, "+
		   "       sum(a2.receivable2) as daily_fee2, "+
		   "        sum(a3.receivable3) as daily_fee3 "+
		   "   from yearInfo a, incomeInfo1 a1, incomeInfo2 a2, incomeInfo3 a3 "+
		   "  where a.date0 = a1.invoice_date(+) "+
		   "    and add_months(a.date0, -12) = a2.invoice_date(+) "+
		   "    and add_months(a.date0, -24) = a3.invoice_date(+) "+
		   "  group by a.date0 "+
		   "  order by a.date0";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String dates = vobjs[0].toString();//日期
				String jn = vobjs[1].toString();//今年
				String qn = vobjs[2].toString();//去年
				String qqn = vobjs[3].toString();//前年
			   tj.setUnit_name(unit_id);
               tj.setDates(dates);
               tj.setJnMoney(jn);
               tj.setQnMoney(qn);
               tj.setQqnMoney(qqn);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //部门持证统计
    public List<DeviceTjDTO> getZjTj(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select t.cert_type, count(t.cert_type) cert_count "+
 " from employee_cert t, employee t1, sys_org t2 "+
    			" where t.employee_id = t1.id(+) "+
    			"   and t2.id = t1.org_id "+
    			"   and t.status != '99' "+
    			"   and t.status != '9' ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t2.id = '"+unit_id+"' ";
}
            sql=sql+"  group by t.cert_type order by t.cert_type ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String type = vobjs[0].toString();//类别
				String num = vobjs[1].toString();//数量
			   tj.setUnit_name(unit_id);
               tj.setType(type);
               tj.setNum(num);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //机电部门持证统计
    public List<DeviceTjDTO> getZjTjJd(){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select t.cert_type, count(t.cert_type) cert_count "+
 " from employee_cert t, employee t1, sys_org t2 "+
    			" where t.employee_id = t1.id(+) "+
    			"   and t2.id = t1.org_id "+
    			"   and t.status != '99' "+
    			"   and t.status != '9' "+
    	         "   and t2.org_code like 'jd%' ";
            sql=sql+"  group by t.cert_type order by t.cert_type ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String type = vobjs[0].toString();//类别
				String num = vobjs[1].toString();//数量
			   
               tj.setType(type);
               tj.setNum(num);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //承压部门持证统计
    public List<DeviceTjDTO> getZjTjCy(){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select t.cert_type, count(t.cert_type) cert_count "+
    			" from employee_cert t, employee t1, sys_org t2 "+
    			" where t.employee_id = t1.id(+) "+
    			"   and t2.id = t1.org_id "+
    			"   and t.status != '99' "+
    			"   and t.status != '9' "+
    			"   and t2.org_code like 'cy%' and t2.org_code <> 'cy7'";
            sql=sql+"  group by t.cert_type order by t.cert_type ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String type = vobjs[0].toString();//类别
				String num = vobjs[1].toString();//数量
			 
               tj.setType(type);
               tj.setNum(num);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    /**
     * 获取无损持证情况统计
     */
    public List<DeviceTjDTO> getZjTjWs(){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select t.cert_type, count(t.cert_type) cert_count "+
    			" from employee_cert t, employee t1, sys_org t2 "+
    			" where t.employee_id = t1.id(+) "+
    			"   and t2.id = t1.org_id "+
    			"   and t.status != '99' "+
    			"   and t.status != '9' "+
    			"   and t.cert_type in ('MT','PT','RT','UT','TOFD','MFL','AE','ECT')";
        sql=sql+"  group by t.cert_type order by t.cert_type ";
        List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String type = vobjs[0].toString();//类别
				String num = vobjs[1].toString();//数量
			 
				tj.setType(type);
				tj.setNum(num);
	            list.add(tj);
			}
		}
		return list;
    }
    //纠错报告统计
    public List<DeviceTjDTO> getJcTj(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with errorInfo as "+
                " (select t.check_unit_id, "+
    			"        t1.device_sort_code, "+
    			"        substr(t1.device_sort_code, 0, 1) as big_class "+
    			"   from TZSB_INSPECTION_INFO   t, "+
    			"        BASE_DEVICE_DOCUMENT   t1, "+
    			"         TZSB_REPORT_ERROR_INFO t2 "+
    			"   where t.fk_tsjc_device_document_id = t1.id(+) "+
    			"     and t.id = t2.new_info_id(+) "+
    			"     and t.data_status != '99' "+
    			"    and nvl2(t2.id, 1, 0) = '1'  ";
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
            sql=sql+"   and t.advance_time between trunc(sysdate, 'yyyy') and "+
            		"     add_months(trunc(sysdate, 'yyyy'), 12) - 1) "+
            		" select a.check_unit_id, "+
            		"       a.device_sort_code, "+
            		"       count(a.device_sort_code) as gl_count "+
            		"  from errorInfo a "+
            		"  group by a.check_unit_id, a.device_sort_code "+
            		"  order by a.check_unit_id, a.device_sort_code";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String type = vobjs[1].toString();//类别
				String num = vobjs[2].toString();//数量
			   tj.setUnit_name(unit_id);
               tj.setType(type);
               tj.setNum(num);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //查询最近3天报告
    public List<DeviceTjDTO> getBmBg(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select t.report_sn,t.report_com_name,t.advance_time,t.FLOW_NOTE_NAME,t.INSPECTION_CONCLUSION,t.id"+
          "  from TZSB_INSPECTION_INFO   t  "+
         "    where t.data_status != '99'  "+
          " and t.ENTER_TIME2 is not null   "+
         "  and t.INSPECTION_CONCLUSION is not null "+
         "  and t.report_sn  is not null ";
     /*     "   and t.ENTER_TIME2 >= sysdate - 3"; */
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
}
sql=sql+"order by t.ENTER_TIME2 desc";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			int i=0;
			if(list2.size()<50){
				i=list2.size();
			}else{
				i=50;
			}
			for (int j = 0; j < i; j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String com_name = vobjs[1].toString();//使用单位
				String report_sn = vobjs[0].toString();//报告编号
				String advance_time = vobjs[2].toString();//检验日期
				String inspection_conclusion = vobjs[4].toString();//检验结论
				String id=vobjs[5].toString();//id
			   tj.setCom_name(com_name);
               tj.setReport_sn(report_sn);
               tj.setAdvance_time(advance_time);
               tj.setInspection_conclusion(inspection_conclusion);
               tj.setNum(id);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //查询报销费用
    public List<DeviceTjDTO> getBmFy(String unit_id){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="select * from  (select t.statue status,t.user_ names,t.cl_sj dates,'培训费' types,t.department_id　from TJY2_PXFBXD t where t.statue!='DJZF' and t.statue!='REGISTER' and t.statue!='SUBMIT' and t.statue!='NO_PASS' and t.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') union " +
"select t1.status status,t1.people_concerned names,t1.bs_date dates,'费用报销' types,t1.department_id  from TJY2_FYBXD t1 where t1.status!='DJZF'  and t1.status!='REGISTER' and t1.status!='SUBMIT' and t1.status!='NO_PASS' and t1.bs_date >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') union "+
"select t2.statue status,t2.user_ names,t2.cl_sj dates,'差旅费' types,t2.department_id from TJY2_CLFBXD t2 where t2.statue!='DJZF' and t2.statue!='REGISTER' and t2.statue!='SUBMIT' and t2.statue!='NO_PASS' and t2.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss')) t3 where 1=1 "; 
if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
	sql=sql+"     and t3.department_id = '"+unit_id+"' ";
}
sql=sql+"  order by dates desc ";
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String com_name = vobjs[1].toString();//姓名
				String date = vobjs[2].toString();//报告编号
				String type = vobjs[3].toString();//检验日期
				String inspection_conclusion = vobjs[0].toString();//检验结论
			   tj.setCom_name(com_name);
               tj.setDates(date);
               tj.setType(type);
               tj.setInspection_conclusion(inspection_conclusion);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    /**
	 * 统计总数（new）
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> initCount(String unit_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "select count(t.id) "+
				 " from BASE_DEVICE_DOCUMENT t, "+
				 "     TZSB_INSPECTION_INFO t1, "+
				 "     TZSB_INSPECTION t2, "+
				 "     SYS_ORG o, "+
				 "     BASE_REPORTS r, "+
				 "     base_administrative_divisions t5, "+
				 "     TZSB_REPORT_ERROR_INFO t7, "+
				 "     (select c1.* "+
				 "        from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "+
				 "       where c.id = c1.code_table_id "+
				 "          and c.code = 'device_classify') t6, "+
				 "     (select c1.* "+
				 "        from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "+
				 "       where c.id = c1.code_table_id "+
				 "         and c.code = 'PAYMENT_STATUS') t8 "+
				 " where t.id(+) = t1.fk_tsjc_device_document_id "+
				 "  and t1.CHECK_UNIT_ID = o.id(+) "+
				 "  and t1.FK_INSPECTION_ID = t2.id(+) "+
				 "   and t1.REPORT_TYPE = r.id(+) "+
				 "   and t.device_area_code = t5.regional_code(+) "+
				 "  and t1.id = t7.new_info_id(+) "+
				 "  and t.device_sort_code = t6.value(+) "+
				 " and t1.fee_status = t8.value(+) "+
				 "  and t1.data_status='1' ";
		if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
			sql=sql+"     and t1.check_unit_id = '"+unit_id+"' ";
		}
		String annualSql = sql
				+ "and t1.ENTER_TIME2 >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') ";
		String todaySql = sql
				+ "and t1.ENTER_TIME2 >= to_date(to_char(sysdate,'yyyymmdd')||' 00:00:00','yyyymmdd hh24:mi:ss') ";
		
		List<Object> annualC = inspectionInfoDao.createSQLQuery(annualSql).list();//今年检验
		List<Object> todayC = inspectionInfoDao.createSQLQuery(todaySql).list();//今日检验
		map.put("annual", annualC);
		map.put("today", todayC);
        //今日打印
		String sql2 = sql
				+ "and t1.PRINT_TIME >= to_date(to_char(sysdate,'yyyymmdd')||' 00:00:00','yyyymmdd hh24:mi:ss') ";
		if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
			sql2=sql2+"     and t1.check_unit_id = '"+unit_id+"' ";
		}
		List<Object> list = inspectionInfoDao.createSQLQuery(sql2).list();
		map.put("todayDevices", list);
		//今年收款
		String sql3="with incomeInfo as "+
				" (select t.check_unit_id, t.invoice_date, t.receivable "+
				"    from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1 "+
				"    where t.fk_tsjc_device_document_id = t1.id(+) "+
				"     and t.fee_status = '2' "+
				"     and t.data_status= '1' ";
				if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
					sql3=sql3+"     and t.check_unit_id = '"+unit_id+"' ";
				}
				            sql3=sql3+"  and t.invoice_date >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') )"+
				            		"  select sum(a.receivable) as daily_fee "+
				            		"   from incomeInfo a ";
		List<Object> list1 = inspectionInfoDao.createSQLQuery(sql3).list();
		map.put("yyMoney", list1);
		//今年报销
		String sql4="select sum(t3.money) from  (select t.CLXJ1 money,t.department_id　from TJY2_PXFBXD t where t.statue!='DJZF' and t.statue!='REGISTER' and t.statue!='SUBMIT' and t.statue!='NO_PASS' and t.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') union "+
       " select t1.total money,t1.department_id from TJY2_FYBXD t1 where t1.status!='DJZF' and t1.status!='REGISTER' and t1.status!='SUBMIT' and t1.status!='NO_PASS' and t1.bs_date >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') union "+
       " select t2.CL_HJ_JEXJ,t2.department_id money from TJY2_CLFBXD t2 where t2.statue!='DJZF' and t2.statue!='REGISTER' and t2.statue!='SUBMIT' and t2.statue!='NO_PASS' and t2.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss')) t3 where 1=1 ";
				if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
					sql4=sql4+"     and t3.department_id = '"+unit_id+"' ";
				}
				              List<Object> list2= inspectionInfoDao.createSQLQuery(sql4).list();
		map.put("yyBxMoney", list2);
		//差旅费
		String sql5="select count(t2.id) from TJY2_CLFBXD t2 where t2.statue!='DJZF' and t2.statue!='REGISTER' and t2.statue!='SUBMIT' and t2.statue!='NO_PASS' and t2.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') and  t2.department_id = '"+unit_id+"'";
		   List<Object> list3= inspectionInfoDao.createSQLQuery(sql5).list();
		   map.put("clfsl", list3);
		//费用报销
		String sql6="select count(t1.id) from TJY2_FYBXD t1 where t1.status!='DJZF' and t1.status!='REGISTER' and t1.status!='SUBMIT' and t1.status!='NO_PASS' and t1.bs_date >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') and t1.department_id = '"+unit_id+"' ";
		 List<Object> list4= inspectionInfoDao.createSQLQuery(sql6).list();
		   map.put("fysl", list4);		
		//培训费
		String sql7="select count(t.id)　from TJY2_PXFBXD t where t.statue!='DJZF' and t.statue!='REGISTER' and t.statue!='SUBMIT' and t.statue!='NO_PASS' and t.cl_sj >= to_date(to_char(sysdate,'yyyy')||'0101 00:00:00','yyyymmdd hh24:mi:ss') and t.department_id = '"+unit_id+"'";
		 List<Object> list5= inspectionInfoDao.createSQLQuery(sql7).list();
		   map.put("pxsl", list5);	
		return map;
	}
	
    //月份详细对比统计
    public List<DeviceTjDTO> getYfMoneyXX(String unit_id,String dates){
    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
    	String sql="with incomeInfo as   "+
"  (select t.check_unit_id,    "+
"           to_char(t.invoice_date, 'dd') as invoice_month,    "+
"          t.receivable    "+
"      from TZSB_INSPECTION_INFO t, BASE_DEVICE_DOCUMENT t1    "+
"     where t.fk_tsjc_device_document_id = t1.id(+)    "+
"      and t.fee_status = '2'    "+
"     and t.data_status != '99'  ";
    	if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
			sql=sql+"     and t.check_unit_id = '"+unit_id+"' ";
		}

    	sql=sql+ "          and  to_char(t.invoice_date,'YYYY/MM') =to_char(sysdate,'YYYY')||'/"+dates+"'     ) "+
		 "                 select a.check_unit_id, a.invoice_month, sum(a.receivable) as Month_fee   "+ 
		 "                   from incomeInfo a "+
		 "                 group by a.check_unit_id, a.invoice_month     "+
		 "                 order by a.invoice_month "; 
              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
		if(list2.size()>0){
			for (int j = 0; j < list2.size(); j++) {
                DeviceTjDTO tj=new DeviceTjDTO();
				Object [] vobjs = (Object[]) list2.get(j);
				String unit = vobjs[0].toString();//部门
				String datess = vobjs[1].toString();//月份
				String money = vobjs[2].toString();//金额
			   tj.setUnit_name(unit_id);
               tj.setDates(datess);
               tj.setMonth(money);
	            list.add(tj);
			}
		}
		return list;
    	
    }
    //通过部门id获取该部门学历
    public List<QualityTjDTO> getByUnitIdXl(String unit_id){
 	   List<QualityTjDTO> list=new ArrayList<QualityTjDTO>();
 	String	sql="select education_class,numbers,round(100*ratio_to_report(numbers) over(),2) as per from("+
 				"select education_class,count(education_class) numbers from "+
 				"(select "+ 
 						"CASE when education like '%博士%' THEN '博士' "+
 						"when education like '%硕士%' THEN '硕士' "+
 						"when education like '%本科%' THEN '本科' "+
 						"when education like '%专%' THEN '专科' "+
 						"when education like '%高中%' THEN '高中' "+
 						"when education like '%初中%' THEN '初中' "+
 						"when education like '%小学%' THEN '小学' "+
 						"else null "+
 						"end education_class "+
 						"from (select   nvl(t.mba_education,t.initial_education) education  from TJY2_RL_EMPLOYEE_BASE t"
 						+ " where EMP_STATUS in (3,4)";
 	if(unit_id!=null&&!unit_id.equals("")){//查询当前登录人的部门信息
		sql=sql+"    and t.WORK_DEPARTMENT='"+unit_id+"' ";
	}
      sql=sql+" )) group by education_class having education_class is not null)";
 	List<Object> list2=inspectionInfoDao.createSQLQuery(sql).list();
 	if(list2.size()>0){
 			for (int j = 0; j < list2.size(); j++) {
              QualityTjDTO tj=new QualityTjDTO();
 				Object [] vobjs = (Object[]) list2.get(j);
 				String name = vobjs[0].toString();//学历
 				String num = vobjs[1].toString();//数量
 				String report_name = vobjs[2].toString();//报告名称
 			    tj.setName(name);;
 			    tj.setNum(num);
 	            list.add(tj);
 			}
 		}
 	return list;
 	   
    }
    
    /**
	 * 统计部门、经济类型、合计费用 今年
	 * @param unit 
	 * @return
	 */
	public List<DeviceTjDTO> statisticsFee(String unit_id){
		List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
		String sql = "select to_char(tm, 'mm'),sum(money) from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无'  "+
                     "      then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
                     "      union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无'  "+
                     "                     then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
                     "       union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无'  "+
                     "                         then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
                     "       union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无'  "+
                     "                         then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
                     "        from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
                     "         union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
                     "         from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT')  "+
                     "         union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
                     "         from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where  "+
                     "          department_id='"+unit_id+"' and  tm>= trunc(sysdate, 'yyyy') and   tm<add_months(trunc(sysdate, 'yyyy'), 12) - 1 "+
                     "          group by to_char(tm, 'mm') order by to_char(tm, 'mm')"; 

	     List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
			if(list2.size()>0){
				for (int j = 0; j < list2.size(); j++) {
	                DeviceTjDTO tj=new DeviceTjDTO();
					Object [] vobjs = (Object[]) list2.get(j);
					//String unit = vobjs[0].toString();//部门
					String month = vobjs[0].toString();//月份
					String money = vobjs[1].toString();//金额
				  // tj.setUnit_name(unit_id);
	               tj.setMoney(money);
	               tj.setMonth(month);
		            list.add(tj);
				}
			}
		return list;
	}
	 /**
		 * 统计部门、经济类型、合计费用 去年
		 * @param unit 
		 * @return
		 */
		public List<DeviceTjDTO> statisticsFeeQn(String unit_id) {
			List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
			String sql = "select to_char(tm, 'mm'),sum(money) from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无'  "+
	                     "      then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "      union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无'  "+
	                     "                     then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "       union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无'  "+
	                     "                         then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "       union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无'  "+
	                     "                         then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "        from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "         union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
	                     "         from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT')  "+
	                     "         union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
	                     "         from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where  "+
	                     "          department_id='"+unit_id+"' and   tm>= trunc(trunc(sysdate,'yyyy')-2,'yyyy') and tm< add_months(trunc(sysdate, 'yyyy')-1, 0) "+
	                     "          group by to_char(tm, 'mm') order by to_char(tm, 'mm')"; 

		     List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
				if(list2.size()>0){
					for (int j = 0; j < list2.size(); j++) {
		                DeviceTjDTO tj=new DeviceTjDTO();
						Object [] vobjs = (Object[]) list2.get(j);
						//String unit = vobjs[0].toString();//部门
						String month = vobjs[0].toString();//月份
						String money = vobjs[1].toString();//金额
					  // tj.setUnit_name(unit_id);
		               tj.setMoney(money);
		               tj.setMonth(month);
			            list.add(tj);
					}
				}
			return list;
		}
		
		   /**
		 * 查询月份支出 今年
		 * @param unit 
		 * @return
		 */
		public List<DeviceTjDTO> getYfMoneyZc(String unit_id,String dates){
			List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
			String sql = "select tm,sum(money) from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无'  "+
	                     "      then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "      union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无'  "+
	                     "                     then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "       union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无'  "+
	                     "                         then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "       union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无'  "+
	                     "                         then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
	                     "        from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
	                     "         union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
	                     "         from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT')  "+
	                     "         union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
	                     "         from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where  "+
	                     "          department_id='"+unit_id+"' and  to_char(tm,'YYYY/MM') =to_char(sysdate,'YYYY')||'/"+dates+"'   "+
	                     "          group by tm"; 

		     List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
				if(list2.size()>0){
					for (int j = 0; j < list2.size(); j++) {
		                DeviceTjDTO tj=new DeviceTjDTO();
						Object [] vobjs = (Object[]) list2.get(j);
						//String unit = vobjs[0].toString();//部门
						String month = vobjs[0].toString();//月份
						String money = vobjs[1].toString();//金额
					  // tj.setUnit_name(unit_id);
		               tj.setMoney(money);
		               tj.setMonth(month);
			            list.add(tj);
					}
				}
			return list;
		}
		  /**
				 * 每天支出 今年
				 * @param unit 
				 * @return
				 */
				public List<DeviceTjDTO> getMtMoneyZc(String unit_id){
					List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
					String sql = "select tm,sum(money) from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无'  "+
			                     "      then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
			                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
			                     "      union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无'  "+
			                     "                     then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
			                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
			                     "       union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无'  "+
			                     "                         then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
			                     "       from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
			                     "       union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无'  "+
			                     "                         then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state  "+
			                     "        from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status not in  ('REGISTER','SUBMIT')  "+
			                     "         union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
			                     "         from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT')  "+
			                     "         union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state  "+
			                     "         from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where  "+
			                     "          department_id='"+unit_id+"'  "+
			                     "          group by tm"+
				                	"         order by tm desc";

				     List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
						if(list2.size()>0){
							for (int j = 0; j <7; j++) {
				                DeviceTjDTO tj=new DeviceTjDTO();
								Object [] vobjs = (Object[]) list2.get(j);
								//String unit = vobjs[0].toString();//部门
								String month = vobjs[0].toString();//月份
								String money = vobjs[1].toString();//金额
							  // tj.setUnit_name(unit_id);
				               tj.setMoney(money);
				               tj.setMonth(month);
					            list.add(tj);
							}
						}
					return list;
				}
				 //查询最近请休假
			    public List<DeviceTjDTO> getBmQxj(String unit_id){
			    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
			    	try {
						
					
			    	String sql="select t.people_name,(select v.name from pub_code_table t, pub_code_table_values v where v.code_table_id = t.id and code = 'TJY2_RL_LEAVE_TYPE' and v.value = t.leave_type),t.create_date,t.apply_status from TJY2_RL_LEAVE t where t.dep_id='"+unit_id+"' order by t.create_date desc";
			     /*     "   and t.ENTER_TIME2 >= sysdate - 3"; */
			              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();
					if(list2.size()>0){
						int i=0;
						if(list2.size()<15){
							i=list2.size();
						}else{
							i=15;
						}
						for (int j = 0; j < i; j++) {
			                DeviceTjDTO tj=new DeviceTjDTO();
							Object [] vobjs = (Object[]) list2.get(j);
							String com_name = vobjs[1].toString();//请假原因
							String report_sn = vobjs[0].toString();//姓名
							String advance_time = vobjs[2].toString();//创建日期
							String inspection_conclusion = vobjs[3].toString();//申请状态
							inspection_conclusion=getByCode("TJY2_BG_LEAVE_STATUS",inspection_conclusion);
						   tj.setCom_name(com_name);
			               tj.setReport_sn(report_sn);
			               tj.setAdvance_time(advance_time);
			               tj.setInspection_conclusion(inspection_conclusion);
				            list.add(tj);
						}
					}
					
			    	} catch (Exception e) {
						e.printStackTrace();
					}
			    	return list;
			    }
			    //查询科研项目统计
			    public List<DeviceTjDTO> getKytj(){
			    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
			    	try {
						
					
			    	String sql="select to_char(t.fill_date,'yyyy'),count(t.id) from TJY2_SCIENTIFIC_RESEARCH t where t.status='2'  group by to_char(t.fill_date,'yyyy')";
			     /*     "   and t.ENTER_TIME2 >= sysdate - 3"; */
			              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();

						for (int j = 0; j < list2.size(); j++) {
			                DeviceTjDTO tj=new DeviceTjDTO();
							Object [] vobjs = (Object[]) list2.get(j);
							String month = vobjs[0].toString();//年份
							String num = vobjs[1].toString();//数量
					
						tj.setMonth(month);
						tj.setNum(num);
				            list.add(tj);
						}
					
					
			    	} catch (Exception e) {
						e.printStackTrace();
					}
			    	return list;
			    }
			    //查询人员职称
			    public List<DeviceTjDTO> getRyzc(){
			    	List<DeviceTjDTO> list=new ArrayList<DeviceTjDTO>();
			    	try {
						
					
			    	String sql="select t.EMP_TITLE,count(t.id)  from TJY2_RL_EMPLOYEE_BASE t where t.emp_status in('3','4') and t.EMP_TITLE in('工程师','助理工程师','技术员','高级工程师（副高级）','教授级高级工程师（正高级）','其他') group by t.EMP_TITLE";
			     /*     "   and t.ENTER_TIME2 >= sysdate - 3"; */
			              List<Object> list2= inspectionInfoDao.createSQLQuery(sql).list();

						for (int j = 0; j < list2.size(); j++) {
			                DeviceTjDTO tj=new DeviceTjDTO();
							Object [] vobjs = (Object[]) list2.get(j);
							String type = vobjs[0].toString();//类型
							String num = vobjs[1].toString();//数量
					
						tj.setType(type);
						tj.setNum(num);
				            list.add(tj);
						}
					
					
			    	} catch (Exception e) {
						e.printStackTrace();
					}
			    	return list;
			    }
			  
			    public String getByCode(String code,String value){
			 	   String name="";
			 	   String sql="select t1.name,t1.value from PUB_CODE_TABLE t,PUB_CODE_TABLE_VALUES t1 where  t.id=t1.code_table_id and t.code='"+code+"'";
			 		List<Object> list2=inspectionInfoDao.createSQLQuery(sql).list();
			 	   	if(list2.size()>0){
			 	     			for (int j = 0; j < list2.size(); j++) {
			 	     				Object [] vobjs = (Object[]) list2.get(j);
			 	     				
			 	     				String item_name = vobjs[0].toString();//报告列名
			 	     				String item_value = vobjs[1].toString();//报告列值
			 	     				if(item_value.equals(value)){
			 	     					name=item_name;
			 	     				}
			 	     			}
			 	     		}
			 	   
			 	   
			 	return name;
			    }
				
}