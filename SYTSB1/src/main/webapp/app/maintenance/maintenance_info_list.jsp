<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	String data_status = request.getParameter("data_status");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name : "func_name", compare : "=", value : "" },
			{name : "pro_desc", compare : "like", value : "" },
			{name : "org_id", id : "org_id", compare:"=",isMultiSelect:true},
			{name : "advance_user_name", compare : "like", value : "" },
			{group:[
				{name:"advance_date", id:"advance_date", compare:">="},
				{label:"到", name:"advance_date", id:"advance_date1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"data_status", id:"data_status", compare:"="}
		],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}//, '-', { text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
		<sec:authorize access="hasRole('sys_administrate')">
		<%
			// 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：开发中 3：已完成 4：已发布 99：已删除）
			if(StringUtil.isNotEmpty(data_status)){
				if("0".equals(data_status)){
					%>
					, '-', {
						text : '受理',
						id : 'receive',
						click : receive,
						icon : 'modify'
					}
					<%
				}else if("1".equals(data_status)|| "2".equals(data_status)){
					%>
					/*, '-', {
						text : '开发',
						id : 'develop',
						click : develop,
						icon : 'modify'
					}*/

					, '-', {
						text : '处理',
						id : 'modify',
						click : update,
						icon : 'modify'
					}
					/*, '-', {
						text : '修改',
						id : 'modify',
						click : modify,
						icon : 'modify'
					}, '-', {
						text : '论证',
						id : 'prove',
						click : prove,
						icon : 'modify'
					}, '-', {
						text : '完成',
						id : 'finish2',
						click : finish2,
						icon : 'modify'
					}*/
					<%
				}else if("3".equals(data_status)){
					%>
					, '-', {
						text : '发布',
						id : 'publish',
						click : publish,
						icon : 'modify'
					}
					<%
				}
				
				if(!"99".equals(data_status)){
					%>
					, '-', {
						text : '删除',
						id : 'del',
						click : del,
						icon : 'del'
					}
					<%
				}
			}
		%>
		</sec:authorize>
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if (rowData.status == '2'){
		    		fontColor="blue";
		    	}
		   		if (rowData.status == '99'){
	          		fontColor="red";
	            }
				return "style='color:"+fontColor+";'";
		 		//return "style='color:"+fontColor+";font-weight: bold;'";
			},
	   		pageSizeOptions:[10,20,30,50,100,200]
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				detail : false,
				modify : false,
				receive : false,
				develop : false,
				//prove : false,
				finish : false,
				//finish2 : false,
				publish : false,
				turnHistory : false,
				del : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				detail : true,
				modify : true,
				receive : true,
				develop : true,
				//prove : true,
				finish : true,
				//finish2 : true,
				publish : true,
				turnHistory : true,
				del : true
			});
		} else {
			Qm.setTbar({
				detail : false,
				modify : false,
				receive : true,
				develop : true,
				//prove : false,
				finish : true,
				//finish2 : false,
				publish : true,
				turnHistory : false,
				del : true
			});
		}
	}
	
	//处理
	function update(){
		var status = Qm.getValueByName("status").toString();
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "处理",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_info_detail.jsp?status=modify&id='+ Qm.getValueByName("id")+"&statusData="+status
		});
		
	}
	
	//查看
	function detail() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_info_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	//修改
	function modify() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_detail2.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}
	
	// 受理需求
	function receive(){
		top.$.dialog({
			width : 600,
			height : 200,
			lock : true,
			title : "受理需求",
			content : 'url:app/maintenance/receive_info.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 论证
	function prove(){
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "论证情况",
			content : 'url:app/maintenance/prove_info.jsp?status=modify&id='
						+ Qm.getValueByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 标记开发情况
	function develop(){
		top.$.dialog({
			width : 600,
			height : 200,
			lock : true,
			title : "开发情况",
			content : 'url:app/maintenance/develop_info.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 完成
	function finish(){
		top.$.dialog({
			width : 600,
			height : 250,
			lock : true,
			title : "完成情况",
			content : 'url:app/maintenance/finish_info.jsp?status=modify&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 未标记开发直接标记完成
	function finish2(){
		var status = Qm.getValueByName("status").toString();
		if(status != "2"){
			$.ligerDialog.error("亲，该功能未论证暂不能标记完成！");
			return;
		}else{
			top.$.dialog({
				width : 600,
				height : 500,
				lock : true,
				title : "完成情况",
				content : 'url:app/maintenance/finish_info2.jsp?status=modify&id='
							+ Qm.getValueByName("id").toString(),
				data : {
					"window" : window
				}
			});	
		}
	}
	
	// 发布
	function publish() {
		$.ligerDialog.confirm("亲，确定要发布所选记录吗？发布后的信息无法撤回，并将公开显示于系统首页哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "maintenance/info/publishs.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("发布成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("发布失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	//删除
	function del() {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "maintenance/info/del.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
	}

	// 流转过程
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:maintenance/info/getFlowStep.do?id='+Qm.getValueByName("id"),
	   		data : {"window" : window}
	   	});
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项 
				<font color="blue">“蓝色”</font>代表已论证。
			</div>
		</div>
	</div>
	<qm:qm pageid="maintenance_list2" script="false">
		<%
			if(StringUtil.isNotEmpty(data_status)){
				if("1".equals(data_status)){
					%>
					<!-- 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：已论证 3：已完成 4：已发布  5：已确认 99：已删除） -->
					<qm:param name="data_status" compare="in" value="('1','2')" dataType="user"/>
					<%	
				}else{
					%>
					<qm:param name="data_status" value="<%=data_status%>" compare="=" />
					<%					
				}
			}
		%>
	</qm:qm>
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.func_name.binddata=<u:dict code="MAINTENANCE_FUNCTION"></u:dict>;
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where property='dep' and status='used' order by orders "></u:dict>;
	</script>
</body>
</html>
