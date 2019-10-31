package demo.deviceClassify.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.deviceClassify.bean.DeviceClassify;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;

/** 
 * @author 作者 幸鑫
 * @JDK 1.6
 * @version 创建时间：2014年12月17日 上午9:37:36 
 * 类说明 
 */

@Repository("deviceClassifyDao")
public class DeviceClassifyDao extends EntityDaoImpl<DeviceClassify>{
	
	
	/**
	 * 给设备分类插入树级区别代码。用于进行分类判断
	 */
	@SuppressWarnings("unchecked")
	public void inputFirstLevel(){
		DeviceClassify deviceClassify = null;
		String hql = "from DeviceClassify d where d.pid='0'";
		String hql2 = "from DeviceClassify d where substr(d.device_sort_code,3,1)='0' and pid!='0'";
		String hql3 = "from DeviceClassify d where substr(d.device_sort_code,4,1)='0' and substr(d.device_sort_code,3,1)!='0'";
		List list= this.createQuery(hql).list();
		List list2= this.createQuery(hql2).list();
		List list3= this.createQuery(hql3).list();
		//第一个集合，种类插入
//		System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥开始");
		for(int i=0;i<list.size();i++){
//			System.out.println("#############################################最开始处理中。。。");
			deviceClassify =(DeviceClassify)list.get(i);
			String deviceSortCode=deviceClassify.getDevice_sort_code();
			deviceClassify.setTree_level("1");
			deviceClassify.setTree_code(deviceSortCode);
			deviceClassify.setIs_leaves_node("F");
			this.save(deviceClassify);
		}
//		System.out.println("》》》》》》》》》》》》》》》一级修改完毕");
		//第二个集合处理，类别插入
		for(int i=0;i<list2.size();i++){
			deviceClassify =(DeviceClassify)list2.get(i);
			//得到代码的第二位数
			String check_code=deviceClassify.getDevice_sort_code().substring(0, 2);
			//处理第三个集合
			for(int j=0;j<list3.size();j++){
//				System.out.println("+++++++++++++++++++++++++++++++处理中。。。");
				DeviceClassify deviceClassify2 =(DeviceClassify)list3.get(j);
				String check_code2=deviceClassify2.getDevice_sort_code().substring(0, 2);
				String deviceSortCode=deviceClassify.getPid()+deviceClassify.getDevice_sort_code();
				if(check_code.equals(check_code2)){
					deviceClassify.setTree_level("2");
					deviceClassify.setTree_code(deviceSortCode);
					deviceClassify.setIs_leaves_node("F");
					this.save(deviceClassify);
					break;
				}else{
					deviceClassify.setTree_level("2");
					deviceClassify.setTree_code(deviceSortCode);
					deviceClassify.setIs_leaves_node("T");
					this.save(deviceClassify);
					
				}
			}
//			System.out.println("————————————————————————————————————二级修改完毕");
		}
		//第三个集合数据的修改，品种插入
		for(int i=0;i<list3.size();i++){
			deviceClassify =(DeviceClassify)list3.get(i);
			String pid = deviceClassify.getPid();
			String sort_code = deviceClassify.getDevice_sort_code();
			String tree_code = deviceClassify.getPid()+sort_code;
			String check_code= deviceClassify.getDevice_sort_code().substring(0, 1);
			deviceClassify.setTree_level("3");
			deviceClassify.setIs_leaves_node("T");
			if("1".equals(check_code)){
				tree_code="1000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("2".equals(check_code)){
				tree_code="2000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("8".equals(check_code)){
				tree_code="8000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("7".equals(check_code)&&!"F000".equals(pid)){
				tree_code="7000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("3".equals(check_code)){
				tree_code="3000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("4".equals(check_code)){
				tree_code="4000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("9".equals(check_code)){
				tree_code="9000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("6".equals(check_code)){
				tree_code="6000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("5".equals(check_code)){
				tree_code="5000"+tree_code;
				deviceClassify.setTree_code(tree_code);
			}else if("F000".equals(pid)){
				deviceClassify.setTree_code(tree_code);
			}
		}
	}
	
	
	
}
