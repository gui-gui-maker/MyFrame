package com.edu.service.impl;

import java.lang.reflect.Method;
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
import com.edu.util.StringUtil;

import net.sf.json.JSONObject;

@Service
public class PlanEditApplyServiceImpl implements PlanEditApplyService{

	@Autowired
	private PlanEditApplyDao planEditApplyDao;
	@Autowired
	private PlanDao planDao;

	//申请修改
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
            JSONObject value = JSONObject.fromObject(jobj.getString(key));
            //验证此key的是否有在审核途中
            List<PlanEditApply> applies = planEditApplyDao.findWaitDealByFidAndField(id,key);
            if(null!=applies && !applies.isEmpty() && "1".equals(applies.get(0).getStatus())) {
            	continue;
            }else if(null!=applies && !applies.isEmpty() && !"1".equals(applies.get(0).getStatus())) {
            	//更新
            	_apply = applies.get(0);
            	_apply.setApplyTime(new Date());
            	_apply.setoValue(value.getString("oValue"));
            	_apply.setnValue(value.getString("nValue"));
            	_apply.setStatus("0");
            }else {
	            _apply = new PlanEditApply();
	        	_apply.setApplyBy(user.getUsername());
	        	_apply.setApplyTime(new Date());
	        	_apply.setFid(id);
	        	_apply.setField(key);
	        	_apply.setoValue(value.getString("oValue"));
	        	_apply.setnValue(value.getString("nValue"));
	        	_apply.setStatus("0");
            }
        	//save
        	planEditApplyDao.save(_apply);
        	planDao.updateStatus("1",id);
		}

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

	//确认修改
	@Transactional
	public void save(PlanEditApply apply,User user) throws Exception {
		apply.setpValue(StringUtil.isNotEmpty(apply.getpValue())?apply.getpValue():apply.getnValue());
		planEditApplyDao.save(apply);

		//更新plan
		Optional<Plan> optional = planDao.findById(apply.getFid());
		Plan plan = optional.get();
		String field = apply.getField();
		String pValue = apply.getpValue();
		Class<?> pClazz = Plan.class;
		//参数类型现在都是string，暂时不考虑其他
		Method sm = pClazz.getMethod("set"+StringUtil.upperFirstCase(field), String.class); 
		/*Method[] ms = pClazz.getMethods();
		for (int i = 0; i < ms.length; i++) {
			if(("set"+field).toLowerCase().equals(ms[i].getName().toLowerCase())) {
				sm = ms[i];
			}
		}*/
		//if(StringUtil.isNotEmpty(pValue)) {
			sm.invoke(plan, pValue);
		//}else {
		//	sm.invoke(plan, nValue);
		//}
		plan.setLastUpdateBy(user.getUsername());
		plan.setLastUpdateTime(new Date());
		//如果审核完毕，更新plan status = 2
		List<PlanEditApply> list = planEditApplyDao.findWaitDealByFid(apply.getField());
		if(null!=list&&list.size()==0) {
			plan.setStatus("2");
		}

		planDao.save(plan);
	}	
	
}
