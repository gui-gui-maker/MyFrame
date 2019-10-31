<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>消息发送</title>
<%@include file="/k/kui-base-form.jsp"%>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var status="${param.pageStatus}";
var exceptType="${param.exceptType}";
var tbar="";
$(function(){
	if(status=="add" ||status=="modify"){
		tbar=[ 
		          {text : '保存',id : 'save',icon : 'save',click : saveForm},
		          {text : '关闭',id : 'close',icon : 'cancel',click : close} ];
	}else if(status=="detail"){
		tbar=[ {text : '关闭',id : 'close',icon : 'cancel',click : close} ];
	}
	$("#formObj").initForm({
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar,
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('添加成功!',3);
				if(api.data.Qm)
					api.data.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('添加失败!<br/>' + resp.msg);
			}
		}
	});
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
			'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?this.id:this.id) + '\',' + false + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>');
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
			if(this.id != id)
			newReaders.push(this);
		}
	});
	$("#formObj").data("readers",newReaders);
	$("#"+id).remove();
}

//保存
function saveForm(){
	if ($("#formObj").validate().form()) {
		if($("#formObj").data("readers")==undefined){
			$.ligerDialog.error('提示：' + '请选择人员！');
			return false;
		}
		var selectedReaders=$("#formObj").data("readers");
		var ids="";
		for(var i in selectedReaders){
			ids=ids+selectedReaders[i].id+",";
		}
		if(ids.length>0&&ids!=undefined){
			var url="";
			if(exceptType=="title"){
				url="employeeBaseAction/addWorkTitleWarnExcept.do?ids="+ids;
			}else if(exceptType=="retired"){
				url="employeeBaseAction/addRetiredWarnExcept.do?ids="+ids;
			}
			$("body").mask("正在保存数据，请稍后！");
		    $.ajax({
		        url:url,
		        type: "POST",
		        datatype: "json",
		        contentType: "application/json; charset=utf-8",
		       	success: function (resp) {
		        	if(resp.success){
		    			top.$.notice(resp.msg,3);
		    			api.data.window.Qm.refreshGrid();
		    			api.close();
		    		}else{
		    			$.ligerDialog.error(resp.msg);
		    		}
		    		$("body").unmask();
		        },
		        error: function (resp) {
		            $.ligerDialog.error('添加失败！');
		    		$("body").unmask();
		        }
		    });
		}else{
			$.ligerDialog.error('提示：' + '请将信息填写完整！');
		}
		}
}
function close() {
		api.close();
	   }

</script>
</head>
<body>
	<form name="formObj" id="formObj"  action="" getAction="">
		<table class="l-detail-table">
			  <tr>
			  		<td class="l-t-td-left" style="height: 200px;width: 80px;">添加<br />例外<br />人员</td>
			  		<td class="l-t-td-right" id="reader_td">
			  			 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加例外人员">
			 			 <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
			  			</span></c:if>
			 		 </td>
			  </tr>
		</table>
	</form>
</body>
</html>
