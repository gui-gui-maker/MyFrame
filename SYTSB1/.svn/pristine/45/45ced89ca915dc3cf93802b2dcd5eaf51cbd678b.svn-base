<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="com.khnt.pub.codetable.service.CodeTableCache" %>
<%@ page import = "com.khnt.pub.codetable.bean.CodeTable" %>
<%@ page import = "com.khnt.pub.codetable.bean.CodeTableValue" %>
<%@ page import = "org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import = "org.springframework.context.ApplicationContext" %>
<% 
	ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	CodeTableCache ctc = (CodeTableCache)ctx.getBean("codeTableCache");
	CodeTable cgtb = null;//证书类别
	CodeTable leveltb = null;//证书级别
	CodeTable contracttb = null;//合同类别
	if(ctc != null){
		cgtb = ctc.getCodeTable("BASE_LETTER");
		leveltb = ctc.getCodeTable("BASE_CERT_LEVEL");
		contracttb = ctc.getCodeTable("TJY2_RL_CONTRACTTYPE");
		
	}
	List<CodeTableValue>  contractCode = null;
	List<CodeTableValue>  cgCode = null;
	List<CodeTableValue>  levelCode = null;
	if(contracttb!=null){
		contractCode = contracttb.getCodeTableValues();
	}
	if(cgtb!=null){
		cgCode = cgtb.getCodeTableValues();
	}
	if(leveltb!=null){
		levelCode = leveltb.getCodeTableValues();
	}
	request.setAttribute("contractCode",contractCode);
	request.setAttribute("cgCode",cgCode);
	request.setAttribute("levelCode",levelCode);
%> 
<head pageStatus="${param.status}">
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<title>人员信息</title>
<script type="text/javascript">

	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
	
	function detailUser(){
		top.$.dialog({
			width : 800,
			height :600,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add',
			data : {
				"window" : window,view:'app/research/ser_user_indp_list'
			}
		});
	}
	
	function detailInsp(){
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add'+
					"&view=app/research/ser_user_indp_list",
			data : {
				"window" : window
			}
		});
	}
	
	function showBxDetail(val){
		var value = val.substring(0,val.length-1);
		
	//	alert(value+"---${emp.id}")
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "报销详情信息",
			content : 'url:app/research/fee_detail.jsp?years='+value+"&user=${emp.id}",
			data : {
				"window" : window
			}
		});
	}

	//下载证书
	function downCertFile(fileId,id,filename){
		if(fileId==""||fileId==null){
			alert("此证书没有附件！");
			return;
		}
		var l = fileId.split(",").length;
		if(l>1){
			var url = $("base").attr("href")+"fileupload2/downloadPack2.do?ids="+fileId+"&name="+filename;
		}else{
			var url = $("base").attr("href")+"fileupload2/downloadByObjId2.do?id="+fileId+"&proportion=1";
		}
		
		window.location.href = url;
	}
	function viewCertFile(fileId,fileName){
		var selects = [];
		 var ids = fileId.split(",");
		 var names = fileName.split(",")
		 for (var i = 0; i < ids.length; i++) {
			 var id = [];
				id["id"]=ids[i];
				id["name"]=names[i];
				selects[selects.length]=id;
		}

			var previewData = {
				     first: 0,           //首先显示的文件序号（数组元素序号）
				     images: selects
				};
			top.$.dialog({
			     title: name,
			      width: $(top).width()-100,
			      height: $(top).height()-100,
			      resize: false,
			      max: false,
			      min: false,
			      content: "url:pub/fileupload1/fileupload/preview.jsp",
			      data: previewData
			  });
		
	}

</script>
</head>

<body style="overflow: auto;">


<div class="s_n_bg2"></div>

