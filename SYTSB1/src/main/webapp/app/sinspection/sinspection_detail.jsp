<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.lsts.org.dao.EnterDao"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String status = request.getParameter("status");
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	EnterDao enterDao = new EnterDao();
	List list = enterDao.queryEnterInfo(user.getId());
	String com_id = "";
	String com_name = "";
	String license_num = "";
	if(list!=null && list.size()>0){
		com_id = String.valueOf(list.get(0));
		com_name = String.valueOf(list.get(1));
		license_num = String.valueOf(list.get(2));
	}
%>
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var beanData;
var type;
	$(function() {
		$("#form1").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText);
				}
			},
			getSuccess : function(responseText) {
				if(responseText.success){
					beanData=responseText.data;
					type = beanData.device_type;
					$("#content_doc_id").val(beanData.content_doc_id);
					if("detail" == "${param.status}"){
						changeShow("8");
					}else if("modify" == "${param.status}"){
						changeShow(beanData.device_type);
					}
				}
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#form1").validate().form()) {
				    		var content = $("#content_doc_id").val();
				    		if("" != content){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#form1").submit();
					    		}
				    		}else{
				    			$.ligerDialog.alert("请先进行“在线编制”再保存！");
				    		}				    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});
	});

	// 打开正文，编辑正文
	function openDocEditor(){
		//将表单上的数据打包传过去
		var formData=$("#form1").getValues();
		var id=$("#id").val();
		var docId=$("#content_doc_id").val();
		if("detail" != "<%=status%>"){
			type=formData.device_type;
			if((formData.device_type =='' || formData.device_type==null)&&('${param.status}'=='add')){
				$.ligerDialog.warn('在线编制时，请先选择设备类型！');
				return;
			}
		}
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "正文内容",
			parent: api,
			content : 'url:app/sinspection/docEditor.jsp?status=${param.status}&id='+id+'&docId='+docId+'&type='+type,
			data: {pwindow: window,bean: formData}
		}).max();
	}
	
	//编辑完正文后填写正文id
	function editorCallback(docId){
		$("#content_doc_id").val(docId);
	}
	
	// 选择制造单位
	function selectUnit(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择制造单位",
			content: 'url:app/sinspection/choose_unit_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#made_unit_id').val(id);		// 制造单位ID
		$('#made_unit_name').val(name);	// 制造单位名称
	}
	
	// 根据所选设备类别显示相应的数据
	function changeShow(value){
		if("1" == value || "2" == value || "4" == value){
			$("#tr2").attr('style','display:none');
			$("#tr3").attr('style','display:none');
       		$("#tr4").attr('style','display:none');	
       		$("#tr41").attr('style','display:none');
       		$("#tr42").attr('style','display:none');
       		$("#tr5").attr('style','display:none');	
       		$("#tr51").attr('style','display:none');
       		$("#tr52").attr('style','display:none');
		}else if("3" == value){
       		$("#tr2").attr('style','display:block');
       		$("#tr3").attr('style','display:block');
       		$("#tr4").attr('style','display:block');
       		$("#tr41").attr('style','display:block');
       		$("#tr42").attr('style','display:block');	
       		$("#tr5").attr('style','display:none');	
       		$("#tr51").attr('style','display:none');
       		$("#tr52").attr('style','display:none');			
   		}else if("5" == value || "6" == value){
         	$("#tr2").attr('style','display:block');
       		$("#tr3").attr('style','display:block');
       		$("#tr5").attr('style','display:block');
       		$("#tr51").attr('style','display:block');
       		$("#tr52").attr('style','display:block');		
       		$("#tr4").attr('style','display:none');
       		$("#tr41").attr('style','display:none');
       		$("#tr42").attr('style','display:none');					
      	}else if("7" == value){
         	$("#tr2").attr('style','display:block');
       		$("#tr3").attr('style','display:block');
       		$("#tr4").attr('style','display:block');	
       		$("#tr5").attr('style','display:block');	
       		$("#tr41").attr('style','display:none');
       		$("#tr42").attr('style','display:none');
       		$("#tr51").attr('style','display:none');
       		$("#tr52").attr('style','display:none');		
     	}else{
     		$("#tr2").attr('style','display:block');
			$("#tr3").attr('style','display:block');
			$("#tr4").attr('style','display:block');
			$("#tr5").attr('style','display:block');
     	}
	}

