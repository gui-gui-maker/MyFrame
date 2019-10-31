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
			{name: "qrcode",compare: "like"}, 
			{name: "name",compare: "like"}, 
			{group: [
						{name: "last_op_time", compare: ">="},
						{label: "到", name: "last_op_time", compare: "<=", labelAlign: "center", labelWidth:20}
					]}, 
			{name: "category",compare: "="},
			{name: "content",compare: "="},
			{name: "status",compare: "="}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '上架', id: 'stack', icon: 'appear', click: stack
		}, "-", {
			text: '下架', id: 'unstack', icon: 'appropriation', click: unstack
		},/*  "-", {
			text: '作废', id: 'invalid', icon: 'modify', click: invalid
		}, */"-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		},"-", {
			text: '打印二维码', id: 'prints', icon: 'print', click: prints
		}],
		//<tbar:toolBar type="tbar"></tbar:toolBar>, //若无按钮，请删除此行
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
					del: count>0,
					prints : count>0
				});
			}
		}
	};
	//批量打印标签
    function prints(){
    	var rows = Qm.getQmgrid().getSelecteds();
		for(i=0;i<rows.length;i++){
			var codeValue = rows[i].qrcode;//条码
    		var bookName = rows[i].name;//条码
    		var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    		
    		LODOP.ADD_PRINT_BARCODE('-1mm','-1mm','20mm','20mm',"QRCode",codeValue);
    		
			LODOP.ADD_PRINT_TEXT('2mm','22mm','36mm','5mm',codeValue);
     	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
     		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
     		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
     		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
     		
			LODOP.ADD_PRINT_TEXT('7mm','22mm','36mm','15mm',bookName);
     	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
     		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
     		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
     		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
     		
            LODOP.PRINT();
		}
    }
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
			content: 'url:app/fwxm/library/book_detail.jsp?pageStatus=add&identifier=1'
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
			content: 'url:app/fwxm/library/book_detail.jsp?id=' + id + '&pageStatus=modify&identifier=1'
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
			content: 'url:app/fwxm/library/book_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG, "lib/book/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	//上架
	function stack() {
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window,
				bookIds:Qm.getValuesByName("id")
			},
			title: "上架",
			content: 'url:app/fwxm/library/book_stack.jsp?pageStatus=add'
		});
	}
	//下架
	function unstack(){
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window,
				bookIds:Qm.getValuesByName("id")
			},
			title: "下架",
			content: 'url:app/fwxm/library/book_undercarriage.jsp?pageStatus=add'
		});
	}
	
	//作废
	function invalid(){
		
	}

</script>
</head>
<body>
	<qm:qm pageid="lib_book" script="false" singleSelect="false">
    	 <qm:param name="identifier" compare="=" value="1"/>
    </qm:qm>
</body>
</html>