<div class="info_content">

	<div class="info_box">
	   <div class="tip"></div>
	   
	   <div class="box_one">
	   <div class="jbxx info_tit"> 基本信息</div>
	   <div>
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
				<tr>
					<td width="10%" class="l_info_td">姓名：</td>
					<td width="30%" class="r_info_td">${emp.empName}</td>
					<td width="10%" class="l_info_td">职称：</td>
					<td width="30%" class="r_info_td">${emp.empTitle}</td>
					<td width="20%" rowspan="7"  style="padding:5px;"><img  style="width: 140px;height: 196px;"  src="upload/${emp.empPhoto}" /></td>
				</tr>
				<tr>
					<td class="l_info_td">籍贯：</td>
					<td class="r_info_td">${emp.empNativePlace}</td>
					<td class="l_info_td">政治面貌：</td>
					<td class="r_info_td">${emp.empPolitical}</td>
					</tr>
				<tr>
					<td class="l_info_td">身份证号：</td>
					<td class="r_info_td">${fn:substring(emp.empIdCard, 0, 6)}************</td>
					<td class="l_info_td">参加工作时间： </td>
					<td class="r_info_td"> ${fn:substring(emp.joinWorkDate, 0, 10)}</td>
					</tr>
				<tr>
					<td class="l_info_td">电话：</td>
					<td class="r_info_td">${emp.mobilePhone}</td>
					<td class="l_info_td"> </td>
					<td class="r_info_td"></td>
					</tr>
				
			</table>
	   
	   
	   </div>
	   
	   
	   </div>

	  
	   <div  class="charts">
	   <c:if test="${role=='true'}">
			<div class="tubiao">
			<div class="i_icon02 info_tit"> 财务明细</div>
			
			<div class="tb_bor">
			   	<div id="div1" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="fee_bx_chart" renderAt="div1"  paramValue="${emp.id}"/>
				</div>
			
			</div>
			
			</div>
		</c:if>	
		  <c:if test="${rsrole=='true'}">	
			<div class="tubiao">
			<div class="i_icon03 info_tit"> 培训时间</div>
			
			<div class="tb_bor">
			    	<div id="div2" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="personal_px" renderAt="div2" paramValue="%${emp.id}%"/>
				</div>
			
			</div>
			
			</div>
		</c:if>	
			<%-- <div class="tubiao">
			<div class="i_icon04 info_tit"> 出具报告</div>
			
			<div class="tb_bor">
			    	<div id="div3" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="fee_bx_chart" renderAt="div3" />
				</div>
			
			</div>
			
			</div>
			
			<div class="tubiao">
			<div class="i_icon05 info_tit"> 检验设备</div>
			
			<div class="tb_bor">
			   	 	<div id="div4" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
					<chart:chart chartNum="fee_bx_chart" renderAt="div4" />
				</div>
			
			</div>
			
			</div>
			 --%>
			
			
			
	   
	   </div>
	  <c:if test="${rsrole=='true'}">
	  <c:if test="${contractsC0>0}">
	<div class="search_box">
	
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>合同信息</p></a></div>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th>合同类型</th>
						<th>合同开始日期</th>
						<th>合同结束日期</th>
						<th>试用工资</th>
						<th>转正工资</th>
					</tr>
				 	<c:forEach var="insp" items="${contracts0}" begin="0" end="${contractsC0<5?contractstC0:4}">
						   <tr>
							<td><%-- ${insp.contractType} --%>
							<%-- <c:if test="${insp.contractType=='1'}">劳动合同</c:if>
							<c:if test="${insp.contractType=='2'}">临时合同</c:if> --%>
								<c:if test="${not empty contractCode}">
				                    <c:forEach items="${contractCode}" var="code">
				                    	<c:if test="${code.value==insp.contractType}">
				                    		${code.name}
				                    	</c:if>
				                    </c:forEach>
			                    </c:if>
							</td>
							<td>${fn:substring(insp.contractStartDate, 0, 10)}</td>
							<td>${fn:substring(insp.contractStopDate, 0, 10)}</td>
							<td>${insp.trialMoney}元/月</td>
							<td>${insp.positiveMoney}元/月</td>
					</tr>
					   
					   </c:forEach>
					
				</table>

	
		   
		   
		   
		   </div>					
		</div>	
	</c:if>
	 </c:if> 
	<c:if test="${rsrole=='true'}"> 
	  <c:if test="${cartListC0>0}">
	<div class="search_box">
	
		   <div class="title">
			  <div class="icon_box"><a href="#" target="_blank"><p>持证信息</p></a></div>
		   </div>
		   <div class="Per_info">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="per_tab">
					<tr>
						<th style="text-align: center;">证书项目</th>
						<th style="text-align: center;">证书性质</th>
						<th style="width: 200px;text-align: center;">证书编号</th>
						<th style="text-align: center;">初次取证日期</th>
						<th style="text-align: center;">发证日期</th>
						<th style="width: 150px;text-align: center;">证书有效截止日期</th>
						<th style="width: 150px;text-align: center;">附件</th>
					</tr>
				 	<c:forEach var="insp" items="${cartList0}" begin="0" end="${cartListC0<5?cartListC0:4}">
						   <tr>
							<td><%-- <c:if test="${not empty cgCode}">
				                    <c:forEach items="${cgCode}" var="code">
				                    	<c:if test="${code.value==insp.cert_type}">
				                    		${code.name}
				                    	</c:if>
				                    </c:forEach>
			                    </c:if> --%>
			                    
			                    ${insp.cert_type}</td>
							<td>${insp.cert_category}</td>
							<td>${fn:substring(insp.cert_no, 0, 6)}************</td>
							<td>${fn:substring(insp.first_get_date, 0, 10)}</td>
							<td>${fn:substring(insp.cert_begin_date, 0, 10)}</td>
							<td>${fn:substring(insp.cert_end_date, 0, 10)}</td>
							<td>
							<c:if test="${insp.cert_file!=''&&insp.cert_file!=null}">
							<a href="javascript:viewCertFile('${insp.cert_file}','${insp.cert_file_name}')">预览</a>
							/<a href="javascript:downCertFile('${insp.cert_file}','${insp.id}','${emp.empName}${insp.cert_type}')">下载</a>
							</c:if>
							<c:if test="${insp.cert_file==''||insp.cert_file==null}">没有附件</c:if>
							</td>
					</tr>
					   
					   </c:forEach>
					
				</table>

	
		   
		   
		   
		   </div>					
		</div>	
		</c:if>
	</c:if>
	  	   
		   					
   </div>




</div>




     




</body>
</html>