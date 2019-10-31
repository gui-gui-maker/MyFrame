<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>类型管理</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/chart/js/draggable.js"></script>
<script type="text/javascript">
 	var toolBar;
 	var ztree;
	var zNodes;
	var rMenu;
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},callback: {
				onClick: ztreeClick,
				onNodeCreated:function(event, treeId, treeNode){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
						//treeNode.icon = treeNode.image;
						//$(".ztree li span.button.ico_docu").css("background-size","16px!important")
						//treeObj.updateNode(treeNode)
					}
					if(treeNode.id=='root'){
						treeObj.selectNode(treeNode);
						showFormData(treeNode)
						showFormData(treeNode)
					}
				},
				onRightClick: OnRightClick
			}
	};
  	 $(function() {
		//页面布局
        initPage();
        $.getJSON("chart/type/checkSys.do",function(res){
			if(res.success){
				if(res.data){
					$.getJSON("chart/type/transformTree1.do?id=root",function(data){
						ztree = $.fn.zTree.init($("#tree1"), setting, data);
						rMenu = $("#rMenu")
					})
				}else{
					$.ligerDialog.confirm("还未初始化图表相关数据表及参数，是否初始化？",function(yes){
						if(yes){
							$("body").mask("正在创建图表相关数据表及参数。。。");
							$.getJSON("chart/type/createSys.do",function(data){
								if(data.success){
									$("body").unmask();
									$.getJSON("chart/type/transformTree1.do?id=root",function(data){
										ztree = $.fn.zTree.init($("#tree1"), setting, data);
										rMenu = $("#rMenu")
									})
								}
							})
						}
					})
				}
			}
		})
        //按钮工具栏
		toolBar=$("#toptoolbar").ligerToolBar({
			items : [ {
						text : '新增参数大类',
						id : 'add',
						click : addBparam, 
						disabled:false,
						icon : 'add'
						}
						,{line:true}, 
						{
						text : '修改参数大类',
						id : 'modify',
						disabled:true,
						click : updateBparam,
						icon : 'modify'
						},{line:true}, 
						{
						text : '删除参数大类',
						id : 'del',
						disabled:true,
						click : deleteBparam,
						icon : 'del'
						} 
						,{line:true}, 
						{
						text : '详细参数设计',
						id : 'addParam',
						click : addParam,
						disabled:true,
						icon : 'setting'
						}   
						,{line:true}, 
						{
						text : '启用参数类别',
						id : 'useBparam',
						click : useBparam,
						disabled:true,
						icon : 'accept' 
						}
						,{line:true}, 
						{
						text : '禁用参数类别',
						id : 'disableBparam',
						click : disableBparam,
						disabled:true,
						icon : 'error'
						}
						,{line:true}, 
						{
						text : '初始化缓存',
						id : 'initCache',
						click : initCache,
						disabled:false,
						icon : 'refresh'
						}
						
					]
		});
	  });
  	 
  	function initPage(){
 		$("#layout").ligerLayout({
 			leftWidth : 250,
 			topHeight : 30,
 			space: 5,
 			allowTopResize : false,
 			allowLeftCollapse : true,
 			allowRightCollapse : false
 		}); 
     }
  	function refresh(data, status) {
		var node = ztree.getSelectedNodes();
		if (status == "add") {
			var newNode=[];
			newNode.push({
				text:data.name,
				id:data.id,
				swf:data.swfName,
				image:data.icon
			})
			if (node){
				ztree.addNodes(node[0], newNode,false);
			}
			else
			{
				ztree.addNodes(null, newNode,false);
			}
		} else if(status=="edit"||status=="modify") {
			if (node){
				node[0].text = data.name;
				if (node.length>0) {
					ztree.updateNode(node[0]);
				}
			}
		}else{
			var newNode=[];
			newNode.push({
				text:data.name,
				id:data.id,
				swf:data.swfName,
				image:data.icon
			})
			if (node){
				ztree.addNodes(node[0].getParentNode(), newNode,false);
			}
			else
			{
				ztree.addNodes(null, newNode,false);
			}
		}
		ztree.selectNode(ztree.getNodeByParam("id", data.id, null));
		showFormData(ztree.getNodeByParam("id", data.id, null))
	}
  	function ztreeClick(event,treeId,treeNode){
		showFormData(treeNode);
		toolBar.setEnabled({"modify":false})
     	toolBar.setEnabled({"del":false})
     	toolBar.setEnabled({"addParam":false})
     	toolBar.setEnabled({"useBparam":false})
	    toolBar.setEnabled({"disableBparam":false}) 
	}
	function showFormData(treeNode){
		createGrid(treeNode.id)
	}
	function OnRightClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			ztree.cancelSelectedNode();
			//showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			ztree.selectNode(treeNode);
			showRMenu(treeNode.id, event.clientX, event.clientY);
		}
	}
	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_add").show();
			$("#m_edit").show();
			$("#m_detail").show();
			$("#m_copy").hide();
			$("#m_del").hide();
		} else {
			$("#m_add").show();
			$("#m_edit").show();
			$("#m_detail").show();
			$("#m_del").show();
			$("#m_copy").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	
	function editType(obj){
		hideRMenu();
		var actionNode = ztree.getSelectedNodes();
		var title ="";
		var url = "pub/chart/param/type_detail.jsp";
		if(obj=='1'){
			url = url +"?status=detail&id="+actionNode[0].id;
			title = "类型详情"
		}
		if(obj=='2'){
			url = url +"?status=add&pid="+actionNode[0].id;
			title = "新增类型"
		}
		if(obj=='3'){
			url = url +"?status=edit&id="+actionNode[0].id;
			title = "修改类型"
		}
		if(obj=='4'){
			url = url +"?status=edit&id="+actionNode[0].id+"&copy=1";
			title = "复制类型"
		}
		top.$.dialog({
			width:500,
			height:250,
			lock:true,
			title:title,
			content: "url:"+url,
			data:{window:window}
		});
	}
	
	function delType(){
		hideRMenu();
		var actionNode = ztree.getSelectedNodes();
		var tips = "你确定要删除类别【" + actionNode[0].text + "】吗？\n将删除类别及其子类别，删除后不能恢复！";
		$.ligerDialog.confirm(tips, function(yes) {
			if (yes) {
				$.post("chart/type/delete.do", {ids:actionNode[0].id}, function(res) {
					if (res.success) {
						ztree.selectNode(ztree.getNodeByParam("id", 'root', null));
						if (actionNode && actionNode.length>0) {
							ztree.removeChildNodes(actionNode[0]);
							ztree.removeNode(actionNode[0]);
						}
						showFormData(ztree.getNodeByParam("id", 'root', null));
					} else {
						$.ligerDialog.alert('删除失败！', "error");
					}
				});
			}
		});
	}
	
	
	
    function createGrid(typeid) {
    	grid = $("#dataGridDiv").ligerGrid({
			columns:[
			    {display : 'ID', name : 'id', width : 270, align : 'left',hide:true},
			    {display : '参数编号', name : 'code', width : 100, align : 'left'},
			    {display : '参数名称', name : 'name', width : 300, align : 'left'},
				{display : '参数描述', name : 'remark', width : 350, align : 'left',render:function(data){
					return "<div title='"+data.remark+"'>"+data.remark+"</div>"
				}},
			    {display : '是否禁用', name : 'status', width : 80, align : 'center',render:function(data){
			    	return data.status=='1'?"是":"否"
			    }}
			],     
			url:"chart/bparam/getBparam.do?typeId="+typeid,
			usePager : false, 
			height: '100%',
			delayLoad:true,     
			alternatingRow : false,
			rownumbers:true,
			frozen:false,
			//isScroll:false, 
			//tree : { columnName : 'name' }, 
			checkbox : false, 
			autoCheckChildren : false,
			detail:{onShowDetail:onShowChildList ,height:'100%',   width:'100%' } , 
            onSelectRow : function(rowdata, rowindex, rowDomElement) {//行选择事件
             	toolBar.setEnabled({"modify":true})
             	toolBar.setEnabled({"del":true})
             	toolBar.setEnabled({"addParam":true})
             	toolBar.setEnabled({"copeSchema":true})
              
			    if(rowdata.status=='0'){  
			    	toolBar.setEnabled({"useBparam":false})
			    	toolBar.setEnabled({"disableBparam":true}) 
			    }else{   
			        toolBar.setEnabled({"useBparam":true})
			    	toolBar.setEnabled({"disableBparam":false})  
			    } 
			},
			onUnSelectRow:function(rowdata, rowindex, rowDomElement){
			    toolBar.setEnabled({"modify":false})
             	toolBar.setEnabled({"del":false})
             	toolBar.setEnabled({"addParam":false})
             	toolBar.setEnabled({"useBparam":false})
			    toolBar.setEnabled({"disableBparam":false}) 
			}
		});
	}
    var dataTypes = [
	                  {id: "string", text: "字符串"},
	                  {id: "number", text: "数字"},
	                  {id: "boolean", text: "布尔型"}
	              ];
    function onShowChildList(row,detailPanel,callback){
    	$('body').mask('请稍等，数据加载中...');  
    	var griddiv = document.createElement("div");
    	griddiv.scroll=false; 
    	$(detailPanel).append(griddiv);
    	$(griddiv).css('margin',0).ligerGrid({
            columns: [
                      { display: '主键', name: 'id', align: 'left', width: 120 ,hide:true}, 
                      { display: '参数名称', name: 'name', align: 'left',width: 130},
                      { display: '参数代码', name: 'code' , align: 'left', width: 100 }, 
                      { display: '参数类型', name: 'colType', width:80, align: 'left' ,render: function (item) {
  		                	return render(item["colType"],dataTypes);
  		                }
                      },
                      { display: '参数长度', name: 'colLength', width: 65, align: 'left' },
                      { display: '参数默认值', name: 'colDefvalue', width: 150, align: 'left' }, 
                      { display: '参数值范围1', name: 'colSmall',width:80 , align: 'left'}, 
                      { display: '参数值范围2', name: 'colBig', width: 80, align: 'left' },
                      { display: '参数描述', name: 'remark' , width: 100, align: 'left',hide:true},
                      { display: '参数排序', name: 'colSort', width: 60 , align: 'left'},
                      {display : '是否禁用', name : 'status', width : 60, align : 'center',render:function(data){
      			    	return data.status=='1'?"是":"否"
      			      }}
                      ], 
            url:"chart/param/getParam.do?bparamId="+row.id,   
            frozen:false,
            rownumbers:true,
            usePager : false, 
            height: 'auto',    
            showTitle:false ,
            fixedCellHeight :false,
            onAfterShowData:function(){
            	 $('body').unmask();
            }
       });
    }
    function addBparam(){
    		var actionNode = ztree.getSelectedNodes();
       		if(actionNode==undefined){
       		   	$.ligerDialog.alert("图表类型！");
       		   	return false;
       		}
    		var width = 700;
   			var height = 420;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "新增参数大类",
   				data : {
   					"window" : window
   				},
   				content : 'url:pub/chart/param/bparam_detail.jsp?status=add&typeId='+actionNode[0].id 
   			})
    }       
	function updateBparam(){
			var row= grid.getSelectedRow() ;
	   		if(row==null||row==undefined){
	      		return false;
	   	    }
	   	    var id=row.id;
			var width = 700;
   			var height = 420;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "修改参数大类",
   				data : {
   					"window" : window
   				},
   				content : 'url:pub/chart/param/bparam_detail.jsp?status=modify&id='+id
   			})
	}
	 function deleteBparam(){
	   var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
	   var keyID = row.id
	    $.ligerDialog.confirm("您确定要删除此参数大类吗？",function(obj){
	   	    if(obj){ 
	   		    $('body').mask('请稍等，删除数据中...');
	   		    $.getJSON("chart/bparam/delete.do?ids="+keyID,function(res){
	   		    	$('body').unmask(); 
	   		    	if(res.success){ 
	   				    reloadDataGrid(); 
	   				}else{
	   					$.ligerDialog.alert(res.msg);
	   				}
	   		    })
	   		 } 		     
	   	 });
	 }
	  
    function addParam(){
       var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
        var keyID = row.id
    	var width = 800;
   			var height = 600;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "详细参数",
   				data : {
   					"window" : window
   				},
   				content : 'url:pub/chart/param/param_detail.jsp?status=edit&bparamId='+keyID
   			}).max(); 
   }
  function useBparam(){ 
	  var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
	   var keyID = row.id
  	   $.ligerDialog.confirm("您确定要'启用'此参数大类吗？",function(obj){
    	    if(obj){ 
    		    $('body').mask('请稍等，正在启用参数大类...');
	     	    $.post("chart/bparam/updateStatus.do",{id:keyID,status:'0'},
			         function(result){  
			           $('body').unmask(); 
    				    if(result.success){ 
    				       reloadDataGrid(); 
    				    }else{
    					  $.ligerDialog.alert(result.data); 
    			        }
 			        }
 		       ); 
    		 } 		     
    	 });
  }
  
  
  function disableBparam(){
	  var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
	  var keyID = row.id
 	   $.ligerDialog.confirm("您确定要'禁用'此参数大类吗？",function(obj){
   	    if(obj){ 
   		    $('body').mask('请稍等，正在禁用参数大类...');
	     	    $.post("chart/bparam/updateStatus.do",{id:keyID,status:'1'},
			         function(result){  
			           $('body').unmask(); 
   				    if(result.success){ 
   				       reloadDataGrid(); 
   				    }else{
   					  $.ligerDialog.alert(result.data); 
   			        }
			        }
		       ); 
   		 } 		     
   	 });
  } 
  
  //刷新码表值数据
	function reloadDataGrid(){
		var actionNode = ztree.getSelectedNodes();
		if(grid){ 
			 grid.loadServerData(actionNode[0].id); 
		}else{    
			 createGrid();
			 grid.loadServerData(actionNode[0].id); 
		}
	}
	function render(value,data){
	    for (var i in data) {
	    	if (data[i]["id"] == value)
	        {
	        	return data[i]['text'];
	        }
	    }
	    return value;
   }
	
	function initCache(){
		$("body").mask("正在更新缓存。。。")
		$.getJSON("chart/type/initCache.do",function(data){
			if(data.success){
				$("body").unmask();
			}
		})
	}
