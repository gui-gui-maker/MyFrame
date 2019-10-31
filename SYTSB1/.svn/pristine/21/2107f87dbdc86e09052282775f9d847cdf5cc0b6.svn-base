<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>上级任务选择</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults: {labelWidth:70},
				sp_fields: [
					{name:"title",compare:"like"},
					{name:"p_title",compare:"like"},
					{name:"types",compare:"="},
					{name:"levels",compare:"="},
					{name:"stakeholder",compare:"like"},
					{name:"assigner",compare:"like"}
				]
	        };
			function getSelectResult(){
            	var count = Qm.getSelectedCount();
            	if(count==0){
            		$.ligerDialog.warn("请选择一个任务！");
            		return null;
            	}
				var id = Qm.getValueByName("id").toString();
				var title = Qm.getValueByName("title").toString();
				return {id:id,title:title};
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="oa_task_selparent" script="false" singleSelect="true">
            <qm:attr name="id" value="${empty param.cid?'abc':param.cid}" />
		</qm:qm>
	</body>
</html>