package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.service.CodeTableManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.service.CwBorrowMoneyManager;
import com.lsts.humanresources.bean.Tjy2Gjj;
import com.lsts.humanresources.dao.Tjy2GjjDao;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.Tjy2GjjManager;
import com.lsts.office.web.MeetingReqAction;
import com.lsts.qualitymanage.bean.TaskAllot;
import com.lsts.qualitymanage.service.TaskAllotManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("Tjy2GjjAction/RL")
public class Tjy2GjjAction extends SpringSupportAction<Tjy2Gjj, Tjy2GjjManager> {

	@Autowired
   	private EmployeeBaseManager employeeBaseManager;
    @Autowired
    private Tjy2GjjManager  tjy2GjjManager;
    @Autowired
   	private MeetingReqAction meetingReqAction;
    @Autowired
	private CodeTableManager codeTableManager;
    @Autowired
   	private Tjy2GjjDao tjy2GjjDao;
	
    @RequestMapping(value = "saveBasic")
  	@ResponseBody
  	public HashMap<String, Object> saveBasic(HttpServletRequest request, Tjy2Gjj tjy2Gjj)
  			throws Exception {
    	CurrentSessionUser user = SecurityUtil.getSecurityUser(); 	// 获取当前用户登录信息
		HashMap<String, Object> map = new HashMap<String, Object>();

	  			tjy2GjjManager.saveBasic(request, user, tjy2Gjj);
				map.put("success", true);
				map.put("msg", "数据保存成功！");

		return map;
  	}
    
    @RequestMapping(value = "submit")
  	@ResponseBody
  	public HashMap<String, Object> submit(String id)throws Exception {
    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Tjy2Gjj tjy2Gjj=tjy2GjjManager.get(id);
		tjy2Gjj.setBrqr("1");
		tjy2GjjManager.save(tjy2Gjj);
		map.put("success", true);
		map.put("msg", "数据保存成功！");
		return map;
  		
  	}
    @RequestMapping(value = "submit1")
  	@ResponseBody
  	public HashMap<String, Object> submit1(HttpServletRequest request, String id)throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Tjy2Gjj tjy2Gjj=tjy2GjjManager.get(id);
		tjy2Gjj.setBrqr("1");
		tjy2GjjManager.save(tjy2Gjj);
		map.put("success", true);
		map.put("msg", "确认成功！");
		return map;
  		
  	}
    
    @RequestMapping(value = "getcodetabl")
	@ResponseBody
	public HashMap<String, Object> getcodetabl(HttpServletRequest request,String tablname)
			throws Exception {
		    CodeTable  code= codeTableManager.getByCode(tablname);	
		 	return JsonWrapper.successWrapper(code.getCodeTableValues());
	}
    @RequestMapping(value = "getId")
  	@ResponseBody
  	public HashMap<String, Object> getId(HttpServletRequest request,String id)throws Exception {
    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String userid=meetingReqAction.load(request, id);
		String ids=tjy2GjjManager.getId(userid);
		map.put("success", true);
		map.put("id", ids);
		return map;
    }
    
    @RequestMapping(value = "getywId")
  	@ResponseBody
  	public HashMap<String, Object> getywId(String name)throws Exception {
    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String user=tjy2GjjDao.getid(name).toString();
		String ywid = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
				.substring(1,user.toString().length()-1)).replaceAll("'$1'");
		map.put("success", true);
		map.put("id", ywid);
		return map;
    }
    /**
     * 根据员工手机号获取公积金业务ID
     * @param phone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getBusinessId")
  	@ResponseBody
  	public HashMap<String, Object> getBusinessId(String phone)throws Exception {
    	String nameId = employeeBaseManager.getEmpByPhone(phone).getId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String user=tjy2GjjDao.getIdByNameId(nameId).toString();
		String ywid = Pattern.compile("\\b([\\w\\W])\\b").matcher(user.toString()
				.substring(1,user.toString().length()-1)).replaceAll("'$1'");
		map.put("success", true);
		map.put("id", ywid);
		return map;
    }
    @RequestMapping(value = "updateBasicByHand")
  	@ResponseBody
  	public HashMap<String, Object> updateBasicByHand()throws Exception {
    	//CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Tjy2Gjj> list= tjy2GjjManager.updateBasicByHand();
		if(list!=null&&list.size()>0){
			map.put("success", true);
			map.put("msg", "手动更新成功！");
		}else{
			map.put("success", false);
			map.put("msg", "手动更新失败！");
		}
		return map;
    }
    

    
    
    
//    く__,.ヘヽ.　　　　/　,ー､ 〉
//　　　　＼ ', !-─‐-i　/　/´
//　　 　 ／｀ｰ'　　　 L/／｀ヽ､
//　 　 /　 ／,　 /|　 ,　 ,　　　 ',
//　　ｲ 　/ /-‐/　ｉ　L_ ﾊ ヽ!　 i
//　　 ﾚ ﾍ 7ｲ｀ﾄ　 ﾚ'ｧ-ﾄ､!ハ|　 |
//　　　 !,/7 'ゞ'　　 ´i__rﾊiソ| 　 |　　　
//　　　 |.从"　　_　　 ,,,, / |./ 　 |
//　　　 ﾚ'| i＞.､,,__　_,.イ / 　.i 　|
//　　　　 ﾚ'| | / k_７_/ﾚ'ヽ,　ﾊ.　|
//　　　　　 | |/i 〈|/　 i　,.ﾍ |　i　|
//　　　　　.|/ /　ｉ： 　 ﾍ!　　＼　|
//　　 　 　 kヽ>､ﾊ 　 _,.ﾍ､ 　 /､!
//　　　　　 !'〈//｀Ｔ´', ＼ ｀'7'ｰr'
//　　　　　 ﾚ'ヽL__|___i,___,ンﾚ|ノ
//　　　　 　　　ﾄ-,/　|___./
//　　　　 　　　'ｰ'　　!_,.: 周末又在加班，不开心!
    
    
    
	/**
	 * 删除
	 * */
    @RequestMapping(value = "delete")
	@Override
	public HashMap<String, Object> delete(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				tjy2GjjManager.delete(id);
				map.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", true);
				map.put("msg", "删除失败！");
			}
		return map;
	}
	
    
}