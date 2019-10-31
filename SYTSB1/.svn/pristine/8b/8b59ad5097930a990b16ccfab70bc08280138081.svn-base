<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
	//$(function() {xh(); }); 
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"report_sn",compare: "like"},
	         {name:"enter_op_name",compare: "like"},
	         {group: [
	  				{name: "op_time", compare: ">="},
	  				{label: "到", name: "op_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '恢复', id: 'add', icon: 'add', click: add
		}],
	   
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					add: count==1
						
				});
			}
		}
	};
	function xh(){//
		
		top.$.ajax({
            url: "archives/yijina/getbg.do",
            type: "GET",
            dataType:'json',
            async: false,
            success:function (data) {
            	reportNumberId="('"+data.mss+"')";
            	alert(reportNumberId);
           	 //if(data.list){
                 //$.ligerDialog.warn(data.msg);
                //}else{
                	//document.getElementById("div").style.display="none";
                    //$.ligerDialog.warn(data.msg);
           	 //}
            }
		});
	}
	function add() {
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('是否要恢复档案？', function (yes){
	       	 if(!yes){return false;}
			top.$.ajax({
	            url: "archives/destroy/archivesHf.do?id="+id,
	            type: "GET",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	            	if(data.success){
	            		top.$.notice('恢复成功！',3);
                		//api.close();
                		Qm.refreshGrid();//刷新
                     }else{
                         $.ligerDialog.warn(data.msg);
                     }
	            }
			});
		});
	}
	function detail(){
		id = Qm.getValueByName("id");
		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			parent: api,
			title : "业务详情",
			content : 'url:app/flow/info_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "archives/destroy/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ARCHIVES_YXH" singleSelect="true"></qm:qm>
</body>
</html>