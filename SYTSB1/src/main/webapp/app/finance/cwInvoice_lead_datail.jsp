<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">

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
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
$(function() {
	
	var pageStatus="${param.pageStatus}";
	$("#form1").initForm({
		toolbar: [{text: "保存", icon: "save", click: saveData},
				  {text: "关闭", icon: "cancel", click: function(){
					api.close();
				}
			}
		], toolbarPosition: "bottom",
		afterParse:function(){
			if(pageStatus=='add'){
				$("#ids").val(api.data.ids);
				$("#lead_code").val(api.data.invoice_codes);
				$("#lead_num").val(api.data.count);
			}
			/* $("#lead_code").val("${param.invoice_codes}");
			$("#lead_num").val("${param.count}");
			var allid = ;
			$("#ids").val(allid); */
		}
	});	
	

	$('#lead_name').autocomplete("employee/basic/searchEmployee.do", {
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
     	 $("#lead_id").val(row.id);
     });
     
     
     $('#lead_dep').autocomplete("employee/basic/searchOrg.do", {
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
        $("#lead_dep_id").val(row.orgId);
    });
     
	
		
});


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
            $("#lead_id").val(p.id);
            $("#lead_name").val(p.name);
            $("#lead_dep").val(p.org_name);
            $("#lead_dep_id").val(p.org_id);
        }
    });
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
            $("#lead_dep_id").val(p.id);
            $("#lead_dep").val(p.name);
        }
    });
}   




function saveData(){
	 var formData = $("#form1").getValues();
	 var obj=$("#form1").validate().form();
	 var ids = $("#ids").val();
	 if(obj){
		 if($("#lead_name").val() !="" && $("#lead_name").val() != undefined){
   		  if($("#lead_id").val() == "" || $("#lead_id").val() == undefined){
        		 $.ligerDialog.warn("姓名id为空,请重新选择!");
        		 return;
        	 }
         }
        if($("#lead_dep").val() != "" && $("#lead_dep").val() != undefined){
        	if($("#lead_dep_id").val() == "" || $("#lead_dep_id").val() == undefined){
             $.ligerDialog.warn("部门id为空，请重新选择部门！");
              return;
          }
        }
	 	$("body").mask("正在保存...");
		 $.ajax({
    		url : 'cwInvoiceLead/lead/saveLy.do?ids='+ids,
    		type : "POST",
    		dataType : "json",
    		contentType : "application/json; charset=utf-8",
    		data: $.ligerui.toJSON(formData),
    		success : function(data, stats) {
    			$("body").unmask();
    			if(data.success){
    				top.$.dialog.notice('保存成功！');
    				api.data.window.Qm.refreshGrid();
    				api.close();
    			} else {
    				$.ligerDialog.error('保存失败！');
    			}
    		},
    		error : function(data) {
    			$("body").unmask();
    			$.ligerDialog.error('保存失败！');
    		}
    	});
	 }else{
		 return;
	}
}
</script>
</head>
<body>
	<form id="form1" Action="cwInvoiceLead/lead/saveLy.do"  getAction="cwInvoiceLead/lead/detail.do?id=${param.id}">
	<input type="hidden" id="ids" />
	<input type="hidden" id="lead_id" name="lead_id" value="<%=userId%>"/>
	<input type="hidden" id="lead_dep_id" name="lead_dep_id" value="<%=user.getDepartment().getId()%>"/>
	  <fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							领用信息 
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr>
						<td class="l-t-td-left">领用人:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" name="lead_name" id="lead_name" validate="{required:true,maxlength:50}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" value="<sec:authentication property="principal.name"/>"/></td>
						<td class="l-t-td-left">领用部门:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" name="lead_dep" id="lead_dep" validate="{required:true}" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" value="<%=user.getDepartment().getOrgName() %>" /></td>
					<tr>
						<td class="l-t-td-left">领用发票号:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" ligerui="{disabled:true}" id="lead_code" name="lead_code" /></td>
						<td class="l-t-td-left">领用总数:</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" ligerui="{disabled:true}" id="lead_num" name="lead_num" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">领用用途:</td>
						<td class="l-t-td-right" colspan="7" ><textarea name="lead_use" id="lead_use" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea></td>
					</tr>	
					</table>
		</fieldset>
	</form>
</body>
</html>