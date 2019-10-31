package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.common.service.CodeTablesService;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.dao.EquipPpeDao;
import com.lsts.equipment2.service.EquipPpeManager;
import com.lsts.log.service.SysLogService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("equipPpeAction")
public class EquipPpeAction extends SpringSupportAction<EquipPpe, EquipPpeManager> {

    @Autowired
    private EquipPpeManager  equipPpeManager;
    @Autowired
	private CodeTablesService codeTablesService;
    @Autowired
	private SysLogService logService;
    @Autowired
    EquipPpeDao equipPpeDao;
    /**
     * 保存导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveTask")
	@ResponseBody
	public HashMap<String,Object> saveTask(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		
		try {
			contents = equipPpeManager.saveTaskData(files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
    /**
	 * 保存
	 * 
	 * @param request
	 * @param equipPpe
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> save(HttpServletRequest request, EquipPpe equipPpe) throws Exception {
		CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
		String status = request.getParameter("status");//获取状态
		try {
			if (status.equals("add")) {
				String barcode = equipPpeManager.barcode();//生成13位长度的条码
				if(equipPpeManager.searchByBarcode(barcode)==null){
					equipPpe.setBarcode(barcode);
				}else{
					while(true){
						barcode = equipPpeManager.barcode();
						if(equipPpeManager.searchByBarcode(barcode)==null){
							equipPpe.setBarcode(barcode);
							break;
						}
					}
				}
				equipPpe.setCreateDate(new Date());
				equipPpe.setCreateId(curUser.getId());
				equipPpe.setCreateBy(curUser.getName());
			}else if(status.equals("modify")) {
				equipPpe.setLastModifyDate(new Date());
				equipPpe.setLastModifyId(curUser.getId());
				equipPpe.setLastModifyBy(curUser.getName());
			}
			equipPpeManager.save(equipPpe);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存固定资产信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(equipPpe);
	}
	
	/**
     * 根据卡片编号获取固定资产信息
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "checkCardNo")
	@ResponseBody
	public HashMap<String,Object> checkCardNo(String cardNo) {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	List<EquipPpe> list = new ArrayList<EquipPpe>();
    	list = equipPpeManager.getByCardNo(cardNo);
    	hashMap.put("list", list);
		return hashMap;
	}
    
    /**
     * 根据自编号获取固定资产信息
     * @param selfNo
     * @return
     */
    @RequestMapping(value = "checkSelfNo")
	@ResponseBody
	public HashMap<String,Object> checkSelfNo(String selfNo) {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	List<EquipPpe> list = new ArrayList<EquipPpe>();
    	list = equipPpeManager.getBySelfNo(selfNo);
    	hashMap.put("list", list);
		return hashMap;
	}
	
    /**
     * 盘点固定资产
     * @param id
     * @return
     */
    @RequestMapping(value = "inventory")
	@ResponseBody
	public HashMap<String,Object> inventory(HttpServletRequest request,String barcode) {
    	CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	EquipPpe equipPpe = equipPpeManager.searchByBarcode(barcode);
    	if(equipPpe==null){
    		hashMap.put("message", "没查到相应的固定资产！");
    	}else{
    		equipPpe.setInventoryDate(new Date());
    		equipPpe.setInventoryId(curUser.getId());
    		equipPpe.setInventoryName(curUser.getName());
    		equipPpe.setInventory("YPD");
    		try {
				equipPpeManager.save(equipPpe);
				// 写入日志
    			logService.setLogs(equipPpe.getId(), 
    					"固定资产盘点（扫码枪）", 
    					"扫码枪盘点固定资产", 
    					curUser.getId(), curUser.getName(), new Date(),
    					request != null ? request.getRemoteAddr() : "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		hashMap.put("equipPpe", equipPpe);
    	}
		return hashMap;
    	
	}
    /**
     * 盘点固定资产（手动）
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "inventoryByHand")
    @ResponseBody
    public HashMap<String, Object> inventoryByHand(HttpServletRequest request,String ids) throws Exception {
    	return equipPpeManager.inventoryByHand(request,ids);
    }
    /**
     * 更新二维码位数不正确的资产
     * @param id
     * @return
     */
    @RequestMapping(value = "updateErrBarcode")
	@ResponseBody
	public HashMap<String,Object> updateErrBarcode() {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	int updateCount=equipPpeManager.updateErrBarcode();
    	hashMap.put("msg", "已更新"+updateCount+"条固定资产的二维码信息！");
		return hashMap;
    	
	}

    /**
     * 固定资产报废操作
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "scrap")
    @ResponseBody
    public HashMap<String, Object> scrap(String ids) throws Exception {
    	return equipPpeManager.scrap(ids);
    }
 	/**
 	 * 批量修改计量单位、放置地点、使用部门、使用人、资产状态、归属、是否盘点、备注
 	 * @param entity
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
    @RequestMapping(value = "bathModify")
    @ResponseBody
    public HashMap<String, Object> bathModify(HttpServletRequest request) throws Exception {	
    	HashMap<String, Object>  map = new HashMap<String, Object>();
    	CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
    	String ppeId = request.getParameter("ppeId");
    	String [] ppeIds = ppeId.split(",");
    	String entity=request.getParameter("entity").toString();
    	if(StringUtil.isEmpty(entity)) {
    		map.put("success", false);
			map.put("msg", "未勾选要修改的项目！");
    	}else {
    		String setString="";
        	JSONObject jsonObject = JSONObject.fromObject(entity);
        	//通过迭代器获得json当中所有的key值
            Iterator keys = jsonObject.keys();
            //然后通过循环遍历出的key值
            while (keys.hasNext()){
                String key = String.valueOf(keys.next());
                String value = jsonObject.getString(key);
                if(StringUtil.isEmpty(setString)) {
                	setString = " set "+key+" = '"+value+"' ";
                }else {
                	setString += " ,"+key+" = '"+value+"' ";
                }
            }
            try {
            	if(StringUtil.isEmpty(setString)) {
            		map.put("success", false);
        			map.put("msg", "未获取到要修改的数据！");
            	}else {
            		String hql = "update EquipPpe "+setString+" where id in ('"+ppeId.replace(",", "','")+"')";
            		equipPpeDao.createQuery(hql).executeUpdate();
                	for(int i=0;i<ppeIds.length;i++) {
                		// 写入修改日志
            			logService.setLogs(ppeIds[i], 
            					"固定资产批量修改", 
            					"修改内容："+entity, 
            					curUser.getId(), curUser.getName(), new Date(),
            					request != null ? request.getRemoteAddr() : "");
                	}
        			map.put("success", true);
            	}
    		} catch (Exception e) {
    			e.printStackTrace();
    			map.put("success", false);
    			map.put("msg", "请求超时，请稍后再试！");
    		}
    	}
        return map;
    }
}