package com.scts.discipline.web;

import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.StringUtil;
import com.scts.discipline.bean.DisciplinePlan;
import com.scts.discipline.service.DisciplinePlanService;

@Controller
@RequestMapping("disciplinePlanAction")
public class DisciplinePlanAction extends SpringSupportAction<DisciplinePlan, DisciplinePlanService> {

	@Autowired
	private DisciplinePlanService disciplinePlanService;
	
	/**
	 * 导入回访计划
	 * author pingZhou
	 * @param request
	 * @param year
	 * @param month
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "saveImport")
	@ResponseBody
	public HashMap<String,Object> saveImport(HttpServletRequest request ,String year, String month,String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
	//	java.text.SimpleDateFormat formatter = new SimpleDateFormat("\"yyyy-MM\"");
	String year1 =	year.replace( "\"", " ");
	String month1 = month.replace("\"", " ");
		System.out.println(year1);
		try {
			//Date date =  formatter.parse(etime);
			String result = disciplinePlanService.saveBankData(year1,month1,files,getCurrentUser());
			if(StringUtil.isNotEmpty(result)) {
				data.put("result", result);
				data.put("success", false);
			}else {
				data.put("success", true);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
			data.put("success", false);
		}
    	return  data;
	}
	
	/**
	 * 保存回访结果
	 * author pingZhou
	 * @param request
	 * @param ids
	 * @param inspector_grade
	 * @param other_suggest
	 * @return
	 */
	@RequestMapping(value = "saveResult")
	@ResponseBody
	public HashMap<String,Object> saveResult(HttpServletRequest request ,String ids, String inspector_grade,String self_discipline,String other_suggest) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {

			 disciplinePlanService.saveResult(ids,inspector_grade,self_discipline,other_suggest);
			
			data.put("success", true);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
		}
    	return  data;
	}
	
	/**
	 * 标志已回访
	 * author pingZhou
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "callFlag")
	@ResponseBody
	public HashMap<String,Object> callFlag(HttpServletRequest request ,String id) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {

			
			
			 disciplinePlanService.callFlag(id);
			
			data.put("success", true);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
		}
    	return  data;
	}
	
	/**
	 * 删除计划
	 * author pingZhou
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String,Object> del(HttpServletRequest request ,String ids) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {

			 disciplinePlanService.del(ids);
			
			 data.put("success", true);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
			data.put("msg", "删除失败！");
		}
    	return  data;
	}
	
	/**
	 * 工作服务统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "selectTj")
	@ResponseBody
	public HashMap<String,Object> selectTj(HttpServletRequest request ,HttpServletResponse response) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			List<Map<String, Object>> list=disciplinePlanService.gzfwTj(startTime,endTime);
			
			 data.put("success", true);
			 data.put("data", list);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
			data.put("msg", "查询失败！");
		}
    	return  data;
	}
	/**
	 * 工作服务统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "selectOldTj")
	@ResponseBody
	public HashMap<String,Object> selectOldTj(HttpServletRequest request ,HttpServletResponse response) {
		HashMap<String,Object> data=new HashMap<String,Object>();

		try {
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			List<Map<String, Object>> list=disciplinePlanService.oldGzfwTj(startTime,endTime);
			
			 data.put("success", true);
			 data.put("data", list);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("success", false);
			data.put("msg", "查询失败！");
		}
    	return  data;
	}

  	@RequestMapping(value = "exportCheckedCount")
  	@ResponseBody
	public void exportCheckedCount(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
        HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄
        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
        workbook.setSheetName(0,"工作服务统计");//设置Sheet的名称
        
      //设置列宽
        sheet.setColumnWidth(0, 6000); //部门
        sheet.setColumnWidth(1, 3000); //非常满意-个数
        sheet.setColumnWidth(2, 3000); //非常满意-百分比
        sheet.setColumnWidth(3, 3000); //满意-个数
        sheet.setColumnWidth(4, 3000); //满意-百分比
        sheet.setColumnWidth(5, 3000); //一般-个数
        sheet.setColumnWidth(6, 3000); //一般-百分比
        sheet.setColumnWidth(7, 3000); //回访量
        //获取样式
        HSSFCellStyle cellstype1 = workbook.createCellStyle();
        //居中对齐
        cellstype1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellstype1.setWrapText(true);
//        cellstype1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
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
        font_header.setFontHeightInPoints((short) 15); // 字体高度
        font_header.setFontName(" 宋体 "); // 字体
        cellstype_header.setFont(font_header);
        
        
        
        HSSFRow row0 = sheet.createRow(0); // 创建第一行
        row0.setHeight((short)560);
        HSSFCell cell10 = row0.createCell(0);
        cell10.setCellValue(startTime+"至"+endTime+"工作服务统计表");
        cell10.setCellStyle(cellstype1);
        for (int j = 1; j < 8; j++) {
            HSSFCell cell = row0.createCell(j);
            cell.setCellStyle(cellstype1);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 7));
       

        HSSFRow row2 = sheet.createRow(1); // 创建第二行
        row2.setHeightInPoints(30);
        HSSFCell cell20 = row2.createCell(0);//列
        cell20.setCellValue("统计时间："+startTime+"至"+endTime);
        cell20.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 0, (short) 7));//起始行号，终止行号， 起始列号，终止列号
        for (int i = 1; i < 8; i++) {
        	 HSSFCell cell21 = row2.createCell(i);//列
        	 cell21.setCellStyle(cellstype_header);
		}
        HSSFRow row3 = sheet.createRow(2); // 行
        row3.setHeightInPoints(30);
        HSSFCell cell30 = row3.createCell(0);//列
        cell30.setCellValue("部门");
        cell30.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 0, (short) 0));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell31 = row3.createCell(1);//列
        cell31.setCellValue("非常满意");
        cell31.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 1, (short) 2));//起始行号，终止行号， 起始列号，终止列号
        
        HSSFCell cell32 = row3.createCell(2);//列
        cell32.setCellStyle(cellstype_header);
        HSSFCell cell33 = row3.createCell(3);//列
        cell33.setCellValue("满意");
        cell33.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 3, (short) 4));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell34 = row3.createCell(4);//列
        cell34.setCellStyle(cellstype_header);
        HSSFCell cell35 = row3.createCell(5);//列
        cell35.setCellValue("一般");
        cell35.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 5, (short) 6));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell36 = row3.createCell(6);//列
        cell36.setCellStyle(cellstype_header);
        HSSFCell cell37 = row3.createCell(7);//列
        cell37.setCellValue("回访量");
        cell37.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 7, (short) 7));//起始行号，终止行号， 起始列号，终止列号
        

        HSSFRow row4 = sheet.createRow(3); //行
        row4.setHeightInPoints(30);
        HSSFCell cell40 = row4.createCell(0);//列
        cell40.setCellStyle(cellstype_header);
        HSSFCell cell41 = row4.createCell(1);//列
        cell41.setCellValue("个数");
        cell41.setCellStyle(cellstype_header);
        HSSFCell cell42 = row4.createCell(2);//列
        cell42.setCellValue("百分百");
        cell42.setCellStyle(cellstype_header);
        HSSFCell cell43 = row4.createCell(3);//列
        cell43.setCellValue("个数");
        cell43.setCellStyle(cellstype_header);
        HSSFCell cell44 = row4.createCell(4);//列
        cell44.setCellValue("百分比");
        cell44.setCellStyle(cellstype_header);
        HSSFCell cell45 = row4.createCell(5);//列
        cell45.setCellValue("个数");
        cell45.setCellStyle(cellstype_header);
        HSSFCell cell46 = row4.createCell(6);//列
        cell46.setCellValue("百分比");
        cell46.setCellStyle(cellstype_header);
        HSSFCell cell47 = row4.createCell(7);//列
        cell47.setCellStyle(cellstype_header);
        int fcmysl=0;
        int mysl=0;
        int ybsl=0;
        int hfsl=0;
		List<Map<String, Object>> list=disciplinePlanService.gzfwTj(startTime,endTime);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Set<String> set = map.keySet();
        	if(set != null && !set.isEmpty()) {
        	for(String key : set) {
        	if(map.get(key) == null) { map.put(key, ""); }
        	}
        	}
        	
        	
	        int fcmy=Integer.parseInt( map.get("FCMY").toString());fcmysl=fcmysl+fcmy;
	        int my= Integer.parseInt(map.get("MY").toString());mysl=mysl+my;
	        int yb= Integer.parseInt(map.get("YB").toString());ybsl=ybsl+yb;
	        hfsl=Integer.parseInt(map.get("HFSL").toString())+hfsl;
			int hj=fcmy+my+yb;
	        HSSFRow row5 = sheet.createRow(4+i); //行
	        row5.setHeightInPoints(30);
	        HSSFCell cell50 = row5.createCell(0);//列
	        cell50.setCellValue(String.valueOf(map.get("SJ")));
	        cell50.setCellStyle(cellstype_header);
	        HSSFCell cell51 = row5.createCell(1);//列
	        cell51.setCellValue(String.valueOf(map.get("FCMY")));
	        cell51.setCellStyle(cellstype_header);
	        HSSFCell cell52 = row5.createCell(2);//列
	        //res.data[i].MY)/hj*100
	        NumberFormat numberFormat = NumberFormat.getInstance();  
	        numberFormat.setMaximumFractionDigits(2);  //保留小数位数
	        String result = numberFormat.format((float)fcmy/(float)hj*100);
	        cell52.setCellValue(result );
	        cell52.setCellStyle(cellstype_header);
	        HSSFCell cell53 = row5.createCell(3);//列
	        cell53.setCellValue(String.valueOf(map.get("MY")));
	        cell53.setCellStyle(cellstype_header);
	        HSSFCell cell54 = row5.createCell(4);//列
	        cell54.setCellValue(numberFormat.format((float)my/(float)hj*100));
	        cell54.setCellStyle(cellstype_header);
	        HSSFCell cell55 = row5.createCell(5);//列
	        cell55.setCellValue(String.valueOf(map.get("YB")));
	        cell55.setCellStyle(cellstype_header);
	        HSSFCell cell56 = row5.createCell(6);//列
	        cell56.setCellValue(numberFormat.format((float)yb/(float)hj*100));
	        cell56.setCellStyle(cellstype_header);
	        HSSFCell cell57 = row5.createCell(7);//列
	        cell57.setCellValue(String.valueOf(map.get("HFSL")));
	        cell57.setCellStyle(cellstype_header);
		}
        

        HSSFRow row6 = sheet.createRow(4+list.size()); //行
        row6.setHeightInPoints(30);
        HSSFCell cell60 = row6.createCell(0);//列
        cell60.setCellValue("合计：");
        cell60.setCellStyle(cellstype_header);
        HSSFCell cell61 = row6.createCell(1);//列
        cell61.setCellValue(fcmysl);
        cell61.setCellStyle(cellstype_header);
        HSSFCell cell62 = row6.createCell(2);//列
        cell62.setCellStyle(cellstype_header);
        HSSFCell cell63 = row6.createCell(3);//列
        cell63.setCellValue(mysl);
        cell63.setCellStyle(cellstype_header);
        HSSFCell cell64 = row6.createCell(4);//列
        cell64.setCellStyle(cellstype_header);
        HSSFCell cell65 = row6.createCell(5);//列
        cell65.setCellValue(ybsl);
        cell65.setCellStyle(cellstype_header);
        HSSFCell cell66 = row6.createCell(6);//列
        cell66.setCellStyle(cellstype_header);
        HSSFCell cell67 = row6.createCell(7);//列
        cell67.setCellValue(hfsl);
        cell67.setCellStyle(cellstype_header);
        
        

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(("工作统计分析表"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
        OutputStream outp = response.getOutputStream();
        workbook.write(response.getOutputStream());
        outp.flush();
        outp.close();
        
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportCheckedCountOld")
	public void exportCheckedCountOld(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
        HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄
        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
        workbook.setSheetName(0,"工作服务统计");//设置Sheet的名称
        
      //设置列宽
        sheet.setColumnWidth(0, 6000); //月份
        sheet.setColumnWidth(1, 3000); //合计
        sheet.setColumnWidth(2, 3000); //非常满意-个数
        sheet.setColumnWidth(3, 3000); //非常满意-百分比
        sheet.setColumnWidth(4, 3000); //满意-个数
        sheet.setColumnWidth(5, 3000); //满意-百分比
        sheet.setColumnWidth(6, 3000); //一般-个数
        sheet.setColumnWidth(7, 3000); //一般-百分比
        //获取样式
        HSSFCellStyle cellstype1 = workbook.createCellStyle();
        //居中对齐
        cellstype1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellstype1.setWrapText(true);
//        cellstype1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
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
        font_header.setFontHeightInPoints((short) 15); // 字体高度
        font_header.setFontName(" 宋体 "); // 字体
        cellstype_header.setFont(font_header);
        
        
        
        HSSFRow row0 = sheet.createRow(0); // 创建第一行
        row0.setHeight((short)560);
        HSSFCell cell10 = row0.createCell(0);
        cell10.setCellValue("工作服务统计表");
        cell10.setCellStyle(cellstype1);
        for (int j = 1; j < 8; j++) {
            HSSFCell cell = row0.createCell(j);
            cell.setCellStyle(cellstype1);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 7));
       

        HSSFRow row2 = sheet.createRow(1); // 创建第二行
        row2.setHeightInPoints(30);
        HSSFCell cell20 = row2.createCell(0);//列
        cell20.setCellValue("统计时间："+startTime+"至"+endTime);
        cell20.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 0, (short) 7));//起始行号，终止行号， 起始列号，终止列号
        for (int i = 1; i < 8; i++) {
        	 HSSFCell cell21 = row2.createCell(i);//列
        	 cell21.setCellStyle(cellstype_header);
		}
        HSSFRow row3 = sheet.createRow(2); // 行
        row3.setHeightInPoints(30);
        HSSFCell cell30 = row3.createCell(0);//列
        cell30.setCellValue("月份");
        cell30.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 0, (short) 0));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell31 = row3.createCell(1);//列
        cell31.setCellValue("合计");
        cell31.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 1, (short) 1));//起始行号，终止行号， 起始列号，终止列号
        
        
        HSSFCell cell32 = row3.createCell(2);//列
        cell32.setCellValue("非常满意");
        cell32.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 2, (short) 3));//起始行号，终止行号， 起始列号，终止列号
        
        HSSFCell cell33 = row3.createCell(3);//列
        cell33.setCellStyle(cellstype_header);
        HSSFCell cell34 = row3.createCell(4);//列
        cell34.setCellValue("满意");
        cell34.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 4, (short) 5));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell35 = row3.createCell(5);//列
        cell35.setCellStyle(cellstype_header);
        HSSFCell cell36 = row3.createCell(6);//列
        cell36.setCellValue("一般");
        cell36.setCellStyle(cellstype_header);
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 6, (short) 7));//起始行号，终止行号， 起始列号，终止列号
        HSSFCell cell37 = row3.createCell(7);//列
        cell37.setCellStyle(cellstype_header);
        
        

        HSSFRow row4 = sheet.createRow(3); //行
        row4.setHeightInPoints(30);
        HSSFCell cell40 = row4.createCell(0);//列
        cell40.setCellStyle(cellstype_header);
        HSSFCell cell41 = row4.createCell(1);//列
        cell41.setCellStyle(cellstype_header);
        HSSFCell cell42 = row4.createCell(2);//列
        cell42.setCellValue("个数");
        cell42.setCellStyle(cellstype_header);
        HSSFCell cell43 = row4.createCell(3);//列
        cell43.setCellValue("百分百");
        cell43.setCellStyle(cellstype_header);
        HSSFCell cell44 = row4.createCell(4);//列
        cell44.setCellValue("个数");
        cell44.setCellStyle(cellstype_header);
        HSSFCell cell45 = row4.createCell(5);//列
        cell45.setCellValue("百分比");
        cell45.setCellStyle(cellstype_header);
        HSSFCell cell46 = row4.createCell(6);//列
        cell46.setCellValue("个数");
        cell46.setCellStyle(cellstype_header);
        HSSFCell cell47 = row4.createCell(7);//列
        cell47.setCellValue("百分比");
        cell47.setCellStyle(cellstype_header);
//        int fcmysl=0;
//        int mysl=0;
//        int ybsl=0;
////        int hfsl=0;
		List<Map<String, Object>> list=disciplinePlanService.oldGzfwTj(startTime, endTime);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map=list.get(i);
			Set<String> set = map.keySet();
        	if(set != null && !set.isEmpty()) {
        	for(String key : set) {
        	if(map.get(key) == null) { map.put(key, ""); }
        	}
        	}
        	
        	
	        int fcmy=Integer.parseInt( map.get("FCMY").toString());//fcmysl=fcmysl+fcmy;
	        int my= Integer.parseInt(map.get("MY").toString());//mysl=mysl+my;
	        int yb= Integer.parseInt(map.get("YB").toString());//ybsl=ybsl+yb;
			int hj=fcmy+my+yb;
			
			
	        HSSFRow row5 = sheet.createRow(4+i); //行
	        row5.setHeightInPoints(30);
	        HSSFCell cell50 = row5.createCell(0);//列
	        cell50.setCellValue(String.valueOf(map.get("SJ")));
	        cell50.setCellStyle(cellstype_header);
	        HSSFCell cell51 = row5.createCell(1);//列
	        cell51.setCellValue(hj);
	        cell51.setCellStyle(cellstype_header);
	        
	        
	        HSSFCell cell52 = row5.createCell(2);//列
	        cell52.setCellValue(String.valueOf(map.get("FCMY")));
	        cell52.setCellStyle(cellstype_header);
	        HSSFCell cell53 = row5.createCell(3);//列
	        //res.data[i].MY)/hj*100
	        NumberFormat numberFormat = NumberFormat.getInstance();  
	        numberFormat.setMaximumFractionDigits(2);  //保留小数位数
	        String result = numberFormat.format((float)fcmy/(float)hj*100);
	        cell53.setCellValue(result );
	        cell53.setCellStyle(cellstype_header);
	        HSSFCell cell54 = row5.createCell(4);//列
	        cell54.setCellValue(String.valueOf(map.get("MY")));
	        cell54.setCellStyle(cellstype_header);
	        HSSFCell cell55 = row5.createCell(5);//列
	        cell55.setCellValue(numberFormat.format((float)my/(float)hj*100));
	        cell55.setCellStyle(cellstype_header);
	        HSSFCell cell56 = row5.createCell(6);//列
	        cell56.setCellValue(String.valueOf(map.get("YB")));
	        cell56.setCellStyle(cellstype_header);
	        HSSFCell cell57 = row5.createCell(7);//列
	        cell57.setCellValue(numberFormat.format((float)yb/(float)hj*100));
	        cell57.setCellStyle(cellstype_header);
		}
        

        

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(("工作统计分析表"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
        OutputStream outp = response.getOutputStream();
        workbook.write(response.getOutputStream());
        outp.flush();
        outp.close();
        
        
//		String startTime=request.getParameter("startTime");
//		String endTime=request.getParameter("endTime");
//		try {
//			List<Map<String, Object>> list=disciplinePlanService.oldGzfwTj(startTime,endTime);
//			
//			
//			
//			request.getSession().setAttribute("startTime", startTime);
//			request.getSession().setAttribute("endTime", endTime);
//			request.getSession().setAttribute("data", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("工作服务统计导出失败！");
//		}
//		return "app/discipline/export_checked_count";
	}
	
}
