package com.edu.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.Plan;
import com.edu.bean.PlanEditApply;
import com.edu.bean.User;
import com.edu.hb.repository.PlanDao;
import com.edu.hb.repository.PlanEditApplyDao;
import com.edu.service.PlanEditApplyService;

import net.sf.json.JSONObject;

@Service
public class PlanEditApplyServiceImpl implements PlanEditApplyService{

	@Autowired
	private PlanEditApplyDao planEditApplyDao;
	@Autowired
	private PlanDao planDao;

	@Transactional
	public void save(String appplies,User user) throws Exception {
		JSONObject jobj = JSONObject.fromObject(appplies);
		String id = jobj.getString("id");
        PlanEditApply _apply = null;
        Iterator iterator = jobj.keys();
		while(iterator.hasNext()){
            String key = (String) iterator.next();
            if("id".equals(key)) {
            	continue;
            }
            //验证此key的是否有在审核途中
            List<PlanEditApply> applies = planEditApplyDao.findWaitDealByFidAndField(id,key);
            if(null!=applies && !applies.isEmpty()) {
            	continue;
            }
            if("others".equals(key)) {
            	_apply = new PlanEditApply();
            	_apply.setApplyBy(user.getUserName());
            	_apply.setApplyTime(new Date());
            	_apply.setFid(id);
            	_apply.setField(key);
            	_apply.setOtherApply(jobj.getString(key));
            	_apply.setStatus("0");
            	//save
            	planEditApplyDao.save(_apply);
            	continue;
            }
            JSONObject value = JSONObject.fromObject(jobj.getString(key));
            _apply = new PlanEditApply();
        	_apply.setApplyBy(user.getUserName());
        	_apply.setApplyTime(new Date());
        	_apply.setFid(id);
        	_apply.setField(key);
        	_apply.setoValue(value.getString("oValue"));
        	_apply.setnValue(value.getString("nValue"));
        	_apply.setStatus("0");
        	//save
        	planEditApplyDao.save(_apply);
		}
		List<PlanEditApply> list = planEditApplyDao.findWaitDealByFid(id);
		Optional<Plan> optional = planDao.findById(id);
		Plan plan = optional.get();
		plan.setApplies(list.size());
		planDao.save(plan);
	}

	@Override
	public List<PlanEditApply> findByFid(String id) throws Exception {
		// TODO Auto-generated method stub
		return planEditApplyDao.findByFid(id);
	}

	@Override
	public List<PlanEditApply> findByField(String key) {
		// TODO Auto-generated method stub
		return planEditApplyDao.findByField(key);
	}	
	
}
