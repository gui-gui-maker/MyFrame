package demo.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.khnt.chart.service.ChartDataMgr;

public class ChartDataMrgImpl implements ChartDataMgr {

	@Override
	public List<Map<String, Object>> getChartDatas(String dataparam)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0;i<10;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("DEPNAME", "测试部门"+i);
			map.put("NUM", 10+i);
			list.add(map);
		}
		return list;
	}
}
