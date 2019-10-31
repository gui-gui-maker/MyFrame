<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	            
	        }
	    });
	});
	function saveClose(){
		var vals = [];
		var jsonStr = "{}";
		$('input:checkbox:checked').each(function (index, item) {
			vals.push($(this).val());
		});
		if(vals==""){
			$.ligerDialog.error("未勾选要修改的项目！");
			return false;
		}else{
			var ppeId = "${param.id}";
			var jsonObj = JSON.parse(jsonStr);
			for(var i in vals){
				if(vals[i]=="userDepartment"){
					jsonObj["userDepartmentId"] = $("#userDepartmentId").val();
				}else if(vals[i]=="userName"){
					jsonObj["userId"] = $("#userId").val();
				}
				jsonObj[vals[i]] = $("#"+vals[i]).val();
				JSON.stringify(jsonObj);
			}
			var entity= $.ligerui.toJSON(jsonObj);
	        $.ajax({
	            url: "equipPpeAction/bathModify.do",
	            data: {
	            	"entity":entity,
	            	"ppeId":ppeId
	            },   
	            type: "POST",
	            success: function (data, stats) {
	                if (data["success"]) {
	                	top.$.notice("批量修改成功！");
	               		api.close();
	                	api.data.window.refreshGrid();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	                $.ligerDialog.error(data.msg);
	            }
	        });
		}
    }
	function close(){
        api.close();
    }
	//使用部门选择
	function chooseOrg(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择部门",
            content: 'url:app/common/org_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#userDepartmentId").val(p.id);
                $("#userDepartment").val(p.name);
            }
        });
    }
	//使用人员选择
	function choosePerson(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择人员",
	        content: 'url:app/common/person_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#userId").val(p.id);
	            $("#userName").val(p.name);
	            $("#userDepartmentId").val(p.org_id);
	            $("#userDepartment").val(p.org_name); 
	        }
	    });
	} 

</script>
</head>
<body>



<form name="formObj" id="formObj" method="post" >
<input type="hidden" name="userDepartmentId" id="userDepartmentId"/>
<input type="hidden" name="userId" id="userId"/>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>基本信息</div>
		</legend>
	<table width="100%">		
		<tr>
			<td class="l-t-td-left">计量单位</td>
			<td class="l-t-td-right"> 
	        	<input name="unit" id="unit" type="text" ltype='text' validate="{maxlength:200}"/>
	        </td>
	        <td><input value="unit" type="checkbox"/></td>
	        <td class="l-t-td-left">放置地点</td>
			<td class="l-t-td-right"> 
	        	<input name="placeLocation" id="placeLocation" type="text" ltype='text' validate="{maxlength:200}"/>
	        </td>
	        <td><input value="placeLocation" type="checkbox"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">使用部门</td>
			<td class="l-t-td-right"> 
	        	<input name="userDepartment" id="userDepartment" type="text" ltype='text' validate="{maxlength:100}"  readonly="readonly" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
	        </td>
	        <td><input value="userDepartment" type="checkbox"/></td>
			<td class="l-t-td-left" style="width: 150px">使用人</td>
			<td class="l-t-td-right"> 
	        	<input name="userName" id="userName" type="text" ltype='text' validate="{maxlength:100}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
	        </td>
	        <td><input value="userName" type="checkbox"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">资产状态</td>
			<td class="l-t-td-right"> 
	        	<u:combo name="assetStatus" code="TJY2_PPE_ASSET_STATUS" validate="{required:true}"/>
	        </td>
	        <td><input value="assetStatus" type="checkbox"/></td>
			<td class="l-t-td-left">是否盘点</td>
			<td class="l-t-td-right"> 
	        	<u:combo name="inventory" code="TJY2_EQUIP_INVENTORY" validate="{required:true}"/>
	        </td>
	        <td><input value="inventory" type="checkbox"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">最低使用年限</td>
			<td class="l-t-td-right"> 
	        	<input name="serviceLife" id="serviceLife" type="text" ltype='text' validate="{maxlength:200}"/>
	        </td>
	        <td><input value="serviceLife" type="checkbox"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">归属</td>
			<td class="l-t-td-right" colspan="4">
				<u:combo name="owner" code="TJY2_PPE_OWNER"/>
			</td>
			<td><input value="owner" type="checkbox"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">备注</td>
			<td class="l-t-td-right" colspan="4">
				<textarea name="remark" id="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:500}"></textarea>
			</td>
			<td><input value="remark" type="checkbox"/></td>
		</tr>
	</table>
	</fieldset>
	</form>
</body>
</html>
