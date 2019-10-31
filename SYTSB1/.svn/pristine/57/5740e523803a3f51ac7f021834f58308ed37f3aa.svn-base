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
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right'}, //可以自己定义 layout:column,float,labelSeparator,labelWidth
		sp_fields: [ 
			{name: "book_name",compare: "like"}, 
			{name: "borrowed_man",compare: "="}, 
			{group: [
				{name: "borrowed_time", compare: ">="},
				{label: "到", name: "int_val", compare: "<=", labelAlign: "center", labelWidth:20}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '借阅', id: 'add', icon: 'add', click: borrow
		}, "-", {
			text: '续借', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '归还', id: 'back', icon: 'give-back', click: back
		}/* , "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		} */],
		listeners: {
			rowClick: function(rowData, rowIndex) {
				
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
				//alert("归还人："+rowData.returned_man+" typeof returned_man:" + typeof rowData.returned_man);
                if(rowData.returned_man != "") // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.isexceed=='cq') // 记录为红色
                {
                    return "style='color:red'";
                }
                if(rowData.isexceed=='wc') // 记录为绿色
                {
                    return "style='color:black'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count>0/* ,
					del: count>0 */
				});
			}
		}
	};
	
	

	function edit() {
		var limit = Qm.getValuesByName("reborrow_time_limit");
		for(var i in limit){
			if(""!=limit[i]){
				top.$.dialog.notice({content:'所选书籍中有已经续借过的，请重新选择！！'});
				return;
			}
		}
		var returned = Qm.getValuesByName("returned_time");
		for(var i in returned){
			if(""!=returned[i]){
				top.$.dialog.notice({content:'所选书籍中有已经归还过的，请重新选择！！'});
				return;
			}
		}
		var id = Qm.getValuesByName("id");
		top.$.dialog({
			width: 600,
			height: 150,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "续借",
			content: 'url:app/fwxm/library/borrow_continue.jsp?ids=' + id + '&pageStatus=modify'
		});
	}

	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/fwxm/library/borrow_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG, "lib/borrow/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	//借出
	function borrow(){
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window/* ,
				bookIds:Qm.getValuesByName("book_id") */
			},
			title: "借阅",
			content: 'url:app/fwxm/library/borrow.jsp?pageStatus=add'
		});
	}
	//归还
	function back(){
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window,
				bookIds:Qm.getValuesByName("book_id")
			},
			title: "归还",
			content: 'url:app/fwxm/library/borrow_back.jsp?pageStatus=add'
		});	
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
	        <div class="l-page-title-note">提示：列表数据项
	            <font color="black">“黑色”</font>代表正常，
	            <font color="green">"绿色"</font>代表已归还，
	            <font color="red">“红色”</font>代表借阅超期。
	        </div>
	    </div>
	</div>
	<qm:qm pageid="lib_borrow" script="false" singleSelect="false">
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>