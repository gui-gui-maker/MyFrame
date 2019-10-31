package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.CodeUniversity;
import com.edu.bean.University0;
import com.edu.hb.repository.CodeUniversityDao;
import com.edu.hb.repository.UniversityDao;
import com.edu.service.UniversityService;
@Service
public class UniversityServiceImpl implements UniversityService{

	@Autowired
	UniversityDao universityDao;
	@Autowired
	CodeUniversityDao codeUniversityDao;
	
	@Override
	public University0 findByYxdm(String yxdm) throws Exception {
		// TODO Auto-generated method stub
		return universityDao.findByYxdm(yxdm);
	}

	@Override
	public List<CodeUniversity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return codeUniversityDao.findAll();
	}

	@Override
	public List<CodeUniversity> findByNf(int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeUniversityDao.findByNf(nf);
	}

	@Override
	public List<CodeUniversity> findByYxdmAndNf(String yxdm, int nf) throws Exception{
		// TODO Auto-generated method stub
		return codeUniversityDao.finByYxdmAndNf(yxdm,nf);
	}

}
