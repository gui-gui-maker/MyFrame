<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right'}, //可以自己定义 layout:column,float,labelSeparator,labelWidth
		sp_fields: [ 
			{name: "applier",compare: "like"},
			{name: "receive_org",compare: "="}, 
			{group: [
						{name: "apply_time", compare: ">="},
						{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
					]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '领取', id: 'receive', icon: 'consuming', click: receive
		} ],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.status=='领取') // 记录为绿色
                {
                    return "style='color:green'";
                }else{
                    return "style='color:black'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1
				});
			}
		}
	};
	
	function receive() {
		top.$.dialog({
			width: 400,
			height: 240,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "领取",
			content: 'url:app/fwxm/library/book_receive.jsp?pageStatus=add'
		});
	}
	
	function detail() {
		var id = Qm.getValueByName("apply_id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/fwxm/library/receive_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
</script>
</head>
	<body>
		<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
		        <div class="l-page-title-note">提示：列表数据项
		            <font color="black">“黑色”</font>代表待领取，
		            <font color="green">"绿色"</font>代表已领取。
		        </div>
		    </div>
		</div>
		<qm:qm pageid="lib_book_ready" script="false" singleSelect="false"></qm:qm>
		<script type="text/javascript">
			Qm.config.columnsInfo.receive_org.binddata = <u:dict sql = "select id,org_name text from sys_org where status = 'used' order by orders2"></u:dict>;
		</script>
	</body>
</html>