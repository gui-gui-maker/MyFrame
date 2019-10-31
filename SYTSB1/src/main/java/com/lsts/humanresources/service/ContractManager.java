package com.lsts.humanresources.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.humanresources.bean.Contract;
import com.lsts.humanresources.bean.Upload;
import com.lsts.humanresources.dao.ContractDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contract")
public class ContractManager extends EntityManageImpl<Contract,ContractDao>{
    @Autowired
    ContractDao contractDao;
    public List<Contract> getByEmpId(String id){
    	List<Contract> list=new ArrayList<Contract>();
    	String hql="from Contract where FK_EMPLOYEE_ID=? and termination='0'";
    	list=contractDao.createQuery(hql, id).list();
    	return list;
    }
    /**
	 * 取出合同文档
	 * @param id
	 * @return
	 * @throws KhntException
	 */
	public Contract getFile(String id) throws KhntException{
		//取得文档
		byte[] order = contractDao.getFile(id);
		//取得表记录
		Contract contract = contractDao.get(id);
		contract.setDocumentDoc(order);
		return contract;
	}
	/**
	 * 保存合同文档
	 * @param inputStream
	 * @param order
	 */
	public String saveO(InputStream inputStream, Contract order) {
		String orderId = contractDao.saveO(inputStream,order);
		return orderId;
	}
}
