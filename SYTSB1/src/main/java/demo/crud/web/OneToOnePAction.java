package demo.crud.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;

import demo.crud.bean.OneToOneP;
import demo.crud.service.OneToOnePManager;


@Controller
@RequestMapping("demo/onetoonep")
public class OneToOnePAction extends SpringSupportAction<OneToOneP, OneToOnePManager> {

    @Autowired
    private OneToOnePManager  oneToOnePManager;

    /**
     * 系统默认删除方法采用 hql方式 delete from xx where id=?  
     */
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		//oneToOnePManager.del(ids);级联删除
		oneToOnePManager.deleteBusiness(ids);
		return JsonWrapper.successWrapper();
	}
    
}