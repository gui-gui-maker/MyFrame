package com.fwxm.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.contract.bean.ContractAudit;
import com.fwxm.contract.service.ContractAuditService;
import com.khnt.core.crud.web.SpringSupportAction;

@Controller
@RequestMapping("contractAuditAction")
public class ContractAuditAction extends SpringSupportAction<ContractAudit, ContractAuditService> {

	@Autowired
	private ContractAuditService contractAuditService;
	
}
