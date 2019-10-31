package com.lsts.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.bean.Tjy2Fcp;
import com.lsts.finance.dao.FybxdDao;
import com.lsts.finance.dao.Tjy2FcpDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("Tjy2Fcp")
public class Tjy2FcpManager extends EntityManageImpl<Tjy2Fcp,Tjy2FcpDao>{
    @Autowired
    Tjy2FcpDao tjy2FcpDao;
    @Autowired
	FybxdDao fybxdDao;
   /*新增时id为空 修改 id不为空 以免修改时总金额又加一遍*/
   /* public void savefybxd(Fybxd fybxd1,String fybxdid , String userid ,String userName, java.math.BigDecimal total) throws ParseException {

    	List<Tjy2Fcp> tjy2Fcp23 =  tjy2FcpDao.getids(userid);
    	Tjy2Fcp fcp=null;
    	 System.out.println(tjy2Fcp23.size());
    	 //如果是表没有的人就新增
    	 if(fybxdid==null || fybxdid=="" ||tjy2Fcp23.size()==0 ){
    	if(tjy2Fcp23.size()==0 ){
    		 fcp = new Tjy2Fcp();
    		 fcp.setAverageF(total);
    	     fcp.setTotal(total);
    	}else{
    		 fcp = tjy2FcpDao.get(tjy2Fcp23.get(0).getId());
    		 fcp.setAverageF(total.add(tjy2Fcp23.get(0).getAverageF()));
 	    	 fcp.setTotal(fcp.getAverageC().add(fcp.getAverageP().add(total).add(tjy2Fcp23.get(0).getAverageF())));
    	}
 }else{
	 fcp = tjy2FcpDao.get(tjy2Fcp23.get(0).getId());
	 Fybxd fy = fybxdDao.get(fybxdid);
	 fcp.getAverageF();
	 
	System.out.println(total.subtract(fy.getTotal()));
	int res =total.compareTo(fy.getTotal()); 
	//判断修改时的值是否大于修改前的数据
	if(res==1){
		fcp.setAverageF(fcp.getAverageF().add(total.subtract(fy.getTotal()) ));
		fcp.setTotal(fcp.getTotal().add( total.subtract(fy.getTotal())));
		fybxdDao.save(fybxd1);
	}else if(res==-1){
		System.out.println(fcp.getAverageF().subtract(fy.getTotal().subtract(total)));
		System.out.println(fcp.getTotal().subtract( fy.getTotal().subtract(total)));
		fcp.setAverageF(fcp.getAverageF().subtract(fy.getTotal().subtract(total) ));
		fcp.setTotal(fcp.getTotal().subtract( fy.getTotal().subtract(total)));
		fybxdDao.save(fybxd1);
	}
	
	 
 }
    	 fcp.setSysId(userid);
	    	fcp.setSysName(userName);
    	 if(fcp.getAverageC()==null){
	    		 fcp.setAverageC(new BigDecimal(0));
	    	 }
	    	 if(fcp.getAverageP()==null){
	    		 fcp.setAverageP(new BigDecimal(0));
	    	 }
    	
    	 
    	tjy2FcpDao.save(fcp);
    	
    	
    	
    }*/
    /* id=当前登录人id 对应sys——userid*/
	public void savefybxd(Fybxd fybxd,String id) {
		// TODO Auto-generated method stub
		List<Tjy2Fcp> tjy2Fcp23 =  tjy2FcpDao.getids(id);
		Tjy2Fcp fcp=null;
		System.out.println(tjy2Fcp23.size());
		//如果原来没有此人数据新增一条
		if(tjy2Fcp23.size()==0 ){
   		 fcp = new Tjy2Fcp();
   		if(fcp.getAverageC()==null){
   		 fcp.setAverageC(new BigDecimal(0));
   	 }
   	 if(fcp.getAverageP()==null){
   		 fcp.setAverageP(new BigDecimal(0));
   	 }
   	 fcp.setSysId(id);
   	 fcp.setSysName(fybxd.getPeopleConcerned());
   		 fcp.setAverageF(fybxd.getTotal());
   	     fcp.setTotal(fybxd.getTotal());
   	     //如果原来有此人在原来的数额上加上新增的金额
		}else{
			fcp = tjy2Fcp23.get(0);
			if(fcp.getAverageC()==null){
				fcp.setAverageC(new BigDecimal(0));
	    	 }
	    	 if(fcp.getAverageP()==null){
	    		 fcp.setAverageP(new BigDecimal(0));
	    	 }
	    	 System.out.println(fcp.getAverageF());
	    	 fcp.setAverageF(fcp.getAverageF().add(fybxd.getTotal()));
			System.out.println(fcp.getAverageF());
			fcp.setTotal(fcp.getAverageC().add(fcp.getAverageP().add(fcp.getAverageF())));
			
		}
		
		tjy2FcpDao.save(fcp);
		System.out.println(123);
		
		
	}
	public void saveclfbxd(Clfbxd clfbxd, String user_id,String user_name,BigDecimal size) {
		// TODO Auto-generated method stub
		List<Tjy2Fcp> tjy2Fcp23 =  tjy2FcpDao.getids(user_id);
		Tjy2Fcp fcp=null;
		BigDecimal bigDecimalDivide = clfbxd.getClHjJexj().divide(size, 2, BigDecimal.ROUND_HALF_UP);
		System.out.println(tjy2Fcp23.size());
		//如果原来没有此人数据新增一条
		if(tjy2Fcp23.size()==0 ){
   		 fcp = new Tjy2Fcp();
   		if(fcp.getAverageF()==null){
   		 fcp.setAverageF(new BigDecimal(0));
   	 }
   	 if(fcp.getAverageP()==null){
   		 fcp.setAverageP(new BigDecimal(0));
   	 }
   	 fcp.setSysId(user_id);
   	 fcp.setSysName(user_name);
   
   		 fcp.setAverageC(bigDecimalDivide);
   	     fcp.setTotal(bigDecimalDivide);
   	     //如果原来有此人在原来的数额上加上新增的金额
		}else{
			fcp = tjy2Fcp23.get(0);
			if(fcp.getAverageF()==null){
				fcp.setAverageF(new BigDecimal(0));
	    	 }
	    	 if(fcp.getAverageP()==null){
	    		 fcp.setAverageP(new BigDecimal(0));
	    	 }
	    	 System.out.println(fcp.getAverageC());
	    	 fcp.setAverageC(fcp.getAverageC().add(bigDecimalDivide));
			System.out.println(fcp.getAverageF().add(fcp.getAverageP().add(fcp.getAverageC())));
			fcp.setTotal(fcp.getAverageF().add(fcp.getAverageP().add(fcp.getAverageC())));
		}
		tjy2FcpDao.save(fcp);
	}
	
	
	
