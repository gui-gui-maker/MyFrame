<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>为权限配置资源</title>
<%@include file="/k/kui-base-form.jsp"%>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
status="${param.pageStatus}";
$(function(){
	$("#formObj").initForm({
		toolbar : [ {
        	text : '立即发送',
        	id : 'save1',
	        icon : 'save',
	        click : saveAdd
          },{
        	text : '保存',
        	id : 'save',
	        icon : 'save',
	        click : saveForm
          }, {
           	text : '关闭',
	        id : 'close',
	        icon : 'cancel',
	        click : close
          } ],
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('保存成功!',3);
				if(api.data.Qm)
					api.data.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('保存失败!<br/>' + resp.msg);
			}
		},
		getSuccess: function(resp){
			var readers = [];
			$("#formObj").setValues(resp.message)
			if(resp.employee){
				 for(var i=0;i<resp.employee.length;i++){
					readers.push({
						types : "reader",
						name: resp.employee[i].empName,
						id: resp.employee[i].id
					});
				} 
				 if(status=="detail"){
					 addReaderDetail(readers,false);
				 }else{
					 addReader(readers,false);
				 }
				 
			}
		}
	});
	/* $("#formObj").validate({
		submitHandler : saveForm
	}); */
});


function selectReaders(){
	selectUnitOrUser("4",1,"","",function(datas){
		if(!datas.code)return;
		var codeArr = datas.code.split(",");
		var nameArr = datas.name.split(",");
		var readers = [];
		var existReaders = $("#formObj").data("readers")||[];
		for(var i in codeArr){
			var exist = false;
			for(var j in existReaders){
				if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
					exist=true;
			}
			if(exist) continue;
			readers.push({
				types : "reader",
				name: nameArr[i],
				id: codeArr[i]
			});
		}
		addReader(readers,true);
	});
}
function addReaderDetail(newReaders,isNew){
	$.each(newReaders,function(){
		$("#reader_td").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.name + 
			'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')">&nbsp;</span></span>');
	});
}
function addReader(newReaders,isNew){
	var readers = $("#formObj").data("readers");
	$.each(newReaders,function(){
		$("#reader_td").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.name + 
			'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>');
	});
	if(readers)
		readers = readers.concat(newReaders);
	else
		readers = newReaders;
	$("#formObj").data("readers",readers);
}

function deleteReader(id,isNew){
	var readers = $("#formObj").data("readers");
	var newReaders = [];
	$.each(readers,function(){
		if(!isNew){
			if(this.id == id)
				this.valid = false;
			newReaders.push(this);
		}
	});
	$("#formObj").data("readers",newReaders);
	$("#"+id).remove();
}
//发送
function saveAdd(){
	 var readers = $("#formObj").data("readers"); 
		var formdata = transFormDataToJSON($("#formObj").getValues());
		formdata["employee"] = readers;
		var rlMessage= $.ligerui.toJSON(formdata);
		$("body").mask("消息发送中......");
		$.ajax({
	        url: "rlMessageAction/saveMessage.do",
	        type: "POST",
	      /*   dataType: "json", 
	        contentType: "application/json;charset=utf-8", */
	        data:{"rlMessage":rlMessage,"status":"1"},
	        success: function (resp, stats) {
	        	if(resp.success){
	    			top.$.notice("操作成功！",3);
	    			api.data.window.Qm.refreshGrid();
	    			api.close();
	    		}else{
	    			$.ligerDialog.error("操作失败！<br/>" + resp.msg);
	    		}
	    		$("body").unmask();
	        },
	        error: function (data) {
	            $.ligerDialog.error('操作失败！<br/>' + data.msg);
	    		$("body").unmask();
	        }
	    });
}
//保存
function saveForm(){
	 var readers = $("#formObj").data("readers"); 
	
	var formdata = transFormDataToJSON($("#formObj").getValues());
	formdata["employee"] = readers;
	var rlMessage= $.ligerui.toJSON(formdata);
	$("body").mask("正在保存数据！");
	$.ajax({
        url: "rlMessageAction/saveMessage.do",
        type: "POST",
        /* dataType: "json",
        contentType: "application/json;charset=utf-8", */
        data:{"rlMessage":rlMessage,"status":"0"},
        success: function (resp, stats) {
        	if(resp.success){
    			top.$.notice("操作成功！",3);
    			api.data.window.Qm.refreshGrid();
    			api.close();
    		}else{
    			$.ligerDialog.error("操作失败！<br/>" + resp.msg);
    		}
    		$("body").unmask();
        },
        error: function (data) {
            $.ligerDialog.error('操作失败！<br/>' + data.msg);
    		$("body").unmask();
        }
    });
}
function close() {
		api.close();
	   }
</script>
</head>
<body>
		   <form name="formObj" id="formObj"  getAction="rlMessageAction/detailMessage.do?id=${param.id}">
		   <input type="hidden" id="id" name="id" />
				<table class="l-detail-table">
		           <tr>
						<td class="l-t-td-left">发送方式</td>
						<td class="l-t-td-right"><u:combo  name="sendManner" code="TJY2_RL_MESSAGETYPE" validate="required:true"></u:combo></td>
				  </tr>
				  <tr>
		                <td class="l-t-td-left">接收人</td>
			            <td class="l-t-td-right" id="reader_td">
				            <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加接收人">
					               <!-- <span  class="add btn"  onclick="selectReaders();">&nbsp;</span> -->
					                <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
				                   </span></c:if>
			           </td>
			         </tr>
				  <tr>
						<td class="l-t-td-left">消息内容</td>
						<td ><textarea  name="message" id="remark" rows="4" cols="25" class="l-textarea" ></textarea></td>
				  </tr>
		
	        	</table>
	       </form>
</body>
</html>
