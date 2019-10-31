package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.CodeUniversity;
import com.edu.hb.repository.CodeUniversityDao;
import com.edu.service.UniversityService;
@Service
public class UniversityServiceImpl implements UniversityService{

	@Autowired
	CodeUniversityDao codeUniversityDao;

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
		return codeUniversityDao.findByYxdmAndNf(yxdm,nf);
	}

	@Override
	public List<CodeUniversity> findByYxdhAndNf(String yxdh, int nf) throws Exception {
		// TODO Auto-generated method stub
		return codeUniversityDao.findByYxdhAndNf(yxdh,nf);
	}

}
