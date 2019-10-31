<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.scts.payment.bean.InspectionPayInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.lsts.finance.bean.CwBankDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<style type="text/css">
#table td{border:0px black solid}
</style>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '打印预览',
				id : 'prn_preview',
				icon : 'print',
				click:prn_preview
			}, {
				text : '打印',
				id : 'prn_print',
				icon : 'print',
				click : prn_print
			}, {
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
				
			}
		});
		//单独处理项,签名
	  	$.post("payment/payInfo/getPayInfoSign.do",{id:"${param.id}"},function(res){
	  		if(res.success){
	  			//alert(res.image);
	  			//签名
	  			$("div table tr td img").attr("src",res.image);
	  		}else{
	  			//alert(res.msg);
	  			$("div table tr td image").attr("src","");
	  		}
	  	});
	})
	
	function prn_preview() {	
		//单独处理项,签名
	  	$.post("payment/payInfo/getPayInfoSign.do",{id:"${param.id}"},function(res){
	  		if(res.success){
	  			var imageFile=res.image;
	  			CreateOneFormPage(imageFile);	
	  			LODOP.PREVIEW();	
	  		}else{
	  			var imageFile=""
	  			CreateOneFormPage(imageFile);	
	  			LODOP.PREVIEW();	
	  		}
	  	});
			
	};
	
	function prn_print() {		
		//单独处理项,签名
	  	$.post("payment/payInfo/getPayInfoSign.do",{id:"${param.id}"},function(res){
	  		if(res.success){
	  			var imageFile=res.image;
	  			CreateOneFormPage(imageFile);
	  			LODOP.PRINT();
	  			close();	
	  		}else{
	  			var imageFile=""
	  			CreateOneFormPage(imageFile);
	  			LODOP.PRINT();
	  			close();	
	  		}
	  	});
		
	};
	
	function CreateOneFormPage(imageFile){
			// 获取打印对象
			LODOP=getLodop();
			// 设置打印样式  
			var strBodyStyle="<style> table{border:2;text-align:center;margin-left:60px;} table,td { border: 0 solid #000000;border-collapse:collapse;font-size:12px } "+
			"</style>";
			// 设置打印方式
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0,"B5");	// 1 纵向打印 2 横向打印
			
			var printReportContent = "";
			<%
			InspectionPayInfo inspectionPayInfo = (InspectionPayInfo)request.getSession().getAttribute("inspectionPayInfo");
			List<InspectionInfoDTO>  inspectionInfoDTOList = inspectionPayInfo.getInspectionInfoDTOList();
			
			%>
				
			<%
			// 收费明细小于6行不显示页码
			if(inspectionInfoDTOList.size()>8){
				%>
				// 设置打印页码
				var pageHtml = "<font style='font-size:12px' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>";
				// 打印页码（上边距、左边距、宽、高）
				LODOP.ADD_PRINT_HTM(0,550,300,20,pageHtml);			
				// 循环上一命令（页码）
				LODOP.SET_PRINT_STYLEA(0,"ItemType",1);	
				<%
			}
			
			String kpdw = inspectionPayInfo.getCompany_name();
			kpdw = new String(kpdw).trim();
			kpdw = kpdw.replaceAll("　", "");
			for(int i=0;i<inspectionInfoDTOList.size();i++){
				InspectionInfoDTO inspectionInfoDTO = inspectionInfoDTOList.get(i);
				if(i==0){
					%>
					printReportContent += "<table width='600' cellpadding='0' cellspacing='0' align='center' border='0' id='table'>";
					printReportContent += "<caption><center><font size='3'><b>四川省特种设备检验研究院<br/>检验收费结算单</b></font></center></caption>";
					printReportContent += "<tr align='center' height='20px'><td width='60'>受检单位</td>";
					printReportContent += "<td colspan='4'><%=inspectionInfoDTO.getCom_name()%></td>";
					printReportContent += "<td>编号</td><td><%=inspectionPayInfo.getPay_no()%></td></tr>";
					printReportContent += "<tr align='center' width='60' height='20px'><td>开票单位</td>";
					printReportContent += "<td colspan='4'><%=kpdw %></td>";
					printReportContent += "<td>检验部门</td><td><%=inspectionInfoDTO.getCheck_department()%></td></tr>";
					printReportContent += "<tr align='center' height='20px'><td width='60'>序号</td><td>报告/证书编号</td><td colspan='3'>设备注册代码</td><td colspan='2'>检验费（元）/台</td></tr>";
					<%
				}
				%>
				if(printReportContent == ""){
					printReportContent+="<table width='600' cellpadding='0' cellspacing='0' align='center' border='0' id='table'>";
				}
				printReportContent += "<tr align='center'><td align='center' width='60'><%=(i+1)%></td><td align='center' width='110'><%=inspectionInfoDTO.getReport_sn()%></td><td width='185' colspan='3'><%=inspectionInfoDTO.getDevice_registration_code() %></td><td colspan='2' width='150'><%=inspectionInfoDTO.getAdvance_fees()%></td></tr>";
				<%
					if((i==15) || i==39 || i==63 || i==87 || i==111 || i==135 ){
						%>
						doPrintPage(strBodyStyle, printReportContent);
						printReportContent = "";
						<%
					}else{
						if((inspectionInfoDTOList.size()>8 && inspectionInfoDTOList.size()<16)
								|| (inspectionInfoDTOList.size()>33 && inspectionInfoDTOList.size()<40) 
								|| (inspectionInfoDTOList.size()>57 && inspectionInfoDTOList.size()<64)
								|| (inspectionInfoDTOList.size()>81 && inspectionInfoDTOList.size()<88)
								|| (inspectionInfoDTOList.size()>105 && inspectionInfoDTOList.size()<112)
								|| (inspectionInfoDTOList.size()>129 && inspectionInfoDTOList.size()<136)
								//|| (inspectionInfoDTOList.size()>131 && inspectionInfoDTOList.size()<135)
								//|| (inspectionInfoDTOList.size()>102 && inspectionInfoDTOList.size()<107)
								//|| (inspectionInfoDTOList.size()>116 && inspectionInfoDTOList.size()<121)
								){
							if(i==(inspectionInfoDTOList.size()-1)){
								%>
								doPrintPage(strBodyStyle, printReportContent);
								printReportContent = "";
								<%
							}
						}
					}

				if((i+1) == inspectionInfoDTOList.size()){
					%>
					if(printReportContent == ""){
						printReportContent = "<table width='600' cellpadding='0' cellspacing='0' align='center' border='0' id='table'>";
					}
					printReportContent += "<tr height='20px'><td align='center'>合计</td><td>共<%=inspectionInfoDTOList.size()%>份报告</td><td colspan='3'></td><td colspan='2'><%=inspectionPayInfo.getPay_received()%></td></tr>";
					printReportContent += "<tr height='20px'><td align='center' width='60'>发票编号</td><td colspan='6' align='left'><%=inspectionPayInfo.getInvoice_no()%></td></tr>";
					
					//printReportContent += "</tr>";
					printReportContent += "<tr align='left' height='20px'><td colspan='2'>部门负责人：</td><td colspan='2'>检验人员：</td><td  width='250' style='text-align:left;font-size:13px;display:block;' colspan='3'><b style='float: left;display:block;line-height: 48px'>经办人签字：</b><c:if test='${not empty param.pathFile}'><img style='width:160px;vertical-align: text-bottom;'  src='${param.pathFile}' /></c:if></td></tr>";
					printReportContent += "<tr align='left' height='20px'><td colspan='2'>日期：</td><td colspan='2'>日期：</td><td style='font-size:13px;padding-right:150px' colspan='3'><b>电话：</b><%=inspectionPayInfo.getTel()%></td></tr>";
					
					printReportContent += "<tr height='10px'><td><div align='left' style=' position:absolute; top:320px;right:20px;bottom:20px;left:550px'><b>";

						<%
						if("1".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "现金: <%=inspectionPayInfo.getPay_received() %>元</td>";
							<%
						}else if("2".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "转账:<%=inspectionPayInfo.getRemark()%>元";
							<%
								List<CwBankDTO> cwBankDTOList = inspectionPayInfo.getCwBankDTOList();
								boolean diffDw = false;
								if(!cwBankDTOList.isEmpty()){
									for(CwBankDTO cwBankDTO : cwBankDTOList){
										if(cwBankDTO != null){
											String zzdw = cwBankDTO.getAccountName();
											zzdw = new String(zzdw).trim();
											zzdw = zzdw.replaceAll("　", "");
											if(!kpdw.equals(zzdw)){
												diffDw = true;
											}
											if(cwBankDTO.getJyTime() != null){
												%>
												printReportContent += " 冲<%=DateUtil.getDateTime("yyyy-MM-dd", cwBankDTO.getJyTime()) %></br>";
												<%
											}
										}
										
									}
								}
								if(diffDw){
									%>
									printReportContent += "<font size='4'>▲</font>";
									<%
								}
							%>
							printReportContent += "</td>";
							<%
						}else if("4".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "现金: <%=inspectionPayInfo.getCash_pay() %>元</br>转账:<%=inspectionPayInfo.getRemark() %>元";
							<%
								List<CwBankDTO> cwBankDTOList = inspectionPayInfo.getCwBankDTOList();
								boolean diffDw = false;
								if(!cwBankDTOList.isEmpty()){
									for(CwBankDTO cwBankDTO : cwBankDTOList){
										if(cwBankDTO != null){
											String zzdw = cwBankDTO.getAccountName();
											zzdw = new String(zzdw).trim();
											zzdw = zzdw.replaceAll("　", "");
											if(!kpdw.equals(zzdw)){
												diffDw = true;
											}
											if(cwBankDTO.getJyTime() != null){
												%>
												printReportContent += " 冲<%=DateUtil.getDateTime("yyyy-MM-dd", cwBankDTO.getJyTime()) %></br>";
												<%
											}
										}
										
									}
								}
								if(diffDw){
									%>
									printReportContent += "<font size='4'>▲</font>";
									<%
								}
							%>
							printReportContent += "</td>";
							<%
						}else if("5".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "POS: <%=inspectionPayInfo.getPay_received() %>元</td>";
							<%
						}else if("6".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "现金: <%=inspectionPayInfo.getCash_pay() %>元   </br>POS:  <%=inspectionPayInfo.getPos() %>元</td>";
							<%
						}else if("7".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "POS:<%=inspectionPayInfo.getPos() %>元   </br>转账:  <%=inspectionPayInfo.getRemark() %>元";
							<%
								List<CwBankDTO> cwBankDTOList = inspectionPayInfo.getCwBankDTOList();
								boolean diffDw = false;
								if(!cwBankDTOList.isEmpty()){
									for(CwBankDTO cwBankDTO : cwBankDTOList){
										if(cwBankDTO != null){
											String zzdw = cwBankDTO.getAccountName();
											zzdw = new String(zzdw).trim();
											zzdw = zzdw.replaceAll("　", "");
											if(!kpdw.equals(zzdw)){
												diffDw = true;
											}
											if(cwBankDTO.getJyTime() != null){
												%>
												printReportContent += " 冲<%=DateUtil.getDateTime("yyyy-MM-dd", cwBankDTO.getJyTime()) %></br>";
												<%
											}
										}
										
									}
								}
								if(diffDw){
									%>
									printReportContent += "<font size='4'>▲</font>";
									<%
								}
							%>
							printReportContent += "</td>";
							<%
						}else if("8".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "上缴财政: <%=inspectionPayInfo.getPay_received() %>元</td>";
							<%
						}else if("9".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "承兑汇票: <%=inspectionPayInfo.getPay_received() %>元</td>";
							<%
						}else if("12".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "平板(支付宝): <%=inspectionPayInfo.getAlipay_mobile() %>元</td>";
							<%
						}else if("13".equals(inspectionPayInfo.getPay_type())){
							%>
							printReportContent += "平板(微信): <%=inspectionPayInfo.getWechat_mobile() %>元</td>";
							<%
						}
						
					%>
					printReportContent += "</b></tr>  ";

					printReportContent += "<tr align='center' height='10px'><td colspan='7' style='text-align:center;border:none;'><div align='center' style=' position:absolute; top:380px;right:200px;bottom:20px;left:200px'>（<%=inspectionInfoDTO.getCheck_department()%>）<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %></div></td></tr></table>";
					

					// 获取打印内容
					var strFormHtml=strBodyStyle+"<body>"+printReportContent+"</body>";
					LODOP.NewPage();	// 强制分页
					// 打印表格（上边距、左边距、宽、高、打印内容）
					LODOP.ADD_PRINT_TABLE(50,0,"100%","100%",strFormHtml);	


					//固定格式
				


					<%
				}
			}
		%>
	};
	
	function doPrintPage(strBodyStyle, printReportContent){
		// 获取打印内容
		if(printReportContent!=""){
			printReportContent = printReportContent+"</table>";
		}
		var strFormHtml=strBodyStyle+"<body>"+printReportContent+"</body>";
		LODOP.NewPage();	// 强制分页
		LODOP.ADD_PRINT_TABLE(50,0,"100%","100%",strFormHtml);
	}
	
	function close(){
		api.close();
	}
