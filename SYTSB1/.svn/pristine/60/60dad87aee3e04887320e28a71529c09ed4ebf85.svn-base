<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户角色授权</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = { 
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
		sp_fields:[
	      {name:"loginname",compare:"like",value:""},
	      {name:"name",compare:"like",value:""}
	    ],
		tbar:[
          { text:'设置栏目权限', id:'edit',icon:'setting', click: setUserClassPermission}
        ],
        listeners: {
        	selectionChange :function(rowData,rowIndex){
    			var count = Qm.getSelectedCount();//行选择个数
    			Qm.setTbar({edit : count==1});
        	}
        }
	};
    //资源授权
    function setUserClassPermission(){
        var width=400;
        var height=500;
        var windows=top.$.dialog({
            width:width,
            height:height,
            lock:true,
            title:"栏目资源配置",
            content: 'url:app/webmanage/userPermission/permission_Class_resource.jsp?perId='+Qm.getValueByName("id"),
            ok:function(w){
                var iframe=this.iframe.contentWindow;
                iframe.setUserClass();
                return false;
            },
            cancel:function(w){//取消按钮函数
            }
        });
    }
</script>
</head>
<body>
	<qm:qm pageid="user_1" script="false" singleSelect="true">
		<qm:param name="org_id" compare="=" value="${param.orgid}" />
	</qm:qm>
</body>
</html>
