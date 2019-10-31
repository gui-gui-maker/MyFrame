<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
String userId=e.getId();
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
String nowTime=""; 
nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义 
		sp_fields:[
					{name: "work_content", compare: "like"},
					 {name: "work_status", compare: "like"},
				     {group:[
								{name:"create_date", compare:">=", value:""},
								{label:"到", name:"create_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
							]}	
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		},
		{
			text: '新增', id: 'add', icon: 'add', click: add
		},
		{
			text: '修改', id: 'modify', icon: 'modify', click: modify
		},
		{
			text: '删除', id: 'del', icon: 'del', click: del
		},
		{
			text: '标记完成', id: 'signFinish', icon: 'check', click: signFinish
		},
		{
			text: '重启记事', id: 'restart', icon: 'back', click: restart
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
		         var work_status = Qm.getValuesByName("work_status");
		         var allowEdit =  true;
		         var restart = true;
		         for(var i=0;i<work_status.length;i++){
		        	 if(work_status[i]=="已完成"){
		        		 allowEdit =  false;
		        	 }else if(work_status[i]=="未开始"||work_status[i]=="进行中"){
		        		 var restart = false;
		        	 }
		         }
		         Qm.setTbar({
		          	detail: count==1,
		          	modify:count==1&&allowEdit,
		          	del:count>0,
		          	signFinish:count>0&&allowEdit,
		          	restart:count>0&&restart
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
	        	var fontColor="black";
	            if(rowData.work_status=='已完成'){
	           	 fontColor="green";
	            }else if(rowData.work_status=='进行中'){
	           	 fontColor="blue";
	            }
	            return "style='color:"+fontColor+"'";
				}
		}
}
		
function detail(){
    top.$.dialog({
        width: 700,
        height: 800,
        lock: true,
        parent: api,
        data: {
      	 window: window
        },
        title: "详情",
        content: 'url:app/office/office_ownnote_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
     });
}
function add(){
	top.$.dialog({
         width: 700,
         height: 800,
         lock: true,
         parent: api,
         data: {
       	 window: window
         },
         title: "新增",
         content: 'url:app/office/office_ownnote_detail.jsp?pageStatus=add'
      });
}
function modify(){
	top.$.dialog({
	       width: 700,
	       height: 800,
	       lock: true,
	       parent: api,
	       data: {
	     	 window: window
	       },
	       title: "修改",
	       content: 'url:app/office/office_ownnote_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
	    });

}
function del(){
	$.del("删除后将不能恢复，确定删除?",
	  		"officeOwnnoteAction/delete.do",
	  		{"ids":Qm.getValuesByName("id").toString()});
}
function signFinish(){
	$.ligerDialog.confirm('是否标记完成？', function (yes){
		if(!yes){return false;}
		$.ajax({
	    	url: "officeOwnnoteAction/signFinish.do?ids="+Qm.getValuesByName("id").toString(),
	        type: "POST",
	        datatype: "json",
	        contentType: "application/json; charset=utf-8",
	        success: function (resp) {
	        	$.ligerDialog.alert(resp.data);
	            Qm.refreshGrid();
	        },
	        error: function (resp) {
	        	$.ligerDialog.alert(resp.data);
	        }
	    });
	});
  }
function restart(){
	$.ligerDialog.confirm('是否重启记事？', function (yes){
		if(!yes){return false;}
		$.ajax({
	    	url: "officeOwnnoteAction/restart.do?ids="+Qm.getValuesByName("id").toString(),
	        type: "POST",
	        datatype: "json",
	        contentType: "application/json; charset=utf-8",
	        success: function (resp) {
	        	$.ligerDialog.alert(resp.data);
	            Qm.refreshGrid();
	        },
	        error: function (resp) {
	        	$.ligerDialog.alert(resp.data);
	        }
	    });
	});
  }
//刷新Grid
function refreshGrid() {
    Qm.refreshGrid();
}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未开始，
                <font color="blue">“蓝色”</font>代表进行中，
                <font color="green">“绿色”</font>代表已完成。
            </div>
        </div>
    </div>
       <qm:qm pageid="TJY2_OFFICE_OWNNOTE">
       		<sec:authorize access="!hasRole('sys_administrate')">
       			<qm:param name="create_id" value="<%=uu.getId()%>" compare="=" />
       		</sec:authorize>
       </qm:qm>
</body>
</html>