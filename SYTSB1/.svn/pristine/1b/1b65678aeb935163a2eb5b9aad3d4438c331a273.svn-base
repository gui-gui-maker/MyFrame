<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>检验资料报送打印签收信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	var bar =[];
 	bar =[ 
		{ text:'详情', id:'detail',icon:'detail', click: detail}
 	]
 	
 	var qmUserConfig = {
 		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
	        {name:"org_name", id:"org_name", compare:"like"},
			{name:"commit_user_name",compare:"like"},
			{name:"commit_date",compare:"="}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({detail:count==1});
	     	},
	    	afterQmReady:function(){
	      		Qm.searchData();
	   		}
		}
	};
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "报送打印详情",
			content : 'url:app/report/report_print_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="report_print_record" script="false" singleSelect="false">
			<qm:param name="data_status" compare="in" value="('0','1','2')"/>	
		</qm:qm>
	</body>
</html>
