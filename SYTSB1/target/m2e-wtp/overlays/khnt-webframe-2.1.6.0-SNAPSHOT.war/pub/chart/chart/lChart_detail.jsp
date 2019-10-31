<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>图表设置</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="/k/kui-base-chart.jsp"%>
<script type="text/javascript">
var CTYPES={};
var OPENPARM="";
var _this;
var pageStatus = "${param.status}";
var typeId = "${param.typeId}";
var PUB_CHARTID = "${param.id}";
$(function() {
	$("#formObj").initForm({
		toolbar:[{id:'colse',text:"关闭",icon:'cancel',click:function(){
				if(api.data.window.Qm)
				{
					api.data.window.Qm.refreshGrid();
				}
				api.close();
			}
		}]
	});
});



function setTypeData()
{
	$.ajax({
		url: "lchart/conf/getChartTypes.do",
		type: "POST",
		datatype: "json",
		async: false,
		contentType: "application/json; charset=utf-8",
		success: function (res, stats) {
			if (res.success) {
				CTYPES = res.data;
			}
		},
		error: function (data) {
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function onLoad()
{
	getDomains();
	setTypeData();
	
	if (pageStatus=="add")
	{
		$("#FK_TYPE_ID_TXT").ligerGetComboBoxManager().selectValue(typeId);
	}
	else if (pageStatus=="modify")
	{
		getChartInfo();
	}
}

function getChartInfo()
{
	$.post("lchart/conf/getChartInfo.do",{id:PUB_CHARTID},function(res){
		if(res.success){
			for(var i=0; i<res.data.length;i++)
			{
				$("#domainId").val(res.data[0].FK_DOMAIN_ID);
				$("#cid").val(res.data[0].ID);
				$("#domain").val(res.data[0].MC);
				$("#FK_TYPE_ID_TXT").ligerGetComboBoxManager().selectValue(res.data[0].FK_TYPE_ID);
				$("#CHARTID").val(res.data[0].CHARTID);
				$("#BZ").val(res.data[0].BZ);
				$("#isBig-txt").ligerGetRadioGroupManager().setValue(res.data[0].IS_BIG);
				$("#isSwich-txt").ligerGetRadioGroupManager().setValue(res.data[0].IS_SWICH);
				getDomainParams();
				getDataset();
				setChartIcon(res.data[0].FK_TYPE_ID);
				$("#swichType").ligerGetComboBoxManager().selectValue(res.data[0].SWICH_TYPES);
				getBParm();
				RenderChart(res.data[0].CHARTID,"div1");
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function getDomains()
{
	$.post("lchart/conf/getDomains.do",{},function(res){
		if(res.success){
			for(var i=0; i<res.data.length;i++)
			{
				$("#dmlist").append("<option value='"+res.data[i].MC+"'>");
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function saveDomain()
{
	var dm = formObj.domain.value;
	if (dm=="")
	{
		$.ligerDialog.warn('提示：请输入或选择Domain！');
		return;	
	}
	$.post("lchart/conf/saveDomain.do",{'mc':dm},function(res){
		if(res.success && res.data.length>0){
			formObj.domainId.value=res.data[0].ID;
			getDomainParams();
			getDataset();
			autorefresh();//自动刷新
		}else{
			$.ligerDialog.error('提示：数据保存失败！');
		}
	});
}

function saveChart()
{
	var dm = $("#domain").val();
	var dmid = $("#domainId").val();
	if (dm=="")
	{
		$.ligerDialog.warn('提示：请输入或选择Domain！');
		return;	
	}
	if (dmid=="")
	{
		$.ligerDialog.warn('提示：请先保存Domain！');
		return;	
	}
	var typeId = $("#FK_TYPE_ID").val();
	var chartId = $("#CHARTID").val();
	var cid = $("#cid").val();
	var bz = $("#BZ").val();
	var isBig = $("#isBig-txt").ligerGetRadioGroupManager().getValue();
	var isSwich = $("#isSwich-txt").ligerGetRadioGroupManager().getValue();
	var swichTypes = $("#swichType").ligerGetComboBoxManager().getValue();
	var swichTypesTxt = $("#swichTypeTxt").val();
	if (typeId==""||typeId=="root")
	{
		$.ligerDialog.warn('提示：请设置图表类型！');
		return;	
	}
	if (chartId=="")
	{
		$.ligerDialog.warn('提示：请设置图表调用名称！');
		return;	
	}
	$.post("lchart/conf/saveChart.do",{'dmid':dmid,'tpid':typeId,'cid':chartId,'id':cid,'bz':bz,isBig:isBig,isSwich:isSwich,swichTypes:swichTypes,swichTypesTxt:swichTypesTxt},function(res){
		if(res.success && res.data.length>0){
			$("#cid").val(res.data[0].ID);
			getBParm();
			RenderChart(chartId,"div1","");
		}else{
			$.ligerDialog.error('提示：'+res.msg);
		}
	});
}

function getDomainParams()
{
	$.post("lchart/conf/getParams.do",{'dmid':formObj.domainId.value},function(res){
		if(res.success){
			var iHtml="";
			$("#dmparamtab tr").each(function(){
				if($(this).attr("id")!="dptitle"){
					$(this).remove();
				}
			})
			for (var i=0;i<res.data.length;i++)
			{
				iHtml="<tr class='l-grid-row'>";
				iHtml+="<td class='l-grid-row-cell l-grid-row-cell-rownumbers' style='width:10%'><div class='l-grid-row-cell-inner' style='width:20px;text-align:center'>"+(i+1)+"</div></td>";
				iHtml+="<td class='l-grid-row-cell' ><div class='l-grid-row-cell-inner' style='text-align:left'>"+(res.data[i].MC)+"</div></td>";
				iHtml+="<td class='l-grid-row-cell'><div class='l-grid-row-cell-inner' style='text-align:left'>"+(res.data[i].MRZ)+"</div></td>";
				iHtml+="</tr>";
				$("#dmparamtab").append(iHtml);
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function getDataset()
{
	$.post("lchart/conf/getDataset.do",{'dmid':formObj.domainId.value},function(res){
		if(res.success){
			$("#dsparamtab tr").each(function(){
				if($(this).attr("id")!="dstitle"){
					$(this).remove();
				}
			})
			var iHtml="";
			for (var i=0;i<res.data.length;i++)
			{
				iHtml="<tr class='l-grid-row'>";
				iHtml+="<td class='l-grid-row-cell l-grid-row-cell-rownumbers' style='width:10%'><div class='l-grid-row-cell-inner' style='width:20px;text-align:center'>"+(i+1)+"</div></td>";
				iHtml+="<td class='l-grid-row-cell' ><div class='l-grid-row-cell-inner' style='text-align:left'>"+(res.data[i].MC)+"</div></td>";
				iHtml+="<td class='l-grid-row-cell'><div class='l-grid-row-cell-inner' style='text-align:left'>"+(res.data[i].LX)+"</div></td>";
				iHtml+="<td class='l-grid-row-cell'><div class='l-grid-row-cell-inner' style='text-align:left'>"+(res.data[i].KYSX)+"</div></td>";
				iHtml+="</tr>";
				$("#dsparamtab").append(iHtml);
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function getBParm()
{
	$.post("lchart/conf/getBParms.do",{'tpid':formObj.FK_TYPE_ID.value},function(res){
		if(res.success){
			OPENPARM="";
			var iHtml="";
			$("#menus").html("");
			for (var i=0;i<res.data.length;i++)
			{
				if (res.data[i].DATA_PARAM=="0")
				{
					iHtml="<div class='menu' style='cursor:pointer' id='BM"+res.data[i].ID+"' title='"+res.data[i].REMARK+"' onclick=setSParams('"+res.data[i].ID+"') >&nbsp;&nbsp;&nbsp;&nbsp;&diams;"+res.data[i].NAME+"</div>";
					iHtml+="<ul id='SM"+res.data[i].ID+"' style='display:none'></ul>";
				}
				else
				{
					iHtml="<div class='menu' style='cursor:pointer'  id='BM"+res.data[i].ID+"' title='"+res.data[i].REMARK+"' onclick=setDataParams('"+res.data[i].ID+"') >&nbsp;&nbsp;&nbsp;&nbsp;&diams;"+res.data[i].NAME+"</div>";
					iHtml+="<ul id='SHOW"+res.data[i].ID+"' style='display:none'></ul>";
					iHtml+="<ul id='SM"+res.data[i].ID+"' style='display:none'></ul>";
					iHtml+="<div id='SAVE"+res.data[i].ID+"' class='save' style='display:none'><button type='button' style='cursor:pointer' onclick=saveSet('"+res.data[i].ID+"')>保存</button><input id='TYPE"+res.data[i].ID+"' type='hidden' value=''/><input id='CODES"+res.data[i].ID+"' type='hidden' value=''/><input id='TYPES"+res.data[i].ID+"' type='hidden' value=''/><input id='PID"+res.data[i].ID+"' type='hidden' value=''/></div>";
				}
				$("#menus").append(iHtml);
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
	 
}

function setSParams(b)
{
	if ($("#SM"+b).css("display")=="block")
	{
		$("#SM"+b).css("display","none");
		return;
	}
	else if (OPENPARM.indexOf(b)!=-1)
	{
		$("#SM"+b).css("display","block");
		return;
	}
	
	$.post("lchart/conf/getSParms.do",{'bid':b},function(res){
		if(res.success){
			OPENPARM += b+","; 
			$("#SM"+b).css("display","block");
			var iHtml="";
			
			for (var i=0;i<res.data.length;i++)
			{
				iHtml="<li class='sli'><span class='sli_span'>"+res.data[i].CODE+"</span><div class='sli_div'>"+setInput(res.data[i])+"</div></li>";
				$("#SM"+b).append(iHtml); 
				if (res.data[i].COL_TYPE=="string"||res.data[i].COL_TYPE=="json"||res.data[i].COL_TYPE=="color"){
					$("#"+res.data[i].ID).ligerTextBox();
				}
				else if (res.data[i].COL_TYPE=="number"){
					$("#"+res.data[i].ID).ligerSpinner();
				}
				else if (res.data[i].COL_TYPE=="boolean"||res.data[i].COL_TYPE=="select"){
					$("#"+res.data[i].ID+"-txt").ligerComboBox();
				}
			}
			if (pageStatus=="modify") setSPValue(b);//填值
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function setSPValue(b)
{
	$.post("lchart/conf/getSParmsValues.do",{'bid':b,'cid':PUB_CHARTID},function(res){
		if(res.success){
			for (var i=0;i<res.data.length;i++)
			{
				if (res.data[i].COL_TYPE=="string"||res.data[i].COL_TYPE=="json"||res.data[i].COL_TYPE=="color"){
					$("#"+res.data[i].FK_PARAM_ID).ligerGetTextBoxManager().setValue(res.data[i].P_VALUE);
				}
				else if (res.data[i].COL_TYPE=="number"){
					$("#"+res.data[i].FK_PARAM_ID).val(res.data[i].P_VALUE);
				}
				else if (res.data[i].COL_TYPE=="boolean"||res.data[i].COL_TYPE=="select"){
					$("#"+res.data[i].FK_PARAM_ID+"-txt").ligerGetComboBoxManager().selectValue(res.data[i].P_VALUE);
				}
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function setDataParams(b)
{
	if ($("#SM"+b).css("display")=="block")
	{
		$("#SM"+b).css("display","none");
		$("#SHOW"+b).css("display","none");
		$("#SAVE"+b).css("display","none");
		return;
	}
	else if (OPENPARM.indexOf(b)!=-1)
	{
		$("#SM"+b).css("display","block");
		$("#SHOW"+b).css("display","block");
		$("#SAVE"+b).css("display","block");
		return;
	}
	
	$.post("lchart/conf/getSParms.do",{'bid':b},function(res){
		if(res.success){
			OPENPARM += b+","; 
			$("#SM"+b).css("display","block");
			
			$("#SAVE"+b).css("display","block");
			$("#TYPE"+b).val(res.data[0].PARAM_TYPE);
			var iHtml="";
			var codes="";
			var types="";
			for (var i=0;i<res.data.length;i++)
			{
				codes+=res.data[i].CODE+",";
				types+=res.data[i].COL_TYPE+",";
				iHtml="<li class='sli'><span class='sli_span'>"+res.data[i].CODE+"</span><div class='sli_div'>"+setSetInput(b,res.data[i])+"</div></li>";
				$("#SM"+b).append(iHtml); 
				if (res.data[i].COL_TYPE=="string"||res.data[i].COL_TYPE=="json"||res.data[i].COL_TYPE=="color"){
					$("#"+b+res.data[i].CODE).ligerTextBox();
				}
				else if (res.data[i].COL_TYPE=="number"){
					$("#"+b+res.data[i].CODE).ligerSpinner();
				}
				else if (res.data[i].COL_TYPE=="boolean"||res.data[i].COL_TYPE=="select"){
					$("#"+b+res.data[i].CODE+"-txt").ligerComboBox();
				}
			}
			$("#CODES"+b).val(codes);
			$("#TYPES"+b).val(types);
			//读取已存的参数集JSON
			$("#SHOW"+b).css("display","block");
			getSet(b,$("#cid").val(),$("#TYPE"+b).val());
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function getSet(b,cid,ptype)
{
	$.post("lchart/conf/getSetParms.do",{cid:cid,ptype:ptype},function(res){
		if(res.success){
			var iHtml="";
			for (var i=0;i<res.data.length;i++)
			{
				iHtml+="<li class='show'><div class='show_json' id='PV"+res.data[i].ID+"'>"+res.data[i].P_VALUE+"</div><div class='show_edit'><a class='l-a l-icon-edit' href=javascript:editSet('"+b+"','"+res.data[i].ID+"') title='编辑'><span>编辑</span></a><a class='l-a l-icon-del' href=javascript:delSet('"+b+"','"+ptype+"','"+res.data[i].ID+"') title='删除'><span>删除</span></a></div></li>";
			}
			$("#SHOW"+b).html(iHtml); 
		}else{
				$.ligerDialog.error('提示：数据读取失败！');
			}
	});
}
function delSet(b,ptype,id)
{
	if ($("#PID"+b).val()==id) $("#PID"+b).val("");
	$.post("lchart/conf/delSetParam.do",{id:id},function(res){
		if(res.success){
			getSet(b,$("#cid").val(),ptype);
			autorefresh();//自动刷新
		}
		else{
			$.ligerDialog.error('提示：数据删除失败！');
		}
	});
}

function editSet(b,id)
{
	$("#PID"+b).val(id);
	var pvalue = $("#PV"+id).html();
	
	//清空上一组属性值
	var codes = $("#CODES"+b).val().split(",");
	var types = $("#TYPES"+b).val().split(",");
	for (var i=0;i<codes.length;i++)
	{
		if (types[i]=="string"||types[i]=="json"||types[i]=="color"){
			$("#"+b+codes[i]).ligerGetTextBoxManager().setValue("");
		}
		else if (types[i]=="number"){
			$("#"+b+codes[i]).val("");
		}
		else if (types[i]=="boolean"||types[i]=="select"){
			$("#"+b+codes[i]+"-txt").ligerGetComboBoxManager().selectValue("");
		}
	}
	
	if (pvalue.length>2) pvalue = pvalue.substring(1,pvalue.length-1);
	//alert("{"+pvalue+"}");
	pvalue = $.parseJSON("{"+pvalue+"}");
	var ptype = "";
	var kv = "";
	var k ="";
	var v ="";
	for (var i=0;i<codes.length; i++)
	{
		try
		{
			k = codes[i];
			v = eval("pvalue."+k);
			ptype = $("#"+b+k).attr("ptype");
			if (ptype==undefined){
				$("#"+b+k+"-txt").ligerGetComboBoxManager().selectValue(v);
			}
			else if (ptype=="number"){
				$("#"+b+k).val(v);
			}
			else if (ptype=="string"||ptype=="json"||ptype=="color"){
				$("#"+b+k).ligerGetTextBoxManager().setValue(v);
			}
		}
		catch(e){}
	}
	
	
/*	alert(pvalue.length());
	pvalue = pvalue.split("',");
	
	for (var i=0;i<pvalue.length; i++)
	{
		kv = pvalue[i].split(":'");
		k = kv[0].replace(/'/gm,"");
		v = kv[1].replace(/'/gm,"")
		
	}
*/}

function saveSet(b)
{
	$("#SHOW"+b).css("display","block");
	var json = "{";
	var codes = $("#CODES"+b).val();
	var pType = $("#TYPE"+b).val();
	var pId = $("#PID"+b).val();
	var isNull = true;
	if (codes.length>1) codes = codes.substring(0,codes.length-1);
	codes = codes.split(",");
	for (var i=0;i<codes.length; i++)
	{
		if ($("#"+b+codes[i]).val()!="")
		{
			json+="\""+codes[i]+"\":\""+$("#"+b+codes[i]).val()+"\",";
			isNull = false;
		}
	}
	if (isNull) return;
	json = json.substring(0,json.length-1);
	json+="}";
	$.post("lchart/conf/saveSetParam.do",{cid:$("#cid").val(),ptype:pType,pvalue:json,pid:pId},function(res){
		if(res.success){
			//更新
			$("#PID"+b).val("");
			var ptype = "";
			for (var i=0;i<codes.length; i++)
			{
				ptype = $("#"+b+codes[i]).attr("ptype");
				if (ptype==undefined){
					$("#"+b+codes[i]+"-txt").ligerGetComboBoxManager().selectValue("");
				}
				else if (ptype=="number"){
					//$("#"+b+codes[i]).ligerGetSpinnerManager().setValue("");
					$("#"+b+codes[i]).val("");
				}
				else if (ptype=="string"||ptype=="json"||ptype=="color"){
					$("#"+b+codes[i]).ligerGetTextBoxManager().setValue("");
				}
			}
			getSet(b,$("#cid").val(),pType);
			autorefresh();//自动刷新
		}else{
			$.ligerDialog.error('提示：数据保存失败！');
		}
	});
}

function setInput(data)
{
	var result = "";
	if (data.COL_TYPE=="string"||data.COL_TYPE=="json")
	{
		result = "<input id='"+data.ID+"' pname='"+data.CODE+"' ptype='"+data.PARAM_TYPE+"' ltype='text' title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' ligerui={onChangeValue:function(){saveParam('"+data.COL_TYPE+"','"+data.ID+"')}} />";
	}
	else if (data.COL_TYPE=="number")
	{
		result = "<input id='"+data.ID+"' pname='"+data.CODE+"' ptype='"+data.PARAM_TYPE+"' ltype='spinner' title='"+data.REMARK+"' onblur=saveParam('"+data.COL_TYPE+"','"+data.ID+"') validate={maxlength:"+data.COL_LENGTH+"} ligerui={type:'int'"+setInitNumber(data.COL_DEFVALUE)+setMinNumber(data.COL_SMALL)+setMaxNumber(data.COL_BIG)+"}>";
	}
	else if (data.COL_TYPE=="boolean")
	{
		result = "<input id='"+data.ID+"-txt' pname='"+data.CODE+"' ptype='"+data.PARAM_TYPE+"' ltype='select' title='"+data.REMARK+"' ligerui={onSelected:function(){saveParam('"+data.COL_TYPE+"','"+data.ID+"')},valueFieldID:'"+data.ID+"'"+setInitNumber(data.COL_DEFVALUE)+",data:[{id:'0',text:'否'},{id:'1',text:'是'}]}>";
	}
	else if (data.COL_TYPE=="color")
	{
		result = "<input id='"+data.ID+"' pname='"+data.CODE+"' ptype='"+data.PARAM_TYPE+"' ltype='text' title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' ligerui={onChangeValue:function(){saveParam('"+data.COL_TYPE+"','"+data.ID+"')},iconItems:[{icon:'edit',click:function(val,e,srcObj){showColor(val,e,srcObj)}}]}>";
	}
	else if (data.COL_TYPE=="select")
	{
		result = "<input id='"+data.ID+"-txt' pname='"+data.CODE+"' ptype='"+data.PARAM_TYPE+"' ltype='select'  title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' ligerui={valueFieldID:'"+data.ID+"',data:"+data.BIND_DATA+",onSelected:function(){saveParam('"+data.COL_TYPE+"','"+data.ID+"')}}>";
	}
	return result;
}

function setSetInput(b,data)
{
	var result = "";
	if (data.COL_TYPE=="string"||data.COL_TYPE=="json")
	{
		result = "<input id='"+b+data.CODE+"' pname='"+data.CODE+"' ptype='"+data.COL_TYPE+"'  ltype='text' title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' />";
	}
	else if (data.COL_TYPE=="number")
	{
		result = "<input id='"+b+data.CODE+"' pname='"+data.CODE+"' ptype='"+data.COL_TYPE+"' ltype='spinner' title='"+data.REMARK+"' validate={maxlength:"+data.COL_LENGTH+"} ligerui={type:'int'"+setInitNumber(data.COL_DEFVALUE)+setMinNumber(data.COL_SMALL)+setMaxNumber(data.COL_BIG)+"}>";
	}
	else if (data.COL_TYPE=="boolean")
	{
		result = "<input id='"+b+data.CODE+"-txt' pname='"+data.CODE+"' ptype='"+data.COL_TYPE+"' ltype='select' title='"+data.REMARK+"' ligerui={valueFieldID:'"+b+data.CODE+"'"+setInitNumber(data.COL_DEFVALUE)+",data:[{id:'0',text:'否'},{id:'1',text:'是'}]}>";
	}
	else if (data.COL_TYPE=="color")
	{
		result = "<input id='"+b+data.CODE+"' pname='"+data.CODE+"' ptype='"+data.COL_TYPE+"' ltype='text' title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' ligerui={iconItems:[{icon:'edit',click:function(val,e,srcObj){showColor(val,e,srcObj)}}]}>";
	}
	else if (data.COL_TYPE=="select")
	{
		result = "<input id='"+b+data.CODE+"-txt' pname='"+data.CODE+"' ptype='"+data.COL_TYPE+"' ltype='select'  title='"+data.REMARK+"' validate='{maxlength:"+data.COL_LENGTH+"}' ligerui={valueFieldID:'"+b+data.CODE+"',data:"+data.BIND_DATA+"}>";
	}
	return result;
}

function saveParam(pType,id)
{
	if ($("#"+id).val()!=undefined)
	{
		var vType = "";
		var pName = "";
		if ($("#cid").val()=="") return;
		if (pType=="boolean"||pType=="select")
		{
			vType = $("#"+id+"-txt").attr("ptype");
			pName = $("#"+id+"-txt").attr("pname");
		}
		else
		{
			vType = $("#"+id).attr("ptype");
			pName = $("#"+id).attr("pname");
		}
		
		//if (vType!="chart") return;
		//else
		//{
		$.post("lchart/conf/saveParam.do",{pid:id,cid:$("#cid").val(),ptype:vType,pname:pName,pvalue:$("#"+id).val()},function(res){
			if(res.success){
				if (res.update)	autorefresh();//自动刷新
			}else{
				$.ligerDialog.error('提示：数据保存失败！');
			}
		});
		//}
	}
}

function setInitNumber(num)
{
	//alert(num);
	if (num==null) return "";
	else return ",initValue:'"+num+"'";
}

function setMinNumber(num)
{
	//alert(num);
	if (num==null) return "";
	else return ",minValue:'"+num+"'";
}

function setMaxNumber(num)
{
	//alert(num);
	if (num==null) return "";
	else return ",maxValue:'"+num+"'";
}

function showColor(val,e,srcObj)
{
	_this = e;
	top.$.dialog({
		width:'340px',
		height:'190px',
		lock:true,
		parent:api,
		title:"颜色选择",
		content: 'url:pub/chart/chart/color_detail.jsp',
		data:{window:window}
	});
}

function setColorValue(retvalue){
	try {
		_this.setValue(retvalue);
	} catch (e) {
		$("input[name='"+_this.id+"']").val(retvalue);
		_this.src=retvalue;
	}
}

function manageParam(){
	var dm = formObj.domain.value;
	var v_dmid = formObj.domainId.value;
	if (v_dmid==""||dm=="") {
		$.ligerDialog.warn('提示：请确定Domain后再操作！');
		return;
	}
	top.$.dialog({
		width:'800px',
		height:'400px',
		lock:true,
		parent:api,
		title:"Domain参数管理",
		content: 'url:pub/chart/chart/lmanage_param.jsp?domainId='+v_dmid,
		data:{window:window}
	});
}

function manageDomain(){
	top.$.dialog({
		width:'400px',
		height:'300px',
		lock:true,
		parent:api,
		title:"Domain管理",
		content: 'url:pub/chart/chart/lmanage_domain.jsp',
		data:{window:window},
		cancel:true
	});
}

function manageDataset()
{
	var v_dmid = formObj.domainId.value;
	if (v_dmid=="") {
		$.ligerDialog.warn('提示：请确定Domain后再操作！');
		return;
	}
	top.$.dialog({
		width:'800px',
		height:'300px',
		lock:true,
		parent:api,
		title:"数据集管理",
		content: 'url:pub/chart/chart/lmanage_dataset.jsp?domainId='+v_dmid,
		data:{window:window}
	}).max();
}

function setChartIcon(val)
{
	for (var i=0;i<CTYPES.length;i++)
	{
		if (CTYPES[i].ID==val)
		{
			$.getJSON("chart/type/getCommonChartType.do?id="+val,function(res){
				$("#swichType").ligerGetTextBoxManager().setData(res.data);
			})
			$("#chartView").attr("src",CTYPES[i].ICON);
			break;
		}
	}
}

function autorefresh()
{
	var auto = $("#autorefresh").attr("checked");
	if (auto=="checked")
	{
		refreshview();
	}
}

function refreshview()
{
	var cid = $("#CHARTID").val();
	
	var chartRef = FusionCharts(cid);
	if (chartRef==undefined) RenderChart(cid,"div1","");
	else RefreshChart(cid,"{}");
}

function getSwichType(val,text){
	$("#swichTypeTxt").val(text);
}
</script>
</head>
<body onload="onLoad()">
	<form id="formObj" action="" getAction="" onkeydown="if (event.keyCode==13) return false;">
	  <input type="hidden" name="domainId" id="domainId"/>
	  <input type="hidden" name="cid" id="cid"/>
    <div style="padding:10px;">
    	<div style="width:30%;float:left;">
            <div class="wrap">
                <div class="cnwp">
                    <div class="caption_div">Domain设置<a href="javascript:void(0)" onclick="manageDomain()"><font color="#FF9933">【管理】</font></a></div>
                    <div style="height:30px; overflow:auto;">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td align="right">Domain&nbsp;</td>
                            <td>
                            <input list="dmlist" id="domain" ltype="text" validate="{required:true}"/>
                            <datalist id="dmlist">
                            </datalist>
                            </td>
                            <td align="center"><button id="btn01" type="button" onclick="saveDomain()" style="cursor:pointer">第1步</button></td>
                        </tr>
                        <tr style="display:none">
                          <td align="right" colspan="3" >
                           <font color="#FF0000">注：参数默认值<br />
                            <span>$S{user.id}即用户Id;</span><br />
                            <span style="white-space:nowrap;">$S{user.orgcode}即用户机构id;</span>
							</font>
                          </td>
                        </tr>
                    </table>
                  </div>
                  <div style="height:170px; overflow:auto;">
                  <fieldset class="l-fieldset">
			<legend class="l-legend">
				<span>
					全局参数<a href="javascript:void(0)" onclick="manageParam()"><font color="#FF9933">【管理】</font></a>
				</span>
			</legend>
                  	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                        		<tr>
                        			<td align="left" style="width:100%">
										<div class="l-grid-body l-grid-body2 l-scroll ">
											<div style="width: 100%;" class="l-grid-body-inner">
												<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
													<tbody id="dmparamtab">
														<tr id="dptitle" class="l-grid-hd-row l-grid-header" style="height: 23px;" >
															<td class="l-grid-hd-cell l-grid-hd-cell-rownumbers" style="width:10%"> 
																<div class="l-grid-hd-cell-inner;" style="width:26px">序号</div>
															</td>
															<td  class="l-grid-hd-cell" style="width:50%">
																<div class="l-grid-row-cell-inner;" style="text-align:center">参数名称</span></div>
															</td>
															<td class="l-grid-hd-cell" style="width:40%">
																<div class="l-grid-row-cell-inner;" style="text-align:center">默认值</span></div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
                        			</td>
                        		</tr>
                        	</table>
                        	</fieldset>
                  </div>
                </div>
            </div>
        </div>
        <div style="width:70%;white-space:nowrap;float:right;">
        	<div style="width:40%;float:left;">
                <div class="wrap">
                    <div class="cnwp">
                        <div class="caption_div">数据集设置<a href="javascript:void(0)" onclick="manageDataset()"><font color="#FF9933">【管理】</font></a></div>
                        <div style="height:200px; overflow:auto;text-align:center">
                        <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                        		<tr>
                        			<td align="left" style="width:90%">
										<div class="l-grid-body l-grid-body2 l-scroll ">
											<div style="width: 100%;" class="l-grid-body-inner">
												<table class="l-grid-body-table" cellpadding="0" cellspacing="0">
													<tbody id="dsparamtab">
														<tr id="dstitle" class="l-grid-hd-row l-grid-header" style="height: 23px;" >
															<td class="l-grid-hd-cell l-grid-hd-cell-rownumbers" style="width:10%"> 
																<div class="l-grid-hd-cell-inner;" style="width:26px">序号</div>
															</td>
															<td  class="l-grid-hd-cell" style="width:15%">
																<div class="l-grid-row-cell-inner;" style="text-align:center">&nbsp;&nbsp;名称&nbsp;&nbsp;</span></div>
															</td>
															<td class="l-grid-hd-cell" style="width:15%">
																<div class="l-grid-row-cell-inner;" style="text-align:center">&nbsp;&nbsp;类型&nbsp;&nbsp;</span></div>
															</td>
															<td class="l-grid-hd-cell" style="width:60%">
																<div class="l-grid-row-cell-inner;" style="text-align:center">&nbsp;&nbsp;可用属性&nbsp;&nbsp;</span></div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
                        			</td>
                        		</tr>
                        	</table>
                      </div>
                    </div>
                </div>
            </div>
            <div style="width:60%;white-space:nowrap;float:right;">
                <div class="wrap">
                    <div class="cnwp">
                        <div class="caption_div">Chart设置</div>
                        <div style="height:200px; overflow:auto;">
                       	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="15%" align="right">图表类型&nbsp;</td>
                                <td colspan="5"><input type="text" id="FK_TYPE_ID_TXT" ltype="select" validate="{required:true}" ligerui="{valueFieldID:'FK_TYPE_ID',value:'', data: <u:dict sql='select t.id,t.name as text from SYS_CHART_TYPE t where t.swf_name is not null order by t.type'/>,onSelected:setChartIcon}"/></td>
                            </tr>
                            <tr>
                              <td align="right">调用名称&nbsp;</td>
                                <td align="center" colspan="3">
                                	<input id="CHARTID" name="CHARTID" value="" ltype="text" validate="{required:true}" />
                                </td>
                                <td width="40%" rowspan="3" colspan="2" align="center" valign="middle" >
                                	<img id="chartView" width="160" height="120" border="0" src="" />
                                </td>
                            </tr>
                            <tr>
                            	<td align="right">放大&nbsp;</td>
                                <td><u:combo name="isBig" code="sys_sf" ltype="radioGroup" attribute="initValue:'1'"></u:combo></td>
                                <td align="right">切换&nbsp;</td>
                                <td><u:combo name="isSwich" code="sys_sf" ltype="radioGroup" attribute="initValue:'0'"></u:combo></td>
                            </tr>
                            <tr>
                                <td align="right">切换类型&nbsp;</td>
                                <td colspan="3"><input ltype="select" id="swichType" name="swichType" ligerui="{isMultiSelect:true,onSelected:getSwichType}">
                                <input type="hidden" id="swichTypeTxt" name="swichTypeTxt">
                                <td>
                            </tr>
                            <tr>
                                <td align="right">备注&nbsp;</td>
                                <td colspan="5"><textarea id="BZ" name="BZ" rows="1" class="l-textarea"></textarea></td>
                            </tr>
                            <tr>
                              <td align="right">&nbsp;</td>
                              <td align="center" colspan="5"><button style="cursor:pointer" type="button" onclick="saveChart()">第2步</button></td>
                            </tr>
                        </table>
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="padding:10px;">
        <div style="width:30%;float:left;">
            <div class="wrap">
                <div class="cnwp">
                    <div class="caption_div">参数设置</div>
                    <div style="height:420px; overflow:auto; text-align:center" id="menus">图表类型确定后自动加载...</div>
                </div>
            </div>
        </div>
        <div style="width:70%;white-space:nowrap;float:right;">
            <div class="wrap" style="width:98.8%">
                <div class="cnwp">
                    <div class="caption_div">Chart预览</div>
                    <div style="height:20px">　 　预览刷新选项：<input type="checkbox" id="autorefresh" name="autorefresh" value="1" /><label for="autorefresh">自动刷新</label> 　　　<span style="cursor:pointer; color:#FF9933" onclick="refreshview()">【点击此处手动刷新】</span>　　</div>
                    <div style="height:400px; overflow:auto;" id="review">
                        <div id="div1" style="width:100%; height:100%; border:hidden;" align="center"><br><br><font color="#999999">正在加载图形数据,请稍候...</font></div>
                        <!--chart:chart chartNum="test01" renderAt="div1" width="100%" height="100%"/-->
                    </div>
                </div>
            </div>
        </div>
    </div>
	</form>
</body>
</html>
