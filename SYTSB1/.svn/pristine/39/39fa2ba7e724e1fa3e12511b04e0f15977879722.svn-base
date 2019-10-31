package com.fwxm.material.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.bean.Supplier;
import com.fwxm.material.dao.GoodsDao;
import com.fwxm.material.dao.SupplierDao;
import com.fwxm.supplies.bean.Warehousing;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

@Service("supplierManager")
public class SupplierManager extends EntityManageImpl<Supplier, SupplierDao>{
	@Autowired
	SupplierDao supplierDao;
	@Autowired
	GoodsDao goodsDao;
	
	
	public HashMap<String, Object> saveBean(Supplier entity){
		return null;
		
	}
	
	public HSSFWorkbook outYsb(String gysId,String startTime,String endTime,String orgId) throws ParseException{
		HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄
		try {

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        Date time=sdf.parse(endTime);
	        Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(time);  
	        rightNow.add(Calendar.MONTH, 1); 
			//查询类型
			List<Map<String, String>> list=goodsDao.getGoodsTypeBygysIdNotBg(gysId,startTime,sdf.format(rightNow.getTime()),orgId);
			
			//获取样式
            HSSFCellStyle cellstype1 = workbook.createCellStyle();
            //居中对齐
            cellstype1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellstype1.setWrapText(true);
            //设置字体
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 20); // 字体高度
            font.setFontName(" 黑体 "); // 字体
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellstype1.setFont(font);
            //获取样式
            HSSFCellStyle cellstype = workbook.createCellStyle();
            //居中对齐
            cellstype.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellstype.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellstype.setWrapText(true);
            cellstype.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            cellstype.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            cellstype.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            cellstype.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            //设置字体
            HSSFFont font1 = workbook.createFont();
            font1.setFontHeightInPoints((short) 11); // 字体高度
            font1.setFontName(" 宋体 "); // 字体
            cellstype.setFont(font1);
            


            HSSFCellStyle cellstype_header = workbook.createCellStyle();
            //居中对齐
            cellstype_header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellstype_header.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellstype_header.setWrapText(true);
            cellstype_header.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            cellstype_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            cellstype_header.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            cellstype_header.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            //设置字体
            HSSFFont font_header = workbook.createFont();
            font_header.setFontHeightInPoints((short) 12); // 字体高度
            font_header.setFontName(" 宋体 "); // 字体
//            font_header.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellstype_header.setFont(font_header);
	        if(!list.isEmpty()){
	        	for (int i = 0; i < list.size(); i++) {
	        		Map<String, String> map=list.get(i);
//	        		if(!"办公耗材".equals(map.get("LX_NAME"))){
	                    HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
	                    
	                    workbook.setRepeatingRowsAndColumns(i, 0, 8, 0, 5);//设置重复打印表头
	                    HSSFPrintSetup printSetup = sheet.getPrintSetup();
	                    printSetup.setLandscape(true);//设置横向打印
	                    printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);        //A4纸张打印
	                    printSetup.setScale((short) 85);//缩放比例
	                    sheet.setMargin(HSSFSheet.BottomMargin,( double ) 1.5 );// 页边距（下）
	                    sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.5 );// 页边距（左）
	                    sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );// 页边距（右）
	                    sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );// 页边距（上）
	                    Footer footer = sheet.getFooter();
	                    footer.setCenter( "第" + HeaderFooter.page() + "页，共 " + HeaderFooter.numPages()+"页" );
	                    
	                    
	                    
			            workbook.setSheetName(i, map.get("LX_NAME")+"验收表");//设置Sheet的名称
			            //设置列宽
		                sheet.setColumnWidth(0, 6000); //产品名称
		                sheet.setColumnWidth(1, 8000); //供应商
		                sheet.setColumnWidth(2, 6000); //规格及型号
		                sheet.setColumnWidth(3, 3000); //单位
		                sheet.setColumnWidth(4, 3000); //数量
		                sheet.setColumnWidth(5, 3000); //单价
		                sheet.setColumnWidth(6, 3000); //总金额
		                sheet.setColumnWidth(7, 5000); //使用部门
		                sheet.setColumnWidth(8, 5000); //验收人签字
		              
		                HSSFRow row0 = sheet.createRow(0); // 创建第一行
		                row0.setHeightInPoints(40);
		                HSSFCell cell10 = row0.createCell(0);
		                cell10.setCellValue(startTime+"至"+endTime+map.get("LX_NAME")+"验收表");
		                cell10.setCellStyle(cellstype1);
		                for (int j = 1; j < 9; j++) {
		                    HSSFCell cell = row0.createCell(j);
		                    cell.setCellStyle(cellstype1);
		                }
		                sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 8));
		                
		                List<Warehousing> wlist=supplierDao.getWarehousing(gysId,startTime,sdf.format(rightNow.getTime()),orgId,map.get("ID"));
		                Warehousing bean=null;
		                if(!wlist.isEmpty()){
		                	bean=wlist.get(0);
		                }
		                if(bean!=null){
		                	  HSSFRow row1 = sheet.createRow(1); // 创建第2行
		                      row1.setHeightInPoints(40);
		                      HSSFCell cell0 = row1.createCell(0);
		                      cell0.setCellValue("收货单位");
		                      cell0.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));//起始行号，终止行号， 起始列号，终止列号
		                      HSSFCell cell1 = row1.createCell(1);
		                      cell1.setCellValue(bean.getShdw());
		                      cell1.setCellStyle(cellstype_header);
		                      
		                      
		                      HSSFRow row2 = sheet.createRow(2); // 创建第3行
		                      row2.setHeightInPoints(40);
		                      HSSFCell cell00 = row2.createCell(0);
		                      cell00.setCellValue("地址");
		                      cell00.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 8));//起始行号，终止行号， 起始列号，终止列号
		                      HSSFCell cell01 = row2.createCell(1);
		                      cell01.setCellValue(bean.getDz());
		                      cell01.setCellStyle(cellstype_header);
		                      for (int j = 2; j < 9; j++) {
		                          HSSFCell cell = row1.createCell(j);
		                          cell.setCellStyle(cellstype_header);
		                          HSSFCell cell2 = row2.createCell(j);
		                          cell2.setCellStyle(cellstype_header);
		                      }
		                      
		                      HSSFRow row3 = sheet.createRow(3); // 创建第4行
		                      row3.setHeight((short)460);
		                      HSSFCell cell30 = row3.createCell(0);//列
		                      cell30.setCellValue("联系人姓名");
		                      cell30.setCellStyle(cellstype_header);
		                      
		                      
		                      sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 2));//起始行号，终止行号， 起始列号，终止列号
		                      HSSFCell cell31 = row3.createCell(3);
		                      cell31.setCellValue("联系人部门");
		                      cell31.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));//起始行号，终止行号， 起始列号，终止列号
		                      HSSFCell cell32 = row3.createCell(6);
		                      cell32.setCellValue("电话");
		                      cell32.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 8));//起始行号，终止行号， 起始列号，终止列号
		                      
		                      HSSFRow row4 = sheet.createRow(4); // 创建第5行
		                      row4.setHeightInPoints(40);
		                      HSSFCell cell40 = row4.createCell(0);//列
		                      cell40.setCellValue(bean.getLxrmc());
		                      cell40.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));//起始行号，终止行号， 起始列号，终止列号终止列号
		                      HSSFCell cell41 = row4.createCell(3);
		                      cell41.setCellValue(bean.getLxrbm());
		                      cell41.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));//起始行号，终止行号， 起始列号，终止列号
		                      HSSFCell cell42 = row4.createCell(6);
		                      cell42.setCellValue(bean.getDh());
		                      cell42.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 8));//起始行号，终止行号， 起始列号，终止列号
		                      
		                      for (int j = 1; j < 3; j++) {
		                          HSSFCell cell = row3.createCell(j);
		                          cell.setCellStyle(cellstype_header);
		                          HSSFCell cell4 = row4.createCell(j);
		                          cell4.setCellStyle(cellstype_header);
		                      }
		                      for (int j = 4; j < 6; j++) {
		                          HSSFCell cell = row3.createCell(j);
		                          cell.setCellStyle(cellstype_header);
		                          HSSFCell cell4 = row4.createCell(j);
		                          cell4.setCellStyle(cellstype_header);
		                      }for (int j = 7; j < 9; j++) {
		                          HSSFCell cell = row3.createCell(j);
		                          cell.setCellStyle(cellstype_header);
		                          HSSFCell cell4 = row4.createCell(j);
		                          cell4.setCellStyle(cellstype_header);
		                      }
		                      
		                }

	                      HSSFRow row5 = sheet.createRow(5); // 创建第6行
	                      row5.setHeight((short)500);
	                      String [] title={"产品名称","供应商","规格型号","单位","数量","单价（元）","金额（元）","使用部门","使用部门人员签字"};
	                      for (int j = 0; j < title.length; j++) {
	                      	HSSFCell cell50 = row5.createCell(j);//列
	                          cell50.setCellValue(title[j]);
	                          cell50.setCellStyle(cellstype_header);
	      					}
	                      //产品列表
	                      List<Map<String, Object>> wplist=supplierDao.getWpList(gysId, startTime, sdf.format(rightNow.getTime()), orgId,map.get("ID"));
	                      
	                      Double zje=0.00;
	                      Double zhj=0.00;
	                      //查询数据列
	                      for (int j = 0; j < wplist.size(); j++) {
	                      Map<String, Object> gbean=wplist.get(j);
	                 	 Set<String> set = gbean.keySet();
	                  	 if(set != null && !set.isEmpty()) {
	                  	 for(String key : set) {
	                  	 if(gbean.get(key) == null) { gbean.put(key, ""); }
	                  	 }
	                  	 }
	                  	
	                  	
	                   	   double je=Double.parseDouble(gbean.get("JE").toString());
	                   	   zje=zje+je*Float.parseFloat(gbean.get("SL").toString());
	                   	   zhj=zhj+je*Float.parseFloat(gbean.get("SL").toString());
	                   	   
	                   	   HSSFRow row6 = sheet.createRow(sheet.getLastRowNum()+1);
	                   	   row6.setHeightInPoints(40);
	                   	   HSSFCell cell60 = row6.createCell(0);//列
	                   	   cell60.setCellValue(gbean.get("WPMC").toString());
	                   	   cell60.setCellStyle(cellstype_header);
	                   	   HSSFCell cell61 = row6.createCell(1);//列
	                   	   cell61.setCellValue(gbean.get("GYSMC").toString());
	                   	   cell61.setCellStyle(cellstype_header);
	                   	   HSSFCell cell62 = row6.createCell(2);//列
	                   	   cell62.setCellValue(gbean.get("GGJXH").toString());
	                   	   cell62.setCellStyle(cellstype_header);
	                   	   HSSFCell cell63 = row6.createCell(3);//列
	                   	   cell63.setCellValue(gbean.get("DW").toString());
	                   	   cell63.setCellStyle(cellstype_header);
	                   	   HSSFCell cell64 = row6.createCell(4);//列
	                   	   cell64.setCellValue(gbean.get("SL").toString());
	                   	   cell64.setCellStyle(cellstype_header);
	                   	   HSSFCell cell65 = row6.createCell(5);//列
	                   	   cell65.setCellValue(je);
	                   	   
	                   	   cell65.setCellStyle(cellstype_header);
	                   	   HSSFCell cell66 = row6.createCell(6);//列
	                   	   
	                   	   cell66.setCellValue(je*Float.parseFloat(gbean.get("SL").toString()));
	                   	   cell66.setCellStyle(cellstype_header);
	                   	   HSSFCell cell67 = row6.createCell(7);//列
	                   	   cell67.setCellValue(gbean.get("LQBM").toString());//使用部门
	                   	   cell67.setCellStyle(cellstype_header);
	                   	   HSSFCell cell68 = row6.createCell(8);//列
	                   	   cell68.setCellValue("");
	                   	   cell68.setCellStyle(cellstype_header);
	                      
	                   	   int size=j+1;
	                   	   if(size%6==0 || size==wplist.size()){
		              	   HSSFRow row7 = sheet.createRow(sheet.getLastRowNum()+1);
		                   row7.setHeightInPoints(40);
		               	   HSSFCell cell70 = row7.createCell(0);//列
		               	   cell70.setCellValue("合计");
		               	   cell70.setCellStyle(cellstype_header);

		               	   HSSFCell cell71 = row7.createCell(1);//列
		               	   cell71.setCellStyle(cellstype_header);
		               	   HSSFCell cell72 = row7.createCell(2);//列
		               	   cell72.setCellStyle(cellstype_header);
		               	   HSSFCell cell73 = row7.createCell(3);//列
		               	   cell73.setCellStyle(cellstype_header);
		               	   HSSFCell cell77 = row7.createCell(7);//列
		               	   cell77.setCellStyle(cellstype_header);
		               	   HSSFCell cell78 = row7.createCell(8);//列
		               	   cell78.setCellStyle(cellstype_header);
		               	   
		               	   
		               	   HSSFCell cell74 = row7.createCell(4);//列
//		               	   cell74.setCellValue(num);
		               	   cell74.setCellStyle(cellstype_header);
		               	   HSSFCell cell75 = row7.createCell(5);//列
//		               	   cell75.setCellValue(je);
		               	   cell75.setCellStyle(cellstype_header);
		               	   HSSFCell cell76 = row7.createCell(6);//列
		               	   cell76.setCellValue(zje);
		               	   cell76.setCellStyle(cellstype_header);
		               	   zje=0.0;
		               	   
		               	   if(size==wplist.size()){//创建总计
		               		HSSFRow row=sheet.createRow(sheet.getLastRowNum()+1);
		               		row.setHeightInPoints(40);
			               	   HSSFCell cell90 = row.createCell(0);//列
			               	   cell90.setCellValue("总计");
			               	   cell90.setCellStyle(cellstype_header);
			               	   for (int y = 1; y < 6; y++) {
		                          HSSFCell cell = row.createCell(y);
		                          cell.setCellStyle(cellstype_header);
			               	   }
			               	   HSSFCell cell96 = row.createCell(6);//列
			               	   cell96.setCellValue(zhj);
			               	   cell96.setCellStyle(cellstype_header);

			               	   HSSFCell cell97 = row.createCell(7);//列
			               	   cell97.setCellStyle(cellstype_header);
			               	   HSSFCell cell98 = row.createCell(8);//列
			               	   cell98.setCellStyle(cellstype_header);
		               	   }
		               	   
		               	   
		               	   //最后一行

		                   HSSFRow row8 = sheet.createRow(sheet.getLastRowNum()+1);
		                   row8.setHeightInPoints(40);
		               	   HSSFCell cell80 = row8.createCell(0);//列
		               	   cell80.setCellValue("经办人：");
		               	   cell80.setCellStyle(cellstype_header);
		               	   HSSFCell cell81 = row8.createCell(1);//列
		               	   cell81.setCellValue("");
		               	   cell81.setCellStyle(cellstype_header);
		                      sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(),sheet.getLastRowNum(), 1, 4));//起始行号，终止行号， 起始列号，终止列号
		               	   for (int y = 2; y < 5; y++) {
		                          HSSFCell cell = row8.createCell(y);
		                          cell.setCellStyle(cellstype_header);
		                      }

		               	   HSSFCell cell85 = row8.createCell(5);//列
		               	   cell85.setCellValue("时间：");
		               	   cell85.setCellStyle(cellstype_header);
		               	   sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(), 6, 8));//起始行号，终止行号， 起始列号，终止列号
		               	   for (int y = 6; y < 9; y++) {
		                          HSSFCell cell = row8.createCell(y);
		                          cell.setCellStyle(cellstype_header);
		                      }
		              	   }
	                      }
	                      
	                      //列表合并
	                      //------------合并供应商
