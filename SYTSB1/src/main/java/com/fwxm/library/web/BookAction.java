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
import com.fwxm.library.bean.Cupboard;
import com.fwxm.library.service.BookManager;
import com.khnt.core.crud.web.SpringSupportAction;



/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Book
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Controller
@RequestMapping("lib/book")
public class BookAction extends
		SpringSupportAction<Book, BookManager> {

	@Autowired
	private BookManager bookManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, Book entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.save(request,entity);
			map.put("success", true);
			map.put("data",entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据二维码获取书籍
	 * @param request
	 * @param qrcode
	 * @param act 操作类别
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getBookByCode")
	@ResponseBody
	public HashMap<String, Object> getBook(HttpServletRequest request, String qrcode,String act) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Book book = bookManager.getBookByCode(qrcode,act);
			map.put("success", true);
			map.put("data",book);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据id集合获取book集合
	 * @param request
	 * @param qrcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getBooks")
	@ResponseBody
	public HashMap<String, Object> getBooks(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Book> books = bookManager.getBooks(ids);
			map.put("success", true);
			map.put("data",books);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 上架，即更新书架上所放书籍
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="stack")
	@ResponseBody
	public HashMap<String, Object> stack(HttpServletRequest request,@RequestBody Cupboard entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.stack(entity);
			map.put("success", true);
			map.put("data",entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 下架，即更新书架上所放书籍
	 * @param ids 书籍id
	 * @throws Exception
	 */
	@RequestMapping(value="unstack")
	@ResponseBody
	public HashMap<String, Object> unstack(HttpServletRequest request,String bookIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.unstack(bookIds);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 图书领取
	 * @param request
	 * @param receivedBy
	 * @param qrcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="receive")
	@ResponseBody
	public HashMap<String, Object> receive(HttpServletRequest request,
			String receivedBy,String qrcode) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.receive(receivedBy,qrcode);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 图书作废
	 * @param request
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="invalid")
	@ResponseBody
	public HashMap<String, Object> invalid(HttpServletRequest request,
			String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.invalid(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			bookManager.delete(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="getUserByEmpid")
	@ResponseBody
	public HashMap<String, Object> name(String empid) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object[]> list = bookManager.getUserByEmpId(empid);
			map.put("success", true);
			map.put("data",list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
}
