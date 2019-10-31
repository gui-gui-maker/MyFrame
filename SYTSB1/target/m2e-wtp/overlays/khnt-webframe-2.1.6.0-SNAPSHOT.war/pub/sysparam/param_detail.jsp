<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#formObj").initForm({
            	toolbar:[{id:'save',icon:'save',click:function(){
        		if($("#formObj").validate().form()){
        			var param  = $("input[name='groups']").val()+$("input[name='name']").val();
        			if(validateName(api.data.grid,param)){
        				var data = $("#formObj").getValues();
        				if($("input[name='sys']").attr("disabled")=='disabled'||$("input[name='sys']").attr("disabled")==true){
        					data["sys"]="1";
        				}
        				$("body").mask("正在保存....");
        				$.ajax({
        					url:"pub/sysParam/saveP.do",
                            type: "POST",
                            datatype: "json",
                            contentType: "application/json; charset=utf-8",
                            data: $.ligerui.toJSON(data),
                            success: function (data, stats) {
                                if (data["success"]) {
                                	 api.data.grid.loadData();
                                     api.close();
                                } else {
                                    $.ligerDialog.error('保存失败！' + data.msg);
                                }
                                $("body").unmask();
                            },
                            error: function (data) {
                            	$("body").unmask();
                                $.ligerDialog.error('保存失败！' + data.msg);
                            }
                        });
        			}
        		}
        		},text:'保存'},{id:'close',icon:'cancel',click:function(){api.close()},text:'关闭'}],
                getSuccess:function(res){
                	if(res.success&&res.data.sys=='1'){
                		setDisabled();
                	}
                }
            });
        });
        function validateName(grid,param) {
            var data = grid.getData();
            for (var i in data) {
                var value=($.kh.isNull(data[i]["groups"])?"":data[i]["groups"])+data[i]["name"];
                if("${param.pageStatus}"=="add"){
                	if (value == param ) {
                   		$.ligerDialog.error("参数名称和分组的组合不能重复！");
                    	return false;
                	}
                }
                if("${param.pageStatus}"=="modify"|| "${param.pageStatus}"=="edit"){
                	if (value == param && data[i]["id"]!=$("#id").val()) {
                   		$.ligerDialog.error("参数名称和分组的组合不能重复！");
                    	return false;
                	}
                }
            }
            return true;
        }
        function setDisabled(){
        	$("#formObj input").each(function(){
        		if($(this).attr("name")!='_val'&&$(this).attr("name")!='id'&&$(this).attr("name")!='value'&&$(this).attr("name")!="display"){
        			$(this).ligerGetTextBoxManager().setDisabled(true);
        		}
        	})
        }
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post"
      action="pub/sysParam/saveP.do"
      getAction="pub/sysParam/detail.do?id=${param.id}">
    <input name="id" id="id" type="hidden"/>
    <table cellpadding="3" cellspacing="0" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">参数说明：</td>
            <td class="l-t-td-right" colspan="3"><input name="remark" type="text"
                                            ltype='text' validate="{required:true,maxlength:500}" ligerui="{explain:'参数说明：请按照下面格式填写如：系统名称 - 子系统'}"/></td>
        </tr>
        <tr>
        	<td class="l-t-td-left">参数名称：</td>
            <td class="l-t-td-right" colspan="3"><input name="name" type="text"
                                            ltype='text' validate="{required:true,maxlength:50}" ligerui="{explain:'参数名称说明：参数名称+分组不能重复'}"/></td>                    
        </tr>
        <tr>
            <td class="l-t-td-left">参数值：</td>
            <td class="l-t-td-right" colspan="3">
            <textarea rows="5" cols="20" name="value" ltype="textarea"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">数据类型：</td>
            <td class="l-t-td-right"><input name="dataType" type="text"
                                            ltype='select' validate="{required:true}" ligerui="{data:[{id: 'string', text: '字符串'},{id: 'number', text: '数字'},{id: 'boolean', text: '布尔型'},{id: 'user', text: '用户自定义'}]}"/></td>
            <td class="l-t-td-left">分组：</td>
            <td class="l-t-td-right"><input name="groups" type="text"
                                            ltype='text' value="" validate="{maxlength:50}"/></td>                            
        </tr>
        <tr>
            <td class="l-t-td-left">分类：</td>
            <td class="l-t-td-right"><input name="types" type="text"
                                            ltype='select' ligerui="{initValue:'browser',data:[ {id: 'browser', text: '前台'},{id: 'server', text: '后台'}]}"/></td>
             <td class="l-t-td-left">排序：</td>
            <td class="l-t-td-right"><input name="orders" type="text"
                                            ltype='text' validate="{maxlength:10}" /></td>                           
        </tr>
        <tr>
            <td class="l-t-td-left">系统参数：</td>
            <td class="l-t-td-right"><input name="sys" type="radio"
                                            ltype='radioGroup' validate="{required:true}" ligerui="{initValue:'0',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}"/></td>
              <td class="l-t-td-left">系统可见：</td>
            <td class="l-t-td-right"><input name="display" type="radio"
                                            ltype='radioGroup' validate="{required:true}" ligerui="{initValue:'1',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}"/></td>                               
        </tr>
    </table>
</form>
</body>
</html>
