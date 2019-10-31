package com.fwxm.dining.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.dao.FoodCardDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;


@Service("foodCardService")
public class FoodCardService extends
EntityManageImpl<FoodCard, FoodCardDao>{
	@Autowired
	private FoodCardDao foodCardDao;
	
	
	/**
	 * 
	 * @param id 卡号
	 * @return
	 */
	public FoodCard getCardByCardNo(String id) {
		String hql = "from FoodCard f where f.cardNo=? and f.cardStatus!=0";
		return (FoodCard)foodCardDao.createQuery(hql, id).uniqueResult();
	}
	/**
	 * 
	 * @param id 员工号
	 * @return
	 */
	public FoodCard getCardByEmpId(String id) {
		String hql = "from FoodCard f where f.userId = ? and f.cardStatus = 1";
		return (FoodCard)foodCardDao.createQuery(hql, id).uniqueResult();
	}
	//挂失
	public void deleteCards(String ids) {
		String[] idss = ids.split(",");
		for(String id: idss)
		{
			String hql="update FoodCard set cardStatus = 0 where id = ?";
			foodCardDao.createQuery(hql, id).executeUpdate();
		}
		
	}
	public FoodCard recharge(String id, int count, String userAccount) {
		
		FoodCard foodCard = foodCardDao.get(id);
		try {
			int oCount = foodCard.getCount()==null?0:foodCard.getCount().intValue();
			foodCard.setCount(count+oCount);
			foodCard.setLastAdd(count);
			foodCard.setLastDecrease(0);
			foodCard.setLastOperator(userAccount);
			foodCard.setLastAction("充值");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return foodCard;
	}
	public void saveCard(FoodCard foodCard) throws Exception {
		@SuppressWarnings("unchecked")
		List<FoodCard> list = foodCardDao.createQuery("from FoodCard where userId = ? and cardStatus=1", foodCard.getUserId()).list();
		if(list.size()>0){
			throw new Exception("已有卡...");
		}else{
			foodCardDao.save(foodCard);
		}
	}


}
