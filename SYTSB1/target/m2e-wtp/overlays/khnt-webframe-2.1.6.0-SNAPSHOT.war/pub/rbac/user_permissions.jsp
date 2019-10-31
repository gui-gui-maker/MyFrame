<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统用户</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields:[
	    	{name:"p_code",compare:"like"},
			{name:"p_name",compare:"like"},
			{name:"remark",compare:"like"}
	    ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	//Qm.setTbar({modify:count==1,detail:count==1,del:count>0,config:count==1,enable:count==1,disenable:count==1,setting:count==1,settings:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
			},
			rowAttrRender:function(item,rowid){
			},
			tree : {
				columnId : 'p_name',
				idField : 'id',
				parentIDField : 'parent_id'
			},
			pageSize : 100
		}
	};
</script>
</head>
<body>
	<qm:qm pageid="user_permission" script="false" singleSelect="true">
		<qm:attr name="userid" value="${param.userId }" />
	</qm:qm>
</body>
</html>
