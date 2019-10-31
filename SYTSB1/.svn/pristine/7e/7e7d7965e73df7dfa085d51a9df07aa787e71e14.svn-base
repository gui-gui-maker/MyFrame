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
var tbar="";
$(function(){
	if(status=="add" ||status=="modify"){
		tbar=[ {text : '立即发送',id : 'save1',icon : 'save',click : saveSend},
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
				top.$.dialog.notice('保存成功!',3);
				if(api.data.Qm)
					api.data.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('保存失败!<br/>' + resp.msg);
			}
		},
		getSuccess: function(resp){
			$("#sendName1").hide();
			var readers = [];
			$("#formObj").setValues(resp.officeMessage)
			if(resp.employees){
				$("#sendName1").show();
				 $("#sendName2").hide();
				 $("#sendName2").val();
				 for(var i=0;i<resp.employees.length;i++){
					readers.push({
						types : "reader",
						name: resp.employees[i].empName,
						id: resp.employees[i].id
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
});
function getOfficeMessage(){
	$.ajax({
    	url: "officeMessageAction/getOfficeMessage.do?id=${param.id}",
        type: "POST",
        datatype: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resp) {
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
        },
        error: function (data) {
        	$.ligerDialog.alert("获取信息失败！");
        }
    });
	
}

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
//发送
function saveSend(){
	if ($("#formObj").validate().form()) {
		if($("#formObj").data("readers")==undefined&&$("#sendNumber").val()==""){
			$.ligerDialog.error('提示：' + '请选择消息接收人或接收电话！');
			return false;
		}
		$("input:radio[name='sendType']").attr("disabled",false);
		var formData = $("#formObj").getValues();
		formData.employee=$("#formObj").data("readers");
	    $("body").mask("正在保存数据，请稍后！");
	    $.ajax({
	        url:"officeMessageAction/saveMessage.do?saveType=saveAndSend&pageStatus=${param.pageStatus}",
	        type: "POST",
	        datatype: "json",
	        contentType: "application/json; charset=utf-8",
	       	data:$.ligerui.toJSON(formData),
	       	success: function (resp) {
	        	if(resp.success){
	    			top.$.notice("发送成功！",3);
	    			api.data.window.Qm.refreshGrid();
	    			api.close();
	    		}else{
	    			$.ligerDialog.error("操作失败！");
	    		}
	    		$("body").unmask();
	        },
	        error: function (resp) {
	            $.ligerDialog.error('发送失败！');
	    		$("body").unmask();
	        }
	    });
	}else{
		$.ligerDialog.error('提示：' + '请将信息填写完整！');
	}
	
}
//保存
function saveForm(){
	if ($("#formObj").validate().form()) {
		if($("#formObj").data("readers")==undefined&&$("#sendNumber").val()==""){
			$.ligerDialog.error('提示：' + '请选择消息接收人或接收电话！');
			return false;
		}
		$("input:radio[name='sendType']").attr("disabled",false);
		var formData = $("#formObj").getValues();
		formData.employee=$("#formObj").data("readers");
	    $("body").mask("正在保存数据，请稍后！");
	    $.ajax({
	        url:"officeMessageAction/saveMessage.do?saveType=save&pageStatus=${param.pageStatus}",
	        type: "POST",
	        datatype: "json",
	        contentType: "application/json; charset=utf-8",
	       	data:$.ligerui.toJSON(formData),
	       	success: function (resp) {
	        	if(resp.success){
	    			top.$.notice("保存成功！",3);
	    			api.data.window.Qm.refreshGrid();
	    			api.close();
	    		}else{
	    			$.ligerDialog.error("保存失败！");
	    		}
	    		$("body").unmask();
	        },
	        error: function (resp) {
	            $.ligerDialog.error('保存失败！');
	    		$("body").unmask();
	        }
	    });
	}else{
		$.ligerDialog.error('提示：' + '请将信息填写完整！');
	}
	
}
function close() {
		api.close();
	   }
//发送类型radio点击事件
function resChange1(val){
	if(val=="0"){//常规
		$("#tr-sendTime").hide();
	}else if(val=="1"){//定时
		$("#tr-sendTime").show();
	}
}
//是否临时号码radio点击事件
function resChange2(val){
	if(val=="0"){//不是临时号码
		$("#tr-timing").hide();
		$("#sendName2").hide();
		$("#sendName1").show();
		$("input:radio[name='sendType'][value=0]").attr("checked",true);
		$("input:radio[name='sendType']").attr("disabled",false);
	}else if(val=="1"){//是临时号码
		$("#sendName1").hide();
		$("#sendName2").show();
		$("#tr-timing").show();
		$("input:radio[name='sendType'][value=1]").attr("checked",true);
		$("input:radio[name='sendType']").attr("disabled",true);
	}
}
</script>
</head>
<body>
		   <form name="formObj" id="formObj"  action="" getAction="officeMessageAction/detailMessage.do?id=${param.id}">
		   <input type="hidden" id="id" name="id" />
		   <input type="hidden" id="sendId" name="sendId"/>
		   <input type="hidden" id="createDate" name="createDate"/>
		   <input type="hidden" id="createId" name="createId"/>
		   <input type="hidden" id="createBy" name="createBy"/>
				<table class="l-detail-table">
					<tr>
						<td class="l-t-td-left">发送类型</td>
						<td class="l-t-td-right"><input type="radio" name="sendStyle" id="sendStyle" ltype="radioGroup" validate="{required:true}"
							ligerui="{onChange:resChange1,value:'0',data: [ { text:'常规', id:'0' }, { text:'定时', id:'1' }]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">临时号码</td>
						<td class="l-t-td-right"><input type="radio" name="isTemporaryTel" id="isTemporaryTel" ltype="radioGroup" validate="{required:true}"
							ligerui="{onChange:resChange2,value:'0',data: [ { text:'否', id:'0' },{ text:'是', id:'1' }]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">发送方式</td>
						<td class="l-t-td-right"><input type="radio" name="sendType" id="sendType" ltype="radioGroup" validate="{required:false}"
							ligerui="{value:'0',data: [ { text:'微信', id:'0' }, { text:'短信', id:'1' }, { text:'微信和短信', id:'2' }] }" />
						</td>
				  </tr>
				  <tr id="sendName1">
		                <td class="l-t-td-left">接收人</td>
			            <td class="l-t-td-right" id="reader_td">
				            <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加接收人">
					               <!-- <span  class="add btn"  onclick="selectReaders();">&nbsp;</span> -->
					                <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
				                   </span></c:if>
			           </td>
			         </tr>
			         <tr id="sendName2">
		                <td class="l-t-td-left">临时接收人</td>
			            <td class="l-t-td-right"><input type="text" ltype="text" name="sendName" id="sendName"/>
			           </td>
			         </tr>
			      <tr id="tr-timing">
						<td class="l-t-td-left">电话号码</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="sendNumber" id="sendNumber"/>
						</td>
				  </tr>
				  <tr id="tr-sendTime">
						<td class="l-t-td-left">发送时间</td>
						<td class="l-t-td-right"><input name="sendTime" id="sendTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/>
						</td>
				  </tr>
				  <tr>
						<td class="l-t-td-left">消息内容</td>
						<td ><textarea  name="sendDimension" id="sendDimension" rows="4" cols="25" class="l-textarea" validate="{required:true}"></textarea></td>
				  </tr>
		
	        	</table>
	       </form>
</body>
</html>
