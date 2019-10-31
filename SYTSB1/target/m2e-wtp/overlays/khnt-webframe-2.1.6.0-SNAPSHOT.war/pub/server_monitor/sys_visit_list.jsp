<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统资源访问记录</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var qmUserConfig = {
            sp_defaults:{columnWidth:0.25},
            sp_fields:[
				{name:"user_name",compare:"like",labelWidth:50},
				{name:"user_address",compare:"like",labelWidth:50},
				{columnWidth:0.5,group:[{
					name:"visit_time",compare:">=",width:"120",labelWidth:60
				},{
					label:"至",name:"visit_time",compare:"<=",labelAlign:"center",labelWidth:20,width:"120"
				}]}
            ],
            listeners : {
				tree : {
					columnId : 'r_name',
					idField : 'id',
					parentIDField : 'parent_id'
				}
            }
        };
    </script>
	<style type="text/css">
	.l-grid-row-cell-inner{padding: 4px 0!important;}
	</style>
	</head>
	<body>
		<qm:qm pageid="sys_src_visit">
			<qm:attr name="vurl" value="${param.url}" />
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.url.visible="${param.showUrl}"
		</script>
	</body>
</html>