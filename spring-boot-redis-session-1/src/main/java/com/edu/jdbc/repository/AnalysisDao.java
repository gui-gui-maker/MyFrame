package com.edu.jdbc.repository;

import java.util.List;

import com.edu.bean.Analysis;


public interface AnalysisDao {

	List<Analysis> findByParams(String yxdh, String kldm, String zydh, int Score);

}
