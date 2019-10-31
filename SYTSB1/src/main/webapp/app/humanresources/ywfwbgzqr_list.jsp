<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();

String uId = SecurityUtil.getSecurityUser().getId();
%>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		 sp_fields:[
				     {name: "name", compare: "like"},
				     {name: "department", compare: "like"},
				     {name: "education", compare: "="},
				     {name: "jobs", compare: "="},
				     {name: "professional", compare: "="},
				     {name: "yes_no", compare: "="}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}<sec:authorize access="hasRole('TJY2_RL_GJJ')">, "-",{
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-",{
			text: '修改', id: 'modify', icon: 'modify', click: modify
		}, "-",{
			text: '删除', id: 'del', icon: 'del', click: del
		}</sec:authorize>, "-",{
			text: '确认', id: 'submit', icon: 'submit', click: submit
		}
		],
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
		         var count = Qm.getSelectedCount();
		         var yes_no = Qm.getValueByName("yes_no");
		         if(yes_no=="未确认"){
		         Qm.setTbar({
		          	detail: count==1,
		          	modify:count==1,
		          	del:count>0,
		          	submit:count==1
		       });
		         }else{
		         Qm.setTbar({
			          	detail: count==1,
			          	modify:count==1,
			          	del:count>0,
			          	submit:count>0
			       });
		         }
		         
	        },
	        rowAttrRender : function(rowData, rowid) {
	            console.log(rowData);
	            var fontColor="black";
	            if(rowData.yes_no=='未确认'){
	           	 fontColor="#8E8E8E";
	            }
	            if((rowData.total==""||rowData.total==null)&&rowData.yes_no==='0'){
	            	fontColor="red";
	            }

                // 离职标示为橙色
	            if(rowData.data_status === '98') {
	                fontColor = 'orange';
                }
                // 关键字段修改后未确认标示为蓝色
                if(rowData.data_status === '97' && rowData.yes_no==='0') {
                    fontColor = 'blue';
                }
	            return "style='color:"+fontColor+"'";
	        }
}
}

function submit(){
	
	 var id = Qm.getValueByName("id");
	 
     $.ligerDialog.confirm('是否要确认？', function (yes){
     	if(!yes){return false;}
         top.$.ajax({
              url: "tjy2YwfwbgzqrbAction/submit.do?Id="+id,
              type: "GET",
              dataType:'json',
              async: false,
              success:function (data) {
                 if(data.success){
                   //  $.ligerDialog.success("提交成功！");
                    top.$.notice('确认成功！！！',3);
                     Qm.refreshGrid();//刷新
                 }else{
                     $.ligerDialog.warn("出错了！请重试！!");
                 }
              },
              error:function () {
                  //$.ligerDialog.warn("提交失败！");
             	 $.ligerDialog.error("出错了！请重试！！！");
              }
          });
     });
	
	
	
}
		
		function modify(){
	 top.$.dialog({
         width: 600,
         height: 300,
         lock: true,
         parent: api,
         data: {
       	 window: window
         },
         title: "修改",
         content: 'url:app/humanresources/ywfwbgzqr_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
      });
	
       }
		function add(){
			top.$.dialog({
		         width: 600,
		         height: 300,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增",
		         content: 'url:app/humanresources/ywfwbgzqr_detail.jsp?pageStatus=add&id='+Qm.getValueByName("id")
		      });
		}
		function del() {
			 var id = Qm.getValueByName("id");
		        $.ligerDialog.confirm('是否要确认删除？', function (yes){
		        	if(!yes){return false;}
		            top.$.ajax({
		                 url: "tjy2YwfwbgzqrbAction/delete.do?id="+id,
		                 type: "GET",
		                 dataType:'json',
		                 async: false,
		                 success:function (data) {
		                    if(data.success){
		                       top.$.notice('删除成功！！！',3);
		                        Qm.refreshGrid();//刷新
		                    }else{
		                        $.ligerDialog.warn("出错了！请重试！!");
		                    }
		                 },
		                 error:function () {
		                     //$.ligerDialog.warn("提交失败！");
		                	 $.ligerDialog.error("出错了！请重试！！！");
		                 }
		             });
		        });
		}
		function detail(){
	         top.$.dialog({
		         width: 600,
		         height: 300,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/humanresources/ywfwbgzqr_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
        }
</script>

</head>
<body>
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表本人已确认，
                <font color="#8E8E8E">“灰色”</font>代表本人未确认。
                <font color="red">“红色”</font>代表新增未修改数据。
                <font color="blue">“蓝色”</font>代表新关键字段值被修改数据。
                <font color="orange">“橙色”</font>代表离职人员数据。
            </div>
        </div>
    </div>

        <qm:qm pageid="TJY2_YWFWBGZQRB " singleSelect="true">
       <sec:authorize access="!hasRole('TJY2_RL_GJJ')">
		<qm:param name="name_id" value="<%=userId%>" compare="=" />
		</sec:authorize>
            <qm:param name="data_status" value="1" compare="=" />
            <%--增加展示离职人员工资数据--%>
            <qm:param name="data_status" value="98" compare="=" logic="or" />
            <%--增加展示关键字段被修改的人员工资数据--%>
            <qm:param name="data_status" value="97" compare="=" logic="or"/>
       </qm:qm>
	<script type="text/javascript">
		Qm.config.columnsInfo['yes_no']['binddata'] = [ {id: '0', text: '未确认'}, {id: '1', text: '已确认'} ];
	</script>
</body>
</html>