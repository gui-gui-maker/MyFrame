<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<head>
<title>车辆保险提醒列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ name : "year_month", compare : "like",value:"<%=nowTime%>"}],
		tbar : [{ text : '详情', id : 'detail', click : detail, icon : 'detail' },
			{ text : '新增', id : 'add', click : add, icon : 'add' },
			{ text : '修改', id : 'modify', click : modify, icon : 'modify' },
			{ text : '删除', id : 'del', click : del, icon : 'del' }
			<sec:authorize access="hasRole('sys_administrate')">
			,
			{ text : '初始化本月数据', id : 'initialize', click : initialize, icon : 'f5' }
			</sec:authorize>
			],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var data_status = Qm.getValueByName("data_status");
		if (count == 1) {
			Qm.setTbar({detail : true, modify : true, del : true});
		} else {
			Qm.setTbar({detail : false, modify : false, del : false});
		}
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		if(selectedId == null || selectedId == "" || selectedId == "undefined"){
			$.ligerDialog.alert("此车最新油耗记录不存在或已被删除！请添加油耗信息！");
		}else{
			var width = 800;
			var height = 800;
			var windows = top.$.dialog({
				width : width,
				height : height,
				lock : true,
				title : "详情",
				data : {
					"window" : window
				},
				content : 'url:app/car/car_wb/pc/car_mpg_detail.jsp?pageStatus=detail&id='+ selectedId
			});
		}
	}
	//新增
	function add() {
		var width = 800;
		var height = 800;
		top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/car_mpg_detail.jsp?pageStatus=add'
		});
	}
	//修改
	function modify() {
		var selectedId = Qm.getValueByName("id");
		if(selectedId == null || selectedId == "" || selectedId == "undefined"){
			$.ligerDialog.alert("此车最新油耗记录不存在或已被删除！请添加油耗信息！");
		}else{
			var width = 800;
			var height = 800;
			var windows = top.$.dialog({
				width : width,
				height : height,
				lock : true,
				title : "修改",
				data : {
					"window" : window
				},
				content : 'url:app/car/car_wb/pc/car_mpg_detail.jsp?pageStatus=modify&id=' + selectedId
			});
		}
	}
	//删除
	function del() {
		var selectedId = Qm.getValueByName("id");
		if(selectedId == null || selectedId == "" || selectedId == "undefined"){
			$.ligerDialog.alert("此车最新油耗记录不存在或已被删除！请添加油耗信息！");
		}else{
			$.del("确定要删除？删除后无法恢复！","carMpgAction/delete.do",{"ids":selectedId});
		}
	}
	function initialize(){
		$.ligerDialog.confirm('是否要初始化本月数据？已有数据不会丢失！', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "carMpgAction/initialize.do",
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                    if(data){
                       top.$.notice(data.msg,3);
                       Qm.refreshGrid();//刷新
                    }else{
                        $.ligerDialog.warn(data.msg);
                    }
                 },
                 error:function () {
                     //$.ligerDialog.warn("提交失败！");
                	 $.ligerDialog.error("出错了！请重试！！！");
                 }
             });
        });
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<qm:qm pageid="TJY2_CAR_MPG " script="false" singleSelect="true">
	</qm:qm>
</body>
</html>