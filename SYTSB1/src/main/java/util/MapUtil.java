package util;

import java.util.*;

public class MapUtil {
	
	public static List<HashMap<String, Object>> keyToLowerCase(List<HashMap<String, Object>> list) throws Exception{
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> lhm = null;
		for (HashMap<String, Object> hashMap : list) {
			lhm = new LinkedHashMap<String, Object>();
			Set<String> set= hashMap.keySet();
			for (String key : set) {
				lhm.put(key.toLowerCase(), hashMap.get(key));
			}
			maps.add(lhm);
		}
		return maps;
	}
}
