package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.CodeJhlb;
import com.edu.bean.CodeJhxz;
import com.edu.bean.CodeKl;
import com.edu.bean.CodePc;
import com.edu.bean.CodeZklx;
import com.edu.bean.Pcdm;
import com.edu.hb.repository.CodeJhlbDao;
import com.edu.hb.repository.CodeJhxzDao;
import com.edu.hb.repository.CodeKlDao;
import com.edu.hb.repository.CodePcDao;
import com.edu.hb.repository.CodeZklxDao;
import com.edu.service.CodeService;
@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodePcDao codePcDao;
	@Autowired
	CodeKlDao codeKlDao;
	@Autowired
	CodeJhxzDao codeJhxzDao;
	@Autowired
	CodeJhlbDao codeJhlbDao;
	@Autowired
	CodeZklxDao codeZklxDao;
	
	@Override
	public List<CodePc> getPcdmByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codePcDao.findByNf(nf);
	}
	@Override
	public List<CodeKl> getKlByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeKlDao.findByNf(nf);
	}
	@Override
	public List<CodeJhxz> getJhxzByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeJhxzDao.findByNf(nf);
	}
	@Override
	public List<CodeJhlb> getJhlbByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeJhlbDao.findByNf(nf);
	}
	@Override
	public List<CodeZklx> getZklxByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeZklxDao.findByNf(nf);
	}

}