//	                      sheet.addMergedRegion(new CellRangeAddress(6, 5+wplist.size(), 1, 1));//起始行号，终止行号， 起始列号，终止列号
	                      
//	                      HSSFRow row7 = sheet.createRow(6+wplist.size());
//	                      row7.setHeight((short)460);
//	               	   HSSFCell cell70 = row7.createCell(0);//列
//	               	   cell70.setCellValue("合计");
//	               	   cell70.setCellStyle(cellstype_header);
//
//	               	   HSSFCell cell71 = row7.createCell(1);//列
//	               	   cell71.setCellStyle(cellstype_header);
//	               	   HSSFCell cell72 = row7.createCell(2);//列
//	               	   cell72.setCellStyle(cellstype_header);
//	               	   HSSFCell cell73 = row7.createCell(3);//列
//	               	   cell73.setCellStyle(cellstype_header);
//	               	   HSSFCell cell77 = row7.createCell(7);//列
//	               	   cell77.setCellStyle(cellstype_header);
//	               	   HSSFCell cell78 = row7.createCell(8);//列
//	               	   cell78.setCellStyle(cellstype_header);
//	               	   
//	               	   
//	               	   HSSFCell cell74 = row7.createCell(4);//列
//	               	   cell74.setCellValue(num);
//	               	   cell74.setCellStyle(cellstype_header);
//	               	   HSSFCell cell75 = row7.createCell(5);//列
//	               	   cell75.setCellValue(je);
//	               	   cell75.setCellStyle(cellstype_header);
//	               	   HSSFCell cell76 = row7.createCell(6);//列
//	               	   cell76.setCellValue(zje);
//	               	   cell76.setCellStyle(cellstype_header);
//	               	   
//	               	   
//	               	   //最后一行
//
//	                   HSSFRow row8 = sheet.createRow(7+wplist.size());
//	                   row8.setHeight((short)460);
//	               	   HSSFCell cell80 = row8.createCell(0);//列
//	               	   cell80.setCellValue("经办人：");
//	               	   cell80.setCellStyle(cellstype_header);
//	               	   HSSFCell cell81 = row8.createCell(1);//列
//	               	   cell81.setCellValue("");
//	               	   cell81.setCellStyle(cellstype_header);
//	                      sheet.addMergedRegion(new CellRangeAddress(7+wplist.size(), 7+wplist.size(), 1, 4));//起始行号，终止行号， 起始列号，终止列号
//	               	   for (int j = 2; j < 5; j++) {
//	                          HSSFCell cell = row8.createCell(j);
//	                          cell.setCellStyle(cellstype_header);
//	                      }
//
//	               	   HSSFCell cell85 = row8.createCell(5);//列
//	               	   cell85.setCellValue("时间：");
//	               	   cell85.setCellStyle(cellstype_header);
//	               	   sheet.addMergedRegion(new CellRangeAddress(7+wplist.size(), 7+wplist.size(), 6, 8));//起始行号，终止行号， 起始列号，终止列号
//	               	   for (int j = 6; j < 9; j++) {
//	                          HSSFCell cell = row8.createCell(j);
//	                          cell.setCellStyle(cellstype_header);
//	                      }
	        		}
