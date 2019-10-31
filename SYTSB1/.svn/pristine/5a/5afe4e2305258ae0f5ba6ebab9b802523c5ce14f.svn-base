<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>方案管理</title>  
  
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
 
	var tree = null;
    var curNode=null;
    var imgUrl="pub/rbac/img/";
     //方案主列表信息
    var grid=null;
    var toolBar=null;
     function initPage(){
    		//页面布局
 		$("#layout").ligerLayout({
 			leftWidth : 200,
 			topHeight : 30,
 			space: 5,
 			allowTopResize : false,
 			allowLeftCollapse : true,
 			allowRightCollapse : false
 		}); 
         //菜单树
         tree = $("#tree1").ligerTree({
             checkbox : false,
             <%
          	if(len==6){
          		
          %>
          url : 'rbac/area/getAreaTreeByNative.do?code=<%=code%>',
          <%
          	}else if(len==4){
          		
          %>
          url : 'rbac/area/getAreaTreeWithAsync.do?code=<%=code%>',
          <%		
          	}
          %> 
             attribute : [ "pid","code","text"],
             iconFieldName:"levels",
             iconParse:function(data){
                 return imgUrl+data["levels"]+".png";
             }, 
             onAfterAppend : function(parentNode,newdata) { 
               
            	 this.selectNode(selectRoot); 
             	
			},
			onSelect : function(node){  
			    toolBar.setEnabled({"modify":false})
             	toolBar.setEnabled({"del":false})
             	toolBar.setEnabled({"addSchema":false})
             	toolBar.setEnabled({"copeSchema":false})
             	toolBar.setEnabled({"useSchema":false})
			    toolBar.setEnabled({"disableSchema":false}) 
				reloadDataGrid()
			     
			},
             onBeforeExpand: function(node){
                 if (node.data.children && node.data.children.length == 0)
                 {
                     tree.loadData(node.target,"rbac/area/getAreaTreeByPid.do?id="+node.data.id);
                 }
             }
         });
          
     }
     
     function selectRoot(treenodedata,treedataindex){
    	 
    	  if(treenodedata.code=="<%=code%>"){
    		  tree.selectNode(treenodedata.id);
    		  return true;
    	  }
    	  
     }
     
     
    function reloadNode(){
        if(curNode!=null){
            if(!tree.hasChildren(curNode) ){
                tree.upgrade(curNode);
            }
            tree.loadData(curNode.target,"rbac/area/getAreaTreeByPid.do?id="+curNode.data.id,{},true);
        }
    }
    function removeNode(id){
        var node=tree.getDataByID(id);
        if(node){
            var parent=tree.getParentTreeItem(node);
            tree.remove(node);
            if(!tree.hasChildren(parent) ){
                tree.demotion(curNode);
            }
        }
    }
	$(function() {
		//页面布局

        initPage();
      //按钮工具栏
		toolBar=$("#toptoolbar").ligerToolBar({
			items : [ {
						text : '新增方案',
						id : 'add',
						click : addCareSchema, 
						disabled:false,
						icon : 'add'
						}
						,{line:true}, 
						{
						text : '修改方案',
						id : 'modify',
						disabled:true,
						click : updateCareSchema,
						icon : 'modify'
						},{line:true}, 
						{
						text : '删除方案',
						id : 'del',
						disabled:true,
						click : deletCareSchema,
						icon : 'del'
						} 
						,{line:true}, 
						{
						text : '公式设计',
						id : 'addSchema',
						click : addSchema,
						disabled:true,
						icon : 'setting'
						}   
						,{line:true}, 
						{
						text : '启用方案',
						id : 'useSchema',
						click : useSchema,
						disabled:true,
						icon : 'accept' 
						}
						,{line:true}, 
						{
						text : '禁用方案',
						id : 'disableSchema',
						click : disableSchema,
						disabled:true,
						icon : 'error'
						}	,
						{line:true}  
						,
						{
						text : '方案拷贝',
						id : 'copeSchema',
						disabled:true,
						click : copeSchema,
						icon : 'copy'
						} 
						
					]
		});
	  });
	
    function createGrid(areacode,year) {
       
    	grid = $("#dataGridDiv").ligerGrid({
			columns:[
			    {display : 'ID', name : 'id', width : 270, align : 'left',hide:true},
			    {display : '行政区划', name : 'areaName', width : 80, align : 'left'},
			    {display : '行政区划CODE', name : 'areaCode', width : 80,hide:true},
				{display : '方案年度', name : 'schemeYear', width : 80, align : 'center'},
				{display : '方案名称', name : 'schemeName', width : 250, align : 'left',render:function(data){
					return "<div title='"+data.schemeName+"'>"+data.schemeName+"</div>"
				}},
				{display : '方案编号', name : 'schemeCode', width : 100, align : 'left'},
				{display : '是否当前方案', name : 'isCurrentlyName', width : 80, align : 'center'}, 
			    {display : '是否当前方案', name : 'isCurrently', width : 80, align : 'center',hide:true}, 
				{display : '备注', name : 'remark', width : 150, align : 'left',render:function(data){
					return "<div title='"+data.remark+"'>"+data.remark+"</div>"
				}},
				{display : '创建人', name : 'createUser', width : 80, align : 'left'},
			    {display : '创建人ID', name : 'createUserid', width : 270, align : 'center',hide:true},
			    {display : '创建时间', name : 'createTime', width : 120, align : 'center'}
			],     
			url:"app/yljz/schemeinfo/getAllSchemeInfo.do",
			usePager : false, 
			height: '100%',
			delayLoad:true,     
			alternatingRow : false, 
			//isScroll:false, 
			//tree : { columnName : 'name' }, 
			checkbox : false, autoCheckChildren : false,
			detail:{onShowDetail:onShowChildList ,height:'100%',   width:'100%' } , 
            onSelectRow : function(rowdata, rowindex, rowDomElement) {//行选择事件
             	toolBar.setEnabled({"modify":true})
             	toolBar.setEnabled({"del":true})
             	toolBar.setEnabled({"addSchema":true})
             	toolBar.setEnabled({"copeSchema":true})
              
			    if(rowdata.isCurrently=='1'){  
			    	toolBar.setEnabled({"useSchema":false})
			    	toolBar.setEnabled({"disableSchema":true}) 
			    }else{   
			        toolBar.setEnabled({"useSchema":true})
			    	toolBar.setEnabled({"disableSchema":false})  
			    } 
			},
			onUnSelectRow:function(rowdata, rowindex, rowDomElement){
			    toolBar.setEnabled({"modify":false})
             	toolBar.setEnabled({"del":false})
             	toolBar.setEnabled({"addSchema":false})
             	toolBar.setEnabled({"copeSchema":false})
             	toolBar.setEnabled({"useSchema":false})
			    toolBar.setEnabled({"disableSchema":false}) 
			}
		});
	}
    
  //显示救助类别 
    function onShowChildList(row,detailPanel,callback){
    	$('body').mask('请稍等，数据加载中...');  
        var orderid=row.id
    	var griddiv = document.createElement("div");
    	griddiv.scroll=false; 
    	$(detailPanel).append(griddiv);
    	$(griddiv).css('margin',0).ligerGrid({
            columns: [
                      { display: '主键', name: 'id', align: 'left', width: 120 ,hide:true}, 
                      { display: '补助类别', name: 'subsidyTypeName', width: 130 ,hide:true },
                      { display: '业务类型', name: 'orderTypeName' , align: 'left', width: 109 }, 
                      { display: '公式', name: 'formula', width:330, align: 'left' },
                      { display: '参保救助比例', name: 'rate', width: 90, align: 'rigth' },
                      { display: '未参保救助比例', name: 'nrate', width: 100, align: 'right' }, 
                      { display: '计算方式', name: 'countTypeName',width:80 , align: 'left'}, 
                      { display: '起付线', name: 'payingStandard', width: 60, align: 'rigth' },
                      { display: '年救助标准', name: 'yearStandard' , width: 80, align: 'right'},
                      { display: '单次标准', name: 'singleStandard', width: 70 , align: 'right'}  
                      ], 
            url:"app/yljz/schemeinfo/getSchmaByID.do?schmaInfoid="+row.id,   
            groupColumnName: 'subsidyTypeName',
            groupColumnDisplay: '补助类别 ',       
            usePager : false, 
            height: 'auto',    
            showTitle:false ,
            fixedCellHeight :false,
            onAfterShowData:function(){
            	 $('body').unmask();
            }
       });
    }
    //拷贝方案
    function copeSchema(){
    	 var row= grid.getSelectedRow() ;
	   		if(row==null||row==undefined){
	      		return false;
	   	    }
	   	    var id=row.id;
			var width = 600;
   			var height = 550;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "方案拷贝",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/yljz/baseinfo/schema/schemaCope_detail.jsp?status=modify&id='+id
   			})
    }    
        //新增方案
    function addCareSchema(){
       		var node=tree.getSelected();
       		var areacode="";
       		var areaname="";
       		try{
       		if(node==undefined){
       		   	$.ligerDialog.alert("请选择行政区划！");
       		   	return false;
       		}else{
       			 areacode=node.data.code;
       			 areaname=node.data.text; 
       		}
       		}catch(e){
       			$.ligerDialog.alert("请选择行政区划！");
       		    throw new Error(10,"获取行政区划代码错误！")  
       		}
    		var width = 700;
   			var height = 420;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "新增方案",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/yljz/baseinfo/schema/schemaManager_detail.jsp?status=add&areacode='+areacode+"&areaname="+areaname 
   			})
    
    }       
	//修改方案 
	function updateCareSchema(){
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
   				title : "修改方案",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/yljz/baseinfo/schema/schemaManager_detail.jsp?status=modify&id='+id
   			})
	
	
	}
	 //删除方案
	 function deletCareSchema(){
	   var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
	   var keyID = row.id
	   var isCurrently=row.isCurrently
	   var schemeName = row.schemeName;//方案名
	   if(isCurrently=='1'){
	       $.ligerDialog.alert("方案:"+schemeName+"，此方案为‘启用’状态不能被删除，如需删除方案，请先将此方案设置为‘禁用’状态，才能进行删除操作！");  
	   }else{
	    $.ligerDialog.confirm("您确定要删除此方案吗？",function(obj){
    	    if(obj){ 
    		    $('body').mask('请稍等，删除数据中...');
	     	    $.post("app/yljz/schemeinfo/deleteSchemeInfo.do",{'SchemeInfoId':keyID+"",'status':isCurrently+""},
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
	    
	 }
	  
	//公式设计
    function addSchema(){
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
   				title : "公式设计",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/yljz/baseinfo/schema/schema_detail.jsp?status=detail&id='+keyID
   			}).max(); 
    }
  //启用方案  
  function useSchema(){ 
  		var node=tree.getSelected();
  		if(node==null){
  		    $.ligerDialog.alert("请选择行政区划！");
  		     return false;
  		} 
        var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      $.ligerDialog.alert("请在方案列表中选择一条方案记录！");
	      return false;
	   }
	   var keyID = row.id
	   var isCurrently=row.isCurrently
	   var year=row.schemeYear;//方案年度
	   var areacode=node.data.code;
  	   $.ligerDialog.confirm("您确定要'启用'此方案吗？此方案启动后原有方案将会变更为'禁用'。",function(obj){
    	    if(obj){ 
    		    $('body').mask('请稍等，正在启用方案...');
	     	    $.post("app/yljz/schemeinfo/updateSchemeInfoStatus.do",{'areacode':areacode,'year':year,'SchemeInfoId':keyID+"",'updatestatus':"1",'nowStatus':isCurrently+""},
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
  
  
  //禁用方案
  function disableSchema(){
  	   var node=tree.getSelected();
  		if(node==null){
  		    $.ligerDialog.alert("请选择行政区划！");
  		     return false;
  		} 
      var row= grid.getSelectedRow() ;
	   if(row==null||row==undefined){
	      return false;
	   }
	   var keyID = row.id
	   var isCurrently=row.isCurrently 
	   var year=row.schemeYear;//方案年度
	   var areacode=node.data.code;
  	  $.ligerDialog.confirm("您确定‘禁用’此方案吗？",function(obj){
    	    if(obj){ 
    		    $('body').mask('请稍等，正在禁用方案...'); 
    		    $.post("app/yljz/schemeinfo/updateSchemeInfoStatus.do",{'areacode':areacode,'year':year,'SchemeInfoId':keyID+"",'updatestatus':"0",'nowStatus':isCurrently+""},
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
		var year= document.getElementById("year").value;
		var node=tree.getSelected();
		if(grid){ 
			 
			  grid.loadServerData({"areacode":node.data.code,"year":year}); 
		}else{    
			 createGrid();
			 grid.loadServerData({"areacode":node.data.code,"year":year}); 
		}
	}
</script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    } 
</style>
</head>
<body  class="p5">  
	<div id="layout">
        <div position="top" id="toptoolbar"></div>
		<div position="left" title="行政区划" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center"   > 
 		    <table id="qm-search-p" border="0"><tbody>
 		       <tr>	  <td width="20px"></td>
 		       	   <td>  方案年度    </td>
 		           <td width="140px" height="33px">
 		               <div class="column" style="width:70%; "> 
 		                   <div class="field-left">
 		                     	<div class="l-text"><input type="text" id="year" class="l-text-field" ligeruiid="p-q-f0">
 		                  		<div class="l-text-l"></div>
 		                  		 <div class="l-text-r"></div>
 		                   	    </div>
 		                    </div>
 		                    </div> 
 		           </td> 
 		           <td class="l-list-search">
 		               <button onClick="reloadDataGrid();" class="l-button-warp l-button" id="listSearch" ligeruiid="Button1001"><div class="l-button-main l-button-hasicon"><div class="l-button-text">查询</div><div class="l-button-icon l-icon-search-list"></div></div></button>
 		           </td>
 		           </tr>
 		           </tbody>
 		     </table> 
		   
			 <div id="dataGridDiv" style="margin:0; padding:0"></div> 
		</div>
	</div>
	 
</body>
</html>
