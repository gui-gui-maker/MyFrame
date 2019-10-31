<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head >
<%
    Map map = new HashMap();
    map=(Map)request.getAttribute("map");
  
	List<Object[]> empMan =(List<Object[]>)map.get("empMan");
	//String area_code = (String) request.getAttribute("area_code");
	List<Object[]> empWoman =(List<Object[]>)map.get("empWoman");
	List<Object[]> empMating =(List<Object[]>)map.get("empMating");
	List<Object[]> empMatingNo =(List<Object[]>)map.get("empMatingNo");

	Map map1 = new HashMap();
	Map map2 = new HashMap();
	Map map3 = new HashMap();
	Map map4 = new HashMap();
	Map map5 = new HashMap();

	int totalNormal = 0 ;
	int totalStop = 0;
	int totalCrap = 0 ;
	int totalInsp = 0 ;
	int totalWarn = 0;
       for (int i = 0 ; i < empMan.size(); i++){
		String work_department = (String)empMan.get(i)[0];
		String my_count =(String) empMan.get(i)[1].toString();
		totalNormal += Integer.parseInt(my_count);
		map1.put(work_department,my_count);
	} 
	  for (int i = 0 ; i < empWoman.size(); i++){
		String work_department = (String)empWoman.get(i)[0];
		String my_count =(String) empWoman.get(i)[1].toString();
		totalStop += Integer.parseInt(my_count);
		map2.put(work_department,my_count);
	} 
	for (int i = 0 ; i < empMating.size(); i++){
		String work_department = (String)empMating.get(i)[0];
		String my_count =(String) empMating.get(i)[1].toString();
		totalCrap += Integer.parseInt(my_count);
		map3.put(work_department,my_count);
	}
	for (int i = 0 ; i < empMatingNo.size(); i++){
		String work_department = (String)empMatingNo.get(i)[0];
		String my_count =(String) empMatingNo.get(i)[1].toString();
		totalInsp += Integer.parseInt(my_count);
		map4.put(work_department,my_count);
	}
	int totalAll = totalNormal + totalStop ;
	
	int total100020 = Integer.parseInt((String)(map1.get("100020")==null?"0":map1.get("100020")))
				+	Integer.parseInt((String)(map2.get("100020")==null?"0":map2.get("100020")));
	int total100021 = Integer.parseInt((String)(map1.get("100021")==null?"0":map1.get("100021")))
		+	Integer.parseInt((String)(map2.get("100021")==null?"0":map2.get("100021")));
	int total100022 = Integer.parseInt((String)(map1.get("100022")==null?"0":map1.get("100022")))
	+	Integer.parseInt((String)(map2.get("100022")==null?"0":map2.get("100022")));
	int total100023 = Integer.parseInt((String)(map1.get("100023")==null?"0":map1.get("100023")))
	+	Integer.parseInt((String)(map2.get("100023")==null?"0":map2.get("100023")));
	int total100063 = Integer.parseInt((String)(map1.get("100063")==null?"0":map1.get("100063")))
			+	Integer.parseInt((String)(map2.get("100063")==null?"0":map2.get("100063")));
	int total100024 = Integer.parseInt((String)(map1.get("100024")==null?"0":map1.get("100024")))
	+	Integer.parseInt((String)(map2.get("100024")==null?"0":map2.get("100024")));
	int total100033 = Integer.parseInt((String)(map1.get("100033")==null?"0":map1.get("100033")))
	+	Integer.parseInt((String)(map2.get("100033")==null?"0":map2.get("100033")));
	int total100034 = Integer.parseInt((String)(map1.get("100034")==null?"0":map1.get("100034")))
	+	Integer.parseInt((String)(map2.get("100034")==null?"0":map2.get("100034")));
	int total100035 = Integer.parseInt((String)(map1.get("100035")==null?"0":map1.get("100035")))
	+	Integer.parseInt((String)(map2.get("100035")==null?"0":map2.get("100035")));
	int total100036 = Integer.parseInt((String)(map1.get("100036")==null?"0":map1.get("100036")))
			+	Integer.parseInt((String)(map2.get("100036")==null?"0":map2.get("100036")));
	int total100037 = Integer.parseInt((String)(map1.get("100037")==null?"0":map1.get("100037")))
			+	Integer.parseInt((String)(map2.get("100037")==null?"0":map2.get("100037")));
	int total100038 = Integer.parseInt((String)(map1.get("100038")==null?"0":map1.get("100038")))
			+	Integer.parseInt((String)(map2.get("100038")==null?"0":map2.get("100038")));
	int total100031 = Integer.parseInt((String)(map1.get("100031")==null?"0":map1.get("100031")))
			+	Integer.parseInt((String)(map2.get("100031")==null?"0":map2.get("100031")));
	int total100028 = Integer.parseInt((String)(map1.get("100028")==null?"0":map1.get("100028")))
			+	Integer.parseInt((String)(map2.get("100028")==null?"0":map2.get("100028")));
	int total100029 = Integer.parseInt((String)(map1.get("100029")==null?"0":map1.get("100029")))
			+	Integer.parseInt((String)(map2.get("100029")==null?"0":map2.get("100029")));
	int total100082 = Integer.parseInt((String)(map1.get("100082")==null?"0":map1.get("100082")))
			+	Integer.parseInt((String)(map2.get("100082")==null?"0":map2.get("100082")));
	int total100032 = Integer.parseInt((String)(map1.get("100032")==null?"0":map1.get("100032")))
			+	Integer.parseInt((String)(map2.get("100032")==null?"0":map2.get("100032")));
	int total100041 = Integer.parseInt((String)(map1.get("100041")==null?"0":map1.get("100041")))
			+	Integer.parseInt((String)(map2.get("100041")==null?"0":map2.get("100041")));
	int total100039 = Integer.parseInt((String)(map1.get("100039")==null?"0":map1.get("100039")))
			+	Integer.parseInt((String)(map2.get("100039")==null?"0":map2.get("100039")));
	int total100027 = Integer.parseInt((String)(map1.get("100027")==null?"0":map1.get("100027")))
			+	Integer.parseInt((String)(map2.get("100027")==null?"0":map2.get("100027")));
	int total100085 = Integer.parseInt((String)(map1.get("100085")==null?"0":map1.get("100085")))
			+	Integer.parseInt((String)(map2.get("100085")==null?"0":map2.get("100085")));
	int total100026 = Integer.parseInt((String)(map1.get("100026")==null?"0":map1.get("100026")))
			+	Integer.parseInt((String)(map2.get("100026")==null?"0":map2.get("100026")));
	int total100025 = Integer.parseInt((String)(map1.get("100025")==null?"0":map1.get("100025")))
			+	Integer.parseInt((String)(map2.get("100025")==null?"0":map2.get("100025")));
	int total100030 = Integer.parseInt((String)(map1.get("100030")==null?"0":map1.get("100030")))
			+	Integer.parseInt((String)(map2.get("100030")==null?"0":map2.get("100030")));
	int total100040 = Integer.parseInt((String)(map1.get("100040")==null?"0":map1.get("100040")))
			+	Integer.parseInt((String)(map2.get("100040")==null?"0":map2.get("100040")));
	int total100045 = Integer.parseInt((String)(map1.get("100045")==null?"0":map1.get("100045")))
			+	Integer.parseInt((String)(map2.get("100045")==null?"0":map2.get("100045")));
	int total100046 = Integer.parseInt((String)(map1.get("100046")==null?"0":map1.get("100046")))
			+	Integer.parseInt((String)(map2.get("100046")==null?"0":map2.get("100046")));
	int total100062 = Integer.parseInt((String)(map1.get("100062")==null?"0":map1.get("100062")))
			+	Integer.parseInt((String)(map2.get("100062")==null?"0":map2.get("100062")));
	int total100065 = Integer.parseInt((String)(map1.get("100065")==null?"0":map1.get("100065")))
			+	Integer.parseInt((String)(map2.get("100065")==null?"0":map2.get("100065")));
	int total100066 = Integer.parseInt((String)(map1.get("100066")==null?"0":map1.get("100066")))
			+	Integer.parseInt((String)(map2.get("100066")==null?"0":map2.get("100066")));
	int total100067 = Integer.parseInt((String)(map1.get("100067")==null?"0":map1.get("100067")))
			+	Integer.parseInt((String)(map2.get("100067")==null?"0":map2.get("100067")));
	int total100044 = Integer.parseInt((String)(map1.get("100044")==null?"0":map1.get("100044")))
			+	Integer.parseInt((String)(map2.get("100044")==null?"0":map2.get("100044")));
	int total100043 = Integer.parseInt((String)(map1.get("100043")==null?"0":map1.get("100043")))
			+	Integer.parseInt((String)(map2.get("100043")==null?"0":map2.get("100043")));
	int total100078 = Integer.parseInt((String)(map1.get("100078")==null?"0":map1.get("100078")))
			+	Integer.parseInt((String)(map2.get("100078")==null?"0":map2.get("100078")));
	int total100083 = Integer.parseInt((String)(map1.get("100083")==null?"0":map1.get("100083")))
			+	Integer.parseInt((String)(map2.get("100083")==null?"0":map2.get("100083")));
