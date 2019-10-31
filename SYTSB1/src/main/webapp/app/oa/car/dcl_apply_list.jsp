<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:60},//可以自己定义 layout:column,float,
				sp_fields : [{
						name : "apply_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
					}, {
						name : "car_num", compare : "like"
					}, {
						name : "state", compare : "="
					},{
						name : "destination", compare : "like"
					},
					{group:[{name:"start_time",compare:">=",value:""},
					           {label:"到",name:"start_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					           ],columnWidth:0.33
					},{group:[
					          {name:"end_time",compare:">=",value:""},
					          {label:"到",name:"end_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					          ],columnWidth:0.33
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
				//行选择改变事件
				function selectionChange() {
					var count = Qm.getSelectedCount();//行选择个数
					var state = Qm.getValuesByName("state");
					if (count == 0) {
						Qm.setTbar({
							add : true,
							modify : false,
							del : false,
							detail : false,
							submit:false,
							handle:false
				
						});
					} 
					else if (count == 1) {
						if(state=="草稿"){
							Qm.setTbar({
								add : true,
								modify : true,
								del : true,
								detail : true,
								submit:true,
								handle:true
							});
						}else{
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : true,
								submit:false,
								handle:true
							});
						}
				
					}
					else {
						state=state.toString();
						if(state.indexOf("处理中")!=-1||state.indexOf("未派车")!=-1||state.indexOf("已派车")!=-1||state.indexOf("已归还")!=-1){
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : false,
								submit:false,
								handle:true
					
							});
						}else{
							Qm.setTbar({
								add : true,
								modify : false,
								del : true,
								detail : false,
								submit:false,
								handle:true
					
							});
						}
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
					var title = "修改"
					if (item.id == "modify") {//点击修改按钮调用的本方法
						selectedId = Qm.getValuesByName("id");
					} else {//双击数据调用本方法
						selectedId[0] = item.id;
						status = "detail";
						title = "详情";
					}
					if (selectedId.length > 1) {
						$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
						return;
					} else if (selectedId.length < 1) {
						$.ligerDialog.alert('请先选择要修改的数据！', "提示");
						return;
					}
					var width = 700;
					var height = 332;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : title,
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/apply_detail.jsp?status=' + status
							+ '&id=' + selectedId
					});
				}
				function add(item) {
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
						content : 'url:app/oa/car/apply_detail.jsp?status=add'
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

				function detail(item) {
					var selectedId = Qm.getValuesByName("id");
					var width = 700;
					var height = 332;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "详情",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/apply_detail.jsp?status=detail'
							+ '&id=' + selectedId
					});
				}
				function submitSelect(){
					selectUnitOrUser(1,0,'editorCode','editor','',submit);
				}
	
				function submit(){
					var selectedId = Qm.getValuesByName("id");
					var editor=$("#editor").val();
					var editorCode=$("#editorCode").val();
					$.ajax({
						url:"oa/car/apply/submit.do?ids="+selectedId,
						async:false,
						type:"post",
						dataType:"json",
						data:{editor:editor,editorCode:editorCode},
						success:function(data) {
							if(data.success){
								top.$.dialog.notice({content:'数据提交成功！'});
								Qm.refreshGrid();
							}else{
								$.ligerDialog.error('提交失败');
							}
						}
					});
				}
				function handle(){
					var width = 500;
					var height = 214;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "处理",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/handle_detail.jsp?id='+Qm.getValuesByName("id")
					});
				}
		</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();	
%>
<body>
	<input type="hidden" name="editor" id="editor" />
	<input type="hidden" name="editorCode" id="editorCode" />
	<qm:qm pageid="oa_car_app" script="false" singleSelect="true">
		<qm:param name="editor_code" value="<%=user.getId()%>" compare="=" />
		<qm:param name="state" value="处理中" compare="=" />
	</qm:qm>
</body>
</html>