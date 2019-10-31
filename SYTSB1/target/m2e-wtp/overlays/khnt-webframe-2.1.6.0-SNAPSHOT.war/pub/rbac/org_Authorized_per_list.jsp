<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>组织机构权限配置</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields : [ {
			name : "p_name", compare : "like",labelWidth:60
		}, {
			name : "p_code", compare : "like",labelWidth:60
		}, {
			name : "remark", compare : "like",labelWidth:60
		}],
		listeners : {
			pageSizeOptions: [10, 20, 30, 40, 50,100,500,1000],
			tree : {
						columnId : 'p_name',
						idField : 'id',
						parentIDField : 'parent_id'
			},
			autoCheckChildren : false,
			onAfterShowData : function() {
				initGridSelectRange();
			},
			onCheckRow : function(checked,rowdata, rowindex, rowDomElement){
				parent.addOrRemoveRole(checked,rowdata);
			},
			onBeforeCheckAllRow : function(){
				return false;
			}
		}
	};
	
	//表格渲染时，将被选择的角色勾选
	function initGridSelectRange(){
		var idRange = parent.getRoleArr();
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
		<qm:qm pageid="sys_permission" singleSelect="false" pagesize="1000"/>
	</sec:authorize>
	<sec:authorize ifNotGranted="sys_administrate,super_administrate">
		<qm:qm pageid="sys_self_permission" singleSelect="false"  pagesize="1000" />
	</sec:authorize>
</body>
</html>
