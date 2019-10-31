package com.lsts.approve.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CopyFieldValueUtil {
	/**
	 * 两个对象的类型相同，将新对象的所有属性值赋值到原来的对象
	 * @param older javaBean
	 * @param newer
	 * @return
	 * @throws Exception
	 */
	public static Object copy(Object older,Object newer) throws Exception{
		Class<?> clzz  = older.getClass();
		Field[] fields = clzz.getDeclaredFields();//得到对象中的字段
		 for (int i = 0; i < fields.length; i++) {
             Field field = fields[i];
             String fieldName = field.getName();
             if("serialVersionUID".equals(fieldName)){
            	 continue;
             }
             // 获得属性的首字母并转换为大写，与setXXX对应
             String firstLetter = fieldName.substring(0, 1).toUpperCase();
             String setMethodName = "set" + firstLetter + fieldName.substring(1);
             Method setMethod = clzz.getMethod(setMethodName,new Class[] { field.getType() });
             String getMethodName = "get" + firstLetter + fieldName.substring(1);
             Method getMethod = clzz.getMethod(getMethodName);
             //新对象的值
             Object value = getMethod.invoke(newer);//调用新对象的getXXX方法
             setMethod.invoke(older, new Object[] { value });//调用旧对象的setXXX方法
         }
		return older;
	}

}
