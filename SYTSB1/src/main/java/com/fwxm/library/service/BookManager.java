package com.fwxm.library.service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.fwxm.library.bean.Book;
import com.fwxm.library.bean.BookFlowLog;
import com.fwxm.library.bean.Cupboard;
import com.fwxm.library.bean.Receive;
import com.fwxm.library.dao.BookDao;
import com.fwxm.library.dao.BookFlowLogDao;
import com.fwxm.library.dao.CupboardDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName BookManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Service("bookManager")
public class BookManager extends EntityManageImpl<Book, BookDao> {
	private static final String STACK = "1";
	private static final String UNDERCARRIAGE = "2";
	private static final String BORROW = "3";
	private static final String BACK = "4";
	private static final String RECEIVE = "5";
	private static final String INVALIDATE = "6";
	@Autowired
	BookDao bookDao;
	@Autowired
	CupboardDao cupboardDao;
	@Autowired
	BookFlowLogDao bookFlowLogDao;
	@Autowired
	UserDao userDao;
	
	public void save(HttpServletRequest request, Book book) throws Exception{
		if(StringUtil.isEmpty(book.getId())){
			User user = SecurityUtil.getSecurityUser().getSysUser();
			Date datetime = new Date();
			book.setLastOpTime(datetime);
			//记录录入人员
			book.setCreateBy(user.getId());
			book.setCreateTime(datetime);
			//状态设置
			book.setStatus("0");
			//新增
			String numbStr = request.getParameter("numb");
			int numb = 1;
			if(numbStr!=null){
				numb = Integer.parseInt(numbStr);
			}
			//拼接二维码
			//先查询是否有同名、同类别的书籍
			String sameBooksHql = "from Book where name=? and category=? and content=?";
			List<Book> sameBooks = bookDao.createQuery(sameBooksHql, book.getName(),book.getCategory(),book.getContent()).list();
			String maxCodeSql = "";
			if(sameBooks.size()>0){
				maxCodeSql = "select max(to_number(SUBSTR(qrcode, instr(qrcode, '-', 1, 3)+1, 2))) maxnum "
						+ "from lib_book where name=? and category = ? and content = ?";
				List<Object[]> lobj = bookDao.createSQLQuery(maxCodeSql, book.getName(),book.getCategory(),book.getContent()).list();
				int index = Integer.parseInt(lobj.get(0)[0].toString());
				String baseCode = sameBooks.get(0).getQrcode();
				baseCode = baseCode.substring(0,baseCode.length()-2);
				for(int i=0;i<numb;i++){
					index++;
					String suffix = "0"+index;
					suffix = suffix.substring(suffix.length()-2);
					book.setQrcode(baseCode+suffix);
					Book b = (Book)book.clone();
					bookDao.save(b);
				}
			}else{
				maxCodeSql = "select max(to_number(SUBSTR(qrcode, instr(qrcode, '-', 1, 2)+1, 3))) maxnum "
						+ "from lib_book where category = ? and content = ?";
				List<Object> lobj = bookDao.createSQLQuery(maxCodeSql,book.getCategory(),book.getContent()).list();
				String index = null;
				if(lobj.get(0)==null){
					index = "001";
				}else{
					BigDecimal ind = (BigDecimal)lobj.get(0);
					int indNumb = ind.intValue();
					index = String.valueOf("00"+(indNumb+1));
				}
				String baseCode = book.getCategory()+"-"+book.getContent()+"-"+index.substring(index.length()-3)+"-";
				for(int i=0;i<numb;i++){
					String suffix = "0"+(i+1);
					suffix = suffix.substring(suffix.length()-2);
					book.setQrcode(baseCode+suffix);
					Book b = (Book)book.clone();
					bookDao.save(b);
				}
			}
			
		}else{
			//修改
			super.save(book);
		}
	}
	/**
	 * 根据操作类别查询相应的书籍
	 * @param qrcode 二维码
	 * @param act 操作（上架、下架、借出、归还、作废、领取）
	 * @return
	 * @throws Exception
	 */
	public Book getBookByCode(String qrcode,String act) throws Exception{
		@SuppressWarnings("unchecked")
		List<Book> list = bookDao.createQuery("from Book where qrcode=?", qrcode).list();
		if(list.size()!=1){
			throw new Exception("未获取到唯一书籍！");
		}
		Book book = list.get(0);
		switch (act) {
		case STACK:
			if("1".equals(book.getStatus())){
				throw new Exception("已上架图书不可重复上架，请确认图书状态！");
			}
			break;
		case UNDERCARRIAGE:
			if(!"1".equals(book.getStatus())){
				throw new Exception("已下架图书不可重复下架，请确认图书状态！");
			}
			break;
		case BORROW:
			if("3".equals(book.getStatus())
					||"6".equals(book.getStatus())
					||"1".equals(book.getIdentifier())){
				throw new Exception("借出、作废状态下的图书、资料不可以借出！");
			}
			break;
		case BACK:
			if(!"3".equals(book.getStatus())){
				throw new Exception("图书未借出！");
			}
			break;
		case RECEIVE:
			if("0".equals(book.getIdentifier())){
				throw new Exception("此图书不是资料，不能领取！");
			}
			break;
		case INVALIDATE:
			if("3".equals(book.getStatus())){
				throw new Exception("图书已借出，不在库！");
			}
			break;

		default:
			break;
		}
		
		return book;
	}

