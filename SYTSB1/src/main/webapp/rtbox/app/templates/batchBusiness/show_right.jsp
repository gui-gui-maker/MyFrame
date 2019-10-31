<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检验报告</title> 
<%

String infoId = request.getParameter("info_id");
if(infoId==null){
	infoId = request.getParameter("fk_inspection_info_id");
}
String inspectOp  = "select u.id,u.name from sys_user u, tzsb_inspection_info info where info.check_op_id like '%'||u.id||'%'  and info.id='" + infoId + "'";
//String auditOp = "select u.id,u.name from sys_user u,sys_user_role ur,sys_role r where r.id = ur.role_id and ur.user_id = u.id and r.name like '%报告审核%'";

StringBuffer sql = new StringBuffer();
sql.append("	with classify as	");
sql.append("	 (select decode(cla.big_classify_code,	");
sql.append("	                '1000',	");
sql.append("	                '锅炉报告审核',	");
sql.append("	                '2000',	");
sql.append("	                '容器报告审核',	");
sql.append("	                '3000',	");
sql.append("	                '电梯报告审核',	");
sql.append("	                '4000',	");
sql.append("	                '起重报告审核',	");
sql.append("	                '5000',	");
sql.append("	                '厂车报告审核',	");
sql.append("	                '6000',	");
sql.append("	                '游乐报告审核',	");
sql.append("	                '7000',	");
sql.append("	                '管道元件报告审核',	");
sql.append("	                '8000',	");
sql.append("	                '管道报告审核',	");
sql.append("	                '7310',	");
sql.append("	                '安全阀报告审核') text	");
sql.append("	    from TZSB_INSPECTION_INFO info,	");
sql.append("	         BASE_DEVICE_DOCUMENT doc,	");
sql.append("	         BASE_DEVICE_CLASSIFY cla	");
sql.append("	   where info.fk_tsjc_device_document_id = doc.id	");
sql.append("	     and doc.device_sort_code = cla.device_sort_code	");
sql.append("	     and info.id = '").append(infoId).append("')	");
sql.append("		");
sql.append("	select t1.id code, t1.name text	");
sql.append("	  from sys_user t1	");
sql.append("	 where t1.id in (select s.user_id	");
sql.append("	                   from sys_user_role s, Sys_Role e	");
sql.append("	                  where e.id = s.role_id	");
sql.append("	                    and e.name = (select text from classify ))	");

String auditOp =  sql.toString();
System.out.println(sql.toString());
%>


<% 

String code = request.getParameter("code");
String pageName = request.getParameter("pageName");
String version = request.getParameter("version");

//String pagePath = "/rtbox/app/templates/"+code+"/"+version+"/"+pageName;

%>
<%@include file="/rtbox/public/base.jsp"%>
 <link href="rtbox/app/templates/default/tpl.css" rel="stylesheet" />
 <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<style type="text/css">
		body, table, tr, th, td, input { margin: 0; padding: 0; width:95%;}
		.dtitle { text-align: center; font-weight: bold; font-size:24px; font-family: 'simsun'; }
		#layout2 {width: 90%; font-family: 'simsun'; }
		#layout2 table { position: relative; font-size: 14px; table-layout: fixed; vertical-align: top; border-collapse: collapse!important; width: 90%; }
		#layout2 table tr{min-height:7mm;height:auto; }
		#layout2 table.two { border: 1px solid #000; cellpadding:1; cellspacing:1; }
		#layout2 table.two td { border: 1px solid #000; }
		#layout2 table.l-checkboxlist-table { border: 0px solid #fff; cellpadding:1; cellspacing:1; }
		#layout2 table.l-checkboxlist-table td { border: 0px solid #fff; }
		
		#layout2 table.two td.noborder1 {border-right:0px solid #fff!important;}
		#layout2 table.two td.noborder2 {border-left:0px solid #fff!important;}
		#layout2 table p { margin: 0;width: 90%; text-align: center; }
		#layout2 table input {width:95%;height: 1.4em;padding: 0px; }
		 #layout2 table input.iptw2 {width:72%;}
		#layout2 table input:focus { outline: 0 none; background: rgba(0,0,0,.02); border: 1px solid #ccc; }
		#layout2 table textarea { width: 90%;border: 1px solid #fff;}
		#layout2 table textarea:focus { outline: 0 none; background: rgba(0,0,0,.02); border: 1px solid #ccc; }
		#layout2 table.l-checkboxlist-table input { width:20px}
		#layout2 table p.bianma {text-align:right;}
		.l-text{height:100%;}
		.l-text-field{position:static!important;width:100%}
		.l-text-wrapper{width:95%}
		#docx4j_tbl_4 tr td:nth-child(1){  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) p{  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) p span{  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) span{  text-align: left;}
		.error{
			float:right;
			position: relative;
		}
		</style>
