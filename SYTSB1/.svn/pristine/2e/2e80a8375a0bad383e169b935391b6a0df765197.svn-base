<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
%>
	var pageStatus="${param.pageStatus}";
	var tbar="";
	$(function() {
		if(pageStatus=="detail"){
	 		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="add"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
		      				//表单验证
					    	if ($("#formObj").validate().form()) {
					    		$("#formObj").submit();
					    	}else{
					    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
					    	}
		      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="modify"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
  				//表单验证
		    	if ($("#formObj").validate().form()) {
		    		$("#formObj").submit();
		    	}else{
		    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
		    	}
  			}},
	  			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText)
				}
			},
			getSuccess : function(response) {
				if (response.success){
				}else {
					$.ligerDialog.alert("获取数据错误!");
					return;
				}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
		});
	});
	
	function selectCar(){
		var title = "车辆选择";
		var url = "url:app/oa/car/selectCar_list1.jsp?state=0";
		var width = 800	;
		top.$.dialog({
			width : width,
			height : 400,
			lock : true,
			parent : api,
			id : "win98",
			title : title,
			content : url,
			cancel: true,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelectResult();
				$("#carNum").val(datas.carnum);
				$("#carLogo").val(datas.carbrand);
				$("#engineNo").val(datas.engineNo);
				return true;
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
	            $("#useDepartmentId").val(p.id);
	            $("#useDepartment").val(p.name);
	        }
	    });
	}  
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post" action="CarrepairNoteAction/save.do?pageStatus=${param.pageStatus}"
		getAction="CarrepairNoteAction/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>
		<input name="useDepartmentId" type="hidden" id="useDepartmentId" value="<%=curUser.getDepartment().getId()%>"/>
		<input name="senrepairId" type="hidden" id="senrepairId" value="<%=userId%>"/>
		<input type="hidden" id="status" name="status" value="WTJ"/>
		<input type="hidden" name="createDate" id="createDate"/>
	     <input type="hidden" name="createId" id="createId"/>
	     <input type="hidden" name="createBy" id="createBy"/>
	     <input type="hidden" name="lastModifyDate" id="lastModifyDate"/>
	     <input type="hidden" name="lastModifyId" id="lastModifyId"/>
	     <input type="hidden" name="lastModifyBy" id="lastModifyBy"/>
		<table border="1" cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">标题</td>
				<td class="l-t-td-right" colspan="3">
					<!-- <input name="type" id="type" type="text" ltype='text' validate="{maxlength:100}"/> -->
					<u:combo name="type" code="TJY2_CAR_NOTETITLE" validate="{required:true}" attribute="initValue:'tjy'"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">使用部门</td>
				<td class="l-t-td-right" colspan="3">
					<!-- <input name="useDepartment" id="useDepartment" type="text" ltype='text' validate="{maxlength:100}"/> -->
					<input validate="{required:true}" value="<%=curUser.getDepartment().getOrgName()%>" readonly="readonly" ltype="text"  name="useDepartment" id="useDepartment"  type="text" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">车牌号</td>
				<td class="l-t-td-right">
					<input validate="{required:true}" name="carNum" id="carNum" type="text" ltype='text' validate="{maxlength:100}" onclick="selectCar()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCar()}}]}"/></td>
				</td>
				<td class="l-t-td-left">品牌型号</td>
				<td class="l-t-td-right">
					<input name="carLogo" id="carLogo" type="text" ltype='text' validate="{maxlength:100}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">发动机号</td>
				<td class="l-t-td-right">
					<input validate="{required:true}" name="engineNo" id="engineNo" type="text" ltype='text' validate="{maxlength:100}"/>
				</td>
			    <td class="l-t-td-left">车辆识别代码</td>
				<td class="l-t-td-right">
					<input validate="{required:false}" name="carCode" id="carCode" type="text" ltype='text' validate="{maxlength:100}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">送修人姓名</td>
				<td class="l-t-td-right">
					<input name="senrepairName" id="senrepairName" type="text" ltype='text' validate="{maxlength:100}" value="<%=e.getName()%>"/>
				</td>
			    <td class="l-t-td-left">送修时间</td>
				<td class="l-t-td-right"> 
        			<input validate="{required:true}" name="senrepairDate" id="senrepairDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        		</td>
			</tr>
			<tr>
				<td class="l-t-td-left">维修项目</td>
				<td class="l-t-td-right" colspan="3">
				   <textarea name="repairItem" id="repairItem" rows="16" cols="50" class="l-textarea" validate="{maxlength:3000}"></textarea>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>

