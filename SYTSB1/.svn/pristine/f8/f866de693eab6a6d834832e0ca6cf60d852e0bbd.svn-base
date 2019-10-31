<%@page import="java.util.Map"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());
	CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String user=sessionUser.getSysUser().getName();
String userBm= sessionUser.getDepartment().getId();
Map<String,String> roles=sessionUser.getRoles();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">

var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
				     {name: "project_name", compare: "like"},
				     {name: "unit_gk", compare: "like"},
				     {name: "project_head", compare: "like"}
		] , <tbar:toolBar type="tbar" code="skygl_xmsb"> </tbar:toolBar>,
		/* tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		  ,  "-",
		  {text: '新增', id: 'add', icon: 'add', click: add } 
		  , "-", {
				text: '修改', id: 'modify', icon: 'modify', click: modify
			} 
		, "-", {
			text: '删除', id: 'del', icon: 'del', click: del
		}, "-", {
			text: '提交', id: 'sub', icon: 'sub', click: sub
		}, "-", {
			text: '审批', id: 'ok', icon: 'ok', click: ok
		}] */
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
	        	   var flag=true;
	        	   var flag1=false;
	        	   var flag2=true;
	        	   var flag3=true;
	        	   var flag4=true;
	        	   var count = Qm.getSelectedCount();
	        	   var status=Qm.getValuesByName("status");
	        	   var create_mans=Qm.getValuesByName("create_man");
	        	   var project_heads=Qm.getValuesByName("project_head");
	        	   var audit_ids=Qm.getValuesByName("audit_id");
	        	   if(create_mans!="<%=user%>" && project_heads!="<%=user%>"&&'<%=roles.get("402883a05939b237015939d3838d163f")%>'=='null'){
	        		   flag3=false;
	        	   }
	        	   for(var i = 0 ; i <status.length ;i++){
	           		if(status[i]!="0"){
	           			flag1=true; 
	           			flag=false;
	           		}
	           	}
		            Qm.setTbar({ 
		          	detail: count==1,
		          	alteration: count==1,
		          	cancellation : count>0,
		          	modify:count==1&&flag&&flag3,
		          	del:count>0&&flag&&flag3,
		          	sub:count>0&&flag&&flag3,
		          	remark:count==1,
		          	ok:count>0&&flag1,
		          	distribution:count>0&&flag2
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	 var fontColor="black";
	        	 if (rowData.status=='1'){
	        		 fontColor="blue";
	        	 }
	        	
	        	 if(rowData.status=='2'){
	        		 fontColor="green";
	        	 }
	        	
	        	 return "style='color:"+fontColor+"'";
	 				}

}
}
		
		
		
		function ok(){
		var url='url:app/fwxm/scientific/province/scientific_audit_check.jsp?pageStatus=add&id='+Qm.getValuesByName("id");
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
	         content: url
          });
	  /*  $.ligerDialog.confirm('确认审批？', function (yes){
        if(!yes){return false;}
        top.$.ajax({
                     url: "tjy2ScientificResearchAction/updateConfirm.do?id="+id,
                     type: "GET",
                     dataType:'json',
                     async: false,
                     success:function (data) {
                        if(data.success){
                            $.ligerDialog.success("提交成功！");
                            Qm.refreshGrid();//刷新
                        }else{
                            $.ligerDialog.warn(data.msg);
                        }
                     },
                     error:function () {
                         $.ligerDialog.warn("提交失败！");
                     }
                 });
    }); */
         }
		function del(){
			      $.del("确定删除?", "/com/tjy2/scientificProvince/deleteBase.do", {
			       	"id" : Qm.getValuesByName("id").toString()
			      })
		}
		
		function detail(){
	         top.$.dialog({
		         width: 1000,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/fwxm/scientific/province/province_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
	         
        }
		function add(){
			top.$.dialog({
		         width: 1000,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增",
		         content: 'url:app/fwxm/scientific/province/province_detail.jsp?pageStatus=add&type=sq'
	          });
		}
        function modify(){
        	top.$.dialog({
		         width: 1000,
		         height: 500 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改",
		         content: 'url:app/fwxm/scientific/province/province_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&type=sq"
	          });
        	
        }
        function remark(){
        	top.$.dialog({
		         width: 500,
		         height: 400 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "审核说明",
		         content: 'url:app/fwxm/scientific/scientific_remark.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
        	
        }
       function sub(){
    	   //判断值是否完整
    	  /*  $.ajax({
				url : "tjy2ScientificResearchAction/getScientific.do?id="+Qm.getValuesByName("id"),
				type : "POST",
				success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						if(data.flag){
						
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
			}); */
    		//申报书填完才可以提交
			 $.ligerDialog.confirm("是否提交？",
						function(yes) {
					    if(yes){
					    	 top.$.dialog({
						         width: 300,
						         height: 200 ,
						         lock: true,
						         parent: api,
						         data: {
						       	 window: window
						         },
						         title: "选择审核人",
						         content: 'url:app/fwxm/scientific/province/province_fp.jsp?pageStatus=add&id='+Qm.getValueByName("id")
					          });
					    	
					}
				        
				});
       }
       function distribution(){
    	   top.$.dialog({
		         width: 300,
		         height: 200 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "选择审核人",
		         content: 'url:app/fwxm/scientific/scientific_detail_cg.jsp?pageStatus=add&id='+Qm.getValueByName("id")
	          });
       }
       
</script>

</head>
<body>
 <div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表项目申报书未提交，
                <font color="green">“绿色”</font>代表项目申报书审核通过。
			</div>
		</div>
	</div>
       <qm:qm pageid="scientific_pro_list">
      	<!--  若无科研项目管理权限或则项目申报查看权限，则只查看自己的相关数据 -->
     <%--   <%if(roles.get("402883a05939b237015939d3838d163f")==null && roles.get("402883a25f4dd138015f52ba0888584f")==null){ %>
       <qm:param name="create_man" value="<%=sessionUser.getName()%>" compare="="  />
       <qm:param name="project_head" value="<%=sessionUser.getName()%>" compare="=" logic="or" />
       <qm:param name="audit_id" value="<%=sessionUser.getId()%>" compare="=" logic="or" />
       <%}%>  --%>
       </qm:qm>
</body>
</html>