<%@page import="util.TS_Util"%>
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
<link href="app/cloud_platform/down/css/common_down.css" rel="stylesheet" type="text/css">
<style type="text/css">
filename{
	width: 50%;
}
filesize{
	width: 10%;
}
xsize{
	width: 10%;
}
update{
	width: 30%;
}
head{
	background-color: #4F94CD;
}
.th{
	border-right-style: solid;
	border-right-color: white;
}
</style>
<script type="text/javascript">
var p = 1;
var sump =2;
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
//排序字段
var sort_field="";
//排序的方式（desc,asc）
var sort_type=null;
var hiddenType = null;
//根目录id
var root_id="";
var down = true;
var queryInfoM = null;
$(function() {
	$("#toolbar1").ligerToolBar({
		items: [
	               {text:"文件上传", icon:"submit",id:"makerUpload", click:function(){
	            	   makerUpload(false);
	                   }
	               },"-",
	               {text:"刷新", icon:"refresh",id:"refreshData1", click:function(){
	            	   		refreshData1(pid,null);
	            		}
	               },"-",
	              {text:"删除", icon:"del",id:"delete", click:function(){
			                	  if(ids.length<1){
			                		  //alert("请选择要删除的文件");
			                	  }else{
			                		  deleteFile();
			                	  }
				        	}
		             }]
	}); 
	
	/* $("#search").click(function(){
    	
    }) */
	//初始化数据
	$.post("resourceSpace/openPersonalSpace.do?spaceType=9",{userId:"<%=userid%>"},function(res){
		////alert(JSON.stringify(res))
			queryInfo =res.queryInfo;
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
				refreshData1(pid);
			}
			
		//	$.ligerDialog.error('获取数据失败！');
		
	})

	var btn = [
               {text:"文件上传", icon:"submit",id:"makerUpload", click:function(){
            	   makerUpload(false);
                   }
               },"-",
               {text:"刷新", icon:"refresh",id:"refreshData1", click:function(){
            	   		refreshData1(pid,null);
            		}
               },"-",
              {text:"删除", icon:"del",id:"delete", click:function(){
		                	  if(ids.length<1){
		                		  //alert("请选择要删除的文件");
		                	  }else{
		                		  deleteFile();
		                	  }
			        	}
	             }];
	 var ua = window.navigator.userAgent;
	 document.onkeydown=function(event){
		    var e = event || window.event || arguments.callee.caller.arguments[0];           
		     if(e && e.keyCode==13){ // enter 键
		    	 $("#search").click();
		    }
		};	
	/* $("#formObj").initForm({
		
		  toolbar:btn,
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
	$("#formObj").submit(function(){
		 var com_name = $("#com_name").val();
    	if(com_name==""||com_name=="搜索您的文件"){
    		//$.ligerDialog.
    		com_name  = null;
    	}
    	
    	refreshData1(pid,com_name); 
		return;
	}) */
	/* document.onkeydown=function(event){
	    var e = event || window.event || arguments.callee.caller.arguments[0];           
	     if(e && e.keyCode==13){ // enter 键
	    	 $("#search").click();
	    }
	};	 */
});

/**
 * 创建上传窗口 公共方法
 * @param chunk 是否分割大文件
 * @param callBack 上传成功之后的回调
 */
