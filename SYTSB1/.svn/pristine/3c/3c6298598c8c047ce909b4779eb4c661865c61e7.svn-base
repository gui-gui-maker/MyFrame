package com.lsts.org.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.utils.StringUtil;

import util.ReportUtil;
/**
 * 实现更多新闻分页功能的Action
 * @author Administrator
 *
 */
@Controller
@RequestMapping("pagination")
public class NewsPagination {
	@RequestMapping("newsPagination")
	@ResponseBody
	public HashMap<String,Object> getNewsPagination(HttpServletRequest request) throws Exception{
		HashMap<String, Object> map =  new HashMap<String, Object>();
		   String name = request.getParameter("name");
		   String page = request.getParameter("page");
		   String ps = !StringUtil.isEmpty(request.getParameter("pageSize"))?request.getParameter("pageSize"):"15";
		   
		   int pageSize = Integer.parseInt(ps);
		   if(page==null){
			   System.out.println("----------page1------"+page);
			   map = ReportUtil.getNewsN(name,ps,"1");
		   }else{
			   String start = (Integer.parseInt(page)-1)*pageSize+1+"";
			   System.out.println("----------page--start------"+start);
			   map = ReportUtil.getNewsN(name,ps,start);
		   }
		  
		 //  String data = map.get("data").toString();
		   String sumC = map.get("sumC").toString();
		   int s = Integer.parseInt(sumC);
		   int pages = 0;
		   if(s%pageSize>0){
			   pages = (s/pageSize)+1;
		   }else{
			   pages = s/pageSize;
		   }
		   map.put("success", true);
		   map.put("pages", pages);
		 //  map.put("pageSize", pageSize);
		  // map.put("data", data);
		   return map;
	}
}
