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
var folder_id = "";
var beans=null;
var ids = [];
var hiddenType = sessionStorage.getItem("hiddenType");
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 //save();
    }
};	
$(function() {
	if("${param.type}"=="1"){
		$("#formObj").initForm({
			
			  toolbar:[
	                   {text:"接收", id:'save',icon:"save", click:save},
	                   {text:"不接收", id:'refuse',icon:"cancel", click:refuse},
	                   {text:"首页", icon:"back", click:function(){
	                		   window.location.href= $("base").attr("href")+"app/cloud_platform/court/court_main.jsp";

	                	   }	                	 
	                   	 }
	                		 ],
	                		 toolbarPosition :"top"
		});
	}else{
		$("#formObj").initForm({
			
			  toolbar:[
	                   {text:"接收", id:'save',icon:"save", click:save},
	                   {text:"不接收", id:'refuse',icon:"cancel", click:refuse}]
	
	});
	}
	refreshData("",1);
});

function save(){
	$("#save").attr("disabled","disabled");
	if (ids.length>0) {
		//resourcePath  file_type
		
			var root_id = sessionStorage.getItem("owner_root_id");
			top.$.dialog({
				width : 600,
				height : 450,
				lock : true,
				title : "选择文件夹",
				content : 'url:app/cloud_platform/owner/receive_folders.jsp?status=add&spaceType=${param.spaceType}',
				data : {
					"window" : window,ids:ids,pid:pid,beans:beans,root_id:root_id
				},close:function(){
					window.close();
				}
			}); 
		
	}else{
		top.$.notice("请选择接收文件！");
		$("#save").removeAttr("disabled"); 
	}
	
}

function refuse(){
	//alert(ids.length+"---"+ids)
	var root_id="";
	for (var i = 0; i < ids.length; i++) {
		$.ajax({
            url: "resourcePath/refuseShare.do",
            type: "POST",
			async:false,
            data: {delId:ids[i],type:beans[ids[i]].infoType},
            success: function (data, stats) {
            	root_id=api.data.root_id;
            },
            error: function (data) {
            	$("#save").removeAttr("disabled"); 
                $.ligerDialog.error('保存失败！');
            }
        });
		
	}
	refreshData(root_id,1);
}

