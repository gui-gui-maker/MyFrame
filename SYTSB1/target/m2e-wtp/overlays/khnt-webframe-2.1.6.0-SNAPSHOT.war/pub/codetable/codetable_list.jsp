<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_fields : [
		 	{name:"code",compare:"like"},
			{name:"name",compare:"like"},
			{name:"type",compare:"="}
		],
		tbar:[/*  {
			text: '修改',id: 'modify',click: modify,icon: "modify",disable: true
		}, "-", */ {
			text: '增加值',id: 'addValue',click: addValue,icon: 'info-add',disable: true
		}, "-", {
			text: '修改值',id: 'modifyValue',click: modifyValue,icon: "info-edit",disable: true
		}, "-", {
			text: '删除值',id: 'delValue',click: delValue,icon: "info-del",disable: true
		},
		"-", {
			text: '禁用值',id: 'jy',click: change,icon: "info-del",disable: true
		},"-",
		{
			text: '启用值',id: 'qy',click: change,icon: "info-del",disable: true
		},"-", {
			text: '刷新缓存',id: 'refreshCache',click: refreshCache,icon: 'refurbish'
		}, "-", {
			text: '<span style="color:red">更新码表后，必须执行【刷新码表】同步系统缓存！</span>',id: 'refreshCache'
		} ],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					modify : count == 1,
					addValue : count == 1,
					delValue : count == 1,
					modifyValue:count==1,
					jy:count==1,
					qy:count==1
				});
			},
			detail: {
				onShowDetail:onShowChildList ,
				height:'100%',   
				width:'100%'
			}
		}
	};
	
	function onShowChildList(row,detailPanel,callback){
  	 	var grid = document.createElement('div');
  	 	grid.setAttribute("id",row.id+"div");
        $(detailPanel).append(grid);
        $(grid).css('margin',0).ligerGrid({
        	columns: [ {
				display: 'id', name: 'id',hide:true
			},{
				display: '名称', name: 'name', width: '250', align: 'left',render:function(row){
					return "<div title='"+row.name+"'>"+row.name+"</div>";
				}
			}, {
				display: '值', name: 'code', width: '100', align: 'left'
			}, {
				display: '排序', name: 'sort', width: '70', align: 'left'
			},{
				display: '状态', name: 'status', width: '70', align: 'center',render:function(row){
					return row.status=='0'?"禁用":"启用";
				}
			} ,{
				display: '说明', name: 'remark', width: '150', align: 'left',render:function(row){
					return "<div title='"+row.remark+"'>"+row.remark+"</div>";
				}
			} ],
			url:'pub/codetablevalue/getCodetableValues.do?id=' + row.id,
			usePager: false,
			height: '100%',
			tree: { columnName: 'name' },
			checkbox: false,
			autoCheckChildren: false,
			isScroll: false, 
			rownumbers:true,
			onAfterShowData: callback
        });  
	} 
	
	function modify() {
		top.$.dialog({
			width: 450,
			height: 260,
			lock: true,
			data: {"window": window},
			title: "修改码表",
			content: 'url:pub/codetable/codetable_detail.jsp?status=modify&id='
					+ Qm.getValueByName("id")
		});
	}
	//增加值
	function addValue() {
		var actionNodeID2 = "";
		if(Qm.getValueByName("type")=="树状类型"){
			var grid = getChildGrid();
			if(grid){
				var row = grid.getSelectedRow();
				if (row) actionNodeID2 = row.id;
			}
		}
		top.$.dialog({
			width: 450,
			height: 220,
			lock: true,
			title: "新增表值",
			content: 'url:pub/codetable/codetablevalue_detail.jsp?status=add&id='
					+ Qm.getValueByName("id")
					+ '&code_table_value_id='
					+ actionNodeID2,
			data: {
				"window": window
			}
		});
	}
	//修改值
	function modifyValue() {
		var row ;
		var grid = getChildGrid();
		if(grid){
			row = grid.getSelectedRow();
		}
		if (row) {
			var actionNodeID2 = row.id;
			top.$.dialog({
				width: 450,
				height: 220,
				lock: true,
				title: "修改表值",
				content: 'url:pub/codetable/codetablevalue_detail.jsp?status=modify&id='
						+ actionNodeID2,
				data: {
					"window": window
				}
			});
		} else {
			$.ligerDialog.warn('请选择需要修改的值!')
		}
	}

	//删除值
	function delValue() {
		var row;
		var grid = getChildGrid();
		if(grid){
			row = grid.getSelected();
		}
		if (row) {
			$.ligerDialog.confirm("删除码值将删除自身值和子码值，确定要删除？",function(yes) {
				if (yes) {
					$.post("pub/codetablevalue/deleteValue.do",{
						"ids": row.id,
						"codeTabledId": Qm.getValueByName("id")
					}, function(resp) {
						if (resp.success) {
							grid.remove(row);//执行删除
						} else {
							$.ligerDialog.error('删除失败');
							return false;
						}
					}, "json");
				}
			});
		} else {
			$.ligerDialog.warn('请选择您要删除的码表值！')
		}
	}

	//刷新码表值数据
	function reloadDataGrid() {
		Qm.refreshGrid();
	}
	
	//刷新码表缓存
	function refreshCache(){
		$("body").mask("正在刷新...");
		 $.getJSON("pub/codetable/refreshCache.do",function(resp){
         	if(resp.success){
         		top.$.notice('刷新缓存成功！',4)
         		Qm.refreshGrid();
         	}
         	else
         		$.ligerDialog.error('刷新缓存失败！请稍后再试。');
			$("body").unmask();
    	});
	}
	function change(obj){
		var row  ;
		var grid = getChildGrid();
		if(grid){
			row = grid.getSelected();
		}
		if (row) {
			var title = "";
			var status = "";
			var t="";
			if(obj.id=='jy'){
				title = "确定要禁用码值"+row.code+"吗？";
				t = "禁用成功";
				status = 0;
			}else{
				title = "确定要启用码值"+row.code+"吗？";
				t = "启用成功";
				status = 1;
			}
			$.ligerDialog.confirm(title,function(yes) {
				if (yes) {
					$.getJSON("pub/codetablevalue/changeStatus.do",{status:status,ids:row.id,codeTabledId:Qm.getValueByName("id")},function(res){
						if(res.success){
							top.$.notice(t,4);
							reloadDataGrid();
						}else{
							$.ligerDialog.error('删除失败');
							return false;
						}
					})
				}
			});
		} else {
			$.ligerDialog.warn('请选择您要操作的码表值！')
		}
	}
	
	function refreshTree(){
		Qm.refreshGrid();
	}

	
	function getChildGrid(){
		var rowId = Qm.getValueByName("id");
		var gridManager = $("#"+rowId+"div").ligerGetGridManager();
		return gridManager;
	}
	
</script>
</head>
<body>
	<qm:qm pageid="codetable_list" singleSelect="true"></qm:qm>
</body>
</html>