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
    <title>公招面试面试缴费</title>
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
                { text:'缴费', id:'jf',icon:'money', click:jf}
                ,
                "-",
                { text:'撤销', id:'cx',icon:'back', click:cx}
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
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({jf:false,cx:false});
            }else if(count==1){
                if(Qm.getValueByName("sign")=='8'){
                    Qm.setTbar({jf:true,cx:false});
                }else
                {
                	Qm.setTbar({jf:false,cx:true});
                }
            }else{

                Qm.setTbar({sh:false,cx:false});
            }
        }

        function jf(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=700;
            var height=450;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"面试费缴费",
                content: 'url:app/zp/jltd/interviewPaymentDetail.do?status='+status+'&access=1&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }
        function cx()
		{
			var selectedId=Qm.getValueByName("id");
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要撤销的数据！', "提示");
                return;
            }
			$.ligerDialog.confirm('确定需要撤销已缴费的数据？', function (yes) 
		    		{ 
		    			if(yes)
		    			{
		    				//撤销不通过
		                    $.getJSON('app/zp/jltd/jfcx.do',{id:selectedId,status:'2'},function(data){
		                        if(data.success){
		                                top.$.dialog.notice({content:'撤销操作成功！'});
		                                submitAction();
		                        }else
		                        {
		                        		top.$.dialog.notice({content:'撤销操作失败！'});
		                        }
		                    });
		    			}
		    		});
		}
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_022" script="false" singleSelect="true" >

</qm:qm>
<script type="text/javascript">
Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>