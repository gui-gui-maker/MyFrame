<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
var a="${param.report_number_id}";
var id=null;
//$(function() {alert(a);}); 
	var qmUserConfig = {
        tbar:[
	        { text:'查看档案', id:'select',icon:'detail',click:detail}/*,
	        "-",
	        { text:'关闭', id:'close',icon:'modify',click:close}*/
        ],
        listeners: {
        	rowDblClick: function(rowData, rowIndex) {
				detail();
			},
            selectionChange : function(rowData,rowIndex){
            	var count=Qm.getSelectedCount();//行选择个数
                Qm.setTbar({});
            }
        }
    };
	function sety() {
		str=a.replace(/[,]/g,"','");
		id="('"+str+"')";
		//alert(id);
	}
	function detail() {
		var ids = Qm.getValueByName("id");
//		alert(sn);
		if(!ids){
          $.ligerDialog.warn("请先选择数据！");
          return;
      }
		top.$.dialog({
			width: "90%",
			height:"90%",
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "查询档案",
			content: 'url:app/archives/archives_information.jsp?pageStatus=detail&bh='
					+Qm.getValueByName("report_sn")+"&fileId="+Qm.getValueByName("id")

		});
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_report58" singleSelect="true">
		<qm:param name="id" value="${param.report_number_id}" logic="and" compare="in" dataType="user" />
	</qm:qm>
</body>
</html>