package com.lsts.archives.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.lsts.archives.bean.ArchivesRz;
import com.lsts.archives.service.ArchivesRzManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
@RequestMapping("archives/rz")
public class ArchivesRzAction extends SpringSupportAction<ArchivesRz, ArchivesRzManager> {

    @Autowired
    private ArchivesRzManager  archivesRzManager;
	
}