function Uploader(chunk,callBack){
	///if_down 参数用来确定是下载中心文件，可供外网不用登陆检索
	var addWin = $('<div style="overflow: hidden;"/>');
	var upladoer = $('<iframe/>');
	upladoer.attr({'src':'app/cloud_platform/upload/uploader.jsp?chunk='+chunk+'&pid='+pid+"&spaceType=${param.spaceType}&if_down=1",width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
	addWin.window({
		title:"上传文件",
		height:380,
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
		 refreshData1(pid,null);
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
			refreshData1(next_id,null);
			
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
					width : '90%',
					height : '95%',
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
				refreshData1(ids[0],null);
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
						width : '90%',
						height : '95%',
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
	////alert("下载事件！")
	/* for (var i = 0; i < ids.length; i++) {
		$.post("resourceInfo/openDownLog.do",{logType:'下载',file_id:ids[i],file_type:'2'},function(res){
		})
		$("#aa").attr("href","fileupload2/downloadCompress.do?id="+ids[i]+"&proportion=0");
		$("#down").click();
		////
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
	//alert("触发选择事件")
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
	////$(".item").attr("bgcolor","white");
	if($(checkitem).attr("checked")=="checked"){
		//$("#"+id).attr("bgcolor","#BCD2EE");
	}else{
		//$("#"+id).attr("bgcolor","white");
		removeByValue(ids,sid);
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
	//$(".item").attr("bgcolor","white");
}

//刷新目录与资源数据
function refreshData1(next_id,name){
	pid = '4028839458af31b40158d23e06590011';
	select = {};
	ids = [];
	p = 1;
	var param = {pathId:pid,orderName:sort_field,orderBy:sort_type,name:name,orgF:"1"};
	if("<%=userid%>"=="402884c4477c9bac01477fe0d188001b"){
		param = {pathId:pid,orderName:sort_field,orderBy:sort_type,name:name};
	}
	//resourcePath/queryResourceByPath.do
	$.post("resourcePath/queryDownResourceByPath.do",param,function(res){
		$("body").unmask();	
		queryInfo =res.queryInfo;
			pid = res.pathId;
			$("#cloadList").html("");
			$("#filePages").hide();
			 beans = [];
			 queryInfoM = queryInfo
			 var sump1 = queryInfo.length/20;
			 var l = (sump1+"").split(".").length;
			 if(l>1){
				 sump1 =  ((sump1+"").split(".")[0]-0)+1
			 }else{
				 sump1 =  ((sump1+"").split(".")[0]-0)
			 }
			 sump = sump1;
			 if(queryInfo.length>0){
					$("#cloadList").append(
						    '<div class="Per_info">'
							+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
							' class="per_tab" style="text-align: left;" id="cloadLists">'
							+'<tr>'
							+'<th style="width: 20px;"><input type="checkbox" name="contract_1" ltype="checkboxGroup"'
							+'onchange="checkAll(this)"'
							+	'ligerui="{initValue:\'\',data:[{text:\'\',id:\'1\'}]}" style="display: inline;"/></th>'
							+'<th style="width: 50px;text-align: center;">类型</th>'
							+'<th>文件名称</th>'
							+'<th>查看地址</th>'
							+'<th style="width: 200px;">修改日期</th>'
							+'<th style="width: 100px;">大小</th>'
							+'<th style="width: 100px;">来源</th>'
							+'</tr></table><div>	');
					var s3 = queryInfo.length;
					if(s3>20){
						s3=20
					}
					for (var i = 0; i < s3; i++) {
							
							var info = queryInfo[i];
							var id = info.id;
							beans[id] = info;
							var infoType = info.infoType;
							var infoName = info.infoName;
							var infoIsHidden = info.infoIsHidden;
							var infoRemark = info.infoRemark;
							var infoSize = (info.infoSize==null)?'0':info.infoSize;
							var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
							var shareUser =  (info.shareUser==null)?'本地':info.shareUser;
							var resourceType = info.resourceType;
							var flag = true;
							var icon = "file.png";
							var name = infoName;
							var name_1 = name.split(".");
							var suffix = name_1[name_1.length-1];
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
						var tritem = "<img src='k/kui/images/file-type/32/"+icon+"' alt='' /> ";
						var td = '<tr  style="text-align: left;" class="item" id="'+i
						+'" onmousedown="if(event.button == 2) showGRMenu(this)"'+
						'  onclick="selectFile(this,1);"'+
						'onmouseout="closeGRMenu()" oncontextmenu="return false;"'+
						' value="cs">'
							+'<td style="width: 20px;text-align: left;">'+
							'<input type="checkbox" '
							+'ligerui="{initValue:\'\',data:[{text:\'\',id:\'1\'}]}"'+
						" ltype='checkboxGroup' class='check l-checkbox-group' id='"+id+"'"
						+" style='display: inline;' onclick='test(this)'/>"
							+'</td>'
							+'<td style="width: 50px;text-align: center;">'+tritem+ '</td>'
							+'<td  style="text-align: left;" >'+infoName+'</td>'
						+'<td  style="text-align: left;" ><textarea  ondblclick="selectOneTd(\'name'+i
						+'\')" rows="1" style="border:0pt;margin:0;padding:0;'
						+'height:30px;outline:none;display:inline;" cols="" id="name'+i
						+'" readonly contenteditable="false">http://y.scsei.org.cn/?'+id
						+'</textarea> </td>'
						+'<td style="width: 200px;text-align: left;">'+last_update_date+ '</td>'
						+'<td style="width: 100px;text-align: left;">'+infoSize+"kB</td>"
						+'<td style="width: 100px;text-align: left;">'+shareUser+ '</td>'
						
						/* if(insp.resourceType=='2'){
							td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
						} */
						td = td + '</td><tr>'
						$("#cloadLists").append(td);
						
						 if(queryInfo.length>20){
							$("#filePages").show();
							$("#nowPage").html(p)
							$("#sumC").html(sump1);
						}
					}
				}else{
					$("#cloadList").append(
						    '<div class="Per_info" align="center">没有查询到文件！</div>')
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
			            'open': function(t) {
			            	openFile();
			            },
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
				          'devote':function(t){
				        	  devoteFile();
				          },
						     'shareLink': function(t) {
						    	 showShareLink();
						     }
			          },
			          onShowMenu: function(e, menu) {
			        	  //alert(ids)
			        	
			        	  if (ids.length > 1) {
			        		  $("#shareLink", menu).remove(); 
			        	  	$("#reName", menu).remove(); 
			        		$("#open", menu).remove(); 
			        		if(<%=TS_Util.getCurOrg(user).getId()%>!="100028"){
			        			$("#shux", menu).remove(); 
			        		}
			        	  } 
			        	  if(!down){
			        			$("#down", menu).remove(); 
			        			$("#hide", menu).remove(); 
			        			$("#devote", menu).remove(); 
			        			
			        		}
			        	  $(e.currentTarget).siblings().removeClass("SelectedRow").end().addClass("SelectedRow"); 
			        	  return menu; 
			        	  }
			    });
			
	})
}
//属性
function shux(){
	var url='';
	if(<%=TS_Util.getCurOrg(user).getId()%>=="100028"){
	if(ids.length>1){
		url='app/cloud_platform/court/court_detail.jsp?status=add&ids='+ids;
	}else{
		url='app/cloud_platform/court/court_detail.jsp?status=modify&ids='+ids;
	}
	}else{
		url='app/cloud_platform/court/court_detail.jsp?status=detail&ids='+ids;
	}
	top.$.dialog({
		width : 600,
		height : 450,
		lock : true,
		title : "文件属性",
		content : 'url:'+url,
		data : {
			"window" : window
		}
	}); 
	
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
	selectUnitOrUser("30", 1, 'personId', 'personName',ids.toString());
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
					$.post("resourcePath/delResourcePath.do",{delId:ids[i],type:beans[ids[i]].infoType,spaceType:'9'},function(res){
						if(res.success){
							top.$.notice("删除成功！");
							refreshData1(pid,null);
						}else{
							$.ligerDialog.error(res.msg);
						}
						
					})
				}
			}
		})
}

