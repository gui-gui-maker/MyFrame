
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通讯录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	 var qmUserConfig = {
		 sp_defaults : {
				labelWidth : 60,
				columnWidth : 200,
				width : 130
			},
		sp_fields: [
				{ name: "name", compare: "like", value: "", width: ""},
				{ name: "mobile_tel", compare: "like", value: "", width: ""}
			],
 	  	tbar: [{
 	 		text: '详情', id: 'detailValue', icon: 'detail', click: detailValue
		}], 
	 	listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detailValue();
			},
			rowAttrRender:function(item,rowid){
				if(item.look_address==0){
					item.address="******";
				}
				if(item.look_mailing_address==0){
					item.mailing_address="******";
				}
				if(item.look_mobile_tel==0){
					item.mobile_tel="******";
				}
				if(item.look_home_tel==0){
					item.home_tel="******";
				}
				if(item.look_v_tel==0){
					item.v_tel="******";
				}
				if(item.look_emergency==0){
					item.emergency_linkman_name="******";
					item.emergency_linkman_tel="******";
					item.linkman_relation="******";
				}
				if(item.look_address==0||item.look_mailing_address==0||item.look_mobile_tel==0||item.look_home_tel==0||item.look_v_tel==0||item.look_emergency==0){
					return 'style="color:#e6640d;"';
				}
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();    
		 	 	Qm.setTbar({
		 	 		detailValue: count==1,
				});  
			}
		} 
	};
	function detailValue() {
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");
		var look_address= Qm.getValueByName("look_address");
		var look_mailing_address= Qm.getValueByName("look_mailing_address");
		var look_mobile_tel= Qm.getValueByName("look_mobile_tel");
		var look_home_tel= Qm.getValueByName("look_home_tel");
		var look_v_tel= Qm.getValueByName("look_v_tel");
		var look_emergency= Qm.getValueByName("look_emergency");	
				if(fk_employee_id!=""){
					if(id!=1){
						top.$.dialog({
							width: 700,
							height: 400,
							lock: true,
							parent: parent.api,
							data: {
								window: window
							},
							title: "详情",
							content: 'url:pub/addressBook/public_look_detail.jsp?id=' 
									+ id + '&pageStatus=detail&res=2&look_address='
									+look_address+'&look_mailing_address='+look_mailing_address+
						            '&look_mobile_tel='+look_mobile_tel+'&look_home_tel='+
						            look_home_tel+'&look_v_tel='+look_v_tel+'&look_emergency='+look_emergency  
						}); 
					}else{
						top.$.dialog({
							width: 700,
							height: 400,
							lock: true,
							parent: parent.api,
							data: {
								window: window
							},
							title: "详情",
							content: 'url:pub/addressBook/public_look_detail.jsp?id=' + fk_employee_id 
									+ '&pageStatus=detail&res=1&look_address='
							+look_address+'&look_mailing_address='+look_mailing_address+
				            '&look_mobile_tel='+look_mobile_tel+'&look_home_tel='+
				            look_home_tel+'&look_v_tel='+look_v_tel+'&look_emergency='+look_emergency  
						}); 	
					}
				}else{
					top.$.dialog({
						width: 700,
						height: 400,
						lock: true,
						parent: parent.api,
						data: {
							window: window
						},
						title: "详情",
						content: 'url:pub/addressBook/public_look_detail.jsp?id=' + id 
								+ '&pageStatus=detail&res=2&look_address='
						+look_address+'&look_mailing_address='+look_mailing_address+
			            '&look_mobile_tel='+look_mobile_tel+'&look_home_tel='+
			            look_home_tel+'&look_v_tel='+look_v_tel+'&look_emergency='+look_emergency  
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
	 <qm:qm pageid="public_address_look"  script="false" singleSelect="true">
	       <qm:param name="my_type" compare="llike" value="${param.my_type}" />
	 </qm:qm>	
</body>
</html>