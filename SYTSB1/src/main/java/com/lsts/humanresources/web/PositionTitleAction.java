package com.lsts.humanresources.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.PositionTitle;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.humanresources.service.PositionTitleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("positionTitleAction")
public class PositionTitleAction extends SpringSupportAction<PositionTitle, PositionTitleManager> {
    private Logger logger = LoggerFactory.getLogger(PositionTitleAction.class);

    @Autowired
    private PositionTitleManager positionTitleManager;
    @Autowired
    private EmployeeBaseManager employeeBaseManager;
    @Autowired
    private EmployeesService employeesService;
    public String conId;

    //保存合同
    @RequestMapping(value = "savePositionTitle")
    @ResponseBody
    public HashMap<String, Object> savePositionTitle(PositionTitle entity, String empId, String renew,
                                                     HttpServletRequest request) {
        HashMap<String, Object> wrapper = new HashMap<>();
        try {
            if(StringUtil.isEmpty(empId)) {
                empId = entity.getFkEmployeeId();
            }
            EmployeeBase employeeBase = employeeBaseManager.get(empId);
            //判断是否为续签合同
            if (renew != null && !"null".equals(renew)) {
                if (renew.equals("renew")) {
                    PositionTitle con = positionTitleManager.get(entity.getId());
                    //终止之前的合同
                    con.setTermination("1");
                    positionTitleManager.save(con);
                    entity.setId("");
                }
            }
            if (conId != null && !"null".equals(conId)) {
                if (!"".equals(conId)) {
                    this.logger.debug("11111111111111111111111");
                    PositionTitle cont = positionTitleManager.get(conId);
                    cont.setTermination("0");
                    cont.setFkEmployeeId(empId);
                    cont.setSignedMan(employeeBase.getEmpName());
                    cont.setPositionTitleStartDate(entity.getPositionTitleStartDate());
                    cont.setPositionTitleStopDate(entity.getPositionTitleStopDate());
                    cont.setTrialStartDate(entity.getTrialStartDate());
                    cont.setTrialStopDate(entity.getTrialStopDate());
                    cont.setPositionTitleType(entity.getPositionTitleType());
                    cont.setPositiveMoney(entity.getPositiveMoney());
                    cont.setTrialMoney(entity.getTrialMoney());
                    positionTitleManager.save(cont);
                    wrapper.put("id", cont.getId());
                }
            } else {
                entity.setTermination("0");
                entity.setFkEmployeeId(empId);
                entity.setSignedMan(employeeBase.getEmpName());
                positionTitleManager.save(entity);
                wrapper.put("id", entity.getId());
            }
            employeeBase.setPositionTitleType(entity.getPositionTitleType());
            employeeBase.setPositionTitleStopDate(entity.getPositionTitleStopDate());
            employeeBaseManager.save(employeeBase);

            wrapper.put("success", true);
        } catch (Exception e) {
            this.logger.error("获取信息：{}", e.getMessage());
            wrapper.put("success", false);
            wrapper.put("message", "获取信息出错！");
            e.printStackTrace();
        }
        return wrapper;
    }

    //查询合同
    @RequestMapping(value = "detailPositionTitle")
    @ResponseBody
    public HashMap<String, Object> detailPositionTitle(String empId, HttpServletRequest request)
            throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            List<PositionTitle> list = positionTitleManager.getByEmpId(empId);
            PositionTitle con = new PositionTitle();
            if (list.size() > 0) {
                con = list.get(0);
            }
            wrapper.put("con", con);
            wrapper.put("success", true);
        } catch (Exception e) {
            this.logger.error("获取信息：{}", e.getMessage());
            wrapper.put("success", false);
            wrapper.put("message", "获取信息出错！");
            e.printStackTrace();
        }
        return wrapper;
    }

    //终止合同
    @RequestMapping(value = "terminationPositionTitle")
    @ResponseBody
    public HashMap<String, Object> terminationPositionTitle(String id, HttpServletRequest request)
            throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            PositionTitle con = positionTitleManager.get(id);
            con.setTermination("1");
            positionTitleManager.save(con);
            wrapper.put("success", true);
        } catch (Exception e) {
            log.error("获取信息：" + e.getMessage());
            wrapper.put("success", false);
            wrapper.put("message", "获取信息出错！");
            e.printStackTrace();
        }
        return wrapper;
    }

    /**
     * 保存上传文件
     */
    @ResponseBody
    @RequestMapping("saveFile")
    public HashMap<String, Object> saveFile(HttpServletRequest request,
                                            String id, String documentId) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        request.setCharacterEncoding("UTF-8");
        String documentName = request.getParameter("documentName");
        try {
            PositionTitle con = new PositionTitle();
            if (id != null && !id.equals(null)) {
                if (!id.equals("") && !id.equals(null)) {
                    con = positionTitleManager.get(id);
                }
            }
            con.setDocumentId(documentId);
            con.setDocumentName(documentName);
            positionTitleManager.save(con);
            conId = con.getId();
            wrapper.put("success", true);
        } catch (Exception e) {
            wrapper.put("success", false);
        }
        return wrapper;
    }

    /**
     * 取得合同文档
     *
     * @param id
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getFile")
    public void getFile(String id, HttpServletResponse response) throws Exception {
        PositionTitle file = positionTitleManager.getFile(id);
        //下载文档
        FileUtil.download(response, file.getDocumentDoc(), file.getDocumentName(), "application/octet-stream");
    }

    /**
     * 保存合同文档
     *
     * @param request  请求
     * @param deviceId 设备ID
     * @param orderDoc 文档名字
     * @param id       ID
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("saveO")
    public HashMap<String, Object> saveO(HttpServletRequest request,
                                         String id, String documentId, String documentName) throws Exception {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //所有文件
            Map files = multipartRequest.getFileMap();
            if (files.size() > 0) {//获取监察指令文档的信息
                Iterator fileNames = multipartRequest.getFileNames();
                PositionTitle order = new PositionTitle();
                String fileName = (String) fileNames.next();
                CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
                //sorder.setDocumentName(documentName);
                order.setDocumentId(documentId);
                if (id != null) {
                    order.setId(id);
                }
                log.debug((new StringBuilder("上传的文件：")).append(file.getOriginalFilename()).toString());
                //保存文档和记录内容
                positionTitleManager.saveO(file.getInputStream(), order);

                wrapper.put("success", true);
            }

        } catch (Exception e) {
            wrapper.put("success", false);
        }
        return wrapper;
    }

}