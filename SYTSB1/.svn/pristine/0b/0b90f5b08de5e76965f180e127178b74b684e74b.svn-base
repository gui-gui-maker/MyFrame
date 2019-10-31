package com.lsts.qualitymanage.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.TxwjspSealRegSug;
import com.lsts.qualitymanage.service.TxwjspSealRegSugManager;


@Controller
@RequestMapping("qa/seal/info")
public class TxwjspSealRegSugAction extends SpringSupportAction<TxwjspSealRegSug, TxwjspSealRegSugManager> {

    @Autowired
    private TxwjspSealRegSugManager  txwjspSealRegSugManager;
	
    /**
	 * 查询审批意见
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getAllByBusId")
	@ResponseBody
	public HashMap<String, Object>  getAllByBusId(String id) {
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误");
		}
		
		return JsonWrapper.successWrapper(txwjspSealRegSugManager.getAllByBusId(id));
	}
}