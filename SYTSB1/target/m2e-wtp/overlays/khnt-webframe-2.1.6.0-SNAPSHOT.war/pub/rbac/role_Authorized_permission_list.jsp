<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户角色授权</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [
			{name:"p_name",compare:"like",labelWidth:60},
			{name:"p_code",compare:"like",labelWidth:60},
			{name:"remark",compare:"like",labelWidth:60}
		],
		listeners : {
			pageSizeOptions: [10, 20, 30, 40, 50,100,500,1000],
			tree : {
				columnId : 'p_name',
				idField : 'id',
				parentIDField : 'parent_id'
			},
			autoCheckChildren : false,
			onAfterShowData : initGridSelectRange,
			onCheckRow : function(checked,rowdata, rowindex, rowDomElement){
				parent.addOrRemovePermision(checked,{id:rowdata.id,name:rowdata.p_name});
			},
			onCheckAllRow:function(checked,row){
				parent.removeAllPermision();
				if(checked){
					var data = Qm.getQmgrid().getData();
					for(var i in data){
						parent.addOrRemovePermision(checked, {id:data[i].id,name:data[i].p_name});
					}
				}
			}
		}
	};

	function initGridSelectRange(){
		var idRange = parent.getRolePermissionArr();
		var ids = idRange.toString();
		var datas =Qm.getQmgrid().getData();
        for (var i in datas) {
        	Qm.getQmgrid().unselect(i);
            for (var j in ids.split(",")) {
                if (datas[i]["id"] == ids.split(",")[j]) {
					Qm.getQmgrid().select(i);
				}
            }
        }
	}
</script>
</head>
<body class="p0">
	<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
		<qm:qm pageid="sys_permission" script="false" singleSelect="false" pagesize="1000" />
	</sec:authorize>
	<sec:authorize ifNotGranted="sys_administrate,super_administrate">
		<sec:authorize ifAllGranted="unit_administrate">
			<qm:qm pageid="sys_permission_unit" script="false" singleSelect="false" pagesize="1000" />
		</sec:authorize>
		<sec:authorize ifNotGranted="unit_administrate" ifAllGranted="dep_administrate">
			<qm:qm pageid="sys_permission_dept" script="false" singleSelect="false" pagesize="1000" />
		</sec:authorize>
	</sec:authorize>
</body>
</html>
