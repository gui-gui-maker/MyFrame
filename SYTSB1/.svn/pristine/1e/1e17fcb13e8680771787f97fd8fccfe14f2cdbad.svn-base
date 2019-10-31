<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备申请审核</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"apply_type", compare:"="},
            	{name:"apply_unit_name", compare:"like"},
            	{name:"apply_name", compare:"like"}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail},
                "-",
                { text:'审核', id:'check',icon:'check', click: check}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({check: true, detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({check: false, detail: false});
	    	}else{
	    		Qm.setTbar({check: false, detail: false});
	    	}
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			var apply_type =  Qm.getValueByName("apply_type").toString();
			var url = 'url:app/equipment/apply/equipment_apply_detail.jsp?status=detail&id=' + id;
			if("采购" == apply_type){
        		url = 'url:app/equipment/apply/equipment_buy_detail.jsp?status=detail&id=' + id;
        	}else if("维修" == apply_type){
        		url = 'url:app/equipment/apply/equipment_repair_detail.jsp?status=detail&id=' + id;
        	}else if("借用" == apply_type){
        		url = 'url:app/equipment/apply/equipment_borrow_detail.jsp?status=detail&id=' + id;
        	}else if("报废" == apply_type){
        		url = 'url:app/equipment/apply/equipment_scrap_detail.jsp?status=detail&id=' + id;
        	}else if("停用" == apply_type){
        		url = 'url:app/equipment/apply/equipment_stop_detail.jsp?status=detail&id=' + id;
        	}
			top.$.dialog({
				width:800,
				height:480,
				lock:true,
				title:"查看详情",
				content: url,
				data:{window:window},
				cancel:true
			});
		}
        
        //审核
        function check(){
        	var apply_type =  Qm.getValueByName("apply_type").toString();
        	if("采购" == apply_type){
        		apply_type = "01";
        	}else if("维修" == apply_type){
        		apply_type = "02";
        	}else if("借用" == apply_type){
        		apply_type = "03";
        	}else if("报废" == apply_type){
        		apply_type = "04";
        	}else if("停用" == apply_type){
        		apply_type = "05";
        	}
			top.$.dialog({
				width: 700,
				height: 200,
				lock:true,
				title:"审核",
				data: {window:window},
				content: 'url:app/equipment/apply/audit/equipment_apply_check.jsp?status=modify&apply_type='+apply_type+'&id='+Qm.getValueByName("id")
			});
        }    
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<%
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
		%>
		<qm:qm pageid="eq2_sqsh_list" singleSelect="false">
		<%
			if(StringUtil.isNotEmpty(user.getId()) && !"100001".equals(user.getSysUser().getOrg().getId())){
				%>
				<qm:param name="apply_submit_object_id" compare="=" value="<%=user.getId() %>"/>
				<%
			}
		%>	
		</qm:qm>
	</body>
</html>
