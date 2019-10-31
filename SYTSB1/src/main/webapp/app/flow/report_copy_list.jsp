<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告录入</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	
	
	
	var ids = "${param.ids}";
	
	
	
	
	
	
	
	var report_type="${param.report_type}"
	
	var qmUserConfig = {
		sp_fields : [
		{name:"sn", compare:"like"},
		{name:"report_sn", compare:"like"},
		{
			name : "device_registration_code",
			compare : "like",
			value : ""
		}, {
			name : "report_com_name",
			compare : "like",
			value : ""
		}, {
			name : "make_units_name",
			compare : "like",
			value : ""
		}, {
			name : "enter_op_name",
			compare : "like",
			value : ""
		}, {
			name : "flow_note_name",
			compare : "like",
			value : ""
		}],
		tbar : [

		{
			text : '查看报告',
			id : 'detail',
			icon : 'detail',
			click : showReport
		}, "-",

		

		{
			text : '报告复制',
			id : 'copy',
			icon : 'copy',
			click : copy
		}

		],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					//input : count == 1,
					detail : count == 1,
					//device : count == 1,
					copy : count ==1
				});
			},

			afterQmReady : function() {
				Qm.setCondition([ {
					name : "id",
					compare : "not in",
					value : "(${param.par})",
					dataType : "user"
				},
				//{
				//	name : "is_report_input",
				//	compare : "=",
				//	value : "2",
				//},
				/*{
					name : "report_type",
					compare : "=",
					value : "${param.report_type}"
				}]);*/
				{
					name : "report_name",
					compare : "like",
					value : "${param.report_name}"
				}]);
				Qm.searchData();
			}
		}
	};
	// 查看报告
	function showReport(){	
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
		//var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
		//if(is_user_defined==""){
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);
			var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
			top.$.dialog({
				width : w, 
				height : h, 
				lock : true, 
				title:"报告信息",
				content: 'url:'+url,
				data : {"window" : window}
			}).max();
			//var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
	}
	function copy() {
		$.ligerDialog.confirm('确定复制报告内容?', function (yes) { 
				if(yes){
					$.getJSON('department/basic/reportCopy.do',{infoId:ids,report_type:'${param.report_type}',old_report_type:Qm.getValueByName("report_type"),id:Qm.getValueByName("id")},function(data){
						if(data){
							top.$.notice("复制成功！");
							api.data.window.submitAction();
							api.close();
						}
					});
				}
		});
	}
	function submitAction(o) {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_copy" script="false" singleSelect="false"  seachOnload="false">
	
	</qm:qm>
	<script type="text/javascript">
		Qm.config.columnsInfo.flow_note_name.binddata = [
			{id: '报告录入', text: '报告录入'},
			{id: '报告审核', text: '报告审核'},
			{id: '报告签发', text: '报告签发'},
			{id: '打印报告', text: '报告打印'},
			{id: '报告领取', text: '报告领取'},
			{id: '报告归档', text: '报告归档'}
		];
	</script>
</body>
</html>
