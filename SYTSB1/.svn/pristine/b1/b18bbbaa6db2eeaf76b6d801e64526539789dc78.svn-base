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
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right'}, //可以自己定义 layout:column,float,labelSeparator,labelWidth
		sp_fields: [ 
			{name: "code",compare: "like"}, 
			{name: "type",compare: "="}, 
			{name: "isPrint",compare: "="}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, /* "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", */ {
			text: '打印二维码', id: 'prints', icon: 'print', click: prints
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
                if(rowData.列名1=='XXX') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.列名2=='YYY') // 记录为红色
                {
                    return "style='color:red'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0
				});
			}
		}
	};
	
	function add() {
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/fwxm/library/qrcode_add_detail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/fwxm/library/qrcode_detail.jsp?id=' + id + '&pageStatus=modify'
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
			content: 'url:app/fwxm/library/qrcode_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG, "lib/qrcode/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function getSelectedRow(){
		return Qm.getQmgrid().getSelecteds();
	}
	//批量打印标签
    function prints(){
    	var rows = getSelectedRow();
		for(i=0;i<rows.length;i++){
    		var codeValue = rows[i].code;//条码
    		var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    		
    		LODOP.ADD_PRINT_BARCODE(13,13,114,114,"QRCode",codeValue);

    		LODOP.ADD_PRINT_TEXT(127,18,109,10,codeValue);
     	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
     		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
     		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
     		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
     		
            LODOP.PREVIEW();
            LODOP.PRINT();
		}
    }
</script>
</head>
<body>
	<qm:qm pageid="lib_qrcode" script="false" singleSelect="false">
		<qm:param name="is_del" value="0" compare="=" />
    </qm:qm>
</body>
</html>