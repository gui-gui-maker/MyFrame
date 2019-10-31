<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.3,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 120
		},
		sp_fields : [ 
		              {name : "lx_name",compare : "like"}, 
		              {name : "lx_bh",compare : "like"}, 
		              {name : "create_name",compare : "like"}],
		tbar : [ 
		         {text : '详情',id : 'detail',icon : 'detail',click : detail},"-",
		         {text : '新增',id : 'add',icon : 'add',click : add},"-",
		         {text : '修改',id : 'modify',icon : 'modify',click : modify},"-",
				 { text:'删除', id:'del',icon:'delete', click:del}
		],
		             


		listeners : {
			rowClick : function(rowData, rowIndex) {

			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail : count == 1,
					modify : count == 1,
					del : count > 0,
				});
			}
		}
	}

	function del() {
		$.del("确定删除?", "com/tjy2/goodsType/deleteByIds.do", {
			"ids" : Qm.getValuesByName("id").toString()
		})
	}

	function detail() {
		top.$.dialog({
			width : 600,
			height : 300,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "详情",
					content : 'url:app/supplies/goodsType_detail.jsp?pageStatus=detail&id='
							+ Qm.getValueByName("id")
				});

	}
	function add() {
		top.$.dialog({
					width : 600,
					height : 300,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "新增物品类型",
					content : 'url:app/supplies/goodsType_detail.jsp?pageStatus=add'
				});

	}
	function modify() {
		top.$.dialog({
			width : 600,
			height : 300,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "修改物品类型",
					content : 'url:app/supplies/goodsType_detail.jsp?pageStatus=modify&id='
							+ Qm.getValueByName("id") + "&status=2"
				});

	}
</script>

</head>
<body>
	<div class="lb_gys_list" id="titleElement">
		<qm:qm pageid="ch_goodsType_list">
		</qm:qm>
	</div>
</body>
</html>