	public void savepxfbxd(Pxfbxd clfbxd, String user_id, String user_name,BigDecimal size) {
		// TODO Auto-generated method stub
				List<Tjy2Fcp> tjy2Fcp23 =  tjy2FcpDao.getids(user_id);
				Tjy2Fcp fcp=null;
				BigDecimal bigDecimalDivide = clfbxd.getClHjJexj().divide(size, 2, BigDecimal.ROUND_HALF_UP);
				System.out.println(tjy2Fcp23.size());
				//如果原来没有此人数据新增一条
				if(tjy2Fcp23.size()==0 ){
		   		 fcp = new Tjy2Fcp();
		   		if(fcp.getAverageF()==null){
		   		 fcp.setAverageF(new BigDecimal(0));
		   	 }
		   	 if(fcp.getAverageC()==null){
		   		 fcp.setAverageC(new BigDecimal(0));
		   	 }
		   	 fcp.setSysId(user_id);
		   	 fcp.setSysName(user_name);
		   		 fcp.setAverageP(bigDecimalDivide);
		   	     fcp.setTotal(bigDecimalDivide);
		   	     //如果原来有此人在原来的数额上加上新增的金额
				}else{
					fcp = tjy2Fcp23.get(0);
					if(fcp.getAverageF()==null){
						fcp.setAverageF(new BigDecimal(0));
			    	 }
			    	 if(fcp.getAverageC()==null){
			    		 fcp.setAverageC(new BigDecimal(0));
			    	 }
			    	 
			    	 System.out.println(fcp.getAverageP());
			    	 fcp.setAverageP(fcp.getAverageP().add(bigDecimalDivide));
					System.out.println(fcp.getAverageF().add(fcp.getAverageP().add(fcp.getAverageC())));
					fcp.setTotal(fcp.getAverageF().add(fcp.getAverageP().add(fcp.getAverageC())));
				}
				tjy2FcpDao.save(fcp);
		
	}
}