</script>
<style type="text/css">
	/* table tr td image {
	 position:absolute;
	 right:102px; /*左右位置*/
	 width:100px;
	 height:100px;
	 z-index:1;
	} */
</style>
</head>
<body >
<form id="form1" style="height:99%;">
<div style="overflow:hidden;text-align:center" id='printPaymentDiv'>
	<table width="800" cellpadding="6" cellspacing="0" align="center" border="0" id="table">
		<caption>
		<br />
		<center><font size="3"><b>四川省特种设备检验研究院<br/>检验收费结算单</b></font></center></caption>
       	<c:forEach items="${sessionScope.inspectionPayInfo.inspectionInfoDTOList}" var="data" varStatus="status">  
       		<c:if test="${status.index + 1 == 1}">
       		<tr align="center">
       			<td>受检单位</td>
         		<td colspan="4"><c:out value="${data.com_name}"></c:out></td>
         		<td>编号</td>
              	<td><c:out value="${sessionScope.inspectionPayInfo.pay_no}"></c:out></td>
       		</tr>	
       		<tr align="center">
       			<td>开票单位</td>
         		<td colspan="4"><c:out value="${sessionScope.inspectionPayInfo.company_name}"></c:out></td>
         		<td>检验部门</td>
              	<td><c:out value="${data.check_department}"></c:out></td>
       		</tr>	
       		<tr align="center">
       			<td>序号</td>
       			<td>报告/证书编号</td>
       			<td colspan="3">设备注册代码</td>
       			<td colspan="2">检验费（元）/台</td>
       		</tr> 
       		</c:if>   
         	<tr align="center">
            	<td><c:out value="${status.index + 1}"></c:out></td>  
           		<td>${data.report_sn}</td>
               	<td colspan="3">${data.device_registration_code}</td>
              	<td colspan="2">${data.advance_fees}</td>
        	</tr>
        	<c:if test="${fn:length(sessionScope.inspectionPayInfo.inspectionInfoDTOList) == (status.index + 1)}">
        	<tr>
       			<td align="center">合计</td>
         		<td>共<c:out value="${fn:length(sessionScope.inspectionPayInfo.inspectionInfoDTOList)}"></c:out>份报告</td>
              	<td colspan="3"></td>
              	<td colspan="2"><c:out value="${sessionScope.inspectionPayInfo.pay_received}"></c:out></td>
       		</tr>	
       		<tr>
       			<td align="center">发票编号</td>
         		<td colspan="6" align="left"><c:out value="${sessionScope.inspectionPayInfo.invoice_no}"></c:out></td>
       		</tr>	
       		<tr align="left">
       			<c:choose>
	       			<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '1'}"><!-- 1：现金 -->
		       			<td width='50'>现金</td>
		         		<td width='120'><c:out value="${sessionScope.inspectionPayInfo.pay_received}"></c:out>元</td>
		         		<td width='50'>POS</td>
		              	<td width='100'></td>
		              	<td width='50'>转账</td>
		              	<td width='100'></td>
		              	<td width='250' style="text-align:left;font-size:13px">
	              			<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
		              	<!-- <td width='100'></td> -->
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '2'}"><!-- 2：转账 -->
		       			<td width='50'>现金</td>
		         		<td width='120'></td>
		         		<td width='50'>POS</td>
		              	<td width='100'></td>
		         		<td width='50'>转账</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.remark}"></c:out></td><!-- 转账（只显示备注内容，不显示转账金额） -- 2014-09-23修改 -->
		              	<td width='250' style="text-align:left;font-size:13px">
							<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
						</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '4'}"><!-- 4：现金及转账 -->
		       			<td width='50'>现金</td>
		         		<td width='120'><c:out value="${sessionScope.inspectionPayInfo.cash_pay}"></c:out>元</td>
		              	<td width='50'>POS</td>
		              	<td width='100'></td>
		              	<td width='50'>转账</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.remark}"></c:out>元</td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '5'}"><!-- 5：POS机刷卡 -->
		       			<td width='50'>现金</td>
		         		<td width='120'></td>
		              	<td width='50'>POS</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.pay_received}"></c:out>元</td>
		              	<td width='50'>转账</td>
		              	<td width='100'></td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '6'}"><!-- 6：现金及POS机刷卡 -->
		       			<td width='50'>现金</td>
		         		<td width='120'><c:out value="${sessionScope.inspectionPayInfo.cash_pay}"></c:out>元</td>
		              	<td width='50'>POS</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.pos}"></c:out>元</td>
		              	<td width='50'>转账</td>
		              	<td width='100'></td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '7'}"><!-- 7：转账及POS机刷卡 -->
		       			<td width='50'>现金</td>
		         		<td width='120'></td>
		              	<td width='50'>POS</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.pos}"></c:out>元</td>
		              	<td width='50'>转账</td>
		              	<td width='100'><c:out value="${sessionScope.inspectionPayInfo.remark}"></c:out>元</td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '12'}"><!-- 12：平板支付 -->
		       			<td width='50'>平板(支付宝)</td>
		         		<td width='120'><c:out value="${sessionScope.inspectionPayInfo.alipay_mobile}"></c:out>元</td>
		              	<td width='50'>POS</td>
		              	<td width='100'></td>
		              	<td width='50'>转账</td>
		              	<td width='100'></td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
	              	<c:when test="${sessionScope.inspectionPayInfo.pay_type eq '13'}"><!-- 12：平板支付 -->
		       			<td width='50'>平板(微信)</td>
		         		<td width='120'><c:out value="${sessionScope.inspectionPayInfo.wechat_mobile}"></c:out>元</td>
		              	<td width='50'>POS</td>
		              	<td width='100'></td>
		              	<td width='50'>转账</td>
		              	<td width='100'></td>
		              	<td width='250' style="text-align:left;font-size:13px">
		              		<b style="float: left;display:block;line-height: 48px">经办人签字：</b>
	              			<img style="width:160px;vertical-align: text-bottom;" src="" />
		              	</td>
	              	</c:when>
       			</c:choose>
       		</tr>	
       		<tr align="left">
       			<td colspan="3">部门负责人：</td>
         		<td colspan="3">监检人员：</td>
         		<td ></td>
       		</tr>	
       		<tr align="left">
         		<td colspan="3">日期：</td>
         		<td colspan="3">日期：</td>
         		<td style="font-size:13px"><b>电话：</b><c:out value="${sessionScope.inspectionPayInfo.tel}"></c:out></td>
       		</tr>	
       		<tr align="right">
       			<td colspan="7" style="text-align:right;border:none;">
       				<div style="text-align:right;padding-right:30px">（<c:out value="${data.check_department}"></c:out>）<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %></div>
       			</td>
       		</tr>	
        	</c:if>
    	</c:forEach>	
	</table>
</div>
</form>
</body>
</html>