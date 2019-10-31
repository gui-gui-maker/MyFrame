<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>作废</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
			         {group:[
			         	{name:'invoice_code',compare:'>='},
						{label:'到',name:'invoice_code', compare:'<=',labelAlign:"center",labelWidth:20}
			         ]}
			         ],
			         tbar:[{
							text: '详情', id: 'detail', icon: 'detail', click: detail
						  }
						],
						listeners:{
							rowClick:function(rowData,rowIndex){
							},
							rowDblClick:function(rowDate,rowIndex){
								Qm.getQmgrid().selectRange("id", [rowData.id]);
								detail();
							},
							selectionChange:function(rowDate,rowIndex){
								var count = Qm.getSelectedCount();
								Qm.setTbar({
									detail:count==1
								});
							}
						}
					};
	
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 700,
			height : 300,
			lock : true,
			title : "业务详情",
			content : 'url:app/finance/cwInvoice_lead_datail1.jsp?pageStatus=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}

</script>

</head>
<body>
	<qm:qm pageid="TJY2_CW_PGZF" script="false" singleSelect="false"></qm:qm>
</body>
</html>