<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<title>检验设备箱</title>

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
                       {name:'box_number',compare:'like'},
                       {name:'box_dep_name',compare:'like'}
            ],
			tbar:[
					{ text:'增加', id:'add',icon:'add', click: add}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	selectionChange();
	                },
	        		rowDblClick :function(rowData,rowIndex){
	        			detail(rowData.id);
	        		},
	        		rowAttrRender : function(rowData, rowid) {
	    	            var fontColor="black";
	    	            return "style='color:"+fontColor+"'";
	    	        }
	            }
	};
	
	 // 行选择改变事件
	function selectionChange() {
		Qm.setTbar({add: true, detail: true});     
	}
    //新增
    function add(){
		top.$.dialog({
			width: 900,
			height: 600,
			lock:true,
			title:"新增",
			data: {window:window},
			content: 'url:app/equipment/base/equipment_box_add.jsp?pageStatus=add'
		});
    }
    //选择
    function choose(){
    	if(Qm.getSelectedCount()!=1){
            return null;
        }else{
            return {
                id: Qm.getValueByName("id"),
                box_number: Qm.getValueByName("box_number")
            };
        }
	}
    
    // 刷新Grid
    function refreshGrid() {
        Qm.refreshGrid();
    }
	
</script>

</head>
<body>
	<qm:qm pageid="TJY2_EQUIPBOX_CHOOSE" singleSelect="false"></qm:qm>
</body>
</html>