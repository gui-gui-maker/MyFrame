package com.edu.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.edu.bean.PlanParam;
import com.edu.bean.Profile;

public class BusinessUtil {

	public static String getZymc(PlanParam major) {
		
		StringBuilder resultVar= new StringBuilder();
		
		switch(major.getXzdm()) {
			case "2":
				resultVar.append(Xuezhi.Er.getValue());
				 break;
			case "5":
				resultVar.append(Xuezhi.Wu.getValue());
				 break;
			case "6":
				resultVar.append(Xuezhi.Liu.getValue());
				 break;
			case "7":
				resultVar.append(Xuezhi.Qi.getValue());
				 break;
			case "8":
				resultVar.append(Xuezhi.Ba.getValue());
				 break;
			case "9":
				resultVar.append(Xuezhi.Jiu.getValue());
				 break;
			case "A":
				resultVar.append(Xuezhi.A.getValue());
				 break;
			default :
				//resultVar.append(Xuezhi.Er.getValue());
		}
		//专业名称
		resultVar.append(major.getZszymc());
		//招考方向
		if(StringUtil.isNotEmpty(major.getZkfx())) {
			resultVar.append("("+major.getZkfx()+")");
		}
		// 招考类型，  对口职教、编导类文理科需要加
		if("43".equals(major.getZklxdm()) && "3".equals(major.getKldm())) {
			resultVar.append("(只招文科考生)");
		}
		else if("43".equals(major.getZklxdm()) && "7".equals(major.getKldm())) {
			resultVar.append("(只招理科考生)");
		}
		else if(StringUtil.isNotEmpty(major.getZklxdm())
				&& Integer.parseInt(major.getZklxdm()) >= 71 
				&& Integer.parseInt(major.getZklxdm()) <= 86) {
			resultVar.append("("+major.getZklxmc()+")");
		}
		// 专业类别有： 师范生、指挥类， 对口招生、公费师范生不加（师范）
		if("5".equals(major.getZklxdm())
				&&!major.getZkfx().contains("师范")
				&&!major.getZybz().contains("师范")
				&&!major.getPcdm().contains("AB,MOSNPT")) {
			resultVar.append("(师范)");
		}
		else if("2".equals(major.getZylbdm())) {
			resultVar.append("(指挥类)");
		}
		//藏彝文加试或单考
		if("15".equals(major.getZylbdm())) {
			resultVar.append("(加试藏文)");
		}
		else if("16".equals(major.getZylbdm())) {
			resultVar.append("(加试彝文)");
		}
		else if("11".equals(major.getZylbdm())&&!"41".equals(major.getJhlbdm())) {
			resultVar.append("(招收藏文单考考生)");
		}
		else if("12".equals(major.getZylbdm())&&!"41".equals(major.getJhlbdm())) {
			resultVar.append("(招收彝文单考考生)");
		}
		//就读校区
		if(StringUtil.isNotEmpty(major.getBxdd())) {
			resultVar.append("("+major.getBxdd()+")");
		}
		//包含专业
		if(StringUtil.isNotEmpty(major.getBhzygs())) {
			resultVar.append("(包含专业:"+major.getBhzygs()+")");
		}
		//外语语种
		if(StringUtil.isNotEmpty(major.getWyyz())) {
			resultVar.append("(招收"+major.getWyyz()+"语种考生)");
		}
		//外语口试
		if("1".equals(major.getSfks())) {
			resultVar.append("(外语口试)");
		}
		//专业备注
		if(StringUtil.isNotEmpty(major.getZybz())) {
			resultVar.append("(专业备注:"+major.getZybz()+")");
		}
		return resultVar.toString();
	}
	
	//检查资料是否完善
	public static boolean checkProfile(Profile p) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		boolean f = true;
		Field[] fs = p.getClass().getDeclaredFields();
		
		for (int i = 0; i < fs.length; i++) {
			String fName = fs[i].getName();
			if("serialVersionUID".equals(fName)) {
				continue;
			}
			System.out.println(fName);
			String mName = "get"+StringUtil.upperFirstCase(fName);
			Method m = Profile.class.getDeclaredMethod(mName);
			Object obj = m.invoke(p);
			if (!"status".equals(fs[i].getName()) 
					&& !"id".equals(fs[i].getName())
					&& !"user".equals(fs[i].getName()) 
					&& StringUtil.isEmpty(obj)) {
				f = false;
			}
		}
		return f;
	}
}
