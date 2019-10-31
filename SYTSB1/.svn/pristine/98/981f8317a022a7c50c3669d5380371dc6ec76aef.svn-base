<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/k/kui-base-list.jsp" %>
<%  
	String report_name = request.getSession().getAttribute("report_name").toString();
	String cur_date_str = DateUtil.getDateTime("yyyyMMdd", new Date());	
	String title = report_name + cur_date_str;
	response.setHeader("Content-disposition","attachment; filename="+new String(title.getBytes("gb2312"), "ISO8859-1" )+".xls"); 
%>
<style type="text/css">
#table td{text-align:center;}
.xlsText{mso-number-format:"\@";} 
</style>
</head>
<body>
<div id="tableExcel"> 
	<table width="700" cellpadding="6" cellspacing="0" bgcolor="#cccccc" border="1" id="table">
    	<tr align="center">
        	<td colspan="44">
        		<font size="3"><b>${sessionScope.report_name}</b></font>
        	</td>
    	</tr>
        <tr>
        	<td >序号</td>
        	<td >报告编号</td>
			<td >鉴定人</td>
			<td >鉴定日期</td>
			<td >图号</td>
			<td >型号</td>
			<td >锅炉名称</td>
			<td >制造单位</td>
			<td >预收金额</td>
			<td >锅炉类型</td> 
			<td >设计属性</td>
			<td >鉴定属性</td>
			<td >审查属性</td>
			<td >额定出力（t/h）或（MW）</td>
			<td >额定出口压力（MPa）</td>
			<td >锅炉给水（回水）温度（℃）</td>
			<td >额定出口温度（℃）</td>
			<td >设计燃料种类</td>
			<td >结构形式</td>
			<td >燃烧方式</td>
			<td >燃料低位发热量不低于（MJ/Kg）</td>
			<td >燃烧机型号</td>
			<td >稳定工况范围（%）</td> 
			<td >设计热效率（%）</td>
			<td >总图编号/设计/审核/批准人员/批准日期/备注</td>
			<td >水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期/备注</td>
			<td >对流管束编号/设计/审核/批准人员/批准日期/备注</td>
			<td >锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期/备注</td>
			<td >过热器编号/设计/审核/批准人员/批准日期/备注</td>
			<td >减温器编号/设计/审核/批准人员/批准日期/备注</td>
			<td >省煤器编号/设计/审核/批准人员/批准日期/备注</td>
			<td >再热器编号/设计/审核/批准人员/批准日期/备注</td>
			<td >有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期/备注</td> 
			<td >强度计算汇总表编号/设计/审核/批准人员/批准日期/备注</td>
			<td >主给水管道编号/设计/审核/批准人员/批准日期和备注</td>
			<td >主蒸汽管道编号/设计/审核/批准人员/批准日期和备注</td>
			<td >再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期和备注</td> 
			<td >再热蒸汽热段管道编号/设计/审核/批准人员/批准日期和备注</td>
			<td >制造许可级别</td>
			<td >制造许可证编号</td>
			<td >制造单位地址</td>
			<td >备注</td>
			<td >本体耗钢量（吨）</td>
			<td >是否自编号</td>
		</tr>
       	<c:forEach items="${sessionScope.data}" var="data" varStatus="status">
         	<tr align="center">
            	<td>
	          		<c:out value="${status.index + 1}"></c:out>
              	</td>  
              	<td>${data.report_sn}</td>
           		<td class="xlsText">${data.inspection_user_name1}</td>
             	<td>${data.inspection_date_str}</td>
          		<td class="xlsText">${data.device_pic_no}</td>
          		<td class="xlsText">${data.device_model}</td>
          		<td class="xlsText">${data.device_name}</td>
             	<td class="xlsText">${data.made_unit_name}</td>
          		<td class="xlsText">${data.advance_fees}</td>
          		<td class="xlsText">${data.device_type_name}</td>
          		<td class="xlsText">${data.design_property}</td>
             	<td class="xlsText">${data.check_property}</td>
          		<td class="xlsText">${data.exam_property}</td>
          		<td class="xlsText">${data.rated_output}</td>
          		<td class="xlsText">${data.device_pressure}</td>
             	<td class="xlsText">${data.use_temp2}</td>
          		<td class="xlsText">${data.use_temp}</td>
          		<td class="xlsText">${data.design_fuel_type}</td>
          		<td class="xlsText">${data.struct_type}</td>
             	<td class="xlsText">${data.burn_mode}</td>
          		<td class="xlsText">${data.calorific_value}</td>
          		<td class="xlsText">${data.burner_model}</td>
          		<td class="xlsText">${data.work_range}</td>
             	<td class="xlsText">${data.design_heat}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_ztbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_bttbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_dlgsbh}</td>
             	<td class="xlsText">${data.glsjwj_jdqd_gtbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_grqbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_jwqbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_smqbh}</td>
             	<td class="xlsText">${data.glsjwj_jdqd_zrqbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_rylxttbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_qdjshzbbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_zjsgdbh}</td>
             	<td class="xlsText">${data.glsjwj_jdqd_zzqgdbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_zrzqldgdbh}</td>
          		<td class="xlsText">${data.glsjwj_jdqd_zrzqrdgdbh}</td>
          		<td class="xlsText">${data.made_license_level}</td>
          		<td class="xlsText">${data.made_license_code}</td>
             	<td class="xlsText">${data.made_unit_addr}</td>
          		<td class="xlsText">${data.remark}</td>
          		<td class="xlsText">${data.weight_steels}</td>
          		<c:choose>
          			<c:when test="${data.is_self_sn eq '1'}">
          				<td>是</td>
          			</c:when>
          			<c:otherwise>
          				<td>否</td>
          			</c:otherwise>
          		</c:choose>
        	</tr>
    	</c:forEach>
	</table>
</div>
</body>
</html>