<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流转中</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"sn",compare:"="},
			{name:"device_registration_code",compare:"like"},
			{name:"com_name",compare:"like"}
	    ],
		tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detail},
             { text:'流转过程', id:'follow',icon:'follow', click: turnHistory},
             { text:'查看报告', id:'showReport',icon:'detail', click: showReport}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({detail:count==1,follow:count==1,showReport:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}
		}
	};

	// 详情
		function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}		
			top.$.dialog({
				width : 700,
				height : 300,
				lock : true,
				title : "业务详情",
				content : 'url:app/flow/info_detail.jsp?status=detail&id='+ id,
				data : {
					"window" : window
				}
			});
		}

		function del(){
	
	
	  $.del("确定作废?",
	    		"department/basic/delReport.do",
	    		{"ids":Qm.getValuesByName("id").toString()});
}
		
		// 流转过程
		function turnHistory(){	
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("id"),
	   			data : {"window" : window}
	   		});
		}
		
		// 查看报告
		function showReport(){	
			var id = Qm.getValueByName("id").toString();
			var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
			var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
			if(is_user_defined==""){
				var w=window.screen.availWidth;
				var h=(window.screen.availHeight);
				var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
				top.$.dialog({
					width : w, 
					height : h, 
					lock : true, 
					title:"报告信息",
					content: 'url:'+url,
					data : {"window" : window}
				}).max();
				//var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
			} else {
				var file_name = Qm.getValueByName("file_name").toString();	// 自定义报告文件名
				//获取自定义报告的文件名
				var urls = "app/report/getInfoFileName.jsp?id="+id+"&file_name="+file_name;
				temp = SendXML(urls);
				if(temp!="false"){
					var url = "/pub/fileupload/down_file.jsp?fileName=" + temp;
					WinOpen(1,url,"",340,600);
				} else {
					alert("操作异常，请重新操作。");
				}
			}
		}
	
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="flowing_list" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
