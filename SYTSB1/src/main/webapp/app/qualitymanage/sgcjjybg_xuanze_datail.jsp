<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
    var fid="${param.fid}";
    var xzbh="${param.xzbh}";//选择编号
 	$(function () {
         tbar=[{ text: '新增', id: 'up', icon: 'save', click: directChange},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
         if ("${param.pageStatus}"=="detail")
 	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
         $("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
        function directChange(){ 
        	var obj=$("#form1").validate().form();
       	 if(obj){
       		 //$("#form1").submit();
       		 var formData = $("#form1").getValues();
                $("body").mask("正在保存......");
               $.ajax({
                   url: "sgcjjybgNum/a/save.do?fid="+fid+"&xzbh="+xzbh,
                   type: "POST",
                   datatype: "json",
                   contentType: "application/json; charset=utf-8",
                   data: $.ligerui.toJSON(formData),
                   success: function (data, stats) {
                       $("body").unmask();//alert(99);
                       if (data["success"]) {
                       	top.$.notice("保存成功！！",3);	
                           //top.$.dialog.notice({content:'保存成功！'});
                       	api.data.window.Qm.refreshGrid();
                       	api.close();
                       }else{
                           $.ligerDialog.error('提示：' + "保存失败！！");
                           api.data.window.Qm.refreshGrid();//刷新
                       }
                   },
                   error: function (data,stats) {
                       $("body").unmask();
                       $.ligerDialog.error('提示：' + "保存失败！！");
                   }
               });
       	 }else{
       		 return;
       	}} 
    </script>
</head>
<body>
	<form id="form1" action="sgcjjybgNum/a/save.do" getAction="sgcjjybgNum/a/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="status" name="status"/>
		<input type="hidden" id="fksgId" name="fksgId"/>
		<input type="hidden" id="rowId" name="rowId"/>
		<input type="hidden" id="registrant" name="registrant"/>
		<input type="hidden" id="registrantId" name="registrantId"/>
		<input type="hidden" id="registrantDate" name="registrantDate"/>
		<input type="hidden" id="fwbjhbgNum" name="fwbjhbgNum"/>
		<input type="hidden" id="identifierType" name="identifierType" value="1"/>
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
			<tr>
	         	<td class="l-t-td-left">检验项目代码</td>
        		<td class="l-t-td-right"><u:combo name="testCoding" code="TJY2_ZLZ_JYXMBM" validate="required:false"  attribute="isMultiSelect:false" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注</td>
				<td class="l-t-td-right"><textarea name="remarks" id="remarks" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}" validate="{required:true}"></textarea></td>
			</tr>
					
		</table>
	</form>
</body>
</html>