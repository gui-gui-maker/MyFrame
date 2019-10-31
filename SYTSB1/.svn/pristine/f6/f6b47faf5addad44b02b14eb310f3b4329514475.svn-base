package com.lsts.finance.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.CwInvoiceP;
import com.lsts.finance.dao.CwInvoiceFDao;
import com.lsts.finance.dao.CwInvoicePDao;

@Service("CwInvoicePManager")
public class CwInvoicePManager extends EntityManageImpl<CwInvoiceP, CwInvoicePDao> {
	
	@Autowired
	CwInvoicePDao cwInvoicePDao;
	@Autowired
	CwInvoiceFDao cwInvoiceFDao;
	
	public final static String CW_SUBMIT = "SUBMIT"; // 已提交
	
//	public final static String CW_FB_SK = "01"; //手开
//	public final static String CW_FB_JD = "02";//机打
//	public final static String CW_FB_SW = "03"; //税务
	
	/**删除
  	 * @param ids
  	 * @throws Exception
  	 */
  	public void delete(CwInvoiceP cwInvoicep) throws Exception{
  		cwInvoicep = cwInvoicePDao.get(cwInvoicep.getId());
  		int start = Integer.parseInt(cwInvoicep.getInvoiceStart());//发票开始号
  		int end = Integer.parseInt(cwInvoicep.getInvoiceEnd());//发票结束号
  		/*StringBuffer invoiceCode = new StringBuffer() ;
  		int count = 0 ;
  		for (int i=start;i<=end;i++){
  			invoiceCode.append(start + count).append(",");
  			count++;
  		}*/
  		List b = cwInvoicePDao.getDatail(String.valueOf(start),cwInvoicep.getInvoiceNum());
  		BigDecimal scount =  (BigDecimal) b.get(0);
  		if( scount.intValue() <= 0  ){
  			cwInvoicePDao.getDelete(String.valueOf(start),cwInvoicep.getInvoiceNum());
			cwInvoicePDao.getDel(cwInvoicep.getId(),start,end);
  		}else{
  			throw new KhntException("已有使用或领用发票,不能删除");
  		
  			
  		}
    }
  	
  	/**保存发票信息
  	 * @param ids
  	 * @throws Exception
  	 */
  	public void saveFp(CwInvoiceP cwInvoicep,CurrentSessionUser user) throws KhntException{ 
  			
  			int start = Integer.parseInt(cwInvoicep.getInvoiceStart());//发票开始号
  			int end = Integer.parseInt(cwInvoicep.getInvoiceEnd());//发票结束号
  			int invoiceLen = cwInvoicep.getInvoiceStart().length();
	  		List b = cwInvoicePDao.getCode(String.valueOf(start),cwInvoicep.getInvoiceNum());
	  		BigDecimal scount =  (BigDecimal) b.get(0);
	  		if( scount.intValue() == 0){
	  			//保存发票登记记录
		  		cwInvoicep.setRegistrantId(user.getId()); //登记人ID
		  		cwInvoicep.setRegistrantName(user.getName());//登记人姓名
				cwInvoicep.setRegistrantDate(new Date());//登记时间
		  		cwInvoicePDao.save(cwInvoicep);
		  		
		  		for (int i=start;i<=end;i++) {
		  			String num = String.valueOf(i);
		  			if(num.length()<invoiceLen){
		  				//自动补0
		  				for(int j=0;j < invoiceLen - String.valueOf(i).length();j++){
		  					num = "0" + num;
		  				}
		  			}
  					CwInvoiceF cwInvoiceF = new CwInvoiceF();
  					cwInvoiceF.setInvoiceCode(num);//发票号
  					cwInvoiceF.setInvoiceType(cwInvoicep.getInvoiceType());
		  	  		cwInvoiceF.setStatus("WSY");//未使用
		  			cwInvoiceF.setCwInvoiceP(cwInvoicep);
		  			int money=0;
					if(cwInvoiceF.getInvoiceMoney()== null || cwInvoiceF.getInvoiceMoney().equals("")){
						cwInvoiceF.setInvoiceMoney(new BigDecimal(money));
					}
	  	  			cwInvoiceFDao.save(cwInvoiceF);
		  		}
	  		} else{
		  		throw new KhntException("发票号已有重复,请重新输入");
	  		}
  	}
}
