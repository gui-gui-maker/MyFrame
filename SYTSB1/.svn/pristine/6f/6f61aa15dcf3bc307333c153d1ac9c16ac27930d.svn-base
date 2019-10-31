<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
				 {name:"account",compare: "like"},
		         {name:"action",compare: "="},
		  		 {group:[
					{name:"op_time", compare:">=", value:"", width:"100"},
					{label:"到", name:"op_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20, width:"100"}
				 ]}
			],
			tbar: [{text: '详情', id: 'detail', icon: 'detail', click: detail}],
			listeners: {
						rowClick: function(rowData, rowIndex) {},
						rowDblClick: function(rowData, rowIndex){detail();},
						selectionChange: function(rowData, rowIndex) {
							var count = Qm.getSelectedCount();
							Qm.setTbar({
								detail: count==1
							});
						},
	        			rowAttrRender : function(rowData, rowid) {
				                var fontColor="black";
				                return "style='color:"+fontColor+"'";
            			}
		}
	};
	function detail() {
		var row = Qm.getQmgrid().getSelected();
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window,
				row:row
			},
			title: "详情",
			content: 'url:app/approve/record/action_record_detail.jsp?id='+Qm.getValueByName("id")+'&pageStatus=detail'
		});
	}
</script>
</head>
<body>
	<!--<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表单据未提交，
                <font color="orange">“橙色”</font>代表未审核，
                <font color="blue">“蓝色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过，
                <font color="indigo">“紫色”</font>代表报告已领取，<br />
                <font color="gray">“灰色”</font>代表报告部分归还/已归还。
			</div>
		</div>
	</div>-->
	<qm:qm pageid="sys_record_list"></qm:qm>
</body>
</html>