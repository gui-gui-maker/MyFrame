package util;

import java.util.*;

/** 
* @author 作者 Jack Rio
* @JDK 1.8
* @version 创建时间：2018年6月29日 下午1:31:17 
* 类说明 
*/
public class LikeHashMap<T extends String,E> extends HashMap<T,E> {

	public Set keySet() {
		Set set = super.keySet();
		TreeSet tSet = null;
		if (set != null) {
			// 对已存在的key进行排序
			tSet = new TreeSet(set);
		}
		return tSet;
	}

	public List<E> get(T key, boolean like) {
		List<E> value = new ArrayList<E>();
		// 是否为模糊搜索
		if (like) {
			List<T> keyList = new ArrayList<T>();
			TreeSet<T> treeSet = (TreeSet) this.keySet();
			for (T string : treeSet) {
				// 通过排序后,key是有序的.
				if (string.indexOf(key) != -1) {
					keyList.add(string);
					value.add(this.get(string));
				} else if (string.indexOf(key) == -1 && keyList.size() == 0) {
					// 当不包含这个key时而且key.size()等于0时,说明还没找到对应的key的开始
					continue;
				} else {
					// 当不包含这个key时而且key.size()大于0时,说明对应的key到当前这个key已经结束.不必要在往下找
					break;
				}
			}
			keyList.clear();
			keyList = null;
		} else {
			value.add(this.get(key));
		}
		return value;
	}

	public static void main(String[] args) {
		LikeHashMap<String,Integer > hMap = new LikeHashMap<String,Integer >();
		for (int i = 0; i < 10; i++) {
			hMap.put("A_" + i, new Integer(i*i));
		}
		long time = System.currentTimeMillis();
		for(Integer in : hMap.get("A", true)) {
			System.out.println("========="+in.toString());
		}
		System.out.println(hMap.get("A", true).size());
		System.out.println(System.currentTimeMillis() - time);
	}

}