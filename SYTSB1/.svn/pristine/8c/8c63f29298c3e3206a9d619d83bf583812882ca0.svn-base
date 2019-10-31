<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>公务用车申请列表查询页（车队派车/收车）</title>
<%@ include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.25,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 100
		},//可以自己定义 layout:column,float,
		sp_fields : [ {
			name : "use_dep_id",
			compare : "=",
			xtype : "combo",
			onBeforeOpen : showDepartList
		}, {
			name : "use_user_name",
			compare : "like"
		}, {
			group : [ {
				name : "apply_date",
				compare : ">=",
				value : ""
			}, {
				label : "到",
				name : "apply_date",
				compare : "<=",
				value : "",
				labelAlign : "center",
				labelWidth : 20
			} ],
			columnWidth : 0.25
		}, {
			name : "plate_number",
			compare : "like"
		}, { 
			name : "status_1",
			compare : "llike",
			treeLeafOnly:false
		}],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}, '-', {
			text : '派车',
			id : 'assign',
			click : assign,
			icon : 'role'
		}, '-', {
			text : '收车',
			id : 'receive',
			click : receive,
			icon : 'dispose'
		}, '-', {
			text : '流转过程',
			id : 'turnHistory',
			icon : 'follow',
			click : turnHistory
		} ],
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
	            // 0：提交申请/用车部门负责人待审核
	            if (rowData.status == '0'){
	            	fontColor="black";
	            }
	            // 1：办公室待审核
	            if (rowData.status == '1'){
	            	fontColor="blue";
	            }
	            // 2：分管院领导待审核
	            if (rowData.status == '2'){
	            	fontColor="purple";
	            }
	            // 3：车队负责人待审核
	            if (rowData.status == '3'){
	            	fontColor="red";
	            } 
	            // 4：派车中
	            if (rowData.status == '4'){
	            	fontColor="maroon";
	            }
	         	// 5：用车中
	            if (rowData.status == '5'){
	            	fontColor="orange";
	            }
	         	// 6：已收车
	            if (rowData.status == '6'){
	            	fontColor="green";
	            }
	         	// 98：已退回
	            if (rowData.status == '98'){
	            	fontColor="grey";
	            }
	         	// 99：已作废
	            if (rowData.status == '99'){
	            	fontColor="pink";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				assign : false,
				receive : false,
				detail : false,
				turnHistory : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				assign : true,
				receive : true,
				detail : true,
				turnHistory : true

			});
		} else {
			Qm.setTbar({
				assign : false,
				receive : false,
				detail : false,
				turnHistory : false
			});
		}
	}

	//显示部门选择列表
	function showDepartList() {
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		$(this).data('unitId', unitId);
		$(this).data('unitName', unitName);
		showOrgList.call(this);
	}

	function detail() {
		var selectedId = Qm.getValueByName("id");
		var width = 1050;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_apply_detail2.jsp?status=detail'
					+ '&id=' + selectedId
		});
	}
	
	// 派车
	function assign(){
		var status = Qm.getValueByName("status");
		if(status!="4"){
			$.ligerDialog.alert('亲，该用车申请已派车，不能重复操作哦！', "提示");
			return;
		}
		
		top.$.dialog({
			width : 1050,
			height : 800,
			lock : true,
			title : "车队派车",
			content : 'url:app/car/car_apply_assign.jsp?status=modify&id='
						+ Qm.getValueByName("id").toString(),
			data : {
				"window" : window
			}
		});	
	}
	
	// 收车
	function receive(){
		var status = Qm.getValueByName("status");
		if(status=="4"){
			$.ligerDialog.alert('亲，该用车申请未派车，暂不能收车哦！', "提示");
			return;
		}else if(status=="6"){
			$.ligerDialog.alert('亲，该用车申请已收车，不能重复操作哦！', "提示");
			return;
		}else{
			top.$.dialog({
				width : 1050,
				height : 800,
				lock : true,
				title : "车队收车",
				content : 'url:app/car/car_apply_receive.jsp?status=modify&id='
							+ Qm.getValueByName("id").toString(),
				data : {
					"window" : window
				}
			});	
		}
	}

	function turnHistory() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流转过程",
			content : 'url:car/apply/getFlowStep.do?id='
					+ Qm.getValueByName("id").toString(),
			data : {
				"window" : window
			}
		});
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<!-- 提示文字 -->
    <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：
			    <font color="black">“黑色”</font>代表提交申请/用车部门负责人待审核；
				<font color="purple">“紫色”</font>代表分管院领导待审核；
				<font color="red">“红色”</font>代表车队负责人待审核；
				<font color="maroon">“褐红色”</font>代表派车中；
				<font color="blue">“蓝色”</font>代表办公室负责人待审核；
				<font color="orange">“橘色”</font>代表用车中；
				<font color="green">“绿色”</font>代表已收车；
				<font color="grey">“灰色”</font>代表已退回；
				<font color="pink">“粉色”</font>代表已作废。
			</div>
		</div>
	</div>
	<qm:qm pageid="car_apply_list" script="false" singleSelect="false">	
		<qm:param name="data_status" value="4" compare="=" logic="or"/>
		<qm:param name="data_status" value="5" compare="=" logic="or"/>
		<qm:param name="data_status" value="6" compare="=" logic="or"/>
	</qm:qm>
</body>
</html>