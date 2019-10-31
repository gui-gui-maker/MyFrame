package com.khnt.signseal.util;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.eseals.bbf.data.ByteArrayStream;
import cn.eseals.bbf.doc.BBFDocument;
import cn.eseals.bbf.doc.ViewMark;
import cn.eseals.seal.data.SealInfo;
import cn.eseals.seal.data.StandardDataObject;

public class TakeSamples {

	public static void takeSeal(byte[] bbfile) throws Exception{
		
		BBFDocument doc=new BBFDocument();
		doc.load(new ByteArrayStream(bbfile));
		
		int len=doc.getMarkCount();
		for (int i=0;i<len;i++)
		{
			ViewMark vm=doc.getViewMark(i);
			if (!vm.getType().equals("eseal"))
				continue;
			
			StandardDataObject sdo=new StandardDataObject();
			sdo.setXML(vm.getData().getStrVal());
			SealInfo si=sdo.getSealInfo();
			
			if(i==0){
				String sealxml = si.getXML();
				System.out.println(sealxml);
				FileOutputStream fos = new FileOutputStream("f://" + si.getSealId() + ".xml");
				fos.write(sealxml.getBytes("UTF-8"));
				fos.close();
			}
			
			/*ByteArrayOutputStream baos=new ByteArrayOutputStream();
			DeflaterOutputStream dos=new DeflaterOutputStream(baos);
			dos.write(si.getXML().getBytes("UTF-8"));
			dos.flush();
			dos.close();*/
		}
		
	}
	
	
	 public static byte[] toByteArray(String filename) throws Exception{  
         
	        File f = new File(filename);  
	        if(!f.exists()){  
	            throw new FileNotFoundException(filename);  
	        }  
	  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
	        BufferedInputStream in = null;  
	        try{  
	            in = new BufferedInputStream(new FileInputStream(f));  
	            int buf_size = 1024;  
	            byte[] buffer = new byte[buf_size];  
	            int len = 0;  
	            while(-1 != (len = in.read(buffer,0,buf_size))){  
	                bos.write(buffer,0,len);  
	            }  
	            return bos.toByteArray();  
	        }catch (IOException e) {   
	            throw e;  
	        }finally{  
	            try{  
	                in.close();  
	            }catch (IOException e) {  
	                  
	            }  
	            bos.close();  
	        }  
	    }
	 
	 public static void main(String[] args) {
		 try {
			takeSeal(toByteArray("src/1.bbf"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
