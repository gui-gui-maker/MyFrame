<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通讯录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var userId = "<sec:authentication property="principal.id" />";
	 var qmUserConfig = {
		sp_fields: [
			{ name: "name", compare: "like", labelWidth: 40},
			{ name: "mobile_tel", compare: "like", labelWidth: 40},
			{ name: "office_tel", compare: "like", labelWidth: 60}
		],
	 	listeners: {
			rowDblClick: detailValue,
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				parent.falsh(count);
			}
		} 
	};
	
	function addValue(addressListId) {
		top.$.dialog({
			width: 800,
			height: 500,
			lock: true,
			parent: parent.api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:pub/addressBook/private_address_detail.jsp?pageStatus=add&myType='+addressListId
		}); 
	}
	function editValue() {
		var id = Qm.getValueByName("id");
			top.$.dialog({
				width: 800,
				height: 500,
				lock: true,
				parent: parent.api,
				data: {
					window: window
				},
				title: "修改",
				content: 'url:pub/addressBook/private_address_detail.jsp?id=' + id + '&pageStatus=modify'
			}); 
		}
	
	function delValue() {
	
			var id = Qm.getValueByName("id");
			 $.del(kFrameConfig.DEL_MSG, "rbac/pubAddress/delete.do", {
					"ids": Qm.getValuesByName("id").toString()
				}); 	
		}
		
	function detailValue() {
		var id = Qm.getValueByName("id");
			top.$.dialog({
				width: 800,
				height: 500,
				lock: true,
				parent: parent.api,
				data: {
					window: window
				},
				title: "详情",
				content: 'url:pub/addressBook/private_address_detail.jsp?id=' + id + '&pageStatus=detail&res=1'
			}); 		
			
		}
	
	
	//设置
	function updateValue(){
		var id = Qm.getValueByName("id");

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
		
	function loadGridData(orgId){
		//alert(orgId);
		//alert(Qm.config.defaultSearchCondition[0].value);
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
	//alert("${param.userId}");
</script>
</head>
<body>
	 <qm:qm pageid="private_address_list"  script="false" singleSelect="false">
	       <qm:param name="my_type" compare="llike" value="${param.my_type}" />
	 </qm:qm>	
</body>
</html>