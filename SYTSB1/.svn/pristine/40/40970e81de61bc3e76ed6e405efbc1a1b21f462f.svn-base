<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
 <%String sql="select * from TJY2_OFFICE_MESSAGE t where t.send_status='1' order by t.create_date desc"; 
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义 
		sp_fields:[
					{name: "send_name", compare: "like"},
					 {name: "send_number", compare: "like"},
					 {name: "is_temporary_tel", compare: "like"},
				     {name: "send_type", compare: "like"},
				     {name: "send_style", compare: "like"},
				     {group:[
								{name:"send_time", compare:">=", value:""},
								{label:"到", name:"send_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
							]}	
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		],
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
		         var count = Qm.getSelectedCount();
		         Qm.setTbar({
		          	detail: count==1
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
				}
		}
}
		
function detail(){
    top.$.dialog({
        width: 700,
        height: 400,
        lock: true,
        parent: api,
        data: {
      	 window: window
        },
        title: "详情",
        content: 'url:app/office/office_message_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
     });
}
</script>
</head>
<body>
       <qm:qm pageid="TJY2_OFFICE_MESSAGE" singleSelect="true" sql="<%=sql %>">
       </qm:qm>
</body>
</html>