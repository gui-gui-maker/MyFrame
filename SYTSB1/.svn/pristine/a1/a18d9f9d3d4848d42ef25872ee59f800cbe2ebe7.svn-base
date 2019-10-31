<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>公招成绩录入</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <style type="text/css">
      .l-icon-search-add {background:url('/k/kui/skins/icons/search-add.gif') no-repeat center;}
    </style>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:70},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"gw_num",compare:"like",value:""},
                {name:"gw_name",compare:"like",value:""},
                {name:"name",compare:"like",value:""},      
                {name:"zj_no",compare:"like",value:""},
                {name:"examname",compare:"like",value:""},
                {name:"num",compare:"like",value:""}
            ],

            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail},
				"-",
                { text:'录入成绩', id:'result',icon:'add', click:result}
				 <sec:authorize access="hasRole('cj_edit')">
					,
	                "-",
	                { text:'成绩修改', id:'edit',icon:'edit', click:edit}
	             </sec:authorize>
                <sec:authorize access="hasRole('screen_person')">
                ,
                "-",
                { text:'筛选面试资格审查人员', id:'create',icon:'search-add', click:create}
                 </sec:authorize>
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
                    //if(rowData.sign=="已提交")
                    //{
                    //    return "style='color:blue'";
                    //}else if(rowData.sign=="审核打回"){
                    //    return "style='color:red'";
                    //}else{
                    //    return "";
                    //}
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
        	//alert(Qm.getValueByName("id"));
        	
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({result:false,create:true,detail:false,edit:false});
            }else if(count==1){
                if(Qm.getValueByName("sign")=="已录入笔试成绩"){
                	if(Qm.getValueByName("status")=="3"){
                		Qm.setTbar({result:false,create:true,detail:true,edit:false});
                	}else{
                		Qm.setTbar({result:false,create:true,detail:true,edit:true});
                	}
                    
                }else if(Qm.getValueByName("sign")=="打印准考证"){
                	if(Qm.getValueByName("status")=="3"){
                		Qm.setTbar({result:false,create:true,detail:true,edit:false});
                	}else{
                		Qm.setTbar({result:true,create:true,detail:true,edit:false});
                	}
                }else {
                	Qm.setTbar({result:false,create:true,detail:true,edit:false});

                }


            }else{

                Qm.setTbar({result:false,detail:false});
            }
        }

        //生成面试人员
        function create(item){
        	
        	$.getJSON("app/zp/system/compareTime.do",{timeName:"WriteResultsTime"},function(data){
            	if(data.success){
            		//判断是否已经筛选
            		$.getJSON('app/zp/jltd/mssx.do?status=3',function(data){
            			if(data.success)
            			{
            				$.ligerDialog.alert("面试资格审查人员已经筛选！");
                			return;
            			}
            			else
            			{
            				$.ligerDialog.confirm("请确认笔试成绩已经全部录入,<font color=\"red\">每次公招只能点击一次</font>，点击【是】后将筛选出面试审核人员，请到面试审核开始下一环节处理？",function(val){
//                              alert(val);
//                              alert(id);
                              if(val)
                              {
                            	  $.getJSON('app/zp/jltd/uninsertbs.do?sign=6',function(data){
                             		 if(data.success)
                             		 {
                             			 $.ligerDialog.alert("还有未录入笔试成绩的数据！");
                             			 return;
                             		 }
                             		 else
                             	     {
                             			 //检查是否有为录入成绩的人员
                                          //请求数据，生成体检人员
                                          $.post('app/zp/jltd/createInterviewList.do',{id:'33'},function(data){
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
                          });
            			}
            		});
            	}else{
            		//alert(1);
            		top.$.dialog.notice({content:data.data});
            		Qm.refreshGrid();
            	}
            });
            
        }
        function edit(item)
        {
             var selectedId=Qm.getValueByName("id");
             var status = "edit";
             var width=600;
             var height=400;
             var windows=top.$.dialog({
                 width:width,
                 height:height,
                 lock:true,
                 title:"修改笔试成绩",
                 content: 'url:app/zp/jltd/resulteditDetail.do?status='+status+'&id='+selectedId+'',
                 data: {"window": window}
             });
        }
        function result(item){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"录入笔试成绩",
                content: 'url:app/zp/jltd/resultDetail.do?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }

        function detail(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            if(item.id!='detail')
            {
            	selectedId = item.id;
            }
            if(selectedId==null||selectedId==""){
                top.$.ligerDialog.alert('请选择一条需要查看的数据！', "提示");
                return;
            }

            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看详细信息",
                content: 'url:app/zp/jltd/resultDetail.do?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_007" script="false" singleSelect="true" >

</qm:qm>
</body>
</html>