<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备校核预警列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {			
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义	
			sp_fields:[		
						{name:"eq_name",compare:"like",value:""},
						{name:"eq_no",compare:"like",value:""},
						{group:[
								{name:"eq_next_check_date", compare:">=", value:""},
								{label:"到", name:"eq_next_check_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
							]}
		            ],
	            tbar:[
					{ text:'详情', id:'detail',icon:'detail', click: detail}	
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
                		selectionChange();
                	},
	        		rowDblClick :function(rowData,rowIndex){
	        			detail(rowData.id);
	        		},				
	                rowAttrRender : function(rowData, rowid) {
	           		    var blue = 60;	// 2个月内到期
	           		 	var orange = 30;	// 1个月内到期
	           			var red = 0;	// 已过期	           		    
	            		var fontColor="black";            		
	            		//到期60天至30天之间 显示蓝色
	            		if (rowData.warning <= blue && rowData.warning > orange){
	            			fontColor="blue";
	            		}
	                    //到期30天之内 显示橙色
	            		else if(rowData.warning <= orange && rowData.warning > red ) {
	            			fontColor="orange";
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
	//详情
	function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + id,
				data:{window:window}
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
				<font color="blue">“蓝色”</font>代表设备2个月内校核到期，
				<font color="orange">“橙色”</font>代表设备1个月内校核到期，
				<font color="red">“红色”</font>代表设备校核已过期。			
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_EQ_JHYJ_LIST" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>