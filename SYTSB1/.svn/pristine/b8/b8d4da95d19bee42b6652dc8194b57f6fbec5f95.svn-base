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
	String org_code = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		var areas = <u:dict sql="select id,regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
		var check_types = <u:dict sql="select t.value id, t.name text from PUB_CODE_TABLE_VALUES t, pub_code_table t1 where t.code_table_id = t1.id and t1.code = 'BASE_CHECK_TYPE'"></u:dict>;
		var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;
		var payments = <u:dict code="PAYMENT_STATUS"></u:dict>;
		
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"report_com_name", id:"report_com_name", compare:"like"},
            	{name:"report_sn", id:"report_sn", compare:"like"},
            	{name:"big_class", id:"big_class", compare:"="},
            	{name:"check_type", id:"check_type", compare:"="},
            	{name:"make_units_name", id:"make_units_name", compare:"like"},
            	{name:"maintain_unit_name", id:"maintain_unit_name", compare:"like"},
            	{name:"check_unit_id", id:"check_unit_id", compare:"="},
            	{name:"inspection_conclusion", id:"inspection_conclusion", compare:"like"},
            	{group:[
					{name:"advance_time", id:"advance_time", compare:">="},
					{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{name:"fee_status", id:"fee_status", compare:"="},
				{name:"data_status", id:"data_status", compare:"="}
            ],
            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click: detail},
				'-', 
 				{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}           
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
        		},
    			rowAttrRender : function(rowData, rowid) {
   				 	var fontColor="black";
   		         	// （is_mobile 1：移动检验）
   		            if (rowData.is_mobile == '1'){
   		            	fontColor="blue";
   		            }
   		         	if (rowData.data_status == '99'){
		            	fontColor="red";
		            }
   		         	if (rowData.error_id == '1'){
		            	fontColor="purple";
		            }
   		            return "style='color:"+fontColor+"'";
   				},
   	            pageSizeOptions:[10,20,30,50,100,200]
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({detail: true, turnHistory: true, showReport: true, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true, download:true, export_pdf:true, export_pdf2:true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: true,printTags:true,printEnd:true,payTotal:true,printInfo:true,printTagsE:true, download:false, export_pdf:true, export_pdf2:true});
	    	}else{
	    		Qm.setTbar({detail: false, turnHistory: false, showReport: false, print: false,printTags:false,printEnd:false,payTotal:false,printInfo:false,printTagsE:false, download:false, export_pdf:false, export_pdf2:false});
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
			$("input[name='big_class-txt']").ligerComboBox().selectValue(render(rowData["big_class"],deviceType));	// 设备类别
			$("input[name='area_id-txt']").ligerComboBox().selectValue(render(rowData["area_id"],areas));	// 设备所在地
			$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue(render(rowData["check_unit_id"],check_deps));	// 检验部门
			
			//$("input[name='report_id-txt']").ligerComboBox().selectValue(render(rowData["report_id"],reports));	// 报告类型
			$("input[name='fee_status-txt']").ligerComboBox().selectValue(render(rowData["fee_status"],payments));	// 收费状态
		}
		
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			$("input[name='fee_status-txt']").ligerComboBox().selectValue('');	// 收费状态
			$("input[name='big_class-txt']").ligerComboBox().selectValue('');	// 设备类别
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue('');	// 检验部门
			$("input[name='area_id-txt']").ligerComboBox().selectValue('');	// 设备所在地
			$("input[name='data_status-txt']").ligerComboBox().selectValue('');	// 报告状态
			$("input[name='is_mobile-txt']").ligerComboBox().selectValue('');	// 出具方式
			$("input[name='error_id-txt']").ligerComboBox().selectValue('');	// 是否纠错报告
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
				top.$.dialog({
					width : 800, 
					height : 400, 
					lock : true, 
					title:"查看报告",
					content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&id='+id,
					data : {"window" : window,"report_sn":report_sn,"date":ispdf}
				}).max();
			}				
		}
		
		function export_pdf(){
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"报告盖章",
				content: 'url:app/flow/export/report_export_index.jsp?id='+ids+'&re_export=1&printType=0',
				data : {"window" : window}
			}).max();
		}
		
		// 报告导出
		function export_pdf2(){
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"报告导出",
				content: 'url:app/query/report_export_index.jsp?id='+ids,
				data : {"window" : window}
			}).max();
		}

		// 打印报告
		function doPrintReport(){	
			var ids = Qm.getValuesByName("inspection_info_id").toString();
			var flow_note_name = Qm.getValuesByName("flow_note_name").toString();	// 当前步骤
			var ispdf =  Qm.getValuesByName("export_pdf").toString();
			if(flow_note_name.indexOf("录入")!=-1 || flow_note_name.indexOf("审核")!=-1 || flow_note_name.indexOf("签发")!=-1){
				$.ligerDialog.error("所选报告中包含有未签发的报告，不能打印，请重新选择！");
				return;
			}else{
				var pdf = "";
				if(ispdf!=null){
					var pdflist = ispdf.split(",");
					for(var i=0;i<pdflist.length;i++){
						pdf += pdflist[i];
					}
				}
				if(ispdf==null || ispdf=="" || pdf==""){
					top.$.dialog({ 	
						width : w, 
						height : h, 
						lock : true, 
						title:"打印报告",
						content: 'url:app/query/report_print_index.jsp?printType=1',
						data : {
							"window" : window,
							"id":ids
						}
					}).max();
					
					
				}else{
					var ids = Qm.getValuesByName("id");
					var report_sns = Qm.getValuesByName("report_sn");
					var export_pdfs = Qm.getValuesByName("export_pdf");
					//var acc_id=Qm.getValuesByName("activity_id");
					//var flow_num=Qm.getValuesByName("flow_num_id");
					var printcopies=Qm.getValuesByName("printcopies");
					var l = ids.length;
					
					openN(0,ids,report_sns,export_pdfs,l,printcopies,2);
					
				}
			}
		}

		function openN(i,ids,report_sns,export_pdfs,l,printcopies,type){
			top.$.dialog({
				width : 800, 
				height : 400, 
				lock : true, 
				title:"查看报告",
				content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ids[i],
				data : {"window" : window,"report_sn":report_sns[i],"date":export_pdfs[i],"type":2,"printcopies":printcopies[i]},
				close:function(){
					i++;
					if(i<l){
						openN(i,ids,report_sns,export_pdfs,l,printcopies,type);
					}else{
						Qm.refreshGrid();
					}
					}
				}).max();
		
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
				content: 'url:app/query/report_print_index_tags.jsp?flag=yes&device_id='+device_id,
				data : {
					"window" : window,
					"id":ids
				}
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
		
		// 下载附件
		function downloadAttachments() {
			var is_upload = Qm.getValueByName("is_upload").toString();
			if(is_upload != "1"){
				$.ligerDialog.alert('亲，系统暂不支持该类报告的附件下载功能哦！', "提示");
				return;
			}
			top.$.dialog({
				width : 600, 
				height : 400, 
				lock : true, 
				title : "下载附件", 
				data : {"window" : window},
				cancel : true,
				content : 'url:app/flow/report_attachment_upload.jsp?status=detail&info_id=' + Qm.getValueByName("inspection_info_id").toString()
			});
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
		<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">提示：列表数据项 
					<font color="black">“黑色”</font>代表电脑出具报告，
					<font color="blue">“蓝色”</font>代表平板出具报告，
					<font color="purple">“紫色”</font>代表纠错报告，
					<font color="red">“红色”</font>代表已作废报告。
				</div>
			</div>
		</div>
	<qm:qm pageid="contract_report_list" script="false" singleSelect="false">
		<qm:param name="pact_id" value="${param.id }" compare="=" />
	</qm:qm>
</body>
</html>
