package com.fwxm.library.service;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.User;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.fwxm.library.bean.Book;
import com.fwxm.library.bean.Receive;
import com.fwxm.library.dao.BookDao;
import com.fwxm.library.dao.ReceiveDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName ReceiveManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Service("receiveManager")
public class ReceiveManager extends EntityManageImpl<Receive, ReceiveDao> {
	@Autowired
	ReceiveDao receiveDao;
	@Autowired
	BookDao bookDao;
	@Override
	public synchronized void save(Receive entity) throws Exception {
		if(StringUtil.isEmpty(entity.getId())){
			entity.setStatus("0");
			entity.setApplyTime(new Date());
			super.save(entity);
			Set<Book> books = entity.getBooks();
			// 绑定要领取的book
			for (Book book : books) {
				Book b = bookDao.get(book.getId());
				if(null != b.getFk_receive_id() && !entity.getId().equals(b.getFk_receive_id())){
					throw new Exception(b.getQrcode()+b.getName()+"已被其他人申请领取！");
				}
				b.setFk_receive_id(entity.getId());
				bookDao.save(b);
			}
		}else{
			super.save(entity);
			Set<Book> books = entity.getBooks();
			for (Book book : books) {
				Book b = bookDao.get(book.getId());
				if(null != b.getFk_receive_id() && !entity.getId().equals(b.getFk_receive_id())){
					throw new Exception(b.getQrcode()+b.getName()+"已被其他人申请领取！");
				}
				b.setFk_receive_id(entity.getId());
				bookDao.save(b);
			}
			//删除原来绑定书籍多余的
			List<Book> oBooks = bookDao.createQuery("from Book where fk_receive_id = ?", entity.getId()).list();
			for (Book obook : oBooks) {
				boolean flag = false;
				for (Book book : books) {
					//如果更新后的书籍也有，则不动它
					if(book.getId().equals(obook.getId())){
						flag = true;
						break;
					}
				}
				if(!flag){
					//更新后没有这本书，则更新此本书的fk_receive_id
					obook.setFk_receive_id(null);
				}
			}
		}
	}
	public Receive detail(String id) throws Exception{
		Receive entity = receiveDao.get(id);
		List<Book> books =  bookDao.createQuery("from Book where fk_receive_id = ?", entity.getId()).list();
		entity.setBooks(new HashSet<Book>(books));
		return entity;
	}
	public void sub(String ids) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		//Org o = user.getOrg();
		List<Receive> receives =  receiveDao.createQuery("from Receive where id in ('"+ ids.replaceAll(",", "','")+"')").list();
		for (Receive receive : receives) {
			String status = receive.getStatus();
			if(!"0".equals(status)&&!"88".equals(status)){
				throw new Exception("数据已提交！");
			}
			//暂时不验证是不是部门负责人
			receive.setStatus("1");
			receive.setReceiveOrgLeader(user.getId());
		}
	}
	public void delete(String ids) throws Exception{
		List<Receive> receives =  receiveDao.createQuery("from Receive where id in ('"+ ids.replaceAll(",", "','")+"')").list();
		for (Receive receive : receives) {
			String status = receive.getStatus();
			if(!"0".equals(status)&&!"88".equals(status)){
				throw new Exception("已提交提数据不能删除！");
			}
			//更新绑定数据
			bookDao.createQuery("update Book set fk_receive_id = null where fk_receive_id = ?", receive.getId()).executeUpdate();
			//delete
			receiveDao.remove(receive);
		}
	}
	public void approve(String ids, String approveResult, String approveSuggestion)  throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		List<Receive> receives =  receiveDao.createQuery("from Receive where id in ('"+ ids.replaceAll(",", "','")+"')").list();
		for (Receive receive : receives) {
			String status = receive.getStatus();
			if(!"1".equals(status)){
				throw new Exception("不是审核阶段数据！");
			}
			receive.setApproveBy(user.getId());
			receive.setApproveResult(approveResult);
			receive.setApproveSuggestion(approveSuggestion);
			receive.setApproveTime(new Date());
			receive.setStatus("2");
		}
	}

	
}
