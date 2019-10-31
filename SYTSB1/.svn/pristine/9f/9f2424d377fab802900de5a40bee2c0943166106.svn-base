<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>合同到期提醒设置</title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function () {
	tbar=[{text: "保存", icon: "save", click: function(){
    	if ($("#formObj").validate().form()) {
    		$("#formObj").submit();
    	}else{
    		$.ligerDialog.error('提示：' + '请填写完整后保存！');
    	}}},
		{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	$("#formObj").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.close();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
      		}
		}, getSuccess: function (response){
		},
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar
	});
	getSetting();//修改时获取消息提醒信息
})
function getSetting(){
	$.ajax({
    	url: "contractRemindAction/getSetting.do",
        type: "POST",
        datatype: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resp) {
        	var contractRemind = resp.data;
        	if(contractRemind!=null&&contractRemind!=""){
        		$("#formObj").setValues(contractRemind);
        		var readers = [];
                var names = [];
                var ids = [];
                if(contractRemind.remindName){
                     names = contractRemind.remindName.split(",");
                     ids = contractRemind.remindId.split(",");
                     for(var i=0;i<names.length;i++){
                        readers.push({
                            types : "reader",
                            name: names[i],
                            id: ids[i]
                        });
                    } 
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
var CCnames='';//接收人姓名
var CCids='';//接收人id
function addReader(newReaders,isNew){
	var names = '';
	var ids = '';
	var repids='';
	var repNames='';
	//alert(newReaders);
	for(var i in newReaders){
         if(CCids.indexOf(newReaders[i].id)==-1){
             names = names+newReaders[i].name+","; 
             ids = ids+newReaders[i].id+",";
             var mtext='<span class="label label-read" id="' + (isNew?newReaders[i].id:newReaders[i].id) + '"><span class="text">' + newReaders[i].name;
             if("${param.pageStatus}"!="detail"){
                mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
             }
             if("${param.pageStatus}"=="detail"){
                mtext = mtext+',';
              }
             $("#reader_td").prepend(mtext);
         } else if (newReaders[i].name){
             repNames = repNames+newReaders[i].name+",";
         }
    }
    if(repNames != ""){
             $.ligerDialog.warn("人员："+"<span style='color:red;'>"+repNames.substring(0,repNames.length-1)+"</span>"+" 已存在！");
    }
    CCnames = CCnames+names;
    CCids = CCids+ids;
    $("#remindName").val(CCnames.substring(0, CCnames.length-1));
    $("#remindId").val(CCids);
 }
 function deleteReader(id,isNew){
     $("#"+id).remove();
     var ids = CCids.split(",");
     var names = CCnames.split(",");
     for(var i in ids){
        if(id==ids[i]){
            ids[i]="";
            names[i]="";
        }
     }
     var cid="";
     var cname="";
     for(var i in ids){
        if(ids[i]!=""){
            cid = cid+ids[i]+',';
            cname = cname+names[i]+',';
        }
     }
     CCnames = cname;
     CCids = cid;
     $("#remindName").val(CCnames);
     $("#remindId").val(CCids);
 }
</script>
</head>
<body>
	<form name="formObj" id="formObj" action="contractRemindAction/saveSetting.do" 
	getAction="">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="remindId" name="remindId"/>
		<input type="hidden" id="createDate" name="createDate"/>
		<input type="hidden" id="createId" name="createId"/>
		<input type="hidden" id="createBy" name="createBy"/>
		<input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
		<input type="hidden" id="lastModifyId" name="lastModifyId"/>
		<input type="hidden" id="lastModifyBy" name="lastModifyBy"/>
		<table class="l-detail-table">
			<!-- <tr>
				生日到期提醒提前周/月
				<td class="l-t-td-left">提醒时间（提前）</td>
				<td class="l-t-td-right"><input type="radio" name="contractRemindTime" id="contractRemindTime" ltype="radioGroup" validate="{required:true}"
					ligerui="{value:'1',data: [ { text:'当月', id:'1' }, { text:'半个月', id:'2' }, { text:'一天', id:'3' } ] }" />
				</td>
			</tr>
			<tr>
				生日到期提醒发送方式
				<td class="l-t-td-left">发送方式</td>
				<td class="l-t-td-right"><input type="radio" name="contractSendType" id="contractSendType" ltype="radioGroup" validate="{required:true}"
					ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' } ] }" />
				</td>
			</tr> -->
	  		<tr>
	            <td class="l-t-td-left">接收人</td>
	           	<td class="l-t-td-right" id="reader_td">
	           	<input type="hidden" name="remindName" id="remindName"/>
	            <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加接收人">
	      		<span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
	       	 </span></c:if>
	        	</td>
	      	</tr>
			<%-- <tr>
				<!-- 生日到期提醒内容 -->
				<td class="l-t-td-left">发送内容</td>
				<td ><textarea  name="contractRemind" id="contractRemind" rows="2" cols="25" class="l-textarea">即将到期的合同的有：<%=name%>【人力资源管理部】</textarea></td>
			</tr>
			<tr>
				<!-- 当事人生日到期提醒内容 -->
				<td class="l-t-td-left">当事人<br />接收内容</td>
				<td ><textarea  name="contractRemindSelf" id="contractRemindSelf" rows="2" cols="25" class="l-textarea">您的合同即将到期，请即时与人力资源部协商相关事宜！【人力资源管理部】</textarea></td>
			</tr> --%>
	</table>
  </form>
</body>
</html>