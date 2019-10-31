package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.FileUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.bean.Contract;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.service.ContractManager;
import com.lsts.humanresources.service.EmployeeBaseManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("contractAction")
public class ContractAction extends SpringSupportAction<Contract, ContractManager> {

    @Autowired
    private ContractManager  contractManager;
    @Autowired
    private EmployeeBaseManager employeeBaseManager;
    @Autowired
    private EmployeesService employeesService;
    public String conId;
    //保存合同
  	@RequestMapping(value = "saveContract")
  	@ResponseBody
  	public HashMap<String, Object> saveContract(Contract entity,String empId,String renew,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			EmployeeBase employeeBase=employeeBaseManager.get(empId);
  			//判断是否为续签合同
  			if(renew!=null&&!renew.equals(null)){
  			if(renew.equals("renew")){
  				Contract con=contractManager.get(entity.getId());
  				//终止之前的合同
  				con.setTermination("1");
  				contractManager.save(con);
  				entity.setId("");
  			}
  			}
  			if(conId!=null&&!conId.equals(null)){
  			if(!conId.equals("")&&!conId.equals(null)){
  				System.out.println("11111111111111111111111");
  				Contract cont=contractManager.get(conId);
  				cont.setTermination("0");
  				cont.setFkemployeeid(empId);
  				cont.setSignedMan(employeeBase.getEmpName());
  				cont.setContractStartDate(entity.getContractStartDate());
  				cont.setContractStopDate(entity.getContractStopDate());
  				cont.setTrialStartDate(entity.getTrialStartDate());
  				cont.setTrialStopDate(entity.getTrialStopDate());
  				cont.setContractType(entity.getContractType());
  				cont.setPositiveMoney(entity.getPositiveMoney());
  				cont.setTrialMoney(entity.getTrialMoney());
  				contractManager.save(cont);
  				wrapper.put("id", cont.getId());
  			}
  			}else{
  			entity.setTermination("0");
  			entity.setFkemployeeid(empId);
  			entity.setSignedMan(employeeBase.getEmpName());
  			contractManager.save(entity);
  			wrapper.put("id", entity.getId());
  			}
  		employeeBase.setContractType(entity.getContractType());
  		employeeBase.setContractStopDate(entity.getContractStopDate());
  		employeeBaseManager.save(employeeBase);
  		   
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("获取信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  //查询合同
  	@RequestMapping(value = "detailContract")
  	@ResponseBody
  	public HashMap<String, Object> detailContract(String empId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			List<Contract> list=contractManager.getByEmpId(empId);
  			Contract  con=new Contract();
  			if(list.size()>0){
  				con=list.get(0);
  			}
  			wrapper.put("con", con);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("获取信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  //终止合同
  	@RequestMapping(value = "terminationContract")
  	@ResponseBody
  	public HashMap<String, Object> terminationContract(String id,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			Contract  con=contractManager.get(id);
  			con.setTermination("1");
  			contractManager.save(con);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("获取信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	/**
  	 * 保存上传文件
  	 *
  	 */
  	@ResponseBody
	@RequestMapping("saveFile")
	public HashMap<String, Object> saveFile(HttpServletRequest request,
			String id,String documentId) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		request.setCharacterEncoding("UTF-8");
		String documentName = request.getParameter("documentName"); 
		try {
			Contract con=new Contract();
			if(id!=null&&!id.equals(null)){
			if(!id.equals("")&&!id.equals(null)){
			con=contractManager.get(id);
			}
			}
			con.setDocumentId(documentId);
			con.setDocumentName(documentName);
			contractManager.save(con);
			conId=con.getId();
			wrapper.put("success", true);
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}
  	/**
	 * 取得合同文档
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFile")
	public void getFile(String id,HttpServletResponse response) throws Exception {
		Contract file = contractManager.getFile(id);
		//下载文档
		FileUtil.download(response, file.getDocumentDoc(), file.getDocumentName(), "application/octet-stream");
	}
	/**
	 * 保存合同文档
	 * @param request 请求
	 * @param deviceId 设备ID
	 * @param orderDoc 文档名字
	 * @param id ID
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveO")
	public HashMap<String, Object> saveO(HttpServletRequest request,
			String id,String documentId,String documentName) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		//所有文件
		Map files = multipartRequest.getFileMap();
		if (files.size() > 0)
		{//获取监察指令文档的信息
			Iterator fileNames = multipartRequest.getFileNames();
			Contract order = new Contract();
			String fileName = (String)fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile)files.get(fileName);
			//sorder.setDocumentName(documentName);
			order.setDocumentId(documentId);
			if(id!=null){
				order.setId(id);
			}
			log.debug((new StringBuilder("上传的文件：")).append(file.getOriginalFilename()).toString());
			//保存文档和记录内容
			contractManager.saveO(file.getInputStream(),order);
			
			wrapper.put("success", true);
		}
		
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}
  	
}