</script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    }
    #rMenu {position:absolute; visibility:hidden; top:0; background-color: #679ebb;text-align: left;padding: 2px;z-index:99}
	#rMenu ul li{
		margin: 1px 0;
		padding: 6px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #e7eff4;
	}
</style>
</head>
<body class="p5">
	<div id="layout">
		<div id="rMenu">
				<ul>
					<li id="m_detail" onclick="editType(1);">类型详情</li>
					<li id="m_add" onclick="editType(2);">增加类型</li>
					<li id="m_edit" onclick="editType(3);">修改类型</li>
					<li id="m_copy" onclick="editType(4);">复制类型</li>
					<li id="m_del" onclick="delType();">删除类型</li>
				</ul>
			</div>
		<div position="left" title="图表类别" class="overflow-auto">
			<ul id="tree1" class="ztree"></ul>
		</div>
		<div position="center">
			<!-- 
			<table id="qm-search-p" border="0">
				<tbody>
					<tr>
						<td width="20px"></td>
						<td></td>
						<td width="140px" height="33px">
							<div class="column" style="width:70%; ">
								<div class="field-left">
									<div class="l-text">
										<input type="text" id="year" class="l-text-field"
											ligeruiid="p-q-f0">
											<div class="l-text-l"></div>
											<div class="l-text-r"></div>
									</div>
								</div>
							</div></td>
						<td class="l-list-search">
							<button onClick="reloadDataGrid();"
								class="l-button-warp l-button" id="listSearch"
								ligeruiid="Button1001">
								<div class="l-button-main l-button-hasicon">
									<div class="l-button-text">查询</div>
									<div class="l-button-icon l-icon-search-list"></div>
								</div>
							</button></td>
					</tr>
				</tbody>
			</table>
 			-->
 			<div position="top" id="toptoolbar"></div>
			<div id="dataGridDiv" style="margin:0; padding:0"></div>
		</div>
	</div>
</body>
</html>
