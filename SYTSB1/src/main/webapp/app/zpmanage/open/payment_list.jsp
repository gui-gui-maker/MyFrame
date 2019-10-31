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
    <title>公招缴费确认</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'rigth',labelSeparator:'',labelWidth:70},// 可以自己定义
            //自定义查询面板查询字段
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
                { text:'缴费', id:'pay',icon:'check', click:pay},
                "-",
                { text:'撤销', id:'cx',icon:'back', click:cx},
                "-",
                { text:'退费', id:'tx',icon:'return', click:tx}
            ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                   // detail(rowData);
                },
                selectionChange :function(rowData,rowIndex){
                    selectionChange();
                },
                rowAttrRender : function(rowData, rowid)
                {
                    //已经提交为蓝色，提交后审核部通过的为红色
                    //alert(rowData.sign);
                    if(rowData.sign=="已退费")
                    {
                        return "style='color:blue'";
                    }   
                    else{
                        return "";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({pay:false,cx:false,detail:false,tx:false});
            }else if(count==1){
                if(Qm.getValueByName("sign")=="资格审核通过"){
                    Qm.setTbar({pay:true,cx:false,detail:true,tx:false});
                }else if(Qm.getValueByName("sign")=="已缴费"){
                    Qm.setTbar({pay:false,cx:true,detail:true,tx:false});
                }else if(Qm.getValueByName("sign")=="取消招聘并退费"){
                    Qm.setTbar({pay:false,cx:false,detail:true,tx:true});
                }else
                	{
                	 Qm.setTbar({pay:false,cx:false,detail:true,tx:false});
                	}

            }else{

                Qm.setTbar({pay:false,verify:false,detail:false,tx:false});
            }
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
		                    $.getJSON('app/zp/jltd/jfcx.do',{id:selectedId},function(data){
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
		function tx(){
			var selectedId=Qm.getValueByName("id");
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要退费的数据！', "提示");
                return;
            }
			$.ligerDialog.confirm('确定需要退费？', function (yes) 
		    		{ 
		    			if(yes)
		    			{
		    				//撤销不通过
		                    $.getJSON('app/zp/jltd/tf.do',{id:selectedId},function(data){
		                        if(data.success){
		                                top.$.dialog.notice({content:'退费操作成功！'});
		                                submitAction();
		                        }else
		                        {
		                        		top.$.dialog.notice({content:'退费操作失败！'});
		                        }
		                    });
		    			}
		    		});
		}
        function verify(){
            var selectedId=Qm.getValueByName("id");
            $.ligerDialog.confirm("请确认考试费用已经到账！到账点击【是】，否则点击【否】。",function(val){
                if(val){
                    //请求数据，生成体检人员
                    $.post('app/zp/jltd/verifyPay.do',{id:selectedId},function(data){
                        //submitAction();
                        if(data.success){
                            top.$.ligerDialog.alert("操作成功！");
                        }else{
                            top.$.ligerDialog.alert("错误！请联系管理员！");
                        }
                        //top.$.ligerDialog.alert(data);
                        Qm.refreshGrid();
                    },'json');
                }
            });

        }

        function pay(item){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            if(selectedId==null||selectedId==""){
            	top.$.ligerDialog.alert('请选择一条需要缴费的数据！', "提示");
                return;
            }
            
            var width=750;
            var height=450;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"现场缴费",
                content: 'url:app/zp/jltd/payDetail.do?status='+status+'&id='+selectedId+'',
//                content: 'url:app/zpmanage/open/payment_detail.jsp',
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

            var width=750;
            var height=450;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看详细信息",
                content: 'url:app/zp/jltd/payDetail.do?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_006" script="false" singleSelect="true" >

</qm:qm>
</body>
</html>