package demo.crud.web;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;

import demo.crud.bean.OneToOneF;
import demo.crud.service.OneToOneFManager;


@Controller
@RequestMapping("demo/onotoonef")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OneToOneFAction extends SpringSupportAction<OneToOneF, OneToOneFManager> {

    @Autowired
    private OneToOneFManager  oneToOneFManager;
	
}