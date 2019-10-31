package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.equipment2.bean.EquipmentBoxRecord;
import com.lsts.equipment2.service.EquipmentBoxRecordManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;


@Controller
@RequestMapping("equipment/BoxRecord")
public class EquipmentBoxRecordAction extends SpringSupportAction<EquipmentBoxRecord, EquipmentBoxRecordManager> {

    @Autowired
    private EquipmentBoxRecordManager  equipmentBoxRecordManager;
	
}