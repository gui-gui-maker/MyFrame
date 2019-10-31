package com.lsts.expert.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.expert.bean.ExpertPre;
import com.lsts.expert.bean.ExpertRecord;
import com.lsts.expert.bean.ExpertRecordList;
import com.lsts.expert.dao.ExpertPreDao;
import com.lsts.expert.dao.ExpertRecordDao;
import com.lsts.expert.dao.ExpertRecordListDao;
import com.lsts.humanresources.dao.EmployeeBaseDao;


@Service("expertRecordService")
public class ExpertRecordService extends EntityManageImpl<ExpertRecord, ExpertRecordDao> {

	@Autowired
	private ExpertRecordDao expertRecordDao;
	@Autowired
	private ExpertPreDao expertPreDao;

	public List<Object[]> getExpByType(String id) {
		ExpertRecord expertRecord = expertRecordDao.get(id);
		//未参加专家
		String noComeOp = "";
		if(expertRecord.getNocome_op()!=null){
			noComeOp = expertRecord.getNocome_op().replace(",", "','");
		}
		
		
		//专家类型
		String expertType = expertRecord.getExpert_type();
		String [] expertTypes = expertType.split(",");
		List<Object[]> persons = new ArrayList<Object[]>();
		if(expertTypes.length>0){
			for (String string : expertTypes) {
				String sql = "select emp_name, person_type from TJY2_RL_EMPLOYEE_BASE where person_type like '%"+string+"%' ";
				
				if(expertRecord.getNocome_op()!=null){
					sql = "select emp_name, person_type from TJY2_RL_EMPLOYEE_BASE where person_type like '%"+string+"%' and id not in ('"+noComeOp+"')";
				}
				
				persons.addAll(expertRecordDao.createSQLQuery(sql).list());
			}
		}
		return persons;
	}
	@Autowired
	private ExpertRecordListDao recordListDao;
	@Autowired
	private EmployeeBaseDao employeeBaseDao;

