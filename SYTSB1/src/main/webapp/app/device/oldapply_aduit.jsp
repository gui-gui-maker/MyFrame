<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%
	CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String user=sessionUser.getSysUser().getId();
String userBm= sessionUser.getDepartment().getId();
   
    
%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float
		
		sp_fields: [ 
			{name: "applicants",id:"applicants",compare: "like" }
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		},  "-", {
			text: '审核', id: 'submit', icon: 'submit', click: submit
		}],
		
		listeners: {
			
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.flowStep=='1') // 记录为绿色
                {
                    return "style='color: black'";
                }else if(rowData.flowStep=='2') // 记录为红色
                {
                    return "style='color: blue'";
                }else if(rowData.flowStep=='3') // 记录为红色
                {
                    return "style='color: green'";
                }else if(rowData.flowStep=='4') // 记录为红色
                {
                    return "style='color:red'";
                }  
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					submit:count>0
				});
			}
		}
	};
	

	

	function detail(){
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title:"详情",
			content: 'url:app/device/oldreport_apply_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
					
			data : {"window" : window}
		});
	}
	
	
	function submit(){
		var flow_steps = Qm.getValuesByName("flowStep");
		for (var i = 0; i < flow_steps.length; i++) {
			if(flow_steps[i]=="3" || flow_steps[i]=="4"){
				$.ligerDialog.warn('提示：计划已审核完毕！');
				return;
			}
		}
			var flow_step = Qm.getValueByName("flowStep");
			var nextStep = (flow_step-0)+1;
			top.$.dialog({
				width : 400,
				height : 300,
				lock : true,
				title : "提交审核",
				content : 'url:app/device/oldapply_step.jsp?status=add&id='
						+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep="+nextStep,
				data : {
					"window" : window
				},
				close:function(){
					submitAction();
				}
			});
	}
	function submitAction() {
		Qm.refreshGrid();
	}
       
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表未审核；
				<font color="blue">“蓝色”</font>代表部门负责人已通过；
				<font color="green">“绿色”</font>代表质量监督管理部通过；
				<font color="red">“红色”</font>代表不通过。
			</div>
		</div>
	</div> 
	<qm:qm pageid="report_apply" script="false" singleSelect="false">
		<qm:param name="data_status" value="99" compare="<>" />
			<qm:param name="flow_step" value="('1','2','3','4')" compare="in" dataType="user"/>
			<%if(!userBm.equals("100026")) {%>
	<qm:param name="handle_op_id" value="<%=user %>" compare="=" />
	
	<%} %>
			
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>