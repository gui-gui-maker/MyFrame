<%@page import="com.khnt.rbac.bean.Org"%>
<%@page import="com.khnt.rbac.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
User user = SecurityUtil.getSecurityUser().getSysUser();
Org o = user.getOrg();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
var pageStatus='${param.pageStatus}';
var g;
var categories = <u:dict code="lib_category"/>;
var categories2 = <u:dict code="lib_category2"/>;
var bookStatus = <u:dict code="book_status"/>;
	$(function () {
		initGrid();
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			},
			{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function close(){	
						 	api.close();
						}
			}],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			getSuccess:function(res){
				if(res.success){
					if(res.data.books.length>0){
						addBook(res.data.books);
					}
				}
			},
			afterParse : function(){
				if("add"=="${param.pageStatus}"){
					$("#applier").ligerGetComboBoxManager().setValue('<%=user.getId()%>');
					$("#receiveOrg").ligerGetComboBoxManager().setValue('<%=o.getId()%>');
				}
				$("#applier").ligerGetComboBoxManager().setDisabled();
				$("#receiveOrg").ligerGetComboBoxManager().setDisabled();
				$("#receiveOrgLeader").ligerGetComboBoxManager().setDisabled();
				$("#status").ligerGetComboBoxManager().setDisabled();
				$("#approveBy").ligerGetComboBoxManager().setDisabled();
				$("#approveResult").ligerGetComboBoxManager().setDisabled();
				$("#approveSuggestion").ligerGetTextBoxManager().setDisabled();
			}
		});
	});
	
	//初始化表格
	function initGrid() {
		var cols = [];
		if(pageStatus!='detail'){
			cols.push({
				display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:selectBook()' title='增加'><span>增加</span></a>", 
				isSort: false, 
				minWidth:30,
				width: '5%',
				height:'5%',
				render: function (rowdata, index, value ) {
					var h = "";
					if (!rowdata._editing) {
						h += "<a class='l-a l-icon-del' href='javascript:del(g,"+index+")' title='删除'><span>删除</span></a> ";
					}
					return h;
				}
       		});
		}
		cols = cols.concat([{display : 'id', name : 'id', width : '1', hide : true}, 
				           	{display : '二维码', name : 'qrcode', width : '15%'}, 
			           		{display : '书名', name : 'name', width : '30%'},
				           	{display : '类别', name : 'category', width : '15%',
					        	   render : function(rowdata) {
					        		   for (var i = 0; i < categories.length; i++) {
											if (rowdata["category"] == categories[i].id) {
												return categories[i].text;
											}
									   }
							   }}, 
				           	{display : '类型', name : 'content', width : '10%',
					        	   render : function(rowdata) {
					        		   for (var i = 0; i < categories2.length; i++) {
											if (rowdata["content"] == categories2[i].id) {
												return categories2[i].text;
											}
									   }
								   }
						   	}, 
						   	{display : '状态',name : 'status',width : '15%',
							   		render : function(rowdata) {
					        		   	for (var i = 0; i < bookStatus.length; i++) {
											if (rowdata["status"] == bookStatus[i].id) {
												return bookStatus[i].text;
											}
									   	}
								   	}
						   	}]);
		g = $("#grid").ligerGrid({
			columns : cols,
			rownumbers : true,
			height : "320",
			width : "98%",
			frozenRownumbers : false,
			usePager : false,
			data : {Rows : []},
			onSelectRow : function(rowdata, rowid, rowobj) {

			}
		});
	}
	function addBook(books){
		var existBooks = g.getData();
		for(var i in books){
			if(!isExist(books[i],existBooks)){
				g.addRow(books[i]);
			}
		}
	}
	//判断表格中是否存在了
	function isExist(_this,exists){
		var f = false;
		for(var i in exists){
			if(exists[i].id == _this.id){
				f = true;
			}
		}
		return f;
	}
	function del(obj, index) {
		var data = obj.getSelectedRow();
		var dataId = data.id;
		$.ligerDialog.confirm("确定要移除吗？", function(yes) {
			if (yes) {
				obj.deleteRow(index);
			}
		});
	}
	function selectBook(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择资料",
            content: 'url:app/fwxm/library/book_material_available_list.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getBooks();
                if(!p){
                    top.$.notice("请选择资料！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                var ids = [];
                for(var i in p){
                	if(p[i].id){
	                	ids.push(p[i].id);
                	}
                }
                $.post("lib/book/getBooks.do",{"ids":ids.join(",")},function(res){
                	if(res.success){
		                addBook(res.data);
                	}
                });
            }
        });
    }
	function save(){
		//验证表单数据
		if($('#formObj').validate().form()){
			
			var formData = $("#formObj").getValues();
	        if(!validateGrid(g)){
	        	return false;
			}
	        if(g.getData().length==0){
	        	top.$.dialog.notice({content:'至少添加一条图书信息！'});
	        	return false;
	        }
	        formData["books"] = g.getData();
	        var  jsonString = $.ligerui.toJSON(formData);
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "lib/receive/save.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(formData),
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                $("#save").removeAttr("disabled");
	            }
	        });
		}
	}
</script>
</head>
<body>
<form id="formObj" action="lib/receive/save.do" getAction="lib/receive/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="applyTime" name="applyTime">
    <input type="hidden" id="approveTime" name="approveTime">
    <fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>图书资料领取申请</div>
		</legend>
	    <table cellpadding="3" class="l-detail-table">
	        <tr>
	            <td class="l-t-td-left">申请人：</td>
	            <td class="l-t-td-right">
	            	<input name="applier" id="applier" type="text" 
	            		ltype="select" ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}"/>
	            </td>
	            <td class="l-t-td-left">领取部门：</td>
	            <td class="l-t-td-right">
	            	<input name="receiveOrg" id="receiveOrg" type="text" 
	            		ltype="select" ligerui="{data:<u:dict sql='select id,org_name text from sys_org where 1=1'/>}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">申请理由：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<textarea name="applyReason" id="applyReason" rows="2" validate="{required:true}"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">部门负责人：</td>
	            <td class="l-t-td-right">
	            	<input name="receiveOrgLeader" id="receiveOrgLeader" type="text" 
	            	ltype="select" ligerui="{data:<u:dict sql='select id,name text from sys_user where 1=1'/>}" />
	            </td>
	            <td class="l-t-td-left">状态：</td>
	            <td class="l-t-td-right">
	            	<input name="status" id="status" type="text" 
	            		ltype="select" ligerui="{data:<u:dict code='lib_receive_status'/>}" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">审批人：</td>
	            <td class="l-t-td-right">
	            	<input name="approveBy" id="approveBy" type="text" ltype="select" ligerui="{
	            	data:<u:dict sql='select id,name text from sys_user where 1=1'/>}" />
	            </td>
	            <td class="l-t-td-left">审批结果：</td>
	            <td class="l-t-td-right">
	            	<input name="approveResult" id="approveResult" type="text" 
	            		ltype="select" ligerui="{data:[{id:'0',text:'不同意'},{id:'1',text:'同意'}]}" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">审批意见：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<textarea name="approveSuggestion" id="approveSuggestion" rows="2"></textarea>
	            </td>
	        </tr>
	    </table>
	</fieldset>
    <fieldset class="l-fieldset" style="height: 500px">
		<legend class="l-legend">图书列表 </legend>
		<div id="grid" style="height: 400px;"></div>
	</fieldset>
</form>
</body>
</html>