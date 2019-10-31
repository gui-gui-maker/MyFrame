package com.guido.util;


public class BusinessUtil {

	public static String getZymc(Object xzdm,Object zszymc,Object zkfx,Object zklxdm,
				 Object kldm,Object pcdm,Object zklxmc,Object jhlbdm,Object bxdd,
				 Object bhzygs, Object wyyz,Object sfks,Object zybz,Object zylbdm,Object jhxzdm) {
		
		StringBuilder resultVar= new StringBuilder();
		
		String v_xzdm = xzdm==null?"":xzdm.toString();
		String v_zszymc = zszymc==null?"":zszymc.toString();
		String v_zkfx = zkfx==null?"":zkfx.toString();
		String v_zklxdm = zklxdm==null?"":zklxdm.toString();
		
		String v_kldm = kldm==null?"":kldm.toString();
		String v_pcdm = pcdm==null?"":pcdm.toString();
		String v_zklxmc = zklxmc==null?"":zklxmc.toString();
		String v_jhlbdm = jhlbdm==null?"":jhlbdm.toString();
		String v_bxdd = bxdd==null?"":bxdd.toString();
		
		String v_bhzygs = bhzygs==null?"":bhzygs.toString();
		String v_wyyz = wyyz==null?"":wyyz.toString();
		String v_sfks = sfks==null?"":sfks.toString();
		String v_zybz = zybz==null?"":zybz.toString();
		String v_zylbdm = zylbdm==null?"":zylbdm.toString();
		//String v_jhxzdm = jhxzdm==null?"":jhxzdm.toString();
		
		switch(v_xzdm) {
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
		resultVar.append(v_zszymc.trim());
		//招考方向
		if(!StringUtil.isNotEmpty(v_zkfx)) {
			resultVar.append("("+v_zkfx.trim()+")");
		}
		// 招考类型，  对口职教、编导类文理科需要加
		if("43".equals(v_zklxdm)&&"3".equals(v_kldm)) {
			resultVar.append("(只招文科考生)");
		}
		else if("43".equals(v_zklxdm)&&"7".equals(v_kldm)) {
			resultVar.append("(只招理科考生)");
		}
		else if(!StringUtil.isNotEmpty(v_zklxdm)&&Integer.parseInt(v_zklxdm)>=71 && Integer.parseInt(v_zklxdm)<=86) {
			resultVar.append("("+v_zklxmc.trim()+")");
		}
		// 专业类别有： 师范生、指挥类， 对口招生、公费师范生不加（师范）
		if("5".equals(v_zklxdm)&&!v_zkfx.contains("师范")&&!v_zybz.contains("师范")&&!v_pcdm.contains("AB,MOSNPT")) {
			resultVar.append("(师范)");
		}
		else if("2".equals(v_zylbdm)) {
			resultVar.append("(指挥类)");
		}
		//藏彝文加试或单考
		if("15".equals(v_zylbdm)) {
			resultVar.append("(加试藏文)");
		}
		else if("16".equals(v_zylbdm)) {
			resultVar.append("(加试彝文)");
		}
		else if("11".equals(v_zylbdm)&&!"41".equals(v_jhlbdm)) {
			resultVar.append("(招收藏文单考考生)");
		}
		else if("12".equals(v_zylbdm)&&!"41".equals(v_jhlbdm)) {
			resultVar.append("(招收彝文单考考生)");
		}
		//就读校区
		if(!StringUtil.isNotEmpty(v_bxdd)) {
			resultVar.append("("+v_bxdd+")");
		}
		//包含专业
		if(!StringUtil.isNotEmpty(v_bhzygs)) {
			resultVar.append("(包含专业:"+v_bhzygs+")");
		}
		//外语语种
		if(!StringUtil.isNotEmpty(v_wyyz)) {
			resultVar.append("(招收"+v_wyyz+"语种考生)");
		}
		//外语口试
		if("1".equals(v_sfks)) {
			resultVar.append("(外语口试)");
		}
		//专业备注
		if(!StringUtil.isNotEmpty(v_zybz)) {
			resultVar.append("(专业备注:"+v_zybz+")");
		}
		return resultVar.toString();
	}
}
