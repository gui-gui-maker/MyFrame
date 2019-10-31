/**
 * 
 */
package com.lsts.util;

import com.khnt.security.KHThirdUserAuthenticator;

/** 
* @author 作者 Jack Rio
* @JDK 1.6
* @version 创建时间：Nov 23, 2015 4:25:35 PM 
* 类说明 
*/
/**
 * @author hedge
 *
 */
public class TzsbSecurity implements KHThirdUserAuthenticator {

	/* (non-Javadoc)
	 * @see com.khnt.security.KHThirdUserAuthenticator#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public void authenticate(String arg0, String arg1,String arg2) throws Exception {
		System.out.println("登陆前验证：{username:"+arg0+",arg1:"+arg1+",arg2:"+arg2+"}");
		if("admin".equals(arg0)){
			throw new Exception();
		}
	}
}