//				}
	        	
	        	
	            
	            
	        }else{
                HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
                HSSFRow row0 = sheet.createRow(0); // 创建第一行
                row0.setHeightInPoints(40);
                HSSFCell cell10 = row0.createCell(0);
                cell10.setCellValue("没有查找到验收数据");
                cell10.setCellStyle(cellstype1);
                sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 8));
	        }
	        
	        
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	/**
	 * 导出办公耗材验收表
	 * @param gysId
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 * @throws ParseException 
	 */
	public HSSFWorkbook outBgYsb(String gysId,String startTime,String endTime,String orgId) throws ParseException{
		HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        Date time=sdf.parse(endTime);
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(time);  
        rightNow.add(Calendar.MONTH, 1); 
        
        
		List<Map<String, Object>> list=supplierDao.getBghcBean(gysId, startTime, sdf.format(rightNow.getTime()), orgId);
		 HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet

         workbook.setRepeatingRowsAndColumns(0, 0, 5, 0, 6);//设置重复打印表头
         
		 HSSFPrintSetup printSetup = sheet.getPrintSetup();
         printSetup.setLandscape(true);//设置横向打印
         printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);        //A4纸张打印
         printSetup.setScale((short) 120);//缩放比例
         sheet.setMargin(HSSFSheet.BottomMargin,( double ) 1.5 );// 页边距（下）
         sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.5 );// 页边距（左）
         sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );// 页边距（右）
         sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );// 页边距（上）
         Footer footer = sheet.getFooter();
         footer.setCenter( "第" + HeaderFooter.page() + "页，共 " + HeaderFooter.numPages()+"页" );
		 
         workbook.setSheetName(0,startTime+"至"+endTime+"办公耗材验收表");//设置Sheet的名称
       //设置列宽
         sheet.setColumnWidth(0, 9000); //产品名称
         sheet.setColumnWidth(1, 6000); //规格、型号
         sheet.setColumnWidth(2, 3000); //数量
         sheet.setColumnWidth(3, 3000); //单位
         sheet.setColumnWidth(4, 3000); //单价
         sheet.setColumnWidth(5, 4000); //总金额（元）
         HSSFCellStyle cellstype1 = workbook.createCellStyle();
         //居中对齐
         cellstype1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         cellstype1.setWrapText(true);
         //设置字体
         HSSFFont font = workbook.createFont();
         font.setFontHeightInPoints((short) 20); // 字体高度
         font.setFontName(" 黑体 "); // 字体
         font.setBoldweight(Font.BOLDWEIGHT_BOLD);
         cellstype1.setFont(font);

         HSSFCellStyle cellstype_header = workbook.createCellStyle();
         //居中对齐
         cellstype_header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         cellstype_header.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         cellstype_header.setWrapText(true);
         cellstype_header.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
         cellstype_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
         cellstype_header.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
         cellstype_header.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

         //设置字体
         HSSFFont font_header = workbook.createFont();
         font_header.setFontHeightInPoints((short) 12); // 字体高度
         font_header.setFontName(" 宋体 "); // 字体
         cellstype_header.setFont(font_header);
         
         
         
         HSSFRow row0 = sheet.createRow(0); // 创建第一行
         row0.setHeight((short)560);
         HSSFCell cell10 = row0.createCell(0);
         cell10.setCellValue("办公耗材验收表");
         cell10.setCellStyle(cellstype1);
         for (int j = 1; j < 6; j++) {
             HSSFCell cell = row0.createCell(j);
             cell.setCellStyle(cellstype1);
         }
         sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 5));
         if(list!=null && list.size()>0){
        	 Map<String, Object> map=list.get(0);
        	 Set<String> set = map.keySet();
         	if(set != null && !set.isEmpty()) {
         	for(String key : set) {
         	if(map.get(key) == null) { map.put(key, ""); }
         	}
         	}
         	
             HSSFRow row1 = sheet.createRow(1); // 创建第2行
             row1.setHeightInPoints(20);
             HSSFCell cell0 = row1.createCell(0);
             cell0.setCellValue("收货单位");
             cell0.setCellStyle(cellstype_header);
             HSSFCell cell01 = row1.createCell(1);
             cell01.setCellValue(map.get("SHDW").toString() );
             cell01.setCellStyle(cellstype_header);
             for (int j = 2; j < 6; j++) {
                 HSSFCell cell = row1.createCell(j);
                 cell.setCellStyle(cellstype_header);
             }
             sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 1, (short) 5));//起始行号，终止行号， 起始列号，终止列号
             

             HSSFRow row2 = sheet.createRow(2); // 行
             row2.setHeightInPoints(20);
             HSSFCell cell20 = row2.createCell(0);
             cell20.setCellValue("地址");
             cell20.setCellStyle(cellstype_header);
             HSSFCell cell21 = row2.createCell(1);
             cell21.setCellValue(map.get("DZ").toString() );
             cell21.setCellStyle(cellstype_header);
             for (int j = 2; j < 6; j++) {
                 HSSFCell cell = row2.createCell(j);
                 cell.setCellStyle(cellstype_header);
             }
             sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 1, (short) 5));//起始行号，终止行号， 起始列号，终止列号

             HSSFRow row3 = sheet.createRow(3); // 行
             row3.setHeightInPoints(20);
             HSSFCell cell30 = row3.createCell(0);
             cell30.setCellValue("供应商");
             cell30.setCellStyle(cellstype_header);
             HSSFCell cell31 = row3.createCell(1);
             cell31.setCellValue(map.get("GYSMC").toString() );
             cell31.setCellStyle(cellstype_header);
             for (int j = 2; j < 6; j++) {
                 HSSFCell cell = row3.createCell(j);
                 cell.setCellStyle(cellstype_header);
             }
             sheet.addMergedRegion(new CellRangeAddress(3, (short) 3, 1, (short) 5));//起始行号，终止行号， 起始列号，终止列号
             
             HSSFRow row4 = sheet.createRow(4); // 行
             row4.setHeightInPoints(20);
             HSSFCell cell40 = row4.createCell(0);
             cell40.setCellValue("采购人");
             cell40.setCellStyle(cellstype_header);
             HSSFCell cell41 = row4.createCell(1);
             cell41.setCellValue("联系人部门" );
             cell41.setCellStyle(cellstype_header);
             for (int j = 2; j < 5; j++) {
                 HSSFCell cell = row4.createCell(j);
                 cell.setCellStyle(cellstype_header);
             }
             sheet.addMergedRegion(new CellRangeAddress(4, (short) 4, 1, (short) 4));//起始行号，终止行号， 起始列号，终止列号

             HSSFCell cell45 = row4.createCell(5);
             cell45.setCellValue("电话" );
             cell45.setCellStyle(cellstype_header);
             

             HSSFRow row5 = sheet.createRow(5); // 行
             row5.setHeightInPoints(20);
             HSSFCell cell50 = row5.createCell(0);
             cell50.setCellValue(map.get("LXRMC").toString());
             cell50.setCellStyle(cellstype_header);
             HSSFCell cell51 = row5.createCell(1);
             cell51.setCellValue(map.get("LXRBM").toString());
             cell51.setCellStyle(cellstype_header);
             for (int j = 2; j < 5; j++) {
                 HSSFCell cell = row5.createCell(j);
                 cell.setCellStyle(cellstype_header);
             }
             sheet.addMergedRegion(new CellRangeAddress(5, (short) 5, 1, (short) 4));//起始行号，终止行号， 起始列号，终止列号
             HSSFCell cell55 = row5.createCell(5);
             cell55.setCellValue(map.get("DH").toString());
             cell55.setCellStyle(cellstype_header);
             

             HSSFRow row6 = sheet.createRow(6); // 行
             row6.setHeightInPoints(20);
             HSSFCell cell60 = row6.createCell(0);
             cell60.setCellValue("产品名称");
             cell60.setCellStyle(cellstype_header);
             HSSFCell cell61 = row6.createCell(1);
             cell61.setCellValue("规格型号");
             cell61.setCellStyle(cellstype_header);
             HSSFCell cell62 = row6.createCell(2);
             cell62.setCellValue("数量");
             cell62.setCellStyle(cellstype_header);
             HSSFCell cell63 = row6.createCell(3);
             cell63.setCellValue("单位");
             cell63.setCellStyle(cellstype_header);
             HSSFCell cell64 = row6.createCell(4);
             cell64.setCellValue("单价（元）");
             cell64.setCellStyle(cellstype_header);
             HSSFCell cell65 = row6.createCell(5);
             cell65.setCellValue("金额（元）");
             cell65.setCellStyle(cellstype_header);
             double money = 0;
             double zj = 0;
             for (int i = 0; i < list.size(); i++) {
            	 Map<String, Object> goods=list.get(i);
            	 Set<String> sets = goods.keySet();
             	if(sets != null && !sets.isEmpty()) {
             	for(String key : sets) {
             	if(goods.get(key) == null) { goods.put(key, ""); }
             	}
             	}
            	 
            	 
             	
            	 HSSFRow row7 = sheet.createRow(sheet.getLastRowNum()+1); // 行
            	 row7.setHeightInPoints(20);
                 HSSFCell cell70 = row7.createCell(0);
                 cell70.setCellValue(goods.get("WPMC").toString());
                 cell70.setCellStyle(cellstype_header);
                 HSSFCell cell71 = row7.createCell(1);
                 cell71.setCellValue(goods.get("GGJXH").toString());
                 cell71.setCellStyle(cellstype_header);
                 HSSFCell cell72 = row7.createCell(2);
                 cell72.setCellValue(goods.get("CSSL").toString());
                 cell72.setCellStyle(cellstype_header);
                 HSSFCell cell73 = row7.createCell(3);
                 cell73.setCellValue(goods.get("DW").toString());
                 cell73.setCellStyle(cellstype_header);
                 double d1= Double.parseDouble(String.valueOf(goods.get("JE")));
           	  	BigDecimal md1 = new BigDecimal(d1);
           	  	md1.setScale(2,BigDecimal.ROUND_HALF_UP);//设置保留2位小数,四舍五入
           	   
                 HSSFCell cell74 = row7.createCell(4);
                 cell74.setCellValue(md1.doubleValue());
                 cell74.setCellStyle(cellstype_header);
                 HSSFCell cell75 = row7.createCell(5);
                
                 BigDecimal md2 = new BigDecimal(Float.valueOf(String.valueOf(goods.get("CSSL"))));
                 BigDecimal m=md1.multiply(md2);//单价乘数量
               	 m.setScale(2,BigDecimal.ROUND_HALF_UP);
                 
                 money=money+m.doubleValue();
                 zj=zj+m.doubleValue();
                 
                 cell75.setCellValue(m.doubleValue());
                 cell75.setCellStyle(cellstype_header);
                 
                 
                 int size=i+1;
            	   if(size%7==0  ||size==list.size() ){
            		   HSSFRow row8 = sheet.createRow(sheet.getLastRowNum()+1); // 行
                       row8.setHeightInPoints(20);
                       HSSFCell cell80 = row8.createCell(0);
                       cell80.setCellValue("合计");
                       cell80.setCellStyle(cellstype_header);
                       for (int j = 1; j < 5; j++) {
                      	 HSSFCell cell81 = row8.createCell(j);
                      	 cell81.setCellStyle(cellstype_header);
                       }
                       HSSFCell cell85 = row8.createCell(5);
                       
                       cell85.setCellValue(money);
                       cell85.setCellStyle(cellstype_header);
                       money=0;
                       
                       
                       if(size==list.size()){
                    	   HSSFRow rowZhj8 = sheet.createRow(sheet.getLastRowNum()+1); // 行
                    	   rowZhj8.setHeightInPoints(20);
                           HSSFCell cellZhj80 = rowZhj8.createCell(0);
                           cellZhj80.setCellValue("总计");
                           cellZhj80.setCellStyle(cellstype_header);
                           for (int j = 1; j < 5; j++) {
                          	 HSSFCell cellzj81 = rowZhj8.createCell(j);
                          	cellzj81.setCellStyle(cellstype_header);
                           }
                           HSSFCell cellzj85 = rowZhj8.createCell(5);
                           
                           cellzj85.setCellValue(zj);
                           cellzj85.setCellStyle(cellstype_header);
                       }
                       
                       
                       
                       HSSFRow row9 = sheet.createRow(sheet.getLastRowNum()+1); // 行
                       row9.setHeightInPoints(20);
                       HSSFCell cell90 = row9.createCell(0);
                       cell90.setCellValue("使用部门");
                       cell90.setCellStyle(cellstype_header);
                       HSSFCell cell92 = row9.createCell(1);
                       cell92.setCellValue("院内各部门");
                       cell92.setCellStyle(cellstype_header);
                       for (int j = 2; j < 6; j++) {
                      	 HSSFCell cell91 = row9.createCell(j);
                      	 cell91.setCellStyle(cellstype_header);
          			 }

                       sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(), 1, (short) 5));//起始行号，终止行号， 起始列号，终止列号
                       
                       HSSFRow row10 = sheet.createRow(sheet.getLastRowNum()+1); // 行
                       row10.setHeightInPoints(20);
                       HSSFCell cell100 = row10.createCell(0);
                       cell100.setCellValue("验收人");
                       cell100.setCellStyle(cellstype_header);
                       for (int j = 1; j < 6; j++) {
                      	 HSSFCell cell101 = row10.createCell(j);
                      	 cell101.setCellStyle(cellstype_header);
          			 }
                       sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(), 1, (short) 5));//起始行号，终止行号， 起始列号，终止列号
                       
                       HSSFRow row11 = sheet.createRow(sheet.getLastRowNum()+1); // 行
                       row11.setHeightInPoints(20);
                       HSSFCell cell101 = row11.createCell(0);
                       cell101.setCellValue("纪检人");
                       cell101.setCellStyle(cellstype_header);
                       sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(), 1, (short) 2));//起始行号，终止行号， 起始列号，终止列号

                       HSSFCell cell1011 = row11.createCell(1);
                       HSSFCell cell102 = row11.createCell(2);
                       cell1011.setCellStyle(cellstype_header);
                       cell102.setCellStyle(cellstype_header);
                       
                       HSSFCell cell103 = row11.createCell(3);
                       cell103.setCellValue("日期");
                       cell103.setCellStyle(cellstype_header);
                       HSSFCell cell104 = row11.createCell(4);
                       HSSFCell cell105 = row11.createCell(5);
                       cell104.setCellStyle(cellstype_header);
                       cell105.setCellStyle(cellstype_header);
                       sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(),4, (short) 5));//起始行号，终止行号， 起始列号，终止列号

                       
            	   }
			}
             
             
         }
         
         
		return workbook;
	}
	
	
	
	
	
	
	
	public HSSFWorkbook outRk(String gysId,String startTime,String endTime,String orgId) throws ParseException{
		HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄
//		supplierDao.getGoodsByGysIdAndTime(gysId, startTime, entTime, typeId)

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        Date time=sdf.parse(endTime);
	        Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(time);  
	        rightNow.add(Calendar.MONTH, 1); 
	        
	        

            //获取样式
              HSSFCellStyle cellstype1 = workbook.createCellStyle();
              //居中对齐
              cellstype1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              cellstype1.setWrapText(true);
              //设置字体
              HSSFFont font = workbook.createFont();
              font.setFontHeightInPoints((short) 20); // 字体高度
              font.setFontName(" 黑体 "); // 字体
              font.setBoldweight(Font.BOLDWEIGHT_BOLD);
              cellstype1.setFont(font);
              //获取样式
              HSSFCellStyle cellstype = workbook.createCellStyle();
              //居中对齐
              cellstype.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              cellstype.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
              cellstype.setWrapText(true);
              cellstype.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
              cellstype.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
              cellstype.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
              cellstype.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
              //设置字体
              HSSFFont font1 = workbook.createFont();
              font1.setFontHeightInPoints((short) 11); // 字体高度
              font1.setFontName(" 宋体 "); // 字体
              cellstype.setFont(font1);
              


              HSSFCellStyle cellstype_header = workbook.createCellStyle();
              //居中对齐
              cellstype_header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
              cellstype_header.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
              cellstype_header.setWrapText(true);
              cellstype_header.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
              cellstype_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
              cellstype_header.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
              cellstype_header.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
              
              

              HSSFCellStyle cellstype_header1 = workbook.createCellStyle();
              //居左对齐
              cellstype_header1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
              cellstype_header1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
              cellstype_header1.setWrapText(true);
              
              
              //设置字体
              HSSFFont font_header = workbook.createFont();
              font_header.setFontHeightInPoints((short) 12); // 字体高度
              font_header.setFontName(" 宋体 "); // 字体
//              font_header.setBoldweight(Font.BOLDWEIGHT_BOLD);
              cellstype_header.setFont(font_header);
              cellstype_header1.setFont(font_header);
              
              
        //查询入库单的类型；
        List<Map<String, String>> list=goodsDao.getGoodsTypeBygysId(gysId,startTime,sdf.format(rightNow.getTime()),orgId);
        if(!list.isEmpty()){
        	for (int i = 0; i < list.size(); i++) {
            	Map<String, String> map=list.get(i);
       		 	HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet

                workbook.setRepeatingRowsAndColumns(i, 0, 9, 0, 1);//设置重复打印表头
       		 	HSSFPrintSetup printSetup = sheet.getPrintSetup();
       		 	printSetup.setLandscape(true);//设置横向打印
	             printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);        //A4纸张打印
	             printSetup.setScale((short) 75);//缩放比例
	             sheet.setMargin(HSSFSheet.BottomMargin,( double ) 1.5 );// 页边距（下）
	             sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.5 );// 页边距（左）
	             sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );// 页边距（右）
	             sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );// 页边距（上）
	             Footer footer = sheet.getFooter();
	             footer.setCenter( "第" + HeaderFooter.page() + "页，共 " + HeaderFooter.numPages()+"页" );
       		 	
       		 	
                workbook.setSheetName(i, map.get("LX_NAME"));//设置Sheet的名称
                //设置列宽
                sheet.setColumnWidth(0, 4000); //日期
                sheet.setColumnWidth(1, 8000); //品名
                sheet.setColumnWidth(2, 6000); //规格、型号
                sheet.setColumnWidth(3, 8000); //供应商
                sheet.setColumnWidth(4, 3000); //数量
                sheet.setColumnWidth(5, 3000); //单位
                sheet.setColumnWidth(6, 3500); //单价
                sheet.setColumnWidth(7, 3500); //金额
                sheet.setColumnWidth(8, 6000); //备注
                HSSFRow row0 = sheet.createRow(0); // 创建第一行
                row0.setHeight((short)560);
                HSSFCell cell10 = row0.createCell(0);
                cell10.setCellValue(map.get("LX_NAME")+"入库单");
                cell10.setCellStyle(cellstype1);
                for (int j = 1; j < 9; j++) {
                    HSSFCell cell = row0.createCell(j);
                    cell.setCellStyle(cellstype1);
                }
                sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 8));
                
                
                HSSFRow row1 = sheet.createRow(1); // 创建第2行
                row1.setHeightInPoints(40);
                HSSFCell cell0 = row1.createCell(0);
                cell0.setCellValue("日期");
                cell0.setCellStyle(cellstype_header);
                HSSFCell cell1 = row1.createCell(1);
                cell1.setCellValue("品名");
                cell1.setCellStyle(cellstype_header);
                HSSFCell cell2 = row1.createCell(2);
                cell2.setCellValue("规格、型号");
                cell2.setCellStyle(cellstype_header);
                HSSFCell cell3 = row1.createCell(3);
                cell3.setCellValue("供应商");
                cell3.setCellStyle(cellstype_header);
                HSSFCell cell4 = row1.createCell(4);
                cell4.setCellValue("数量");
                cell4.setCellStyle(cellstype_header);
                HSSFCell cell5 = row1.createCell(5);
                cell5.setCellValue("单位");
                cell5.setCellStyle(cellstype_header);
                HSSFCell cell6 = row1.createCell(6);
                cell6.setCellValue("单价（元）");
                cell6.setCellStyle(cellstype_header);
                HSSFCell cell7 = row1.createCell(7);
                cell7.setCellValue("金额（元）");
                cell7.setCellStyle(cellstype_header);
