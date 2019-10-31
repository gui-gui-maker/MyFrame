<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
  <%@ include file="/k/kui-base-form.jsp"%>
    	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var grid;
	$(function(){
		 $("#toptoolbar").ligerToolBar({ items: [
												 {text: '详情',id:'detail', icon:'detail',click:itemclick,disable:true},
												 { line:true },
		                                         {text: '入库', icon:'cart-put', click:addclick},
		                                         { line:true },
		                                         { text: '修改', click: itemclick }
		                                     ]
		                                     });
		init();
	});
	
	
	
	
	
	function itemclick(){alert("ok");}
	function init(){
        var column=null;
     	   column =[
     	            {display: "",
				isSort: false, 
				width: '30',
				render : function(item, index) {
					return "<a class='l-a l-icon-cart-put' href='javascript:saveDevice(grid," + index + ")'><span>删除</span></a>";
				}
			},
 		 			{ display: '产品名称', name: 'INVOICE_NO',align: 'center', width: 78,totalSummary:{ render: function (e){return "合计：" }}},
 		 			{ display: '入库数量', name: 'rksl',align: 'center', width: 78,editor: { type: 'int' },totalSummary:{ render: function (e){return "" }}},
 		 			
 		 			{ display: '供应商', name: 'CLSS',align: 'center', width: 180,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '类型', name: 'lx',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '规格及型号', name: 'COMPANY_NAME',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '单位', name: 'DEPT',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '数量', name: 'PAY_NO',align: 'center', width: 80,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '金额', name: 'je',align: 'center', width: 80,totalSummary:{ render: function (e){return e.sum }}},
 		 			{ display: '税额', name: 'se',align: 'center', width: 80,totalSummary:{ render: function (e){return  e.sum }}},
 		 			{ display: '总金额', name: 'zje',align: 'center', width: 80,totalSummary:{ render: function (e){return e.sum }}},
 		 			{ display: '使用部门', name: 'PAY_TYPE',align: 'center', width: 100,totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '入库时间', name: 'time',align: 'center', width: 80,type:'date',format: 'yyyy-MM-dd',editor: { type: 'date'},totalSummary:{ render: function (e){return "" }}},
 		 			{ display: '备注', name: 'bz',align: 'center', width: 180,totalSummary:{ render: function (e){return "" }}}
 				]
    		grid = $("#checkGrid").ligerGrid({
             columns:column, 
             enabledEdit: true,
             data:{Rows:[]},
             rownumbers:true,
             frozenRownumbers: false,
             usePager: false,
             allowUnSelectRow:true,
             height:'90%',
             onSelectRow:function(data,rowIndex,rowObj){
            	 var len=grid.getSelectedRows().length;
            	 if(len==1){
					$("#toptoolbar").ligerToolBar().setEnabled({detail: true});
            	 }
            	 
             }
 	  	});
	}
	
	 function getCheckedData(){
		 alert(3);
	 }
	 var rows=[];
	function saveDevice(i,n){
        var manager = $("#checkGrid").ligerGetGridManager();
        var row = manager.getSelectedRow();
        if (!row) { //alert('请选择入库物品'); return; 
        }else{
        	rows.push(row);
        }
//         alert(JSON.stringify(row));
	}
	function addclick(){
		top.$.dialog({
					width : 1100,
					height : 500,
					lock : true,
					parent : api,
					data : {
						window : window,
						beans:rows
					},
					title : "退货",
					content : 'url:app/supplies/replenish/return/return_detail.jsp?pageStatus=add'
				});
	}
	

    function query(){
      	$("body").mask("正在统计数据,请等待...");
//         $.getJSON(encodeURI("feeStatisticsAction/deptDetail.do?startDate=${param.startDate}&endDate=${param.endDate}&dept=${param.dept}"),function(res){
        	var gridDataArr=new Array();
//         	for(var i=0; i<res.rows.length;i++){
        		var rowData=new Object();
        		rowData.INVOICE_NO="电池";
        		rowData.rksl="5";
        		rowData.CLSS="京东";
        		rowData.lx="办公用品";
        		rowData.COMPANY_NAME="DJI";
        		rowData.DEPT="只";
        		rowData.PAY_NO="5";
        		rowData.je="300";
        		rowData.se="1";
        		rowData.time="2018-09-26";
        		rowData.zje="1505";
        		rowData.PAY_TYPE="信息中心";
        		rowData.CASH_PAY="";
        		

        		var rowData1=new Object();
        		rowData1.INVOICE_NO="键盘";
        		rowData1.rksl="1";
        		rowData1.CLSS="京东";
        		rowData1.lx="办公用品";
        		rowData1.COMPANY_NAME="CHERRY";
        		rowData1.DEPT="个";
        		rowData1.PAY_NO="1";
        		rowData1.je="850";
        		rowData1.se="12";
        		rowData1.time="2018-09-26";
        		rowData1.zje="862";
        		rowData1.PAY_TYPE="信息中心";
        		rowData1.CASH_PAY="";
        		

        		var rowData2=new Object();
        		rowData2.INVOICE_NO="编程指南";
        		rowData2.rksl="1";
        		rowData2.CLSS="淘宝";
        		rowData2.lx="图书资料";
        		rowData2.COMPANY_NAME="DFWSADW";
        		rowData2.DEPT="本";
        		rowData2.PAY_NO="1";
        		rowData2.je="360";
        		rowData2.se="12";
        		rowData2.time="2018-09-26";
        		rowData2.zje="372";
        		rowData2.PAY_TYPE="信息中心";
        		rowData2.CASH_PAY="";

        		gridDataArr.push(rowData1);
        		gridDataArr.push(rowData);
        		gridDataArr.push(rowData2);
//         	}

		    if(gridDataArr!=null){
			   grid.loadData({Rows:gridDataArr});
		    }
		    $("body").unmask();
        	
//         });
        
        
    }
</script>

</head>
<body  onload="query()">
 <div id="toptoolbar"></div>
 
	<div class="lb_gys_list" id="titleElement">
	
		<div id="checkGrid">
		</div>
	</div>
</body>
</html>