package com.fwxm.dining.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.DiningWindow;
import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.bean.FoodCardDayToDayAccount;
import com.fwxm.dining.bean.FoodOrder;
import com.fwxm.dining.bean.FoodPubm;
import com.fwxm.dining.bean.FoodPubo;
import com.fwxm.dining.bean.Sequence;
import com.fwxm.dining.dao.DiningWindowDao;
import com.fwxm.dining.dao.FoodCardDao;
import com.fwxm.dining.dao.FoodOrderDao;
import com.fwxm.dining.dao.FoodPubmDao;
import com.fwxm.dining.dao.FoodPuboDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

@Service("foodOrderService")
public class FoodOrderService extends EntityManageImpl<FoodOrder,FoodOrderDao>{
	@Autowired
	private FoodOrderDao foodOrderDao;
	@Autowired
	private FoodPuboDao foodPuboDao;
	@Autowired
	private FoodPubmDao foodPubmDao;
	@Autowired
	private FoodCardDao foodCardDao;
	@Autowired
	private FoodCardDayToDayAccountService foodCardDayToDayAccountService;
	@Autowired
	private DiningWindowDao diningWindowDao;

	@SuppressWarnings("unchecked")
	public FoodOrder saveOrder(FoodOrder foodOrder) throws Exception {
		List<FoodOrder> list = (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where fpo_id = ? and card_no=? and ostatus=0", foodOrder.getFpo_id(),foodOrder.getCard_no()).list();
		List<FoodOrder> list2 = (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where fpo_id = ? and card_no=? and ostatus=1", foodOrder.getFpo_id(),foodOrder.getCard_no()).list();
		if(list!=null&&list.size()>0){
			FoodOrder ofo = list.get(0);
			ofo.setFpm_ids(foodOrder.getFpm_ids());
			ofo.setFpm_names(foodOrder.getFpm_names());
			int count = foodOrder.getCount();
			int ocount = ofo.getCount();
			if(count<ocount){
				//退扣
				FoodCard foodCard = (FoodCard) foodCardDao.createQuery("from FoodCard where cardNo = ?",foodOrder.getCard_no()).uniqueResult();
				
				foodCard.setCount(foodCard.getCount()+ocount-count);
				foodCard.setLastAction("修改订量");
				foodCard.setLastDecrease(0);
				foodCard.setLastAdd(ocount-count);
				foodCard.setLastOperator("sys");
			}else if(count>ocount){
				//扣卡
				FoodCard foodCard = (FoodCard) foodCardDao.createQuery("from FoodCard where cardNo = ?",foodOrder.getCard_no()).uniqueResult();
				if(foodCard.getCount()<(count-ocount)){
					throw new Exception("余额不足，请充值！");
				}
				foodCard.setCount(foodCard.getCount()-(count-ocount));
				foodCard.setLastAction("修改订量");
				foodCard.setLastDecrease(count-ocount);
				foodCard.setLastAdd(0);
				foodCard.setLastOperator("sys");
			}
			ofo.setCount(foodOrder.getCount());
			ofo.setQuantum(foodOrder.getQuantum());
			foodOrder=ofo;
		}else if(list2!=null&&list2.size()>0){
			throw new Exception("有效订单状态已更改，更新失败！");
		}else{
			FoodCard foodCard = (FoodCard) foodCardDao.createQuery("from FoodCard where cardNo = ? and cardStatus!=0",foodOrder.getCard_no()).uniqueResult();
			if(foodCard.getCount()<foodOrder.getCount()){
				throw new Exception("余额不足，请充值！");
			}
			foodOrderDao.save(foodOrder);
			//扣卡
			foodCard.setCount(foodCard.getCount()-foodOrder.getCount());
			foodCard.setLastAction("订餐");
			foodCard.setLastDecrease(foodOrder.getCount());
			foodCard.setLastAdd(0);
			foodCard.setLastOperator("sys");
		}
		return foodOrder;
	}
	private FoodPubo getCurrentFpo(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = sdf.format(now);
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		System.out.println("当前:"+hour);
		String mealType = "0"; 
		if(hour<10){
			mealType = "0"; 
		}else if(10<=hour&&hour<15){
			mealType = "1";
		}else if(hour>=15){
			mealType="2";
		}
		String mark =mealType+day;
		String hql = "from FoodPubo where mark = ?";
		FoodPubo fpo= (FoodPubo) foodPuboDao.createQuery(hql, mark).uniqueResult();
		return fpo;
	}
	//获取当前时间段的个人订单
	public List<FoodOrder> getCurrentPersonOrders(String emp_id) throws Exception{
		FoodPubo fpo = getCurrentFpo();
		return (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where emp_id = ? and fpo_id=?", emp_id,fpo.getId()).list();
	}
	@SuppressWarnings("unchecked")
	public List<FoodOrder> getOwnOrders(String id) {
		List<FoodOrder> os = (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where emp_id = ?", id).list();
		return os;
	}
	

	public void deleteOrders(String ids) {
		foodOrderDao.deleteOrders(ids);	
	}
	/**
	 * 查出员工正在排队的订单
	 * @param emp_id
	 * @param id
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FoodOrder> getOsByEmpFpoSta(String emp_id, String id, int i) {
		List<FoodOrder> os = (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where emp_id = ? and fpo_id=? and ostatus= ?", emp_id,id,i).list();
		return os;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getQuantumCount(String id) {
		String sql = "select quantum,sum(count) count from food_order where fpo_id=? and ostatus<>-1 group by quantum";
		List<Object[]> list = foodOrderDao.createSQLQuery(sql, id).list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<FoodOrder> getOsByFpo(String id) {
		List<FoodOrder> os = (List<FoodOrder>) foodOrderDao.createQuery("from FoodOrder where fpo_id = ?", id).list();
		return os;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCosGroupBySta(String id) {
		List<Object[]> os = (List<Object[]>) foodOrderDao.createSQLQuery("select  ostatus, sum(count) co from Food_Order where fpo_id = ? group by ostatus", id).list();
		return os;
	}
	/**
	 * 逻辑删除
	 * @param ids
	 */
	public void remove(String ids) throws Exception{
			String[] idss = ids.split(",");
			for(String id:idss){
				FoodOrder foodOrder	= (FoodOrder) foodOrderDao.createQuery("from FoodOrder where id = ?", id).uniqueResult();
				if(foodOrder.getOstatus()!=0){
					throw new Exception("订单状态已改变，删除失败！！");
				}
				//不显示更新，hibernate自动维护
				foodOrder.setOstatus(-1);
				FoodCard foodCard = (FoodCard) foodCardDao.createQuery("from FoodCard where cardNo = ?",foodOrder.getCard_no()).uniqueResult();
				foodCard.setLastAction("退订");
				foodCard.setLastDecrease(0);
				foodCard.setLastAdd(foodOrder.getCount());
				foodCard.setCount(foodCard.getCount()+foodOrder.getCount());
				foodCard.setLastOperator("sys");
			}
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCount(String startTime, String endTime) {
		String sql = "select sum(count) al,sum(decode(o.ostatus,0,o.count,null)) book,sum(decode(o.ostatus,1,o.count,null)) ready,sum(decode(o.ostatus,2,o.count,null)) get,po.meal_name from food_order o,food_pubo po where o.fpo_id = po.id and po.use_time between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') group by po.meal_name";
		List<Object[]> list = foodOrderDao.createSQLQuery(sql,startTime,endTime).list();
		return list;
		
	}
	@SuppressWarnings("unchecked")
	public Map<String,Object> getOsByQuan(String startTime, String endTime,int diningName) {
		String sql = "select o.count,o.fpm_names from food_order o,food_pubo po where o.fpo_id = po.id and po.use_time between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') and po.meal_name=?";
		List<Object[]> list = foodOrderDao.createSQLQuery(sql,startTime,endTime,diningName).list();
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("diningName", diningName);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(list.size()>0){
			for(Object[] objs : list){
				int count = Integer.parseInt(objs[0].toString());
				String nsString = (String) objs[1];
				String[] names = nsString.split(",");
				for(String name : names){
					Integer num = map.get(name);
					if(num!=null){
						num = num+count;
						map.put(name,num);
					}else{
						map.put(name, count);
					}
				}
				
			}
			rs.put("data", map.entrySet());
		}
		return rs;
		
	}

	
	public List<Object[]> getOsByEmp(String id, int pageSize, int page) {
		String sql = " select fpm_ids,id,use_time,meal_name, count,wdw,prepareNumber from (select a.*, rownum as rn from (select o.fpm_ids,po.id,po.use_time,po.meal_name,o.count,o.wdw,o.prepareNumber,o.rowid from food_order o,food_pubo po where o.fpo_id = po.id and o.emp_id = ? and po.use_time> sysdate order by po.use_time desc) a) where rn>? and rn<=?";
		List<Object[]> list = foodOrderDao.createSQLQuery(sql,id,(page-1)*pageSize,page*pageSize).list();
		return list;
	}
	public int getOsCount(String id) {
		String sql = "select count(*) from food_order o, food_pubo po where o.fpo_id = po.id and o.emp_id = ? and po.use_time> sysdate";
		Object obj = (Object) foodOrderDao.createSQLQuery(sql,id).uniqueResult();
		int count = Integer.parseInt(obj.toString()) ;
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getCardCurrentOrders(FoodPubo fpo) {
		String fpoId = fpo.getId();
		String sql = "select t.emp_id,count(*),t.ostatus from FOOD_ORDER t where t.fpo_id = ? group by t.ostatus,t.emp_id";
		List<Object[]> list =  null;
		List<Map<String,Object>> list2 =  new ArrayList<Map<String,Object>>();
		try{
			 list =  foodOrderDao.createSQLQuery(sql,fpoId).list();	
			 for(Object[] objs : list){
				 Map<String,Object> map = new HashMap<String,Object>();
				 map.put("emp", objs[0]);
				 map.put("count", objs[1]);
				 map.put("status", objs[2]);
				 list2.add(map);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return list2;
	}
	@SuppressWarnings("unchecked")
	public List<FoodOrder> getAllCurrentOrders(FoodPubo fpo) {
		 
		return foodOrderDao.createQuery("from FoodOrder where fpo_id=? and ostatus=0",fpo.getId()).list();
	}
	
	//作废订单
	public List<FoodOrder> removeOrdersByIds(String[] ids) {
		List<FoodOrder> list = new ArrayList<FoodOrder>();
		for(String id : ids){
			foodOrderDao.createQuery("update FoodOrder set ostatus=-1 where id = ?", id).executeUpdate();
			FoodOrder fo = (FoodOrder) foodOrderDao.createQuery("from FoodOrder where id = ?", id).uniqueResult();
			list.add(fo);
		}
		return list;
	}
	//备餐完毕的订单才能被刷出来
	@SuppressWarnings("unchecked")
	public FoodOrder getCurrentOrderByCard(FoodPubo fpo, String cardNo) {
		List<FoodOrder> list = foodOrderDao.createQuery("from FoodOrder where fpo_id=? and card_no = ? and ostatus=1",fpo.getId(),cardNo).list();
		FoodOrder foodOrder= null;
		if(list.size()>0){
			foodOrder = list.get(0);
			foodOrder.setGrade(10);//领餐最优先
		}
		return foodOrder;
	}
	//准备备餐订单
	@SuppressWarnings("unchecked")
	public FoodOrder updatePriorityOrder(DiningWindow diningWindow, FoodPubo fpo,int prepareNumber) {
		FoodOrder foodOrder = null;
		try{
			List<FoodOrder> list = foodOrderDao.createQuery("from FoodOrder where ostatus=0 and grade=(select min(grade) from FoodOrder where ostatus=0 and fpo_id = ?) and  fpo_id = ?", fpo.getId(),fpo.getId()).list();
			if(list.size()>0){
				foodOrder = list.get(0);
				//foodOrderDao.createQuery("update FoodOrder set wdw=?,prepareNumber=? where id = ?",diningWindow.getWindowNumber(),prepareNumber,foodOrder.getId()).executeUpdate();
				foodOrder.setPrepareNumber(prepareNumber);
				foodOrder.setWdw(diningWindow.getWindowNumber());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return foodOrder;
	}
	//准备备餐订单
	@SuppressWarnings("unchecked")
	public FoodOrder updateAssignedOrder(DiningWindow diningWindow, FoodPubo fpo,int prepareNumber) {
		FoodOrder foodOrder = null;
		try{
			List<FoodOrder> list = foodOrderDao.createQuery("from FoodOrder where ostatus=0 and wdw=? and fpo_id=?", diningWindow.getWindowNumber(),fpo.getId()).list();
			if(list.size()>0){
				foodOrder = list.get(0);
				//foodOrderDao.createQuery("update FoodOrder set prepareNumber=? where id = ?", prepareNumber,foodOrder.getId());
				foodOrder.setPrepareNumber(prepareNumber);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return foodOrder;
	}
	@SuppressWarnings("unused")
	private Sequence getNumber(HttpServletRequest request,String fpo_id) throws Exception{
		//获取备餐编号
		ServletContext context = request.getSession().getServletContext();
		Sequence s =  (Sequence) context.getAttribute("sequence");
		if(s==null){
			s = new Sequence();
			s.setFpoId(fpo_id);
			s.setNumber(1);
			context.setAttribute("sequence",s);
		}else{
			if(!s.getFpoId().equals(fpo_id)){
				s.setFpoId(fpo_id);
				s.setNumber(1);
			}else{
				s.setNumber(s.getNumber()+1);
			}
		}
		return s;
	}
	//更新备餐完毕
	public FoodOrder preparedOk(HttpServletRequest request,String id, DiningWindow win) throws Exception{
		FoodOrder fo = (FoodOrder) foodOrderDao.createQuery("from FoodOrder where id=?", id).uniqueResult();
		int window = fo.getWdw();
		if(window!=0){
			throw new Exception("餐已备");
		}else{
			fo.setWdw(win.getWindowNumber());
		}
		fo.setOstatus(1);
		fo.setGrade(30);//已备未领
		fo.setPreparedTime(new Date());
		Sequence s = getNumber(request,fo.getFpo_id());
		fo.setPrepareNumber(s.getNumber());
		return fo;
	}
	//确认领餐
	public void comfirmOrderDistributed(String id) {
		//已备已领
		foodOrderDao.createQuery("update FoodOrder set ostatus=2,grade=40,gettime=? where id = ?",new Date(), id).executeUpdate();
	}
	//获取未领餐的订单
	public FoodOrder distributeOrderWindow(FoodPubo fpo, String cardNo) throws Exception {
		@SuppressWarnings("unchecked")
		List<FoodOrder> list = foodOrderDao.createQuery("from FoodOrder where fpo_id=? and card_no = ? and ostatus in (0,1)",fpo.getId(),cardNo).list();
		FoodOrder foodOrder= null;
		if(list.size()>0){
			foodOrder = list.get(0);
			if(foodOrder.getWdw()==0){
				//升级优先级
				foodOrder.setGrade(15);
			}
		}
		return foodOrder;
	}
	//手机签到初始化
		public FoodOrder initComming(FoodPubo fpo, String employeeId) throws Exception {
			@SuppressWarnings("unchecked")
			List<FoodOrder> list = foodOrderDao.createQuery("from FoodOrder where fpo_id=? and emp_id = ? and ostatus in (0,1)",fpo.getId(),employeeId).list();
			FoodOrder foodOrder= null;
			if(list.size()>0){
				foodOrder = list.get(0);
			}
			return foodOrder;
		}
		//手机签到动作
		public FoodOrder signComming(String id) throws Exception {
			FoodOrder order = (FoodOrder) foodOrderDao.createQuery("from FoodOrder where id=?",id).uniqueResult();
			if(order.getWdw()==0){
				@SuppressWarnings("unchecked")
				List<DiningWindow> wdws = diningWindowDao.createQuery("from DiningWindow where windowStatus=1").list();
				if(wdws.size()==0){
					throw new Exception("食堂窗口未开启！");
				}
				int num = wdws.size();
				double d = Math.random();
				int rad = (int)d*num+1;
				order.setWdw(rad);
			}
			order.setIsSigned("yes");
			return order;
		}
		@SuppressWarnings("unchecked")
		public HashMap<String, Object> getOrderByDateAndMeal(String date, String meal) throws ParseException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Date use_time = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar ca=Calendar.getInstance();
			ca.setTime(use_time);
			int type = Integer.parseInt(meal);
			ca.add(Calendar.HOUR_OF_DAY, type==0?10:type==1?15:20);
			use_time = ca.getTime();
			List<FoodPubo> fpos = foodPuboDao.createQuery("from FoodPubo where use_time=? and meal_name=? and pub_status=1", use_time,type).list();
			if(fpos.size()>0){
				FoodPubo fpo = fpos.get(0);
				List<FoodPubm> fpms =  foodPubmDao.createQuery("from FoodPubm where fpo_id = ?", fpo.getId()).list();
				map.put("pubms", fpms);
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				User u = (User) user.getSysUser();
				Employee e = u.getEmployee();
				List<FoodOrder> orders = foodOrderDao.createQuery("from FoodOrder where fpo_id=? and emp_id=? and ostatus in (0,1)", fpo.getId(),e.getId()).list();
				if(orders.size()>0){
					FoodOrder order = orders.get(0);
					map.put("order", order);
				}else{
					return map;
				}
			}else{
				return null;
			}
			return map;
		}
		//按优先级排序
		public List<FoodOrder> getOrders(){
			FoodPubo fpo = getCurrentFpo();
			Query query = foodOrderDao.createQuery("from FoodOrder where fpo_id=? and ostatus!=-1 order by grade ", fpo.getId());
			query.setFirstResult(0);
			query.setMaxResults(20);
			return (List<FoodOrder>)query.list();
		}
}
