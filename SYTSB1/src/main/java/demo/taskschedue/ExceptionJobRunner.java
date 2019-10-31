package demo.taskschedue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

import com.khnt.core.exception.KhntException;
import com.khnt.task.JobRunner;

public class ExceptionJobRunner implements JobRunner {

	Log log = LogFactory.getLog(ExceptionJobRunner.class);

	@Override
	public void excuteJob(JSONObject param) throws KhntException {
		log.debug("演示任务调度执行失败的示例！任务参数：" + param.toString());
		throw new KhntException("演示任务执行失败时的错误信息！");
	}

}
