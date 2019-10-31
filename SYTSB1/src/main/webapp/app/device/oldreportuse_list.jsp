<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>机构查询列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	User user = (User)SecurityUtil.getSecurityUser().getSysUser();
	String userId = user.getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
				{name:"report_name", id:"report_name", compare:"like"}
        ],
        tbar:[
            { text:'详情', id:'detail',icon:'detail', click: detail},
            "-",
	        { text:'启用', id:'enable',icon:'add',click:enable},
	        "-",
			{ text:'停用', id:'disable',icon:'delete', click: disable}
        ],
        listeners: {
            selectionChange : function(rowData,rowIndex){
            	var count=Qm.getSelectedCount();//行选择个数
                Qm.setTbar({detail:count==1,enable:count>0,disable:count>0});
            },
	
            rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if (rowData.flag == '0'){
	            	fontColor="red";
	            }
	            if (rowData.flag == '1'){
	            	fontColor="blue";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};
	
	
	
	function close(){
		api.close();
	}
//启用
	function enable(){
	        $.ligerDialog.confirm('确定启用？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: "report/basic/enable.do?ids=" + Qm.getValuesByName("id").toString(),
	                 type: "post",
	                 async: false,
	                 success:function (data) {
	                    if(data.success){
	                    	top.$.notice("操作成功！");
							Qm.refreshGrid();
	                    }else{
	                    	top.$.notice("操作失败！");
	                    }
	                 },
	                 error:function () {
	                     //$.ligerDialog.warn("提交失败！");
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
	}
	
	//报废
    function disable(){
        $.ligerDialog.confirm('确定停用？', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "report/basic/disable.do?ids=" + Qm.getValuesByName("id").toString(),
                 type: "post",
                 async: false,
                 success:function (data) {
                    if(data.success){
                    	top.$.notice("操作成功！");
						Qm.refreshGrid();
                    }else{
                    	top.$.notice("操作失败！");
                    }
                 },
                 error:function () {
                     //$.ligerDialog.warn("提交失败！");
                	 $.ligerDialog.error("出错了！请重试！！！");
                 }
             });
        });
}
//	停用
	/* function disable(){
		alert(1);
		  $.disable("确定停用?",
				  "report/basic/disable.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    } */
	
    function detail(){		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"报告信息",
			content: 'url:app/report/report_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	
	
	
	
	
	

	
	
	</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：状态
               <font color="blue">“蓝色”</font>代表启用；
					<font color="red">“红色”</font>代表停用；
			</div>
		</div>
	</div>
	<qm:qm pageid="report_flagchange" script="false" singleSelect="false"   >
	

	</qm:qm>
	
	
</body>
</html>