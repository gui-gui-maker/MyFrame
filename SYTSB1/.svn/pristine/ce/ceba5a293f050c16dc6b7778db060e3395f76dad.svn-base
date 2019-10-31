<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>供货商管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
		<script language="javascript" src="app/flow/report/report.js"></script>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"accept_no", compare:"="},
            	{name:"flow_note_name", compare:"like"},
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail},
            	{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},
            	{ text:'查看报告', id:'showReport',icon:'detail', click: showReport}
            	
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.inspection_info_id);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({detail: true, turnHistory: true, showReport: true, print: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false});
	    	}else{
	    		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false});
	    	}
		}
	
		// 详情
		function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("inspection_info_id").toString();
			}		
			top.$.dialog({
				width : 800,
				height : 500,
				lock : true,
				title : "业务详情",
				content : 'url:app/flow/info_detail.jsp?status=detail&id='+ id,
				data : {
					"window" : window
				}
			});
		}
		
		// 流转过程
		function turnHistory(){	
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("inspection_info_id"),
	   			data : {"window" : window}
	   		});
		}
		
		// 查看报告
		function showReport(){	
			var id = Qm.getValueByName("inspection_info_id").toString();
			var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
			var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
			if(is_user_defined==""){
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
				var urls = "app/query/getInfoFileName.jsp?id="+id+"&file_name="+file_name;
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
        
        function closewindow(){
			api.close();
		}
    </script>
	</head>
	<body>	
		<qm:qm pageid="infoStep_list" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
<%--Qm.config.columnsInfo.str3.binddata=<u:dict sql="select t.id,t.name from pub_code_table_values t where t.code_table_id='4028807036572ae1013657b7a4a0000b'"></u:dict>;--%>
Qm.config.columnsInfo.report_id.binddata=<u:dict sql='select id,report_name from base_reports'></u:dict>;
Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
<%--Qm.config.columnsInfo.num1.binddata = [
	{id: '00000000', text: '优抚类别', children: [
		{id: '402880b33d158ac2013d15952f080008', text: '烈士遗属'},
		{id: '402880b33d158ac2013d159463ac0006', text: '病故军人遗属'},
		{id: '402880b33d158ac2013d159632b60009', text: '60周岁以上农村籍退役人员'},
		{id: '402880b33d158ac2013d1594309a0005', text: '在乡复原军人'},
		{id: '402880b33d158ac2013d15967c4e000a', text: '60周岁以上错杀被平反人员子女'},
		{id: '402880b33d158ac2013d159495200007', text: '因公牺牲军人遗属'},
		{id: '402880b33d158ac2013d15973c98000b', text: '60周岁以上烈士子女'},
		{id: '402880b33d158ac2013d15977d8b000c', text: '铀矿开采军队退役人员'},
		{id: '402880b33d158ac2013d1597bb33000d', text: '其他参加核试验退役人员'},
		{id: '402880b33d158ac2013d15981914000e', text: '原8023部队退役人员'},
		{id: '402880b33d158ac2013d159875bb000f', text: '红军失散人员'},
		{id: '402880b33d158ac2013d1598b1a10010', text: '在乡西路军红军老战士'},
		{id: '402880b33d158ac2013d159909b60011', text: '在乡退伍红军老战士'},
		{id: '402880b33d158ac2013d1599540c0012', text: '伤残民兵民工'},
		{id: '402880b33d158ac2013d1599aed60013', text: '伤残人民警察'},
		{id: '402880b33d1a79c4013d1a7e9ae10000', text: '伤残国家机关工作人员'},
		{id: '5', text: '残疾军人'},
		{id: '6', text: '参战涉核人员'},
		{id: '7', text: '参战退役人员'},
		{id: '8', text: '带病回乡退伍军人'}
	]}
];--%>
</script>
	</body>
</html>
