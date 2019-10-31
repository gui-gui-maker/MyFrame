package com.neo.repository;

import java.util.List;

import com.neo.model.Analysis;

public interface AnalysisDao {

	List<Analysis> findByParams(String yxdh, String kldm, String zydh, int Score);

}
