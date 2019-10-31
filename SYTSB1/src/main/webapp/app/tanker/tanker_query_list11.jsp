<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
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
	String user_account = user.getSysUser().getAccount();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
	String org_code = user.getDepartment().getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		var areas = <u:dict sql="select id,regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
		var check_types = <u:dict code="BASE_CHECK_TYPE"></u:dict>;
		var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;
		var payments = <u:dict code="PAYMENT_STATUS"></u:dict>;
		
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"ladle_car_number", id:"ladle_car_number", compare:"like"},
            	{name:"ladle_car_domain_num", id:"ladle_car_domain_num", compare:"like"},
				{name:"report_sn", id:"report_sn", compare:"like"},
				{name:"report_com_name", id:"report_com_name", compare:"like"},
            	{name:"check_unit_id", id:"check_unit_id", compare:"="},
            	{name:"inspection_conclusion", id:"inspection_conclusion", compare:"="},
            	{name:"check_op_name", id:"check_op_name", compare:"like"},
            	{name:"lrry", id:"lrry", compare:"like"},
            	{name:"make_date", id:"make_date", compare:"like"},
            	{group:[
					{name:"advance_time", id:"advance_time", compare:">="},
					{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{group:[
					{name:"enter_time", id:"enter_time", compare:">="},
					{label:"到", name:"enter_time", id:"enter_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{group:[
					{name:"issue_Date", id:"issue_Date", compare:">="},
					{label:"到", name:"issue_Date", id:"issue_Date1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
            	{group:[
					{name:"print_time", id:"print_time", compare:">="},
					{label:"到", name:"print_time", id:"print_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{name:"fee_status", id:"fee_status", compare:"="}
            ],
            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click: detail},
				'-', 
				{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},
				'-', 
 				{text : '清空', id : 'empty', icon : 'modify', click : empty}                  
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.inspection_info_id);
        		},
        		rowClick:function(rowData){
        			setConditionValues(rowData);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({detail: true, turnHistory: true, showReport: true, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true});
	    	}else{
	    		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false,printTags:false,printEnd:false,payTotal:false,printInfo:false,printTagsE:false});
	    	}
		}
		
		function setConditionValues(rowData){
			//$('#device_registration_code1').val(Qm.getValueByName("device_registration_code").toString());	// 设备注册代码
			
			$("#qm-search-p input").each(function(){
				var name = $(this).attr("id");
				if(!$.kh.isNull(name)){
					$(this).val(rowData[name]);
				}
			})
			$("input[name='device_sort-txt']").ligerComboBox().selectValue(render(rowData["device_sort"],deviceType));	// 设备类别
			$("input[name='area_id-txt']").ligerComboBox().selectValue(render(rowData["area_id"],areas));	// 设备所在地
			$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue(render(rowData["check_unit_id"],check_deps));	// 检验部门
			
			$("input[name='report_id-txt']").ligerComboBox().selectValue(render(rowData["report_id"],reports));	// 报告类型
			$("input[name='fee_status-txt']").ligerComboBox().selectValue(render(rowData["fee_status"],payments));	// 收费状态
		}
		
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			$("input[name='fee_status-txt']").ligerComboBox().selectValue('');	// 收费状态
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
				var ispdf =  Qm.getValuesByName("export_pdf").toString();
				if(ispdf==null||ispdf==""){
					var report_id = Qm.getValueByName("report_id").toString();	// 报告类型
					var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
					top.$.dialog({
						width : w, 
						height : h, 
						lock : true, 
						title:"报告信息",
						content: 'url:'+url,
						data : {"window" : window}
					}).max();
				}else{
					var report_sn = Qm.getValueByName("report_sn").toString();
					var inspect_date = Qm.getValueByName("advance_time").toString();
					var date = inspect_date.substring(0,4)+inspect_date.substring(5,7)+inspect_date.substring(8,10);
					var to_swf = Qm.getValueByName("to_swf").toString();
					if(to_swf==null||to_swf==""){
						$("body").mask(" 第一次查看该报告，正在准备文档，请稍后......");
						 $.post("inspectionInfo/basic/pdf2Swf.do",{"pdfPath":date+"/"+report_sn+"/"+report_sn+".pdf","swfPath":date+"/"+report_sn,"swfName":report_sn},function(res){
			        		   if(res.success){
			        			   $("body").unmask();
			        				//alert(date+"/"+report_sn+"/"+report_sn+".swf")
			        				 top.$.dialog({
			        					width : 800, 
			        					height : 400, 
			        					lock : true, 
			        					title:"查看报告",
			        					content: 'url:app/query/showFile.jsp?status=detail&path='+date+"/"+report_sn+"/"+report_sn+".swf"+'&id='+id,
			        					data : {"window" : window,"report_sn":report_sn,"date":ispdf},
			        					close:function(res){
			        						Qm.refreshGrid();
			        					}
			        				}).max(); 
			        		   }
			        	   }) 
					}else{
						//alert(date+"/"+report_sn+"/"+report_sn+".swf")
						 top.$.dialog({
							width : 800, 
							height : 400, 
							lock : true, 
							title:"查看报告",
							content: 'url:app/query/showFile.jsp?status=detail&path='+date+"/"+report_sn+"/"+report_sn+".swf"+'&id='+id,
							data : {"window" : window,"report_sn":report_sn,"date":ispdf}
						}).max(); 
					}
					
				
				}	
				
				/* var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
				top.$.dialog({
					width : w, 
					height : h, 
					lock : true, 
					title:"报告信息",
					content: 'url:'+url,
					data : {"window" : window}
				}).max(); */
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
			var flow_note_name = Qm.getValuesByName("flow_note_name").toString();	// 当前步骤
			if(flow_note_name.indexOf("录入")!=-1 || flow_note_name.indexOf("审核")!=-1 || flow_note_name.indexOf("签发")!=-1){
				$.ligerDialog.error("所选报告中包含有未签发的报告，不能打印，请重新选择！");
				return;
			}else{
				top.$.dialog({ 	
					width : w, 
					height : h, 
					lock : true, 
					title:"打印报告",
					content: 'url:app/query/report_print_index.jsp?id='+ids+'&printType=1',
					data : {"window" : window}
				}).max();
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
		
		// 打印二维码标签
		function printTagsE(){	
			var device_id = Qm.getValuesByName("id").toString();
			var conclusions = Qm.getValuesByName("inspection_conclusion").toString();
			if(conclusions.indexOf("不合格")!=-1){
				$.ligerDialog.error("所选报告中包含有检验不合格的报告，不能打印标签，请重新选择！");
				return;
			}
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);			
			top.$.dialog({ 
				width : w, 
				height :h, 
				lock : true, 
				title:"打印标签",
				content: 'url:app/query/report_print_index_tags.jsp?flag=yes&id='+ids+'&device_id='+device_id,
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
		
		function printInfo(){
			var report_types = Qm.getValuesByName("report_type").toString();
			var report_type = "";
			if(report_types.indexOf(",")!=-1){
				report_type = report_types.split(",");
				for(var i = 0 ; i < report_type.length ; i++){
					for(var j = 1 ; j < report_type.length ; j++){
						if(report_type[i]!=report_type[j]){
							$.ligerDialog.alert("只能选择同一个模板的报告");
							return;
						}
					}
				}
				report_types = report_type[0];
			} 
	
			$.getJSON('report/query/queryModle.do?report_type='+report_types,function(data){
				if(data){
					var ids = Qm.getValuesByName("inspection_info_id").toString();
					var w=window.screen.availWidth;
					var h=(window.screen.availHeight);			
					top.$.dialog({
						width : w, 
						height :h, 
						lock : true, 
						title:"打印信息表",
						content: 'url:app/query/report_print_index_message.jsp?report_type='+report_types+'&ids='+ids,
						data : {"window" : window}
					}).max();
				}else{
					$.ligerDialog.alert("没有找到信息表模板！");
				}
			});
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
		
		function render(value,data){
		    for (var i in data) {
		    	if (data[i]["text"] == value)
		        {
		    		
		        	return data[i]['id'];
		        }
				if(data[i].children)
				{
					for(var j in data[i].children)
					{
						if(data[i].children[j]["text"] ==value)
							return data[i].children[j]['id'];
						if(data[i].children[j].children)
						{
							for(var k in data[i].children[j].children)
								if(data[i].children[j].children[k]["text"]==value)
								{
									return data[i].children[j].children[k]["id"];
								}
						}
					}
				}
		    }
		    return value;
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
		<qm:qm pageid="tanker_query_list" singleSelect="false">
			
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
Qm.config.columnsInfo.device_sort.binddata=<u:dict sql="select t.value code,t.name text from pub_code_table_values t where t.code_table_id='402883a04b35cf38014b38c2da07245a' and t.value like '%00' and t.code_table_values_id is not null order by t.sort"></u:dict>;
<%
	if(StringUtil.isNotEmpty(isDt)){
		%>
		Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports where report_name like '%梯%' "></u:dict>;
		<%
	}else{
		%>
		Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports"></u:dict>;
		<%
	}
%>

Qm.config.columnsInfo.inspection_conclusion.binddata=[
                                                      
{id: '允许使用', text: '允许使用'},
{id: '停止使用', text: '停止使用'}                     
                                                      
                                                      ];
Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
Qm.config.columnsInfo.fee_status.binddata=<u:dict code="PAYMENT_STATUS"></u:dict>;
Qm.config.columnsInfo.flow_note_name.binddata = [
		{id: '报告录入', text: '起草'},
		{id: '报告签发', text: '报告审核'},
		{id: '打印报告', text: '封存未打印'},
		{id: '报告领取', text: '封存已打印'},
		{id: '报告归档', text: '报告已领取'},
		{id: '报告归档', text: '归档'}

];
</script>
	</body>
</html>
