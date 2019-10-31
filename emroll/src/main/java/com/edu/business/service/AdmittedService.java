package com.edu.business.service;

import java.util.List;
import java.util.Map;

import com.edu.business.bean.College;


public interface AdmittedService {

	public void statisticalScores(List<College> list,int year) throws Exception;
}
