package com.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.bean.Plan;
import com.edu.hb.repository.CatalogDao;
import com.edu.hb.repository.PlanDao;
import com.edu.jdbc.repository.PlanJDao;
import com.edu.service.PlanService;
import com.edu.util.StringUtil;

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	PlanDao planDao;
	@Autowired
	PlanJDao planJDao;
	@Autowired
	CatalogDao catalogDao;
	
	//搜索
	public Page<Plan> findByCondition(Plan pp, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return planDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //默认查询未删除的
            predicates.add(cb.greaterThanOrEqualTo(root.get("xgbj"),0));
            //equal 示例
            if (StringUtil.isNotEmpty(pp.getYxdh())){
            	predicates.add(cb.equal(root.get("yxdh"),pp.getYxdh()));
            }
            if (StringUtil.isNotEmpty(pp.getDyml())){
            	predicates.add(cb.equal(root.get("dyml"),pp.getDyml()));
            }
            if (StringUtil.isNotEmpty(pp.getYxdm())){
            	predicates.add(cb.equal(root.get("yxdm"),pp.getYxdm()));
            }
			/*
			 * //like 示例 if (!StringUtils.isNullOrEmpty(params.getRealName())){
			 * predicates.add(cb.like(root.get("realName"),"%"+params.getRealName()+"%")); }
			 */
			/*
			 * //between 示例 if (params.getMinAge()!=null && params.getMaxAge()!=null) {
			 * Predicate agePredicate = cb.between(root.get("age"), params.getMinAge(),
			 * params.getMaxAge()); predicates.add(agePredicate); }
			 */
			/*
			 * //greaterThan 大于等于示例 if (params.getMinAge()!=null){
			 * predicates.add(cb.greaterThan(root.get("age"),params.getMinAge())); }
			 */
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
	}
	@Transactional
	public void save(Plan plan) throws Exception {
		planDao.save(plan);
		//更新目录
		List<Object[]> rules = catalogDao.findAllRule();
		//目录
		List<String> rightCatalogs = new ArrayList<>();
		
		for (Object[] objs : rules) {
			//拼接sql
			String sql = "select * from t_zydh where xgbj>=0 and jhid='"+plan.getJhid()+"' and ("+objs[1].toString()+")"; 
			
			//执行sql
			List<Plan> plans = planJDao.getPlanBySql(sql);
			//如果查询到结果集
			if(plans.size()>0) {
				System.out.println(sql);
				rightCatalogs.add(objs[0].toString());
			}
		}
		if(rightCatalogs.size() != 1) {
			throw new Exception("没有或多个目录规则适用！");
		}else {
			plan.setDyml(rightCatalogs.get(0));
			planDao.save(plan);
		}
	}
	
	/**
	 * 逻辑删除
	 */
	@Transactional
	public void ldel(String ids) {
		String[] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {
			planDao.ldel(idss[i]);
		}
	}

}
