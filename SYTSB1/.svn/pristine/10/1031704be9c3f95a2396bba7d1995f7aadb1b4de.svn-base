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
    <title>直接考核资格审核</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:70},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"gw_num",compare:"like",value:""},
                {name:"gw_name",compare:"like",value:""},
                {name:"name",compare:"like",value:""},      
                {name:"zj_no",compare:"like",value:""}
            ],
            tbar:[
  				{ text:'详情', id:'detail',icon:'detail', click:detail},
  				"-",
                  { text:'审核', id:'sh',icon:'check', click:sh},
                  "-",
                  { text:'撤销', id:'cx',icon:'back', click:cx}
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
                    //alert(rowData.sign);
                    if(rowData.sign=="待审核")
                    {
                        return "style='color:red'";
                    }else if(rowData.sign=="资格审核不通过"){
                       return "style='color:blue'";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({sh:false,detail:false,cx:false});
            }else if(count==1){
            	Qm.setTbar({sh:true,detail:true,cx:true});
                if(Qm.getValueByName("sign")=="待审核"){
                    Qm.setTbar({sh:true,detail:true,cx:false});
                }else {
                    Qm.setTbar({sh:false,detail:true,cx:true});
                }

            }else{

                Qm.setTbar({sh:false,detail:false});
            }
        }

        function cx(item)
        {
        	var selectedId=Qm.getValueByName("id");
        	var sign = Qm.getValueByName("sign");
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要撤销的数据！', "提示");
                return;
            }
			$.ligerDialog.confirm('确定需要撤销审核的数据？', function (yes) 
		    		{ 
		    			if(yes)
		    			{
		    				var status ;
		    				if(sign =='待面试审核')
		    				{
		    					status = '1'
		    				}
		    				else if(sign == '资格审核不通过')
		    				{
		    					status ='2'	
		    				}
		    				//撤销不通过
		                    $.getJSON('app/zp/jltd/othercx.do',{id:selectedId,status:status},function(data){
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
        function sh(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要审核的数据！', "提示");
                return;
            }
            
            var width=1024;
            var height=768;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"资格审核",
                content: 'url:app/zpmanage/other/outter/Qualification_detail.jsp?status='+status+'&check=1&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }

        function detail(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            if(selectedId==null||selectedId==""){
                top.$.ligerDialog.alert('请选择一条需要审核的数据！', "提示");
                return;
            }

            var width=1024;
            var height=768;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"资格审核",
                content: 'url:app/zpmanage/other/outter/Qualification_detail.jsp?status='+status+'&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_023" script="false" singleSelect="true" >

</qm:qm>
</body>
</html>