%>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
$(function() {
	$("#formObj").initForm({
		  toolbar:[""]
});
}); 
function showList(work_department,status){
	top.$.dialog({
		width : 1000, 
		height : 500, 
		lock : true, 
		title:"详情信息",
		content: 'url:app/humanresources/employee_statistical_list.jsp?work_department='+work_department+"&status="+status,
		data : {"window" : window}
	});
}

</script>
<style>
</style>
	</head>
	
	

<body>
 <form name="formObj" id="formObj" method="post" >
   	<h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">按部门统计员工 </h1>
   
   <table cellpadding="0" cellspacing="0"  class="l-detail-table" height="590px">

						<tr id='queryhead'>
						<td class="l-t-td-left"><b>部门名称</b></td>
						<td class="l-t-td-left"><b>男</b></td>
						<td class="l-t-td-left"><b>女</b></td>
						<td class="l-t-td-left"><b>已婚</b></td>
						<td class="l-t-td-left"><b>未婚</b></td>
						<td class="l-t-td-left"><b>总计</b></td>
						</tr>

						<tr align="center">
						<td class="l-t-td-right">院党政领导</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100029','1');return false"><%=map1.get("100029")==null?"0":map1.get("100029")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100029','2');return false"><%=map2.get("100029")==null?"0":map2.get("100029")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100029','3');return false"><%=map3.get("100029")==null?"0":map3.get("100029")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100029','4');return false"><%=map4.get("100029")==null?"0":map4.get("100029")%></td>
						<td class="l-t-td-right"><%=total100029%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">办公室</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100040','1');return false"><%=map1.get("100040")==null?"0":map1.get("100040")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100040','2');return false"><%=map2.get("100040")==null?"0":map2.get("100040")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100040','3');return false"><%=map3.get("100040")==null?"0":map3.get("100040")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100040','4');return false"><%=map4.get("100040")==null?"0":map4.get("100040")%></td>
						<td class="l-t-td-right"><%=total100040%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">人力资源管理部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100041','1');return false"><%=map1.get("100041")==null?"0":map1.get("100041")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100041','2');return false"><%=map2.get("100041")==null?"0":map2.get("100041")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100041','3');return false"><%=map3.get("100041")==null?"0":map3.get("100041")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100041','4');return false"><%=map4.get("100041")==null?"0":map4.get("100041")%></td>
						<td class="l-t-td-right"><%=total100041%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">财务管理部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100025','1');return false"><%=map1.get("100025")==null?"0":map1.get("100025")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100025','2');return false"><%=map2.get("100025")==null?"0":map2.get("100025")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100025','3');return false"><%=map3.get("100025")==null?"0":map3.get("100025")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100025','4');return false"><%=map4.get("100025")==null?"0":map4.get("100025")%></td>
						<td class="l-t-td-right"><%=total100025%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">质量监督管理部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100026','1');return false"><%=map1.get("100026")==null?"0":map1.get("100026")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100026','2');return false"><%=map2.get("100026")==null?"0":map2.get("100026")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100026','3');return false"><%=map3.get("100026")==null?"0":map3.get("100026")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100026','4');return false"><%=map4.get("100026")==null?"0":map4.get("100026")%></td>
						<td class="l-t-td-right"><%=total100026%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">科研技术管理部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100030','1');return false"><%=map1.get("100030")==null?"0":map1.get("100030")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100030','2');return false"><%=map2.get("100030")==null?"0":map2.get("100030")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100030','3');return false"><%=map3.get("100030")==null?"0":map3.get("100030")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100030','4');return false"><%=map4.get("100030")==null?"0":map4.get("100030")%></td>
						<td class="l-t-td-right"><%=total100030%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">业务发展部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100045','1');return false"><%=map1.get("100045")==null?"0":map1.get("100045")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100045','2');return false"><%=map2.get("100045")==null?"0":map2.get("100045")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100045','3');return false"><%=map3.get("100045")==null?"0":map3.get("100045")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100045','4');return false"><%=map4.get("100045")==null?"0":map4.get("100045")%></td>
						<td class="l-t-td-right"><%=total100045%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">保障部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100046','1');return false"><%=map1.get("100046")==null?"0":map1.get("100046")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100046','2');return false"><%=map2.get("100046")==null?"0":map2.get("100046")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100046','3');return false"><%=map3.get("100046")==null?"0":map3.get("100046")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100046','4');return false"><%=map4.get("100046")==null?"0":map4.get("100046")%></td>
						<td class="l-t-td-right"><%=total100046%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">科研技术研究所</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100062','1');return false"><%=map1.get("100062")==null?"0":map1.get("100062")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100062','2');return false"><%=map2.get("100062")==null?"0":map2.get("100062")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100062','3');return false"><%=map3.get("100062")==null?"0":map3.get("100062")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100062','4');return false"><%=map4.get("100062")==null?"0":map4.get("100062")%></td>
						<td class="l-t-td-right"><%=total100062%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">业务服务部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100027','1');return false"><%=map1.get("100027")==null?"0":map1.get("100027")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100027','2');return false"><%=map2.get("100027")==null?"0":map2.get("100027")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100027','3');return false"><%=map3.get("100027")==null?"0":map3.get("100027")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100027','4');return false"><%=map4.get("100027")==null?"0":map4.get("100027")%></td>
						<td class="l-t-td-right"><%=total100027%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">党委办公室</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100085','1');return false"><%=map1.get("100085")==null?"0":map1.get("100085")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100085','2');return false"><%=map2.get("100085")==null?"0":map2.get("100085")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100085','3');return false"><%=map3.get("100085")==null?"0":map3.get("100085")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100085','4');return false"><%=map4.get("100085")==null?"0":map4.get("100085")%></td>
						<td class="l-t-td-right"><%=total100085%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">纪检监察室</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100031','1');return false"><%=map1.get("100031")==null?"0":map1.get("100031")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100031','2');return false"><%=map2.get("100031")==null?"0":map2.get("100031")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100031','3');return false"><%=map3.get("100031")==null?"0":map3.get("100031")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100031','4');return false"><%=map4.get("100031")==null?"0":map4.get("100031")%></td>
						<td class="l-t-td-right"><%=total100031%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">文化宣传中心</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100028','1');return false"><%=map1.get("100028")==null?"0":map1.get("100028")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100028','2');return false"><%=map2.get("100028")==null?"0":map2.get("100028")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100028','3');return false"><%=map3.get("100028")==null?"0":map3.get("100028")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100028','4');return false"><%=map4.get("100028")==null?"0":map4.get("100028")%></td>
						<td class="l-t-td-right"><%=total100028%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">信息中心</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100082','1');return false"><%=map1.get("100082")==null?"0":map1.get("100082")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100082','2');return false"><%=map2.get("100082")==null?"0":map2.get("100082")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100082','3');return false"><%=map3.get("100082")==null?"0":map3.get("100082")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100082','4');return false"><%=map4.get("100082")==null?"0":map4.get("100082")%></td>
						<td class="l-t-td-right"><%=total100082%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">基建办</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100043','1');return false"><%=map1.get("100043")==null?"0":map1.get("100043")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100043','2');return false"><%=map2.get("100043")==null?"0":map2.get("100043")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100043','3');return false"><%=map3.get("100043")==null?"0":map3.get("100043")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100043','4');return false"><%=map4.get("100043")==null?"0":map4.get("100043")%></td>
						<td class="l-t-td-right"><%=total100043%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">事故技术分析中心</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100078','1');return false"><%=map1.get("100078")==null?"0":map1.get("100078")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100078','2');return false"><%=map2.get("100078")==null?"0":map2.get("100078")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100078','3');return false"><%=map3.get("100078")==null?"0":map3.get("100078")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100078','4');return false"><%=map4.get("100078")==null?"0":map4.get("100078")%></td>
						<td class="l-t-td-right"><%=total100078%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">资料室</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100083','1');return false"><%=map1.get("100083")==null?"0":map1.get("100083")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100083','2');return false"><%=map2.get("100083")==null?"0":map2.get("100083")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100083','3');return false"><%=map3.get("100083")==null?"0":map3.get("100083")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100083','4');return false"><%=map4.get("100083")==null?"0":map4.get("100083")%></td>
						<td class="l-t-td-right"><%=total100083%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验一部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100020','1');return false"><%=map1.get("100020")==null?"0":map1.get("100020")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100020','2');return false"><%=map2.get("100020")==null?"0":map2.get("100020")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100020','3');return false"><%=map3.get("100020")==null?"0":map3.get("100020")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100020','4');return false"><%=map4.get("100020")==null?"0":map4.get("100020")%></td>
						<td class="l-t-td-right"><%=total100020%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验二部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100021','1');return false"><%=map1.get("100021")==null?"0":map1.get("100021")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100021','2');return false"><%=map2.get("100021")==null?"0":map2.get("100021")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100021','3');return false"><%=map3.get("100021")==null?"0":map3.get("100021")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100021','4');return false"><%=map4.get("100021")==null?"0":map4.get("100021")%></td>
						<td class="l-t-td-right"><%=total100021%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验三部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100022','1');return false"><%=map1.get("100022")==null?"0":map1.get("100022")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100022','2');return false"><%=map2.get("100022")==null?"0":map2.get("100022")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100022','3');return false"><%=map3.get("100022")==null?"0":map3.get("100022")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100022','4');return false"><%=map4.get("100022")==null?"0":map4.get("100022")%></td>
						<td class="l-t-td-right"><%=total100022%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验四部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100023','1');return false"><%=map1.get("100023")==null?"0":map1.get("100023")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100023','2');return false"><%=map2.get("100023")==null?"0":map2.get("100023")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100023','3');return false"><%=map3.get("100023")==null?"0":map3.get("100023")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100023','4');return false"><%=map4.get("100023")==null?"0":map4.get("100023")%></td>
						<td class="l-t-td-right"><%=total100023%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验五部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100063','1');return false"><%=map1.get("100063")==null?"0":map1.get("100063")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100063','2');return false"><%=map2.get("100063")==null?"0":map2.get("100063")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100063','3');return false"><%=map3.get("100063")==null?"0":map3.get("100063")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100063','4');return false"><%=map4.get("100063")==null?"0":map4.get("100063")%></td>
						<td class="l-t-td-right"><%=total100063%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">机电设备检验六部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100024','1');return false"><%=map1.get("100024")==null?"0":map1.get("100024")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100024','2');return false"><%=map2.get("100024")==null?"0":map2.get("100024")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100024','3');return false"><%=map3.get("100024")==null?"0":map3.get("100024")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100024','4');return false"><%=map4.get("100024")==null?"0":map4.get("100024")%></td>
						<td class="l-t-td-right"><%=total100024%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">容器检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100034','1');return false"><%=map1.get("100034")==null?"0":map1.get("100034")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100034','2');return false"><%=map2.get("100034")==null?"0":map2.get("100034")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100034','3');return false"><%=map3.get("100034")==null?"0":map3.get("100034")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100034','4');return false"><%=map4.get("100034")==null?"0":map4.get("100034")%></td>
						<td class="l-t-td-right"><%=total100034%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">产品监督检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100035','1');return false"><%=map1.get("100035")==null?"0":map1.get("100035")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100035','2');return false"><%=map2.get("100035")==null?"0":map2.get("100035")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100035','3');return false"><%=map3.get("100035")==null?"0":map3.get("100035")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100035','4');return false"><%=map4.get("100035")==null?"0":map4.get("100035")%></td>
						<td class="l-t-td-right"><%=total100035%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">锅炉检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100033','1');return false"><%=map1.get("100033")==null?"0":map1.get("100033")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100033','2');return false"><%=map2.get("100033")==null?"0":map2.get("100033")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100033','3');return false"><%=map3.get("100033")==null?"0":map3.get("100033")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100033','4');return false"><%=map4.get("100033")==null?"0":map4.get("100033")%></td>
						<td class="l-t-td-right"><%=total100033%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">罐车检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100036','1');return false"><%=map1.get("100036")==null?"0":map1.get("100036")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100036','2');return false"><%=map2.get("100036")==null?"0":map2.get("100036")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100036','3');return false"><%=map3.get("100036")==null?"0":map3.get("100036")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100036','4');return false"><%=map4.get("100036")==null?"0":map4.get("100036")%></td>
						<td class="l-t-td-right"><%=total100036%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">管道检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100037','1');return false"><%=map1.get("100037")==null?"0":map1.get("100037")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100037','2');return false"><%=map2.get("100037")==null?"0":map2.get("100037")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100037','3');return false"><%=map3.get("100037")==null?"0":map3.get("100037")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100037','4');return false"><%=map4.get("100037")==null?"0":map4.get("100037")%></td>
						<td class="l-t-td-right"><%=total100037%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">石化装置检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100065','1');return false"><%=map1.get("100065")==null?"0":map1.get("100065")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100065','2');return false"><%=map2.get("100065")==null?"0":map2.get("100065")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100065','3');return false"><%=map3.get("100065")==null?"0":map3.get("100065")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100065','4');return false"><%=map4.get("100065")==null?"0":map4.get("100065")%></td>
						<td class="l-t-td-right"><%=total100065%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">储气井检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100066','1');return false"><%=map1.get("100066")==null?"0":map1.get("100066")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100066','2');return false"><%=map2.get("100066")==null?"0":map2.get("100066")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100066','3');return false"><%=map3.get("100066")==null?"0":map3.get("100066")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100066','4');return false"><%=map4.get("100066")==null?"0":map4.get("100066")%></td>
						<td class="l-t-td-right"><%=total100066%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">无损检验事业部</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100067','1');return false"><%=map1.get("100067")==null?"0":map1.get("100067")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100067','2');return false"><%=map2.get("100067")==null?"0":map2.get("100067")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100067','3');return false"><%=map3.get("100067")==null?"0":map3.get("100067")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100067','4');return false"><%=map4.get("100067")==null?"0":map4.get("100067")%></td>
						<td class="l-t-td-right"><%=total100067%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">司法鉴定中心</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100044','1');return false"><%=map1.get("100044")==null?"0":map1.get("100044")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100044','2');return false"><%=map2.get("100044")==null?"0":map2.get("100044")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100044','3');return false"><%=map3.get("100044")==null?"0":map3.get("100044")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100044','4');return false"><%=map4.get("100044")==null?"0":map4.get("100044")%></td>
						<td class="l-t-td-right"><%=total100044%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">区县局</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100032','1');return false"><%=map1.get("100032")==null?"0":map1.get("100032")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100032','2');return false"><%=map2.get("100032")==null?"0":map2.get("100032")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100032','3');return false"><%=map3.get("100032")==null?"0":map3.get("100032")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100032','4');return false"><%=map4.get("100032")==null?"0":map4.get("100032")%></td>
						<td class="l-t-td-right"><%=total100032%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">天府新区</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100038','1');return false"><%=map1.get("100038")==null?"0":map1.get("100038")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100038','2');return false"><%=map2.get("100038")==null?"0":map2.get("100038")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100038','3');return false"><%=map3.get("100038")==null?"0":map3.get("100038")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100038','4');return false"><%=map4.get("100038")==null?"0":map4.get("100038")%></td>
						<td class="l-t-td-right"><%=total100038%></td>
						</tr>
						<tr align="center">
						<td class="l-t-td-right">成都质监局</td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100039','1');return false"><%=map1.get("100039")==null?"0":map1.get("100039")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100039','2');return false"><%=map2.get("100039")==null?"0":map2.get("100039")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100039','3');return false"><%=map3.get("100039")==null?"0":map3.get("100039")%></td>
						<td class="l-t-td-right"><a href="#NFSGO_COM" onclick="showList('100039','4');return false"><%=map4.get("100039")==null?"0":map4.get("100039")%></td>
						<td class="l-t-td-right"><%=total100039%></td>
						</tr>
						
						<tr align="center">
						<td class="l-t-td-right"><b>总计</b></td>
						<td class="l-t-td-right"><b><%=totalNormal%></b></td>
						<td class="l-t-td-right"><b><%=totalStop%></b></td>
						<td class="l-t-td-right"><b><%=totalCrap%></b></td>
						<td class="l-t-td-right"><b><%=totalInsp%></b></td>
						<td class="l-t-td-right"><b><%=totalAll%></b></td>
						</tr>
						
				</table> 
       </form>
</body>
</html>

