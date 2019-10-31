package com.edu.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.business.bean.Admitted;
@Repository
public interface AdmittedRepository extends JpaSpecificationExecutor<Admitted>,JpaRepository<Admitted,String>{
	@Query(value="select sum(1), min(tdcj), avg(tdcj), kldm\n" +
					"  from BASE_ADMITTED\n" + 
					" where yxdm = :yxdm\n" + 
					"   and zydm = :zydm\n" + 
					"   and version = :vers\n" + 
					" group by kldm",nativeQuery=true)
	public List<Object[]> statisticScore(@Param("yxdm")String yxdm, @Param("zydm") String zydm, @Param("vers") int vers);
	
	@Modifying
	@Query(value="insert into STATISTIC_CUR3_SCORE(id,c_code,c_numb,c_name,m_code,m_name,persons,min_score,avg_score,dept,p_year)\n" +
					"values(sys_guid(),:c_code,:c_numb,:c_name,:m_code,:m_name,:persons,:min_score,:avg_score ,:dept,:p_year)",nativeQuery=true)
	public void addScoreStatistical(@Param("c_code")String c_code,
			@Param("c_numb")  String c_numb,
		    @Param("c_name")  String c_name,
		    @Param("m_code")  String m_code,
		    @Param("m_name")  String m_name,
		    @Param("persons")  Integer persons,
		    @Param("min_score")  Integer min_score,
		    @Param("avg_score")  Float avg_score,
		    @Param("dept")  String dept,
		    @Param("p_year")  String p_year);

	
}
