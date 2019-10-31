package com.edu.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.CatalogJH;
import com.edu.bean.Pcdm;
import com.edu.bean.S1Param;
import com.edu.bean.Statistics1;
import com.edu.service.StatisticsService;
import com.edu.service.impl.RedisService;

@Controller
@RequestMapping("statisticsWeb")
public class StatisticsWeb {

	@Autowired
	private RedisService redisService;

	@Autowired
	StatisticsService statisticsService;

	@RequestMapping("toStatisticsByYxdm")
	public String tosStatisticsByYxdm() {
		return "statistics-yxdm";
	}

	@RequestMapping("toStatisticsByParams")
	public String tosStatisticsByParams() {
		return "statistics-params";
	}

	@RequestMapping("toStatisticsByCatalog")
	public String tosStatisticsByCatalog() {
		return "statistics-list";
	}

	@RequestMapping("statisticsJhsByCatalog")
	@ResponseBody
	public Map<String, Object> statisticsJhsByCatalog(String catalogs) throws Exception {
		Map<String, Object> wapper = new HashMap<String, Object>();
		List<CatalogJH> list = statisticsService.queryJhsByCatalog(catalogs);
		wapper.put("total", list.size());
		wapper.put("rows", list);
		return wapper;
	}

	@RequestMapping("statisticsByYxdmData")
	@ResponseBody
	public Map<String, Object> statisticsByYxdmData(String yxdm) throws Exception {
		Map<String, Object> wapper = new HashMap<String, Object>();
		Map<String, S1Param> result = new HashMap<String, S1Param>();
		List<Statistics1> list = statisticsService.findByParams(yxdm);

		for (Statistics1 s1 : list) {
			String key = s1.getYxdh() + s1.getYxdm() + s1.getPcmc() + s1.getKldm();
			S1Param s1p = result.get(key);
			if (s1p == null) {
				s1p = new S1Param();
				s1p.setKey(key);
				s1p.setYxdh(s1.getYxdh());
				s1p.setYxdm(s1.getYxdm());
				s1p.setYxmc(s1.getYxmc());
				s1p.setPcmc(s1.getPcmc());
				s1p.setPcdm(s1.getPcdm());
				s1p.setKldm(s1.getKldm());
				s1p.setKlmc(s1.getKlmc());
				result.put(key, s1p);
			}
			if (s1.getNf() == 16) {
				s1p.setJhs16(s1.getJhs());
				s1p.setLqs16(s1.getLqs());
				s1p.setDdx16(s1.getDdx());
			} else if (s1.getNf() == 17) {
				s1p.setJhs17(s1.getJhs());
				s1p.setLqs17(s1.getLqs());
				s1p.setDdx17(s1.getDdx());
			} else if (s1.getNf() == 18) {
				s1p.setJhs18(s1.getJhs());
				s1p.setLqs18(s1.getLqs());
				s1p.setDdx18(s1.getDdx());
			}
		}
		List<S1Param> stts = mapToList(result);
		wapper.put("total", stts.size());
		wapper.put("rows", stts);
		return wapper;
	}

	private List<S1Param> mapToList(Map<String, S1Param> map) {
		List<S1Param> list = new ArrayList<S1Param>();
		Iterator<String> i = map.keySet().iterator();
		while (i.hasNext()) {
			list.add(map.get(i.next()));
		}
		// 排序
		return list.stream().sorted(Comparator.comparing(StatisticsWeb::comparingByYxdm)
				.thenComparing(StatisticsWeb::comparingByPcdm).reversed().thenComparing(StatisticsWeb::comparingByKldm))
				.collect(Collectors.toList());
	}

	private static Integer comparingByYxdm(S1Param s1p) {
		return Integer.parseInt(s1p.getYxdm());
	}

	private static String comparingByPcdm(S1Param s1p) {
		return s1p.getPcdm();
	}

	private static Integer comparingByKldm(S1Param s1p) {
		return Integer.parseInt(s1p.getKldm());
	}
}
