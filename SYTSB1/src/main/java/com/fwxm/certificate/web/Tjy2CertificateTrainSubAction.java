package com.fwxm.certificate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fwxm.certificate.bean.Tjy2CertificateTrainSub;
import com.fwxm.certificate.service.Tjy2CertificateTrainSubManager;
import com.khnt.core.crud.web.SpringSupportAction;


@Controller
@RequestMapping("tjy2CertificateTrainSubAction")
public class Tjy2CertificateTrainSubAction extends SpringSupportAction<Tjy2CertificateTrainSub, Tjy2CertificateTrainSubManager> {

    @Autowired
    private Tjy2CertificateTrainSubManager  tjy2CertificateTrainSubManager;
	
}