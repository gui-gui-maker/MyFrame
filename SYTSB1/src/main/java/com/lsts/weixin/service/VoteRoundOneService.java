package com.lsts.weixin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.weixin.bean.VoteRoundOne;
import com.lsts.weixin.dao.VoteDao;

import net.sf.json.JSONArray;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarInfoManager
 * @JDK 1.6
 * @author
 * @date
 */
@Service("voteRoundOneService")
public class VoteRoundOneService extends
		EntityManageImpl<VoteRoundOne, VoteDao> {
	
	@Autowired
	private VoteDao voteDao ;
	
	public void saveRoundOne(String userId,String name, String phone, String str_result) throws Exception {
		
		VoteRoundOne one = new VoteRoundOne();
		String[] results = str_result.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into TJY2_VOTE_ROUND_ONE values (").append("SYS_GUID()").append(",'").append(userId).append("',") ;
		for ( int i = 0 ; i < results.length ; i++) {
			sql.append("'").append(results[i]).append("',");
			/*
			if(results[i]!=null && !results[i].equals("")){
				sql.append("1").append("','");
			} else {
				sql.append("0").append("',");
			}*/
		}
		sql.append("sysdate , '1' ,'").append(name).append("','").append(phone).append("' )");
		System.out.println("==============="+sql.toString());
		voteDao.createSQLQuery(sql.toString()).executeUpdate();
		
	}
	

	public boolean chooseStep(String step) {
		if("0".equals(step)){
			String sql = "update TJY2_VOTE_ROUND_TWO_STEP t set t.step_flag = '0'";
			voteDao.createSQLQuery(sql).executeUpdate();
		}else{
			String chooseHql = "update TJY2_VOTE_ROUND_TWO_STEP t set t.step_flag = '1' where t.step = ?";
			String unChooseHql = "update TJY2_VOTE_ROUND_TWO_STEP t set t.step_flag = '0' where t.step <> ?";
			voteDao.createSQLQuery(chooseHql, step).executeUpdate();
			voteDao.createSQLQuery(unChooseHql, step).executeUpdate();
		}
		return true;
	} 
	
	public void reSetRoundOne(String userId) throws Exception {
		
		VoteRoundOne one = new VoteRoundOne();
		//String[] results = str_result.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append(" update TJY2_VOTE_ROUND_ONE set status='0' where userid = ? and status='1' ");
		
		voteDao.createSQLQuery(sql.toString(),userId).executeUpdate();
		
	}

	public int checkVoted(String userId) {
		String sql = "select count(1) nums from TJY2_VOTE_ROUND_ONE where userid = ? and status='1' ";
		List<BigDecimal> list = voteDao.createSQLQuery(sql, userId).list();
		BigDecimal num = list.get(0) ;
		return num.intValue();
	}
		
	
	public Map<String,Object> countVote(){
		StringBuffer sql  = new StringBuffer();
		sql.append("	select sum(vote_01) vote_01,	");
		sql.append("	       sum(vote_02) vote_02,	");
		sql.append("	       sum(vote_03) vote_03,	");
		sql.append("	       sum(vote_04) vote_04,	");
		sql.append("	       sum(vote_05) vote_05,	");
		sql.append("	       sum(vote_06) vote_06,	");
		sql.append("	       sum(vote_07) vote_07,	");
		sql.append("	       sum(vote_08) vote_08,	");
		sql.append("	       sum(vote_09) vote_09,	");
		sql.append("	       sum(vote_10) vote_10,	");
		sql.append("	       sum(vote_11) vote_11,	");
		sql.append("	       sum(vote_12) vote_12,	");
		sql.append("	       sum(vote_13) vote_13,	");
		sql.append("	       sum(vote_14) vote_14,	");
		sql.append("	       sum(vote_15) vote_15,	");
		sql.append("	       sum(vote_16) vote_16,	");
		sql.append("	       sum(vote_17) vote_17,	");
		sql.append("	       sum(vote_18) vote_18,	");
		sql.append("	       sum(vote_19) vote_19,	");
		sql.append("	       sum(vote_20) vote_20,	");
		sql.append("	       sum(vote_21) vote_21,	");
		sql.append("	       sum(vote_22) vote_22	");
		sql.append("	  from TJY2_VOTE_ROUND_ONE	");
		sql.append("	 where status = '1'	");
		List list = voteDao.createSQLQuery(sql.toString()).list();
		Object[] obj = (Object[])list.get(0);
		Map<String,String> map = new HashMap<String,String>();
		int total = 0 ;
		for(int i = 0 ; i < obj.length ; i++){
			map.put(String.valueOf(i), ((BigDecimal)obj[i]).toString());
			total = total +((BigDecimal)obj[i]).intValue();
		}
		List<Map.Entry<String, String>> mappingList = sortMap(map);
		
		Map<String,Object> ret_map = new HashMap<String,Object>();
		ret_map.put("total", new Integer(total));
		ret_map.put("list", mappingList);
		return ret_map;
	}
	
	public List<Map.Entry<String, String>> sortMap(Map<String,String> map){
		List<Map.Entry<String, String>> mappingList 
			= new ArrayList<Map.Entry<String, String>>(map.entrySet());
		Collections.sort(mappingList, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
				return new Integer(mapping2.getValue()).compareTo(new Integer(mapping1.getValue()));
			}
			
		});
		
		
		/*List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for (Map.Entry<String, String> mapping : mappingList) {
			HashMap<String,String> subMap = new HashMap<String,String>();
			subMap.put(mapping.getKey(), mapping.getValue());
			list.add(subMap);
		}*/
		/*for (Map.Entry<String, Integer> mapping : mappingList) {
			System.out.println(mapping.getKey() + ":" + mapping.getValue());
		}*/
		return mappingList;
	}
	
	
	//查询当前投票轮次
	public String checkThisVoteRound(){
		String sql = " select step from TJY2_VOTE_ROUND_TWO_STEP where step_flag='1' ";
		List<String> list = voteDao.createSQLQuery(sql ).list();
		String step = list.get(0) ;
		return step ;
	}
	
	public int checkVotedFinal(String userId) {
		String sql = " select count(t.id) nums from TJY2_VOTE_ROUND_TWO t,TJY2_VOTE_ROUND_TWO_STEP t1 where  t.userid = ? and t.status='1' and t.step = t1.step and t1.step_flag = 1 ";
		List<BigDecimal> list = voteDao.createSQLQuery(sql, new String[]{userId}).list();
		BigDecimal num = list.get(0) ;
		return num.intValue();
	}
	
	
	public void saveRoundFinal(String userId,String name, String phone, String str_result,String step) throws Exception {
		VoteRoundOne one = new VoteRoundOne();
		String[] results = str_result.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into TJY2_VOTE_ROUND_TWO values (").append("SYS_GUID()").append(",'").append(userId).append("',") ;
		for ( int i = 0 ; i < results.length ; i++) {
			sql.append("'").append(results[i]).append("',");
			/*
			if(results[i]!=null && !results[i].equals("")){
				sql.append("1").append("','");
			} else {
				sql.append("0").append("',");
			}*/
		}
		sql.append("sysdate , '1' ,'").append(name).append("','").append(phone).append("' ,'").append(step).append("' )");
		System.out.println("==============="+sql.toString());
		voteDao.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	public int checkIsCanSave(String step){
		String sql = " select count(1) from TJY2_VOTE_ROUND_TWO_STEP  where step = ? and step_flag='1' ";
		List<BigDecimal> list = voteDao.createSQLQuery(sql, step).list();
		BigDecimal num = list.get(0) ;
		return num.intValue();
	}
	
	
	public void reSetRoundFinal(String userId,String step) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update TJY2_VOTE_ROUND_TWO set status='0' where userid = ? and step = ? and status='1' ");
		voteDao.createSQLQuery(sql.toString(),new String[]{userId,step}).executeUpdate();
	}
	
	public Map<String,Object> countVoteFinal(){
		StringBuffer sql  = new StringBuffer();
		sql.append("	select sum(vote_01) vote_01,	");
		sql.append("	       sum(vote_02) vote_02,	");
		sql.append("	       sum(vote_03) vote_03,	");
		sql.append("	       sum(vote_04) vote_04,	");
		sql.append("	       sum(vote_05) vote_05,	");
		sql.append("	       sum(vote_06) vote_06,	");
		sql.append("	       sum(vote_07) vote_07,	");
		sql.append("	       sum(vote_08) vote_08,	");
		sql.append("	       sum(vote_09) vote_09,	");
		sql.append("	       sum(vote_10) vote_10	");
		sql.append("	  from TJY2_VOTE_ROUND_TWO	");
		sql.append("	 where status = '1'	");
		List list = voteDao.createSQLQuery(sql.toString()).list();
		Map<String,Object> ret_map = new HashMap<String,Object>();
		Map<String,String> map = new HashMap<String,String>();
		List<Map.Entry<String, String>> mappingList = null;
		
		Object[] obj = (Object[])list.get(0);
		
		int total = 0 ;
		for(int i = 0 ; i < obj.length ; i++){
			BigDecimal b = (obj[i]==null? new BigDecimal(0):(BigDecimal)obj[i]);
			map.put(String.valueOf(i), b.toString());
			total = total + b.intValue();
		}
		
		mappingList = sortMap(map);
		
		BigDecimal tt = new BigDecimal(total).divide(new BigDecimal(5));
		ret_map.put("total", new Integer(tt.intValue()));
		ret_map.put("list", mappingList);
		return ret_map;
	}
	
	
	public String[][] countVoteByStep(){
		StringBuffer sql  = new StringBuffer();
		sql.append("    select to_char(nvl(sum(vote_01), 0)) vote_01,	");
		sql.append("	       to_char(nvl(sum(vote_02), 0)) vote_02,	");
		sql.append("	       to_char(nvl(sum(vote_03), 0)) vote_03,	");
		sql.append("	       to_char(nvl(sum(vote_04), 0)) vote_04,	");
		sql.append("	       to_char(nvl(sum(vote_05), 0)) vote_05,	");
		sql.append("	       to_char(nvl(sum(vote_06), 0)) vote_06,	");
		sql.append("	       to_char(nvl(sum(vote_07), 0)) vote_07,	");
		sql.append("	       to_char(nvl(sum(vote_08), 0)) vote_08,	");
		sql.append("	       to_char(nvl(sum(vote_09), 0)) vote_09,	");
		sql.append("	       to_char(nvl(sum(vote_10), 0)) vote_10,	");
		sql.append("	       to_char(count(1)) voteCount,step	");
		sql.append("	  from TJY2_VOTE_ROUND_TWO	");
		sql.append("	 where status = '1'	");
		sql.append("	 group by step	");
		sql.append("	 order by step asc	");


		List list = voteDao.createSQLQuery(sql.toString()).list();
		//JSONArray ja2 = JSONArray.fromObject(list);
		//System.out.println(ja2);
		String[][] total = new String[3][11];
		int listSize = list.size();
		List<HashMap<String,String>> resList = new ArrayList<HashMap<String,String>>();
		for (int i = 0 ; i < 3 ;i++){
			HashMap<String,String> map = new HashMap<String,String>();
			if(listSize >= i+1){
				Object[] obj = (Object[])list.get(i);
				for( int j = 0 ; j <= 10 ; j++) {
					if(total[i][j] == null)
						total[i][j] = "0" ;
					if(i == 0)
						total[i][j] = String.valueOf(Integer.valueOf((String)obj[j]).intValue()  );
					else 
						total[i][j] = String.valueOf(  Integer.valueOf(total[i-1][j]).intValue() + Integer.valueOf((String)obj[j]).intValue()  );
					//BigDecimal dd = (BigDecimal)obj[j];dd.intValue()
					//map.put(String.valueOf(j), (String)obj[j]);
				}
			} else {
				for( int j = 0 ; j <= 10 ; j++) {
					/*if(total[i][j] == null)
						total[i][j] = "0" ;*/
					total[i][j] = total[i-1][j];
					
				}
			}
		}
		//System.out.println(total[2].toString());
		return total;
	}


	public String getStep() {
		String step = "";
		String sql = "select t.step from  TJY2_VOTE_ROUND_TWO_STEP t where t.step_flag = '1'";
		List list = voteDao.createSQLQuery(sql).list();
		if(list.size()>0){
			step = list.get(0).toString();
		}
		return step;
	}


	public String getVotedByStep(String step){
		String sql = "select count(1) from TJY2_VOTE_ROUND_TWO t where t.status='1' and t.step=? ";
		List<BigDecimal> list = voteDao.createSQLQuery(sql, step).list();
		BigDecimal num = list.get(0) ;
		return num.toString();
	}
	public HashMap<String, Object> checkUserName(String name, HashMap<String, Object> map) {
		String sql = "select count(t.id) from  sys_user t where t.status = '1' and t.name=?";
		List list = voteDao.createSQLQuery(sql,name).list();
		if(list.size()>0){
			String count = list.get(0).toString();
			if("1".equals(count)){
				map.put("flag", true);
			}else if("0".equals(count)){
				map.put("flag", false);
				map.put("msg", "该用户不能参与投票！");
			}else{
				map.put("flag", false);
				map.put("msg", "至少有两个该名字的用户！");
			}
		}else{
			map.put("flag", false);
			map.put("msg", "投票人验证失败！");
		}
		return map;
	}
	
}
