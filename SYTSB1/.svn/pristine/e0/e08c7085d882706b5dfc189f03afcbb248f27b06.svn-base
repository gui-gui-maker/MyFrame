<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import=" tzsb.inspection.flow.insing.bean.GetInspection"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import ="com.khnt.utils.DateUtil"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	
	List<GetInspection> li=null;
	if(request.getSession().getAttribute("bjxxcxData2") !=null){
	li=(List<GetInspection>)request.getSession().getAttribute("bjxxcxData2");
	
	}
	

//for(GetInspection inspection:li){
		
		//out.print(inspection.getReportInfo()+"-------");
	//}
		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<style>
.l-grid-hd-row { border-bottom: 1px solid #A3C0E8; height: 22px; line-height: 22px; background: #269ff1; overflow: hidden; width: 80%; }
</style>

<title>内江市特种设备监督检验所</title>

<link rel="icon" href="/rsc/public/skin/default/images/index/favicon.ico" type="image/x-icon" media="screen" />
<link rel="shortcut icon" href="/rsc/public/skin/default/images/index/favicon.ico" type="image/x-icon" media="screen" />
<link rel="stylesheet" href="/rsc/public/css/main.css" type="text/css" media="all" />
<script type="text/javascript" src="/rsc/public/js/jquery.min.js"></script>
<script type="text/javascript" src="/rsc/public/js/main.js"></script>
<script type="text/javascript" src="/rsc/public/js/easytabs.js"></script>
<script src="/app/webmanage/js/foucs.js" type="text/javascript"></script>
<script src="/app/webmanage/js/WdatePicker.js" type="text/javascript"></script>
<script src="/app/webmanage/js/jquery.js" type="text/javascript"></script>
<script src="/app/webmanage/js/Guestbook.js" type="text/javascript"></script>
<script src="/app/webmanage/js/SearchData.js" type="text/javascript"></script>
<script src="/app/webmanage/js/InspectionSave.js" type="text/javascript"></script>
<script type="text/javascript">
function fz(){
	document.getElementById("device_type").value = '<%=request.getSession().getAttribute("deviceType")==null?"": request.getSession().getAttribute("deviceType").toString()%>';
	
}


</script>
</head>
<body onload="fz();">



			<div id="pageMain" class="page-main">
<!--网页sate-->	
	<!--网页头-->
	<div class="head">
		<div class="head_middle">
			<div class="head_middle_left"></div>
			<!--菜单导航-->
			<div class="main_menu">
				<ul>
					 <li><a href="/rsc/index.html">网站首页</a></li>
		          <li><a href="/rsc/jysjj/index.html">检验所简介</a></li>
		         <li><a href="/rsc/bsdt/index.html">办事大厅</a></li>
		          <li><a href="/rsc/xzzx/index.html">下载中心</a></li>
	                  <li><a href="/rsc/lxfs/index.html">联系方式</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--广告-->
	<div class="about_ad_bg">
		<div class="about_ad"><span>办事大厅</span></div>
	</div>
	<!--中间-->
	<div class="middle_body">
		<div class="middle_main">
			<div class="middle_main_left">
				<ul>
					
					 <li><a href="/rsc/jysjj/index.html" title="">机构概要</a></li>
          <li><a href="/rsc/zxbj/index.html" title="">在线报检</a></li>
          <li><a href="/rsc/jybgcx/report.jsp" title="">检验报告查询</a></li>
          <li><a href="/rsc/bsxz/index.html" title="">办事指南</a></li>
          <li><a href="/rsc/xzzx/index.html" title="">下载中心</a></li>
          <li><a href="/rsc/lxfs/index.html" title="">联系我们</a></li>
				</ul>
			</div>
			<div class="middle_main_right">
				<div class="middle_main_right_top">检验报告查询</div>
				<div class="middle_main_right_detailed">
					<div class="bsdt_welcome">
					</div>
					<div class="bsdt_zxbj_box">
						<div class="bsdt_zxbj_box_in">
							<div class="bsdt_zxbj_box_text"></div>
							<div  class="bsdt_zxbj_box_tab">
							<form  id="formObj" name="formObj" method="post" action="/infomanage/webInspection/ReportSearch.do">
								<table class="bsdt_zxbj_box_tab_table">
									<tr>
										<td style="text-align:right;">单位名称：</td>
										<td><input style="width:220px; height:28px;" id="com_name" name="com_name" value="<%=request.getSession().getAttribute("comName")==null?"":request.getSession().getAttribute("comName").toString() %>"></td>
										<td style="font-size:12px; color:#999; text-indent:20px;"></td>
									</tr>
									<tr>
										<td>设备大类：</td>
										<td><select style="width:220px; height:28px;"  id="device_type" name="device_type">
								<option value="1000" title="锅炉">锅炉</option>
								<option value="2000" title="压力容器">压力容器</option>
								<option value="3000" title="电梯">电梯</option>
								<option value="4000" title="起重机械">起重机械</option>
								<option value="5000" title="厂内机动车辆">厂内机动车辆</option>
								
							</select></td>
									
									</tr>
									<tr>
										<td>检验日期：</td>
										<td colspan="2"><input style="width:220px; height:28px;"  name="start_check_date" id="start_check_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}" onClick="WdatePicker()" validate="{disabled:true}" value="<%=request.getSession().getAttribute("startDate")==null?"":request.getSession().getAttribute("startDate").toString() %>"/>
							至
							<input style="width:220px; height:28px;"  name="end_check_date" id="end_check_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}" onClick="WdatePicker()" validate="{disabled:true}" value="<%=request.getSession().getAttribute("endDate")==null?"":request.getSession().getAttribute("endDate").toString() %>"/></td>
									</tr>
								</table>
							</div>
							<div class="bsdt_zxbj_box_butz"><INPUT class="bsdt_zxbj_box_but" type="submit" value="查询" onclick="query()"></div>
						</div>
					</div>
					<!--列表-->
					<table border="0" cellspacing="0" cellpadding="0" width="99%" align="center" class="bsdt" style=" ">
						<tr style=" font-size:14px; text-align:center; color:#999; background-color:#eee; line-height:30px;"">
							<td>单位名称</td>
							<td>报告书编号</td>
							<td>检验日期</td>
							<td>报告类型</td>
							<td>设备大类</td>
							<td>报告情况</td>
						</tr>
						
						<%if(li !=null ){   for(GetInspection inspection:li){%>
						
							
							<tr>
							<td ><%=inspection.getCompany_name()==null?"":inspection.getCompany_name() %></td>
							<td ><%=inspection.getSn()==null?"":inspection.getSn()%></td>
							<td ><%=inspection.getAdvance_time()==null?"":inspection.getAdvance_time().substring(0,10)%></td>
							<td ><%=inspection.getReport_type()==null?"":inspection.getReport_type() %></td>
							<td ><%=inspection.getDevice_sort_code()==null?"":inspection.getDevice_sort_code()%></td>
							<td><%=inspection.getReportInfo()==null?"":inspection.getReportInfo() %></td>
							</tr>
						
						<% }}else{%>
							<tr>
							<td colspan="6" align="center">无查询内容！</td>
							
							</tr>
						<% }%>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
			</div>
		</div>
	</div>
</div>
<div class="foot">
		<p> @(C) 2004-2013 版权所有: 内江市特种设备监督检验所<br />
			蜀ICP备12008332 地址：内江市东兴区西林大道789号<br />
			技术支持：成都川大科鸿新技术研究所<br />
			
		</p>
	</div>
</div>
</div>
</div>
</body>
</html>
