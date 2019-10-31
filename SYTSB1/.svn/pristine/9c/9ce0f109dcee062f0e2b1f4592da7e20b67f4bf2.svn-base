<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
    var userId="<%=userid%>";
 	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float		
		sp_fields: [ 
			{name: "update_no",compare: "like"},
			{name: "file_name_no",compare: "like"},
			{name: "handle_status",compare: "like"}
		],
		tbar: [ { text: '文件详情', id: 'detail', icon: 'detail', click: detail}, "-", 
		        { text: '审核', id: 'audit', icon: 'check', click: audit}, "-", 
		        { text: '审批', id: 'sign', icon: 'check', click: sign}, "-", 
				{ text: '修改', id: 'edit', icon: 'modify', click: edit},"-",
				{ text: '完成修改', id: 'finishEdit', icon: 'check', click: finishEdit}
				],
		//<tbar:toolBar type="tbar"></tbar:toolBar>,
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.handle_status=='审核') 
                {
                    return "style='color:blue'";
                }
                if(rowData.handle_status=='批准') 
                {
                    return "style='color:purple'";
                }
                if(rowData.handle_status=='不通过') 
                {
                    return "style='color:red'";
                }
                if(rowData.handle_status=='通过')
                {
                    return "style='color:green'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				var flag=true;
				var handle_status = Qm.getValuesByName("handle_status");
		   		for(var i = 0 ; i <handle_status.length ;i++){
		    		if(handle_status[i]!= handle_status[0]){
		    			flag = false ;
		    			break;
		    		}
		    	}
		   		var edit_status=true;
	   			for(var i = 0 ; i <handle_status.length ;i++){
		    		if(handle_status[i]!="通过"){
		    			edit_status = false ;
		    			break;
		    		}
		    	}
	   			var as=true;
	   			for(var i = 0 ; i <handle_status.length ;i++){
		    		if(handle_status[i]=="不通过"){
		    			as = false ;
		    			break;
		    		}
		    	}
	   			var aduit_status=true;
	   			for(var i = 0 ; i <handle_status.length ;i++){
		    		if(handle_status[i]!="审核"){
		    			aduit_status = false ;
		    			break;
		    		}
		    	}
	   			var sign_status=true;
	   			for(var i = 0 ; i <handle_status.length ;i++){
		    		if(handle_status[i]!="批准"){
		    			sign_status = false ;
		    			break;
		    		}
		    	}
	   			var handle_id = Qm.getValuesByName("handle_id");
	   			for(var i = 0 ; i <handle_id.length ;i++){
		    		if(handle_id[i]!=userId){
		    			sign_status = false ;
		    			aduit_status = false
		    			break;
		    		}
		    	}
				Qm.setTbar({
					detail: count==1,
					audit: count>0&&flag&&aduit_status,
					sign: count>0&&flag&&sign_status,
					edit: count==1&&edit_status,
					finishEdit: count>0&&edit_status
				});
			}
		}
	};
	
	function audit() {
		var id = Qm.getValueByName("id");

		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/qualitymanage/qualityFiles_audit_detail.jsp?id=' + id + '&step=audit&pageStatus=modify'
		});
		/* var ids = Qm.getValuesByName("id");
		top.$.dialog({
			width: 360,
			height: 250,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/qualitymanage/qualityFiles_choose_opinion.jsp?pageStatus=add&step=audit&ids='+ids
		}); */
	}
	function sign() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审批",
			content: 'url:app/qualitymanage/qualityFiles_audit_detail.jsp?id=' + id + '&step=sign&pageStatus=modify'
		});
		/* var ids = Qm.getValuesByName("id");
		top.$.dialog({
			width: 360,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "签发",
			content: 'url:app/qualitymanage/qualityFiles_choose_opinion.jsp?pageStatus=add&step=sign&ids='+ids
		}); */
	}

	

	function detail() {
		var fk_qfiles_new_id = Qm.getValueByName("fk_qfiles_new_id");
		var fk_qfiles_old_id = Qm.getValueByName("fk_qfiles_old_id");
		var id;
		if(fk_qfiles_new_id!="undefined"&&fk_qfiles_new_id!=""&&fk_qfiles_new_id!=null){
			id = fk_qfiles_new_id;
		}else{
			id = fk_qfiles_old_id;
		}
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/qualityFiles_modify_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function edit() {
		var fk_qfiles_new_id = Qm.getValueByName("fk_qfiles_new_id");
		var u_id = Qm.getValueByName("id");
		if(fk_qfiles_new_id!=""){
			top.$.dialog({
				width: 850,
				height: 600,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "修改",
				content: 'url:app/qualitymanage/qualityFiles_update_detail.jsp?id=' + fk_qfiles_new_id + '&pageStatus=modify&step=1&u_id='+u_id
			});
		}else{
			var fk_qfiles_old_id = Qm.getValueByName("fk_qfiles_old_id");
			top.$.dialog({
				width: 850,
				height: 600,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "修改",
				content: 'url:app/qualitymanage/qualityFiles_update_detail.jsp?id=' + fk_qfiles_old_id + '&pageStatus=modify&step=0&u_id='+u_id
			});
		}
		
		/* top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/tzsb/qualityfile/qualityFilesUpdate_detail.jsp?pageStatus=midify&id='+id
		}); */
	}
	function finishEdit(){
		var ids = Qm.getValuesByName("id");
		$.getJSON ("qualityFilesUpdateAction/finishuUpdateFiles.do?ids="+ids,function(res){
			 if(res.success){
				 top.$.notice("保存成功！");
				 Qm.refreshGrid();			
			 }
		 });
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表审核;
				<font color="purple">“紫色”</font>代表批准;
				<font color="green">“绿色”</font>代表通过;
				<font color="red">“红色”</font>代表不通过。
				
			</div>
		</div>
	</div>
	<qm:qm pageid="quality_files_update" script="false" singleSelect="false">
    	<qm:param name="handle_id" value="<%=userid %>" compare="=" logic="or"/>
    	<qm:param name="registrant" value="<%=userid %>" compare="=" logic="or"/>
    </qm:qm>
</body>
</html>