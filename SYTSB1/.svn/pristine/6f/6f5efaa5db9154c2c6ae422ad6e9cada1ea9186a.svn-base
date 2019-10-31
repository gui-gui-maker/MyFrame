<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <%@include file="/k/kui-base-list.jsp"%> --%>
<%@include file="/k/kui-base-form.jsp"%>
<title>选择单位/部门</title>
<script type="text/javascript">
var unitId = "<sec:authentication property="principal.unit.id" />";
var unitName = "<sec:authentication property="principal.unit.orgName" />";
// var filter=encodeURIComponent("全部");
var filter="all";
var orgTreeManager=null;
$(function(){
	$("#layout").ligerLayout({
		leftWidth : 340,
		rightWidth: 250
	});
	$("#button1").ligerButton({icon:"search",text:"查询",click:function(){
		filter=$("#dw-txt").ligerComboBox().getValue();
		if(filter==""||filter==null){
			filter="all";
		}
		changeVal(filter);
	}});
	orgTreeManager = $("#orgTree").ligerTree({
		checkbox:  "${param.checkbox}"=="1"?true:false,
		selectCancel : false,
        iconFieldName : "level",
        iconParse : function(data){
        	if(data["level"]==0)
           		return "k/kui/images/icons/16/home.png";
        	else if(data["level"]==1)
              	return "k/kui/images/icons/16/org.png";
        	else
            	return "k/kui/images/icons/16/user.png";
        },
		data : [],
		nodeWidth: 150,
		onCheck:function(node,checked){
			//选中所有的子节点
			var subNodeArr=node.data.children;
			if(subNodeArr!=null&&subNodeArr.length>0){
				for(var i=0;i<subNodeArr.length;i++){
					var subNode=subNodeArr[i];
					if(checked){
						addUserItem(subNode.id,"",subNode.text,"${param.type}");
					}
					else{
						removeUserItem(subNode.text)
					}
				}
			}
			else{
				var selOrg=node.data;
				if(checked){
					addUserItem(selOrg.id,"",selOrg.text,"${param.type}");
				}
				else{
					removeUserItem(selOrg.text)
				}
			}
		},
		onSelect:function(node){
			if("${param.checkbox}"=="0"){
				//清除原来选择的数据
				$("#person_list").empty();
				
				var selOrg=node.data;
				addUserItem(selOrg.id,"",selOrg.text,"${param.type}");
			}
		},
        onBeforeExpand: function(node){
        	if(filter=="all"){
        		if (node.data.children && node.data.children.length == 0)
    			   // this.loadData(node.data,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
        		    this.loadData(node.data,"oa/pub/myOrg/getSubordinate.do?orgid=" + node.data.id+"&filter="+filter);
        	}
        }
	});
	$.getJSON("oa/pub/myOrg/getSubordinate.do?orgid=" + unitId+"&filter="+filter,function(dataList){
		orgTreeManager.append(unitId,[{
			id : unitId,
			text : unitName,
			level : "0",
			children : dataList
		}]);
	});
	
	setSelVal();
});

//添加选择人
function addUserItem(id,tel,name,type){
	if($("#"+id).size() > 1) return;
	$("#person_list").append("<option id='"+id+"' value='" + tel + "'>" +name +"</option");
}

//移除已选人员
function removeUserItem(code){
	$("#person_list option").each(function(){
		if($(this).text()==code)
			$(this).remove();
	});
}

//选择结果
function getSelectResult(){
	var result = {id:"",tel:"",name:""};
	var options = $("#person_list option");
	var i=0;
	$.each(options,function(){
		 result.id+=(i==0?"":",")+$(this).attr("id");
		 result.tel += (i==0?"":",") + $(this).val();
		 result.name += (i==0?"":",")+ $(this).text();
		 i=i+1;
	});
	return result;
}



//双击以选择项目移除
function removeItem(sel){
	$(sel).find("option:selected").remove();
}

/**
 * 将选择的信息设置在已选列表中
 */
function setSelVal(){
	var idVal = "${param.id}";
	var telVal= "${param.tel}";
	var nameVal="${param.name}";
	if(idVal!=""&&idVal!="undefined"){
		for(var i=0;i< idVal.split(',').length;i++){
			$("#person_list").append("<option id='"+idVal.split(',')[i]+"' value='"+telVal.split(',')[i]+"'>"+nameVal.split(',')[i]+"</option");
		}
	}
}

function changeVal(filter){
// 	 filter=encodeURIComponent(ele.value);
	$.getJSON("oa/pub/myOrg/getSubordinate.do?orgid=" + unitId+"&filter="+filter,function(dataList){
		orgTreeManager.clear();            
		orgTreeManager.append(unitId,[{
			id : unitId,
			text : unitName,
			level : "0",
			children : dataList
		}]);
	});
}
</script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    }
</style>
</head>
<body>
	<div id="layout">
	
		<div position="left" title="组织机构" style="overflow:auto;">
<!-- 				<span style="margin-left: 20px;"> -->
<!-- 					<input type="radio" name="unit" value="全部" checked="checked" onclick="changeVal(all);"/>全部 -->
<!-- 					<span style="margin-left:20px;"></span> -->
<!-- 					<input type="radio" name="unit" value="社区居委会" onclick="changeVal(sq);"/>社区居委会 -->
<!-- 				    <span style="margin-left:20px;"></span> -->
<!-- 				    <input type="radio" name="unit" value="村委会" onclick="changeVal(cj);"/>村委会 -->
<!-- 				    <span style="margin-left:20px;"></span> -->
<!-- 				    <input type="radio" name="unit" value="敬老院" onclick="changeVal(jly);"/>敬老院 -->
<!-- 				    <span style="margin-left:20px;"></span> -->
<!-- 				</span> -->
				<form id="formObj">
					<span style="margin-left: 20px;"> 
					<table>
 						<tr>
							<td class="l-t-td-left">选择机构:</td>
							<td class="l-t-td-right">
							  <div id="button1"></div> 
							</td>
						</tr>
 					</table>
 				</span> 
 					</form>
		    	<ul id="orgTree"></ul>
		</div>
		<div position="right" title="已选单位/部门">
			<select id="person_list" multiple="multiple" ondblclick="removeItem(this)" title="双击移除"
				style="border:none;width:245px;height:100%;font-size:14px;padding: 2px;"></select>
		</div>
	
	</div>
</body>
</html>
