<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<link rel="stylesheet" href="app/k/km/lib/kh-mobile.css"/>
<script src="app/k/jqm/jquery2.js"></script>
<script src="app/k/km/lib/kh-mobile.js"></script>
<script src="app/k/km/lib/kh-mobile-list.js"></script>

<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<script src="app/k/km/lib/kh-mobile-list.js"></script>

<%
	String phone = (String)session.getAttribute("Phone");
	String name = (String)session.getAttribute("Name");
	String accessToken = (String)session.getAttribute("AccessToken");
	%>
<%-- <%@include file="/k/jqm/mobile-base.jsp" %> --%>
<script type="text/javascript">
/* function hideTitle(){
	window.KHANJS.titleShow("null");
} */
var isClick;
$(function(){
	$("#detail-page").hide();
	$.ajax({
		url : "${pageContext.request.contextPath}/employeeBaseAction/empByPhone.do?phone="+'<%=phone%>',
		//url : "${pageContext.request.contextPath}/employeeBaseAction/empByPhone.do?phone=13980788181",
		type : "post",
		datatype : "json",
		contentType: "application/json; charset=utf-8",
      success : function (data) {
      	if(data.success){
      		var employeeBase = data.employeeBase;
      		var workDepartment = employeeBase.workDepartment;
      		//var workDepartment = "100041";
			var empId = employeeBase.id;
      		if(workDepartment == "100041" || workDepartment == "100029" || empId == "402883a046d0c7990146d0c88c3b0001"){
      			isClick = true;
      		}else{
      			isClick = false;
      		}
      	}else{
      		dialogShow(data.msg, 300, 100);
      	}
      }
	});
	
	//性别
	getcodetabl("BASE_SEX","empSex");
	//员工来源
	getcodetabl("TJY2_RL_MANSOURCE","manSource");
	//岗位
	getcodetabl("TJY2_RL_POSITION","position");
	//等级
	getcodetabl("TJY2_RL_LEVEL","grade");
	//员工身份
	getcodetabl("pub_user_type", "empPosition");
	//经费形式
	getcodetabl("TJY2_RL_FUNDINGSHAPE", "fundingShape");
	
	$("#next1").click(function() {
		var name = $("#name").val();
		if(name != ""){
			if(isClick == true){
				/* var selectedId = sThis.find('input[name="id"]').val() ;
				location.href="${pageContext.request.contextPath}/app/weixin/rs/rs_detail_1.jsp?name="+name;*/
				$.ajax({
					url : "${pageContext.request.contextPath}/employeeBaseAction/getEmpOnWXByName.do?name="+encodeURI(encodeURI(name)),
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			      success : function (data) {
			      	if(data.success){
						   var emplist = data.emplist;
						   //dAlert(emplist.length);
						   if(emplist.length == 1){
							   var emp = emplist[0];
							   $("#detail-page").show();
							   $("#form2").transform("detail");
						       $("#form2").setValues(emp);
						       var g1=emp.joinWorkDate;
			 				 	if(g1!=null){
			 				 	 $("#joinWorkDate").html(g1.substring(0,7));
			 				 	}
			 				 	var g2=emp.initialStopDate;
			  				 	if(g2!=null){
			  				 	 $("#initialStopDate").html(g2.substring(0,7));
			  				 	}
						       var g3=emp.intoWorkDate;
			  				 	if(g3!=null){
			  				 	 $("#intoWorkDate").html(g3.substring(0,7));
			  				 	}
			  				 	var g4=emp.empSex;
			  				 	if(g4==null){$("#empSex").html("");}
			  				 	var g5=emp.empIdCard;
			  				 	if(g5==null){$("#empIdCard").html("");}
			  				 	var g6=emp.empNativePlace;
			  				 	if(g6==null){$("#empNativePlace").html("");}
			  				 	var g7=emp.empPolitical;
			  				 	if(g7==null){$("#empPolitical").html("");}
			  				 	var g8=emp.mobilePhone;
			  				 	if(g8==null){$("#mobilePhone").html("");}
			  				 	var g9=emp.empTitle;
			  				 	if(g9==null){$("#empTitle").html("");}
			  				 	var g10=emp.initialEducation;
			  				 	if(g10==null){$("#initialEducation").html("");}
			  				 	var g11=emp.initial_degree;
			  				 	if(g11==null){$("#initial_degree").html("");}
			  				 	var g12=emp.initialMajor;
			  				 	if(g12==null){$("#initialMajor").html("");}
			  				 	var g13=emp.initialSchool;
			  				 	if(g13==null){$("#initialSchool").html("");}
			  				 	var g14=emp.mbaEducation;
			  				 	if(g14==null){$("#mbaEducation").html("");}
			  				 	var g15=emp.mba_degree;
			  				 	if(g15==null){$("#mba_degree").html("");}
			  				 	var g16=emp.mbaMajor;
			  				 	if(g16==null){$("#mbaMajor").html("");}
			  				 	var g17=emp.mbaSchool;
			  				 	if(g17==null){$("#mbaSchool").html("");}
			  				 	var g18=emp.workTitle;
			  				 	if(g18==null){$("#workTitle").html("");}
			  				 	var g19=emp.manSource;
			  				 	if(g19==null){$("#manSource").html("");}
			  				 	var g20=emp.fundingShape;
			  				 	if(g20==null){$("#fundingShape").html("");}
			  				 	var g21=emp.workDepartmentName;
			  				 	if(g21==null){$("#workDepartmentName").html("");}
			  				 	var g22=emp.position;
			  				 	if(g22==null){$("#position").html("");}
			  				 	var g23=emp.grade;
			  				 	if(g23==null){$("#grade").html("");}
			  				 	var g24=emp.empPosition;
			  				 	if(g24==null){$("#empPosition").html("");}
						   }else if(emplist.length >= 2){
							   $("#detail-page").hide();
							   
						   }else{
						   dAlert("无此人信息！");
						   }
			      	}else{
			      		//$("body").unmask();
			      		dialogShow(data.msg, 300, 100);
			      	}
			      }
				});
			}else{
				dAlert("您无权查看职工信息！");
			} 
		}else{
			dAlert("姓名不能为空！");
		}
	});
}) 

