package demo.bpm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.bean.Process;
import com.khnt.bpm.core.support.FinishFlowInf;

import demo.bpm.dao.BpmServiceDao;

@Service("testFinishFlow")
public class FinishFlowImpl implements FinishFlowInf {

	@Autowired
	BpmServiceDao bpmServiceDao;

	public void finishFlow(Process process, int finishType, BpmUser bpmUser) {
		//根据finishType得知流程是如何结束的
		// 所有流程环节如果审批不通过，都是强制异常结束的，只有当流程顺序流转结束才是正常结束
		
		// 根据这个特性，在请假流程中，只要任意环节审批不通过，都是异常结束，否则就是审批通过。
		// 这里更改状态，正常结束时表示审批通过，状态为2，否则为不通过，状态为3
		bpmServiceDao.get(process.getBusinessId())
				.setState(finishType == FinishFlowImpl.FINISH_TYPE_COMMON ? "2" : "3");

		System.out.println("请假审批流程【" + process.getFlowName() + "】执行完成！");
	}
}