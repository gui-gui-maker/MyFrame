/**
 * 
 */
package util;

import java.util.Date;

import com.khnt.utils.DateUtil;

/** 
* @author 作者 Jack Rio
* @JDK 1.6
* @version 创建时间：2016年5月18日 上午11:39:42 
* 类说明 
*/
/**
 * @author hedge
 *
 */
public class Test {
	public static void main(String[] args){
		String ym = DateUtil.getDateTime("yyyyMM", new Date()) ;
		System.out.println(ym);
	}
}
