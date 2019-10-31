package com.fwxm.library.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.library.bean.Book;
import com.fwxm.library.bean.Borrow;
import com.fwxm.library.service.BorrowManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Borrow
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Controller
@RequestMapping("lib/borrow")
public class BorrowAction extends
		SpringSupportAction<Borrow, BorrowManager> {

	@Autowired
	private BorrowManager borrowManager;
	
	@RequestMapping(value="saveBorrow")
	@ResponseBody
	public HashMap<String, Object> saveBorrow(HttpServletRequest request,@RequestBody Borrow b) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			borrowManager.borrow(b);
			map.put("success", true);
			map.put("data",1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="borrowContinue")
	@ResponseBody
	public HashMap<String, Object> borrowContinue(HttpServletRequest request,String ids,Integer timeLimitAdd) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			borrowManager.borrowContinue(ids,timeLimitAdd);
			map.put("success", true);
			map.put("data",1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="back")
	@ResponseBody
	public HashMap<String, Object> back(HttpServletRequest request,String bookIds,String returnMan) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			borrowManager.back(bookIds,returnMan);
			map.put("success", true);
			map.put("data",1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("data", e.getMessage());
		}
		return map;
	}
	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		// TODO Auto-generated method stub
		return super.detail(request, id);
	}

}
