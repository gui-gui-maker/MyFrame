<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="report.config.jsp"%>
<script language="javascript">
var ok = "true" ;
var MRViewer ;
function   getNewDate(strInterval,NumDay,dtDate)
{   
	
	var dtTmp=new Date(dtDate.replace(/-/g,"/"));
	if (isNaN(dtTmp)) {
		dtTmp=new Date();
	}
	switch (strInterval) {
		case "s":return new Date(Date.parse(dtTmp)+(1000*NumDay));
		case "n":return new Date(Date.parse(dtTmp)+(60000*NumDay));
		case "h":return new Date(Date.parse(dtTmp)+(3600000*NumDay));
		case "d":return new Date(Date.parse(dtTmp)+(86400000*NumDay));
		case "w":return new Date(Date.parse(dtTmp)+((86400000*7)*NumDay));
		case "m":return new Date(dtTmp.getFullYear(),(dtTmp.getMonth())+NumDay,dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
		case "y":return new Date((dtTmp.getFullYear()+NumDay),dtTmp.getMonth(),dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
	}
}
function doSubmit()
{
	document.all.ptBtn1.disabled = true;
	document.all.ptBtn2.disabled = true;
	if(confirm("确定提交报告？")){
		var Obj=formObj;
		var url = "/cfdinfo?CONTROLLER=cfd.tsjy.checkDevice.PostBack&op=input&report_type=${report_type}&fk_inspection_info_id=${inspection_info_id}&device_id=${device_id}";
		MRViewer.postbackurl = document.location.protocol + '//' + document.location.host + url;
		//MRViewer.postbackurl=url;
		MRViewer.PostBack();
		if(ok=="false")
			window.returnValue="yes#false";
		else 
			window.returnValue="yes";
	} else {
		document.all.ptBtn1.disabled = false;
		document.all.ptBtn2.disabled = false;
	}
	//Obj.action = url;
	//Obj.submit();
}

function doSave()
{
	//var Obj=formObj;
	//var url = "/cfdinfo?CONTROLLER=cfd.tsjy.checkDevice.PostBack&op=input&isSave=save&report_type=${report_type}&fk_inspection_info_id=${inspection_info_id}&device_id=${device_id}";
	//MRViewer.postbackurl = document.location.protocol + '//' + document.location.host + url;
	//MRViewer.postbackurl=url;
	//var urlPara = "?flow_num=${param.flow_num}&report_type=${param.report_type}&activity_id=${param.activity_id}&ins_info_id=${param.ins_info_id}&device_id=${param.device_id}"
	//MRViewer.postbackurl = protocol + '//' + host + sid + saveURL+urlPara;
	
	
	//MRViewer.PostBack();
	//window.returnValue="reOpen";
	//Obj.action = url;
	//Obj.submit();
	
	var url = $("base").attr("href")+"department/basic/reportInfoInput.do" ;
	var urlPara = "?flow_num=${param.flow_num}&report_type=${param.report_type}&activity_id=${param.activity_id}&ins_info_id=${param.ins_info_id}&device_id=${param.device_id}"
		//MRViewer.postbackurl = protocol + '//' + host + sid + saveURL+urlPara;
	MRViewer.postbackurl = url+urlPara;
	MRViewer.PostBack();
}

function MRViewer_BeforePostBack() 


{
	
	disableButton();
	failed = MRViewer.CheckRequired();
	if (failed != "")
	{
		//alert("下列内容必须输入：\r\n" + failed);
		//enableButton();
		//document.all.ptBtn1.disabled = false;
		//document.all.ptBtn2.disabled = false;
		//return false;//返回false终止数据提交
	} 
	return true;
}

function MRViewer_AfterPostBack()
{

	enableButton();
	//api.close();
}


//设置报表属性
function setReports()
{
	

	//设置显示页
	MRViewer = document.all("MRViewer") ;

	MRViewer.Zoom(120);
	

	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	var report_item = "${REPORTPARA.report_item}";
	var ss = report_item.split(",");
	var status = false;
	for(var i=0;i<pagecount;i++){
		//判断在审核签发状态下编辑框不能修改
		page = report.Pages(i);
		objCount = page.ObjectCount;
		
		/*if("${param.type}"!="input"){
			
			for(var j=0;j<objCount;j++){
				obj = page.Objects(j); 
				if(obj!=null && obj.Prop("TypeName")=="Memo"){
						obj.Prop("PostbackOption.Valuekind") = 0;
				}
			}
			
		}*/
		
		
		for(var j=0;j<ss.length;j++){
			if(i+1==ss[j])
			{
				status = true;
				page = report.Pages(i);
				page.Prop("Visible") = "True";
				break;
			}
			else
			{
				try{
				page = report.Pages(i);
				page.Prop("Visible") = "False";
				} catch(e){}
			}
		}
	}
	if(!status)
	{
		alert("报告模板上没有对应的索引！");
		close();
		return;
	}
	doSubmitsetReports();
	//===============================
	MRViewer.Preview();
}
//提交前把所有报表上的数据都设置为可提交
function doSubmitsetReports()
{
	//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	var ss = '${REPORTPARA.report_item}'.split(",");
	for(var i=0;i<pagecount;i++){
		try{
			page = report.Pages(i);
			objCount = page.ObjectCount;
			for(var j=0;j<objCount;j++){
				obj = page.Objects(j); 
				if(obj!=null && obj.Prop("TypeName")=="Memo"){
					if(obj.Prop("memo").indexOf("\"")!=-1&&obj.Prop("memo").indexOf("TotalP")==-1)
					{
						obj.Prop("PostbackOption.Key") = obj.Prop("memo").split("\"")[1];
					}
				}
				if(obj.Prop("memo").indexOf("[BGDS.\"COM_NAME\"]")!=-1){
					if(com_name.indexOf("尊宴餐饮娱乐有限公司")!=-1){
						obj.Prop("Memo") = "成都珺龍尊宴餐饮娱乐有限公司";
					}else{
						obj.Prop("Memo") = com_name;
					}
				}
				if(obj.Prop("memo").indexOf("[BGDS.\"DEVICE_USE_PLACE\"]")!=-1){
					if(device_use_place.indexOf("子树村七组186号")!=-1){
						obj.Prop("Memo") = "锦江区棬子树村七组186号";
					}else{
						obj.Prop("Memo") = device_use_place;
					}
				}
			}
		} catch(e){}
	}
}

/*function uploadfile(objId,activeName)
{
	var fileOparam= new Object();
	fileOparam.fileNo = 1;
	fileOparam.loadType = ["jpg","gif","jpeg"];
	var wObj= window.showModalDialog("/pub/jsp/upload/upload_file.htm",fileOparam,"dialogwidth:320px;dialogheight:230px;help=no;status=no;center=yes");
	
	if(wObj!=undefined)
	{
		var upArrLen = wObj.length;
		for(var i=0;i<upArrLen;i++)
		{
			if(i==0)
			{
				v2 = MRViewer.GetObjByName(activeName);
				if(v2){
					v2.Prop("Memo") = wObj[0][10];
				}
				MRViewer.Populate();
				
			}
		}
	}
}*/

function uploadfile(objId,activeName){
	var width = 400;
	var height = 600;
	var nameP = activeName+"P";
	var picObj = MRViewer.GetObjByName(nameP);
	if(picObj){
		height = picObj.Prop("Height");
		width = picObj.Prop("Width");
	}

	var wObj= window.showModalDialog("${pageContext.request.contextPath}/app/flow/report/upload.jsp?status=add&pic_type=report&ins_info_id=${param.ins_info_id}&item_name="+activeName+"&width="+width+"&height="+height,[],"dialogwidth:600px;dialogheight:480px;help=no;status=no;center=yes");
	if(wObj!=undefined)
	{
		v2 = MRViewer.GetObjByName(activeName);
		if(v2){
			v2.Prop("Memo") = wObj;
		}
		MRViewer.Populate();
	}
	
}

//画图
function MRViewer_OnObjectClick(objId)
{

	
	var activeName = MRViewer.GetObjectProp(objId,"name");
	
	

	
	if(activeName==undefined){
		return;
	}
	
	if((activeName.indexOf("picturetext")!=-1 || activeName.indexOf("PICTURETEXT")!=-1) && activeName.substring(activeName.length-1)!="P")
	{	
		/*if(!confirm("是否打开绘图编辑器？"))
		{
			uploadfile(objId,activeName);
		} 
		else 
		{
			v2 = MRViewer.GetObjByName(activeName);
			
			var valuex = "";
			if(v2){
				valuex = v2.Prop("Memo");
			}
			var nameP = activeName+"P";
			v3 = MRViewer.GetObjByName(nameP);
			var height = "800"; 
			var width = "600";
			if(v3){
				height = v3.Prop("Height");
				width = v3.Prop("Width");
			}
			var w=window.screen.availWidth;
			var h=window.screen.availHeight;
			
			//var wObj= window.open("/tsjy/jsp/pic_tools/draw.jsp?old_vlaue="+valuex+"&height="+height+"&width="+width,[],"status=yes,location=yes,resizable=yes,center=yes");
			var wObj= window.showModalDialog("/app/draw_pic/draw.jsp?old_vlaue="+valuex+"&height="+height+"&width="+width,[],"dialogwidth:"+w+";dialogHeight:"+h+";help=no;status=no;center=yes");
			
			
			
			var urls = "tsjy/jsp/pic_tools/getFileName.jsp";
			temp = SendXML(urls);
			alert(temp);
			if(temp!=undefined && temp!=""){
				v2 = MRViewer.GetObjByName(activeName);
				if(v2){
					v2.Prop("Memo") = temp;
				}
				MRViewer.Populate();
			}
		}*/
		
/*
		if(wObj!=undefined)
		{
			if(wObj!=""){
				v2 = MRViewer.GetObjByName(activeName);
				if(v2){
					v2.Prop("Memo") = wObj;
				}
				MRViewer.Populate();
			}
		}*/
		uploadfile(objId,activeName);
	}
	if(activeName.indexOf("check")!=-1){
		/*if(activeName==undefined){
			return;
		}
		var value = MRViewer.GetObjectProp(objId, "Memo");
		if (value=="√"){
			v2 = MRViewer.GetObjByName(activeName+"A");
			if(v2){
				v2.Prop("Memo") = "";
			}
			MRViewer.SetObjectProp(objId, "Memo", "");
		} else {
			v2 = MRViewer.GetObjByName(activeName+"A");
			if(v2){
				v2.Prop("Memo") = "　";
			}
			MRViewer.SetObjectProp(objId, "Memo", "√");
		}
		MRViewer.Populate();*/
		
		if(activeName==undefined){
			return;
		}
		var value = MRViewer.GetObjectProp(objId, "Memo");
		if (value=="√"){
			 MRViewer.GetObjByName(activeName).Prop("Memo") = "";
		} else {
			 MRViewer.GetObjByName(activeName).Prop("Memo") = "√";
			
			
		}
		MRViewer.Populate();
		
	}
	if(activeName.indexOf("time")!=-1){
		if(activeName==undefined){
			return;
		}
		var ObjName = activeName+"A";
		 v2 = MRViewer.GetObjByName(ObjName);
		 if(v2){
			 v2.Prop("Memo") = "/";
			 MRViewer.Populate();
		 }
    }
}

function MRViewer_OnObjectEdited(objId,objvalue)//objvalue为控件的值
{
	//修改关联页的检验日期和下次检验日期
	if (MRViewer.GetObjectProp(objId,"PostbackOption.key")=="INSPECTION_DATE")//修改检验日期，进行关联修改
	{
		//alert(objValue)
		//MRViewer.setVariable('< %= reportsEn.getStringValue("mrdataset",0)%>."INSPECTION_DATE"', objvalue);
		//RViewer.setVariable('< %= reportsEn.getStringValue("mrdataset",0)%>."LAST_INSPECTION_DATE"', getNewDate("d",< % =reportsEn.getStringValue("interval",0)%>,objvalue).toLocaleDateString());
		//MRViewer.preview();
	}

	if (MRViewer.GetObjectProp(objId,"PostbackOption.key")=="INSPECTION_CONCLUSION")
	{
		if(objvalue.indexOf("不合格")!= -1 || objvalue.indexOf("停止运行")!= -1 || objvalue.indexOf("5")!= -1 || objvalue.indexOf("暂停运行")!= -1){
			alert("是否确定检验结论为“不合格”？");
			ok = "false" ;
		} else {
			ok = "true" ;
		}
	}
	
	var activeName = MRViewer.GetObjectProp(objId,"name");
	if(activeName==undefined){
		return;
	}
	if(activeName.indexOf("selectRow")!=-1)
	{
		if(activeName==undefined){
			return;
		}
		var ObjName = activeName.substring(0,6);
		v2 = MRViewer.GetObjByName(ObjName+"A");
		if(v2){
			v2.Prop("Memo") = objvalue;
		}
		v3 = MRViewer.GetObjByName(ObjName+"B");
		if(v3){
			v3.Prop("Memo") = objvalue;
		}
		v4 = MRViewer.GetObjByName(ObjName+"C");
		if(v4){
			v4.Prop("Memo") = objvalue;
		}
		v5 = MRViewer.GetObjByName(ObjName+"D");
		if(v5){
			v5.Prop("Memo") = objvalue;
		}
		v6 = MRViewer.GetObjByName(ObjName+"E");
		if(v6){
			v6.Prop("Memo") = objvalue;
		}
		v7 = MRViewer.GetObjByName(ObjName+"F");
		if(v7){
			v7.Prop("Memo") = objvalue;
		}
		v8 = MRViewer.GetObjByName(ObjName+"G");
		if(v8){
			v8.Prop("Memo") = objvalue;
		}
		v9 = MRViewer.GetObjByName(ObjName+"H");
		if(v9){
			v9.Prop("Memo") = objvalue;
		}
		
		MRViewer.Populate();
	}

	if(activeName.indexOf("linkage")!=-1)
	{
		if(activeName==undefined){
			return;
		}
		var ObjName = activeName.substring(0,activeName.length-1);
		if(objvalue=="不符合"){
			v2 = MRViewer.GetObjByName(ObjName);
			
			if(v2){
				v2.Prop("Memo") = "不合格";
				MRViewer.Populate();//使修改后的结果生效
			}
		}
	}
	
	
	if(activeName.indexOf("change")!=-1)
	{
		if(activeName==undefined){
			return;
		}
		//var ObjName = activeName+"S";
		
	
		
		
		
		var ObjName = activeName.substring(0,activeName.length-1);
		
		
	    var activeName = MRViewer.GetObjectProp(objId,"name");
		if(activeName.indexOf("change")!=-1)
		{
			if(objvalue=="符合"){
	            v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }		
			} else if (objvalue=="符合要求"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			}else if (objvalue=="无此项"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	    		    if(device_type == "3"){
	    		    	v2.Prop("Memo") = "-";
	    		    }else{
	    		    	v2.Prop("Memo") = "无此项";
	    		    }
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			} else if (objvalue=="/"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "-";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			}
			else if (objvalue=="资料确认符合"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			}else if (objvalue=="资料符合要求"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			}
			else if(objvalue=="不符合")
			{
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "不合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			    
				for(var i = 1 ; i <= 24 ; i++){
					var va = MRViewer.GetObjByName(activeName+"A");
					var vav = "";
					if(va){
						vav = va.Prop("Memo");
						
					}
					var vb = MRViewer.GetObjByName(activeName+"B");
					var vab = "";
					if(vb){
						vab = vb.Prop("Memo");
					}
					var num = i;
					if (i < 10 ){
						num = "0"+i ;
					}
					var objNames = "changeA0"+num+"A" ;
					v2 = MRViewer.GetObjByName(objNames, '${REPORTPARA.total_page}' ); //total_page
					if(v2){
						if(v2.Prop("Memo")!=""){
							continue;
						}
						v2.Prop("Memo") = vav;

						v2 = MRViewer.GetObjByName("changeA0"+num+"C",'${REPORTPARA.total_page}');
						if(v2){
							v2.Prop("Memo") = i;
						}
						MRViewer.Populate('${REPORTPARA.total_page}');
						break;
					}
				}
			}else if(objvalue=="不符合要求"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "不合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			    
				for(var i = 1 ; i <= 24 ; i++){
					var va = MRViewer.GetObjByName(activeName+"A");
					var vav = "";
					if(va){
						vav = va.Prop("Memo");
						
					}
					var vb = MRViewer.GetObjByName(activeName+"B");
					var vab = "";
					if(vb){
						vab = vb.Prop("Memo");
					}
					var num = i;
					if (i < 10 ){
						num = "0"+i ;
					}
					var objNames = "changeA0"+num+"A" ;
					v2 = MRViewer.GetObjByName(objNames, '${REPORTPARA.total_page}' ); //total_page
					if(v2){
						if(v2.Prop("Memo")!=""){
							continue;
						}
						v2.Prop("Memo") = vav;

						v2 = MRViewer.GetObjByName("changeA0"+num+"C",'${REPORTPARA.total_page}');
						if(v2){
							v2.Prop("Memo") = i;
						}
						MRViewer.Populate('${REPORTPARA.total_page}');
						break;
					}
				}
			}else if(objvalue=="资料不符合要求"){
				v2 = MRViewer.GetObjByName(ObjName);//找到第8页的指定Memo
			    if (v2){
	    		    //alert(v2.Prop("Name"));
	                v2.Prop("Memo") = "不合格";
	    		    MRViewer.Populate();//使修改后的结果生效
			    }
			    
				for(var i = 1 ; i <= 24 ; i++){
					var va = MRViewer.GetObjByName(activeName+"A");
					var vav = "";
					if(va){
						vav = va.Prop("Memo");
						
					}
					var vb = MRViewer.GetObjByName(activeName+"B");
					var vab = "";
					if(vb){
						vab = vb.Prop("Memo");
					}
					var num = i;
					if (i < 10 ){
						num = "0"+i ;
					}
					var objNames = "changeA0"+num+"A" ;
					v2 = MRViewer.GetObjByName(objNames, '${REPORTPARA.total_page}' ); //total_page
					if(v2){
						if(v2.Prop("Memo")!=""){
							continue;
						}
						v2.Prop("Memo") = vav;

						v2 = MRViewer.GetObjByName("changeA0"+num+"C",'${REPORTPARA.total_page}');
						if(v2){
							v2.Prop("Memo") = i;
						}
						MRViewer.Populate('${REPORTPARA.total_page}');
						break;
					}
				}
			}
		}	
		
	}
}
/**
function MRViewer_OnObjectClick(objId)//objvalue为控件的值
{
	var activeName = MRViewer.GetObjectProp(objId,"name");
	
	
	if(activeName.indexOf("S")!=-1)
	{
		var ObjName = activeName.substring(0,activeName.length-1);
		//alert(ObjName);
		var obj1 = report.FindObject(ObjName);
		MRViewer.SetObjectProp(obj1, "Memo", "检验合格");MRViewer.Populate();
		alert(obj1.Prop("name"));
		var values = MRViewer.getPropValue(ObjName,"Memo");
		
		if(values=="合格"){
			MRViewer.SetObjectProp(objId, "Memo", "检验合格");
			
			//MRViewer.Preview();
			MRViewer.Populate();
			//alert(MRViewer.getObjectProp(objId, "Memo"));
		}
		
	}
}
*/

function SendXML(Url,synchro) {

	var Obj=new XMLObj();

	//默认情况下，synchro为同步。

	if(synchro) {

		synchro=true;

	}

	else {

		synchro=false;

	}

	Obj.sendXML(Url,synchro);

	return Obj.returnXML();



}
function SendText(Url,synchro) {

	var Obj=new XMLObj();

	//默认情况下，synchro为同步。

	if(synchro) {

		synchro=true;

	}

	else {

		synchro=false;

	}

	Obj.sendXML(Url,synchro);

	return Obj.returnText();



}



//xml http 对象

//2006年11月09日 星期四 17:10:10 lybykw

function XMLObj()

{

	var xml=this;

	this.config={

		about:"",

		synchro:false

	}

	this.Http=new ActiveXObject("Microsoft.XMLHTTP");



	this.sendXML=function(Url,synchro)

	{

		try {

			xml.Http.onreadystatechange = xml.returnXML;

			xml.Http.open("get", Url, synchro);	//true|不同步（异步），false|同步

			xml.Http.send();

		} catch(e) {

			alert("SendXml:" + e + "\n请使用 Internet Explorer (IE) 6.0 以上版本进入系统。");

		}

	}



	this.returnXML=function()

	{

		if (xml.Http.readystate == 4) {

			var v="";

			v=xml.Http.responseText;

			v=v.replace(/\s/gi, "");	//先把返回值，变成字符串才行。

			return v;

		}

	}

	this.returnText=function()

	{

		if (xml.Http.readystate == 4) {

			var v="";

			v=xml.Http.responseText;

			return v;

		}

	}

}
</script>
</html>