function getcodetabl(code,id) {
		$.ajax({
			url : "${pageContext.request.contextPath}/employeeBaseAction/getcodetabl.do?tablname=" + code,
			type : "POST",
			async : false,
			success : function(json) {
				var s = "";
				if (json.success) {
					for ( var i in json.data) {
						s += "<option value='"+json.data[i].value+"'>"
								+ json.data[i].name + "</option>";
					}
					$("#" + id).html(s);

				}
			}
		});
	}
</script>
<style>
.wrapper{ margin-top:23%;};
</style>

</head>
<body>
<div  id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>职工档案-职工查询</h2>
		</div>
	</div>
	<div id="wrapper1" class="wrapper">
	<div style=" padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
    background-color: white;">
		<div class="page-header" align="center">
			<h1></h1>
		</div>
		<div class="page-panel" id="m-page-panel">
			<form id="form1" name="form1" method="post" action="" onsubmit="return false;" getAction="" pageStatus="detail">
				<table border="0" cellpadding="0" cellspacing="0" width="100%"
					height="" align="">
					<tr class="tr1">
						<td width="50" class="tdtext1">姓名：</td>
						<td  style="padding-right: 0px;width: 58%;">
						<input type="text" class="form-control" id="name" name="empName" maxLength="10"
							style="width: 100%;padding-right: 5px;padding-left: 5px;"></td>
						<td>
							<div class="bt1" >
								<div class="text-center row" style="width: 100%;padding-left: 10%;">
									<div class=""><a id="next1" class="button button-block button-rounded button-primary" 
										style="padding-left: 0px;padding-right: 0px;width: 100%;">查询</a></div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		</div>
		</div>
