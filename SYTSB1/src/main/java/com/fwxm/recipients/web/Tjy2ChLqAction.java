package com.fwxm.recipients.web;

import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.recipients.service.Tjy2ChLqManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chlq")
public class Tjy2ChLqAction extends
        SpringSupportAction<Tjy2ChLq, Tjy2ChLqManager> {
    @Autowired
    private Tjy2ChLqManager tjy2ChLqManager;


    @RequestMapping({"savechlq"})
    @ResponseBody
    public HashMap<String, Object> savechlq(@RequestBody Tjy2ChLq entity) throws Exception {
        synchronized (this) {
            return tjy2ChLqManager.savechlq(entity);
        }
    }

    @RequestMapping({"usualSave"})
    @ResponseBody
    public HashMap<String, Object> usualSave(HttpServletRequest request, @RequestBody Tjy2ChLq entity) throws Exception {
        synchronized (this) {
            String processToType = request.getParameter("processToType");
            return tjy2ChLqManager.usualSave(entity, processToType);
        }
    }

    @RequestMapping(value = "getFlowStep")
    @ResponseBody
    public ModelAndView getFlowStep(HttpServletRequest request)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = tjy2ChLqManager.getFlowStep(request.getParameter("id"));
        ModelAndView mav = new ModelAndView("app/fwxm/recipients/flow_card", map);
        return mav;
    }

    @RequestMapping(value = "subflow")
    @ResponseBody
    public HashMap<String, Object> subFlow(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.subFlow(request);
    }

    @RequestMapping(value = "shbtg")
    @ResponseBody
    public HashMap<String, Object> shbtg(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.shbtg(request);
    }

    @RequestMapping(value = "shtg")
    @ResponseBody
    public HashMap<String, Object> shtg(HttpServletRequest request) throws Exception {
        return tjy2ChLqManager.shtg(request);
    }

    @RequestMapping({"delete"})
    @ResponseBody
    public HashMap<String, Object> delete(String ids) throws Exception {
        if (StringUtil.isEmpty(ids)) {
            this.log.error("要删除的ID集合为空");
            return JsonWrapper.failureWrapperMsg("要删除的目标对象ID为空！");
        } else {
            this.tjy2ChLqManager.deleteChlq(ids.split(","));
            return JsonWrapper.successWrapper(ids);
        }
    }
}
