package demo.fileupload.web;

import com.khnt.task.Task;

public class testrun extends Task {
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected boolean needExecuteImmediate() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void run() {
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");
	}

	@Override
	public Task[] taskCore() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean useDb() {
		// TODO Auto-generated method stub
		return false;
	}
}