function editResourceImportantFlag(){
	top.$.dialog({
		width : 400,
		height : 150,
		lock : true,
		title : "文件星级",
		content : 'url:app/cloud_platform/owner/edit_resource_important_flag.jsp?status=add',
		data : {
			"window" : window,ids:ids
		}
	});
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

function uploadBigFile(){
	top.$.dialog({
		width : 550,
		height :150,
		lock : true,
		title : "选择文件夹",
		content : 'url:app/cloud_platform/upload/big_file_upload.jsp?status=add&spaceType=${param.spaceType}',
		data : {
			"window" : window,pid:pid,root_id:root_id
		}
	}); 
}

function refreshFolder(){
	var l = folder_ids.length;
	var folder = "所在位置：";
	if(l>1){
		folder = folder + '<a  href="javascript:openFolder(0,\''+folder_ids[0]+'\')" >根目录</a> >>';
	}else{
		folder = folder + '根目录';
	}
	for (var i = 1; i < l-1; i++) {
		folder = folder + '<a  href="javascript:openFolder('+i+',\''+folder_ids[i].id+'\')" >'+folder_ids[i].infoName+'</a> >> ';
	}
	if(l>1){
		folder = folder + folder_ids[l-1].infoName;
	}
	$("#folder").html(folder)
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
			var  link = "http://y.scsei.org.cn/?"+beans[ids[0]].id;
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


function checkAll(c){
	
	if ($(c).attr("checked")&&$(c).val()==1) {
		$(".check").attr("checked","checked");
		//$(".item").attr("bgcolor","#BCD2EE");
		ids = [];
		$(".check").each(
				function() {
					selectFile1(this);
					//ids[ids.length] =$(this).attr("checked");
				}) 
	}else{
		$(".check").removeAttr("checked");
		//$(".item").attr("bgcolor","white");
		select = {};
		ids = [];
	}
}

//资源选择事件处理
function selectFile1(info){
	var sid = $(info).attr("id");
	if($("#sid").attr("checked")==undefined){
		//选中
		$("#sid").attr("checked","checked");
		ids[ids.length]=sid;
	}
	
	down = true;
	for (var i = 0; i < ids.length; i++) {
		var type  = beans[ids[i]].infoType;
		if(type=="1"){
			down=false;
		}
	}
	
}

function selectOneTd(textbox){  
	   var doc = document.getElementById(textbox);
	 var l = doc.value.length;
	   if(doc.setSelectionRange){  //支持setSelectionRange()方法时  
		   doc.setSelectionRange(0,l);  
	   }else {  //不支持setSelectionRange()方法时  
	      var range=doc.createTextRange(); //创建范围  
	      range.collapse(true); //将范围折叠到文本框开始处  
	      //使用moveStart()和moveEnd()方法将范围移动到位  
	      range.moveStart('character',0);  
	      range.moveEnd('character',l);  
	      range.select(); //选择文本  
	      doc.focus(); //想要在文本框中看到文本被选择的效果，还必须让文本框获得焦点  
	   }
	} 

function indexSerachClick(){
	var com_name = $("#com_name").val();
	if(com_name==""||com_name=="搜索您的文件"){
		//$.ligerDialog.
		com_name  = null;
	}
	$("body").mask("正在查询数据，请稍后！");
	refreshData1(pid,com_name);
}

function hoverThis(ss){
	if(p==1&&$(ss).attr("id")=="pre"){
		$(ss).css("color","gray");
	}else if(p==sump&&$(ss).attr("id")=="next"){
		$(ss).css("color","gray");
	}else{
		$(ss).css("color","blue");
	}
	
}
function outThis(ss){
	if(p==1&&$(ss).attr("id")=="pre"){
		$(ss).css("color","gray");
	}else if(p==sump&&$(ss).attr("id")=="next"){
		$(ss).css("color","gray");
	}else{
	$(ss).css("color","black");
	}
}
function toNextPage(){
	if((p+1)<=sump){
		p= p+1;
		$("#nowPage").html(p);
		refreshData(p-1);
		/* $("body").mask("正在查询，请稍后！");
		 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n; */
	}
}

function toPerPage(){
	if(p>1){
		p= p-1;
		$("#nowPage").html(p);
		refreshData(p-1);
		/* $("body").mask("正在查询，请稍后！");
		 window.location.href=$("base").attr("href")+"app/research/news_list.jsp?status=add&name="+name1+"&page="+n; */
		
	}
}

//刷新目录与资源数据
function refreshData(page){
			select = {};
			ids = [];
			//resourcePath/queryResourceByPath.do
			var s = page*20;
			var e = page*20+20;
			//alert(s+"---"+e+"--"+cloadList1.length )
			if(queryInfoM.length<e){
				e=queryInfoM.length;
			}
	
			var td  = "";
	
			$("#cloadList").html("");
			 beans = [];
			 $("#cloadList").append(
						    '<div class="Per_info">'
							+'<table width="100%" border="0" cellspacing="0" cellpadding="0"'+
							' class="per_tab" style="text-align: left;" id="cloadLists">'
							+'<tr>'
							+'<th style="width: 20px;"><input type="checkbox" name="contract_1" ltype="checkboxGroup"'
							+'onchange="checkAll(this)"'
							+	'ligerui="{initValue:\'\',data:[{text:\'\',id:\'1\'}]}" style="display: inline;"/></th>'
							+'<th style="width: 50px;text-align: center;">类型</th>'
							+'<th>文件名称</th>'
							+'<th>查看地址</th>'
							+'<th style="width: 200px;">修改日期</th>'
							+'<th style="width: 100px;">大小</th>'
							+'<th style="width: 100px;">来源</th>'
							+'</tr></table><div>	');
					for (var i = s; i < e; i++) {
							
							var info = queryInfoM[i];
							var id = info.id;
							beans[id] = info;
							var infoType = info.infoType;
							var infoName = info.infoName;
							var infoIsHidden = info.infoIsHidden;
							var infoRemark = info.infoRemark;
							var infoSize = (info.infoSize==null)?'0':info.infoSize;
							var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
							var shareUser =  (info.shareUser==null)?'本地':info.shareUser;
							var resourceType = info.resourceType;
							var flag = true;
							var icon = "file.png";
							var name = infoName;
							var name_1 = name.split(".");
							var suffix = name_1[name_1.length-1];
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
						var tritem = "<img src='k/kui/images/file-type/32/"+icon+"' alt='' /> ";
						var td = '<tr  style="text-align: left;" class="item" id="'+i
						+'" onmousedown="if(event.button == 2) showGRMenu(this)"'+
						'  onclick="selectFile(this,1);"'+
						'onmouseout="closeGRMenu()" oncontextmenu="return false;"'+
						' value="cs">'
							+'<td style="width: 20px;text-align: left;">'+
							'<input type="checkbox" '
							+'ligerui="{initValue:\'\',data:[{text:\'\',id:\'1\'}]}"'+
						" ltype='checkboxGroup' class='check l-checkbox-group' id='"+id+"'"
						+" style='display: inline;' onclick='test(this)'/>"+
							'</td>'
							+'<td style="width: 50px;text-align: center;">'+tritem+ '</td>'
							+'<td  style="text-align: left;" >'+infoName+'</td>'
						+'<td  style="text-align: left;" ><textarea  ondblclick="selectOneTd(\'name'+i
						+'\')" rows="1" style="border:0pt;margin:0;padding:0;'
						+'height:30px;outline:none;display:inline;" cols="" id="name'+i
						+'" readonly contenteditable="false">http://y.scsei.org.cn/?'+id
						+'</textarea> </td>'
						+'<td style="width: 200px;text-align: left;">'+last_update_date+ '</td>'
						+'<td style="width: 100px;text-align: left;">'+infoSize+"kB</td>"
						+'<td style="width: 100px;text-align: left;">'+shareUser+ '</td>'
						
						/* if(insp.resourceType=='2'){
							td = td + '/<a href="javascript:showFile(\''+insp.id+'\',\''+insp.infoName+'\',\''+insp.infoType+'\')">预览</a>';
						} */
						td = td + '</td><tr>'
						$("#cloadLists").append(td);
						/* if(sump>1){
							$("#filePages").show();
						} */
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
			            'open': function(t) {
			            	openFile();
			            },
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
				          'devote':function(t){
				        	  devoteFile();
				          },
						     'shareLink': function(t) {
						    	 showShareLink();
						     }
			          },
			          onShowMenu: function(e, menu) {
			        	  //alert(ids)
			        	
			        	  if (ids.length > 1) {
			        		  $("#shareLink", menu).remove(); 
			        	  	$("#reName", menu).remove(); 
			        		$("#open", menu).remove(); 
			        		if(<%=TS_Util.getCurOrg(user).getId()%>!="100028"){
			        			$("#shux", menu).remove(); 
			        		}
			        	  } 
			        	  if(!down){
			        			$("#down", menu).remove(); 
			        			$("#hide", menu).remove(); 
			        			$("#devote", menu).remove(); 
			        			
			        		}
			        	  $(e.currentTarget).siblings().removeClass("SelectedRow").end().addClass("SelectedRow"); 
			        	  return menu; 
			        	  }
	 			})
}


