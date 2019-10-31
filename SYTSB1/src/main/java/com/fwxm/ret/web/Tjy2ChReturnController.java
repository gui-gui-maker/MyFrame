package com.fwxm.ret.web;

import com.fwxm.ret.bean.Tjy2ChReturn;
import com.fwxm.ret.service.Tjy2ChReturnManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("chreturn")
public class Tjy2ChReturnController extends SpringSupportAction<Tjy2ChReturn, Tjy2ChReturnManager> {

    @Autowired
    Tjy2ChReturnManager tjy2ChReturnManager;

    /**
     * 根据领取单来退还（暂未实装 20181114）
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping({"savechtrbylq"})
    @ResponseBody
    public synchronized HashMap<String, Object> saveChTrByLq(HttpServletRequest request, @RequestBody Tjy2ChReturn entity) throws Exception {
        return tjy2ChReturnManager.saveChTrByLq(request, entity);
    }

    @RequestMapping({"savechtrbyck"})
    @ResponseBody
    public synchronized HashMap<String, Object> saveChTrByCk(HttpServletRequest request, @RequestBody Tjy2ChReturn entity) throws Exception {
        return tjy2ChReturnManager.saveChTrByCk(request, entity);
    }

    /**
     * 生效
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "takeeffect")
    @ResponseBody
    public synchronized HashMap<String, Object> takeEffect(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        Tjy2ChReturn tjy2ChReturn = tjy2ChReturnManager.get(id);
        return tjy2ChReturnManager.takeEffect(request, tjy2ChReturn);
    }

    @RequestMapping({"delete"})
    @ResponseBody
    public HashMap<String, Object> delete(String ids) throws Exception {
        if (StringUtil.isEmpty(ids)) {
            this.log.error("要删除的ID集合为空");
            return JsonWrapper.failureWrapperMsg("要删除的目标对象ID为空！");
        } else {
            this.tjy2ChReturnManager.deleteChTr(ids.split(","));
            return JsonWrapper.successWrapper(ids);
        }
    }
}

