<%@page import="com.lsts.report.bean.SysOrg"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.scts.payment.web.InspectionPayInfoAction"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.scts.payment.bean.InspectionPayInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.lsts.finance.bean.CwBankDTO"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.khnt.utils.StringUtil" %>
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
	var aaa="${param.pathFile}";
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
	  			$("div table tr td img").attr("src","");
	  		}
	  	});
	})

	function prn_preview() {
		CreateOneFormPage();
		LODOP.PREVIEW();
	};

	function prn_print() {
		CreateOneFormPage();
		LODOP.PRINT();
		close();
	};

	function CreateOneFormPage(){
			// 获取打印对象
			LODOP=getLodop();
			// 设置打印样式
			var strBodyStyle="<style> table,td { border: 0 solid #000000;border-collapse:collapse;font-size:12px } "+
			"</style>";
            //strBodyStyle = "";
			// 设置打印方式
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0,"B5");	// 1 纵向打印 2 横向打印

			var printReportContent = "";
			<%
			InspectionPayInfo inspectionPayInfo = (InspectionPayInfo)request.getAttribute("inspectionPayInfo");
			List<InspectionInfoDTO>  inspectionInfoDTOList = inspectionPayInfo.getInspectionInfoDTOList();
			%>

			var inspectionInfoDTOList = "<%=inspectionInfoDTOList%>";
			if(inspectionInfoDTOList==""){
				alert("选择的检验业务没得缴费信息，请重新选择！");
				api.close();
			}
			
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
			if(StringUtils.isNotEmpty(kpdw)){
			    kpdw = kpdw.replaceAll("　", "").trim();;
			}else{
			    kpdw = "";
			}
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
                    printReportContent +="<tr align='left'><td colspan='2'>部门负责人：</td><td colspan='2'>监检人员：</td><td colspan='3'><table><tr><td>经办人签字：</td><td><c:if test='${not empty param.pathFile}'><img style='width:160px;vertical-align: text-bottom;'  src='${param.pathFile}' /></c:if></td></tr></table></td></tr>\n";
					//printReportContent += "<tr align='left' height='20px'><td colspan='2'>部门负责人：</td><td colspan='2'>监检人员：</td><td width='250' style='text-align:left;font-size:13px;display:block;' colspan='3'><div style='float: left;display:block;line-height: 48px'>经办人签字：</div><c:if test='${not empty param.pathFile}'><img style='width:160px;vertical-align: text-bottom;'  src='${param.pathFile}' /></c:if></td></tr>";
					printReportContent += "<tr align='left' height='20px'><td colspan='2'>日期：</td><td colspan='2'>日期：</td><td colspan='3'>电话：<%=inspectionPayInfo.getTel()==null?"":inspectionPayInfo.getTel()%></td></tr>";

                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getCash_pay())&&Float.parseFloat(inspectionPayInfo.getCash_pay())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">现金：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getCash_pay()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getRemark())&&Float.parseFloat(inspectionPayInfo.getRemark())>0){
                        String innerHtml = inspectionPayInfo.getRemark() + "元";
                        List<CwBankDTO> cwBankDTOList = inspectionPayInfo.getCwBankDTOList();
                        boolean diffDw = false;
                        if(!cwBankDTOList.isEmpty()){
                            for(CwBankDTO cwBankDTO : cwBankDTOList){
                                if(cwBankDTO != null){
                                    String zzdw = cwBankDTO.getAccountName();
                                    if(StringUtil.isNotEmpty(zzdw)){
                                        zzdw = zzdw.replaceAll("　", "").trim();
                                    }else{
                                        zzdw = "";
                                    }
                                    if(!kpdw.equals(zzdw)){
                                        diffDw = true;
                                    }
                                    if(cwBankDTO.getJyTime() != null){
                                        innerHtml +=" 冲" + DateUtil.getDateTime("yyyy-MM-dd", cwBankDTO.getJyTime());
                                    }
                                }
                            }
                            if(diffDw){
                                innerHtml +="<font size=\"4\">▲</font>";
                            }
                        }
                    %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">转账：</div><div style="float:right;margin-right: 50px"><%=innerHtml%></div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getPos())&&Float.parseFloat(inspectionPayInfo.getPos())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">POS：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getPos()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getHand_in())&&Float.parseFloat(inspectionPayInfo.getHand_in())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">上缴财政：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getHand_in()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getDraft())&&Float.parseFloat(inspectionPayInfo.getDraft())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">承兑汇票：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getDraft()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getAlipay_money())&&Float.parseFloat(inspectionPayInfo.getAlipay_money())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">支付宝：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getAlipay_money()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getWeixin_money())&&Float.parseFloat(inspectionPayInfo.getWeixin_money())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">微信：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getWeixin_money()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getAlipay_mobile())&&Float.parseFloat(inspectionPayInfo.getAlipay_mobile())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">平板(支付宝)：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getAlipay_mobile()%>元</div></td> </tr>';
                    <%}%>
                    <% if(StringUtil.isNotEmpty(inspectionPayInfo.getWechat_mobile())&&Float.parseFloat(inspectionPayInfo.getWechat_mobile())>0){ %>
                    printReportContent += '<tr align="left"><td colspan="3"></td><td colspan="3"></td> <td><div style="float:left">平板(微信)：</div><div style="float:right;margin-right: 50px"><%=inspectionPayInfo.getWechat_mobile()%>元</div></td> </tr>';
                    <%}%>
					//printReportContent += "</b></tr>  ";
                    printReportContent +=" <tr align='left'>" +
                        "       <td colspan='7' style='text-align:right;border:none;'>" +
                        "       <div style='text-align:center;padding-right:30px'>（<%=inspectionInfoDTO.getCheck_department()%>）<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %></div>" +
                        "       </td>" +
                        "       </tr>";
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
		if("1" == "${param.type}") {
            api.data.pwindow.api.close();
        }
		api.close();
	}
