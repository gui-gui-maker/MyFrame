package demo.crud.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

import demo.crud.bean.OneToMP;
import demo.crud.service.OneToMpManager;


@Controller
@RequestMapping("demo/onetomp")
public class OneToMPAction extends SpringSupportAction<OneToMP, OneToMpManager> {

    @Autowired
    private OneToMpManager  oneToMpManager;

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		oneToMpManager.delete(ids);
		return JsonWrapper.successWrapper();
	}
	
}