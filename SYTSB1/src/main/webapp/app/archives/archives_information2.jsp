<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/rbac/js/draggable.js"></script>
<script type="text/javascript">
var orgTreeManager = null;
var unitId ="<sec:authentication property="principal.unit.id" />";
var unitName = "<sec:authentication property="principal.unit.orgName" />";
//封装数据
var images = []; //检验报告
var first = 0;
$(function() {
	//页面布局
	$("#layout1").ligerLayout({
		leftWidth : 279,
		topHeight: 30,
		space: 5,
		allowTopResize : false,
		allowLeftCollapse : false,
		allowRightCollapse : false
	});
	//组织机构树
	orgTreeManager = $("#tree1").ligerTree({
		checkbox : false,
        iconFieldName : "level",
        iconParse : function(data){
        	if(data["level"]==0)
				return "k/kui/images/16/icons/home.png";
			else if (data["level"] == 1)
				return "k/kui/images/16/icons/org-2.png";
        	else if (data["level"] == 2)
            	return "k/kui/images/16/icons/user.png";
        	else
        		return "";
        },
		data : [],
		attribute : [ "url" ],
		onSelect : function(node) {
			ztreeClick(node.data.id);
		}
	});

	 $.getJSON("uploadsAction/a/getFileTree2.do?fileId=${param.fileId}",function(res){
		 var jy ={"id" : "1","text" : "检验报告","level" : "1","isexpand" : false,
				  "children" : [ {"id" : "1","text" : "文档(0)","level" : "2","isexpand" : false,"children" : []}, 
								 {"id" : "1","text" : "图片(0)","level" : "2","isexpand" : false,"children" : []} ]};
		 var ys ={"id" : "1","text" : "原始记录","level" : "1","isexpand" : false,
				  "children" : [ {"id" : "1","text" : "文档(0)","level" : "2","isexpand" : false,"children" : []}, 
								 {"id" : "1","text" : "图片(0)","level" : "2","isexpand" : false,"children" : []} ]};
		 var zj ={"id" : "1","text" : "自检报告","level" : "1","isexpand" : false,
				  "children" : [ {"id" : "1","text" : "文档(0)","level" : "2","isexpand" : false,"children" : []}, 
								 {"id" : "1","text" : "图片(0)","level" : "2","isexpand" : false,"children" : []} ]};
		 var qt ={"id" : "1","text" : "其他","level" : "1","isexpand" : false,
				  "children" : [ {"id" : "1","text" : "文档(0)","level" : "2","isexpand" : false,"children" : []}, 
								 {"id" : "1","text" : "图片(0)","level" : "2","isexpand" : false,"children" : []} ]};
		 var treeData = [];
		 treeData.push(jy);
		 treeData.push(ys);
		 treeData.push(zj);
		 treeData.push(qt);
		 if(res.success){
			 var data = res.data;
			 if(data.length>0){
				 var jyi = 0;
				 var jyf = 0;
				 var ysi = 0;
				 var ysf = 0;
				 var zji = 0;
				 var zjf = 0;
				 var qti = 0;
				 var qtf = 0;
				 for(var i=0;i<data.length;i++){
					 var fl = {};
					 fl.id = data[i][1];
					 fl.text = data[i][3];
					 fl.level = 3;
					 fl.isexpand=true;
					 fl.children=null;
					 if(data[i][2] == '0' && data[i][0] == '10002'){
						// 检验报告-image
						 jyi++;
						 jy.children[1].children.push(fl);
					 }else if(data[i][2] == '0' && data[i][0] != '10002'){
						// 检验报告-file
						 jyf++;
						 jy.children[0].children.push(fl);
					 }else if(data[i][2] == '1' && data[i][0] == '10002'){
						// 原始记录-image
						 ysi++;
						 ys.children[1].children.push(fl);
					 }else if(data[i][2] == '1' && data[i][0] != '10002'){
						// 原始记录-file
						 ysf++;
						 ys.children[0].children.push(fl);
					 }else if(data[i][2] == '2' && data[i][0] == '10002'){
						// 自检报告-image
						 zji++;
						 zj.children[1].children.push(fl);
					 }else if(data[i][2] == '2' && data[i][0] != '10002'){
						// 自检报告-file 
						 zjf++;
						 zj.children[0].children.push(fl);
					 }else if(data[i][2] == '3' && data[i][0] == '10002'){
						// 其他-image
						 qti++;
						 qt.children[1].children.push(fl);
					 }else if(data[i][2] == '3' && data[i][0] != '10002'){
						// 其他-file
						 qtf++;
						 qt.children[0].children.push(fl);
					 }
				 }
				 jy.children[0].text="文档("+jyf+")";
				 jy.children[1].text="图片("+jyi+")";
				 ys.children[0].text="文档("+ysf+")";
				 ys.children[1].text="图片("+ysi+")";
				 zj.children[0].text="文档("+zjf+")";
				 zj.children[1].text="图片("+zji+")";
				 qt.children[0].text="文档("+qtf+")";
				 qt.children[1].text="图片("+qti+")";
			 }
			 orgTreeManager.append(unitId,[{
				id : "0",
				text : "${param.bh}",
				path :"",
				level : "0",
				children : treeData
			}]); 
			//orgTreeManager.selectNode("0");
			orgTreeManager.expandAll();
		 }
	});  
});	


function ztreeClick(treeId){
	var path="";
	var parent="";
	
	 $.getJSON("uploadsAction/a/folderDetail.do?id="+treeId,function(res){
		 //当前文件类型的全部
		 images = [];
		 //获取当前
		 var files = res.data;
		 //alert(typeof files);
		 var current = '';//当前图片
		 if(files&&files.length>0){
			 for(var i = 0;i<files.length;i++){
				 if(files[i].id==treeId){
					 current = files[i];
					 first = i;
				 }
				 images.push(files[i]);
			 }
			
		 }else{
			 return;
		 }
		 path=current.uploadPath;
		 parent=current.parentId;
		 path=path.replace("\\", ",");
		 if(parent=="10000"){
			 $("#rightFrame").attr("src","app/archives/archives_word.jsp?path="+path);
		 }else if(parent=="10001"){
			 //视频
			 $("#rightFrame").attr("src","pub/fileupload/video_player.jsp?fid="+current.uploadId);
		 }else if(parent=="10002"){
			 $("#rightFrame").attr("src","app/archives/preview2.jsp?id="+current.uploadId);
		 }
		//rightFrame.loadGridData(path);
	 });

}
</script>
 
</head>
<body>
<div id="layout1">
    <div position="left" title="档案" style="overflow:auto;">
			<ul id="tree1"></ul>
	</div>
	<div id="div1" position="center" align="center" >
		<iframe id="rightFrame" name="rightFrame" src=""  frameborder="0" 
			width="100%" height="100%" scrolling="no"></iframe>
	</div>
	
       </div>
</body>
</html>