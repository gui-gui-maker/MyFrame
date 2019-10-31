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
		              {label:"供应商",name : "project_name",compare : "like"}, 
		              {label:"入库单号",name : "project_department",compare : "like"}],
		tbar : [ 
		         {text : '详情',id : 'detail',icon : 'detail',click : detail},"-",
		         {text : '新增退货单',id : 'add',icon : 'add',click : addclick},"-",
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
	        	var state=Qm.getValuesByName("state");
				Qm.setTbar({
					detail : count == 1,
					modify : count == 1,
					del : count > 0,
				});
				if(count>0){
					//所选数据包含已出库和未出库，，，或全部是已出库
					if(($.inArray("已出库", state)>-1 && 	$.inArray("未出库", state)>-1) || ($.inArray("未出库", state)==-1)){
			   			Qm.setTbar({
							modify : false,
							del : false
						});
			   		}
				}
			},rowAttrRender:function(rowData, rowid) {
				 var fontColor="black";
				 if(rowData.state=='已出库'){
					 fontColor="green";
				 }
				 return "style='color:"+fontColor+"'";
			}
		}
	}

	function del() {
		$.del("确定删除?", "com/tjy2/goodsReturn/deleteGoodsReturn.do", {
			"id" : Qm.getValuesByName("id").toString()
		})
	}
	function detail() {
		top.$.dialog({
					width : 1100,
					height : 600,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "详情",
					content : 'url:app/supplies/out/return_detail.jsp?pageStatus=detail&id='
							+ Qm.getValueByName("id")
				});

	}
	function addclick() {
		top.$.dialog({
			width : 1100,
			height : 600,
			lock : true,
			parent : api,
			data : {
				window : window
			},
			title : "退货",
			content : 'url:app/supplies/out/return_detail.jsp?pageStatus=add'
		});

	}
	function modify() {
		top.$.dialog({
			width : 1100,
			height : 600,
			lock : true,
			parent : api,
			data : {
				window : window
			},
			title : "修改",
			content : 'url:app/supplies/out/return_detail.jsp?pageStatus=modify&id='
				+ Qm.getValueByName("id")
		});
		
	}
</script>

</head>
<body>
 <div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表未出库，
                <font color="green">“绿色”</font>代表已出库。
			</div>
		</div>
	</div>
	<div class="lb_gys_list" id="titleElement">
		<qm:qm pageid="ch_th_list">
		</qm:qm>
	</div>
</body>
</html>