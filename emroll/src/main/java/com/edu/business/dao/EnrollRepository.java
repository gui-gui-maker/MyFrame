package com.edu.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.business.bean.Enroll;

import java.util.List;


public interface EnrollRepository extends JpaSpecificationExecutor<Enroll>,JpaRepository<Enroll,String> {

	
	@Query("select t from Enroll t where t.yxdh = ?1")
	Page<Enroll> findByYxdh(String yxdh,Pageable pageable);
	
	@Modifying
	@Query(value="update ZYDH t set t.is_del = '1' where t.id in (:ids)",nativeQuery=true)
	void logicDelete(@Param("ids") List<String> ids);
    
    @Query(value="SELECT distinct ml, msg,sflb\n" +
    				"  FROM (SELECT ml, msg, sflb\n" + 
    				"          FROM newspaper\n" + 
    				"         WHERE ml in (SELECT dyml FROM zydh WHERE yxdh = ?1)\n" + 
    				"        UNION\n" + 
    				"        SELECT ml, msg, sflb\n" + 
    				"          FROM newspaper\n" + 
    				"         WHERE sflb = '0'\n" + 
    				"           AND ml in (SELECT SUBSTR(dyml, 1, 1) || '0000'\n" + 
    				"                        FROM zydh\n" + 
    				"                       WHERE yxdh = ?2)\n" + 
    				"        UNION\n" + 
    				"        SELECT ml, msg, sflb\n" + 
    				"          FROM newspaper\n" + 
    				"         WHERE sflb = '0'\n" + 
    				"           AND ml in (SELECT SUBSTR(dyml, 1, 2) || '000'\n" + 
    				"                        FROM zydh\n" + 
    				"                       WHERE yxdh = ?3)\n" + 
    				"        UNION\n" + 
    				"        SELECT ml, msg, sflb\n" + 
    				"          FROM newspaper\n" + 
    				"         WHERE sflb = '0'\n" + 
    				"           AND ml in (SELECT SUBSTR(dyml, 1, 3) || '00'\n" + 
    				"                        FROM zydh\n" + 
    				"                       WHERE yxdh = ?4)\n" + 
    				"        UNION\n" + 
    				"        SELECT ml, msg, sflb\n" + 
    				"          FROM newspaper\n" + 
    				"         WHERE sflb = '0'\n" + 
    				"           AND ml in (SELECT SUBSTR(dyml, 1, 4) || '0'\n" + 
    				"                        FROM zydh\n" + 
    				"                       WHERE yxdh = ?5)) ml\n" + 
    				" order by ml",
    		nativeQuery=true)
    List<Object[]> findCatalog(String arg1,String arg2,String arg3,String arg4,String arg5);
}

