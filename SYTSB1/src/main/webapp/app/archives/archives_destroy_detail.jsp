<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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

<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    	<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回id

	var areaFlag;//改变状态
  	 
    $(function () {fz();
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
        	$("#xhsq").transform("detail",function(){});
   	    	$("#xhsq").setValues("archives/destroy/detail.do?id=${requestScope.serviceId}");
   	    	$("#xhsq1").setValues("archives/destroy/detail.do?id=${requestScope.serviceId}");
   	    	tbar=[{ text: '审核', id: 'submit', icon: 'submit', click: sh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
    		
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
    	});
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
//     	$('#proposer').autocomplete("employee/basic/searchEmployee.do", {
//             max: 12,    //列表里的条目数
//             minChars: 1,    //自动完成激活之前填入的最小字符
//             width: 200,     //提示的宽度，溢出隐藏
//             scrollHeight: 300,   //提示的高度，溢出显示滚动条
//             scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
//             matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
//             autoFill: false,    //自动填充
//             formatItem: function(row, i, max) {
//                 return row.name + '   ' + row.mobileTel;
//             },
//             formatMatch: function(row, i, max) {
//                 return row.name + row.mobileTel;
//             },
//             formatResult: function(row) {
//                 return row.name;
//             }
//         }).result(function(event, row, formatted) {
// 			$("#proposerId").val(row.id);
//         }); 
    	$('#applyUnit').autocomplete("employee/basic/searchOrg.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
            //alert(row);
                return row.orgName;
            },
            formatMatch: function(row, i, max) {
                return row.orgName;
            },
            formatResult: function(row) {
                return row.orgName;
            }
        }).result(function(event, row, formatted) {
//            alert(row.orgId);
				$("#applyUnitId").val(row.orgId);
        });
    });
	function fz(){
		var report_sn="${param.report_sn}";
    	var ids="${param.ids}";
    	document.getElementById('reportNumberId').innerText="${param.ids}";
    	document.getElementById('reportNumber').innerText="${param.report_sn}";
    	var a=0;
    	if(ids==""){
	    	a="";
	    }else{
	    	var temp = report_sn.split(",");
	    	for (var i = 0; i < temp.length; i++) {
	    		a++;
	    	}
	    }
    	$("#shares").val(a);	
	}
    function sh() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/archives/archives_destroy_yijian.jsp?pageStatus=add&serviceId='+serviceId+
					'&activityId='+activityId+'&processId='+processId
		});
	}
    function directChange(){ 
    	var obj=$("#form1").validate().form();
       	if($("#applyUnit").val() != "" && $("#applyUnit").val() != undefined){
	           if($("#applyUnitId").val() == "" || $("#applyUnitId").val() == undefined){
	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
	               return;
	           }
	         }
		 if($("#proposer").val() != "" && $("#proposer").val() != undefined){
	           if($("#proposerId").val() == "" || $("#proposerId").val() == undefined){
	               $.ligerDialog.warn("人员id为空，请重新选择人员！");
	               return;
	           }
	         }
		 if(obj){
	       	//$("#form1").submit();
			 var formData = $("#form1").getValues();
	         $("body").mask("正在保存......");
	        $.ajax({
	            url: "archives/destroy/save.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            data: $.ligerui.toJSON(formData),
	            success: function (data, stats) {
	                $("body").unmask();
	                if (data["success"]) {
	                    top.$.dialog.notice({content:'保存成功！'});
	                    api.data.window.Qm.refreshGrid();//刷新
	                    api.close();
	                }else{
	                    $.ligerDialog.error('提示：' + data.msg);
	                }
	            },
	            error: function (data,stats) {
	                $("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	            }
	        });
		 }else{
             return;
		 }
           Qm.refreshGrid();//刷新
	 }
    function chooseOrg(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择部门",
            content: 'url:app/common/org_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#applyUnitId").val(p.id);
                $("#applyUnit").val(p.name);
            }
        });
    }
    function choosePerson(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择人员",
            content: 'url:app/common/person_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#proposerId").val(p.id);
                $("#proposer").val(p.name);
            }
        });
    }
    
    function choosefile(){
    	top.$.dialog({
			parent: api,
			width : 860, 
			height : 500, 
			lock : false, 
			title:"选择报告编号",
			content: 'url:app/archives/archives_file_xuanze.jsp',
			data : {"parentWindow" : window}
		});

    }
    function callBackReport(id, report_sn){
    	var a=0;
    	var temp = report_sn.split(",");
    	for (var i = 0; i < temp.length; i++) {
    		a++;
    	}
    	$("#shares").val(a);		
    	$("#reportNumber").val(report_sn);		
    	$("#reportNumberId").val(id);
	}	
   function close(){
   	if(api.data.window.submitAction)
   		api.data.window.submitAction();
   		api.close();
   }
    </script>
   