</script>
</head>
<body >
<form id="form1" style="height:99%;">
<div style="overflow:hidden;text-align:center" id='printPaymentDiv'>
	<table width="800" cellpadding="6" cellspacing="0" align="center" border="0" id="table">
		<caption>
		<img src='payment/payInfo/showTwoDim.do?id=${param.id }'>
		<center><font size="3"><b>四川省特种设备检验研究院<br/>检验收费结算单</b></font></center></caption>
       	<c:forEach items="${inspectionPayInfo.inspectionInfoDTOList}" var="data" varStatus="status">
       		<c:if test="${status.index + 1 == 1}">
       		<tr align="center">
       			<td>受检单位</td>
         		<td colspan="4"><c:out value="${data.com_name}"></c:out></td>
         		<td>编号</td>
              	<td><c:out value="${inspectionPayInfo.pay_no}"></c:out></td>
       		</tr>
       		<tr align="center">
       			<td>开票单位</td>
         		<td colspan="4"><c:out value="${inspectionPayInfo.company_name}"></c:out></td>
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
        	<c:if test="${fn:length(inspectionPayInfo.inspectionInfoDTOList) == (status.index + 1)}">
        	<tr>
       			<td align="center">合计</td>
         		<td>共<c:out value="${fn:length(inspectionPayInfo.inspectionInfoDTOList)}"></c:out>份报告</td>
              	<td colspan="3"></td>
              	<td colspan="2"><c:out value="${inspectionPayInfo.pay_received}"></c:out></td>
       		</tr>
       		<tr>
       			<td align="center">发票编号</td>
         		<td colspan="6" align="left"><c:out value="${inspectionPayInfo.invoice_no}"></c:out></td>
       		</tr>

       		<tr align="left">
                <td colspan="2">部门负责人：</td>
         		<td colspan="2">监检人员：
                </td>
                <td style="font-size:13px" colspan="3">
                    <table>
                        <tr>
                            <td  >经办人签字：</td>
                            <td><img style="width:160px;vertical-align: text-bottom;" src="" /></td>
                        </tr>
                    </table>
                </td>
       		</tr>
       		<tr align="left">
         		<td colspan="2">日期：</td>
         		<td colspan="2">日期：</td>
         		<td style="font-size:13px" colspan="3">电话：<c:out value="${inspectionPayInfo.tel}"></c:out></td>
       		</tr>
        <c:if test="${inspectionPayInfo.cash_pay ne null && inspectionPayInfo.cash_pay+0 ne '0'}">
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">现金：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.cash_pay}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.remark ne null && inspectionPayInfo.remark ne '0'  }"><!-- 2：转账 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">转账：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.remark}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.pos ne null && inspectionPayInfo.pos+0 ne '0'}"><!-- 5：POS机刷卡 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">POS：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.pos}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.hand_in ne null && inspectionPayInfo.hand_in+0 ne '0' }"><!-- 8：上缴财政 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">上缴财政：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.hand_in}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.draft ne null&&inspectionPayInfo.draft+0 ne '0' }"><!-- 8：承兑汇票 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">承兑汇票：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.draft}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.alipay_money ne null && inspectionPayInfo.alipay_money+0 ne '0' }"><!-- 8：支付宝 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">支付宝：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.alipay_money}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.weixin_money ne null && inspectionPayInfo.weixin_money+0 ne '0' }"><!-- 8：微信 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">微信：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.weixin_money}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.alipay_mobile ne null && inspectionPayInfo.alipay_mobile+0 ne '0' }"><!-- 12：平板支付 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">平板(支付宝)：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.alipay_mobile}"></c:out>元</div></td>
            </tr>
        </c:if>
        <c:if test="${inspectionPayInfo.wechat_mobile ne '0' &&  inspectionPayInfo.wechat_mobile ne null}"><!-- 12：平板支付 -->
            <tr align="left">
                <td colspan="3"></td>
                <td colspan="3"></td>
                <td ><div style="float:left">平板(微信)：</div><div style="float:right;margin-right: 50px"><c:out value="${inspectionPayInfo.wechat_mobile}"></c:out>元</div></td>
            </tr>
        </c:if>
       		<tr align="left">
       			<td colspan="7" style="text-align:right;border:none;">
       				<div style="text-align:center;padding-right:30px">（<c:out value="${data.check_department}"></c:out>）<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %></div>
       			</td>
       		</tr>
        	</c:if>
    	</c:forEach>
	</table>
</div>
</form>
</body>
</html>