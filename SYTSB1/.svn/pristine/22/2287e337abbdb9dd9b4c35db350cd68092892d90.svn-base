<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
		});
        function getDriver(){
    		var title = "驾驶员选择";
    		var url = "url:app/oa/car/select_driver_list.jsp";
    		var width = 750	;
    		top.$.dialog({
    			width : width,
    			height : 400,
    			lock : true,
    			parent : api,
    			id : "win98",
    			title : title,
    			content : url,
    			cancel: true,
    			ok : function() {
    				var data = this.iframe.contentWindow.driverSelect();
    				$("#driverId").val(data.id);
    		        $("#driver").val(data.name);
    		        $("#driver").val(data.name);
    		        $("#tel").val(data.telphone);
    				return true;
    			}
    		});
    	}
        function selectCar(){
    		var title = "车辆选择";
    		var url = "url:app/oa/car/selectCar_list1.jsp";
    		var width = 800	;
    		top.$.dialog({
    			width : width,
    			height : 400,
    			lock : true,
    			parent : api,
    			id : "win98",
    			title : title,
    			content : url,
    			cancel: true,
    			ok : function() {
    				var datas = this.iframe.contentWindow.getSelectResult();
    				$("#carid").val(datas.carid);
    				$("#carNum").val(datas.carnum);
    				$("#driver").val(datas.driver);
    				return true;
    			}
    		});
    	}
    </script>
</head>
<body>
<form id="form1" action="oa/car/driverplan/save.do" getAction="oa/car/driverplan/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input name="unitId" type="hidden" value="<sec:authentication property="principal.unit.id"/>"/>
    <table cellpadding="3" class="l-detail-table">
        <tr>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input  type="hidden" name="car.id" id="carid"/>
				<input id="carNum" name="car.carNum" type="text" ltype='text' readonly="readonly" validate="{required:true}" onclick="selectCar()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCar()}}]}"/></td>
		</tr>
		<tr>
			<td class="l-t-td-left">驾驶员：</td>
			<td class="l-t-td-right" >
				<input id="driverId" name="driverId" type="hidden"/>
				<input id="driver" name="driver" type="text" ltype='text'  validate="{required:true,maxlength:15}"  
				ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getDriver()}}]}"/>	
			</td>
		</tr>
        <tr>
            <td class="l-t-td-left">联系电话：</td>
            <td class="l-t-td-right"><input id="tel" name="driverTelephone" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">出车事由：</td>
            <td class="l-t-td-right"><input name="planReason" type="text" ltype='text' validate="{required:true,maxlength:512}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">出车时间：</td>
            <td class="l-t-td-right"><input name="planDate" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm',showTime:true}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">备注：</td>
            <td class="l-t-td-right">
            	<textarea rows="5" cols="20" name="remark" ltype="textarea" validate="{maxlength:512}"></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