<script type="text/javascript" src="rtbox/app/templates/default/tpl_photo2.js"></script>
<script src="rtdroc/app/draw/util/RtDraw.js"></script>
<script type="text/javascript" src="rtbox/public/js/page-data.js"></script>
<script type="text/javascript">
	var markOptions = <u:dict code="problem_nature_type"></u:dict>;
	var fk_report_id = "${param.fk_report_id}";
	var input = "${param.input}";
	var code_ext = "${param.code_ext}";
	var info_id="${param.info_id}"; //ZQ EDIT 0827 
	var pageStatus = "${param.pageStatus}";
	var check_op ="${param.check_op}";
	var relColumn='fk_report_id=${param.fk_report_id}&fk_inspection_info_id=${param.fk_inspection_info_id}';
	var rtboxId = "${param.rtPageId}";
	var rtboxCode = "${param.code}";
	var modelType = "report";
	
	$(function() {
		/* if(paramss.mobile!=1&&paramss.mobile!=2){
			rtboxId = parent.rtboxId;
		} */
		//页面布局
		/* $("#layout2").ligerLayout({
			rightWidth : 150,
			space : 3,
			allowTopResize : false
		}); */
		//先查询模板内容再渲染
		getPageData(rtboxId,"${param.pageCode}",function(data){
				//console.log(res.data)
			$("#layout1").html(data);
				
			
			//处理图片
			initPicture()
				
			$("form").ligerForm();
			initForm();
	
			
			var width = $("#layout2").width();
			$(".l-layout-center").css("width",width)
			if("${param.print}"=='1'){
				printPreviewForm();
			}
			
			$(".checkboxDiv").ligerCheckBoxList();
			
			try {
				changeCheckBoxDIV();
			} catch (e) {
				// TODO: handle exception
			}
			
			//设置复选框默认值
			setligerCheckBoxListInitValue();
			
			
			
		})
	});
	
	function printPreviewForm(){
		
		if("${param.print}"=='1'){
			
			if(_browser == "IE"){
				wb.execwb(7, 1);
			}else{
				window.print();
			}
		}else{
			
				window.open(window.location.href+"&print=1");
			
			
		}
		//$('body').printPreview();
		//
		//$.printPreview.loadPrintPreview();
	}
	

</script>
<script type="text/javascript" src="rtbox/app/templates/batchBusiness/default/tpl02.js"></script>
<!-- <script type="text/javascript" src="rtbox/app/templates/default/jquery.js"></script> -->
<script type="text/javascript" src="rtbox/app/templates/default/SelectTool.js"></script>
<script type="text/javascript" src="rtbox/app/templates/default/Label.js"></script>

<script type="text/javascript" src="rtbox/public/js/browser.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>

<script type="text/javascript" src="rtbox/app/templates/default/enter.js"></script>
</head>
<body>
	<div id="layout" style="width: 99.8%">
	<input type="hidden" id="fk_report_id" name="fk_report_id" value="${param.fk_report_id}">
	<input type="hidden" id="fk_inspection_info_id" name="fk_inspection_info_id" value="${param.fk_inspection_info_id}">
	<input type="hidden" id="inputFocus" name="inputFocus" />
	<input type="hidden" id="code_ext" name="code_ext"  value="${param.code_ext}"/> 
	<div position="center" style="overflow: auto;width: 100%" align="center">
		<form id="formObj" action="inspection/zzjd/reportInfoInputNew.do"
					getAction="inspection/zzjd/reportInfoLoadNew.do">
			
			<%-- <jsp:include page="<%=pagePath %>"></jsp:include> --%>
			<div id="layout1"  class="a4-main " style="width: 100%;height: 100%" >
			</div>
		</form>
	</div>
	</div>
 <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0"></OBJECT>
</body>
</html>
