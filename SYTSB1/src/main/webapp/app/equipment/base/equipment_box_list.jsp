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
                       {name:'box_dep_name',compare:'like'},
                       {group:[
       						{name:"box_time", compare:">=", value:""},
       						{label:"到", name:"box_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
       					]},
            ],
			tbar:[
	            	{ text:'详情', id:'detail',icon:'detail', click: detail},
	                "-",
	                { text:'增加', id:'add',icon:'add', click: add},
	                "-",
	                { text:'修改', id:'modify',icon:'modify', click:modify},
	                "-",
	                { text:'删除', id:'del',icon:'delete', click:del}
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
	    	            if(rowData.warning<=0 && rowData.warning!="" && rowData.warning!=null){
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
            Qm.setTbar({add: true, modify: true, del: true, detail: true});            	
 		}else if(count > 1){
       		Qm.setTbar({add: true, modify: false, del: true, detail: false});
    	}else{
    		Qm.setTbar({add: true, modify: false, del: false, detail: false});
    	}
	}
	function detail(id){
		var id = Qm.getValueByName("id").toString();
		top.$.dialog({
			width:900,
			height:600,
			lock:true,
			title:"查看详情",
			content: 'url:app/equipment/base/equipment_box_detail.jsp?pageStatus=detail&id=' + id,
			data:{window:window},
			cancel:true
		});
	}
    
    //新增
    function add(){
		top.$.dialog({
			width: 900,
			height: 600,
			lock:true,
			title:"新增",
			data: {window:window},
			content: 'url:app/equipment/base/equipment_box_detail.jsp?pageStatus=add'
		});
    }
    
    //修改
    function modify(){
		top.$.dialog({
			width: 900,
			height: 600,
			lock:true,
			title:"修改",
			data: {window:window},
			content: 'url:app/equipment/base/equipment_box_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
		});
    } 
    
    //删除
    function del(){
        $.del("确定要删除？删除后无法恢复！","equipment/box/delete.do",{"ids":Qm.getValuesByName("id").toString()});
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
                <font color="red">“红色”</font>代表设备箱中有下次检定日期超期的设备。
            </div>
        </div>
    </div>
	<qm:qm pageid="TJY2_EQUIPMENT_BOX" singleSelect="false"></qm:qm>
</body>
</html>