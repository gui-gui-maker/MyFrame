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
<script type="text/javascript" src="app/cloud_platform/owner/js/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/cloud_platform/owner/js/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="app/research/js/encode.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";	
//资源bean 方便后面使用
var beans = [];
//选择的id
var select = {};
//选择资源的ids数组
var ids = [];
//个人空间id
var owner_id = "";
//资源信息列表
var queryInfo =null;
//上级目录的父id
var ppid = "";
//上级目录id
var pid="";
//空间隐藏状态

var hiddenType = "";
//根目录id
var root_id="";
var down = true;
$(function() {
	//初始化数据
	$.post("resourceSpace/openPersonalSpace.do?spaceType=${param.spaceType}",{userId:"<%=userid%>"},function(res){
		//alert(JSON.stringify(res))
			queryInfo =res.queryInfo;
			hiddenType = res.hiddenType;
			if(hiddenType=="0"){
				$("span:contains('隐藏模式')").html("隐藏模式显示")
			}else{
				$("span:contains('隐藏模式')").html("非隐藏模式显示")
			}
			var rootId = res.rootId;
			var spaceId = res.spaceId;
			if(queryInfo==null||queryInfo==undefined){
				//第一次初始化空间
				//个人空间id设值
				owner_id = spaceId;
				//父级id
				pid = spaceId;
				root_id = root_id;
				//把空间id存在会话中
				sessionStorage.setItem("owner_root", owner_id);
				sessionStorage.setItem("owner_root_id", root_id);
				sessionStorage.setItem("hiddenType", hiddenType);
			}else{
				//已有个人空间
				owner_id = spaceId;
				pid = rootId;
				root_id = rootId;
				//把空间id存在会话中
				sessionStorage.setItem("owner_root", owner_id);
				sessionStorage.setItem("owner_root_id", root_id);
				sessionStorage.setItem("hiddenType", hiddenType);
				refreshData(pid);
			}
			
		//	$.ligerDialog.error('获取数据失败！');
		
		
	})
	
	$.post("resourcePath/queryResourceNeedReceive.do",function(res){
			queryInfo =res.queryInfo;
			if(queryInfo.length>0){
				top.$.dialog({
					width : 600,
					height : 450,
					lock : true,
					title : "待接收文件",
					content : 'url:app/cloud_platform/owner/need_recevice.jsp?status=add',
					data : {
						"window" : window
					},
					close:function(){
						refreshData(pid);
					}
				}); 
			}
		}
		)
	
	$("#formObj").initForm({
		
		  toolbar:[
                   {text:"刷新", icon:"refresh",id:"refreshData", click:function(){
		           	   		refreshData(pid);
		           		}
		              },"-",
			        {text:"首页", icon:"home",id:"home", click:function(){
			        	if("${param.spaceType}"=="1"){
				        	$.post("resourceSpace/goUserSpaceSize.do",
	            					{spaceType:"${param.spaceType}"},function(res){
	            				if(res.success){
	            					var useSize = res.useSize;
	            					var maxSize = res.maxSize;
	            					var bl = res.bl;
	            					window.location.href= $("base").attr("href")+"app/cloud_platform/owner/owner_main.jsp?bl="+bl+
	            							"&useSize="+useSize+"&maxSize="+maxSize;
	            				}else{
	            					$.ligerDialog.error(res.msg);
	            				}
	            				
	            			})
				        	}else if("${param.spaceType}"=="9"){
				        		window.location.href= $("base").attr("href")+"app/cloud_platform/court/court_index.jsp";
				        	}else if("${param.spaceType}"=="7"){
				        		window.location.href= $("base").attr("href")+"app/cloud_platform/court/department_index.jsp";
				        	}
		        	}
               }
		              ],
        toolbarPosition :"top",
		getSuccess : function(resp) {
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				
			} else {
				log.error('保存失败' + resp)
			}
		}
	});
	
});
/**
 * 创建上传窗口 公共方法
 * @param chunk 是否分割大文件
 * @param callBack 上传成功之后的回调
 */
