<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>

<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备借用登记表</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
	var pageStatus="${param.status}";
	$(function () {
		queryEqyipmentlistandBox("${param.id}");
		$("#form1").initForm({ //参数设置示例
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: [{text: "确定", id: "print", icon: "print", click: print},
         	 		      {text: "关闭", icon: "cancel", click: function(){api.close();}}],
			getSuccess : function(res) {
				if(res.success){
					$("#form1").setValues({id:res.id});
					$("#form1").setValues(res.data);
				}
			}
		});
		$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
	});
	//detail页面加载设备信息
    function createJrxxGrid(data) {
        var jrxx_html = '<div class="l-grid-body l-grid-body2 l-scroll"><table class="l-detail-table b-dyn-table decimal-table"><tr class="l-t-td-title" style="height:30px;font-weight:normal">'
                          +'<td class="l-t-td-title" style="width:20%">所借设备名称</td>'
                          +'<td class="l-t-td-title" style="width:15%">规格型号</td>'
                          +'<td class="l-t-td-title" style="width:15%">产品编号</td>'
                          +'<td class="l-t-td-title" style="width:15%">设备编号</td>'
                          +'<td class="l-t-td-title" style="width:15%">单价（元）</td>'
                          +'<td class="l-t-td-title" style="width:20%">备注</td>';
        if(data && data.length > 0){
        	 for(var i=0;i<data.length;i++){ 
        		 var eq_name="";
            	 var eq_model=""; 
            	 var eq_sn="";
            	 var eq_no="";
            	 var eq_value="";
            	 var remark="";
        		  if(data[i].eq_name!=null){
        			  eq_name=data[i].eq_name;
        		  }
        		  if(data[i].eq_model!=null){
        			  eq_model=data[i].eq_model;
        		  }
        		  if(data[i].eq_sn!=null){
        			  eq_sn=data[i].eq_sn;
        		  }
        		  if(data[i].eq_no!=null){
        			  eq_no=data[i].eq_no;
        		  }
        		  if(data[i].eq_value!=null){
        			  eq_value=data[i].eq_value;
        		  }
        		  if(data[i].remark!=null){
        			  remark=data[i].remark;
        		  }
        		  
        		  
                jrxx_html += '<tr class="l-t-td-right center" style="height:30px">'
                               +'<td class="l-grid-row-cell" style="text-align:center;height:30px">'+eq_name
                               +'</td><td class="l-grid-row-cell" style="text-align:center">' +eq_model
                               +'</td><td class="l-grid-row-cell" style="text-align:center">' +eq_sn
                               +'</td><td class="l-grid-row-cell" style="text-align:center">' +eq_no
                               +'</td><td class="l-grid-row-cell" style="text-align:center">'+eq_value
                               +'</td><td class="l-grid-row-cell" style="text-align:center">'+remark
            }
        	if(data.length<9){
        		for(var j=0;j<9-data.length;j++){
        			if(j==0){
        				jrxx_html += '<tr class="l-t-td-right center" style="height:30px">'
                            +'<td class="l-grid-row-cell" style="text-align:center;height:30px">'+"以下空白"
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">'+""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">'+""
        			}else{
        				jrxx_html += '<tr class="l-t-td-right center" style="height:30px">'
                            +'<td class="l-grid-row-cell" style="text-align:center;height:30px">'+""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
                            +'</td><td class="l-grid-row-cell" style="text-align:center">' +""
        			}
        		}
        	}
        }else{
        	jrxx_html += '<tr><td colspan="6">无数据</td></tr>';
        }
        jrxx_html += '</table></div>';
        $("#device").html(jrxx_html);
    }
	//加载设备列表
	function queryEqyipmentlistandBox(id){
		$.ajax({
        	url: "equipment/Loan/detail1.do?id="+id,
            type: "POST",
            success: function (resp) {
                if (resp.success) {
                	var baseEquipment2s = resp.baseEquipment2s;
               		//detail页面加载设备
               		createJrxxGrid(baseEquipment2s);
                }else{
                	$.ligerDialog.error('提示：' + data.msg);
                }
            },
            error: function (data0,stats) {
                $.ligerDialog.error('提示：' + data.msg);
            }
        });
	}
	//打印
    function print(){
        var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
        var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
        LODOP.PRINT_INIT("打印设备借用登记表");
        LODOP.ADD_PRINT_HTM('12px', '10px', "750px", "100%", strBodyStyle+ $("#form1").html());
        LODOP.SET_PRINT_PAGESIZE (1, 0, 0,"A4");
        //LODOP.PREVIEW();
        LODOP.PRINT();
     }
