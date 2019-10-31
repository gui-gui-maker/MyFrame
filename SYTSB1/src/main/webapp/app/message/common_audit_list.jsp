<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head pageStatus="${param.status}">
	<%-- <% CurrentSessionUser user = SecurityUtil.getSecurityUser();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
	%> --%>
	<link href="app/message/css/mui.min.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="app/message/js/mui.min.js"></script>
	<script type="text/javascript" src="app/message/js/mui.pullToRefresh.js"></script>
	<script type="text/javascript" src="app/message/js/mui.pullToRefresh.material.js"></script>
	<script type="text/javascript">
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	        getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error(response.msg);
	      		}
			}
		});

	});	
	
	function close(){
		api.close();
	}	
</script>
<style type="text/css">
/* div {   
   width: 200px;   
 }    */
             
 h2 {   
   font: 400 40px/1.5 Helvetica, Verdana, sans-serif;   
   margin: 0;   
   padding: 0;   
 }   
             
 ul {   
   list-style-type: none;   
   margin: 0;   
   padding: 0;   
 }   
             
 li {   
   font: 200 20px/1.5 Helvetica, Verdana, sans-serif;   
   border-bottom: 1px solid #ccc;   
 }   
             
 li:last-child {   
   border: none;   
 }   
             
 li {   
   text-decoration: none;   
   color: #000;   
   display: block;   
   /* width: 200px;    */
             
   -webkit-transition: font-size 0.3s ease, background-color 0.3s ease;   
   -moz-transition: font-size 0.3s ease, background-color 0.3s ease;   
   -o-transition: font-size 0.3s ease, background-color 0.3s ease;   
   -ms-transition: font-size 0.3s ease, background-color 0.3s ease;   
   transition: font-size 0.3s ease, background-color 0.3s ease;   
 }   
             
 li:hover {   
   font-size: 30px;   
   background: #f6f6f6;   
 }  
 
</style>
</head>
	<body>
		<form name="formObj" id="formObj" method="post" action="messageContentModAction/save.do"
			getAction="messageContentModAction/detail.do?id=${param.id}">
			<div>   
   <h2>HelvetiList</h2>   
   <ul>   
  				<li class="mui-table-view-cell mui-checkbox mui-left"><input name="checkbox" type="checkbox" value="1"/> 
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis">信息化推进办公室张彦合同付款信息化</h4>
		                    <h5>申请人：李四</h5>
		                    <p class="mui-h6 mui-ellipsis">Hi，李明明，申请交行信息卡，100元等你拿，李明明，申请交行信息卡，100元等你拿，</p>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-h5">12:25</span>
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell mui-checkbox mui-left"><input name="checkbox" type="checkbox" value="2"/> 
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-2">信息化推进办公室张彦合同付款信息化推进办公室张彦合同付款信息化推进办公室张彦合同付款</h4>
		                    <h5>申请人：李四</h5>
		                    <p class="mui-h6 mui-ellipsis">Hi，李明明，申请交行信息卡，100元等你拿，李明明，申请交行信息卡，100元等你拿，</p>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-h5">12:25</span>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell mui-checkbox mui-left"><input name="checkbox" type="checkbox" value="3"/> 
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-2">信息化推进办公室张彦合同付款信息化推进办公室张彦合同付款信息化推进办公室张彦合同付款</h4>
		                    <h5>申请人：李四</h5>
		                    <p class="mui-h6 mui-ellipsis">Hi，李明明，申请交行信息卡，100元等你拿，李明明，申请交行信息卡，100元等你拿，</p>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-h5">12:25</span>
		                    
		                </div>
		            </div>
		        </li><!--   <li><input name="checkbox" type="checkbox" value="1"/> Zurich </li>   
     <li><input name="checkbox" type="checkbox" value="2"/> Geneva </li>   
     <li><input name="checkbox" type="checkbox" value="3"/> Winterthur </li>   
     <li><input name="checkbox" type="checkbox" value="4"/>Lausanne </li>   
     <li><input name="checkbox" type="checkbox" value="5"/> Lucerne </li>    -->
   </ul>   
 </div> 
			
		</form>
	</body>
</html>