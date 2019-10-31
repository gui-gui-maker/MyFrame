<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		 sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},
		 sp_fields:[
		       {label:"委托事宜",name:"remark",compare:"like",value:""},
		       {label:"委托人",name:"entrust_person",compare:"like",value:""}
		], 
		<sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="tbar" code="entrust_list">
			</tbar:toolBar>,
		</sec:authorize>
		<sec:authorize access="hasRole('super_administrate')">
			tbar: [{
				text: '删除', id: 'del', icon: 'delete', click: del
				}, "-", {
				text: '启用委托', id: 'qy', icon: 'userUnlock', click: open
				}
				, "-", {
				text: '结束委托', id: 'jy', icon: 'userLock', click: close
				}
			],
		</sec:authorize>
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				//detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0,
					qy:count==1,
					jy:count==1
				});
			}, afterQmReady: function() {
				var userId = "<sec:authentication property='principal.id'/>";
				Qm.setCondition([{name: "entrust_by", compare: "=", value: userId}]);
				Qm.searchData();
			}
		}
	};
	
	function add() {
		top.$.dialog({
			width: 450,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:pub/entrust/entrust_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 450,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:pub/entrust/entrust_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "entrust/info/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 450,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:pub/entrust/entrust_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function open(){
		var id = Qm.getValueByName("id");
		$.getJSON("entrust/info/open.do",{id:id},function(data){
			if(data.success){
				top.$.notice("委托启用成功!");
				Qm.refreshGrid();
			}else{
				top.$.notice("委托启用失败!");
			}
		})
	}
	function close(){
		var id = Qm.getValueByName("id");
		$.getJSON("entrust/info/close.do",{id:id},function(data){
			if(data.success){
				top.$.notice("委托结束成功!");
				Qm.refreshGrid();
			}else{
				top.$.notice("委托结束失败!");
			}
		})
	}
</script>
</head>
<body>
	<qm:qm pageid="entrust" seachOnload="false">
	</qm:qm>
		<script test="text/javascript">
		Qm.config.sortInfo= [{field:'entrust_start_date',direction:'desc'}];
	</script>
</body>
</html>