<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8"%>

<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript">
var pageStatus = "${param.pageStatus}";
var tbar="";
var isCheck=${param.isCheck}+"";
var verify="";
$(function() {
	if(isCheck=='1'){
		 tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange1},
		       { text: '审核通过', id: 'audit', icon: 'accept', click: audit},
		       { text: '审核不通过', id: 'noaudit', icon: 'forbid', click: noaudit},
               { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
	 }else{
		 tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
		       { text: '保存并提交', id: 'submit', icon: 'submit', click:submit},
               { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
	 }
	$("#form1").initForm({
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar,
		/*success : function(responseText) {//处理成功
        	top.$.dialog.notice({content:'保存成功'});
            api.data.window.Qm.refreshGrid();
        },
        afterParse:function(){//form表单完成渲染后，回调函数
        
        },*/
        getSuccess: function(res){ 
            var readers = [];
            var names = [];
            var ids = [];//alert(res.data.dutyName);
            if(res.data.dutyName){
                 names = res.data.dutyName.split(",");
                 ids = res.data.dutyId.split(",");
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
  });
	$('#dutyName').autocomplete("employee/basic/searchEmployee.do", {
		 max: 12,    //列表里的条目数
         minChars: 1,    //自动完成激活之前填入的最小字符
         width: 200,     //提示的宽度，溢出隐藏
         scrollHeight: 300,   //提示的高度，溢出显示滚动条
         scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
         autoFill: false,    //自动填充
         formatItem: function(row, i, max) {
             return row.name + '   ' + row.mobileTel;
         },
         formatMatch: function(row, i, max) {
             return row.name + row.mobileTel;
         },
         formatResult: function(row) {
             return row.name;
         }
     }).result(function(event, row, formatted) {
    	 $("#dutyId").val(row.id);
     });
	
	$('#dutyDep').autocomplete("employee/basic/searchOrg.do", {
        max: 12,    //列表里的条目数
        minChars: 1,    //自动完成激活之前填入的最小字符
        width: 200,     //提示的宽度，溢出隐藏
        scrollHeight: 300,   //提示的高度，溢出显示滚动条
        scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    //自动填充
        formatItem: function(row, i, max) {
        //alert(row);
            return row.orgName;
        },
        formatMatch: function(row, i, max) {
            return row.orgName;
        },
        formatResult: function(row) {
            return row.orgName;
        }
    }).result(function(event, row, formatted) {
      	 $("#dutyDepId").val(row.orgId);
    });	
});

function choosePerson(){
    top.$.dialog({
        width: 800,
        height: 450,
        lock: true,
        parent: api,
        title: "选择人员",
        content: 'url:app/common/person_choose.jsp',
        cancel: true,
        ok: function(){
            var p = this.iframe.contentWindow.getSelectedPerson();
            if(!p){
                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                return false;
            }
            $("#dutyId").val(p.id);
            $("#dutyName").val(p.name);
            $("#dutyDep").val(p.org_name);
            $("#dutyDepId").val(p.org_id);
        }
    });
}


function chooseOrg(){
    top.$.dialog({
        width: 800,
        height: 450,
        lock: true,
        parent: api,
        title: "选择部门",
        content: 'url:app/qualitymanage/choose_org.jsp',
        cancel: true,
        ok: function(){
            var p = this.iframe.contentWindow.getSelectedPerson();
            if(!p){
                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                return false;
            }
            $("#dutyDepId").val(p.id);
            $("#dutyDep").val(p.name);
            
        }
    });
}   
 	
	
function directChange1(){ 
   	var obj=$("#form1").validate().form();
	 if(obj){
		 if($("#department").val() != "" && $("#department").val() != undefined){
	           if($("#departmentId").val() == "" || $("#departmentId").val() == undefined){
	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
	               return;
	           }
	         }
		 if($("#borrower").val() != "" && $("#borrower").val() != undefined){
	           if($("#borrowerId").val() == "" || $("#borrowerId").val() == undefined){
	               $.ligerDialog.warn("人员id为空，请重新选择人员！");
	               return;
	           }
	         }
		 //$("#form1").submit();
		 var formData = $("#form1").getValues();
         $("body").mask("正在保存......");
        $.ajax({
            url: "taskAllot/allot/save1.do",
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            data: $.ligerui.toJSON(formData),
            success: function (data, stats) {
                $("body").unmask();
                if (data["success"]) {
                	top.$.notice("数据保存成功！！",3);	
                    //top.$.dialog.notice({content:'保存成功！'});
                    api.data.window.Qm.refreshGrid();//刷新
                    //api.close();
                }else{
                    $.ligerDialog.error('提示：' + "数据保存失败！！");
                    api.data.window.Qm.refreshGrid();//刷新
                }
            },
            error: function (data,stats) {
                $("body").unmask();
                $.ligerDialog.error('提示：' + "数据保存失败！！");
            }
        });
	 }else{
		 return;
	}}

function save(SaveAndSub){
	 var obj=$("#form1").validate().form();
	 if(obj){
		 if($("#dutyName").val() !="" && $("#dutyName").val() != undefined){
  		  if($("#dutyId").val() == "" || $("#dutyId").val() == undefined){
       		 $.ligerDialog.warn("姓名id为空,请重新选择!");
       		 return;
       	 }
        }
//        if($("#dutyDep").val() != "" && $("#dutyDep").val() != undefined){
//        	if($("#dutyDepId").val() == "" || $("#dutyDepId").val() == undefined){
//             $.ligerDialog.warn("部门id为空，请重新选择部门！");
//              return;
//          }
//        }
       var formData = $("#form1").getValues();
       $("body").mask("正在保存......");
       $.ajax({
          url: "taskAllot/allot/saveTask.do?SaveAndSub="+SaveAndSub,
          type: "POST",
          datatype: "json",
          contentType: "application/json; charset=utf-8",
          data:$.ligerui.toJSON(formData),
          success: function (data, stats) {
              $("body").unmask();
              if (data["success"]) {
        	  	  api.data.window.Qm.refreshGrid();
        	  	  top.$.dialog.notice({content:'保存成功！'});
            	  $("#id").val(data);
                  if(SaveAndSub=="2"){
					 	 api.close();
                  }
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
	 }else{
		 return;
	 }
}

function audit(){
	var id = "${param.id}";
	$.ligerDialog.confirm('是否要通过审批？', function (yes){
       	 if(!yes){return false;}
       	var formData = $("#form1").getValues();
       $.ajax({
           url: "taskAllot/allot/save1.do",
           type: "POST",
           datatype: "json",
           contentType: "application/json; charset=utf-8",
           data: $.ligerui.toJSON(formData),
           success: function (data, stats) {
        	   if (data["success"]) {
        		   top.$.ajax({
                       url: "taskAllot/allot/audit.do?id="+id,
                       type: "POST",
                       dataType:'json',
                       data:"&verify=1",
                       async: false,
                       success:function (data) {
                       	api.data.window.Qm.refreshGrid();
                           api.close();
                      	   top.$.dialog.notice({content:'审核成功！'});
                       },
                       error:function () {
                           $.ligerDialog.warn("审批失败！");
                       }
                   });
               }else{
                   $.ligerDialog.error('提示：' + "审批失败！");
                   api.data.window.Qm.refreshGrid();//刷新
               }
           }});
          
       });
}


function noaudit (){
	var id = "${param.id}";
	$.ligerDialog.confirm('是否要不通过审核？', function (yes){
      	 if(!yes){return false;}
     	var formData = $("#form1").getValues();
        $.ajax({
            url: "taskAllot/allot/save1.do",
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            data: $.ligerui.toJSON(formData),
            success: function (data, stats) {
         	   if (data["success"]) {
         		  top.$.ajax({
                      url: "taskAllot/allot/audit.do?id="+id,
                      type: "POST",
                      dataType:'json',
                      data:"&verify=2",
                      async: false,
                      success:function (data) {
                      	$("body").unmask();
                              api.data.window.Qm.refreshGrid();
                              api.close();
                         	   top.$.dialog.notice({content:'审核成功！'});
                      },
                      error:function () {
                      	 $("body").unmask();
                          $.ligerDialog.error("审核失败！");
                      }
                  });
                }else{
                    $.ligerDialog.error('提示：' + "审批失败！");
                    api.data.window.Qm.refreshGrid();//刷新
                }
            }});
          
      });
 }
 
function submit(){
		save("2");
        top.$.ajax({
                 url: "taskAllot/allot/submit.do",
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                    	 api.data.window.Qm.refreshGrid();
                         api.close();
                         $.ligerDialog.success("提交成功！");
                         $.ligerDialog.warn(data.msg);
                 },
                 error:function () {
                     $.ligerDialog.warn("提交失败！");
                 }
             });
}

function selectReaders(){
    
    var readers = $("#form1").data("readers"); 
    selectUnitOrUser("4",1,"","",function(datas){
        if(!datas.code)return;
        var codeArr = datas.code.split(",");
        var nameArr = datas.name.split(",");
        var readers = [];
        var existReaders = $("#form1").data("readers")||[];
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


var CCnames='';//负责人姓名
var CCids='';//负责人id
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
             if(pageStatus!="detail"){
                mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
             }
             if(pageStatus=="detail"){
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
    $("#dutyName").val(CCnames.substring(0, CCnames.length-1));
    $("#dutyId").val(CCids);
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
     $("#dutyName").val(CCnames);
     $("#dutyId").val(CCids);
 }
 
</script>


<style type="text/css">
    .l-t-td-right1 .l-text{
    	background-color: rgb(255, 255, 255);
    	box-shadow: none;
		border: none;
		background-image: none;
		width:35%
	}
</style>

</head>

<body>
	<form id="form1" action="taskAllot/allot/saveTask.do" getAction="taskAllot/allot/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id" />
		<input type="hidden" id="dutyId" name="dutyId" />
		<input type="hidden" id="dutyDepId" name="dutyDepId" />
		<input type="hidden" id="status" name="status">
		<input type="hidden" id="registerId" name="registerId">
		<input type="hidden" id="registerName" name="registerName">
		<input type="hidden" id="registerDate" name="registerDate">
		<input type="hidden" id="itemTime" name="itemTime">
		<input type="hidden" id="principalDate" name="principalDate">
		<input type="hidden" id="checkTime" name="checkTime">
		<input type="hidden" id="sendTime" name="sendTime">
		<input type="hidden" id="delay" name="delay">
		
		<h1 class="l-label" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">任&nbsp;务&nbsp;书 </h1><div style="height:2px">&nbsp;</div>
		
		<table border="0" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
            	 <tr>
                    <td class="l-t-td-left">编号:</td>
                    <td class="l-t-td-right1"><input type="text" ltype="text" name="taskSn" readonly="readonly"  /></td>
                 </tr>
        </table>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="zlrw">
					<tr>
						<td class="l-t-td-left">负责人</td>
						<td class="l-t-td-right" id="reader_td">
						<input type="hidden" name="dutyName" id="dutyName"  validate="{required:true}"/>
							 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="负责人">
                              <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
                             </c:if>
						
						</td>
						<td class="l-t-td-left">责任部门</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="dutyDep" id="dutyDep" validate="{required:true}" onclick="chooseOrg();" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">任务名称</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="itemName" id="itemName" validate="{required:true}" /></td>
						<td class="l-t-td-left">期望完成时间</td>
						<td class="l-t-td-right"><input name="itemDate" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="itemDate"  
        					 readonly="readonly" value="<%=nowTime%>" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">任务内容</td>
						<td class="l-t-td-right" colspan="3"><textarea name="itemContent" id="itemContent" rows="8" cols="25" class="l-textarea" validate="{maxlength:2000,required:true}" validate="{required:true}"></textarea></td>
					</tr>
					<tr>
						<td class="l-t-td-left">任务要求</td>
						<td class="l-t-td-right" colspan="3"><textarea name="itemRequire" id="itemRequire" rows="8" cols="25" class="l-textarea" validate="{maxlength:2000,required:true}" validate="{required:true}"></textarea></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注</td>
						<td class="l-t-td-right" colspan="3"><textarea name="remark" id="remark" rows="8" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
					</tr>
					</table>

	</form>
</body>
</html>