function Uploader(chunk,callBack){
	//alert(pid+'--'+owner_id);
	var addWin = $('<div style="overflow: hidden;"/>');
	var upladoer = $('<iframe/>');
	upladoer.attr({'src':'app/cloud_platform/upload/uploader.jsp?chunk='+chunk+'&pid='+pid,width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
	addWin.window({
		title:"上传文件",
		height:350,
		width:550,
		minimizable:false,
		modal:true,
		collapsible:false,
		maximizable:false,
		resizable:false,
		content:upladoer,
		onClose:function(){
			var fw = GetFrameWindow(upladoer[0]);
			var files = fw.files;
			$(this).window('destroy');
			callBack.call(this,files);
		},
		onOpen:function(){
			var target = $(this);
			setTimeout(function(){
				var fw = GetFrameWindow(upladoer[0]);
				fw.target = target;
			},100);
		}
	});
}

/**
 * 根据iframe对象获取iframe的window对象
 * @param frame
 * @returns {Boolean}
 */
function GetFrameWindow(frame){
	return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}
 
function makerUpload(chunk){
	 Uploader(chunk,function(files){
		 if(files && files.length>0){
			 $("#res").text("成功上传："+files.join(","));
		 }
		 refreshData(pid);
	 });
	}
	
	
	
//设置右键菜单框
function showGRMenu(sInfo) {
		var menu = g('menu1');
		
		var evt = window.event || arguments[0];
		/*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
		var rightEdge = sInfo.clientWidth-evt.clientX;
		var bottomEdge = sInfo.clientHeight-evt.clientY;
		/*如果从鼠标位置到容器右边的空间小于菜单的宽度，就定位菜单的左坐标（Left）为当前鼠标位置向左一个菜单宽度*/
		if (rightEdge < menu.offsetWidth) {
		    menu.style.left = sInfo.scrollLeft + evt.clientX - menu.offsetWidth+1 + "px"; 
		}
		
		else{
			/*否则，就定位菜单的左坐标为当前鼠标位置*/
		    menu.style.left = sInfo.scrollLeft + evt.clientX+1 + "px";
			//$("#menu1").css("left",menu.style.left);
		}
		    
		
		/*如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度*/
		if (bottomEdge < menu.offsetHeight){
		    menu.style.top = sInfo.scrollTop + evt.clientY - menu.offsetHeight+1 + "px";
			 //$("#menu1").css("top",menu.style.top);
		}
			
		else{
		    /*否则，就定位菜单的上坐标为当前鼠标位置*/
		    menu.style.top = sInfo.scrollTop + evt.clientY+1 + "px";
		}	
		selectFile(sInfo,2);
		/*设置菜单可见*/
		//$("#menu1").show();
		
		//menu.style.visibility = "visible"; 
		return false;
	}

//根据id获取资源
function g(id) {
    return document.getElementById(id);
};

//右键菜单关闭
function closeGRMenu(){
	$("#menu1").hide();
}
//打开右键菜单
function showGRMenu1(){
	$("#menu1").show();
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
			$.post("resourceInfo/openDownLog.do",{logType:'下载',file_id:ids[0],file_type:'2'},function(res){
			})
			var name = beans[ids].infoName;
			var name_1 = name.split(".");
			var suffix = name_1[name_1.length-1];
			//zip,doc,docx,xls,xlsx,ppt,pptx,pdf
			window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id="+next_id+"&proportion=0";
			
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
				refreshData(ids[0]);
			}else{
				//文件则打开预览
				//文件则打开预览
				$.post("resourceInfo/openDownLog.do",{logType:'下载',file_id:ids[0],file_type:'2'},function(res){
			})
				var name = beans[ids[0]].infoName;
				var name_1 = name.split(".");
				var suffix = name_1[name_1.length-1];
				//zip,doc,docx,xls,xlsx,ppt,pptx,pdf
				window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id="+next_id+"&proportion=0";
				
			}
		}
	}
	
}
function getIds(){
	var selects = [];
	for (var i = 0; i < ids.length; i++) {
		var id = [];
		id["id"]=ids[i];
		id["name"]=beans[ids[i]].infoName;
		selects[selects.length]=id;
	}
	
	return selects;
}

