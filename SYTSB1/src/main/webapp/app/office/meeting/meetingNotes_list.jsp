<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{labelAlign:'right',labelSeparator:'',labelWidth:80},//可以自己定义 layout:column,float,
			sp_fields : [
				{
					name : "name", compare : "like"
				},{
					name : "person_name", compare : "like"
				},
				{group:[
				        {name:"notes_time",compare:">=",value:""},
				        {label:"到",name:"notes_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
				        ]
				}
		], 
		tbar:[{
                text: '详情', id: 'detail', icon: 'detail', click: detail
            }, "-", {
                text: '新增', id: 'add', icon: 'add', click: add 
            }, "-", {
                text: '修改', id: 'modify', icon: 'modify', click:modify
            }, "-", {
                text: '删除', id: 'del', icon: 'delete', click:del
            }
            ],
		////            //提供以下4个事件
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail(rowData);
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				//alert(rowid)
			}, afterQmReady: function() {
				var orgCode = "<sec:authentication property='principal.unit.id' htmlEscape='false' />";
				Qm.setCondition([{name: "unit_id", compare: "=", value: orgCode}]);
				Qm.searchData();
			}
		}
	};
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				add : true, modify : false, del : false, detail : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				add : true, modify : true, del : true, detail : true
			});
		} else {
			Qm.setTbar({
				add : true, modify : false, del : true, detail : false
			});
		}
	}

	function modify(item) {
		var reqId=Qm.getValueByName("fkreqid");
		var selectedId = Qm.getValuesByName("id");
		var status = "modify";
		/* if (item.id == "modify") {//点击修改按钮调用的本方法
			selectedId = Qm.getValuesByName("id");
		} else {//双击数据调用本方法
			selectedId[0] = item.id;
			status = "detail";
		} */
		if (selectedId.length > 1) {
			$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
			return;
		} else if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
			return;
		}
		var width = 700;
		var height = 462;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "修改", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingNotes_detail.jsp?status=' + status + '&id=' + selectedId+'&reqId='+reqId
		});
	}
	function add(item) {
		var width = 700;
		var height = 462;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingNotes_detail.jsp?status=add'

		});
	}

	function del() {
		var reqId = Qm.getValuesByName("fkreqid");
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"oa/meetingNotes/info/deletes.do?reqId="+reqId,
					type:"post",
					async:false,
					success:function(data){
						if(data.success){
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}

	function detail() {
		var selectedId = Qm.getValuesByName("id");
		var reqId= Qm.getValuesByName("fkreqid");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
			return;
		}
		var width = 700;
		var height = 320;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingNotes_detail.jsp?status=detail&id=' + selectedId+'&reqId='+reqId
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_OFFICE_MNOTES" script="false" singleSelect="false"></qm:qm>
</body>
</html>