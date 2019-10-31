<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Map"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="util.TS_Util"%>
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>院空间</title>
<link rel="stylesheet" type="text/css" href="css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/animate.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/responsive-tabs.css " />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/easyResponsiveTabs.js"></script>
<script type="text/javascript" src="/app/cloud_platform/owner/js/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="/app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<%String noticeData = ReportUtil.getNotice("新闻公告");
JSONArray auditData=TS_Util.getOaAuditNum();
CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String user=sessionUser.getSysUser().getName();
String userBm= sessionUser.getDepartment().getId();
Map<String,String> roles=sessionUser.getRoles();
%>

<!--[if lt IE 9]> 

<script type="text/javascript" src="PIE/PIE.js"></script> 

<![endif]-->

<script>
//资源bean 方便后面使用
var beans = [];
//选择的id
var select = {};
var ids="";
$(function() { 
	queryResourceByType();
if (window.PIE) { 

        $('.rounded').each(function() { 

PIE.attach(this); 

        }); 

    } 
$("#search").click(function(){
	var com_name = $("#com_name").val();
	if(com_name==""){
		//$.ligerDialog.
		alert('请填写查询条件！');
		return;
	}
	top.$.dialog({
		width : 800,
		height :600,
		lock : true,
		title : "文件查询",
		content : 'url:app/cloud_platform/owner/show_file_query.jsp?status=add&spaceType=7',
		data : {
			"window" : window,name:com_name
		}
	});
})

//获取新闻公告数据
var noticeData = <%=noticeData%>;
//获取待处理数据
var auditData=<%=auditData%>;

//alert(noticeData);
// var jsonNoticeData = $.parseJSON(noticeData);
if(noticeData!=null){
   
    var noticeUl = $('.n_l_box_list');
    var auditUl=$('.event_list');
    var l = noticeData.length;
    var s =auditData.length;
 	if(l>4){
 		l=4;
 	}
 	if(s>4){
 		s=4;
 	}
 	  for (var i = 0; i < l; i++) {
 	    	if(i==0){
 	    		//noticeUl.append($('<li id="wordList'+(i+1)+'" style="display: list-item;" data-rel="wordList'+(i+1)+'"><a href="javascript:openDialog(\''+noticeData[i].title+'\',\''+noticeData[i].url+'\')">'+noticeData[i].title+'</a></li>'));	
 	    		noticeUl.append($('<li id="wordList'+(i+1)+'" style="display: list-item;" data-rel="wordList'+(i+1)+'"><div class="n_l_box_list_bt"><a  target="_blank"  href="'+noticeData[i].url+'">'+noticeData[i].title+'</a></div><div class="n_l_box_time">'+noticeData[i].date+'</div></li>'));	
 	    		
 	    	}else{
 	    		noticeUl.append($('<li id="wordList'+(i+1)+'" data-rel="wordList'+(i+1)+'"><div class="n_l_box_list_bt"><a  target="_blank"  href="'+noticeData[i].url+'">'+noticeData[i].title+'</a></div><div class="n_l_box_time">'+noticeData[i].date+'</div></li>'));	
 	    		
 	    		}
 	    	
 		}
 	    for(var i = 0; i < s; i++){
 	    	if(auditData.length>0){
 	    		auditUl.append($("<li><p><span><a href='javascript:void(0);' onclick=\"openDialog('待处理','"+auditData[i].url+"')\">"+auditData[i].lable+"（共"+auditData[i].count+"份）</a></span></p></li>"));	
 	    		}else{
 	    			auditUl.append($("<li><p><span>无数据</span></p></li>"));	
 	    		}
 	    }

}
});


