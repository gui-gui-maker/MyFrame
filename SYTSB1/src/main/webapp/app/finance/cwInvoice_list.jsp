<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		 sp_fields:[
		           {name:'buy_name',compare:'like'},
			{group:[
			      {name:'invoice_start',compare:'>='},
			      {label:'到',name:'invoice_end', compare:'<=',labelAlign:"center",labelWidth:20}
			]},
			{group:[
				{name:"buy_date",compare:">="},
				{label:"到",name:"buy_date",compare:"<=",labelAlign:"center",labelWidth:20}
			]}
		],
			
			tbar:[{
				text: '详情', id: 'detail', icon: 'detail', click: detail
			}, "-", {
				text: '新增', id: 'add', icon: 'add', click: add 
			} , "-",{
				text: '删除', id: 'del', icon: 'del', click: del
			} 
			
		
			],
			listeners:{
			rowClick:function(rowData,rowIndex){
			},
			rowDblClick:function(rowDate,rowIndex){
				detail();
			},
			selectionChange:function(rowDate,rowIndex){
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail:count==1,
					edit:count==1,
					del:count>0
				});
							var up_status = Qm.getValueByName("status");
             				var status={};
             				if(count==0){
             					status={detail:false, edit:false, del:false,submit:false,del:false};
             				}else if(count==1){
             					status={detail:true, edit:true, del:true,submit:true,del:true};
             				}
             				Qm.setTbar(status);
             	}			
		}
			
	};
	
	function add(){
		top.$.dialog({
			width:900,
			height:300,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"新增",
			content:'url:app/finance/cwInvoice_detail.jsp?pageStatus=add'
		});
	}
	function edit(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:900,
			height:300,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"修改",
			content:'url:app/finance/cwInvoice_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/cwInvoice_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		var id = Qm.getValueByName("id").toString();
		$.del(kFrameConfig.DEL_MSG, "cwInvoice/reg/del.do?id="+id);
	}
	/*
	 function submitData(){
        var id = Qm.getValueByName("id");
        $.ligerDialog.confirm('是否提交审核？', function (yes){
            if(!yes){return false;}
            top.$.ajax({
                         url: "cwInvoice/reg/submit.do?id="+id,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                            if(data.success){
                                $.ligerDialog.success("提交成功！");
                                Qm.refreshGrid();//刷新
                            }else{
                                $.ligerDialog.warn(data.msg);
                            }
                         },
                         error:function () {
                             $.ligerDialog.warn("提交失败！");
                         }
                     });
        });
    }
	*/
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_PGDJ" script="false" singleSelect="true" >
	
	</qm:qm>
</body>
</html>