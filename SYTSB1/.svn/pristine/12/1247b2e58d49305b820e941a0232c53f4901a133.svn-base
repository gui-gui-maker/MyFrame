<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
%> 
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
	
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
                {name:"report_sn",compare:"like",value:""},
                {name:"com_name",compare:"like",value:""},
                {group:[{name:"create_date",compare:">=",value:"",width:"100"},
                    {label:"到",name:"create_date",compare:"<=",value:"",labelAlign:"center",labelWidth:20}]},
            	{name:"create_op", id:"create_op", compare:"like"},
            	{name:"phone_number", id:"phone_number", compare:"like"},
            	{name:"judge_grade", id:"judge_grade", compare:"like"}
            ],
        tbar: [{ text:'下载录音', id:'down',icon:'export', click:down } ],
          listeners: {
                rowDblClick :function(rowData,rowIndex){
                    },
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
                    if(count==1){
                            Qm.setTbar({down:true});
                    }else{
                        Qm.setTbar({down:false});
                    }
                },
                rowAttrRender: function(rowData, rowid) {
                	
                }
            }
        };
        
        function cs(){
			var id = Qm.getValueByName("inspection_info_id").toString();//报告的id
			var day =$("#day").html();//翻红时限
			var lrsj = "QCDQF";//录入时间
			$.ajax({
                url: "report/yjsz/reportYj.do?id="+id,
				type : "post",
				async : false,
				success : function(data) {
					if (data.success) {
						top.$.notice("成功！");
						Qm.refreshGrid();
					} else {
						$.ligerDialog.alert("失败！" + data.msg);
					}
				}
			});
        	
			
        	
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
		
		// 签发过期
		function signExpired(){	
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue('');	// 检验部门
			$("input[name='flow_note_name-txt']").ligerComboBox().selectValue('报告签发');	// 流程步骤
			$("input[name='isExpired-txt']").ligerComboBox().selectValue('1');
			Qm.searchData();
    		Qm.setCondition([]);
		}
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			$("input[name='check_unit_id-txt']").ligerComboBox().selectValue('');	// 检验部门
			$("input[name='flow_note_name-txt']").ligerComboBox().selectValue('');	// 流程步骤
			$("input[name='isExpired-txt']").ligerComboBox().selectValue('');
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
		
		//语音回访

		function call(){
			var inspection_info_id = Qm.getValueByName("inspection_info_id");
			var report_sn = Qm.getValueByName("report_sn");
			var com_name = Qm.getValueByName("report_com_name");
			var security_op = Qm.getValueByName("security_op");
			var tel = Qm.getValueByName("security_tel");
			var com_id = Qm.getValueByName("com_id");
			
			if(tel==null||tel==""){
				$.ligerDialog.warn('选择的单位没有记录联系电话，不能呼叫或者发送短信！');
				return;
			}
			
			top.$.dialog({
				width : 600, 
				height : 400, 
				lock : true, 
				title:"电话呼叫",
				content: 'url:app/discipline/discipline_call.jsp',
				data : {"window" : window,
						'info_id':inspection_info_id,
						'report_sn':report_sn,
						'com_name':com_name,
						'security_op':security_op,
						'tel':tel,
						"com_id":com_id
				}
			});
			
			//alert(tel)
		}
		function shutdown(){
			var tel = Qm.getValueByName("security_tel");
			top.$.dialog({
				width : 600, 
				height : 400, 
				lock : true, 
				title:"电话挂机",
				content: 'url:app/discipline/discipline_shutdown.jsp',
				data : {"window" : window}
			});
			
			//alert(tel)
		}
		
		function down(){
			var id = Qm.getValueByName("id");
			window.location.href=$("base").attr("href")+"disciplineCallAction/downloadById.do?id="+id;
		}
		
    </script>
	</head>
	<body>
	
		<qm:qm pageid="tjy2_discipline_call" singleSelect="true">
		
		</qm:qm>
		
	</body>
</html>
