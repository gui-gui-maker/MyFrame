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
			{name: "description",compare: "like"},
			{group: [
						{name: "create_time", compare: ">="},
						{label: "到", name: "create_time", compare: "<=", labelAlign: "center", labelWidth:20}
					]}
		],
		
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		},"-", {
			text: '打印二维码', id: 'prints', icon: 'print', click: prints
		}],
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
   		var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
   		//LODOP.SET_PRINT_PAGESIZE(2,600,240,"");
		for(i=0;i<rows.length;i++){
			
    		LODOP.ADD_PRINT_BARCODE('-1mm','-1mm','20mm','20mm',"QRCode",rows[i].qrcode);//条码
			LODOP.ADD_PRINT_TEXT('2mm','22mm','36mm','5mm',rows[i].qrcode);//条码
     	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
     		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
     		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
     		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
			LODOP.ADD_PRINT_TEXT('7mm','22mm','36mm','15mm',rows[i].description);
     	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
     		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
     		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
     		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
            //LODOP.PREVIEW();
            LODOP.PRINT();
		}
    }
	function add() {
		top.$.dialog({
			width: 850,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/fwxm/library/cupboard_detail.jsp?pageStatus=add'
		});
	}
	

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/fwxm/library/cupboard_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}

	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 300,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/fwxm/library/cupboard_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG+"删除后书架上的书籍位子将重置为空。", "/cupboard/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="lib_cupboard" script="false" singleSelect="false">
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>