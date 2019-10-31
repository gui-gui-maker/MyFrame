package demo.bpm.listener;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.support.FinishActionInf;

import demo.bpm.dao.BpmServiceDao;

@Service("testFinishAction")
public class FinishActionImpl implements FinishActionInf {

	public void finishAction(Activity activity,BpmUser bpmUser){
		BpmServiceDao testServiceDao = (BpmServiceDao) ContextLoader.getCurrentWebApplicationContext().getBean(
				"testServiceDao");
		testServiceDao.get(activity.getProcess().getBusinessId()).setState("publish over");
	}

}
