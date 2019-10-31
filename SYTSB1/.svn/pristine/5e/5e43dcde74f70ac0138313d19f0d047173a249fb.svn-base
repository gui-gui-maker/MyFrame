<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>派车管理</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},//可以自己定义 layout:column,float,
			sp_fields : [
				{
					name : "apply_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
				},{
					name : "destination", compare : "like"
				},{
					name : "used_car_reason", compare : "like"
				},{
					name : "car_num", compare : "like"
				},{group:[
				           {name:"start_time",compare:">=",value:""},
				           {label:"到",name:"start_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
				           ],columnWidth:0.33
				},{
					group:[
					       {name:"end_time",compare:">=",value:""},
					       {label:"到",name:"end_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					       ],columnWidth:0.33
				}
		], 
		<tbar:toolBar type="tbar">
		</tbar:toolBar>,
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
				modify(rowData);
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				if (rowData.change_num != "")
					rowData.car_num = rowData.car_num + "(" + rowData.change_num + ")";
				var stl="";
				if(rowData.state=='未派车'){
					stl = "style='color:red'";
				}
				return stl;
			}
		}
	};
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var state = Qm.getValuesByName("state");
		if (count == 0) {
			Qm.setTbar({
				detail : false, submit : false, end : false,confirm:false
			});
		} else if (count == 1) {
			if (state == "未派车") {
				Qm.setTbar({
					detail : true, submit : true, end : false,confirm:false
				});
			}else if (state == "已派车") {
				Qm.setTbar({
					detail : true, submit : false, end : true,confirm:false
				});
			}else if (state == "归还确认中") {
				Qm.setTbar({
					detail : true, submit : false, end : false,confirm:true
				});
			}else{
				Qm.setTbar({
					detail : true, submit : false, end : false,confirm:false
				});
			}
		} else {
			Qm.setTbar({
				detail : false, submit : false, end : false,confirm:false
			});
		}
	}
	
	//显示部门选择列表
	function showDepartList(){
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		$(this).data('unitId',unitId);
		$(this).data('unitName',unitName);
		showOrgList.call(this);
	}

	function modify(item) {
		var selectedId = new Array();
		var status = "modify";
		var title = "修改";
		var width = 700;
		var height = 400;
		if (item.id == "modify") {//点击修改按钮调用的本方法
			selectedId = Qm.getValuesByName("id");
		} else {//双击数据调用本方法
			selectedId[0] = item.id;
			status = "detail";
			title = "详情"
			height = 264;
		}
		if (selectedId.length > 1) {
			$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
			return;
		} else if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
			return;
		}
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : title, data : {
				"window" : window
			}, content : 'url:app/oa/car/apply_detail.jsp?status=' + status + '&id=' + selectedId
		});
	}
	function add(item) {
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/oa/car/apply_detail.jsp?status=add'
		});
	}

	function del() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"oa/car/apply/delete.do?ids="+selectedId,
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
		var width = 700;
		var height = 264;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/oa/car/apply_detail.jsp?status=detail' + '&id=' + selectedId
		});
	}
	function submit() {
		var width = 400;
		var height = 300;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "派车", data : {
				"window" : window
			},
			content : 'url:app/oa/car/sendCar_detail.jsp?id=' + Qm.getValuesByName("id")+"&car_id="+Qm.getValuesByName("car_id")
		});
	}
	function end() {
		var width = 400;
		var height = 357;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "归还车辆", data : {
				"window" : window
			}, content : 'url:app/oa/car/backCar_detail.jsp?id=' + Qm.getValuesByName("id")
		});
	}
	
	function apply(){
		var width = 700;
		var height = 332;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/oa/car/pc_apply_detail.jsp?status=add'
		});
	}
	function confirm() {
		var id = Qm.getValueByName("id");
        $.ligerDialog.confirm('是否确认归还？', function (yes){
            if(!yes){return false;}
            top.$.ajax({
                url: "oa/car/apply/confirm.do?id="+id,
                type: "GET",
                dataType:'json',
                async: false,
                success:function (data) {
                   if(data.success){
                       $.ligerDialog.success("操作成功！");
                       Qm.refreshGrid();//刷新
                   }else{
                       $.ligerDialog.error("操作失败！");
                   }
                },
                error:function () {
                    $.ligerDialog.warn("提交失败！");
                }
            });
        });
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
	Org org = (Org) SecurityUtil.getSecurityUser().getDepartment();
%>
<body>
	<!-- 提示文字 -->
    <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：
				<font color="red">“红色”</font>代表未派车。
				<font color="#000000">“黑色”</font>代表已派车 。
			</div>
		</div>
	</div>
	<qm:qm pageid="oa_c_send" script="false" singleSelect="true">
		<qm:param name="state" value="派车" compare="like" />
		<qm:param name="state" value="归还确认中" compare="like" logic="or"/>
		<qm:param name="org_code" value="<%=unitId %>" compare="=" />
		<sec:authorize access="hasRole('oa_car_info')">
			<qm:param name="level_code" value="" compare="like" />
		</sec:authorize>
		<sec:authorize ifNotGranted="oa_car_info">
			<qm:param name="level_code" value="<%=org.getLevelCode() %>" compare="like"/>
		</sec:authorize>
	</qm:qm>
</body>
</html>