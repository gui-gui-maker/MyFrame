<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
   	<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String nowTime=""; 
				nowTime = dateFormat.format(new Date());%>
  <title></title>
     <%@include file="/k/kui-base-form.jsp" %>
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var isChecked="${param.isChecked}";
    var abc="abc";   
		$(function () {
			//$("#xxx").hide();
			$("#form1").transform("detail");
			$("#form2").transform("add");
   	    //$("#hk").setValues("money/borrow/detail.do?id=${param.id}");
    	//$("#areaFlag").val(areaFlag);
        //if ("${param.pageStatus}"=="detail"){
        tbar=[{ text: '打印', id: 'print', icon: 'print', click: print},
              { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
        ];
        
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            getSuccess : function(res) {
                var zje=res.data.import_money;
                var money = zje.toString();
                var temp = money.split(".");
                if(temp[0].length>0)
                $("#yuan").html(temp[0].charAt(temp[0].length-1));
                if(temp[0].length>1)
                $("#shi").html(temp[0].charAt(temp[0].length-2));
                if(temp[0].length>2)
                $("#bai").html(temp[0].charAt(temp[0].length-3));
                if(temp[0].length>3)
                $("#qian").html(temp[0].charAt(temp[0].length-4));
                if(temp[0].length>4)
                $("#wan").html(temp[0].charAt(temp[0].length-5));
                if(temp[0].length>5)
                $("#shiwan").html(temp[0].charAt(temp[0].length-6));
                
               
                
                if(temp[1].length>0)
                    $("#jiao").html(temp[1].charAt(0));
                    if(temp[1].length>1)
                    $("#fen").html(temp[1].charAt(1));
            }
    	});
    	 $("#form2").initForm({
     		showToolbar: true,
     		toolbarPosition: "bottom",
     		toolbar: [{ text: '通过', id: 'add', icon: 'add', click: function(){
     			var formData = $("#form2").getValues();
     			var cwYijian=$.ligerui.toJSON(formData);
     			 top.$.ajax({
     	            url: "cw/yijian/save12.do?abc="+abc,
     	            type: "POST",
     	            data:"&cwYijian="+cwYijian,
     	            dataType:'json',
     	            success:function (data) {
     	                if(data.success){
     	                    Qm.refreshGrid();//刷新
     	                }else{
     	                    //$.ligerDialog.alert(data.msg);
     	                	top.$.notice(data.msg,3);  
     	                }
     	             },
     	             error:function () {
     	                 $.ligerDialog.warn("保存失败！");
     	             }
     			});
   			}},{ text: '不通过', id: 'add', icon: 'add', click: function(){
     			var formData = $("#form2").getValues();
     			var cwYijian=$.ligerui.toJSON(formData);
     			 top.$.ajax({
     	            url: "cw/yijian/save12.do?abc="+tbar,
     	            type: "POST",
     	            data:"&cwYijian="+cwYijian,
     	            dataType:'json',
     	            success:function (data) {
     	                if(data.success){
     	                    Qm.refreshGrid();//刷新
     	                }else{
     	                    //$.ligerDialog.alert(data.msg);
     	                	top.$.notice(data.msg,3);  
     	                }
     	             },
     	             error:function () {
     	                 $.ligerDialog.warn("保存失败！");
     	             }
     			});
   			}},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}],
     		getSuccess : function(res) {
     			
     		}
     	});
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    });
    function print(){
       var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
       var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
       LODOP.PRINT_INIT("打印还款单");
       LODOP.ADD_PRINT_HTM('0mm', '6mm', "160mm", "280mm", strBodyStyle + $("#form1").html());
       LODOP.PRINT();
    }
    function tj(){
    	
    }

    </script>
    
    <style type="text/css" media="print" id="pstyle">
body,input,button,select,textarea {font-family:"宋体";font-size:12px;font-weight: bold;}
.l-detail-table td, .l-detail-table, .l-table td, .l-table {
    border-collapse: collapse;
    border: 1px solid #d2e0f1;
}

.l-table {
    width: 100%;
    min-height: 60px;
}

.l-panel {
    border: 1px solid #d2e0f1;
}
.l-table .l-td-left{
    padding: 0 5px;
}
.l-t-td-title {
    font-weight: bold;
    text-align: center;
    height: 28px;
}
.l-td-left{
    font-weight: bold;
}
.l-t-td-title, .l-td-title {
    text-align: center;
    background: #f6f6f6;
}

.l-t-td-left, .l-td-left {
    text-align: center;
    height:20px;
    background: #f6f6f6;
}



.l-t-td-right .l-text {
    background-image: none;
}

