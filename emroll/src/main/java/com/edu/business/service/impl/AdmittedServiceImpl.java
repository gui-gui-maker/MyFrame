package com.edu.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.business.bean.College;
import com.edu.business.bean.Major;
import com.edu.business.dao.AdmittedRepository;
import com.edu.business.service.AdmittedService;

public class AdmittedServiceImpl implements AdmittedService{

	@Autowired
	AdmittedRepository admittedRepository;

	@Transactional
	public void statisticalScores(List<College> list,int thisYear) throws Exception {
		for (College college : list) {
			for (Major major : college.getMajors()) {
				//检验重复
				
				List<Object[]> objsList = admittedRepository.statisticScore(college.getCode(), major.getCode(), thisYear-1);
				for (Object[] objs : objsList) {
					admittedRepository.addScoreStatistical(
							college.getCode(), college.getNumb(), college.getName(), 
							major.getCode(), major.getName(),
							Integer.parseInt(objs[0].toString()),Integer.parseInt(objs[1].toString()), Float.parseFloat(objs[2].toString()), 
							objs[3].toString(),String.valueOf(thisYear-1));
				}
				List<Object[]> objsList2 = admittedRepository.statisticScore(college.getCode(), major.getCode(), thisYear-2);
				for (Object[] objs : objsList2) {
					admittedRepository.addScoreStatistical(
							college.getCode(), college.getNumb(), college.getName(), 
							major.getCode(), major.getName(),
							Integer.parseInt(objs[0].toString()),Integer.parseInt(objs[1].toString()), Float.parseFloat(objs[2].toString()), 
							objs[3].toString(),String.valueOf(thisYear-2));
				}
				List<Object[]> objsList3 = admittedRepository.statisticScore(college.getCode(), major.getCode(), thisYear-3);
				for (Object[] objs : objsList3) {
					admittedRepository.addScoreStatistical(
							college.getCode(), college.getNumb(), college.getName(), 
							major.getCode(), major.getName(),
							Integer.parseInt(objs[0].toString()),Integer.parseInt(objs[1].toString()), Float.parseFloat(objs[2].toString()), 
							objs[3].toString(),String.valueOf(thisYear-3));
				}
			}
		}
	}
}