</div>
<div id="wrapper" class="wrapper">
	<div class="container" style="margin-top: 38%;">
		<div class="page-header" align="center">
			<h1></h1>
		</div>
		<div id="detail-page">
			<form id="form2" name="form2" method="post" action="" onsubmit="return false;" getAction="" pageStatus="detail">
				<table border="0" cellpadding="0" cellspacing="0" width="100%"
					height="" align="">
					<tr class="tr1">
						<td width="80" class="tdtext1">姓名：</td>
						<td><input type="text" class="form-control" id="name"
							name="empName" 
							maxLength="10"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">性别：</td>
						<td><select  class="form-control" id="empSex"
							name="empSex"  maxLength="18"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">身份证号：</td>
						<td><input type="text" class="form-control" id="empIdCard"
							name="empIdCard" 
							maxLength="11"></td>

					</tr>
					<tr class="tr1">
						<td class="tdtext1">籍贯：</td>
						<td><input type="text" class="form-control"
							id="empNativePlace" name="empNativePlace" ext_isNull="1" maxLength="25" ext_isNull="1"></td>

					</tr>
					<tr class="tr1">
						<td class="tdtext1">政治面貌：</td>
						<td><input type="text" class="form-control" id="empPolitical"
							name="empPolitical"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">联系电话：</td>
						<td><input type="text" class="form-control" id="mobilePhone"
							name="mobilePhone"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">职称：</td>
						<td><input type="text" class="form-control" id="empTitle"
							name="empTitle"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">参加工作时间：</td>
						<td><input type="text" class="form-control" id="joinWorkDate"
							name="joinWorkDate"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">初始学历：</td>
						<td><input type="text" class="form-control" id="initialEducation"
							name="initialEducation"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">初始学位：</td>
						<td><input type="text" class="form-control" id="initial_degree"
							name="initial_degree"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">初始专业：</td>
						<td><input type="text" class="form-control" id="initialMajor"
							name="initialMajor"></td>
					</tr> 
					<tr class="tr1">
						<td class="tdtext1">初始毕业院校：</td>
						<td><input type="text" class="form-control" id="initialSchool"
							name="initialSchool"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">在职学历：</td>
						<td><input type="text" class="form-control" id="mbaEducation"
							name="mbaEducation"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">在职学位：</td>
						<td><input type="text" class="form-control" id="mba_degree"
							name="mba_degree"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">在职专业：</td>
						<td><input type="text" class="form-control" id="mbaMajor"
							name="mbaMajor"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">在职毕业院校：</td>
						<td><input type="text" class="form-control" id="mbaSchool"
							name="mbaSchool"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">毕业时间：</td>
						<td><input type="text" class="form-control" id="initialStopDate"
							name="initialStopDate"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">职务：</td>
						<td><input type="text" class="form-control" id="workTitle"
							name="workTitle"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">人员来源：</td>
						<td><select  class="form-control" id="manSource"
							name="manSource" ></td>
							
					</tr>
					<tr class="tr1">
						<td class="tdtext1">经费形式：</td>
						<td><select class="form-control" id="fundingShape"
							name="fundingShape"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">所属部门：</td>
						<td><input type="text" class="form-control" id="workDepartmentName"
							name="workDepartmentName"></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">岗位：</td>
						<td><select  class="form-control" id="position"
							name="position" ></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">等级：</td>
						<td><select  class="form-control" id="grade"
							name="grade" ></td>
							
					</tr>
					<tr class="tr1">
						<td class="tdtext1">员工身份：</td>
						<td><select class="form-control" id="empPosition"
							name="empPosition" ></td>
					</tr>
					<tr class="tr1">
						<td class="tdtext1">入院时间：</td>
						<td><input type="text" class="form-control" id="intoWorkDate"
							name="intoWorkDate"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>



