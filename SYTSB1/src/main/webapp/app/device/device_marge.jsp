<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var com_ids = "${param.com_ids}";

	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	      
	            //setData(res.data);
	            
	        }
	    });
	    function setData(data){
	     
	    
	    	
	    }
	    
	
	    
	    function saveClose(){
	    	var deviceId = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
	        $.ajax({
	            url: "device/basic/changeCom.do?deviceId="+deviceId,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (data, stats) {
	                if (data["success"]) {
	                	top.$.notice("批量修改成功！");
	               		api.close();
	                	api.data.window.submitAction();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	                $.ligerDialog.error(data.msg);
	            }
	        });
	    }
	    function saveAdd(){
	        save("saveAdd");
	   }
	   function close(){
	        api.close();
	    }
	});
	

	
	function selectorg(type){
		com_type=type;
		var url = 'url:app/enter/enter_open_list.jsp';
		if (type != "") {
			url += '?com_type='+com_type+'&com_ids='+com_ids;
		}
		
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择使用单位",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name,address,com_code){
		//判断选择单位类型关联相应字段 0 :使用单位 1：制造单位 2： 安装代为 3：设计单位 4：产品监检单位 5:锅炉产品监检单位 9：维保单位
		if(com_type=='0'){
			$('#fk_company_info_use_id').val(id);
			$('#company_name').val(name);
		}else if(com_type=='9'){
			$('#fk_maintain_unit_id').val(id);
			$('#maintain_unit_name').val(name);
		}
	}
</script>
</head>
<body>



<form name="formObj" id="formObj" method="post" >
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>选择使用单位</div>
		</legend>
		<table width="100%">		
			<tr>
				<td class="l-t-td-left">使用单位：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="fk_company_info_use_id" id="fk_company_info_use_id" type="hidden" />
					<input type="text" id="company_name" name="company_name" ltype="text" onclick="selectorg('0')"
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('0')}}]}"/>
				</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>
