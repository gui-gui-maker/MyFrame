package com.fwxm.dining.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodExt;
import com.fwxm.dining.bean.FoodPubm;
import com.fwxm.dining.bean.FoodPubo;
import com.fwxm.dining.bean.FoodPuboExt;
import com.fwxm.dining.dao.FoodDao;
import com.fwxm.dining.dao.FoodPubmDao;
import com.fwxm.dining.dao.FoodPuboDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;

@Service("foodPuboService")
public class FoodPuboService extends
EntityManageImpl<FoodPubo, FoodPuboDao>{
	@Autowired
	private FoodPuboDao foodPuboDao;
	@Autowired
	private FoodPubmDao foodPubmDao;
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private AttachmentDao attachmentDao;
	
	@SuppressWarnings("unchecked")
	public void savePubo(FoodPubo entity,String user) {
		foodPuboDao.save(entity);
		String id = entity.getId();
		foodPubmDao.createQuery("delete FoodPubm where fpo_id = ?", id).executeUpdate();
		List<Food> foods = entity.getFoods();
		for(Food food:foods){
			FoodPubm fpm = new FoodPubm();
			fpm.setFood_name(food.getId());
			fpm.setFood(food.getName());
			fpm.setFpo_id(id);
			fpm.setPub_man(user);
			fpm.setPub_time(new Date());
			fpm.setStatus(1);
			List<Attachment> attachments = attachmentDao.createQuery("from Attachment where businessId = ?", food.getId()).list();
			if(attachments.size()>0){
				Attachment attachment = attachments.get(0);
				fpm.setPic_path(attachment.getFilePath());
			}
			foodPubmDao.save(fpm);
		}
	}
	public FoodPubo getPuboByMark(String mark) {
		String hql = "from FoodPubo where mark = ?";
		FoodPubo fpo= (FoodPubo) foodPuboDao.createQuery(hql, mark).uniqueResult();
		return fpo;
	}
	public void deleteos(String ids) {
		String[] idss = ids.split(",");
		for(String id : idss){
			//删除主数据
			foodPuboDao.delete(id);
			//删除关联菜单
			String sql = "delete from food_pubm where fpo_id = ?";
			foodPubmDao.createSQLQuery(sql, id).executeUpdate();
			//删除关联订单
			String osql = "delete from food_order where fpo_id = ?";
			foodPubmDao.createSQLQuery(osql, id).executeUpdate();
		}
	}
	public void cancelos(String ids) {
		String sql = "update food_pubo set pub_status = '2' where id in (?)";
		foodPuboDao.createSQLQuery(sql, ids).executeUpdate();
	}
	public List<FoodPuboExt> getDailyMenu(Date date) {
		List<FoodPuboExt> list = new ArrayList<FoodPuboExt>();
		String hql = "from FoodPubo fo where fo.use_time >= ? order by fo.use_time desc";
		String hql2 = "select f ,fm.id from Food f,FoodPubm fm where fm.food_name = f.id and fm.fpo_id = ?";
		List<FoodPubo> fpos = (List<FoodPubo>) foodPuboDao.createQuery(hql, date).list();
		for (int i = 0; i < fpos.size(); i++) {
			FoodPubo fpo = fpos.get(i);
			FoodPuboExt fpoe = new FoodPuboExt(fpo);
			String fpo_id = fpo.getId();
			List<Object[]> fs = foodPubmDao.createQuery(hql2, fpo_id).list();
			
			List<FoodExt> foodExts = new ArrayList<FoodExt>();
 			for (Object[] food : fs) {
 				Food f = (Food)food[0];
 				FoodExt foodExt = new FoodExt(f);
 				foodExt.setFpmId(food[1].toString());
 				foodExts.add(foodExt);
 				foodExt = null;
			}
			fpoe.setFs(new HashSet<FoodExt>(foodExts));
			list.add(fpoe);
		}
		return list;
	}
	public FoodPubo getById(String id) throws Exception{
		FoodPubo foodPubo = foodPuboDao.get(id);
		List<Food> foods = foodPubo.getFoods();
		List<FoodPubm> fpms = foodPubmDao.createQuery("from FoodPubm where fpo_id = ?", id).list();
		for (FoodPubm foodPubm : fpms) {
			String foodId = foodPubm.getFood_name();
			Food food = foodDao.get(foodId);
			if(food!=null){
				foods.add(food);
			}
		}
		return foodPubo;
	}
	public void submit(String ids) {
		String sql = "update food_pubo set pub_status = '1',pub_time=? where id in (?)";
		foodPuboDao.createSQLQuery(sql, new Date(),ids).executeUpdate();
	}
	public List<FoodPubo> getFpos(Date use_time, int type) {
		List<FoodPubo> fpos = foodPuboDao.createQuery("from FoodPubo where use_time=? and meal_name=?", use_time,type).list();
		return fpos;
	}
	
	public List<FoodPubo> getQmData() throws Exception{
		String hql = "from FoodPubo fo order by fo.use_time desc";
		Query query = foodPuboDao.createQuery(hql);
		/*query.setFirstResult((pageSize-1)*pageNumber+1);
		query.setMaxResults(pageSize*pageNumber);*/
		List<FoodPubo> list = query.list();
		for (int i = 0; i < list.size(); i++) {
			FoodPubo fo = list.get(i);
			String hql2 = "select f from Food f,FoodPubm fm where fm.food_name = f.id and fm.fpo_id = ?";
			List<Food> fs = foodPubmDao.createQuery(hql2, fo.getId()).list();
			fo.setFoods(fs);
		}
		return list;
	}
}
