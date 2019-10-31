package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.equipment2.bean.EquipmentAudit;
import com.lsts.equipment2.service.EquipmentAuditManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller
@RequestMapping("Equipment/Audit")
public class EquipmentAuditAction extends SpringSupportAction<EquipmentAudit, EquipmentAuditManager> {

    @Autowired
    private EquipmentAuditManager  equipmentAuditManager;
	
}