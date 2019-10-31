<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公招岗位调整</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <style type="text/css">
    .l-icon-clock{background:url('k/kui/skins/icons/clock.gif') no-repeat center;}
	</style>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:60},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
					{name:"gw_num",compare:"like",value:""},
					{name:"gw_name",compare:"like",value:""}
            ],

            tbar:[
                { text:'调整岗位', id:'adjust',icon:'edit', click:adjust}
                ,
                "-",
                { text:'调整完成', id:'alladjust',icon:'clock', click:alladjust}
            ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                    //detail(rowData);
                },
                selectionChange :function(rowData,rowIndex){
                    selectionChange();
                },
                rowAttrRender : function(rowData, rowid)
                {
                    //已经提交为蓝色，提交后审核部通过的为红色
                    if(rowData.flag=="1")
                    {
                        return "style='color:red'";
                    }else{
                        return "";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({adjust:false});
            }else if(count==1){
                if(Qm.getValueByName("flag")=="1"){
                    Qm.setTbar({adjust:true});
                }else{
                    Qm.setTbar({adjust:false});
                }
            }else{
                Qm.setTbar({adjust:false});
            }
        }


        function adjust(item){
        	$.getJSON("app/zp/system/compareTime.do",{timeName:"PayEndTime"},function(data){
            	if(data.success){
            		var selectedId=Qm.getValueByName("id");
                    var status = "edit";
                    var width=600;
                    var height=400;
                    var windows=top.$.dialog({
                        width:width,
                        height:height,
                        lock:true,
                        title:"公开招聘岗位调整",
                        content: 'url:app/zp/xqxx/adjustDetail.do?status='+status+'&id='+selectedId+'&paycount='+Qm.getValueByName("paycount")+'&totalcount='+Qm.getValueByName("totalcount")+'&uncheckcount='+Qm.getValueByName("uncheckcount"),
                        data: {"window": window}
                    });
            	}else{
            		//alert(1);
            		top.$.dialog.notice({content:data.data});
            		Qm.refreshGrid();
            	}
            });
            
        }
        
        function alladjust(){
        	
        	$.getJSON("app/zp/system/compareTime.do",{timeName:"PayEndTime"},function(data){
            	if(data.success){
            		$.getJSON("app/zp/xqxx/saveAllAdjust.do",function(data){
                    	if(data.success){
                    		top.$.dialog.notice({content:'操作成功！'});
                    		Qm.refreshGrid();
                    	}else{
                    		//alert(1);
                    		top.$.dialog.notice({content:'岗位还未调整完成，请继续调整！'});
                    		Qm.refreshGrid();
                    	}
                    });
            	}else{
            		//alert(1);
            		top.$.dialog.notice({content:data.data});
            		Qm.refreshGrid();
            	}
            });
        	
        	
            
        }

        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_014" script="false" singleSelect="true" >

</qm:qm>
</body>
</html>