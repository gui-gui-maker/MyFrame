<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String pageStatus=request.getParameter("pageStatus");
	String type=request.getParameter("type");
	System.out.println(type);
%>
<head pageStatus="${param.pageStatus}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var pageStatus="${param.pageStatus}";
	var tbar;
	$(function() {
		if("${param.type}"=="liaison"){
			$("#tr_").hide();
		}else if("${param.type}"=="note"){
			$("#tr_").show();
			$("#inspectorPeople").attr("required", "true");
			$("#inspectorPeopleDate").attr("required", "true");
		}
		if(pageStatus=="detail"){
	 		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="add"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
     			//表单验证
		    	if ($("#form1").validate().form()) {
		    		$("#form1").submit();
		    	}else{
		    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
		    	}
     			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="modify"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
  				//表单验证
		    	if ($("#form1").validate().form()) {
		    		$("#form1").submit();
		    	}else{
		    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
		    	}
  			}},
	  			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		
		$("#form1").initForm({
			success: function (response) {//处理成功
				if (response.success) {
					top.$.dialog.notice('保存成功!',3);
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败!<br/>' + response.msg);
				}
			}, getSuccess: function (response){
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	})	
	function choosePerson(){
		selectUnitOrUser("4",0,"","",function(datas){
			if(!datas.code)return;
			var codeArr = datas.code.split(",");
			var nameArr = datas.name.split(",");
			$("#inspectorId").val(codeArr[0]);
            $("#inspector").val(nameArr[0]);
		});
	}
	function choosePerson_1(){
		selectUnitOrUser("4",0,"","",function(datas){
			if(!datas.code)return;
			var codeArr = datas.code.split(",");
			var nameArr = datas.name.split(",");
			$("#inspectorPeopleId").val(codeArr[0]);
            $("#inspectorPeople").val(nameArr[0]);
		});
	}
</script>
</head>
<body>
<div title="通知书" id="formObj">
<%
	if("liaison".equals(type)){
%>
	<form id="form1" action="QualityLiaisonAction/save.do?pageStatus=${param.pageStatus}" getAction="QualityLiaisonAction/detail.do?id=${param.id}">
<%
	}else if("note".equals(type) || "seal".equals(type)){
%>
	<form id="form1" action="QualityNoteAction/save.do?pageStatus=${param.pageStatus}" getAction="QualityNoteAction/detail.do?id=${param.id}">
<%
	}
%>
	<input type="hidden" name="id" id="id"/>
	<input type="hidden" name="type" id="type" value="<%=type %>"/>
     <input type="hidden" name="inspectorId" id="inspectorId"/>
     <input type="hidden" name="inspectorPeopleId" id="inspectorPeopleId"/>
     <input type="hidden" name="checkStatus" id="checkStatus"/>
     <input type="hidden" name="printStatus" id="printStatus"/>
     <input type="hidden" name="identifier" id="identifier"/>
     <input type="hidden" name="createDate" id="createDate"/>
     <input type="hidden" name="createId" id="createId"/>
     <input type="hidden" name="createBy" id="createBy"/>
     <input type="hidden" name="lastModifyDate" id="lastModifyDate"/>
     <input type="hidden" name="lastModifyId" id="lastModifyId"/>
     <input type="hidden" name="lastModifyBy" id="lastModifyBy"/>
     <input type="hidden" name="sealDate" id="sealDate"/>
     <input type="hidden" name="sealPoepleId" id="sealPoepleId"/>
     <input type="hidden" name="sealPoepleName" id="sealPoepleName"/>
     <input type="hidden" name="sealStatus" id="sealStatus" value="N"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					<%
						if("liaison".equals(type)){
					%>
						联络单
					<%
						}else if("note".equals(type) || "seal".equals(type)){
					%>
						意见通知书
					<%
						}
					%>
				</div>
			</legend>
		<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
		<%-- <tr>
			<td class="l-t-td-left">通知书类型</td>
			<td class="l-t-td-right" colspan="3">
				<u:combo name="type" code="INSPECTION_NOTE_TYPE" validate="{required:true}" attribute="initValue:'',onSelected:valueChange"/>
			</td>
		</tr> --%>
		<tr> 
        <td class="l-t-td-left"> 受检单位名称</td>
        <td class="l-t-td-right"> 
        <input name="comName" id="comName" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
        <td class="l-t-td-left"> 产品信息</td>
        <td class="l-t-td-right"> 
        <input name="item1" id="item1" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 类型</td>
        <td class="l-t-td-right"> 
        <input name="item2" id="item2" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
        <td class="l-t-td-left"> 截止时间</td>
        <td class="l-t-td-right"> 
        <input name="item3" id="item3" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
       </tr>
       <tr>
			<td class="l-t-td-left">问题和意见</td>
			<td class="l-t-td-right" colspan="3">
				<textarea id="content" name="content" rows="16" cols="50" class="l-textarea" validate="{required:true,maxlength:3000}"></textarea>
		    </td>
		</tr>
	  <tr>
        <td class="l-t-td-left">监检员</td>
        <td class="l-t-td-right"> 
        <input name="inspector" id="inspector" type="text" ltype='text' validate="{required:true,maxlength:100}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
        </td>
        <td class="l-t-td-left">日期</td>
        <td class="l-t-td-right"> 
        <input name="inspectorDate" id="inspectorDate" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
       </tr>
       <tr id="tr_">
        <td class="l-t-td-left">监检机构技术负责人</td>
        <td class="l-t-td-right"> 
        <input name="inspectorPeople" id="inspectorPeople" type="text" ltype='text' validate="{maxlength:100}" ligerui="{iconItems:[{icon:'user',click:choosePerson_1}]}"/>
        </td>
        <td class="l-t-td-left">日期</td>
        <td class="l-t-td-right"> 
        <input name="inspectorPeopleDate" id="inspectorPeopleDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
       </tr>
			<%
				if("detail".equals(pageStatus)){
			%>
			<tr>
			    <td class="l-t-td-left">审核意见</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="checkContent" id="checkContent" rows="5" cols="50" class="l-textarea" validate="{maxlength:1000}"></textarea>
			    </td>
			</tr>
			<tr>
				<td class="l-t-td-left">审核状态</td>
				<td class="l-t-td-right" colspan="3">
					<u:combo name="checkStatus" code="NOTE_CHECK_STATUS" validate="{required:true}" attribute="initValue:''"/>
			    </td>
			</tr>
			<tr>
				<td class="l-t-td-left">审核人</td>
				<td class="l-t-td-right">
					<input id="checkUserName" name="checkUserName" type="text" ltype='text'  />
			    </td>
			    <td class="l-t-td-left">审核时间</td>
				<td class="l-t-td-right">
					<input name="checkDate" id="checkDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
			    </td>
			</tr>	
			<%
				}
			%>
		</table>
		</fieldset>
	</form>
	</div>
</body>
</html>
