<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新注册用户默认角色管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
		<script type="text/javascript">
        var qmUserConfig = {
            sp_fields:[
				{label:"角色名称", name:"name", compare:"like"}
            ],
        	//采用标签形式实现 按钮资源的控制，并且实现资源权限控制 ，自标签可以实现额外按钮添加，木有权限控制 type必填项目值为tbar(list页面按钮) 或toolbar（detail页面按钮）
    		//如果不写code 那么url必须唯一
    		// id表示id按钮id，text：按钮显示名称  icon：按钮显示图标 click:按钮对应放方法 position 按钮放置在权限按钮前后 ，splitLine:是否显示分割线
    		//详细页面按钮控制，请看demo_detail1.jsp
    		<tbar:toolBar type="tbar" code="userNewRole">
    		</tbar:toolBar>, 
            listeners: {
                selectionChange :function(rowData,rowIndex){
                	var count = Qm.getSelectedCount();//行选择个数
                    Qm.setTbar({del:count>0});
                }
            }
        };
        
        //新增角色
        function add(){
        	selectUnitOrUser(2, true, "scode", "sname", function(datas){
        		$.post("sys/defaultUserRole/save.do",{code:$("#scode").val(),name:$("#sname").val()},function(resp){
            		if(resp.success){
            			top.$.notice("操作成功！");
            			Qm.refreshGrid();
            		}else{
            			$.ligerDialog.error("操作失败！<br/>" + resp.msg);
            		}
            	},"json")
        	});
        }
        
        //删除任务节点功能
        function del(){
        	$.ligerDialog.confirm(kFrameConfig.DEL_MSG,function(yes) {
				if (yes) {
					$.post("pub/codetablevalue/deleteValue.do",{
						"ids": Qm.getValuesByName("id").toString(),
						"codeTabledId": Qm.getValuesByName("cid")[0]
					}, function(resp) {
						if (resp.success) {
							Qm.refreshGrid();
						} else {
							$.ligerDialog.error('删除失败');
							return false;
						}
					}, "json");
				}
			});
        }
    </script>
	</head>
	<body>
		<input type="hidden" id="scode" />
		<input type="hidden" id="sname" />
		<qm:qm pageid="sys_default_role" />
	</body>
</html>