</script>
<style type="text/css" media="print" id="pstyle">
* {font-family:"宋体";font-size:15px;letter-spacing:normal;}
table{ margin:-2 auto;width: 650px;}
table td{ height:40px}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
.l-detail-table {
    padding:0px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
.check {
    width:665px;
}
.l-t-td-title{ text-align:center;}
.l-t-td-left{ text-align:center;}
.l-t-td-right{ padding-left:5px;}
</style>
</head>
<body style="background:white;">
	<form name="form1" id="form1" method="post" action="equipment/Loan/saveEqui.do?pageStatus=${param.status}"
		getAction="equipment/Loan/detail.do?id=${param.id}">
		<input name="id" id="id" type="hidden" value="${param.id }"/>	
		<table cellpadding="1">
             <tr>
                 <td class="l-t-td-left" style="width:27%">文件编号：CTJC-005-B16</td>
                 <td class="l-t-td-left"></td>
                 <td class="l-t-td-left"></td>
                 <td class="l-t-td-left"></td>
                 <td class="l-t-td-left"></td>
                 <td class="l-t-td-left" style="width:27%">实施日期：2014年7月1日</td>
                </tr>
            </table>
		<h2 style="margin-left: 36%;font-size:25px;">仪器设备借用登记表</h2>
		<table class="check">
             <tr>
                 <td width="70%">&nbsp;</td>
                 <td width="80px" align="center">编号：</td>
                 <td  style="width: 130px;"   class="l-t-td-right"><input ltype='text' type="text" name="loanNo" id="loanNo" readonly="readonly"/></td>
                </tr>
            </table>
			<table cellpadding="3" cellspacing="0" class="l-detail-table" id="lcsq">
				<tr>	
					<td class="l-t-td-left">借用部门</td>
					<td class="l-t-td-right" colspan="2">
						<input  validate="{maxlength:50,required:true}" value="" readonly="readonly" ltype="text"  name="depName" id="depName"  type="text"/>
					</td>
					<td class="l-t-td-left">部门负责人</td>
					<td class="l-t-td-right" colspan="2"><input type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">借用日期</td>
					<td class="l-t-td-right"><input name="loanTime" id="loanTime" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="" /></td>	
					<td class="l-t-td-left" id="requiredDiv" >预计归还日期</td>
					<td class="l-t-td-right" id="requiredDiv2" ><input name="repayTime" id="repayTime" type="text" ltype='date'  ligerui="{format:'yyyy-MM-dd'}"  /></td>	
					<td class="l-t-td-left" id="realrequiredDiv" >归还日期</td>
					<td class="l-t-td-right" id="realrequiredDiv2" ><input name="realrepayTime" id="realrepayTime" type="text" ltype='date'  ligerui="{format:'yyyy-MM-dd'}"  /></td>
				</tr>
				</table>
				<div id="device"></div>
				<table cellpadding="3" cellspacing="0" class="l-detail-table" id="lcsq">
				<tr>
					<td class="l-t-td-left" style="width:20%">设备借用用途</td>
					<td class="l-t-td-right" colspan="5"><textarea name="reason" rows="2" cols="25" class="l-textarea" validate="{required:true,maxlength:1024}"></textarea></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">保障部意见</td>
					<td class="l-t-td-right" colspan="5"><textarea class="l-textarea" validate="{maxlength:1024}"></textarea></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">院领导审批</td>
					<td class="l-t-td-right" colspan="5"><textarea class="l-textarea" validate="{maxlength:1024}"></textarea></td>				
				</tr>
				<tr>
					<td class="l-t-td-left" rowspan="2">设备借出时状况<br />（借用人填写）</td>
					<td class="l-t-td-right" rowspan="2" colspan="3"><textarea class="l-textarea" validate="{maxlength:1024}"></textarea></td>	
					<td class="l-t-td-left">借用人</td>
					<td class="l-t-td-right">
						<input type="text" ltype='text'/>
					</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">设备管理员</td>
					<td class="l-t-td-right">
						<input type="text" ltype='text'/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left" rowspan="2">设备归还时状况<br />（设备管理员填写）</td>
					<td class="l-t-td-right" rowspan="2" colspan="3"><textarea class="l-textarea" validate="{maxlength:1024}"></textarea></td>	
					<td class="l-t-td-left">借用人</td>
					<td class="l-t-td-right">
						<input type="text" ltype='text' />
					</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">设备管理员</td>
					<td class="l-t-td-right">
						<input type="text" ltype='text'/>
					</td>
				</tr>
			</table>
			<table class="check">
             <tr>
                 <td align="left" colspan="6">备注：借用人在设备外借期间必须按规程操作仪器设备，自觉进行仪器设备的正常维护，保证所借仪器设备完好无损，用后及时归还。如有损坏或丢失，需进行维修或赔偿。</td>
                </tr>
            </table>
	</form>
</body>
</html>