function test(check1){
	var info = $($(check1).parent()[0]).parent()[0];
	//alert($(info).html())
	selectFile(info,1)
}


</script>
</head>
<body>
<div class="item-tm">
		<div id="toolbar1"></div>
	</div>

<div class="s_n_bg"></div>

<div class="ser_top">
	
	
	
	<div class="w854">
	
	<div class="ser_t_lo"></div>
	<div class="searchTextbg2">
            <input id="com_name" class="text ng-pristine ng-valid" type="text" ng-model="searchText" ng-class="{'promptTextRed':promptTextRed}" ng-focus="promptTextRedFocus()" ng-keyup="keyupClick($event)" placeholder="请输入文件名称">
            <input class="btn" type="button" onclick="indexSerachClick()"  id="search" >
    </div>
    </div>
	
</div>

<div class="ser_cont" style="display: block;overflow: auto;height: 72%;">
	<div class="search_box" id="cloadList">
	
		  				
		</div>	




</div>
<div align="center" style="font-size: 16px;color: black;display: none;" id="filePages">
	<span style="cursor:default;" id="pre" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toPerPage()" >上一页</span>
	   <span style="margin-right: 20px;margin-left: 20px;font-size: 14px;" >第<span id="nowPage">1</span>页,共<span id="sumC">1</span>页</span>
	   <span style="cursor:default;" id="next" onmouseover="hoverThis(this)"  onmouseout="outThis(this)" onclick="toNextPage()">下一页</span></div>

	 <div class="contextMenu" id="menu1">
       <ul>
         <li id="open"> 打开</li>
         <li id="down"> 下载</li>
         <li id="shareLink">分享链接</li>
         <li id="reName">重命名</li>
         <li id="del"> 删除</li>
       </ul>
     </div>
	<!-- </form> -->
</body>
</html>
