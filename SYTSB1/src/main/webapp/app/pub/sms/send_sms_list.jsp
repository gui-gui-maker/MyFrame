<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String areaCode=sessionUser.getDepartment().getAreaCode();
%>
<head>
    <title>公益劳动列表</title>
    <%@include file="/k/kui-base-list.jsp"%>
    <script test="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
            //sp_defaults:{columnWidth:0.33,labelAlign:'top',labelSeparator:'',labelWidth:80},// 可以自己定义
//            自定义查询面板查询字段
            sp_fields:[
                {name:"person_name",compare:"like",value:""},
                {name:"account",compare:"like",value:""},
                {name:"content",compare:"like",value:""},
                {name:"statusname",compare:"=",value:""},
                {group:[
                        {name:"send_time",compare:">=",value:""},
                        {label:"到",name:"send_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
                        ]
                } 
            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar:[
                { text:'详情', id:'detail',icon:'detail', click:detail },
                "-",
                { text:'发送', id:'send',icon:'outbox', click:send },
                "-",
                { text:'重新发送', id:'re_send',icon:'outbox',  click:re_send }
            ],
////            //提供以下4个事件
            listeners: {
                rowDblClick :function(rowData,rowIndex){
                	detail();
                	},
                	rowAttrRender:function(item, rowid) {
                        if(item.status=="1")
                            return "style='color: red'";
                        else
                            return "";
                     },
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
                    var status=Qm.getValuesByName("status");
                    if(count==0){
                    	Qm.setTbar({detail:false,send:false,re_send:false});
                    }else if(count==1){
                    	Qm.setTbar({detail:true,send:false,re_send:false});
                    	if(status=="0"){
                    		Qm.setTbar({detail:true,send:true,re_send:false});
                    	}
                    	if(status=="1"){
                    		Qm.setTbar({detail:true,send:false,re_send:true});
                    	}
                    	
                    }else{
                    	Qm.setTbar({detail:false,send:false,re_send:false});
                    	var str=new Array();
                    	str=status.toString().split(",");
                    	var flagAll=true;
                    	//判断当前选择是否是一种状态
                    	for(var i=0;i<str.length;i++){
                    		if(str[0]!=str[i]){
                    			flagAll=false;
                    			break;
                    		}
                    	}
                    	//是一种状态，区分是那种状态
                    	if(flagAll){
                    		if(str[0]=="0"){
                        		Qm.setTbar({send:true,re_send:false});
                        	}
                        	if(str[0]=="1"){
                        		Qm.setTbar({send:false,re_send:true});
                        	}
                    	}
                    }
                }
            }
        };

        function send(){
        	$.ligerDialog.confirm('是否发送当前选择短信？', function (yes) { 
        		if(yes){
        			$("body").mask("正在提交数据...");
		        	var ids=Qm.getValuesByName("id").toString();
		            $.getJSON("pub/message/mobile/sendSms.do",{ids:ids},function(res){
		            	if(res.success){
        					top.$.dialog.notice({content:'处理完成！'});
        					Qm.refreshGrid();
        					$("body").unmask();
        				}else{
        					$("body").unmask();
        					$.ligerDialog.error("错误："+data.msg);
        				}
		            });
	            }
        	});
        }
        
        function re_send() {
        	$.ligerDialog.confirm('是否发送当前选择短信？', function (yes) { 
        		if(yes){
        			$("body").mask("正在提交数据...");
		        	var ids=Qm.getValuesByName("id").toString();
		            $.getJSON("pub/message/mobile/sendSms.do",{ids:ids},function(res){
		            	if(res.success){
        					top.$.dialog.notice({content:'处理完成！'});
        					Qm.refreshGrid();
        					$("body").unmask();
        				}else{
        					$("body").unmask();
        					$.ligerDialog.error("错误："+data.msg);
        				}
		            });
	            }
        	});
		}
        

        function detail(){
        	var width=800;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                data:{window:window} ,
                title:"短信详情",
                content: 'url:app/pub/sms/sms_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
            });
        }
        function submitAction(o) {
            Qm.refreshGrid();
        }
    </script>
</head>
<body>
<q:qm pageid="pub_sms" singleSelect="false" >
</q:qm>
</body>
</html>