function downFile(){
	//只能针对资源
	//alert("下载事件！")
	/* for (var i = 0; i < ids.length; i++) {
		$.post("resourceInfo/openDownLog.do",{logType:'下载',file_id:ids[i],file_type:'2'},function(res){
		})
		$("#aa").attr("href","fileupload2/downloadCompress.do?id="+ids[i]+"&proportion=0");
		$("#down").click();
		//alert($("base").attr("href")+"fileupload2/downloadCompress.do?id="+ids[i]+"&proportion=0")
		//window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id="+ids[i]+"&proportion=0";
	} */
	if(ids.length>1){
		window.location.href = $("base").attr("href")+"fileupload2/downloadPack.do?ids="+ids.toString()+"&proportion=0";
	}else{
		window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id="+ids.toString()+"&proportion=0";
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

//资源选择事件处理
function selectFile(info,flag){
	var id = info.id;
	var checkitem = $($("#"+id).children()[0]).children()[0];
	var sid = $(checkitem).attr("id");
	if($(checkitem).attr("checked")==undefined){
		//选中
		$(checkitem).attr("checked","checked");
		select[sid] = sid;
		ids[ids.length]=sid;
	}else{
		//取消选中
		if(flag =="1"){
			$(checkitem).removeAttr("checked");
			removeByValue(ids,sid);
			delete select[sid];
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
	down = true;
	for (var i = 0; i < ids.length; i++) {
		var type  = beans[ids[i]].infoType;
		if(type=="1"){
			down=false;
		}
	}
	
}

function clear(){
	$("tr").attr("bgcolor","white");
}


function changeSelectStatus(){
	$(".item").attr("bgcolor","white");
}

//刷新目录与资源数据
function refreshData(next_id){
	pid = next_id;
	select = {};
	ids = [];
	$.post("resourceSpace/queryResourceByType.do",{userId:"<%=userid%>",spaceType:"${param.spaceType}",resourceType:"6,3"},function(res){
			queryInfo =res.queryInfo;
			pid = res.pathId;
			$("#mainT").html("");
			 beans = [];
			 var date = "";
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
				var shareUser =  (info.shareUser==null)?'本地':info.shareUser;
				var flag = true;
				//alert(infoName+'---'+infoIsHidden)
				if(hiddenType=='1'&&infoIsHidden=='1'){
					//隐藏模式下，文件的属性为隐藏时则不显示
					flag = false;
				}
				var date1 = "";
				if(last_update_date!="无修改日期"){
					date1 = last_update_date.substring(0,10);
				}else{
					date1 = "无修改日期";
				}
				
				if(flag){
					/* if(date!=date1){
						tritem = "<tr style='height: 3px;'><td colspan='3'>"+date1+"</td></tr>";
						$("#mainT").append(tritem);
						date = date1;
					} */
					//非隐藏的才显示 '
					var name = infoName;
					var name_1 = name.split(".");
					var suffix = name_1[name_1.length-1];
					
					var tritem = "<tr class='item' id='"+i+"' onmousedown='if(event.button == 2) showGRMenu(this)'"+
					"  onclick='selectFile(this,1);'"+
					"onmouseout='closeGRMenu()' oncontextmenu='return false;'"+
					" ondblclick='openFile(this)' value='cs'>"+
					"<td>"+
					"<input type='checkbox' "+
					+"ligerui='{initValue:'',data:[{text:'',id:'1'}]}'"+
					" ltype='checkboxGroup' class='check' id='"+id+"'"
					+" style='display: inline;'/>"+
					"</td><td class='filename'><span class ='skin' >";
					if(infoType=="1"){
						//文件夹
						tritem = tritem+"<img src='k/kui/images/file-type/32/folder.png' alt='' /> ";
					}else{
						var icon = "file.png";
						if(suffix=="docx"||suffix=="doc"){
							icon = "doc1.png";
						}else if(suffix=="pdf"){
							icon = "pdf.png";
						}else if(suffix=="xlsx"||suffix=="xls"){
							icon = "xls.png";
						}else if(suffix=="ppt"||suffix=="pptx"){
							icon = "ppt.png";
						}else if(suffix=="zip"){
							icon = "zip.png";
						}else if(suffix=="jpg"||suffix=="png"||suffix=="gif"){
							icon = "jpg2.png";
						}
						//文件
						tritem = tritem+"<img src='k/kui/images/file-type/32/"+icon+"' alt='' /> ";
					}
					
					
					tritem = "</span> "+tritem+infoName+
					"</td><td class='update'>"+last_update_date+"</td><td class='filesize'>";
					if(infoType=="1"){
						//文件夹
					}else{
						//文件
						tritem = tritem+infoSize+"kB</td>";
					}
					//alert(level)
					//星级
					
					var level = info.level;
					if(level=="1"){
						tritem = tritem+"<td>☆</td>";
					}else if(level=="2"){
						tritem = tritem+"<td>☆☆</td>";
					}else if(level=="3"){
						tritem = tritem+"<td>☆☆☆</td>";
					}else if(level=="4"){
						tritem = tritem+"<td>☆☆☆☆</td>";
					}else if(level=="5"){
						tritem = tritem+"<td>☆☆☆☆☆</td>";
					}else {
						tritem = tritem+"<td></td>";
					}
					
					tritem = tritem+"</td><td class='update th'>"+shareUser+"</td></tr>"
					+"<tr style='height: 3px;'><td colspan='3'></td></tr>";
					$("#mainT").append(tritem);
				}
				
			}
			$('table tr').contextMenu('menu1', {
			      //菜单样式
			      menuStyle: {
			        border: '2px solid #000'
			      },
			      //菜单项样式
			      itemStyle: {
			        fontFamily : 'verdana',
			        backgroundColor : '#EDEDED',
			        color: 'black',
			        border: 'none',
			        padding: '1px'

			      },
			      //菜单项鼠标放在上面样式
			      itemHoverStyle: {
			        color: 'white',
			        backgroundColor: 'blue',
			        border: 'none'
			      },
			      //事件    
			      bindings: 
			          {
			            /* 'open': function(t) {
			            	openFile();
			            }, */
			            'down': function(t) {
			              downFile();
			            },
			            'share': function(t) {
			              fileShare();
			            },
			            'hide': function(t) {
			              fileHide();
			            },
			            'move': function(t) {
				              fileMove();
				          },
				         'reName': function(t) {
				              fileReName();
				         },
				         'del': function(t) {
				        	 deleteFile();
				            },
					     'shareLink': function(t) {
					    	 showShareLink();
					     }
			          },
			          onShowMenu: function(e, menu) {
			        	  if (ids.length > 1) {
			        		  $("#shareLink", menu).remove(); 
			        	  	$("#reName", menu).remove(); 
			        		/* $("#open", menu).remove();  */
			        		if(!down){
			        			$("#down", menu).remove(); 
			        		}
			        		
			        	  } 
			        	  $(e.currentTarget).siblings().removeClass("SelectedRow").end().addClass("SelectedRow"); 
			        	  return menu; 
			        	  }
			    });
			
	})
}
//新建文件夹
function createFolder(){
	
	top.$.dialog({
		width : 400,
		height : 150,
		lock : true,
		title : "新建文件夹",
		content : 'url:app/cloud_platform/owner/create_folder.jsp?status=add',
		data : {
			"window" : window,pid:pid
		}
	}); 
}

//文件分享
function fileShare(){
	selectUnitOrUser(1, 1, 'personId', 'personName',ids.toString());
	/* top.$.dialog({
		width : 400,
		height : 150,
		lock : true,
		title : "分享",
		content : 'url:app/cloud_platform/owner/file_share_detail.jsp?status=add',
		data : {
			"window" : window,select:ids
		}
	}); */
}

//删除文件
function deleteFile(){
		
		$.ligerDialog.confirm("确定删除资源？", function (yes) { 
			if(yes){
				for (var i = 0; i < ids.length; i++) {
					$.post("resourcePath/delResourcePath.do",{delId:ids[i],type:beans[ids[i]].infoType},function(res){
						if(res.success){
							top.$.notice("删除成功！");
							refreshData(pid);
						}else{
							$.ligerDialog.error(res.msg);
						}
						
					})
				}
			}
		})
}
//修改设置隐藏模式
function setSpaceHiddenModel(){
	var hidden_type = sessionStorage.getItem("hiddenType");
	if(hidden_type=="0"){
		$.ligerDialog.confirm("确定设置为隐藏模式？", function (yes) { 
			if(yes){
				$.post("resourceSpace/updateSpaceHidden.do",{id:sessionStorage.getItem("owner_root")},function(res){
					if(res.success){
						top.$.notice("设置成功！");
						hiddenType = "1";
						sessionStorage.setItem("hiddenType", "1");
						refreshData(pid);
						$("span:contains('隐藏模式')").html("非隐藏模式显示")
					}else{
						$.ligerDialog.error(res.msg);
					}
					
				})
			}
		})
		
	}else{
		 top.$.dialog({
				width : 400,
				height : 150,
				lock : true,
				title : "设置隐藏模式",
				content : 'url:app/cloud_platform/owner/check_password.jsp?status=add',
				data : {
					"window" : window,ids:ids.toString(),pid:pid
				}
			}); 
		//$("span:contains('隐藏模式')").html("非隐藏模式")
	}
}

function setF(){
	$("span:contains('隐藏模式')").html("隐藏模式显示")
}

//设置文件或者文件夹隐藏
function fileHide(){
	$.ligerDialog.confirm("确定隐藏资源？", function (yes) { 
		if(yes){
			for (var i = 0; i < ids.length; i++) {
				var type = beans[ids[i]].infoType;
				if(type=="1"){
					//文件夹
					$.ajax({
						url : "resourcePath/hiddenPath.do",
						type : "POST",
						datatype : "json",
						data : {
							id:ids[i]
						},
						success : function(data, stats) {
							$("body").unmask();
							if(!data.success){
								$.ligerDialog.error(res.msg);
							}
						},
						error : function(data, stats) {
							$.ligerDialog.error(res.msg);
						}
					})
				
				}else{
					//文夹
					$.ajax({
						url : "resourceInfo/hiddenResource.do",
						type : "POST",
						datatype : "json",
						data : {
							id:ids[i]
						},
						success : function(data, stats) {
							$("body").unmask();
							if(!data.success){
								$.ligerDialog.error(res.msg);
							}
						},
						error : function(data, stats) {
							$.ligerDialog.error(res.msg);
						}
					})
				
				}
			}
			refreshData(pid);
		}
	})
}

//重命名
function fileReName(){
	var name = beans[ids[0]].infoName;
	var type = beans[ids[0]].infoType;
	var name_1 = name.split(".");
	var suffix = name_1[name_1.length-1];
	name = name.substring(0,name.length-suffix.length-1);
	top.$.dialog({
		width : 400,
		height : 150,
		lock : true,
		title : "文件重命名",
		content : 'url:app/cloud_platform/owner/file_reName.jsp?status=add',
		data : {
			"window" : window,ids:ids.toString(),pid:pid,fileName:name,suffix:suffix,
			type:type
			
		}
	}); 
}
//文件移动
function fileMove(){
	var root_id = sessionStorage.getItem("owner_root_id");
	top.$.dialog({
		width : 600,
		height : 450,
		lock : true,
		title : "选择文件夹",
		content : 'url:app/cloud_platform/owner/select_folders.jsp?status=add',
		data : {
			"window" : window,ids:ids,pid:pid,root_id:root_id
		}
	}); 
}

function showShareLink(){
	if(ids.length>1){
		$.ligerDialog.warn('只能分享单个文件的下载链接！');
	}else{
		var type = beans[ids[0]].infoType;
		if(type=='1'){
			//文件夹则打开下级目录
			$.ligerDialog.warn('不能分享文件夹的下载链接！');
		}else{
			
			var name = beans[ids[0]].infoName;
			var  link = "http://y.scsei.org.cn/?name="+encodeURI(name);
			top.$.dialog({
				width : 600,
				height : 200,
				lock : true,
				title : "文件分享链接",
				content : 'url:app/cloud_platform/show_down_link.jsp?status=add',
				data : {
					"window" : window,link:link
				}
			}); 
			
		}
	}
}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
		<div style="width:100%; height:380px; ">
			<div style="width:100%; height:80%;">
				<%@include file="show_file_page1.jsp" %>
			</div>
			<div style="display: none">
				<a id="aa" href = ""><span id="down"></span></a>
			</div>
		</div>
	 <div class="contextMenu" id="menu1">
       <ul>
        <!--  <li id="open"> 打开</li> -->
         <li id="down"> 下载</li>
         <li id="share">分享</li>
         <li id="shareLink">分享链接</li>
       </ul>
     </div>
	</form>
</body>
</html>
