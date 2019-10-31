<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
			{name: "category",compare: "="},
			{name: "name",compare: "like"}, 
			{name: "qrcode",compare: "like"} 
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.列名1=='XXX') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.列名2=='YYY') // 记录为红色
                {
                    return "style='color:red'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0
				});
			}
		}
	};
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/fwxm/library/book_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function getBooks(){
        if(Qm.getSelectedCount()==0){
            return null;
        }else{
            return Qm.getQmgrid().getSelecteds();
        }
    }
</script>
</head>
	<body>
		<qm:qm pageid="lib_book" script="false" singleSelect="false">
	    	 <qm:param name="identifier" compare="=" value="1"/>
	    	 <qm:param name="status" value="5" compare="<>" />
	    	 <qm:param name="fk_receive_id" value="null" compare="is" dataType="user"/>
	    </qm:qm>
	</body>
</html>