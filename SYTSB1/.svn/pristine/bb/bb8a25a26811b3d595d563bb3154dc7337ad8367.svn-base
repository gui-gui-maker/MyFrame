<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults:{labelAlign:'right',labelSeparator:'',labelWidth:80},//可以自己定义 layout:column,float, 
				sp_fields : [
					{
						name : "company", compare : "like"
					},{
						name : "apply_department_id", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
					},{
						group:[
						       {name:"lease_time",compare:">=",value:""},
						       {label:"到",name:"return_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
						      ]
					}
				],
				<tbar:toolBar type="tbar">
				</tbar:toolBar>,
					listeners : {
						rowClick : function(rowData, rowIndex) {
						},
						rowDblClick : function(rowData, rowIndex) {
							modify(rowData);
						},
						selectionChange : function(rowData, rowIndex) {
							selectionChange();
						},
						rowAttrRender : function(rowData, rowid) {
							if(rowData.change_num!="")rowData.car_num=rowData.car_num+"("+rowData.change_num+")";
						}
					}
				};
				//显示部门选择列表
				function showDepartList(){
					var unitId = "<sec:authentication property="principal.unit.id" />";
					var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
					$(this).data('unitId',unitId);
					$(this).data('unitName',unitName);
					showOrgList.call(this);
				}
				//行选择改变事件
				function selectionChange() {
					var count = Qm.getSelectedCount();//行选择个数
					var state = Qm.getValuesByName("status").toString();
					if (count == 0) {
						Qm.setTbar({
							add : true,
							modify : false,
							del : false,
							detail : false
						});
					} 
					else if (count == 1) {
						if(state[0]== "1"){
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : true
							});
						}else{
							Qm.setTbar({
								add : true,
								modify : true,
								del : true,
								detail : true
							});
						}
					}
					else {
						var delStatus = true;	
						if(state.indexOf("1")!=-1){
							alert(state.indexOf("1")!=-1);
							delStatus=false;
						}
							Qm.setTbar({
								add : true,
								modify : false,
								del : delStatus,
								detail : false
							});
					}
				}

				function modify(item) {
					var selectedId = new Array();
					var status = "modify";
					var title = "修改";
					var width = 700;
					var height = 428;
					if (item.id == "modify") {//点击修改按钮调用的本方法
						selectedId = Qm.getValuesByName("id");
					} else {//双击数据调用本方法
						selectedId[0] = item.id;
						status = "detail";
						title = "详情"
						height = 330
					}
					if (selectedId.length > 1) {
						$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
						return;
					} else if (selectedId.length < 1) {
						$.ligerDialog.alert('请先选择要修改的数据！', "提示");
						return;
					}
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : title,
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/carRent_detail.jsp?status=' + status
							+ '&id=' + selectedId
					});
				}
				function add(item) {
					var width = 700;
					var height = 428;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "新增",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/carRent_detail.jsp?status=add'
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
								url:"oa/car/rent/delete.do?ids="+selectedId,
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

				function detail(item) {
					var selectedId = Qm.getValuesByName("id");
					var width = 700;
					var height = 330;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "详情",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/carRent_detail.jsp?status=detail'
							+ '&id=' + selectedId
					});
				}
			
				function backRentCar(){
					var width = 400;
					var height = 200;
					var windows = top.$.dialog({
						width : width, height : height, lock : true, title : "归还车辆", data : {
							"window" : window
						}, content : 'url:app/oa/car/backRentCar_detail.jsp?id=' + Qm.getValuesByName("id")
					});
				}
		</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId=user.getUnit().getId();
	String departmentId=user.getDepartment().getId();
%>
<body>
	<input type="hidden" name="editor" id="editor" />
	<input type="hidden" name="editorCode" id="editorCode" />
	<qm:qm pageid="oa_car_rent" script="false" singleSelect="false">
		<qm:param name="agent_id" value="<%=user.getId()%>" compare="=" />
	</qm:qm>
</body>
</html>