<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*"%>
<%@ page import="java.util.*"%>
<%@ page import="util.TS_Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>打印单据</title>
<%
	MRDataSet mrds = new MRDataSet();
	DataRecord rec = new DataRecord();
	
	//处理检验业务表基本信息
	Map<String,Object> infoMap 
		= (Map<String,Object>)request.getAttribute("infoMap");
	
	for (String key : infoMap.keySet()) {
		String info_value = TS_Util.nullToString(infoMap.get(key)).replaceAll("\r|\n","");
		if("BORROWMONEYDATE".equals(key)){
			System.out.println(info_value);
			System.out.println(info_value.toString()
					.split(" ")[0]);
			rec.setValue("BORROWMONEYDATE", TS_Util
					.nullToString(
							info_value.toString()
									.split(" ")[0]));
		}else if("IMPORT_MONEY".equals(key)){
			System.out.println(info_value);
			rec.setValue("IMPORT_MONEY", info_value);
			
			String[] moneyArr = info_value.split("\\.");
			if(moneyArr.length==2){
				String pre = moneyArr[0];
				if(pre.length()>0){
					// 金额小数点前面的“元”
					rec.setValue("YUAN", pre.substring(pre.length()-1));
				}else{
					rec.setValue("YUAN", "0");
				}
				
				if(pre.length()>1){
					// 金额小数点前面的“拾”
					rec.setValue("SHI", pre.substring(pre.length()-2,pre.length()-1));
				}
				
				if(pre.length()>2){
					// 金额小数点前面的“佰”
					rec.setValue("BAI", pre.substring(pre.length()-3,pre.length()-2));
				}
				
				if(pre.length()>3){
					// 金额小数点前面的“仟”
					rec.setValue("QIAN", pre.substring(pre.length()-4,pre.length()-3));
				}
				
				if(pre.length()>4){
					// 金额小数点前面的“万”
					rec.setValue("WAN", pre.substring(pre.length()-5,pre.length()-4));
				}
				
				if(pre.length()>5){
					// 金额小数点前面的“拾万”
					rec.setValue("SHIWAN", pre.substring(pre.length()-6,pre.length()-5));
				}
				if(moneyArr[1].length()==2){
					// 金额小数点后面的“角”
					String jiao = moneyArr[1].substring(0,1);
					if(StringUtil.isNotEmpty(jiao) && !"0".equals(jiao)){
						rec.setValue("JIAO", jiao);
					}else{
						rec.setValue("JIAO", "0");
					}
					// 金额小数点后面的“分”
					String fen = moneyArr[1].substring(1,2);
					if(StringUtil.isNotEmpty(fen) && !"0".equals(fen)){
						rec.setValue("FEN", fen);
					}else{
						rec.setValue("FEN", "0");
					}
				}else if(moneyArr[1].length()==1){
					// 金额小数点后面的“角”
					String jiao = moneyArr[1].substring(0,1);
					if(StringUtil.isNotEmpty(jiao) && !"0".equals(jiao)){
						rec.setValue("JIAO", jiao);
					}else{
						rec.setValue("JIAO", "0");
					}
				}
			}else if(moneyArr.length==1){
				String pre = moneyArr[0];
				if(pre.length()>0){
					// 金额小数点前面的“元”
					rec.setValue("YUAN", pre.substring(pre.length()-1));
				}else{
					rec.setValue("YUAN", "0");
				}
				
				if(pre.length()>1){
					// 金额小数点前面的“拾”
					rec.setValue("SHI", pre.substring(pre.length()-2,pre.length()-1));
				}
				
				if(pre.length()>2){
					// 金额小数点前面的“佰”
					rec.setValue("BAI", pre.substring(pre.length()-3,pre.length()-2));
				}
				
				if(pre.length()>3){
					// 金额小数点前面的“仟”
					rec.setValue("QIAN", pre.substring(pre.length()-4,pre.length()-3));
				}
				
				if(pre.length()>4){
					// 金额小数点前面的“万”
					rec.setValue("WAN", pre.substring(pre.length()-5,pre.length()-4));
				}
				
				if(pre.length()>5){
					// 金额小数点前面的“拾万”
					rec.setValue("SHIWAN", pre.substring(pre.length()-6,pre.length()-5));
				}
			}
		}else{
			rec.setValue(key, TS_Util.nullToString(info_value));
		}
	}

	mrds.addRow(rec);
	MREngine engine = new MREngine(pageContext);

	String templetName = (String)request.getAttribute("templetName");
	engine.setRootPath("app/flow/report/temple/CW");
	engine.addReport(templetName+".mrf");

	engine.addMRDataSet("BGDS", mrds);
	engine.bind();
	
%> 
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="report.js.jsp"%>

<script language="javascript">
	$(function() {
		
		$('#audit').click(function(){
			doAudit();
		});
		
		$('#print').click(function(){
			doPrintreport(1,"");
		});
		
		$('#cancel').click(function(){
			api.close();
		});
	})
	
	
	function doPrintreport(print_copies,printer_name)
	{
		MRViewer = document.all("MRViewer") ;
		MRViewer.PrintSetup(0,0,true,"",0,print_copies,true,printer_name);
		MRViewer.Print(false);	// false不提示打印设置框，调用默认的
		MRViewer_AfterPrint('1');
	}
	
	function MRViewer_AfterPrint(flag){
		if(flag=='1'){
			api.close();
		}
	}
	function showBB(){
		$("#sssss").show();
	}
	
	//保存开始禁用按钮
	function disableButton(){
		//$.ligerDialog.alert(111);
	}
	//保存结束按钮可用
	function enableButton(){
		//$.ligerDialog.alert(222);
	}
	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
//-->
</script>
</head>
<body>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
	 <script>
	

	 </script>
</div>
<div class="toolbar-tm">
	<div class="toolbar-tm-bottom" style="text-align:center;">
		<a id="print" class="l-button-warp l-button" ligeruiid="Button1012">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">打印</span>
				<span class="l-button-icon l-icon-print"></span>
			</span>
		</a>
		<a id="cancel" class="l-button-warp l-button" ligeruiid="Button1012">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">关闭</span>
				<span class="l-button-icon l-icon-cancel"></span>
			</span>
		</a>
	</div>
</div>
</body>
</html>