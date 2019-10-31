package com.edu.business.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.business.bean.MajorChange;
import com.edu.business.bean.MajorChanged;
import com.edu.business.dao.MajorChangeRepository;
import com.edu.business.dao.MajorChangedRepository;
import com.edu.business.service.MajorChangeService;
@Service
public class MajorChangeServiceImpl implements MajorChangeService{
	@Autowired
	MajorChangeRepository mcRepository;
	@Autowired
	MajorChangedRepository mcdRepository;
	
	@Transactional
	public void splitMajor() {
		Pageable pageable = PageRequest.of(1, 10);
		Page<MajorChange> rows = mcRepository.findAll(pageable);
		List<MajorChange> majors = rows.getContent();
		for (MajorChange mc : majors) {
			String[] ms = mc.getMajorName().split("、");
			for (int j = 0; j < ms.length; j++) {
				MajorChanged mcd = new MajorChanged();
				mcd.setBatch(mc.getBatch());
				mcd.setClassify(mc.getClassify());
				mcd.setCollegeCode(mc.getCollegeCode());
				mcd.setCollegeName(mc.getCollegeName());
				mcd.setCollegeNum(mc.getCollegeNum());
				mcd.setMajorName(ms[j]);
				mcd.setRemark(mc.getRemark());
				mcdRepository.save(mcd);
			}
			mcRepository.delete(mc);
		}
	}

	@Transactional
	public void splitAllMajor() {
		List<MajorChange> rows = mcRepository.findAll();
		for (MajorChange mc : rows) {
			String[] ms = mc.getMajorName().split("、");
			for (int j = 0; j < ms.length; j++) {
				MajorChanged mcd = new MajorChanged();
				mcd.setBatch(mc.getBatch());
				mcd.setClassify(mc.getClassify());
				mcd.setCollegeCode(mc.getCollegeCode());
				mcd.setCollegeName(mc.getCollegeName());
				mcd.setCollegeNum(mc.getCollegeNum());
				mcd.setMajorName(ms[j]);
				mcd.setRemark(mc.getRemark());
				mcdRepository.save(mcd);
			}
			mcRepository.delete(mc);
		}
		
	}

}