$(function(){
	
	  $(".yuan_tab tr").mouseover(function () {
                $(this).addClass("over");
            }).mouseout(function () {
                $(this).removeClass("over");
            }) 
           
            
});
function openCourt(){
	
	window.location.href="/app/cloud_platform/department/department_main.jsp";
}
function openDialog(title,url){
	top.$.dialog({
		width : $(top).width() * 0.8,
		height : $(top).height() * 0.8,
		lock : true,
		title : title,
		data : {
			"window" : window
		},
		content : 'url:/' + url
	});
}
function queryResourceByType(){
	//文档
		$.ajax({  
	        url : "/resourceSpace/queryResourceByTypes.do",  
	        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
	        type : "POST",  
	        data : {spaceType:"7",resourceType:"1"},  
	        success : function(res) {  
		var item="";
		var queryInfo=res.queryInfo;
		 beans = [];
		for(var i=0;i<queryInfo.length;i++){
			var info = queryInfo[i];
			var id = info.id;
			beans[id] = info;
			var infoType = info.infoType;
			var infoName = info.infoName;
			var infoIsHidden = info.infoIsHidden;
			var infoRemark = info.infoRemark;
			var infoSize = (info.infoSize==null)?'0':info.infoSize;
			var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
			item=item+"<tr id='"+id+"' onclick='selectFile(this,1);' onmouseout='closeGRMenu()' onmousedown='if(event.button == 2) showGRMenu(this)'><td>";
			if(infoName.substring(infoName.indexOf("."),infoName.length)==".doc"||infoName.substring(infoName.indexOf("."),infoName.length)==".docx"){
				item=item+"<img src='images/Word_24.png' />";
			}else if(infoName.substring(infoName.indexOf("."),infoName.length)==".pdf"){
				item=item+"<img src='images/PDF_24.png' />";
			}else if(infoName.substring(infoName.indexOf("."),infoName.length)==".xls"||infoName.substring(infoName.indexOf("."),infoName.length)==".xlsx"){
				item=item+"<img src='images/Excel_24.png' />";
			}else if(infoName.substring(infoName.indexOf("."),infoName.length)==".pptx"||infoName.substring(infoName.indexOf("."),infoName.length)==".ppt"){
				item=item+"<img src='images/PPT_24.png' />";
			}
			item=item+"<a href='#' ondblclick=\"openFile(1,'"+id+"')\">"+infoName+"</a></td><td>"+infoSize+"kB</td><td>"+last_update_date+"</td></tr>";
		}
		$("#yuan_tab1").append(item);
	        }
		
	});
 	//图片
	$.post("/resourceSpace/queryResourceByTypes.do",{spaceType:"7",resourceType:"4"},function(res){
		var item="";
		var queryInfo=res.queryInfo;
		for(var i=0;i<queryInfo.length;i++){
			var info = queryInfo[i];
			var id = info.id;
			var infoType = info.infoType;
			var infoName = info.infoName;
			var infoIsHidden = info.infoIsHidden;
			var infoRemark = info.infoRemark;
			var infoSize = (info.infoSize==null)?'0':info.infoSize;
			var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
			item=item+"<li><a href='#' ondblclick=\"openFile(2,'"+id+"')\"><img width='220px' height='240px' src='/fileupload2/downloadCompress.do?id="+id+"&proportion=0'/>"+last_update_date+"</a></li>";
		}
		$(".pic_list").append(item);
	});
	//音频
	$.post("/resourceSpace/queryResourceByTypes.do",{spaceType:"7",resourceType:"5"},function(res){
		var item="";
		var queryInfo=res.queryInfo;
		for(var i=0;i<queryInfo.length;i++){
			var info = queryInfo[i];
			var id = info.id;
			var infoType = info.infoType;
			var infoName = info.infoName;
			var infoIsHidden = info.infoIsHidden;
			var infoRemark = info.infoRemark;
			var infoSize = (info.infoSize==null)?'0':info.infoSize;
			var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
			item=item+"<tr id='"+id+"' onclick='selectFile(this,1);' onmouseout='closeGRMenu()' onmousedown='if(event.button == 2) showGRMenu(this)'><td><img src='images/Video_24.png' />";
			
			item=item+"<a href='#' >"+infoName+"</a></td><td>"+infoSize+"kB</td><td>"+last_update_date+"</td></tr>";
		}
		$("#yuan_tab2").append(item);
	});
	//其他(压缩文件)
	$.post("/resourceSpace/queryResourceByTypes.do",{spaceType:"7",resourceType:"6,3"},function(res){
		var item="";
		var queryInfo=res.queryInfo;
		for(var i=0;i<queryInfo.length;i++){
			var info = queryInfo[i];
			var id = info.id;
			var infoType = info.infoType;
			var infoName = info.infoName;
			var infoIsHidden = info.infoIsHidden;
			var infoRemark = info.infoRemark;
			var infoSize = (info.infoSize==null)?'0':info.infoSize;
			var last_update_date = (info.last_update_date==null)?'无修改日期':info.last_update_date;
			item=item+"<tr id='"+id+"' onclick='selectFile(this,1);' onmouseout='closeGRMenu()' onmousedown='if(event.button == 2) showGRMenu(this)'><td>";
			if(infoName.substring(infoName.indexOf("."),infoName.length)==".zip"){
				item=item+"<img src='images/ZIP_24.png' />";
			}else if(infoName.substring(infoName.indexOf("."),infoName.length)==".pdf"){
				item=item+"<img src='images/PDF_24.png' />";
			}
			item=item+"<a href='#'>"+infoName+"</a></td><td>"+infoSize+"kB</td><td>"+last_update_date+"</td></tr>";
		}
		$("#yuan_tab3").append(item);
	}); 
	//
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
	           
	            'down': function(t) {
	              downFile();
	            }
	          },
	          onShowMenu: function(e, menu) {
	        	 
	        	  $(e.currentTarget).siblings().removeClass("SelectedRow").end().addClass("SelectedRow"); 
	        	  return menu; 
	        	  }
	    });
}
//打开文件夹或者文件的方法
function openFile(type,id){
	if(type=="1"){
		top.$.dialog({
			width : 900,
			height : 800,
			lock : true,
			title : "预览",
			content : 'url:app/cloud_platform/owner/doc_view.jsp?status=add',
			data : {
				"window" : window,id:id
			}
		}); 
	}else{
		//jpg,png,gif
		var previewData = {
			     first: 0,           //首先显示的文件序号（数组元素序号）
			     images: [       //图片文件列表数组
			            {
			             id:id,      //图片文件id
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
		      data: previewData
		  });
	}
	
}
//设置右键菜单框
function showGRMenu(sInfo) {
		var menu =document.getElementById('menu1');
		
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
//右键菜单关闭
function closeGRMenu(){
	$("#menu1").hide();
}
function downFile(){
	//只能针对资源
	//alert("下载事件！")
	/* for (var i = 0; i < ids.length; i++) {
		$.post("resourceInfo/openDownLog.do",{logType:'下载',file_id:ids[i],file_type:'2'},function(res){
		})
		$("#aa").attr("href","fileupload2/downloadCompress.do?id="+ids[i]+"&proportion=0");
		$("#down").click();
		////
	} */
	
		window.location.href ="/fileupload2/downloadCompress.do?id="+ids+"&proportion=0";

	
}
//资源选择事件处理
function selectFile(info,flag){
	var id = info.id;
	ids=id;
	/* var checkitem = $($("#"+id).children()[0]).children()[0];
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
	} */
	
}
function box2aa(){

	  var screenWidth = $(window).width(); 

	  if(screenWidth <= 1024){
			$(".m-center").css({"margin":"0 0 0 -450px","top":"15%"});
			$(".m-center-wrap").css("width","900px");
			$(".lo_icon").css("top","5px");
			$(".m-c-left").css("width","560px");
	  }
	  
	  if(screenWidth >= 1025 || screenWidth <= 1366) {
		  $(".m-center").css("top","15%");
			$(".m-center-wrap").css("width","1200px");
			$(".m-c-left").css("width","860px");
	  }
	  
	  

	  if(screenWidth > 1440 ){
			$(".m-center").css({"margin":"0 0 0 -601px","top":"20%"});
			$(".m-center-wrap").css("width","1300px");
			$(".m-c-left").css("width","960px");
		
	  }
	  
	

};
$(window).resize(function(){
	box2aa();
});
$(document).ready(function(){
	box2aa();
});

</script>


</head>
<body >
 <div class="contextMenu" id="menu1">
       <ul>
         <li id="down"> 下载</li>
       </ul>
     </div>
<div id="sysMain" class="sysmain">

<div class="top_ser_bg">

	<div class="m-top-upfun">

  <div class="wrapper-orange" >
    <div class="searchMeme-button-right orange-normal  searchMeme-round-right " id="search" >
      <div class="searchMeme-button-icon searchMeme-button-inner">搜 索</div>
    </div>
    <div class="searchMeme-input-right">
      <input type="text" id="com_name" class="searchMeme-water-mark" value="搜索您的文件" style="color:#9C9A9C;" onfocus="if(this.value=='搜索您的文件'){this.value='';this.style.color = '#000'};" onblur="if(this.value==''){this.value='搜索您的文件';this.style.color='#9C9A9C'};" >
    </div>
  </div>
   <div class="m-top-user">
     <%-- <%  if((roles.get("402883a058a93e3f0158aa1d104f2964")!=null)||(roles.get("4028809d588ec2a801589052f54a000c")!=null)){%> --%>
    <a class="more"  href="#" onclick="openCourt()"><p class="more">管理部门空间</p></a>
  <%--  <%  }%> --%>
    </div>

  
  
    
   
  </div>


</div>

  <!--中间主要内容区  start -->
  <div class="m-center">
    <div class="m-center-wrap">
      <div class="m-center-main">
      	
		<div class="m-c-left">
		
			<div id="container">

        <!--Vertical Tab-->
        <div id="parentVerticalTab">
            <ul class="resp-tabs-list hor_1">
                <li id="wend"><img src="images/office02.png" />最新文档</li>
                <li><img src="images/pic02.png" />最新图片</li>
                <li><img src="images/video02.png" />最新视频</li>
				<li><img src="images/other02.png" />其它</li>
            </ul>
            <div class="resp-tabs-container hor_1">
			
                <div>
		
					<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="yuan_tab" id="yuan_tab1">
						<tr >
							<th width="50%" >文件名</th>
							<th width="15%">大小</th>
							<th width="25%">日期</th>
					        <th width="10%"><a href="/app/cloud_platform/court/show_file_office.jsp?spaceType=7" ><p class="more">更多>></p></a></th>
						</tr>
						<!-- <tr>
							<td><img src="images/Word_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪	</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">电梯安全评估规范(DB51/T 2188-2016)</a></td>
							<td>122k</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PPT_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">特种设备目录 2014年颁布</a></td>
							<td>34M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr> -->
					
						
					</table>

				  
				  
                </div>
				
                <div>
                    <a href="/app/cloud_platform/court/show_file_picture.jsp?spaceType=7"  ><p class="more">更多>></p></a>
					<ul class="pic_list">
					   
						<!-- <li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li>
						<li><a href="#"><img src="images/yj_01.png" />2016-1-22 15:11</a></li> -->
					</ul>
					
					
                </div>
                <div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="yuan_tab" id="yuan_tab2">
						<tr>
							<th width="50%" class="">文件名</th>
							<th width="15%">大小</th>
							<th width="25%">日期</th>
							<th width="10%"><a href="/app/cloud_platform/court/show_file_video.jsp?spaceType=7" ><p class="more">更多>></p></a></th>
						</tr>
						<!-- <tr>
							<td><img src="images/Video_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪	</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">电梯安全评估规范(DB51/T 2188-2016)</a></td>
							<td>122k</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">特种设备目录 2014年颁布</a></td>
							<td>34M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Video_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr> -->
					
						
					</table>



                </div>
                <div>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="yuan_tab" id="yuan_tab3">
						<tr>
							<th width="50%" class="">文件名</th>
							<th width="15%">大小</th>
							<th width="25%">日期</th>
							<th width="10%"><a href="/app/cloud_platform/court/show_file_zip.jsp?spaceType=7" ><p class="more">更多>></p></a></th>
						</tr>
						<!-- <tr>
							<td><img src="images/Text_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪	</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PDF_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PDF_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PDF_24.png" /><a href="#">电梯安全评估规范(DB51/T 2188-2016)</a></td>
							<td>122k</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PPT_24.png" /><a href="#">特种设备目录 2014年颁布</a></td>
							<td>34M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/PPT_24.png" /><a href="#">中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪</a></td>
							<td>1M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">住宅电梯使用安全管理规范(DB51/T 2240—2016)</a></td>
							<td>521kb</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">电梯维护保养规范(DB51/T 2239—2016)</a></td>
							<td>1.6M</td>
							<td>2016-1-22 15:11</td>
						</tr>
						<tr>
							<td><img src="images/Word_24.png" /><a href="#">质检总局关于印发《全国质检系统检验检测认证机构整合指导意见》</a></td>
							<td>566K</td>
							<td>2016-1-22 15:11</td>
						</tr> -->
					
						
					</table>

                </div>				
				
				
            </div>
        </div>
		
		


    </div>
		
		</div>
		
		
		<div class="m-c-right">
			
			<div class="top_tj">
			
				<div class="tit"> 
				
				看点推荐<a href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=8&page=" target="_blank"><p class="more">更多>></p></a>
				</div>
				
				<div class="list_box">
				
				   <ul class="n_l_box_list" >
				<!--   <li>
					<div class="n_l_box_list_bt">
					  <a href="#" >省特检院一行赴中国特检院挂职锻炼</a>
					</div>
					<div class="n_l_box_time">2016-02-18  16:22:00</div>
				  </li>
				  <li>
					<div class="n_l_box_list_bt">
					  <a href="#" >中办国办出台意见允许科研人员和教师依法依规适度兼职兼薪</a>
					</div>
					<div class="n_l_box_time">2016-02-18  16:22:00</div>
				  </li>
				  <li>
					<div class="n_l_box_list_bt">
					  <a href="#" >第三党支部开展专题学习活动</a>
					</div>
					<div class="n_l_box_time">2016-02-18  16:22:00</div>
				  </li>
				  <li>
					<div class="n_l_box_list_bt">
					  <a href="#" >省特检院一行赴中国特检院挂职锻炼</a>
					</div>
					<div class="n_l_box_time">2016-02-18  16:22:00</div>
				  </li> -->
				</ul>
			   			
               </div>
			
			</div>
			
			
			<div class="top_yw">
						
							<div class="tit"> 
								待办业务<a href="javascript:void(0);" onclick="openDialog('待处理','app/cloud_platform/court/court_dcl_index.jsp')"><p class="more">更多>></p></a>
							</div>
							
							<div class="list_box">

									<ul class="event_list">
									
											<!-- <li><p><span><a href="#">报告录入（共2份）</a></span></p></li>
											<li><p><span><a href="#">报告审核（共1份）</a></span></p></li>
											<li><p><span><a href="#">报告签发（共110份）</a></span></p></li>
											<li><p><span><a href="#">报告归档（共8份）</a></span></p></li> -->
										
									</ul>
					
									<div class="clearfix"></div>
							

						   </div>
						
						</div>		
		
		
		</div>
		
    

      </div>
    </div>
  </div>
	<script type="text/javascript">
    $(document).ready(function() {
    	
        //Vertical Tab
        $('#parentVerticalTab').easyResponsiveTabs({
            type: 'vertical', //Types: default, vertical, accordion
            width: 'auto', //auto or any width like 600px
            fit: true, // 100% fit in a container
            closed: 'accordion', // Start closed if in accordion view
            tabidentify: 'hor_1', // The tab groups identifier
            activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#nested-tabInfo2');
                var $name = $('span', $info);
                $name.text($tab.text());
                $info.show();
            }
        });
        var oDiv = document.getElementById('wend');   //获取元素div
        oDiv.click();
        $("#parentVerticalTab").tabs("select",0)
    });
</script>

  
</div>
</body>
</html>
