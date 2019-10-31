<%@page import="java.util.Map"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());
	CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String user=sessionUser.getSysUser().getName();
String userBm= sessionUser.getDepartment().getId();
Map<String,String> roles=sessionUser.getRoles();%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/fwxm/scientific/js/doc_order.js"></script>
<script type="text/javascript">

var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
		           {name: "project_name", compare: "like"},
				     {name: "project_department", compare: "like"},
				     { name: "project_head", compare: "like"}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		  ,  "-",
		  {text: '修改任务书', id: 'add', icon: 'add', click: add } 
		  , "-", {
				text: '管理项目成果', id: 'modify', icon: 'modify', click: modify
			}
		  , "-", {
				text: '填写中期检查表', id: 'check', icon: 'add', click: check
			}, "-", {
				text: '提交', id: 'sub', icon: 'sub', click: sub
			}, "-", {
				text: '审批', id: 'ok', icon: 'ok', click: ok
			}],
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
	        	   var count = Qm.getSelectedCount();
	        	var status = Qm.getValuesByName("status");
	        	var falg=true;
	        	var falg1=true;
	        	var falg2=false;
	        	var falg3=false;
	        	var falg4=false;
	        	for(var i=0;i<status.length;i++ ){
	        		if(status[i]!="3"){
	        			falg=false;
	        		}
	        		if(status[i]!="5"){
	        			falg1=false;
	        		}
	        		if(status[i]=="5"||status[i]=="7"){
	        			falg2=true;
	        		}
	        		if(status[i]=="3"||status[i]=="5"||status[i]=="7"){
	        			falg3=true;
	        		}
	        		if(status[i]=="4"||status[i]=="8"||status[i]=="6"){
	        			falg4=true;
	        		}
	        		
	        	}
	        	
		            Qm.setTbar({ 
		          	detail: count==1,
		          	alteration: count==1,
		          	cancellation : count>0,
		          	modify:count==1&&falg2,
		          	del:count>0,
		          	ok:count>0&&falg4,
		          	add:count==1&&falg,
		          	check:count==1&&falg1,
		          	sub:count==1&&falg3
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	var fontColor="black";
	        	if (rowData.status=='4'){
	        		 fontColor="blue";
	        	 }
	        	if (rowData.status=='5'||rowData.status=='7'){
	        		 fontColor="green";
	        	 }
	        	if (rowData.status=='6'){
	        		 fontColor="orange";
	        	 }
	        	if (rowData.status=='8'){
	        		 fontColor="red";
	        	 }
	        	return "style='color:"+fontColor+"'";
	 				}

}
}
		
		
		
function ok(){
 	var id=Qm.getValuesByName("id");
 	 top.$.dialog({
         width: 600,
         height: 300,
         lock: true,
         parent: api,
         data: {
       	 window: window
         },
         title: "科研项目申请书审核",
         content: 'url:app/fwxm/scientific/scientific_audit_check.jsp?pageStatus=add&id='+Qm.getValuesByName("id")
      });
        
         }
      function check(){
    	  top.$.dialog({
 	         width: 900,
 	         height: 500,
 	         lock: true,
 	         parent: api,
 	         data: {
 	       	 window: window
 	         },
 	         title: "填写科技项目中期检查表",
 	         content: 'url:app/fwxm/scientific/scientific_interim.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
          });
    	  
      }
		function del(){
			      $.del("确定删除?", "tjy2ScientificResearchAction/delete.do", {
			       	"ids" : Qm.getValuesByName("id").toString()
			      })
		}
		
		function detail(){
	         top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/fwxm/scientific/scientific_detail.jsp?pageStatus=detail&type=rw&id='+Qm.getValueByName("id")
	          });
	         
        }
		function add(){
			 top.$.dialog({
		         width: 800,
		         height: 400,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改任务书",
		         content: 'url:app/fwxm/scientific/scientific_detail.jsp?pageStatus=modify&type=rw&id='+Qm.getValueByName("id")
	          }); 
			<%-- $.ajax({
	            url: "tjy2ScientificResearchAction/detail.do?id="+Qm.getValueByName("id"),
	            type: "POST",
	           /*    dataType: "json", 
	            contentType: "application/json; charset=utf-8",  */
	           // data:{"id":"${param.id}","projectResultsType":projectResultsType,"projectResults":projectResults,"remark":remark},
	            
	            success : function(response) {
					$("body").unmask();
					
						//打开生成报告页面
						openContentDoc({
							status : "draft",
							id:response.data,
							fid: response.fillDate,
							pdf: response.startDate ,
							aid: response.endDate,
							type:"modify",
							wtype:"rw",
							window:window,
							title : "<%=nowNum%>",
							tbar : {
								edit : true,
								print : true,
								layout : true
							}
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					
				},
	            error : function(data) {
	                $("body").unmask();
	                $.ligerDialog.error('保存数据失败！');
	            }
	        }); --%>
		}
        function modify(){
        	top.$.dialog({
		         width: 900,
		         height: 500 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改",
		         content: 'url:app/fwxm/scientific/scientific_detail_wc.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&status=2"
	          });
        	
        }
        function sub(){
     	   //判断值是否完整
     	   $.ajax({
 				url : "tjy2ScientificResearchAction/getScientific.do?id="+Qm.getValuesByName("id"),
 				type : "POST",
 				success : function(data, stats) {
 					$("body").unmask();
 					if (data["success"]) {
 						if(data.flag){
 							//申报书填完才可以提交
 							 $.ligerDialog.confirm("是否提交？",
 										function(yes) {
 									    if(yes){
 									    	
 									    	$.ajax({
 							     				url : "tjy2ScientificResearchAction/updateConfirm.do?id="+Qm.getValuesByName("id"),
 							     				type : "POST",
 							     				data : {
 							     					result:"1"
 							     				},
 							     				success : function(data, stats) {
 							     					$("body").unmask();
 							     					if (data["success"]) {
 							     						
 							     						Qm.refreshGrid();
 							     						top.$.notice("提交成功！");
 							     					} else {
 							     						$.ligerDialog.error('提示：' + data);
 							     					}
 							     				},
 							     				error : function(data, stats) {
 							     					$("body").unmask();
 							     					$.ligerDialog.error('提示：' + JSON.stringify(data));
 							     				}
 							     			});
 									}
 								        
 								});
 						}else{
 							$.ligerDialog.error("除协作单位可以不填，其余必填！");
 						}
 						
 					} else {
 						$.ligerDialog.error('提示：' + data);
 					}
 				},
 				error : function(data, stats) {
 					$("body").unmask();
 					$.ligerDialog.error('提示：' + JSON.stringify(data));
 				}
 			});
     	  
        }
       
       
</script>

</head>
<body>
<div class="item-tm" id="titleElement">
  <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表该项目任务书正在审核中，
				<font color="green">“绿色”</font>代表该项目正常进行中，
				<font color="orange">“橙色”</font>代表该项目中期检查表正在审核中，
				<font color="red">“红色”</font>代表该项目正在验收中。
				
			</div>
		</div>
	</div>
        <qm:qm pageid="scientific_list_rw">
        <
       <%if(roles.get("402883a05939b237015939d3838d163f")==null){ %>
       <qm:param name="create_man" value="<%=sessionUser.getName()%>" compare="="  />
       <qm:param name="project_head" value="<%=sessionUser.getName()%>" compare="=" logic="or" />
       <%} %> 
       </qm:qm>
</body>
</html>