<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通讯录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	 var qmUserConfig = {
		sp_defaults : {
			labelWidth : 60
		},
		sp_fields: [
			{name: "name", compare: "like"},
			{name: "mobile_tel", compare: "like"},
			{name: "office_tel", compare: "like"}
		],
	 	listeners: {
			rowDblClick: detailValue,
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();    
		    	parent.falsh(count);
		    	if(rowData){
		    		parent.falshTbar(count,rowData.id);
		    	}
			}
		} 
	};
	 
	function addValue(addressListId) {
		top.$.dialog({
			width: 800,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:pub/addressBook/public_address_detail.jsp?pageStatus=add&res=2&myType='+addressListId
		}); 
	}
	function editValue() {
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");	
			if(fk_employee_id!=""){
				$.ligerDialog.warn('由员工表查出的数据，不能修改');
			}else{
				top.$.dialog({
					width: 800,
					height: 500,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "修改",
					content: 'url:pub/addressBook/public_address_detail.jsp?id=' + id + '&pageStatus=modify&res=2'
				}); 
			}
	}
	function delValue() {
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");
			if(id.length<32){
				$.ligerDialog.warn('由员工表查出的数据，不能删除');
			}else{
				 $.del(kFrameConfig.DEL_MSG, "rbac/pubAddress/delete.do", {
						"ids": Qm.getValuesByName("id").toString()
					}); 
			}
		}	
	
	function detailValue() {
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");
				if(fk_employee_id!=""){
					if(id!=1){
						top.$.dialog({
							width: 800,
							height: 500,
							lock: true,
							parent: api,
							data: {
								window: window
							},
							title: "详情",
							content: 'url:pub/addressBook/public_address_detail.jsp?id=' + id + '&pageStatus=detail&res=2'
						}); 
					}else{
						top.$.dialog({
							width: 800,
							height: 500,
							lock: true,
							parent: api,
							data: {
								window: window
							},
							title: "详情",
							content: 'url:pub/addressBook/public_address_detail.jsp?id=' + fk_employee_id + '&pageStatus=detail&res=1'
						}); 	
					}
				}else{
					top.$.dialog({
						width: 800,
						height: 500,
						lock: true,
						parent: api,
						data: {
							window: window
						},
						title: "详情",
						content: 'url:pub/addressBook/public_address_detail.jsp?id=' + id + '&pageStatus=detail&res=2'
					}); 
				} 		
			}
	
	//设置
	function updateValue(){
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");
		var myType=Qm.getValueByName("my_type");
				if(fk_employee_id!=""){
					if(id!=1){
						top.$.dialog({
							width: 800,
							height: 500,
							lock: true,
							parent: api,
							data: {
								window: window
							},
							title: "设置",
							content: 'url:pub/addressBook/pub_address_setting.jsp?id=' + id  +'&pageStatus=modify&res=2'
						}); 	
					}else{
						top.$.dialog({
							width: 800,
							height: 500,
							lock: true,
							parent: api,
							data: {
								window: window
							},
							title: "设置",
							content: 'url:pub/addressBook/pub_address_setting.jsp?id=' + fk_employee_id + '&myType='+myType+'&pageStatus=modify&res=1'
						}); 	 
					}
				}else{
					top.$.dialog({
						width: 600,
						height: 300,
						lock: true,
						parent: api,
						data: {
							window: window
						},
						title: "设置",
						content: 'url:pub/addressBook/pub_address_setting.jsp?id=' + id  +'&pageStatus=modify&res=2'
					}); 	
				}
			}
	function loadGridData(orgId){
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
</script>
</head>
<body>
	 <qm:qm pageid="public_address_list"  script="false" singleSelect="false">
	       <qm:param name="my_type" compare="llike" value="${param.my_type}" />
	 </qm:qm>	
</body>
</html>