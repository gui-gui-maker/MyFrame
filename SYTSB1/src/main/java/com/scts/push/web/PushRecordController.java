package com.scts.push.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.push.bean.PushRecord;
import com.scts.push.service.PushRecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("push/record")
public class PushRecordController extends SpringSupportAction<PushRecord, PushRecordManager> {
    @Autowired
    private PushRecordManager pushRecordManager;

    @RequestMapping(value = "updatetoreceived")
    @ResponseBody
    public HashMap<String, Object> updateToReceived(HttpServletRequest request) throws Exception {
        String cid=request.getParameter("cid");
        String randomUuid=request.getParameter("_RANDOM_UUID");
        return JsonWrapper.successWrapper(pushRecordManager.updateToReceived(cid, randomUuid));
    }
}
