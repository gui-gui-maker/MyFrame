package com.fwxm.dining.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.dao.FoodDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;

@Service("foodService")
public class FoodService extends EntityManageImpl<Food, FoodDao>{
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private AttachmentManager attachmentManager;

	public void deleteFood(String ids) throws Exception{
		foodDao.deleteFood(ids);
	}

	public List<Object[]> getFoodList() throws Exception{
		String sql = "select id , name from food where 1=1";
		List<Object[]> list = foodDao.createSQLQuery(sql).list();
		return list;
	}

	public List<Object[]> getAll() throws Exception{
		
		return foodDao.createSQLQuery("select name from food where 1=1").list();
	}

	public List<Object[]> getSeatFoodList() throws Exception{
		return foodDao.createSQLQuery("select id, name from food where ftype = '10' ").list();
	}

	public Food save(Food food, String uploadFile) throws Exception{
		foodDao.save(food);
		if (!StringUtil.isEmpty(uploadFile)) {
			String[] files = uploadFile.replaceAll("/^,/", "").split(",");
			for (String fid : files) {
				if (!StringUtil.isEmpty(fid))
					attachmentManager.setAttachmentBusinessId(fid,
							food.getId());
			}
		}
		return food;
	}
	
}
