<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
 var navtab ;
$(function(){
	var url={
		"table" : 'pub/form/table/table_detailtable.jsp?pageStatus='+$("#pageStatus").val()+'&tableId='+$("#tableId").val(),
		"column" : 'pub/form/table/table_detailcolumn.jsp?pageStatus='+$("#pageStatus").val()+'&tableId='+$("#tableId").val()
	}
	navtab=$(".navtab").ligerTab({
		height: "100%", onBeforeSelectTabItem: function (tabid)
            {
            
	            if(tabid=="column")
				{
			
					if($("#tableId").val()=="")
					{
					setTab();
					return false;
					}else{
					   url[tabid]='pub/form/table/table_detailcolumn.jsp?pageStatus='+$("#pageStatus").val()+'&tableId='+$("#tableId").val();
					}
				}
            },
		onAfterSelectTabItem:function(tabId){
			if($("#"+tabId).attr("src")==""){
			if(tabId=="table")
			{
			  url[tabId]='pub/form/table/table_detailtable.jsp?pageStatus='+$("#pageStatus").val()+'&tableId='+$("#tableId").val();
			}else if(tabId=="column")
			{
			 url[tabId]='pub/form/table/table_detailcolumn.jsp?pageStatus='+$("#pageStatus").val()+'&tableId='+$("#tableId").val();
			}
				$("#"+tabId).attr("src",url[tabId]);
			}
		}
	});
	$("#table").attr("src",url.table);


});
function gotoColumn(id,pageStatus)
{
   $("#tableId").val(id);
   $("#pageStatus").val("modify");
	api.data.window.Qm.refreshGrid();
}
function setTab()
{
$.ligerDialog.error('请先保存表单信息！');
navtab.selectTabItem("table");
}
</script>
	</head>
	<body>
		<div class="navtab">
			<div title="表信息" tabId="table">
				<iframe id="table" src="" height="100%"></iframe>
			</div>
			<div title="列信息" tabid="column">
				<iframe id="column" src="" height="100%"></iframe>
			</div>

			<input type="hidden" id="tableId" name="tableId" value="${param.id}" />
			<input type="hidden" id="pageStatus" name="pageStatus"
				value="${param.pageStatus}" />
		</div>
	</body>
</html>