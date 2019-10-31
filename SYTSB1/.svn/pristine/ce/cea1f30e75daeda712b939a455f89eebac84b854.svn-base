<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>公务用车申请列表查询页（待审核页面）</title>
<%@ include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
	String userId = cur_user.getId();
	String userName = cur_user.getName();
	String orgId = cur_user.getDepartment().getId();
	String orgId1 = "";
	Map<String, String> roles = cur_user.getRoles();
	//String role_name = "";
	String car_apply_check_status = "";
	
	for(String roleid : roles.keySet()){
		Object obj = roles.get(roleid);
		/*
		if(StringUtil.isNotEmpty(role_name)){
			role_name+= ",";
		}
		role_name+= obj;
		*/
		if("部门负责人".equals(obj)){
			car_apply_check_status = "0";
		}
		if("公务用车申请-办公室负责人审核".equals(obj)){
			car_apply_check_status = "1";
			break;
		}
		if("公务用车申请-分管院领导审核".equals(obj)){
			car_apply_check_status = "2";
			break;
		}
		if("公务用车申请-车队负责人审核".equals(obj)){
			car_apply_check_status = "3";
			break;
		}if("系统管理员".equals(obj)){
			car_apply_check_status = "-1";
			break;
		}
	}
%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var car_apply_check_status = "<%=car_apply_check_status %>";
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.33,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 60
		},//可以自己定义 layout:column,float,
		sp_fields : [
				{
					name : "use_user_name",
					compare : "like"
				},
				{
					group : [
							{
								name : "apply_date",
								compare : ">=",
								value : ""
							},
							{
								label : "到",
								name : "apply_date",
								compare : "<=",value:"",labelAlign:"center",labelWidth:20}
						],columnWidth:0.33
					}],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}, '-', {
			text : '审核',
			id : 'check',
			click : check,
			icon : 'dispose'
		}, '-',  {
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
				check : false,
				detail : false,
				turnHistory : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				check : true,
				detail : true,
				turnHistory : true

			});
		} else {
			Qm.setTbar({
				check : false,
				detail : false,
				turnHistory : false
			});
		}
	}

	// 审核
	function check(){
		var status = Qm.getValueByName("status");
		var title = "";
		var height = 400;
		if("0" == car_apply_check_status){
			if("0" != status){
				if("99" == status){
					$.ligerDialog.alert('亲，该用车申请已作废哦！', "提示");
					return;
				}else if("98" == status){
					$.ligerDialog.alert('亲，该用车申请已退回哦！', "提示");
					return;
				}else{
					$.ligerDialog.alert('亲，该用车申请已审核，不能重复操作哦！', "提示");
					return;
				}
			}
			title = "部门负责人审核";
		}else if("1" == car_apply_check_status){
			if("1" != status && "0" != status){
				$.ligerDialog.alert('亲，该用车申请已审核，不能重复操作哦！', "提示");
				return;
			}else{
				if("0" == status){
					title = "部门负责人审核";
				}else if("1" == status){
					title = "办公室负责人审核";
				}
			}
			height = 800;
		}else if("2" == car_apply_check_status){
			if("2" != status){
				$.ligerDialog.alert('亲，该用车申请已审核，不能重复操作哦！', "提示");
				return;
			}
			title = "分管院领导审核";
			height = 480;
		}else if("3" == car_apply_check_status){
			if("3" != status){
				$.ligerDialog.alert('亲，该用车申请已审核，不能重复操作哦！', "提示");
				return;
			}
			title = "车队负责人审核";
			height = 620;
		}/*else if("-1" == car_apply_check_status){
			if("3" != status){
				$.ligerDialog.alert('亲，您没有审核权限，不能操作哦！', "提示");
				return;
			}
		}*/
		top.$.dialog({
			width : 1050,
			height : height,
			lock : true,
			title : title,
			content : 'url:app/car/car_apply_check.jsp?status=modify&id='
						+ Qm.getValueByName("id").toString() + '&data_status='
						+ status,
			data : {
				"window" : window
			}
		});	
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
			content : 'url:app/car/car_apply_detail2.jsp?status=detail' + '&id='
					+ selectedId
		});
	}
	
	function turnHistory(){
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流转过程",
	   		content: 'url:car/apply/getFlowStep.do?id='+Qm.getValueByName("id").toString(),
	   		data : {"window" : window}
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
		<%
			if(StringUtil.isNotEmpty(car_apply_check_status)){
				if("0".equals(car_apply_check_status)){
					if("100029".equals(orgId)){
						if("孙宇艺".equals(userName)){
							orgId = "100025";	// 财务管理部
							orgId1 = "100059";  // 工会
						}
						if("韩绍义".equals(userName)){
							orgId = "100030";	// 科研技术管理部
						}
						if("李山桥".equals(userName)){
							orgId = "100044";	// 司法鉴定中心
						}
					}
					%>
					<qm:param name="apply_dep_id" value="<%=orgId%>" compare="=" logic="or"/>
					<%if(StringUtil.isNotEmpty(orgId1)){
						%>
						<qm:param name="apply_dep_id" value="<%=orgId1%>" compare="=" logic="or"/>
						<%
					} %>
					<qm:param name="dep_deal_id" value="<%=userId%>" compare="=" logic="or"/>
					<%
				}else if("1".equals(car_apply_check_status)){
					%>
					<qm:param name="apply_dep_id" value="<%=orgId%>" compare="=" logic="or"/>
					<qm:param name="dep_deal_id" value="<%=userId%>" compare="=" logic="or"/>
					<qm:param name="data_status" value="1" compare="=" logic="or"/>
					<qm:param name="office_deal_id" value="<%=userId%>" compare="=" logic="or"/>
					<%
				}else if("2".equals(car_apply_check_status)){
					%>
					<qm:param name="data_status" value="2" compare="=" logic="or"/>
					<qm:param name="leader_deal_id" value="<%=userId%>" compare="=" logic="or"/>
					<%
				}else if("3".equals(car_apply_check_status)){
					%>
					<qm:param name="data_status" value="3" compare="=" logic="or"/>
					<qm:param name="fleet_deal_id" value="<%=userId%>" compare="=" logic="or"/>
					<%
				}		
			}else{
				%>
				<qm:param name="data_status" value="-1" compare="=" />
				<%	
			}
		%>
	</qm:qm>
</body>
</html>