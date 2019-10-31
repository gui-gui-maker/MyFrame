package com.fwxm.dining.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.dining.bean.DiningWindow;
import com.fwxm.dining.bean.Food;
import com.fwxm.dining.bean.FoodCard;
import com.fwxm.dining.bean.FoodOrder;
import com.fwxm.dining.bean.FoodPubm;
import com.fwxm.dining.bean.FoodPubo;
import com.fwxm.dining.service.DiningWindowService;
import com.fwxm.dining.service.FoodCardService;
import com.fwxm.dining.service.FoodOrderService;
import com.fwxm.dining.service.FoodPubmService;
import com.fwxm.dining.service.FoodPuboService;
import com.fwxm.dining.service.FoodService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("dining/foodOrder")
public class FoodOrderAction extends SpringSupportAction<FoodOrder, FoodOrderService>{
	@Autowired
	private FoodOrderService foodOrderService;
	@Autowired
	private FoodCardService foodCardService;
	@Autowired
	private FoodPubmService foodPubmService;
	@Autowired
	private FoodPuboService foodPuboService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private DiningWindowService diningWindowService;
	@Autowired
	private MessageService messageService;
	
	@Autowired
	AttachmentManager attachmentManager;
	/**
	 * 预定
	 * @param request
	 * @param foodOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOrder")
	@ResponseBody
	public HashMap<String, Object> saveOrder(HttpServletRequest request,HttpServletResponse response,Date use_time,String meal_name,int count,String fpm_ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String,Object>();
		FoodOrder foodOrder = null;
		try {
			Calendar ca=Calendar.getInstance();
			ca.setTime(use_time);
			int type = Integer.parseInt(meal_name);
			ca.add(Calendar.HOUR_OF_DAY, type==0?10:type==1?15:20);
			use_time = ca.getTime();
			List<FoodPubo> fpos = foodPuboService.getFpos(use_time,type);//("from FoodPubo where use_time=? and meal_name=?", use_time,type).list();
			FoodPubo fpo = null;
			if(fpos.size()>0){
				fpo = fpos.get(0);
			}else{
				map.put("success", false);
				map.put("msg", "未发布菜单！");
			}
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			User u = (User) user.getSysUser();
			Employee e = u.getEmployee();
			//判断有效一卡通
			FoodCard card = (FoodCard)foodCardService.getCardByEmpId(e.getId());
			if(card==null){
				throw new Exception("没有有效一卡通！");
			}
			FoodOrder order = new FoodOrder();
			//保存员工id,姓名
			order.setOrder_man(e.getName());
			order.setUse_time(use_time);
			order.setMeal_name(meal_name);
			order.setEmp_id(e.getId());
			//保存一卡通号
			order.setCard_no(card.getCardNo());
			
			order.setFpo_id(fpo.getId());
			//保存菜单名id,name
			order.setFpm_ids(fpm_ids);
			
			StringBuilder fnames = new StringBuilder(); 
			String[] idss = fpm_ids.split(",");
			for(String id : idss){
				String foodName = foodPubmService.get(id).getFood();
				fnames.append(foodName + ",");
			}
			String food_names = fnames.toString().substring(0, fnames.toString().length()-1);
			order.setFpm_names(food_names);	
			//保存订餐时间
			order.setOrder_date(new Date());
			//保存订单备餐优先级
			order.setGrade(20);
			//初始化备餐号
			order.setPrepareNumber(0);
			order.setCount(count);
			foodOrder = foodOrderService.saveOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败，请重试！");
		}
		map.put("success", true);
		map.put("data", foodOrder);
		/*JSONObject json = JSONObject.fromObject(map);
		String info = json.toString();
		PrintWriter out = response.getWriter();
        out.write(info);//此处如果返回一个List，需要用net.sf.json.JSONArray.fromObject().toString()
        out.flush();
        out.close();*/
		return map;
	}
	
	
	
	
	/**
	 * 订单最终确认
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "setOrderStatusOver")
	@ResponseBody
	public HashMap<String,Object> setOrderStatusOver(HttpServletRequest request,String id) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		try{
			foodOrderService.comfirmOrderDistributed(id);
			List<FoodOrder> orders = foodOrderService.getOrders();
			map.put("success", true);
			map.put("data", orders);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	/**
	 * 订单统计，显示当前食堂状态
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "typeOrder")
	@ResponseBody
	public HashMap<String, Object> typeOrder(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		FoodPubo fpo = getCurrentFpo();
		if(fpo!=null){
			List<Object[]> cos =foodOrderService.getCosGroupBySta(fpo.getId());
			wrapper.put("success", true);
			wrapper.put("data", cos);
			wrapper.put("dataDate", fpo.getUse_time());
			wrapper.put("dataMeal", fpo.getMeal_name());
		}else{
			return JsonWrapper.failureWrapper(1);
		}
		return wrapper;
	}
	private FoodPubo getCurrentFpo() {
		//进门,查出当前的food_pubo,并存入session，供二次使用
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = sdf.format(now);
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String mealType = "0"; 
		if(hour<10){
			mealType = "0"; 
		}else if(10<=hour&&hour<15){
			mealType = "1";
		}else if(hour>=15){
			mealType="2";
		}
		String mark =mealType+day;
		FoodPubo fpo = foodPuboService.getPuboByMark(mark);
		return fpo;
	}
	@RequestMapping(value = "detailOrder")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		FoodOrder foodOrder = null;
		try{
			
			foodOrder = foodOrderService.get(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", true);
		wrapper.put("data", foodOrder);
		return wrapper;
	}
	@RequestMapping(value = "initOrder")
	@ResponseBody
	public HashMap<String, Object> initOrder(HttpServletRequest request, String date,String meal)
			throws Exception {
		HashMap<String, Object> wrapper = null;
		try{
			wrapper = foodOrderService.getOrderByDateAndMeal(date,meal);
			if(wrapper==null){
				wrapper = new HashMap<String, Object>();
			}
			wrapper.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "获取信息失败,请重试！");
		}
		return wrapper;
	}
	/**
	 * 统计当前订单数量，按状态，卡号group
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cardCurrentOrders")
	@ResponseBody
	public HashMap<String,Object> cardCurrentOrders() throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		FoodPubo fpo = getCurrentFpo();
		List<Map<String,Object>> list = null;
		if(fpo!=null){
			list  = foodOrderService.getCardCurrentOrders(fpo);
		}
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;
	}

	/**
	 * 根据卡号获取当餐预定订单
	 * @param card_no
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCurrentOrderByCard")
	@ResponseBody
	public HashMap<String,Object> getCurrentOrderByCard(String cardNo) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		try{
			FoodPubo fpo = getCurrentFpo();
			FoodOrder foodOrder = null;
			if(fpo!=null){
				foodOrder  = foodOrderService.getCurrentOrderByCard(fpo,cardNo);
			}
			List<FoodOrder> orders = foodOrderService.getOrders();
			map.put("success", true);
			map.put("order", foodOrder);
			map.put("data", orders);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	/**
	 * 
	 * @param cardNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getNotGetOrderByCard")
	@ResponseBody
	public HashMap<String,Object> getNotGetOrderByCard(String cardNo) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		FoodPubo fpo = getCurrentFpo();
		FoodOrder foodOrder = null;
		if(fpo!=null){
			try{
				foodOrder  = foodOrderService.distributeOrderWindow(fpo,cardNo);
				List<FoodPubm> list =null;
				if(foodOrder!=null){
					String fpm_ids = foodOrder.getFpm_ids();
					list = foodPubmService.getPubmByIds(fpm_ids);
				}
				map.put("success", true);
				map.put("order", foodOrder);
				map.put("foods", list);
			}catch(Exception e){
				e.printStackTrace();
				map.put("success", false);
				map.put("data", e.getMessage());
			}
		}
		return map;
	}
	/**
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "initSignedPage")
	@ResponseBody
	public HashMap<String,Object> initSignedPage(String employeeId) throws Exception{
		FoodPubo fpo = getCurrentFpo();
		if(fpo==null){
			return  JsonWrapper.failureWrapper("当前没有餐点！");
		}
		FoodOrder order = foodOrderService.initComming(fpo, employeeId);
		return JsonWrapper.successWrapper(order);
	}
	
	/**
	 * 根据订单id更新备餐完毕
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "prepareCompleted")
	@ResponseBody
	public HashMap<String,Object> prepareCompleted(HttpServletRequest request,String id) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		try{
			CurrentSessionUser u = SecurityUtil.getSecurityUser();
			User user = (User) u.getSysUser();
			Employee e = user.getEmployee();
			DiningWindow window = diningWindowService.getDiningWindowByEmployee(e);
			FoodOrder fo = foodOrderService.preparedOk(request,id,window);
			List<FoodOrder> orders = foodOrderService.getOrders();
			map.put("success", true);
			map.put("order", fo);
			map.put("data", orders);
			/*FoodCard card = foodCardService.getCardByCardNo(fo.getCard_no());
			String content = fo.getOrder_man()+"您好，您的的订餐\n单号："+fo.getId()+"\n菜单："+fo.getFpm_names()+"\n份数："+fo.getCount()+"\n已备好，请及时到"+fo.getWdw()+"窗口领餐。\n["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"]";
			System.out.println(content);
			messageService.sendWxMsg((HttpServletRequest) request, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, content, card.getTel());*/
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据订单id作废预定订单？？？？？
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "removeOrdersByIds")
	@ResponseBody
	public HashMap<String,Object> removeOrdersByIds(HttpServletRequest request) throws Exception {
		String str = request.getParameter("ids");
		JSONArray array = JSONArray.fromString(str);
		Object[] objs = array.toArray();
		String[] ids = new String[objs.length];
		for(int i=0;i<objs.length;i++){
			ids[i] = objs[i].toString();
		}
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<FoodOrder> list = foodOrderService.removeOrdersByIds(ids);
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;
	}
	
	
	/**
	 * 根据一卡通id查询订单？？？？？？？？
	 * @param request
	 * @param id 一卡通的id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> ownOrders(HttpServletRequest request, String id) throws Exception{
		try{
			HashMap<String,Object> wapper = new HashMap<String,Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			User u = (User) user.getSysUser();
			Employee e = u.getEmployee();
			//根据用户id查处一卡通的信息
			FoodCard fd = foodCardService.getCardByCardNo(e.getId());
			List<FoodOrder> os = foodOrderService.getOwnOrders(fd.getId());
			if(!os.isEmpty()){
				wapper.put("seccess",true);
				wapper.put("data",os);
				return wapper;
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return JsonWrapper.failureWrapperMsg("获取订单失败，请重试！");
	}
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "remove")
	@ResponseBody
	public HashMap<String, Object> remove(String ids) throws Exception {
		foodOrderService.remove(ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	
	
	/**
	 * 获取菜单统计数据
	 * @param request
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "foodQuanCount")
	@ResponseBody
	public HashMap<String, Object> foodQuanCount(HttpServletRequest request,String startDate,String endDate,String diningName) throws Exception {
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		int meal = diningName.equals("早餐")?0:diningName.equals("午餐")?1:2;
		try{
			wapper = (HashMap<String, Object>) foodOrderService.getOsByQuan(startDate,endDate,meal);
			wapper.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取信息失败，请重试！");
		}
		return wapper;
	}
	
	/**
	 * 按时间段统计各餐点订单总数
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "orderCount")
	@ResponseBody
	public HashMap<String,Object> orderCount(HttpServletRequest request,String startDate,String endDate) throws Exception{
		List<Object[]> list = foodOrderService.getCount(startDate,endDate);
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success", true);
		wapper.put("data",list);
		
		return wapper;
		
	}
	
	@RequestMapping(value = "weixinOrdersCount")
	@ResponseBody
	public HashMap<String,Object> getOrdersCount() throws Exception{
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User u = (User) user.getSysUser();
		Employee e = u.getEmployee();
		int count = foodOrderService.getOsCount(e.getId());
		wrapper.put("success", true);
		wrapper.put("data", count);
		return wrapper;
	}
	
	@RequestMapping(value = "weixinOrders")
	@ResponseBody
	public HashMap<String,Object> getOrdersByEmp(int pageSize,int page) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> items = null;
		User u = (User) user.getSysUser();
		Employee e = u.getEmployee();
		List<Object[]> os = foodOrderService.getOsByEmp(e.getId(),pageSize,page);
		if(!os.isEmpty()){
			for(Object[] obj : os){
				items = new HashMap<String, Object>();
				items.put("fpoId", obj[1].toString());
				items.put("date", obj[2].toString());
				items.put("name", obj[3].toString());
				items.put("count", obj[4].toString());
				items.put("wdw", obj[5].toString());
				items.put("preparenumber", obj[6].toString());
				String idstr = obj[0].toString();
				if(!StringUtil.isEmpty(idstr)){
					String[] ids = idstr.split(",");
					List<Map<String,Object>> foods = new ArrayList<Map<String,Object>>();
					Map<String,Object> food = null;
					for(String id : ids){
						food = new HashMap<String,Object>();
						//只会查出唯一
						Food f = foodPubmService.getFood(id);
						Attachment a = null;
						if(f!=null){
							food.put("info", f);
							List<Attachment> onefiles = attachmentManager.getBusAttachment(f.getId(), "one");
							if(!onefiles.isEmpty()){
								a = onefiles.get(0);
								food.put("file", a);
							}
						}
						foods.add(food);
					}
					items.put("food",foods);
				}
				list.add(items);
			}
		}
		wrapper.put("success", true);
		wrapper.put("data", list);
		return wrapper;
	}
	@RequestMapping(value = "weixinSignComming")
	@ResponseBody
	public HashMap<String,Object> signComming(String id) throws Exception{
		FoodOrder order =null;
		try{
			order = foodOrderService.signComming(id);
		}catch(Exception e){
			return JsonWrapper.failureWrapper(e.getMessage());
		}
		return JsonWrapper.successWrapper(order);
	}
	@RequestMapping(value = "getGridData")
	@ResponseBody
	public HashMap<String,Object> getGridData(HttpServletRequest request) throws Exception{
		List<FoodOrder> orders =null;
		try{
			orders = foodOrderService.getOrders();
		}catch(Exception e){
			return JsonWrapper.failureWrapper(e.getMessage());
		}
		return JsonWrapper.successWrapper(orders);
	}
	
}
