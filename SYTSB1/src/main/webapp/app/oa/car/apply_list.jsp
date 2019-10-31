<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
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
							var stl="";
							if(rowData.state=="草稿"||rowData.state=="不同意"){
								stl = "style='color:red'";
							}
							if(rowData.state=="处理中"){
								stl = "style='color:blue'";
							}
							if(rowData.state=="未派车"){
								stl = "style='color:#8B008B'";
							}
							if(rowData.state=="已派车"){
								stl = "style='color:#006400'";
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
							add : true,
							modify : false,
							del : false,
							detail : false,
							submit:false,
							handle:false,
							end:false
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
								handle:false,
								end:false
							});
						}else if(state=="处理中"){
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : true,
								submit:false,
								handle:true,
								end:false
							});
						}else if(state=="已派车"){
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : true,
								submit:false,
								handle:false,
								end:true
							});
						}else{
							Qm.setTbar({
								add : true,
								modify : false,
								del : false,
								detail : true,
								submit:false,
								handle:false
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
								handle:false,
								end:false
							});
						}else{
							Qm.setTbar({
								add : true,
								modify : false,
								del : true,
								detail : false,
								submit:false,
								handle:false,
								end:false
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
						var title = "详情"
					}
					if (selectedId.length > 1) {
						$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
						return;
					} else if (selectedId.length < 1) {
						$.ligerDialog.alert('请先选择要修改的数据！', "提示");
						return;
					}
					var width = 700;
					var height = 350;
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
					selectUnitOrUser(1,0,'editorCode','editor',submit);
				}
	
				function submit(){
					var selectedId = Qm.getValuesByName("id");
					var editor=$("#editor").val();
					var editorCode=$("#editorCode").val();
					if(editor==""||editor==undefined||editor==null){
						$.ligerDialog.alert("没有选择处理人!");
						return;
					}
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
					if("<%=user.getId() %>"!=Qm.getValuesByName("editor_code")){
						$.ligerDialog.alert("你不是此条申请的处理人，不能执行此操作！");
			            return;
					}
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
				function end() {
					if("<%=user.getId() %>"!=Qm.getValuesByName("applitor_code")){
						$.ligerDialog.alert("你不是此条申请的申请人，不能执行此操作！");
			            return;
					}
					var width = 400;
					var height = 357;
					var windows = top.$.dialog({
						width : width, height : height, lock : true, title : "归还车辆", data : {
							"window" : window
						}, content : 'url:app/oa/car/backCar_detail.jsp?id=' + Qm.getValuesByName("id")
					});
				}
		</script>
</head>

<body>
	<!-- 提示文字 -->
    <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：
			    <font color="blue">“蓝色”</font>代表处理中。
				<font color="red">“红色”</font>代表草稿或不同意。
				<font color="#8B008B">“紫色”</font>代表未派车。
				<font color="#006400">“绿色”</font>代表已派车。
				<font color="#000000">“黑色”</font>代表申请已归还。
			</div>
		</div>
	</div>
	<!--可以看见自己的和该自己审核的数据  -->
	<input type="hidden" name="editor" id="editor" />
	<input type="hidden" name="editorCode" id="editorCode" />
	<qm:qm pageid="oa_car_app" script="false" singleSelect="false">
		<qm:param name="applitor_code" value="<%=user.getId() %>" compare="=" />
		<qm:param name="editor_code" value="<%=user.getId() %>" compare="=" logic="or"/>
	</qm:qm>
</body>
</html>