function getDomains()
{
	$.post("lchart/conf/getDomains.do",{},function(res){
		if(res.success){
			for(var i=0; i<res.data.length;i++)
			{
				dmlist.innerHTML+="<option value='"+res.data[i].MC+"'>";
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function saveDomain()
{
	$.post("lchart/conf/saveDomain.do",{'mc':formObj.domain.value},function(res){
		if(res.success && res.data.length>0){
			formObj.domainId.value=res.data[0].ID;
			getDomainParams();
		}else{
			$.ligerDialog.error('提示：数据保存失败！');
		}
	});
}

function getDomainParams()
{
	$.post("lchart/conf/getParams.do",{'dmid':formObj.domainId.value},function(res){
		if(res.success){
			var headHtml="<tr>";
                headHtml+="<td align='center'>序号</td>";
				headHtml+="<td align='center'>参数名称</td>";
				headHtml+="<td align='center'>默认值</td>";
				headHtml+="</tr>";
			dmparamtab.innerHTML = headHtml;
			var iHtml="";
			for (var i=0;i<res.data.length;i++)
			{
				iHtml="<tr>";
				iHtml+="<td align='center'>"+(i+1)+"</td>";
				iHtml+="<td align='center'>"+(res.data[i].MC)+"</td>";
				iHtml+="<td align='center'>"+(res.data[i].MRZ)+"</td>";
				iHtml+="</tr>";
                dmparamtab.innerHTML+=iHtml;
			}
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function manageParam(){
	var v_dmid = formObj.domainId.value;
	if (v_dmid=="") {
		$.ligerDialog.warn('提示：请确定Domain后再操作！');
		return;
	}
	top.$.dialog({
		width:'400px',
		height:'300px',
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
		data:{window:window}
	});
}