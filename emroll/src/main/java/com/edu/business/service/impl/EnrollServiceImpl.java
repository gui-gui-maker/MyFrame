package com.edu.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edu.business.bean.Enroll;
import com.edu.business.dao.EnrollRepository;
import com.edu.business.service.EnrollService;
@Service
public class EnrollServiceImpl implements EnrollService{
	@Autowired
	EnrollRepository enrollRepository;
	
	@Override
	public List<Object[]> findCatalog(String yxdh) throws Exception {
		
		return enrollRepository.findCatalog(yxdh, yxdh, yxdh, yxdh, yxdh);
	}

	@Override
	public Page<Enroll> findByCondition(Enroll enroll, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return enrollRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //默认查询未删除的
            predicates.add(cb.equal(root.get("isDel"),"0"));
            //equal 示例
            if (!StringUtils.isEmpty(enroll.getYxdh())){
            	predicates.add(cb.equal(root.get("yxdh"),enroll.getYxdh()));
            }
            if (!StringUtils.isEmpty(enroll.getDyml())){
            	predicates.add(cb.equal(root.get("dyml"),enroll.getDyml()));
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
	public void saveOrUpdate(Enroll enroll) throws Exception {
		enrollRepository.save(enroll);
	}
	
	@Transactional
	public void logicDelete(List<String> ids) throws Exception {
		enrollRepository.logicDelete(ids);
	}

}