</script>
</head>
<body>
	<form name="form1" id="form1" action="supervision/inspection/saveSupervisionInspection.do"
		getAction="supervision/inspection/detail.do?id=${param.id}">
		<input id="id" name="id" type="hidden" />
		<input id="status" name="status" type="hidden" />
		<input id="send_user_id" name="send_user_id" type="hidden" />
		<input id="send_user_name" name="send_user_name" type="hidden" />
		<input id="enter_user_id" name="enter_user_id" type="hidden" />
		<input id="enter_user_name" name="enter_user_name" type="hidden" />
		<input id="enter_date" name="enter_date" type="hidden" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>产品监检</div>
			</legend>
		<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">制造单位：</td>
				<td class="l-t-td-right">
					<input name="made_unit_id" id="made_unit_id" type="hidden" value="<%=com_id %>" />
					<input id="made_unit_name" name="made_unit_name" type="text" ltype='text' validate="{required:true}" ligerUi="disabled:true" value="<%=com_name %>"/>
			    </td>
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right">
					<u:combo name="device_type" code="BASE_CHECK_DEVICE_TYPE" validate="required:true" attribute="onSelected:changeShow"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">产品名称：</td>
				<td class="l-t-td-right"><input name="product_name" type="text" ltype="text" validate="{required:true,maxlength:100}"/></td>
				<td class="l-t-td-left">产品型号：</td>
				<td class="l-t-td-right"><input name="product_model" type="text" ltype="text" validate="{maxlength:20}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">产品类别：</td>
				<td class="l-t-td-right"><input name="product_type" type="text" ltype="text" validate="{maxlength:20}"/></td>
				<td class="l-t-td-left">产品编号：</td>
				<td class="l-t-td-right"><input name="product_code" type="text" ltype="text" validate="{maxlength:20}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">许可证级别<br>及编号：</td>
				<td class="l-t-td-right"><input name="license_num" type="text" ltype="text" validate="{required:true,maxlength:20}" ligerUi="{disabled:true}" value="<%=license_num %>"/></td>	
				<td class="l-t-td-left">制造日期：</td>
				<td class="l-t-td-right"><input name="product_made_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"/></td>
			</tr>
			<tr id="tr2" style="display:none;">
				<td class="l-t-td-left">公称压力：</td>
				<td class="l-t-td-right"><input name="product_pressure" type="text" ltype="text" validate="{maxlength:20}"/></td>
				<td class="l-t-td-left">公称直径：</td>
				<td class="l-t-td-right"><input name="product_diameter" type="text" ltype="text" validate="{maxlength:20}"/></td>
			</tr>
			<tr id="tr3" style="display:none;">
				<td class="l-t-td-left">设计温度：</td>
				<td class="l-t-td-right"><input name="product_temperature" type="text" ltype="text" validate="{maxlength:20}"/></td>
				<td class="l-t-td-left">介质：</td>
				<td class="l-t-td-right"><input name="product_medium" type="text" ltype="text" validate="{maxlength:20}"/></td>	
			</tr>
			<tr id="tr4" style="display:none;">
				<td class="l-t-td-left">主要材料：</td>
				<td class="l-t-td-right"><input name="product_main_material" type="text" ltype="text" validate="{maxlength:20}"/></td>
				<td class="l-t-td-left" id="tr41">壁厚：</td>
				<td class="l-t-td-right" id="tr42"><input name="product_wall_thickness" type="text" ltype="text" validate="{maxlength:20}"/></td>
			</tr>
			<tr id="tr5" style="display:none;">		
				<td class="l-t-td-left">焊接材料：</td>
				<td class="l-t-td-right"><input name="product_welding_material" type="text" ltype="text" validate="{maxlength:20}"/></td>
				<td class="l-t-td-left" id="tr51">阀体材料：</td>
				<td class="l-t-td-right" id="tr52"><input name="product_valuebody_material" type="text" ltype="text" validate="{maxlength:20}"/></td>
			</tr>
			
			<tr id="editdocs" >
				<td class="l-t-td-left">监检编号：</td>
				<td class="l-t-td-right"><input name="inspection_code" type="text" ltype="text" validate="{required:true,maxlength:20}"/></td>
				<td class="l-t-td-left">在线编制：</td>
				<td class="l-t-td-right">
					<input name="content_doc_id" type="hidden" id="content_doc_id" /> 
				<%
					if("detail".equals(status)){
				%>
				   <span style="cursor:pointer"><img src="k\kui\skins\icons\word.gif" onclick="openDocEditor();"></span>
				<% 
					}else{
				%>
				   <span class="l-button" onclick="openDocEditor();"><span class="l-button-main"><span class="l-button-text">编辑正文</span></span></span>
				<%
					}
				%>
				</td>
			</tr>
			<%
				if("detail".equals(status)){
			%>
			<tr>
				<td class="l-t-td-left">录入人：</td>
				<td class="l-t-td-right">
					<input id="enter_user_name" name="enter_user_name" type="text" ltype='text'  />
			    </td>
			    <td class="l-t-td-left">录入时间：</td>
				<td class="l-t-td-right">
					<input name="enter_date" id="enter_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
			    </td>
			</tr>	
			<tr>
				<td class="l-t-td-left">流转科室：</td>
				<td class="l-t-td-right">
					<input id="send_department_name" name="send_department_name" type="text" ltype='text'  />
			    </td>
			    <td class="l-t-td-left">状态：</td>
				<td class="l-t-td-right">
					<input type="radio" name="status" id="status" ltype="radioGroup"
							ligerui="{data: [ { text:'未提交', id:'0' }, { text:'已提交', id:'1' } ] }"/>
			    </td>
			</tr>	
			<%
				}
			%>
		</table>
		</fieldset>
	</form>
</body>
</html>
