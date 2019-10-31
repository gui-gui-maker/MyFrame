package com.fwxm.library.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.dao.EmployeeDao;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;


import com.fwxm.library.bean.Book;
import com.fwxm.library.bean.BookFlowLog;
import com.fwxm.library.bean.Borrow;
import com.fwxm.library.dao.BookDao;
import com.fwxm.library.dao.BookFlowLogDao;
import com.fwxm.library.dao.BorrowDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName BorrowManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Service("borrowManager")
public class BorrowManager extends EntityManageImpl<Borrow, BorrowDao> {
	@Autowired
	BorrowDao borrowDao;
	@Autowired
	BookDao bookDao;
	@Autowired
	BookFlowLogDao bookFlowLogDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MessageService messageService;
	@Autowired
	EmployeeDao employeeDao;
	public void borrow(Borrow b) throws Exception{
		
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		
		for (Book book : b.getBooks()) {
			//记录流转信息
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("3");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(user.getId());
			bookFlowLog.setOperateTime(dateTime);
			bookFlowLogDao.save(bookFlowLog);
			//更新book状态
			bookDao.createQuery("update Book set lastOpTime=?,currentPosition='',status='3' where id = ?",dateTime,book.getId()).executeUpdate();
			//保存借取信息
			Borrow borrow = new Borrow();
			borrow.setBookId(book.getId());
			borrow.setBookName(book.getName());
			borrow.setBookQrcode(book.getQrcode());
			borrow.setBorrowRecordBy(user.getId());
			borrow.setBorrowedMan(b.getBorrowedMan());
			borrow.setBorrowedTime(dateTime);
			borrow.setPullOffShelf(book.getCurrentPosition());
			borrow.setIsSendMail("0");
			borrow.setTimeLimit(b.getTimeLimit());
			borrowDao.save(borrow);
		}
		
	}

	public void borrowContinue(String ids, Integer timeLimitAdd) throws Exception{
		borrowDao.createQuery("update Borrow set reborrowTimeLimit=? where id in ('"
				+ids.replaceAll(",", "','")+"')", timeLimitAdd).executeUpdate();
		
	}

	public void back(String ids,String returnMan) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		List<Book> books = bookDao.createQuery("from Book where id in ('"+ids.replaceAll(",", "','")+"')").list();
		
		for (Book book : books) {
			//记录流转信息
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("4");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(returnMan);
			bookFlowLog.setOperateTime(dateTime);
			bookFlowLogDao.save(bookFlowLog);
			//更新book状态
			book.setStatus("4");
			book.setLastOpTime(dateTime);
			bookDao.save(book);
			//更新借取信息
			borrowDao.createQuery("update Borrow set returnedMan=?,returnRecordBy=?,returnedTime=? where bookId=? and returnedTime is null ", returnMan,user.getId(),dateTime,book.getId()).executeUpdate();
		}
		
	}

	public void sendWxMessage() throws Exception{
		String hql = "select t.id,t.book_name,t.borrowed_man,t.borrowed_time,t.status from lib_borrow t "
				+ "where (t.borrowed_time + t.time_limit + decode(t.reborrow_time_limit, null, 0, t.reborrow_time_limit)) <= sysdate "
				+ "and t.status = '3'";
		List<Object[]> list = borrowDao.createSQLQuery(hql).list();
		System.out.println("图书借阅超期提醒！！！！！！！！！");
		if(list.size()>0){
			for (Object[] os : list) {
				List<Object> ids = userDao.createSQLQuery("select employee_id from sys_user where id=?", os[2].toString()).list();
				String emp_id = (String)ids.get(0);
				Employee emp = employeeDao.get(emp_id);
				HashMap<String, Object> map= new HashMap<String, Object>();
				map.put("userName", emp.getName());
				map.put("bookName", os[1].toString());
				map.put("borrowTime", os[3].toString());
				messageService.sendMassageByConfig(null, os[0].toString(), emp.getMobileTel(),
						null, "lib_borrow",os[2].toString(), map, null, "1",Constant.PEOPLE_CORPID,  Constant.PEOPLE_PWD);
			}
		}
	}

}
