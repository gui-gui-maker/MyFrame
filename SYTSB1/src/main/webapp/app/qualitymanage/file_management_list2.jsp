<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields : [{name : "file_id",compare : "like",value : ""
		},{name : "file_name",compare : "like",value : ""
		}],
		tbar : [/*{text : '查看',id : 'detail',icon : 'detail',click : detail
		}, "-",*/{text : '编辑文件',id : 'add',icon : 'add',click : add
		}/*, "-",{text : '修改',id : 'modify',icon : 'modify',click : modify
		}*/, "-",{text : '删除',id : 'del',icon : 'delete',click : del
		}],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count == 1,
					detail : count == 1,
					del : count > 0
				});
			},rowDblClick: function(rowData, rowIndex) {detail();}
		}
	};

	var device_id = null;
	function add() {
		if (device_id == null) {
			$.ligerDialog.alert("请选择一个类别！");
			return;
		} else {
			device_type = device_id;//
//			alert(device_type);
				top.$.dialog({
				width : 900,
				height : 550,
				lock : true,
				title : "编辑文件",
				content : 'url:app/qualitymanage/file_management_datail2.jsp?pageStatus=modify&device_type='
						+ device_type+'&id='+Qm.getValueByName("id"),
				data : {
					"window" : window
				}
			});
		}
	}
	function detail() {
//		device_type = Qm.getValueByName("big_class").substring(0, 1);
				top.$.dialog({
					width : 600,
					height : 350,
						lock : true,
						title : "详情",
						content : 'url:app/qualitymanage/file_management_datail2.jsp?pageStatus=detail&id='+Qm.getValueByName("id"),
						data : {
							"window" : window
						}
					});
		
	}
	function modify() {
//			device_type = Qm.getValueByName("big_class").substring(0, 1);
//			var device_id = Qm.getValueByName("device_sort").toString();
					top.$.dialog({
						width : 600,
						height : 350,
							lock : true,
							title : "修改",
							content : 'url:app/qualitymanage/file_management_datail2.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
							+'&device_type='+ device_type,
							data : {
								"window" : window
							}
						});
	}
	
	function del() {
		$.del("确定删除?", "tx/file/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
// 		var id = Qm.getValueByName("id");
// 		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
//         	if(!yes){return false;}
// 			top.$.ajax({
// 	            url: "tx/file/delateUploads.do?ids="+id,
// 	            type: "POST",
// 	            dataType:'json',
// 	            async: false,
// 	            success:function (data) {
// 	               if(data.success){
// 	                  top.$.notice("删除成功！",3);
// 	                   Qm.refreshGrid();//刷新
// 	               }else{
// 	                   $.ligerDialog.warn("删除失败！");
// 	                   Qm.refreshGrid();//刷新
// 	               }
// 	            },
// 	            error:function () {
// 	                //$.ligerDialog.warn("提交失败！");
// 	           	 $.ligerDialog.error("出错了！请重试！!");
// 	            }
// 	        });
// 		});
	}

	function loadGridData(nodeId, unitId, url) {
		device_id = nodeId;
		if (nodeId != null) {
				Qm.setCondition([ {
					name : "tjy_file",
					compare : "=",
					value : nodeId
				} ]);
		} else {
			Qm.setCondition([]);
		}
		Qm.searchData();
	}

	//列表刷新
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}
	function reLoad(){
        location.reload();
    }
</script>
</head>
<body>
	<qm:qm pageid="TJY2_QUALITY_FILES" singleSelect="true">
	</qm:qm>
<!--  	<script type="text/javascript"> --> 
<!--  		 根据 sql或码表名称获得Json格式数据 -->
<%--  		Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>; --%>
<!--  	</script> --> 
</body>
</html>