	public HashMap<String, Object> randomExport(String id, HashMap<String, Object> map) {
		
		ExpertRecord expertRecord = expertRecordDao.get(id);
		//专家类型
		String expertType = expertRecord.getExpert_type();
		String [] expertTypes = expertType.split(",");
		
		//机选专家数量
		Integer expertNum = expertRecord.getExpert_num();
		//每组平均数量
		
		//未参加专家
		String noComeOp = "";
		if(expertRecord.getNocome_op()!=null){
			noComeOp = expertRecord.getNocome_op().replace(",", "','");
		}
		
		List<Integer> opCount = new ArrayList<Integer>();
		int summ = 0;
		for (int i = 0; i < expertTypes.length; i++) {

			String sql = "select count(t.id) from TJY2_RL_EMPLOYEE_BASE t where t.person_type like '%"+expertTypes[i]
					+"%'";
			if(expertRecord.getNocome_op()!=null){
				sql = "select count(t.id) from TJY2_RL_EMPLOYEE_BASE t where t.person_type like '%"+expertTypes[i]
						+"%'  and t.id not in ('"+noComeOp+"')";
			}
			@SuppressWarnings("unchecked")
			List<Object> list = employeeBaseDao.createSQLQuery(sql).list();
			int sum = Integer.parseInt(list.get(0).toString());
			opCount.add(sum);
			summ = summ + sum;
		}
		
		int sumb = 0 ;
		int max = 0;
		int pre = 0;
		List<Integer> opCountChose = new ArrayList<Integer>();
		for (int i = 0; i < opCount.size(); i++) {
			Integer ii = opCount.get(i);
			int c = (int) Math.floor((double)ii/summ*expertNum);
			if(c==0){
				c = 1;
			}
			sumb = sumb + c;
			if(ii>pre){
				
				max = i;
				pre = ii;
				
			}
			opCountChose.add(c);
		}
		
		if(sumb<expertNum){
			opCountChose.set(max, opCountChose.get(max)+(expertNum-sumb));
		}
		
		
		
		List<Object[]> resultList = new ArrayList<Object[]>();
		Set<String> userMap = new HashSet<String>();
		
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		
		
		//这里平均取取符合条件的专家名单
		for (int i = 0; i < expertTypes.length; i++) {
			int m = opCountChose.get(i);
			String hql = "from ExpertPre t where t.person_type = ? and t.data_status<>'99'";
			List<ExpertPre> listP = expertPreDao.createQuery(hql, expertTypes[i]).list();
			for (int k = 0; k < listP.size(); k++) {
				if(!userMap.contains(listP.get(k).getPerson_id())&&listP.size()<=m){
					ExpertRecordList recordList = new ExpertRecordList();
					recordList.setExpert_id(listP.get(k).getPerson_id());
					recordList.setFk_tjy2_expert_record_id(id);
					userMap.add(listP.get(k).getPerson_id());
					recordListDao.save(recordList);
					
					Map<String, String> map1 = new HashMap<String, String>();
					map1.put("id", listP.get(k).getPerson_id());
					map1.put("name", listP.get(k).getPerson_name());
					map1.put("expert_type", listP.get(k).getPerson_type_name());
					listP.remove(k);
					mapList.add(map1);
					m--;
				}
			}

			String sql = "select t.id,t.emp_name,t.person_type from TJY2_RL_EMPLOYEE_BASE t where t.person_type like '%"+expertTypes[i]
					+"%'";
			if(expertRecord.getNocome_op()!=null){
				sql = "select t.id,t.emp_name,t.person_type from TJY2_RL_EMPLOYEE_BASE t where t.person_type like '%"+expertTypes[i]
						+"%'  and t.id not in ('"+noComeOp+"')";
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> list = employeeBaseDao.createSQLQuery(sql).list();


			for (int j = 0; j < m; j++) {
					int n = (new Random().nextInt(list.size()));
					System.out.println("-------"+list.size()+"-------------"+n+"------"+list.get(n).toString());
					if(hasStringValue(resultList,list.get(n).toString())){
						m++;
					}else{
						ExpertRecordList recordList = new ExpertRecordList();
						recordList.setExpert_id(list.get(n)[0].toString());
						recordList.setFk_tjy2_expert_record_id(id);
						
						recordListDao.save(recordList);
						
						resultList.add(list.get(n));
					}
					
					list.remove(n);
			}
			
			
		}
		expertRecord.setStatus("1");
		//候选人数量
		expertRecord.setCandidate_num(summ);
		
		expertRecordDao.save(expertRecord);
		
		for (int i = 0; i < resultList.size(); i++) {
			Object[] obj = resultList.get(i); 
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("id", obj[0].toString());
			map1.put("name", obj[1].toString());
			map1.put("expert_type", getName(obj[2].toString()));
			mapList.add(map1);
		}
		
		map.put("result", mapList);
		return map;
	}
	
	public boolean hasValue(List<Integer> arr,int n){
		boolean flag = false;
		
		for (int i = 0; i < arr.size(); i++) {
			if(arr.get(i)==n){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	public boolean hasStringValue(List<Object[]> arr,String val){
		boolean flag = false;
		
		for (int i = 0; i < arr.size(); i++) {
			if(val.equals(arr.get(i))){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	
	public String getName(String value){
		
		String name="";
		String values = value.replace(",", "','");
		
		String sql = "select v.name from PUB_CODE_TABLE_VALUES v, PUB_CODE_TABLE t"
				+ " where t.id = v.code_table_id and v.value  in ('"+values+"')"
					+ "  and t.code='PERSON_TYPE'";
		List<Object> list = expertRecordDao.createSQLQuery(sql).list();
		for (int i = 0; i < list.size(); i++) {
			
			if("".equals(name)){
				name = list.get(i).toString();
			}else{
				name = name+","+ list.get(i).toString();
			}
			
		}
			
		
		
		
		return name;
		
	}
	
}
