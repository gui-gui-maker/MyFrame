<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
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
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	 <link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回id
	
	var yj;

	var areaFlag;//改变状态
	<bpm:ifPer function="TJY2_FN_DYSQ_SQBMFZ" activityId="${requestScope.activityId}">areaFlag="1";</bpm:ifPer>//申请部门负责人
 	<bpm:ifPer function="TJY2_FN_DYSQ_FWBMFZ" activityId="${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//服务部门负责人
 	<bpm:ifPer function="TJY2_FN_DYSQ_FWBMJB" activityId="${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//服务部门经办人
 	<bpm:ifPer function="TJY2_FN_DYSQ_FWBMJBJD" activityId="${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//服务部门经办人(机电)
  	 
    $(function () {
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
        	$("#dy").transform("detail",function(){});
   	    	$("#dy").setValues("archives/print/detail.do?id=${requestScope.serviceId}");
   	    	$("#dy1").setValues("archives/print/detail.do?id=${requestScope.serviceId}");
   	    	$("#dysh").setValues("archives/print/detail.do?id=${requestScope.serviceId}");
   	    	if(areaFlag=="1"){
   		    	document.getElementById("sqbmfzr").readOnly=false; 
   	    	}else if(areaFlag=="2"){
   		    	document.getElementById("fwbmfzr").readOnly=false; 
   	    	}else if(areaFlag=="3" || areaFlag=="4"){
   		    	document.getElementById("fwbmjbr").readOnly=false; 
   	    	}
   	    	tbar=[{ text: '审核不通过', id: 'del', icon: 'forbid', click: nosubmitSh},
                  { text: '通过', id: 'up', icon: 'accept', click: submitSh},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
//    	    	tbar=[{ text: '审核', id: 'submit', icon: 'submit', click: sh},
//                    { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    	//$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    	$('#proposer').autocomplete("employee/basic/searchEmployee.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
                return row.name + '   ' + row.mobileTel;
            },
            formatMatch: function(row, i, max) {
                return row.name + row.mobileTel;
            },
            formatResult: function(row) {
                return row.name;
            }
        }).result(function(event, row, formatted) {
//            alert(row.mobileTel);
			$("#proposerId").val(row.id);
        }); 
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
 	function submitSh(){
 		if(areaFlag=="1"){
		    	yj=document.getElementById("sqbmfzr").value; 
	    	}else if(areaFlag=="2"){
	    		yj=document.getElementById("fwbmfzr").value; 
	    	}else if(areaFlag=="3" || areaFlag=="4"){
	    		yj=document.getElementById("fwbmjbr").value; 
	    	}
 		$.ligerDialog.confirm('是否提交审核？', function (yes){
 	         if(!yes){return false;}
 	          $("body").mask("提交中...");
 	          getServiceFlowConfig("TJY2_FN_DYSQ1","",function(result,data){
 	                 if(result){
 	                      top.$.ajax({
 	                          url: "archives/yijina/dadysh.do?id="+serviceId+
 	                         		 "&typeCode=TJY2_FN_DYSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
 	                          type: "POST",
 	                          dataType:'json',
 	                          data:"&yj="+yj,
 	                          async: false,
 	                          success:function (data) {
 	                              if (data) {
 	                                	$("body").unmask();
 	       	                         	//$.ligerDialog.success("提交成功！");
 		                            	 api.data.window.Qm.refreshGrid();
 		 			                     api.close();
 	                             	 	
 	                              }
 	                          }
 	                      });
 	                 }else{
 	                  $.ligerDialog.alert("出错了！请重试！");
 	                  $("body").unmask();
                  	  api.data.window.Qm.refreshGrid();
 	                 }
 	              });
 	         });
 	}
	function nosubmitSh(){
		if(areaFlag=="1"){
	    	yj=document.getElementById("sqbmfzr").value; 
    	}else if(areaFlag=="2"){
    		yj=document.getElementById("fwbmfzr").value; 
    	}else if(areaFlag=="3" || areaFlag=="4"){
    		yj=document.getElementById("fwbmjbr").value; 
    	}
		getServiceFlowConfig("TJY2_FN_DYSQ1","",function(result,data){
            if(result){
                 top.$.ajax({
                     url: "archives/yijina/dadyth.do?id="+serviceId+
                    		 "&typeCode=TJY2_FN_DYSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
                     type: "POST",
                     dataType:'json',
                     async: false,
                     data:"&yj="+yj,
                     success:function (data) {
                         if (data) {
                            //$.ligerDialog.alert("操作成功！！！");
                            $("body").unmask();
                            api.data.window.Qm.refreshGrid();
			                 api.close();
                         }
                     },
                     error:function () {
                   	  $.ligerDialog.error("出错了！请重试！");
                      api.data.window.Qm.refreshGrid();
                         $("body").unmask();
                     }
                 });
            }else{
             $.ligerDialog.error("出错了！请重试！");
             $("body").unmask();
            }
         });
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
			content: 'url:app/archives/archives_print_yijian.jsp?pageStatus=add&serviceId='+serviceId+
					'&activityId='+activityId+'&processId='+processId
		});
	}
    function directChange(){ 
    	var a=$("#documentId").val();
    	var obj=$("#form1").validate().form();
    	if(obj){
           //	$("#form1").submit();
    		var formData = $("#form1").getValues();
            $("body").mask("正在保存......");
           $.ajax({
               url: "archives/print/save.do",
               type: "POST",
               datatype: "json",
               contentType: "application/json; charset=utf-8",
               data: $.ligerui.toJSON(formData),
               success: function (data, stats) {
                   $("body").unmask();//alert(99);
                   if (data["success"]) {
                   	top.$.notice(data.msg,3);	
                       //top.$.dialog.notice({content:'保存成功！'});
                       api.data.window.Qm.refreshGrid();//刷新
                       api.close();
                   }else{
                       $.ligerDialog.error('提示：' + data.msg);
                       api.data.window.Qm.refreshGrid();//刷新
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
	 }
    function bg(){
    	
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
                $("#applyUnit").val(p.org_name);
                $("#applyUnitId").val(p.org_id);
            }
        });
    }
    
//     function choosefile(){
// 		top.$.dialog({
// 			width : 1000,
// 			height : 450,
// 			lock:true,
// 			title:"配置角色拥有的权限",
// 			content: 'url:app/archives/archives_file_index.jsp',
// 			ok:function(w){
// 				var iframe=this.iframe.contentWindow;
//                 iframe.authorizedRole();
// 				return false;
// 			},
// 			cancel:function(w){//取消按钮函数
// 			}
// 		});
//     }  
    
    function choosefile(){
    	top.$.dialog({
			parent: api,
			width : 860, 
			height : 500, 
			lock : true, 
			title:"选择报告编号",
			content: 'url:app/archives/archives_file_xuanze.jsp',
			data : {"parentWindow" : window}
		});

    }
    function callBackReport(ADVANCE_TIME,id, report_sn,check_op_name,org_name,COM_NAME,check_unit_id,device_registration_code){
    	var a=0;
    	var temp = report_sn.split(",");
    	for (var i = 0; i < temp.length; i++) {
    		a++;
    	}
    	var a=$("#documentId").val();
    	var b=$("#documentIdid").val();
    	if(a==""){
    		$("#documentId").val(report_sn);
    		$("#documentIdid").val(id);
    	}else{
    		$("#documentId").val(a+","+report_sn);	
    		$("#documentIdid").val(b+","+id);
    	}
    	
//     	$("#documentName").val(a);
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
    height: 84px;
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
    height: 84px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 84px;
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
    height: 84px;
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
<form id="form1" action="archives/print/save.do" getAction="archives/print/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="status" name="status"/>
    <input type="hidden" id="documentIdid" name="documentIdid"/>
    <input type="hidden" id="proposerId" name="proposerId"  value="<%=userId%>"/>
    <input type="hidden" id="applyUnitId" name="applyUnitId" value="<%=user.getDepartment().getId() %>"/>
    <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
    <h1 id="dy2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">检验报告/证书（正/副）打印申请表</h1></br>
    <table id="dy1" class="check">
		<tr>
			<td width="650px">&nbsp;</td>
			<td width="40px">编号：</td>
			<td class="l-t-td-right" width="200px"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
		</tr>
	</table>
    <table id="dy" border="1" cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">部门</td>
        	<td class="l-t-td-right"><input value="<%=user.getDepartment().getOrgName() %>" validate="{maxlength:50}" ltype="text"  name="applyUnit" id="applyUnit" type="text" ligerui="{disabled:true,iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
            <td class="l-t-td-left">使用单位名称</td>
            <td class="l-t-td-right"><input validate="{maxlength:100}" name="useUnitName" type="text" ltype="text" /></textarea></td>
		</tr>
        <tr>
<!--         p  "-->
         	<td class="l-t-td-left" >申请人</td>
            <td class="l-t-td-right" ><input readonly="readonly" name="proposer" id="proposer" type="text" value="<sec:authentication property="principal.name"/>" ltype="text" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" /></td>        
            <td class="l-t-td-left">申请日期</td>
        	<td class="l-t-td-right"><input id="applyTime" name="applyTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" validate="{required:true}"/></td>
        </tr>
        <tr>	
			<td class="l-t-td-left">申请原因</td>
			<td class="l-t-td-right1" colspan="3"><textarea name="applyReason" id="applyReason" rows="7" cols="25" class="l-textarea"  validate="{required:true,maxlength:2000}"></textarea></td>
        </tr>   
        <tr>	
            <td class="l-t-td-left" rowspan="2">报告编号</td>
            <td class="l-t-td-right1" colspan="3"><textarea name="documentId" id="documentId" rows="7" cols="25" class="l-textarea" 
            ligerui="{iconItems:[{icon:'add',click:function(){choosefile()}}]}" validate="{required:true}" title="提示：报告编号可以手动输入，分割符号要用英文逗号！！！"></textarea>
<!--             onclick="choosefile();"   -->
        </tr>
        <tr>
            <td class="l-t-td-right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(正副本打印份数请在此格内单独注明)
            </td> 
             <td style="text-align:right">共 &nbsp;&nbsp;
            </td> 
<!--             <td class="l-t-td-right" ><input name="documentName" type="text" ltype="text" /> -->
            <td class="l-t-td-right" >
	            <table>
	            	<tr>
	            		<td class="l-t-td-right"><input id="documentName" name="documentName" type="text" ltype="text" validate="{required:true,maxlength:4}"/></td>
		            	<td style="text-align:left">&nbsp;&nbsp;台&nbsp;&nbsp;</td> 
	            	</tr>
	            </table>
            </td> 
        </tr>
        
    </table>
    <table id="dysh" cellpadding="3">
    	<tr>	
            <td class="l-t-td-left" rowspan="2">申请部门负责人意见</td>
            <td class="l-t-td-right1" colspan="3"><textarea readonly="readonly" name="sqbmfzr" id="sqbmfzr" rows="7" cols="25" class="l-textarea"></textarea></td>
        </tr>
         <tr>
         	<td>
	         	<table border="1" cellpadding="3" class="l-detail-table">
		        	<tr>
		            	<td class="l-t-td-left">签字</td>
	            		<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="sqbmfzrName" type="text" ltype="text" /></td>
	         			<td class="l-t-td-left">日期</td>
	        			<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="sqbmfzrTime" ligerui="{disabled:true}" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
		            </tr>
		        </table>
	        </td>
        </tr>
        <tr>	
            <td class="l-t-td-left" rowspan="2">服务部门负责人意见</td>
            <td class="l-t-td-right1" colspan="3"><textarea readonly="readonly" name="fwbmfzr" id="fwbmfzr" rows="7" cols="25" class="l-textarea" ></textarea></td>
        </tr>
         <tr>
         	<td>
	         	<table border="1" cellpadding="3" class="l-detail-table">
		        	<tr>
		            	<td class="l-t-td-left">签字</td>
	            		<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="fwbmfzrName" type="text" ltype="text" /></td>
	         			<td class="l-t-td-left">日期</td>
	        			<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="fwbmfzrTime" ligerui="{disabled:true}" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
		            </tr>
		        </table>
	        </td>
        </tr>
         <tr>	
            <td class="l-t-td-left" rowspan="2">服务部门经办人</td>
            <td class="l-t-td-right1" colspan="3"><textarea readonly="readonly" name="fwbmjbr" id="fwbmjbr" rows="7" cols="25" class="l-textarea"></textarea></td>
        </tr>
         <tr>
         	<td>
	         	<table border="1" cellpadding="3" class="l-detail-table">
		        	<tr>
		            	<td class="l-t-td-left">签字</td>
	            		<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="fwbmjbrName" type="text" ltype="text" /></td>
	         			<td class="l-t-td-left">日期</td>
	        			<td class="l-t-td-right" rowspan="2"><input readonly="readonly" name="fwbmjbrTime" ligerui="{disabled:true}" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
		            </tr>
		        </table>
	        </td>
        </tr>
    </table>
</form>
</body>
</html>
