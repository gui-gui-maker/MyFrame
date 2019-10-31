<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
		<script language="javascript" src="app/flow/report/report.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
	String org_code = user.getDepartment().getOrgCode();
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
				{name:"report_sn", compare:"like"},
				{name:"report_com_name", compare:"like"},
				{name:"report_id", compare:"=", value: "", treeLeafOnly: false},
            	{name:"check_unit_id", compare:"="},
            	{name:"inspection_conclusion", compare:"like"},
            	{name:"check_op_name", compare:"like"},
            	{name:"lrry", compare:"like"},
            	{group:[
					{name:"advance_time", id:"advance_time1", compare:">="},
					{label:"到", name:"advance_time", id:"advance_time2", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
            	{group:[
					{name:"print_time", id:"print_time1", compare:">="},
					{label:"到", name:"print_time", id:"print_time2", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{name:"fee_status", compare:"="}
            ],
            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click: detail},
				'-', 
				{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},
				<sec:authorize access="hasRole('report_print')">
 				'-', 
				{ text:'打印报告', id:'print',icon:'print', click: doPrintReport},
				'-', 
				{ text:'打印标签', id:'printTags',icon:'print', click: doPrintTags},
 				</sec:authorize>
 				'-', 
				{ text:'打印资料归档目录', id:'printEnd',icon:'print', click: doPrintEnd},
 				"-",
 				{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},
 				'-', 
 				{text : '应收金额合计', id : 'payTotal', icon : 'help', click : payTotal}            
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
	            Qm.setTbar({detail: true, turnHistory: true, showReport: true, print: true,printTags:true,printEnd:true,payTotal:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: true,printTags:true,printEnd:true,payTotal:true});
	    	}else{
	    		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false,printTags:false,printEnd:false,payTotal:false});
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
					$.ligerDialog.alert("操作异常，请重新操作。");
				}
			}
		}

		// 打印报告
		function doPrintReport(){	
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
			var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
			if(is_user_defined==""){	
				top.$.dialog({ 	
					width : w, 
					height : h, 
					lock : true, 
					title:"打印报告",
					content: 'url:app/query/report_print_index.jsp?id='+ids+'&printType=1',
					data : {"window" : window}
				}).max();
				//url = "/cfdinfo?CONTROLLER=com.khnt.command.BlankSecuredCmd&NEXT_PAGE=TSJY_REPORT_PRINT_INDEX&id="+ids;
				//window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
			} else {
				$.ligerDialog.alert("自定义报告请通过“查看报告”按钮下载报告文档进行打印");
			}
		}
        
        // 打印标签
		function doPrintTags(){	
			var id = Qm.getValuesByName("inspection_info_id").toString();
			var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
			if(conclusions.indexOf("不合格")!=-1){
				$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
				return;
			}
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);			
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"打印标签",
				content: 'url:app/query/report_print_index_tags.jsp?id='+id,
				data : {"window" : window}
			}).max();
		}
		
		// 打印检验资料归档目录
		function doPrintEnd(){	
			var report_sn = Qm.getValuesByName("report_sn").toString();		
			top.$.dialog({
				width : $(top).width(),
				height :  $(top).height()-40,
				lock : true,
				title : "打印检验申请单",
				parent: api,
				content : 'url:app/query/docEditor.jsp?status=modify',	
				data: {pwindow: window, bean: report_sn}
			}).max();
		}
		
		// 预收金额合计
		function payTotal(){
			var amount = Qm.getValuesByName("advance_fees").toString();
			doTotal(amount,"预收金额");
		}
		
		function doTotal(amount,title){
			var str = new Array();
			str = amount.split(",");
			var total = 0;
			if (str != null && str.length > 0) {
				for ( var i = 0; i < str.length; i++) {
					if(str[i]==''||str[i]==null){
							str[i]=0;
					}
					total = total + parseFloat(str[i]);
				}
				$.ligerDialog.alert(title+'合计：' + total + "元。");
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
		<qm:qm pageid="report_query_all" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
Qm.config.columnsInfo.report_id.binddata=<u:dict sql='select id,report_name from base_reports'></u:dict>;
Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
Qm.config.columnsInfo.fee_status.binddata=<u:dict code="PAYMENT_STATUS"></u:dict>;
</script>
	</body>
</html>
