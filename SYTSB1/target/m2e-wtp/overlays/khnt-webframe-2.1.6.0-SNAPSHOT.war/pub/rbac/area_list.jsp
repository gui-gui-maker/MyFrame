<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp"%>
    <script>
        var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
            sp_defaults:{columnWidth:0.33,labelAlign:'top',labelSeparator:'',labelWidth:30},// 可以自己定义
//            自定义查询面板查询字段
            sp_fields:[
                {name:"levels",compare:"=",value:""},
                {name:"code",compare:"like",value:""},
                {name:"name",compare:"like",value:""}
            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar:[
                { text:'新增', id:'add',icon:'add', click:addSignle },
                "-",
                { text:'修改', id:'edit',icon:'modify',  click:edit  ,disable:true},
                "-",
                { text:'删除', id:'del',icon:'delete',  click:del ,disable:true},
                "-",
                { text:'批量添加', id:'addMore',icon:'add',  click:addMore },
                "-",
                { text:'置经纬度', id:'setjwd',icon:'modify',  click:setJwd },
                "-",
                { text:'批量设置经纬度', id:'plsetjwd',icon:'modify',  click:setPlJwd }

            ],
////            //提供以下4个事件
            listeners: {
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
                    var status={};
                    if(count==0){
                        status={edit:false,del:false,setjwd:false};
                    }else if(count==1){
                        status={edit:true,del:true,setjwd:true};
                    }else{
                        status={edit:false,del:true,setjwd:false};
                    }
                    Qm.setTbar(status);
                }
            }
        };
        
        var parentNode=null;

        function add(more){
            if(parentNode==null){
                $.ligerDialog.warn("请先从左边地址目录中选择上级地址!");
                return;
            }
            var width=700;
            var height=200;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                data:{window:window} ,
                title:"区划地址",
                content: 'url:pub/rbac/'+(more?"more_detail":"area_detail")+'.jsp?status=add&pcode='+parentNode.code+'&pid='+parentNode.id
            });
        }
        
        function setJwd(){
        	var id = Qm.getValueByName("id");
        	var fullName = Qm.getValueByName("full_name");
        	top.$.dialog({
                width:800,
                height:600,
                lock:true,
                data:{
                	window:window,
                	fullName:fullName
                },
                title:"经纬度设置",
                content: 'url:pub/rbac/jwd_detail.jsp?id='+id
            });
        }
        function setPlJwd(){
        	
        	$.ligerDialog.confirm("您确定要批量设置经纬度？", function (yes) {
        		if(yes){
        			$("body").mask("正在设置请稍后...");
            		$.getJSON("rbac/area/setGeoCode.do",{all:"all"},function(res){
            			$("body").unmask();
            			if(res.success){
            				Qm.refreshGrid();
            			}else{
            				$.ligerDialog.error("批量设置经纬度失败！");
            			}
            		})
        		}
        	})
        }
        
        function addSignle(){
            add(false);
        }
        function addMore(){
            add(true);
        }
        function edit(){
            var width=700;
            var height=200;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                data:{window:window} ,
                title:"区划地址",
                content: 'url:pub/rbac/area_detail.jsp?status=edit&id='+Qm.getValueByName("id")
            });
        }
        function del(){
            $.ligerDialog.confirm("您确定要删除所选数据吗？", function (yes) {
                if (yes) {
                    $.post("rbac/area/del.do", {ids:Qm.getValueByName("id")}, function (data) {
                        if (data.success) {
                            parent.removeNode(data.data);
                            Qm.refreshGrid();
                        } else {
                            var msg="删除失败！";
                            if(data.msg){
                                msg+="<br>"+data.msg;
                            }else if(data.data){
                                msg+="<br>"+data.data;
                            }
                            top.$.dialog.notice({
                                icon : 'k/kui/images/icons/dialog/32X32/fail.png', content : msg
                            });
                        }
                    }, "json");
                }
            });
        }
    </script>
</head>
<body>
<q:qm pageid="sys_04" singleSelect="true" >
   
</q:qm>
</body>
</html>