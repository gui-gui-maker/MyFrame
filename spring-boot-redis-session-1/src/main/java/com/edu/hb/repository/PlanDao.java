package com.edu.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.bean.Plan;

import java.util.List;


public interface PlanDao extends JpaSpecificationExecutor<Plan>,JpaRepository<Plan,String> {
	//单个删除
	@Modifying
	@Query(value="update Plan t set t.xgbj = -1 where t.id = ?1")
	void ldel(String id);
	//批量删除
	@Modifying
	@Query(value="update Plan t set t.xgbj = -1 where t.id in (:ids)") 
	void pldel(@Param("ids") List<String> ids);
	
	@Query("select distinct t.dyml from Plan t where t.yxdm = ?1 and t.dyml<>' ' ")
	List<String> findDymlByYxdm(String yxdm);
	
	@Query("select distinct t.dyml from Plan t where t.yxdm <> ?1")
	List<String> findNotDymlByYxdm(String yxdm);
	/*
	 * @Query("select t from BASE_JYJH t where t.yxdh = ?1") Page<BaseJyJh>
	 * findByYxdh(String yxdh,Pageable pageable);
	 * 
	 * @Query("select distinct t.yxdm,t.yxmc from BASE_JYJH t where t.yxdm = ?1")
	 * List<Object[]> findYxdmAndYxmc(String yxdm);
	 * 
	 * @Query("select distinct t.yxdh,t.yxdm,t.yxmc from BASE_JYJH t where t.yxdm = ?1"
	 * ) List<Object[]> findyxdhYxdmAndYxmc(String yxdm);
	 * 
	 * @Query("select distinct t.yxdm,t.yxmc from BASE_JYJH t where 1=1 ")
	 * List<Object[]> findAllYxdmAndYxmc();
	 * */
	@Query("select t.dyml from Plan t where t.yxdh = ?1")
	List<String> findDymlByYxdh(String yxdh);
	
	@Query(value = "select * from t_zydh t where id in (select distinct fid from plan_edit_apply where status<>1)",nativeQuery=true)
	List<Plan> findAllApplyPlan();
	
	@Query(value = "select * from t_zydh t where id in (select distinct fid from plan_edit_apply where fid not in (select distinct fid from plan_edit_apply where status<>1))",nativeQuery=true)
	List<Plan> findAllCheckedApplyPlan();
	
	List<Plan> findByYxdhAndDyml(String yxdh, String dyml);
	
	 
    
	
	@Query(value = "SELECT distinct ml, msg,sflb\n" + "  FROM (SELECT ml, msg, sflb\n" + "          FROM t_mulu\n"
			+ "         WHERE ml in (SELECT dyml FROM t_zydh WHERE yxdh = ?1)\n" + "        UNION\n"
			+ "        SELECT ml, msg, sflb\n" + "          FROM t_mulu\n" + "         WHERE sflb = '0'\n"
			+ "           AND ml in (SELECT SUBSTR(dyml, 1, 1) || '0000'\n" + "                        FROM t_zydh\n"
			+ "                       WHERE yxdh = ?2)\n" + "        UNION\n" + "        SELECT ml, msg, sflb\n"
			+ "          FROM t_mulu\n" + "         WHERE sflb = '0'\n"
			+ "           AND ml in (SELECT SUBSTR(dyml, 1, 2) || '000'\n" + "                        FROM t_zydh\n"
			+ "                       WHERE yxdh = ?3)\n" + "        UNION\n" + "        SELECT ml, msg, sflb\n"
			+ "          FROM t_mulu\n" + "         WHERE sflb = '0'\n"
			+ "           AND ml in (SELECT SUBSTR(dyml, 1, 3) || '00'\n" + "                        FROM t_zydh\n"
			+ "                       WHERE yxdh = ?4)\n" + "        UNION\n" + "        SELECT ml, msg, sflb\n"
			+ "          FROM t_mulu\n" + "         WHERE sflb = '0'\n"
			+ "           AND ml in (SELECT SUBSTR(dyml, 1, 4) || '0'\n" + "                        FROM t_zydh\n"
			+ "                       WHERE yxdh = ?5)) ml\n" + " order by ml", nativeQuery = true)
	List<Object[]> findCatalog(String arg1, String arg2, String arg3, String arg4, String arg5);

	@Query(value = "SELECT DISTINCT ml,msg,sflb from (  "
			+ "select * from t_mulu where ml in (select dyml from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
			+ "select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,1),'0000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
			+ "select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,2),'000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
			+ "select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,3),'00') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
			+ "select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,4),'0') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) "
			+ ")ml order by ml asc", nativeQuery = true)
	List<Object[]> findPrintMulu(String yxdh);

	
	  
	@Query(value = "SELECT xzdm,zszymc,zkfx,zklxdm,kldm,pcdm,zklxmc,jhlbdm,bxdd,bhzygs,wyyz,sfks,zybz,zylbdm,jhxzdm,sbzydh,xbzydh,zsjhs,sfbz \n"
			+ "FROM vt_zydh \n" + "WHERE yxdh = ?1 AND xgbj >= 0 AND dyml = ?2 \n"
			+ "ORDER BY yxdh,jhxzdm,sbzydh ASC", nativeQuery = true)
	List<Object[]> queryMajorList(String yxdh, String ml);
	@Query(value = "update t_zydh set status=?1 where id=?2", nativeQuery = true)
	void updateStatus(String status ,String id);
	 

	//根据院校代码查询
	//List<BaseJyJh> findByYxdm(String yxdm);
}

