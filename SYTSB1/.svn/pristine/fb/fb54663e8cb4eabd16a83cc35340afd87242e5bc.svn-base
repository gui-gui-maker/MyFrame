package com.lsts.weixin.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.weixin.bean.VoteGoodnews;
import com.lsts.weixin.bean.VoteRoundOne;
import com.lsts.weixin.dao.VoteGoodnewsDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @author 作者 QuincyXu
 * @JDK 1.6
 * @version 创建时间：2016年5月12日11:52:15
 * 类说明 
 */

@Service("voteGoodnewsManager")
public class VoteGoodnewsManager extends EntityManageImpl<VoteGoodnews,VoteGoodnewsDao>{
    @Autowired
    VoteGoodnewsDao voteGoodnewsDao;
    
    public int checkVotedFinal(String userId) {
		if(userId==null){
			userId="noUserId";
		}
		String sql = " select count(t.id) nums from TJY2_VOTE_GOODNEWS t where  t.userid = ? and t.status='1'";
		List<BigDecimal> list = voteGoodnewsDao.createSQLQuery(sql, new String[]{userId}).list();
		BigDecimal num = list.get(0) ;
		return num.intValue();
	}

	public Map<String, Object> countVoteFinal() {
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
		sql.append("	       sum(vote_22) vote_22,	");
		sql.append("	       sum(vote_23) vote_23	");
		sql.append("	  from TJY2_VOTE_GOODNEWS	");
		sql.append("	 where status = '1'	");
		List list = voteGoodnewsDao.createSQLQuery(sql.toString()).list();
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
		
		BigDecimal tt = new BigDecimal(total).divide(new BigDecimal(13));
		ret_map.put("total", new Integer(tt.intValue()));
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
		return mappingList;
	}

	public int checkVoted(String userId) {
		String sql = "select count(1) nums from TJY2_VOTE_GOODNEWS where userid = ? and status='1' ";
		List<BigDecimal> list = voteGoodnewsDao.createSQLQuery(sql, userId).list();
		BigDecimal num = list.get(0) ;
		return num.intValue();
	}

public void saveRoundOne(String userId,String name, String phone, String str_result) throws Exception {
		
		VoteRoundOne one = new VoteRoundOne();
		String[] results = str_result.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into TJY2_VOTE_GOODNEWS values (").append("SYS_GUID()").append(",'").append(userId).append("',") ;
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
		voteGoodnewsDao.createSQLQuery(sql.toString()).executeUpdate();
		
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
		sql.append("	       sum(vote_22) vote_22,	");
		sql.append("	       sum(vote_23) vote_23	");
		sql.append("	  from TJY2_VOTE_GOODNEWS	");
		sql.append("	 where status = '1'	");
		List list = voteGoodnewsDao.createSQLQuery(sql.toString()).list();
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

	public void reSetRoundOne(String userId) throws Exception {
		
		VoteRoundOne one = new VoteRoundOne();
		//String[] results = str_result.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append(" update TJY2_VOTE_GOODNEWS set status='0' where userid = ? and status='1' ");
		
		voteGoodnewsDao.createSQLQuery(sql.toString(),userId).executeUpdate();
		
	}
}
