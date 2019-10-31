package demo.deviceClassify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.deviceClassify.bean.DeviceClassify;
import demo.deviceClassify.dao.DeviceClassifyDao;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

/** 
 * @author 作者 幸鑫
 * @JDK 1.6
 * @version 创建时间：2014年12月17日 上午9:41:52 
 * 类说明 
 */

@Service("deviceClassifyService")
public class DeviceClassifyService extends EntityManageImpl<DeviceClassify,DeviceClassifyDao>{
	@Autowired
	private DeviceClassifyDao deviceClassifyDao;
	
	 @SuppressWarnings("unchecked")
	public void saveClassifyEntity(){
			deviceClassifyDao.inputFirstLevel();
	
	}
}
