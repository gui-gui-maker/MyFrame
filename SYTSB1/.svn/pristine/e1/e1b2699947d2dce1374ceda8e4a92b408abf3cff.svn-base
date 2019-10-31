<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<script type="text/javascript">
$(function() {
	
});

function checkAll(c){
	
	if ($(c).attr("checked")&&$(c).val()==1) {
		$(".check").attr("checked","checked");
		$(".item").attr("bgcolor","#BCD2EE");
		ids = [];
		$(".check").each(
				function() {
					selectFile1(this);
					//ids[ids.length] =$(this).attr("checked");
				}) 
	}else{
		$(".check").removeAttr("checked");
		$(".item").attr("bgcolor","white");
		select = {};
		ids = [];
	}
}

//资源选择事件处理
function selectFile1(info){
	var sid = $(info).attr("id");
	if($("#sid").attr("checked")==undefined){
		//选中
		$("#sid").attr("checked","checked");
		ids[ids.length]=sid;
	}
	
	down = true;
	for (var i = 0; i < ids.length; i++) {
		var type  = beans[ids[i]].infoType;
		if(type=="1"){
			down=false;
		}
	}
	
}
</script>
<style type="text/css">
filename{
	width: 50%;
}
filesize{
	width: 10%;
}
xsize{
	width: 10%;
}
update{
	width: 20%;
}
ly{
	width: 10%;
}
head{
	background-color: #4F94CD;
}
.th{
	border-right-style: solid;
	border-right-color: white;
}
</style>
</head>
<body>
	<br />
	<table style="margin-left: 20px; border="0" width="80%" cellpadding="3" cellspacing="3">
		<thead>
			<tr bgcolor="#EDEDED" style="height: 30px;">
			<td>
			<input type="checkbox" name="contract_1" ltype="checkboxGroup"
			onchange="checkAll(this)"
							ligerui="{initValue:'',data:[{text:'',id:'1'}]}" style="display: inline;"/>
			</td>
				<td class="filename th" onclick="order(this,'name')" name="asc">
				<div align="center"  id="name" ></div>
					名称
				</td>
				
				<td class="update th" onclick="order(this,'last_update_date')" name="asc">
				<div align="center"  id="last_update_date" ></div>
					修改日期
				</td>
				<td class="filesize th">
					大小
				</td>
				<td class="xsize th">
					星级
				</td>
				<td class="ly th">
						来源
				</td>
			</tr>
		</thead>
		<tbody  id ="mainT"></tbody>
		<!-- 
		<tr class="item" id="1" onmousedown="if(event.button == 2) showGRMenu(this)" onclick="selectFile(this);"
					onmouseout="closeGRMenu()" oncontextmenu="return false;" ondblclick="openFile(this)" value="cs">
			<td>
			<input type="checkbox" name="contract_2" ltype="checkboxGroup" class='check' id="check1"
							ligerui="{initValue:'',data:[{text:'',id:'1'}]}" style="display: inline;"/>
			</td>
			<td class="filename">
			
					<span class ="skin" > 
						<img src="k/kui/images/file-type/16/folder.png" alt="" />文件夹
					
					</span>
			</td>
		
			<td class="update">
				2016-01-01 00:00:00
			</td>
				<td class="filesize">
				1kb
			</td>
		</tr>
		<tr style="height: 3px;">
			<td colspan="3"></td>
		</tr>
		<tr class="item" id="2"  onmousedown="if(event.button == 2) showGRMenu(this)" onclick="selectFile(this);"
			onmouseout="closeGRMenu()" oncontextmenu="return false;" ondblclick="openFile(this)">
			<td>
			<input type="checkbox" name="contract_10" ltype="checkboxGroup" class='check'
							 id="check2"
							ligerui="{initValue:'',data:[{text:'',id:'1'}]}" style="display: inline;"/>
			</td>
			<td class="filename">
				<span class ="skin" >
					<img src="k/kui/images/file-type/16/file.png" alt="" />文件
				
				</span>
			</td>
			
			<td class="update">
				2016-01-01 00:00:00
			</td>
			<td class="filesize">
				2kb
			</td>
		</tr> -->
		
		</table>
</body>
</html>