//                HSSFCell cell8 = row1.createCell(8);
//                cell8.setCellValue("发票号");
//                cell8.setCellStyle(cellstype_header);
                HSSFCell cell9 = row1.createCell(8);
                cell9.setCellValue("备注");
                cell9.setCellStyle(cellstype_header);
                
                //数据
                List<Goods> glist=supplierDao.getGoodsByGysIdAndTime(gysId, startTime, sdf.format(rightNow.getTime()), map.get("ID"),orgId);
                double je=0;//总金额总数
                double zj=0;//总计
                SimpleDateFormat sdfrk=new SimpleDateFormat("yyyy-MM-dd");
                for (int j = 0; j < glist.size(); j++) {
                	Goods bean=glist.get(j);
    	            HSSFRow row2 = sheet.createRow(sheet.getLastRowNum()+1); 
    	            row2.setHeightInPoints(40);
    	            HSSFCell cell20 = row2.createCell(0);
    	            cell20.setCellValue(sdfrk.format(bean.getRk_time()));
    	            cell20.setCellStyle(cellstype_header);
    	            HSSFCell cell21 = row2.createCell(1);
    	            cell21.setCellValue(bean.getWpmc());
    	            cell21.setCellStyle(cellstype_header);
    	            HSSFCell cell22 = row2.createCell(2);
    	            cell22.setCellValue(bean.getGgjxh());
    	            cell22.setCellStyle(cellstype_header);
    	            HSSFCell cell23 = row2.createCell(3);
    	            cell23.setCellValue(bean.getGysmc());
    	            cell23.setCellStyle(cellstype_header);
    	            HSSFCell cell24 = row2.createCell(4);
    	            cell24.setCellValue(bean.getCssl());
    	            cell24.setCellStyle(cellstype_header);
    	            HSSFCell cell25 = row2.createCell(5);
    	            cell25.setCellValue(bean.getDw());
    	            cell25.setCellStyle(cellstype_header);

    	   	          BigDecimal md1 = new BigDecimal(bean.getJe());
    	   	          md1.setScale(2,BigDecimal.ROUND_HALF_UP);
    	   	          je=je+md1.doubleValue()*bean.getCssl();
    	   	          zj=zj+md1.doubleValue()*bean.getCssl();
    	   	          
    	            HSSFCell cell26 = row2.createCell(6);
    	            cell26.setCellValue(md1.doubleValue());
    	            cell26.setCellStyle(cellstype_header);
    	            HSSFCell cell27 = row2.createCell(7);
    	            cell27.setCellValue(md1.doubleValue()*bean.getCssl());
    	            cell27.setCellStyle(cellstype_header);
//    	            HSSFCell cell28 = row2.createCell(8);
//    	            cell28.setCellValue(bean.getWarehousing_num());//查询赋值 是发票号
//    	            cell28.setCellStyle(cellstype_header);
    	            HSSFCell cell29 = row2.createCell(8);
    	            cell29.setCellValue(bean.getBz());
    	            cell29.setCellStyle(cellstype_header);
    	            
    	            int size=j+1;
              	    if(size%11==0 || size==glist.size() ){
              	    	 //合计
                        HSSFRow row3 = sheet.createRow(sheet.getLastRowNum()+1); 
                        row3.setHeightInPoints(40);
                        HSSFCell cell30 = row3.createCell(0);
                        cell30.setCellValue("合计：");
                        cell30.setCellStyle(cellstype_header);
                        
                        HSSFCell cell36 = row3.createCell(6);
//                        cell36.setCellValue(dj);
                        cell36.setCellStyle(cellstype_header);

                        HSSFCell cell37 = row3.createCell(7);
                        cell37.setCellValue(je);
                        cell37.setCellStyle(cellstype_header);
                        
                        HSSFCell cell39 = row3.createCell(8);
                        cell39.setCellStyle(cellstype_header);
                        for (int y = 1; y < 6; y++) {
                            HSSFCell cell31 = row3.createCell(y);
                            cell31.setCellStyle(cellstype_header);
            			}
                        je=0;
                        
                        if(size==glist.size()){
                        	 HSSFRow rowzj3 = sheet.createRow(sheet.getLastRowNum()+1); 
                        	 rowzj3.setHeightInPoints(40);
                             HSSFCell cellzj30 = rowzj3.createCell(0);
                             cellzj30.setCellValue("总计：");
                             cellzj30.setCellStyle(cellstype_header);
                             
                             HSSFCell cellzj36 = rowzj3.createCell(6);
//                             cell36.setCellValue(dj);
                             cellzj36.setCellStyle(cellstype_header);

                             HSSFCell cellzj37 = rowzj3.createCell(7);
                             cellzj37.setCellValue(zj);
                             cellzj37.setCellStyle(cellstype_header);
                             HSSFCell cellzj38 = rowzj3.createCell(8);
                             cellzj38.setCellStyle(cellstype_header);
                             for (int y = 1; y < 6; y++) {
                                 HSSFCell cellzj31 = rowzj3.createCell(y);
                                 cellzj31.setCellStyle(cellstype_header);
                 			}
                        }
                        
                        
                        HSSFRow row4 = sheet.createRow(sheet.getLastRowNum()+1); 
                        row4.setHeightInPoints(40);
                        HSSFCell cell40 = row4.createCell(0);
                        cell40.setCellValue("部门负责人：");
                        cell40.setCellStyle(cellstype_header);
                        HSSFCell cell41 = row4.createCell(1);
                        cell41.setCellStyle(cellstype_header);
                        HSSFCell cell42 = row4.createCell(2);
                        cell42.setCellStyle(cellstype_header);
                        HSSFCell cell43 = row4.createCell(3);
                        cell43.setCellValue("采购：");
                        cell43.setCellStyle(cellstype_header);
                        HSSFCell cell44 = row4.createCell(4);
                        cell44.setCellStyle(cellstype_header);
                        HSSFCell cell45 = row4.createCell(5);
                        cell45.setCellStyle(cellstype_header);
                        HSSFCell cell46 = row4.createCell(6);
                        cell46.setCellValue("库管：");
                        cell46.setCellStyle(cellstype_header);
                        HSSFCell cell47 = row4.createCell(7);
                        cell47.setCellStyle(cellstype_header);
                        HSSFCell cell49 = row4.createCell(8);
                        cell49.setCellStyle(cellstype_header);
                        sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), (short) sheet.getLastRowNum(), 7, (short) 8));
                        sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), (short) sheet.getLastRowNum(), 4, (short) 5));
              	    }
    			}
                
            	
    		}
        }else{
        	 HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
             HSSFRow row0 = sheet.createRow(0); // 创建第一行
             row0.setHeightInPoints(40);
             HSSFCell cell10 = row0.createCell(0);
             cell10.setCellValue("没有查找到入库数据");
             cell10.setCellStyle(cellstype1);
             sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 9));
        }
        
        
        
		
		return workbook;
	}
}
