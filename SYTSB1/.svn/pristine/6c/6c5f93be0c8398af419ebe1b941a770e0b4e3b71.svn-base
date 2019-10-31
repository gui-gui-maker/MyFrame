<%--
  Created by IntelliJ IDEA.
  User: qin
  Date: 12-8-2
  Time: 上午11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公招面试资格审核</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
     <style type="text/css">
    .l-icon-money{background:url('k/kui/skins/icons/money.gif') no-repeat center;}
	</style>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:70},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"gw_num",compare:"like",value:""},
                {name:"gw_name",compare:"like",value:""},
                {name:"fk_dep_id",compare:"=",value:""},
                {name:"name",compare:"like",value:""},      
                {name:"zj_no",compare:"like",value:""}
            ],

            tbar:[
                { text:'审核', id:'sh',icon:'check', click:sh},
                "-",
                { text:'面试未缴费', id:'wjf',icon:'money', click:wjf},
                "-",
                { text:'打印准考证', id:'print',icon:'print', click:printTicket}
                //,
                //"-",
                //{ text:'查看', id:'detail',icon:'detail', click:detail}
            ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                  //  detail(rowData);
                },
                selectionChange :function(rowData,rowIndex){
                    selectionChange();
                },
                rowAttrRender : function(rowData, rowid)
                {
                    //已经提交为蓝色，提交后审核部通过的为红色
                    //alert(rowData.sign);
                     if(rowData.istb!=''&&rowData.istb!=undefined)
                 	{
                 		return "style='color:blue'";//蓝色代表替补
                 	}
                    if(rowData.sign=="17")
                    {
                        return "style='color:red'";
                    }else if(rowData.sign=="29"){
                        return "style='color:blue'";
                    }else if(rowData.sign=="30"||rowData.sign=="9"){
                    	return "style='color:grey'";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({sh:false,print:false,detail:false,wjf:false});
            }else if(count==1){
                if(Qm.getValueByName("sign")=='17'){
                    Qm.setTbar({sh:true,print:false,detail:true,wjf:false});
                }else if(Qm.getValueByName("sign")=='8'){
                    Qm.setTbar({sh:false,print:false,detail:true,wjf:true});
                }else if(Qm.getValueByName("sign")=='9'){
                    Qm.setTbar({sh:false,print:false,detail:true,wjf:false});
                }
                else if(Qm.getValueByName("sign")=='29'){
                    Qm.setTbar({sh:false,print:true,detail:true,wjf:false});
                }
            }else{

                Qm.setTbar({sh:false,print:false,detail:false,wjf:false});
            }
        }
		function wjf()
		{
			var selectedId=Qm.getValueByName("id");
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要确定为缴费的数据！', "提示");
                return;
            }
			$.ligerDialog.confirm('确定选择的投递人未缴费？', function (yes) 
		    		{ 
		    			if(yes)
		    			{
		    				//撤销不通过
		                    $.getJSON('app/zp/jltd/saveInterviewPayment.do',{id:selectedId,status:'30'},function(data){
		                        if(data.success){
		                                top.$.dialog.notice({content:'操作成功！'});
		                                submitAction();
		                        }else
		                        {
		                        		top.$.dialog.notice({content:'操作失败！'});
		                        }
		                    });
		    			}
		    		});
		}
        function sh(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"面试资格审核",
                content: 'url:app/zp/jltd/interviewReviewDetail.do?status='+status+'&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }

      //打印准考证 1为笔试，2为面试
        function printTicket(){
    	  var id=Qm.getValueByName("id");
      	  //if(obj=="1"){
      		window.open('<%=basePath%>/app/zp/jltd/ticketPrint.do?id='+id+'&type=2','_blank'); 
      	 // }
        }

        function detail(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";

            var width=1024;
            var height=768;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看详细资料",
                content: 'url:app/zp/jltd/interviewReviewDetail.do?status='+status+'&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_011" script="false" singleSelect="true" >

</qm:qm>
<script type="text/javascript">
   Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>