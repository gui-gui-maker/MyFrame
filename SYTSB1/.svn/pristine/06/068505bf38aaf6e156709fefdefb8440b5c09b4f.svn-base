<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_fields:[{name:"status",compare: "like",width:"200",columnWidth:0.3},
				         {name:"EQUIPMENTNAME",compare: "like",width:"200",columnWidth:0.3}, 
				         {name:"borrower_name",compare: "like",width:"200",columnWidth:0.3},
				  		
				  		
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
			}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
			text: '归还', id: 'submit', icon: 'submit', click: gh
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
			//var up_status = Qm.getValueByName("status");
			
			var  status= Qm.getValueByName("status");
	            if(status=="已归还"){
	            	Qm.setTbar({
						detail: count==1,
						edit: count<1,
						del: count>0,
						submit:count<0,
					});
	            }else{
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							del: count>0,
							submit:count==1,
						});
						
	            }
					
			}
		}
	};
	
	//归还  改变状态和归还时间
	function gh(){
	
	  var id = Qm.getValueByName("id");
	    $.ligerDialog.confirm('是否归还？', function (yes){
	        if(!yes){return false;}
	        top.$.ajax({
	                     url: "equipmentUseRegisterAction/gh.do?id="+id,
	                     type: "GET",
	                     dataType:'json',
	                     async: false,
	                     success:function (data) {
	                    
	                        if(data.success){
	                            var manager = $.ligerDialog.waitting('归还成功！');
                             setTimeout(function (){manager.close();},2000);
	                            Qm.refreshGrid();//刷新
	                        }else{
	                            $.ligerDialog.warn(data.msg);
	                        }
	                     },
	                     error:function () {
	                           var manager = $.ligerDialog.waitting('归还失败！');
                             setTimeout(function (){manager.close();},2000);
	                     }
	                 });
	    });
	
}
	
	function add() {
		top.$.dialog({
			width: 500,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/equipment/apply/equipment_register_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 500,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/equipment/apply/equipment_register_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "equipmentUseRegisterAction/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 500,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/equipment/apply/equipment_register_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	
</script>
</head>
<body>
	<qm:qm pageid="eq2_user_register"  script="false" singleSelect="true" ></qm:qm>
	
	
	
</body>
</html>