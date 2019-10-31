package com.lsts.qualitymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityXybzFile;
import com.lsts.qualitymanage.dao.QualityXybzFileDao;



@Service("Tjy2QualityXybzFile")
public class QualityXybzFileManager extends EntityManageImpl<QualityXybzFile,QualityXybzFileDao>{
    @Autowired
    QualityXybzFileDao qualityXybzFileDao;
    
    /**
	 * 根据设备类别、检验类别获取在用的检验依据文件
	 * @param device_sort -- 设备类别（二类）
	 * @param device_sort_code -- 设备类别（三类）
	 * @param check_type -- 检验类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-21 上午10:35:00
	 */
	@SuppressWarnings("unchecked")
    public List<QualityXybzFile> getFileInfos(String device_sort, String device_sort_code, String check_type) {
    	return qualityXybzFileDao.getFileInfos(device_sort, device_sort_code, check_type);
    }
}
