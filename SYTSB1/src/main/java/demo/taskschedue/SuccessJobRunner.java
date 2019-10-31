package demo.taskschedue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

import com.khnt.core.exception.KhntException;
import com.khnt.task.JobRunner;

public class SuccessJobRunner implements JobRunner {

	Log log = LogFactory.getLog(SuccessJobRunner.class);

	@Override
	public void excuteJob(JSONObject param) throws KhntException {
		log.debug("演示任务调度执行成功的示例，使用class反射方式调用！任务参数：" + param.toString());
	}

}
