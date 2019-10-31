<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
						{name:"equipment_name", compare:"like"},
						{name:"equipment_numbe", compare:"like"},
						{name:"eq_type", compare:"like"},
					  	{group: [
						  		{name: "equipment_time", compare: ">=", value:""},
						  		{label: "到", name: "equipment_time", compare: "<=", labelAlign: "center", labelWidth:20}
						  	]},
						{name:"unit", compare:"like"},
						{name:"status", compare:"="}
			],
		tbar: [ 
		{text: '详情', id: 'detail', icon: 'detail', click: detail}
		, "-", 
		{text: '送检', id: 'add', icon: 'add', click: add}
		,"-",
		{text: '修改', id: 'edit', icon: 'modify', click: edit}
		, "-", 
		{text: '删除', id: 'del', icon: 'delete', click: del}
		,"-",
		{text: '下发', id: 'submit', icon: 'issued', click: addxf}
		],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
			    var up_status = Qm.getValuesByName("status");
			    var allowXf = true;
			    for(var i=0;i<up_status.length;i++){
		        	 if("进行中"==up_status[i] || "已完成"==up_status[i] || "未开始"==up_status[i]){
		        		 allowXf =  false;
		        	 }else if(up_status[i]=="未下发"){
		        		 var restart = false;
		        	 }
		         }
			    Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count==1,
					submit:count>0&&allowXf
				});
			},
			rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if(rowData.status=='进行中'){
                	fontColor="blue";
                }else if(rowData.status=='未开始') {
                    fontColor="orange";
                }else if (rowData.status=='已完成'){
                    fontColor="green";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	
	function addxf(){
	  	var ids = Qm.getValuesByName("id");
	    $.ligerDialog.confirm('是否提交下发？', function (yes){
	        if(!yes){return false;}
	        top.$.ajax({
	            url: "eq/docimasyFkAction/xf.do?ids="+ids,
	            type: "GET",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	           
	               if(data.success){
	                   var manager = $.ligerDialog.waitting('下发成功！');
	                   setTimeout(function (){manager.close();},500);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn(data.msg);
	               }
	            },
	            error:function () {
	                  var manager = $.ligerDialog.waitting('下发失败！');
	                   setTimeout(function (){manager.close();},500);
	            }
	        });
	    });
}
	
	function add() {
		top.$.dialog({
			width: 600,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/equipment/apply/equipment_docimasy_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/equipment/apply/equipment_docimasy_detail_update.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "eq/docimasyFkAction/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/equipment/apply/equipment_docimasy_xq_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未下发，
                <font color="orange">“橙色”</font>代表未开始，
                <font color="blue">“蓝色”</font>代表进行中，
                <font color="green">“绿色”</font>代表已完成。
            </div>
        </div>
    </div>
	<qm:qm pageid="TJY2_EQ_DOCIMASY_FK"  script="false" singleSelect="false" ></qm:qm>
</body>
</html>