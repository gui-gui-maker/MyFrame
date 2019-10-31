<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员证书预警列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {			
			sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义	
			sp_fields:[					
						{name:"name",compare:"like",value:""},
						{name:"org_name",compare:"like",value:""}
		            ],
	            tbar:[
					{ text:'详情', id:'detail',icon:'detail', click: detail}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
                		selectionChange();
                	},
	        		rowDblClick :function(rowData,rowIndex){
	        			detail(rowData.employee_id);
	        		},				
	                rowAttrRender : function(rowData, rowid) {
	           		    var orange = 180;	// 六个月内到期
	           		 	var blue = 90;	// 三个月内到期
	           			var red = 0;	// 已过期	           		    
	            		var fontColor="black";            		
	            		//到期180天至90天之间 显示黄色
	            		if (rowData.warning <= orange && rowData.warning > blue){
	            			fontColor="orange";
	            		}
	                    //到期90天之内 显示蓝色
	            		else if(rowData.warning <= blue && rowData.warning > red ) {
	            			fontColor="blue";
	            		}
	            		//到期 显示红色
	            		else if(rowData.warning <= red) {
	            			fontColor="red";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	
	
	
	            }
	};
	
	// 行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount(); // 行选择个数
		if(count == 1){
	   		Qm.setTbar({detail: true});            	
		}else if(count > 1){
	  		Qm.setTbar({detail: false});
	  	}else{
	   		Qm.setTbar({detail: false});
		}
	}
	
	function detail(basic_id){
		if($.type(basic_id)!="string"){
			basic_id = Qm.getValueByName("employee_id").toString();
		}
		top.$.dialog({
			width:700,
			height:500,
			lock:true,
			title:"查看详情",
			content: 'url:app/employee/employee_detail.jsp?status=detail&id=' + basic_id,
			data:{window:window},
			cancel:true
		});
	}
      
	// 刷新Grid
	function refreshGrid() {
    	Qm.refreshGrid();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="orange">“橙色”</font>代表证书6个月内到期，
				<font color="blue">“蓝色”</font>代表证书3个月内到期，
				<font color="red">“红色”</font>代表证书已过期。			
			</div>
		</div>
	</div>
	<qm:qm pageid="employee_waring" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>