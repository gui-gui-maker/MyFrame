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
	
	 
    
	/*
	 * @Query(value="SELECT distinct ml, msg,sflb\n" +
	 * "  FROM (SELECT ml, msg, sflb\n" + "          FROM t_mulu\n" +
	 * "         WHERE ml in (SELECT dyml FROM t_zydh WHERE yxdh = ?1)\n" +
	 * "        UNION\n" + "        SELECT ml, msg, sflb\n" +
	 * "          FROM t_mulu\n" + "         WHERE sflb = '0'\n" +
	 * "           AND ml in (SELECT SUBSTR(dyml, 1, 1) || '0000'\n" +
	 * "                        FROM t_zydh\n" +
	 * "                       WHERE yxdh = ?2)\n" + "        UNION\n" +
	 * "        SELECT ml, msg, sflb\n" + "          FROM t_mulu\n" +
	 * "         WHERE sflb = '0'\n" +
	 * "           AND ml in (SELECT SUBSTR(dyml, 1, 2) || '000'\n" +
	 * "                        FROM t_zydh\n" +
	 * "                       WHERE yxdh = ?3)\n" + "        UNION\n" +
	 * "        SELECT ml, msg, sflb\n" + "          FROM t_mulu\n" +
	 * "         WHERE sflb = '0'\n" +
	 * "           AND ml in (SELECT SUBSTR(dyml, 1, 3) || '00'\n" +
	 * "                        FROM t_zydh\n" +
	 * "                       WHERE yxdh = ?4)\n" + "        UNION\n" +
	 * "        SELECT ml, msg, sflb\n" + "          FROM t_mulu\n" +
	 * "         WHERE sflb = '0'\n" +
	 * "           AND ml in (SELECT SUBSTR(dyml, 1, 4) || '0'\n" +
	 * "                        FROM t_zydh\n" +
	 * "                       WHERE yxdh = ?5)) ml\n" + " order by ml",
	 * nativeQuery=true) List<Object[]> findCatalog(String arg1,String arg2,String
	 * arg3,String arg4,String arg5);
	 * 
	 * @Query(value="SELECT DISTINCT ml,msg,sflb from (  "
	 * +"select * from t_mulu where ml in (select dyml from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
	 * +"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,1),'0000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
	 * +"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,2),'000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
	 * +"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,3),'00') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
	 * +"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,4),'0') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) "
	 * +")ml order by ml asc", nativeQuery=true) List<Object[]> findPrintMulu(String
	 * yxdh);
	 * 
	 * @Query(value="SELECT\n" + "	yxdh,\n" + "	yxbz,\n" + "	jhlbdm,\n" +
	 * "	yxmc,\n" + "	(\n" + "		CASE yxjblxmc\n" +
	 * "		WHEN '民办院校' THEN\n" + "			'(民办院校)'\n" +
	 * "		WHEN '独立院校' THEN\n" + "			'(独立院校)'\n" +
	 * "		WHEN '中外合作办学' THEN\n" + "			'(中外合作办学)'\n" +
	 * "		WHEN '内地与港澳台地区合作办学' THEN\n" + "			'(内地与港澳台地区合作办学)'\n" +
	 * "		ELSE\n" + "			''\n" + "		END\n" + "	) yxjblxmc,\n" +
	 * "	zjhs,\n" + "	yxdz,\n" + "	yxdhmc,\n" + "	jhxzdm,\n" + "	jffs\n"
	 * + "FROM\n" + "	(\n" + "		SELECT\n" + "			*\n" +
	 * "		FROM\n" + "			(\n" + "				SELECT\n" +
	 * "					*\n" + "				FROM\n" +
	 * "					vt_zydh\n" + "				WHERE\n" +
	 * "					yxdh = ?1\n" + "				AND dyml IS NOT NULL\n"
	 * + "				AND xgbj >= 0\n" + "			) t\n" + "		WHERE\n" +
	 * "			t.dyml = ?2\n" + "	) v\n" + "LIMIT 1", nativeQuery=true)
	 * List<Object[]> queryPlan(String yxdh,String ml);
	 * 
	 * @Query(
	 * value="SELECT xzdm,zszymc,zkfx,zklxdm,kldm,pcdm,zklxmc,jhlbdm,bxdd,bhzygs,wyyz,sfks,zybz,zylbdm,jhxzdm,sbzydh,xbzydh,zsjhs,sfbz \n"
	 * + "FROM vt_zydh \n" + "WHERE yxdh = ?1 AND xgbj >= 0 AND dyml = ?2 \n" +
	 * "ORDER BY yxdh,jhxzdm,sbzydh ASC", nativeQuery=true) List<Object[]>
	 * queryMajorList(String yxdh,String ml);
	 */

	//根据院校代码查询
	//List<BaseJyJh> findByYxdm(String yxdm);
}

