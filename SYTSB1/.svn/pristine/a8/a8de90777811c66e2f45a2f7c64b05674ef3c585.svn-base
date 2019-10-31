package demo.crud.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

import demo.crud.bean.OneToMF;
import demo.crud.service.OneToMFManager;


@Controller
@RequestMapping("demo/onetomf")
public class OneToMFAction extends SpringSupportAction<OneToMF, OneToMFManager> {

    @Autowired
    private OneToMFManager  oneToMFManager;

    @RequestMapping(value="fdetail")
    @ResponseBody
    public HashMap<String, Object> getDetail(String id) throws Exception{
    	List<OneToMF> list = oneToMFManager.getList(id);
    	return JsonWrapper.successWrapper("Rows",list);
    }
    
}