.l-t-td-right .l-text-wrapper {
    width: 100%;
    height: 24px;
}

.l-t-td-right .l-text input {
    height: 24px;
    padding-top: 0;
    line-height: 24px;
}


.l-t-td-right label {
    height: 24px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-td-left-align div.input {
    text-align: left;
}

.l-t-td-right .input-warp div {
}
div .input { width:98%; padding:0px 0px 0px 0px; line-height:20px; border-bottom:1px #9db9e2 solid; }

.l-label {text-align: center; color:black;}
.l-content {text-align: center; color:black;}
.l-td-opinion {padding: 1mm 0!important;}
.l-td-opinion .l-label {height:8mm;line-height:8mm;text-align:left;padding:0 2mm;}
.l-td-opinion .opinion {height:22mm;line-height:7mm;text-align:left;padding:0 2mm;text-align:left;}
.l-td-opinion .opinion span {margin-left:2em;}
.l-td-opinion .opinion textarea {height:100%;border:1px solid #dddddd;padding:1mm;font-size: 16px;width:98%;}
.l-td-opinion {position:relative;}
.l-td-opinion .signer-date {height:8mm; text-align:right;margin:2mm;}
.l-td-opinion .signer-date span {display:inline-block;height:8mm;line-height:8mm;text-align:left;float:right;}
.l-td-opinion .signer-date span.print-text{height:100%;float:none;}
.l-td-opinion .signer-date span.l-content {display: inline-block;border-bottom: 1px solid #dddddd;}
.l-td-opinion .signer-date span.signer{display: inline-block;border-bottom:1px solid #dddddd;}
.l-td-opinion .signer-date span input {border:none;width:98%;height:100%;}
.l-td-opinion .seal {width:140px;height:140px;position:absolute;right:35mm;top:0;}
.l-td-opinion .opinion textarea.remark-opinion{border:1px solid blue;}
.inner_table {margin:-3px -5px -2px -3px;width:101%}
.check{width:705px;}
</style>
</head>
<body >
<div class="navtab">
<div  title="借款单" style="height: 400px">
<form id="form1" action="money/borrow/huansubmit.do" getAction="money/borrow/detail.do?id=${param.id}">

    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="repayment_man_id" name="repayment_man_id" value="<%=userId%>"/>
    <input type="hidden" name="status" value=""/>
    <input name="repayment_time" type="hidden" value="<%=nowTime%>" />
    <h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">借&nbsp;款&nbsp;单 </h1></br>
    
    
    <table border="1" cellpadding="3" class="l-detail-table has-head-image">
        <tr>
        	<td class="l-t-td-left">单位</td>
        	<td colspan="2" class="l-t-td-right">
        		<u:combo name="unit" code="TJY2_UNIT" />
        	</td>
        	<td class="l-t-td-left">部门</td>
        	<td class="l-t-td-right" colspan="2"><input  validate="{maxlength:20}" ltype="text"  name="department" id="department" type="text"/></td>

        </tr>
        <tr>
        	<td class="l-t-td-left">日期</td>
        	<td colspan="2" class="l-t-td-right"><input name="borrowMoneyDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
        	<td class="l-t-td-left">借款人姓名</td>
        	<td colspan="2" class="l-t-td-right">
				<input name="borrower" type="text" ltype="text" validate="{required:true,maxlength:50}" />
			</td>
        </tr>
        <tr>
        	<td colspan="5" align="center" class="l-t-td-left">付款方式</td>
        	<td align="center" class="l-t-td-left">金额</td>
        </tr>
        <tr>
        	<td class="l-t-td-left">现金</td>
        	<td colspan="4" class="l-t-td-left">转账、电汇</td>
        	<td rowspan="2">
        		<table border="0" cellpadding="0" cellspacing="0"  style="margin:-1px -1px -1px -1px;" width="100%">
						<tr style="height:100%">
							<td style="width:19.5%;" align="center"> 拾万</td>
							<td style="width:11.5%;" align="center"> 万</td>
							<td style="width:11.5%;" align="center"> 仟</td>
							<td style="width:11.5%;" align="center"> 佰</td>
							<td style="width:11.5%;" align="center"> 拾</td>
							<td style="width:11.5%;" align="center"> 元</td>
							<td style="width:11.5%;" align="center"> 角</td>
							<td style="width:11.5%;" align="center" style="border-right:0px;"> 分</td>
						</tr>
						<tr>
                            <td class="l-t-td-right"><input id="shiwan" type="text" ltype="text" onkeyup="getMonyNum('wan');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="wan" type="text" ltype="text" onkeyup="getMonyNum('qian');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="qian" type="text" ltype="text" onkeyup="getMonyNum('bai');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="bai" type="text" ltype="text" onkeyup="getMonyNum('shi');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="shi" type="text" ltype="text" onkeyup="getMonyNum('yuan');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="yuan" type="text" ltype="text" onkeyup="getMonyNum('jiao');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="jiao" type="text" ltype="text" onkeyup="getMonyNum('fen');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right" style="border-right:0px;"><input id="fen" ltype="text" onkeyup="getMonyNum('fen');" validate="{maxlength:1}" name="fen" type="text" /></td>
                        </tr>
				</table>
        	</td>
        </tr>
        <tr>
        	<td rowspan="2" class="l-t-td-right"><input id="xianjin" name="cash" type="text" ltype='text' onkeyup="sumAmount();"/></td>
        	<td class="l-t-td-left">收款单位</td>
        	<td class="l-t-td-left">开户行</td>
        	<td class="l-t-td-left">帐号</td>
        	<td class="l-t-td-left">金额</td>
        </tr>
         <tr>
        	<td class="l-t-td-right"><input name="payee" type="text" ltype="text" validate="{maxlength:50}" /></td>
        	<td class="l-t-td-right"><input name="openingBank" type="text" ltype="text" validate="{maxlength:50}" /></td>
        	<td class="l-t-td-right"><input name="accounts" ltype="text" type="text" validate="{maxlength:10}" /></td>
        	<td class="l-t-td-right"><input name="money" id="money" ltype="text" type="text" validate="{maxlength:10}" onkeyup="sumAmount();"/></td>
        	<td class="l-t-td-right">
				<input name="import_money" id="import_money" type="text" ltype="text" validate="{maxlength:10}" title="总金额" onkeyup="sumAmount();"/>        	
			</td>
        </tr>
        <tr>
        	<td class="l-t-td-left">人民币大写</td>
        	<td colspan="2" class="l-t-td-right"><input name="rmb" id="rmb" type="text" ltype="text" validate="{maxlength:50}" /></td>
			<td colspan="3">
			<table>
				<tr>
					<td style="width:105px;" class="l-t-td-left">备注</td>
					<td style="width:350px;" class="l-t-td-right"><input name="remark" type="text" ltype="text" validate="{maxlength:50}" /></td>
				</tr>
			</table>
			</td>
        </tr>
        
    </table>
	<table class="check">
		<tr>
			<td>单位负责人：</td>
			<td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
			 </td>
			<td>分管院领导：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
			<td>部门领导：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
			<td>财务审核：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
			<td>领款人：</td>
			<td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
		</tr>
	</table></form></div>
	<div title="财务审核">
		<form id="form2" method="post">
	
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="fileId" name="fileId" value="${param.id}"/>
    	<input type="hidden" id="auditResult" name="auditResult"/>
    	<input type="hidden" id="auditManId" name="auditManId" value="<%=userId%>"/>
    	
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
				<tr>
					<td class="l-t-td-left">审核人</td>
					<td class="l-t-td-right">
	                    <input id="auditMan" name="auditMan" type="text" ltype='text' ligerUi="{disabled:true}" validate="{required:true}" value="<sec:authentication property="principal.name"/>"/>
	               	</td>
	               	<td class="l-t-td-left">审核时间</td>
        			<td class="l-t-td-right"><input name="borrowMoneyDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">审核意见</td>
					<td class="l-t-td-right1" colspan="3"><textarea name="auditOpinion" id="auditOpinion" rows="5" cols="25" class="l-textarea"  validate="{required:,maxlength:1000}"></textarea></td>
				</tr>
			</table>
			  	<script type="text/javascript">
			  	 $("#form2").initFormList({
			     	root:'datalist',
			         getAction:"cw/yijian/detail.do?fileId=${param.id}",
			         //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
			         actionParam:{},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
			         delAction:'cw/yijian/delete.do',	//删除数据的action
			         delActionParam:{ids:"id"},	//默认为选择行的id 
			         columns:[
			             //此部分配置同grid
			             { display:'意见id', name:'id', width:'5%', hide:true},
			 			{ display:'审核人id', name:'audit_man_id', width:'5%', hide:true},
			 			{ display:'审核表id', name:'file_id', width:'5%', hide:true},
			             { display:'审核人', name:'audit_man', width:'10%'},
			             { display:'审核时间', name:'audit_time', width:'15%'},
			             { display:'审核意见', name:'apply_count', width:'30%'},
			 			{ display:'审核结果', name:'audit_result', width:'10%'}
			             
			         ]
			     });
			  	
				</script>
			</form>
	</div>
</div>	

</body>
</html>
