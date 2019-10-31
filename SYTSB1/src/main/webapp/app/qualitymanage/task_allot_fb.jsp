<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8"%>

<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	var isCheck=${param.isCheck}+"";
	$(function () {
		 tbar=[{ text: '保存', id: 'up', icon: 'save', click: saveAdd},
               { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
		 $("#form1").initForm({    //参数设置示例
		        toolbarPosition: "bottom" ,
		        toolbar:tbar,
		        success : function(responseText) {//处理成功
		        	top.$.dialog.notice({content:'保存成功'});
                    api.data.window.Qm.refreshGrid();
                    api.close();
		        },
		        afterParse:function(){//form表单完成渲染后，回调函数
		        	$("#delayDate").hide();
				}
		 });
		 
		 function audit(){
			 var id = $("#id").val();
				top.$.dialog({
					width: 600,
					height: 250,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "审核",
					content: 'url:app/qualitymanage/task_allot_datail.jsp?pageStatus=detail&id='+id
				});
		 }
		 
		 function saveAdd(){
			var radio= $("input[name='status']:checked").val();
			if(radio == null || radio ==""){
		    	 $.ligerDialog.warn("请选择完成进度!");
	       		 return;
		     }
			 if(radio=='WWC'){
				 var unfinished = $("#unfinished").val();
				 if(unfinished=='' || unfinished==undefined){
					 $("#feedback1").hide();
					 $.ligerDialog.warn('请填写未完成原因!');
					 return;
				 }
			 }else if(radio=='YWC'){
				 var feedback = $("#feedback").val();
				 if(feedback=='' || feedback==undefined){
					 $("#unfinished1").hide();
					 $.ligerDialog.warn('请填写完成情况!');
					 return;
				 }
			 }else{
				 var delayDate = $("#delay").val();
				 if(delayDate=='' || delayDate==undefined){
					 $.ligerDialog.warn('请选择延期时间!');
					 return;
			 	}
		 	 }
			 var formData = $("#form1").getValues();
		       $("body").mask("正在保存......");
		       $.ajax({
		          url: "task/Fk/saveFK.do?ids="+$('#ids').val(),
		          type: "POST",
		          datatype: "json",
		          contentType: "application/json; charset=utf-8",
		          data:$.ligerui.toJSON(formData),
		          success: function (data, stats) {
		              $("body").unmask();
		              if (data["success"]) {
		            	  $("#id").val();
		            	  $("#ids").val();
		                  top.$.dialog.notice({content:data.msg});
		                  api.data.window.Qm.refreshGrid();
						  api.close();
		                  
		              }else{
		                  $.ligerDialog.error('提示：' + data.msg);
		                  api.data.window.Qm.refreshGrid();
		              }
		          },
		          error: function (data,stats) {
		              $("body").unmask();
		              $.ligerDialog.error('提示：' + data.msg);
		          }
		      });
			 
		 }
		 
	});
	function delayShow(){
		 var radio= $("input[name='status']:checked").val();
		 if(radio=='WWC'){
		     $("#feedback,#delay").val("");
			 $("#delayDate").hide();
			 $("#feedback1").hide();
			 $("#unfinished1").show();
			 $("#unfinished").attr("readonly",false);
		 }else if(radio=='YWC'){
		     $("#unfinished,#delay").val("");
			 $("#delayDate").hide();
			 $("#unfinished1").hide();
			 $("#feedback1").show();
			 $("#feedback").attr("readonly",false);
		 }else if(radio=='YQ'){
		     $("#feedback,#unfinished").val("");
			 $("#delayDate").show(); 
			 $("#feedback1").hide();
			 $("#unfinished1").hide();
		 }else{
		     $("#delayDate").val("");
			 $("#delayDate").hide(); 
			 $("#feedback1").show();
			 $("#unfinished1").show();
		 }
		 
	 }
	
</script>

</head>
<body>

    <form id="form1" action="task/Fk/saveFK.do"  getAction="task/Fk/detail.do?id=${param.id}" >
        <input type="hidden" id="id" name="id">
        <input type="hidden" value="${param.id}" id="ids" name="ids">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left">完成进度</td>
        <td class="l-t-td-right">
        	<u:combo name="status" ltype="radioGroup" code="TJY2_ZLGL_RWCL" attribute="onChange:function(){delayShow();}" />
        </td>
       	 <td class="l-t-td-right">
          <table id="delayDate" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       		<td class="l-t-td-left">延期至</td>
       		<td class="l-t-td-right"><input id="delay" name="delay" type="text" ltype="date" validate="{required:false}" 
        		ligerui="{initValue:'',format:'yyyy-MM-dd'}" width="120px;" /> </td>
       	    </table>
       	 </td>
       </tr>
       <tr> 
        <td class="l-t-td-right">
        	<table id="feedback1" cellpadding="3" cellspacing="0" width="800px" class="l-detail-table1">
        <td class="l-t-td-left">完成情况</td>
        <td colspan="3" class="l-t-td-right"> 
       		 <textarea  style="height: 100px" name="feedback" id="feedback" type="text" ltype='text' readonly="readonly" validate="{required:false,maxlength:2000}"></textarea>
        </td>
        	</table>
        </td>
       </tr>
        <tr> 
         <td class="l-t-td-right">
        	<table id="unfinished1" cellpadding="3" cellspacing="0" width="800px" class="l-detail-table1">
        <td class="l-t-td-left">未完成原因</td>
        <td colspan="3" class="l-t-td-right"> 
      		  <textarea  style="height: 100px" name="unfinished" id="unfinished" type="text" ltype='text' readonly="readonly" validate="{required:false,maxlength:2000}"></textarea>
        </td>
        	</table>
      	 </td>
       </tr>
      </table>
    </form>
    
</body>
</html>