</head>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 80px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 80px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 80px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 80px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
<body>
   

<form id="form1" action="archives/destroy/save.do" getAction="archives/destroy/detail.do?id=${param.id}">
     <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="status" name="status"/>
    <input type="hidden" id="reportNumberId" name="reportNumberId"/>
    <input type="hidden" id="proposerId" name="proposerId"  value="<%=userId%>"/>
    <input type="hidden" id="applyUnitId" name="applyUnitId" value="<%=user.getDepartment().getId() %>"/>
    <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
    <h1 id="jysq2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">档案销毁申请单</h1></br>
    <table id="xhsq1" class="check">
			 <tr>
			 	<td width="30px">&nbsp;</td>
			 	<td width="20" align="center">共：</td>
                <td width="30" class="l-t-td-right" align="center"><input ltype='text' readOnly="true" id="shares" name=shares type="text"/></td>	
                <td width="20px" align="center">份</td>
	            <td width="580px">&nbsp;</td>
	            <td>编号：</td>
	            <td class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
            </tr>
	</table>
    <table id="xhsq" border="1" cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">部门</td>
        	<td class="l-t-td-right" colspan="4" ><input  ligerUi="{disabled:true}"  value="<%=user.getDepartment().getOrgName() %>" validate="{maxlength:50}" ltype="text"  name="applyUnit" id="applyUnit" type="text"/></td>
<!-- 		 ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" -->
		</tr>
		<tr>	
            <td class="l-t-td-left" >报告号</td>
            <td class="l-t-td-right1" colspan="4">
            	<textarea id="reportNumber" name="reportNumber" rows="5" cols="25" class="l-textarea" validate="{required:true,maxlength:2000}" onclick="choosefile();"></textarea>
            </td>
        </tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">申请销毁理由、<br />范围及处理意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea name="applyOpinion" id="applyOpinion" rows="7" cols="25" class="l-textarea"  validate="{required:true,maxlength:2000}"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >销毁申请人</td>
			<td class="l-t-td-right"><input name="proposer" id="proposer" type="text" ligerUi="{disabled:true}" 
				value="<sec:authentication property="principal.name"/>" ltype="text" 
				ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" /></td>
				 <td class="l-t-td-left">申请日期</td>
        	<td class="l-t-td-right"><input id="applyTime" name="applyTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" validate="{required:true}"/></td>
		</tr>
<!-- 		签字 -->
		<tr>
            <td class="l-t-td-left" rowspan="2">申请部门意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="sqbm" id="sqbm" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="sqbmTime" name="sqbmTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">科管部意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="kgb" id="kgb" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="kgbTime" name="kgbTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">质管部意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="zgb" id="zgb" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="zgbTime" name="zgbTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">分管院领导意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="fgyld" id="fgyld" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="fgyldTime" name="fgyldTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">技术负责人意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="jsfzr" id="jsfzr" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="jsfzrTime" name="jsfzrTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
		<tr>
            <td class="l-t-td-left" rowspan="2">院长意见</td>
        	<td class="l-t-td-right1" colspan="4"><textarea readonly="readonly" name="yz" id="yz" rows="7" cols="25" class="l-textarea"></textarea></td>
		</tr>
		<tr>
			<td class="l-t-td-left" >审核人</td>
			<td class="l-t-td-right"><input readonly="readonly" name="" id="" type="text" ltype="text"/></td>
				 <td class="l-t-td-left">审核日期</td>
        	<td class="l-t-td-right"><input readonly="readonly" id="yzTime" name="yzTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
		</tr>
    </table>
	
</form>
</body>
</html>
