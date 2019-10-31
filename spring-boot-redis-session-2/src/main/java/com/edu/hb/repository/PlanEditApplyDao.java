package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.bean.PlanEditApply;

public interface PlanEditApplyDao extends JpaRepository<PlanEditApply, String>{
	@Query("from PlanEditApply t where t.fid = ?1 and cast(t.status as integer) < 2")
	List<PlanEditApply> findWaitDealByFid(String fid);
	
	List<PlanEditApply> findByFid(String id);

	List<PlanEditApply> findByField(String key);

	@Query("from PlanEditApply t where t.fid = ?1 and t.field=?2 and cast(t.status as integer) < 2")
	List<PlanEditApply> findWaitDealByFidAndField(String id, String key);

}
