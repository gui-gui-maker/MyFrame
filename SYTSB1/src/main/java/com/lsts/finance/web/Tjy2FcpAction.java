package com.lsts.finance.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Tjy2Fcp;
import com.lsts.finance.service.Tjy2FcpManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller
@RequestMapping("list/fcp")
public class Tjy2FcpAction extends SpringSupportAction<Tjy2Fcp, Tjy2FcpManager> {

    @Autowired
    private Tjy2FcpManager  tjy2FcpManager;
    
    
	
}