	//
	public List<Book> getBooks(String ids) throws Exception{
		return bookDao.createQuery("from Book where id in ('"+ids.replaceAll(",","','")+"')").list(); 
	}

	/**
	 * 上架，即更新书架上所放书籍
	 * @param entity
	 * @throws Exception
	 */
	public void stack(Cupboard entity) throws Exception{
		//检查库位是否存在
		List<Cupboard> list = cupboardDao.createQuery("from Cupboard where qrcode = ?", entity.getQrcode()).list();
		if(list.size()==0){
			throw new Exception("查无此库位！");
		}else if(!"1".equals(list.get(0).getStatus())){
			throw new Exception("库位不能用！");
		}
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		List<Book> books = entity.getBooks();
		for (Book book : books) {
			
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("1");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(user.getId());
			bookFlowLog.setOperateTime(dateTime);
			bookFlowLogDao.save(bookFlowLog);
			
			bookDao.createQuery("update Book set currentPosition=?,lastOpTime=?,status=? where id = ?", 
					entity.getQrcode(),dateTime,"1",book.getId()).executeUpdate();
			
			
			
		}
		
	}

	//下架
	public void unstack(String ids) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		@SuppressWarnings("unchecked")
		List<Book> books = bookDao.createQuery("from Book where id in ('"+ids.replaceAll(",", "','")+"')").list();
		for (Book book : books) {
			
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("2");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(user.getId());
			bookFlowLog.setOperateTime(dateTime);
			bookFlowLogDao.save(bookFlowLog);
			
			bookDao.createQuery("update Book set currentPosition='',lastOpTime=?,status=? where id = ?",
					dateTime,"2",book.getId()).executeUpdate();
			
			
			
		}
	}
	public void receive(String receivedBy, String qrcode) throws Exception {
		
		String hql = "from Book b, Receive r where b.fk_receive_id = r.id and b.qrcode = ? "
				+ " and b.identifier = '1' and r.status = '2' ";
		List<Object[]> list = bookDao.createQuery(hql, qrcode).list();
		if(list.size()==0){
			throw new Exception("资料未被同意领取！！");
		}else{
			Book book = (Book)list.get(0)[0];
			if("5".equals(book.getStatus())){
				throw new Exception("资料已领取，请勿重复扫描！！");
			}
			User user = userDao.get(receivedBy);
			Org org = user.getOrg();
			Receive receive = (Receive)list.get(0)[1];
			if(!org.getId().equals(receive.getReceiveOrg())){
				throw new Exception("资料不是领取人部门申请的！！");
			}
			book.setReceiveBy(user.getId());
			book.setCurrentPosition("");
			book.setReceiveRecordBy(SecurityUtil.getSecurityUser().getId());
			book.setReceiveTime(new Date());
			book.setStatus("5");
			book.setLastOpTime(new Date());
		}
	}
	public void invalid(String ids) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		@SuppressWarnings("unchecked")
		List<Book> books = bookDao.createQuery("from Book where id in ('"+ids.replaceAll(",", "','")+"')").list();
		for (Book book : books) {
			if("3".equals(book.getStatus())){
				throw new Exception("已借出，不能作废！！");
			}
			if("5".equals(book.getStatus())){
				throw new Exception("已领取，不能作废！！");
			}
			if("6".equals(book.getStatus())){
				throw new Exception("已作废，不能作废！！");
			}
				
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("6");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(user.getId());
			bookFlowLog.setOperateTime(dateTime);
			bookFlowLogDao.save(bookFlowLog);
			
			bookDao.createQuery("update Book set currentPosition='',invalidBy=?,invalidTime=?,lastOpTime=?,status=? where id = ?",
					user.getId(),dateTime,dateTime,"6",book.getId()).executeUpdate();
			
		}
	}
	public void delete(String ids) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		@SuppressWarnings("unchecked")
		List<Book> books = bookDao.createQuery("from Book where id in ('"+ids.replaceAll(",", "','")+"')").list();
		for (Book book : books) {
			BookFlowLog bookFlowLog= new BookFlowLog();
			bookFlowLog.setBookOriginalStatus(book.getStatus());
			bookFlowLog.setAction("88");
			bookFlowLog.setBookId(book.getId());
			bookFlowLog.setBookName(book.getName());
			bookFlowLog.setBookCode(book.getQrcode());
			bookFlowLog.setOperator(user.getId());
			bookFlowLog.setOperateTime(dateTime);
			
			bookFlowLogDao.save(bookFlowLog);
			bookDao.remove(book);
		}
	}
	public List<Object[]> getUserByEmpId(String empid) {
		List<Object[]> list = userDao.createSQLQuery("select id,name from sys_user where employee_id = ?", empid).list();
		return list;
	}

	

}
