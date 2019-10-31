<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<head pageStatus="${param.status}">
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userid= user.getId();


%>
<%@ include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" href="app/cloud_platform/upload/bootstrap/easyui.css" type="text/css"></link>
<!-- <script type="text/javascript" src="app/cloud_platform/upload/jquery-1.8.0.min.js"></script> -->
<script type="text/javascript" src="app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";	
var owner_id = "";
var folder_id = api.data.root_id;
var beans=null;
var pid = "";
var hiddenType = sessionStorage.getItem("hiddenType");
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 save();
    }
};	
$(function() {
	
	$("#formObj").initForm({
		
		  toolbar:[{text:"返回上级目录", icon:"back",id:"return", click:function(){
			      		var owner_root = sessionStorage.getItem("owner_root");
			    		//alert(pid+"--"+owner_root)
			    		if(pid==""){
							top.$.notice("当前已是顶层目录！");
						}
			    		$.post("resourcePath/queryParentId.do",
			    					{pathId:pid},function(res){
			    				if(res.success){
			    					if(res.parentId==0){
			    						top.$.notice("当前已是顶层目录！");
			    					}else{
			    						ppid = res.parentId;
			        					refreshData(ppid);
			    					}
			    					
			    				}else{
			    					$.ligerDialog.error(res.msg);
			    				}
			    				
			    			})
			    		}
			       },"-",
                   {text:"确认", id:'save',icon:"save", click:save},
                   {text:"关闭", icon:"close", click:function(){
                	  api.close();
                  	 }
               		}]
	});
	refreshData(api.data.root_id);
});

function save(){
	//api.data.window.close();
	$("#save").attr("disabled","disabled");
	if("${param.spaceType}"==9){
		top.$.dialog({
			width : 600,
			height : 450,
			lock : true,
			title : "添加属性",
			content : 'url:app/cloud_platform/court/court_detail.jsp?status=add&spaceType=${param.spaceType}&ids='+api.data.ids+'&parentPathId='+folder_id,
			data : {
				"window" : window
			},close:function(){
				api.data.window.refreshData("",1);
				api.close();
			}
		}); 
		
	}else{
	
	if ($("#formObj").validate().form()) {
		var name = $("#name").val();
		var ids = api.data.ids;
		var beans_old = api.data.window.beans;
		for (var i = 0; i < ids.length; i++) {
			$.ajax({
	            url: "resourceInfo/updateResourceShareStatus.do",
	            type: "POST",
				async:false,
	            data: {id:ids[i],parentPathId:folder_id},
	            success: function (data, stats) {
	               
	            },
	            error: function (data) {
	            	$("#save").removeAttr("disabled"); 
	                $.ligerDialog.error('保存失败！');
	            }
	        });
			
		}
		api.data.window.refreshData("",1);
		 api.close();
	}else{
		$("#save").removeAttr("disabled"); 
	}
	}
	
}
//刷新目录与资源数据
function refreshData(next_id){
	pid = next_id;
	$.post("resourcePath/queryFileByPath.do",{pathId:pid},function(res){
			queryInfo =res.queryInfo;
			$("#mainT").html("");
			 beans = [];
			for (var i = 0; i < queryInfo.length; i++) {
				
				var info = queryInfo[i];
				var id = info.id;
				beans[id] = info;
				var infoType = info.infoType;
				var infoName = info.infoName;
				var infoIsHidden = info.infoIsHidden;
				var infoRemark = info.infoRemark;
				var infoSize = (info.infoSize==null)?'0':info.infoSize;
				var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
				var resourceType = info.resourceType;
				var flag = true;
				//alert(infoName+'---'+infoIsHidden)
				if(hiddenType=='1'&&infoIsHidden=='1'){
					//隐藏模式下，文件的属性为隐藏时则不显示
					flag = false;
				}
				
				if(flag){
					//非隐藏的才显示 '
					var tritem = "<tr class='item' id='"+i+"' "+
					" onclick='selectFile(this,1);'"+
					"oncontextmenu='return false;'"+
					" ondblclick='openFile(this)' value='cs'>"+
					"<td>"+
					"<input type='checkbox'"
					+'ligerui="{initValue:\'\',data:[{text:\'\',id:\'1\'}]}"'+
					" ltype='checkboxGroup' class='check l-checkbox-group' id='"+id+"'"
					+" style='display: inline;' onclick='test(this)'/>"+
					"</td><td class='filename'><span class ='skin' >";
					if(infoType=="1"){
						//文件夹
						tritem = tritem+"<img src='k/kui/images/file-type/16/folder.png' alt='' />";
					}else{
						//文件
						tritem = tritem+"<img src='k/kui/images/file-type/16/file.png' alt='' />";
					}
					
					
					tritem = tritem+infoName+"</span>"+
					"</td><td class='update'>"+last_update_date+"</td><td class='filesize'>";
					if(infoType=="1"){
						//文件夹
					}else{
						//文件
						tritem = tritem+infoSize+"kb";
					}
					
					tritem = tritem+"</td></tr>"
					+"<tr style='height: 3px;'><td colspan='3'></td></tr>";
					$("#mainT").append(tritem);
				}
				
			}
			
	})
}
function test(check1){
	var info = $($(check1).parent()[0]).parent()[0];
	//alert($(info).html())
	selectFile(info,1)
}
//资源选择事件处理
function selectFile(info,flag){
	$(".check").removeAttr("checked");
	$(".item").attr("bgcolor","white");
	
	var id = info.id;
	var checkitem = $($("#"+id).children()[0]).children()[0];
	var sid = $(checkitem).attr("id");
	if($(checkitem).attr("checked")==undefined){
		//选中
		$(checkitem).attr("checked","checked");
		folder_id=sid;
	}else{
		//取消选中
		
		//select.remove(id);
	}
	//$("#"+id).children(".check").attr("check","check");
	//$(".item").attr("bgcolor","white");
	if($(checkitem).attr("checked")=="checked"){
		$("#"+id).attr("bgcolor","#BCD2EE");
	}else{
		$("#"+id).attr("bgcolor","white");
	}
	
	
}

