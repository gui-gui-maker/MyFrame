package com.lsts.qualitymanage.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityNote;
import com.lsts.qualitymanage.dao.QualityNoteDao;


@Service("QualityNoteManager")
public class QualityNoteManager extends EntityManageImpl<QualityNote,QualityNoteDao>{
    @Autowired
    QualityNoteDao qualityNoteDao;
    
    public final static String NOTE_WTJ = "WTJ";// 未提交
	public final static String NOTE_DSH = "DSH";// 待审核
	public final static String NOTE_PASS = "PASS"; // 审核通过
	public final static String NOTE_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String NOTE_NO_PRINT = "N"; // 未打印
	public final static String NOTE_YES_PRINT = "Y"; // 已打印
	
    public String initIdentifier(){
		String indetifier = "";
		Calendar a=Calendar.getInstance();
		int nowYear = a.get(Calendar.YEAR);
		List<QualityNote> qualityFileprintlist = qualityNoteDao.getAllIndentifier();
		if(qualityFileprintlist==null || qualityFileprintlist.size()==0) {
			indetifier = nowYear+"-"+"001";
		} else {
			int[] indetifierArray = new int[qualityFileprintlist.size()];
			for (int i=0;i<qualityFileprintlist.size();i++) {
				//将编号去掉“-”，转换成int型存入数组
				if(qualityFileprintlist.get(i).getIdentifier()!=null && !"".equals(qualityFileprintlist.get(i).getIdentifier())){
					indetifierArray[i] = Integer.parseInt(qualityFileprintlist.get(i).getIdentifier().replaceAll("-", ""));
				}
			}
			int max = indetifierArray[0];
			//获取数组中最大的值
			for (int i : indetifierArray) {
				max = max>i?max:i;
			}
			String indetifier1 = String.valueOf(max+1);
			if(nowYear>Integer.parseInt(indetifier1.substring(0, 4))) {
				indetifier = nowYear+"-"+"001";
	 		}else{
	 			//编号加1后重新组
	 			indetifier = indetifier1.substring(0, 4)+"-"+indetifier1.substring(4, 7);
	 		}
		}
		return indetifier;
    }
}
