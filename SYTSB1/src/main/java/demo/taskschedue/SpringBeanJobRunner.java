package demo.taskschedue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.khnt.core.exception.KhntException;
import com.khnt.task.JobRunner;

@Component
public class SpringBeanJobRunner implements JobRunner, ApplicationContextAware {

	Log log = LogFactory.getLog(SpringBeanJobRunner.class);

	@Override
	public void excuteJob(JSONObject param) throws KhntException {
		log.debug("演示任务调度执行成功的示例,使用Spring Bean方式调用！当前的Spring Context：" + this.ac.getDisplayName());
		log.debug("任务参数：" + param.toString());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ac = applicationContext;
	}

	ApplicationContext ac;
}
