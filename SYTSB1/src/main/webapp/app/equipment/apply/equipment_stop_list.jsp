<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备停用申请</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
		// 检验设备类型
		var category_01 = <u:dict code="BASE_EQ_JY_CATEGORY"/>
		// 办公设备类型
		var category_03 = <u:dict code="BASE_EQ_BG_CATEGORY"/>
		// 耗材
		var category_08 = <u:dict code="BASE_EQ_HC_CATEGORY"/>
		
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"eq_name", compare:"like"},
            	{name:"eq_no", compare:"like"},
            	{name:"apply_unit_name", compare:"like"},
            	{name:"apply_name", compare:"like"},
            	{group:[
						{name:"apply_date", compare:">=", value:""},
						{label:"到", name:"apply_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
				{name:"apply_audit_result", compare:"="}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail},
                "-",
                { text:'增加', id:'add',icon:'add', click: add},
                { text:'修改', id:'modify',icon:'modify', click:modify},
                { text:'删除', id:'del',icon:'delete', click:del}
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
	            Qm.setTbar({add: true, modify: true, del: true, detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({add: true, modify: false, del: true, detail: false});
	    	}else{
	    		Qm.setTbar({add: true, modify: false, del: false, detail: false});
	    	}
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValuesByName("id").toString();
			}
			top.$.dialog({
				width:800,
				height:480,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/apply/equipment_stop_detail.jsp?status=detail&id=' + id+'&apply_type=05',
				data:{window:window},
				cancel:true
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/apply/equipment_stop_detail.jsp?status=add&apply_type=05'
			});
        }
        
        //修改
        function modify(){
        	var apply_status = Qm.getValuesByName("apply_status").toString();
        	apply_status = parseStatus(apply_status, null); 
        	if("申请" != apply_status){
        		$.ligerDialog.alert(apply_status+"的申请不能修改！");
        		return;
        	}
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/apply/equipment_stop_detail.jsp?status=modify&id='+Qm.getValueByName("id")+'&apply_type=05'
			});
        } 
        
        //删除
        function del(){
        	var apply_status = Qm.getValuesByName("apply_status").toString();
        	if(apply_status.indexOf("审核通过")!=-1 || apply_status.indexOf("审核不通过")!=-1 || 
        	apply_status.indexOf("已出库")!=-1 || apply_status.indexOf("部分入库")!=-1 || 
        	apply_status.indexOf("已入库")!=-1 || apply_status.indexOf("已结束")!=-1){
        		$.ligerDialog.alert("所选记录存在不能删除的申请！");
        		return;
        	}
        	$.del("确定要删除？删除后无法恢复！","equipment2ApplyAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }  
        
        function parseStatus(thisValue,row){
        	var showText = "";
        	if("01" == thisValue){
        		showText = "申请"; 
        	}else if("02" == thisValue){
        		showText = "审核通过"; 
        	}else if("03" == thisValue){
        		showText = "审核不通过"; 
        	}else if("04" == thisValue){
        		showText = "已出库"; 
        	}else if("05" == thisValue){
        		if(row != null){
        			if(row["apply_count"]==row["return_count"]){
	        			showText = "已入库"; 
	        		}else{
	        			showText = "部分入库"; 
	        		}
        		}else{
        			showText = "已入库"; 
        		}
        	}else if("06" == thisValue){
        		showText = "已入库"; 
        	}else if("07" == thisValue){
        		showText = "已结束"; 
        	}else if("08" == thisValue){
        		showText = "部分报废"; 
        	}else if("09" == thisValue){
        		showText = "已报废"; 
        	}
        	return showText;
        }
        
        function parseCategory(thisValue,row){
			var showText = "";
			if("检验设备" == row["eq_type"]){
				showText = render(thisValue,category_01);
			}else if("办公设备" == row["eq_type"]){
				showText = render(thisValue,category_03);
			}else if("耗材" == row["eq_type"]){
				showText = render(thisValue,category_08);
			}
			return showText;
		}
			
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>
		<qm:qm pageid="eq2_stop_list" singleSelect="false">
			<qm:param name="apply_type" value="05" compare="="/>
		</qm:qm>
	</body>
</html>