//打开文件夹或者文件的方法
function openFile(info){
	//alert("打开事件！"+select.value)
	if(info!=undefined){
		var id = $(info).attr("id");
		var checkitem = $($("#"+id).children()[0]).children()[0];
		var next_id = $(checkitem).attr("id");
		//获取选择的文件或者文件夹的id信息 
		ids = [];
		ids[ids.length]=next_id;
		select = {};
		select[next_id] = next_id;
		var type = beans[ids].infoType;
		if(type=='1'){
			//文件夹则打开下级目录
			ppid = pid;
			refreshData(ids[0]);
		}else{
			//文件则打开预览
			var name = beans[ids].infoName;
			var name_1 = name.split(".");
			var suffix = name_1[name_1.length-1];
			$.ligerDialog.warn('待写打开文件方法！');
		}
		
	}else{
		if(ids.length>1){
			$.ligerDialog.warn('只能打开单个文件！');
		}else{
			var type = beans[ids].infoType;
			if(type=='1'){
				//文件夹则打开下级目录
				ppid = pid;
				refreshData(ids[0]);
			}else{
				//文件则打开预览
				var name = beans[ids[0]].infoName;
				var name_1 = name.split(".");
				var suffix = name_1[name_1.length-1];
				$.ligerDialog.warn('待写打开文件方法！');
			}
		}
	}
	
}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
			<br />
		<table style="margin-left: 20px;border-bottom-style:solid;
		 	border-bottom-color: gray;" border="0" width="80%" cellpadding="3" cellspacing="3">
			<thead>
				<tr bgcolor="#EDEDED" style="height: 30px;">
				<td>
				<input type="checkbox" name="contract_1" ltype="checkboxGroup"
				 class='check' onchange="checkAll(this)"
								ligerui="{initValue:'',data:[{text:'',id:'1'}]}" style="display: inline;"/>
				</td>
					<td class="filename th">
						名称
					</td>
					
					<td class="update th">
						修改日期
					</td>
					<td class="filesize th">
						大小
					</td>
				</tr>
			</thead>
			<tbody  id ="mainT"></tbody>
		</table>

	</form>
</body>
</html>
