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
            	{name:"year",compare:"like",value:""},
                {name:"month",compare:"like",value:""},
                {name:"unit",compare:"like",value:""},
                {name:"com_name",compare:"like",value:""},
                {name:"phone_num",compare:"like",value:""},
                {name:"check_op",compare:"like",value:""},
            	{name:"enter_op", id:"enter_op", compare:"like"},
            	{group:[{name:"enter_time",compare:">=",value:""},
                        {label:"到",name:"enter_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}]}
               
            ],
          tbar: [
        	
				{ text:'详情', id:'detail',icon:'detail', click: detail} ,
				{ text:'新增', id:'add',icon:'add', click:add},
				{ text:'修改', id:'modify',icon:'modify', click:modify},
  				{ text:'删除', id:'del',icon:'del', click: del} ,
				{ text:'导入', id:'import',icon:'excel-export', click:imp},
  				{ text:'回访', id:'call',icon:'tel', click: call} ,
  				{ text:'回访结果录入', id:'enter',icon:'check-in', click: enter}
                 ],
          listeners: {
                rowDblClick :function(rowData,rowIndex){
                    },
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
                    if(count==1){
                            Qm.setTbar({call:true,enter:true,modify:true,detail:true,del:true});
                    }else if(count>1){
                            Qm.setTbar({call:false,enter:false,modify:false,detail:false,del:true});
                    }else{
                        Qm.setTbar({call:false,enter:false,modify:false,detail:false,del:false});
                    }
                },
                rowAttrRender: function(rowData, rowid) {
                	
                	if(rowData.flag=="1"){
		                 
	                    return "style='color: blue'";
		               
                     }
                }
            }
        };
        
        function imp(){
        	top.$.dialog({
				width : 400,
				height : 200,
				lock : true,
				title : "导入数据",
				content : 'url:app/discipline/import.jsp?status=add',
				data : {
					"window" : window
				}
			});
        }
        
        function add(){
        	top.$.dialog({
				width : 1000,
				height : 600,
				lock : true,
				title : "新增回访计划",
				content : 'url:app/discipline/discipline_plan_detail.jsp?status=add',
				data : {
					"window" : window
				}
			});
        }
        
        function detail(){
        	var id =  Qm.getValueByName("id");
        	top.$.dialog({
        		width : 1000,
				height : 600,
				lock : true,
				title : "查看回访计划",
				content : 'url:app/discipline/discipline_plan_detail.jsp?status=detail&id='+id,
				data : {
					"window" : window
				}
			});
        }
        
        function modify(){
        	var id =  Qm.getValueByName("id");
        	top.$.dialog({
        		width : 1000,
				height : 600,
				lock : true,
				title : "修改回访计划",
				content : 'url:app/discipline/discipline_plan_detail.jsp?status=modify&id='+id,
				data : {
					"window" : window
				}
			});
        }
        
        function del(){
        	$.del("确定删除?", "disciplinePlanAction/del.do", {
    			"ids" : Qm.getValuesByName("id").toString()
    		});
        }
        
        
        
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
	
        function refreshGrid(){
        	Qm.refreshGrid();
        }
        
        //回访记录
        function enter(){
        	
        	var ids = Qm.getValuesByName("id").toString();
        	
        	top.$.dialog({
				width : 800,
				height : 500,
				lock : true,
				title : "回访结果录入",
				content : 'url:app/discipline/discipline_result_detail.jsp?status=add',
				data : {
					"window" : window,"ids":ids
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
			var id = Qm.getValueByName("id");
			var com_name = Qm.getValueByName("com_name");
			var inspect_op = Qm.getValueByName("check_op");
			var tel = Qm.getValueByName("phone_num");
			var unit = Qm.getValueByName("unit");
			var maintain_name = Qm.getValueByName("maintain_name");
			if(tel==null||tel==""){
				$.ligerDialog.warn('选择的单位没有记录联系电话，不能呼叫或者发送短信！');
				return;
			}
			
			

        	top.$.dialog({
				width : 800,
				height : 500,
				lock : true,
				title : "回访结果录入",
				content : 'url:app/discipline/discipline_result_detail.jsp?status=add',
				data : {
					"window" : window,"ids":id
				}
			});
			
			top.$.dialog({
				width : 660, 
				height : 400, 
				lock : false, 
				title:"电话呼叫",
				content: 'url:app/discipline/discipline_plan_call.jsp',
				parent: api,
				data : {"window" : window,
						'info_id':id,
						'com_name':com_name,
						'inspect_op':inspect_op,
						'tel':tel,
						'maintain_name':maintain_name,
						"unit":unit
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
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="blue">“蓝色”</font>代表——已经回访过
        </div>
    </div>
    </div>
		<qm:qm pageid="tjy2_discipline_plan" singleSelect="false">
		
		</qm:qm>
	
	</body>
</html>
