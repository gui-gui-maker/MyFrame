package com.lsts.qualitymanage.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.lsts.qualitymanage.bean.QualitySug;
import com.lsts.qualitymanage.service.QualitySugManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("qualitySugAction")
public class QualitySugAction extends SpringSupportAction<QualitySug, QualitySugManager> {

    @Autowired
    private QualitySugManager  QualitySugManager;
	
}