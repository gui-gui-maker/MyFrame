package com.fwxm.dining.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.dining.bean.FoodEvaluation;
import com.fwxm.dining.dao.FoodEvaluationDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service
@RequestMapping("foodEvaluationService")
public class FoodEvaluationService extends
EntityManageImpl<FoodEvaluation,FoodEvaluationDao>{
	@Autowired
	private FoodEvaluationDao foodEvaluationDao;

	public void saveEval(FoodEvaluation foodOrderDetail) {
		foodEvaluationDao.save(foodOrderDetail);
		
	}

	public List<FoodEvaluation> queryEvalByPubmId(String id) {
		String hql = "from FoodEvaluation where pubm_id = ?";
		
		return (List<FoodEvaluation>) foodEvaluationDao.createQuery(hql, id).list();
	}

	public void deleteEvals(String ids) {
		foodEvaluationDao.deleteEvals(ids);
	}
	
}
