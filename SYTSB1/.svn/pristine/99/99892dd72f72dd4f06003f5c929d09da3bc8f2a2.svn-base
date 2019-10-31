<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报检信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
					{name:"hall_no",compare:"like",value:""},
					{name:"com_name",compare:"like",value:""},
					{name:"inspection_type",compare:"=",value:""}		
		            ],
	            tbar:[
	              
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					
					{ text:'新增', id:'add',icon:'add', click: add},
					"-",
				
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
					{ text:'提交', id:'sub',icon:'check', click: sub},
					"-",
					{ text:'删除', id:'del',icon:'delete', click: del},
					"-",
				
					{ text:'打印回执单', id:'print',icon:'print', click: print},
					{ text:'作废', id:'del',icon:'delete', click: del},
					"-",
					{ text:'作废记录', id:'delNote',icon:'detail', click: delNote}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({sub:count>0,modify:count==1,detail:count==1,del:count>0,print:count==1});

	                }
	            }
	};
	
	
	function add(){
		
	
			
			
			
			top.$.dialog({
				width : 800, 
				height : 600, 
				lock : true, 
				title:"添加报检信息",
				content: 'url:app/inspection/inspection_detail.jsp?status=add',
				data : {"window" : window}
			});
		
	}
	
	function sub(){
		$.getJSON("inspection/basic/subDep.do?ids="+Qm.getValuesByName("id"), function(resp){
			
			if(resp.success){
			
				top.$.notice("提交成功！");
				submitAction();
			}else{
				$.ligerDialog.alert(resp.message, "提示");
			}
		})
		
		
		
		
	}
	
	function modify(){
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"修改报检信息",
			content: 'url:app/inspection/inspection_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	function del(){
		
		
		  $.del("确定作废?",
		    		"inspection/basic/del.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	
	
	function detail(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"大厅报检信息",
			content: 'url:app/inspection/inspection_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

	function print(){
		var id = Qm.getValueByName("id");
		$.getJSON("inspection/basic/getPrintContent.do?id="+id, function(resp){
			if(resp.success){
				document.getElementById("printDiv").innerHTML=resp.printContent;
				printHtml("printDiv");
			}else{
				$.ligerDialog.error(resp.message);
			}
		})
	}

	function delNote(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"作废历史信息",
			content: 'url:app/inspection/inspection_story.jsp',
			data : {"window" : window}
		});
	}

	function printHtml(printDiv){
		try{
			pagesetup_null();
			newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
			newwin.document.body.innerHTML=document.getElementById(printDiv).innerHTML;
			newwin.window.print();
 			pagesetup_default();
 			newwin.window.close();
		}catch(e){
		}
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="inspection_list" script="false" singleSelect="false">
	</qm:qm>
	<div id="printDiv" style="display:none;"></div>
</body>
</html>