//刷新目录与资源数据
function refreshData(next_id,flag){
	ids=[];
	var url= "resourcePath/queryResourceNeedReceive.do?spaceType=${param.spaceType}";
	if(flag==1){
		url= "resourcePath/queryResourceNeedReceive.do?spaceType=${param.spaceType}";
	}else{
		url= "resourcePath/queryResourceByPath.do";
	}
	pid = next_id;
	$.post(url,{pathId:pid},function(res){
			queryInfo =res.queryInfo;
			pid = res.pathId;
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
				var shareUser =  (info.shareUser==null)?'':info.shareUser;
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
					"ondblclick='openFile(this)' value='cs'>"+
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
					
					tritem = tritem+"</td><td class='update th'>"+shareUser+"</td></tr>"
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
	var id = info.id;
	var checkitem = $($("#"+id).children()[0]).children()[0];
	var sid = $(checkitem).attr("id");
	//alert($(checkitem).attr("checked")+"---"+sid)
	if($(checkitem).attr("checked")==undefined){
		//选中
		$(checkitem).attr("checked","checked");
		ids[ids.length]=sid;
	}else{
		//取消选中
		if(flag =="1"){
			$(checkitem).removeAttr("checked");
			removeByValue(ids,sid);
		}
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
	//alert("触发打开事件！"+info.value)
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
			folder_ids[folder_ids.length]= beans[next_id];
			//alert(folder_ids)
			refreshData(next_id);
			
		}else{
			//文件则打开预览
			$.post("resourceInfo/openDownLog.do",{logType:'预览',file_id:next_id,file_type:'2'},function(res){
			})
			var name = beans[next_id].infoName;
			var name_1 = name.split(".");
			var suffix = name_1[name_1.length-1].toLowerCase();
			//zip,doc,docx,xls,xlsx,ppt,pptx,pdf
			if(suffix=="doc"||suffix=="docx"||suffix=="pdf"||suffix=="xlsx"
				||suffix=="xls"||suffix=="ppt"||suffix=="pptx"){
				top.$.dialog({
					width : 900,
					height : 800,
					lock : true,
					title : "预览",
					content : 'url:app/cloud_platform/owner/doc_view.jsp?status=add',
					data : {
						"window" : window,id:ids
					},
				      close:function (){
				    	  if($(checkitem).attr("checked")!="checked"){
								removeByValue(ids,next_id);
							} 
				      }
				}); 
			}else if(suffix=="jpg"||suffix=="png"||suffix=="gif"
					||suffix=="JPG"||suffix=="PNG"||suffix=="GIF"){
				//jpg,png,gif
				var previewData = {
					     first: 0,           //首先显示的文件序号（数组元素序号）
					     images: [       //图片文件列表数组
					            {
					             id:ids,      //图片文件id
					             name:"cs" //图片文件名称
					            }
					     ]
					};
				top.$.dialog({
				     title: name,
				      width: $(top).width(),
				      height: $(top).height(),
				      resize: false,
				      max: false,
				      min: false,
				      content: "url:pub/fileupload1/fileupload/preview2.jsp",
				      data: previewData,
				      close:function (){
				    	  if($(checkitem).attr("checked")!="checked"){
								removeByValue(ids,next_id);
							} 
				      }
				  });
				
			}else{
				downFile();
				  if($(checkitem).attr("checked")!="checked"){
						removeByValue(ids,next_id);
					} 
			}
			
			
			//$.ligerDialog.warn('待写打开文件方法！');
		}
		
	}else{
		if(ids.length>1){
			$.ligerDialog.warn('只能打开单个文件！');
		}else{
			
			var type = beans[ids[0]].infoType;
			if(type=='1'){
				//文件夹则打开下级目录
				ppid = pid;
				folder_ids[folder_ids.length]= beans[ids[0]];
				//alert(folder_ids)
				refreshData(ids[0]);
			}else{
				$.post("resourceInfo/openDownLog.do",{logType:'预览',file_id:ids[0],file_type:'2'},function(res){
				})
				//文件则打开预览
				var name = beans[ids[0]].infoName;
				var name_1 = name.split(".");
				var suffix = name_1[name_1.length-1].toLowerCase();
				
				//zip,doc,docx,xls,xlsx,ppt,pptx,pdf
				if(suffix=="doc"||suffix=="docx"||suffix=="pdf"||suffix=="xlsx"
					||suffix=="xls"||suffix=="ppt"||suffix=="pptx"){
					top.$.dialog({
						width : 900,
						height : 800,
						lock : true,
						title : "预览",
						content : 'url:app/cloud_platform/owner/doc_view.jsp?status=add',
						data : {
							"window" : window,id:ids
						}
					}); 
				}else if(suffix=="jpg"||suffix=="png"||suffix=="gif"
					||suffix=="JPG"||suffix=="PNG"||suffix=="GIF"){
					//jpg,png,gif
					var selectIds =  getIds();
					var previewData = {
						     first: 0,           //首先显示的文件序号（数组元素序号）
						     images: selectIds
						};
					top.$.dialog({
					     title: name,
					      width: $(top).width(),
					      height: $(top).height(),
					      resize: false,
					      max: false,
					      min: false,
					      content: "url:pub/fileupload1/fileupload/preview2.jsp",
					      data: previewData
					  });
					
				}else{
					downFile();
				}
			}
		}
	}
	
}
//删除数组里面的某个元素
function removeByValue(arr, val) {
	  for(var i=0; i<arr.length; i++) {
	    if(arr[i] == val) {
	      arr.splice(i, 1);
	      break;
	    }
	  }
	}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
			<br />
		<table style="margin-left: 20px; border="0" width="95%" cellpadding="3" cellspacing="3">
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
					<td class="filesize th" style="width: 50px;">
						大小
					</td>
					<td class="update th" style="width: 120px;">
						来源
					</td>
				</tr>
			</thead>
			<tbody  id ="mainT"></tbody>
		</table>

	</form>
</body>
</html>
