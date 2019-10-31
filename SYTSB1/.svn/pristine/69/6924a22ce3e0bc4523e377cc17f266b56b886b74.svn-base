package com.fwxm.dining.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodPubm;
import com.fwxm.dining.dao.FoodDao;
import com.fwxm.dining.dao.FoodPubmDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;

@Transactional
@Service("foodPubmService")
public class FoodPubmService extends EntityManageImpl<FoodPubm, FoodPubmDao>{
	@Autowired
	private FoodPubmDao foodPubmDao;
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private AttachmentDao attachmentDao;
/**
 * 根据发布餐获取菜单
 * @param fpoid
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<FoodPubm> getPubmByFpo(String fpoid) {
		
		String sql = "from FoodPubm where fpo_id = ?";

		return foodPubmDao.createQuery(sql,fpoid).list();
	}
	
	/**
	 * 保存菜单
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<FoodPubm> savePubs(String fpo_id,String ids,String username) {
		List<FoodPubm> list = new ArrayList<FoodPubm>();
		FoodPubm foodPubm = null;
		for (String id : ids.split(",")) {
			String hql = "from FoodPubm where fpo_id=? and food_name=?";
			List<FoodPubm> foodPubms = foodPubmDao.createQuery(hql, fpo_id,id).list();
			if(foodPubms.size()>0){
				foodPubm = foodPubms.get(0);
				foodPubm.setPub_man(username);
			}else{
				foodPubm = new FoodPubm();
				foodPubm.setFood_name(id);
				Food food = foodDao.get(id);
				if(food!=null){
					foodPubm.setFood(food.getName());
				}
				List<Attachment> attachments = attachmentDao.createQuery("from Attachment where businessId = ?", id).list();
				if(attachments.size()>0){
					Attachment attachment = attachments.get(0);
					foodPubm.setPic_path(attachment.getFilePath());
				}
				foodPubm.setFpo_id(fpo_id);
				foodPubm.setPub_man(username);
				foodPubm.setPub_time(new Date());
				foodPubmDao.save(foodPubm);
			}
			list.add(foodPubm);
		}
		return list;
	}

	public String getFoodName(String id) {
		Object obj = foodPubmDao.createSQLQuery("select f.name from food_pubm pm,food f where pm.food_name = f.id and pm.id=?", id).uniqueResult();
		return obj.toString();
	}
	//根据发布id查找菜品信息
	public Food getFood(String id) {
		Food f = (Food) foodPubmDao.createQuery("select f from FoodPubm pm,Food f where pm.food_name = f.id and pm.id=?", id).uniqueResult();
		return f;
	}
	@SuppressWarnings("unchecked")
	public List<FoodPubm> getPubmByName(String fpo_id, String food_name) {
		String sql = "from FoodPubm where fpo_id = ? and food_name = ?";

		return foodPubmDao.createQuery(sql,fpo_id,food_name).list();
	}

	public List<FoodPubm> getPubmByIds(String fpm_ids) {
		List<FoodPubm> list = new ArrayList<FoodPubm>();
		String[] ids = fpm_ids.split(",");
		for(String id : ids){
			FoodPubm f = foodPubmDao.get(id);
			list.add(f);
		}
		return list;
	}
	
	
}
