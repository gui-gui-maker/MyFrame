<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检验报告</title> 

<% 

String infoId = request.getParameter("info_id");
String inspectOp  = "select u.id,u.name from sys_user u, tzsb_inspection_info info where info.check_op_id like '%'||u.id||'%'  and info.id='" + infoId + "'";
//String auditOp = "select u.id,u.name from sys_user u,sys_user_role ur,sys_role r where r.id = ur.role_id and ur.user_id = u.id and r.name like '%电梯报告审核%'";

StringBuffer sql = new StringBuffer();
sql.append("	with classify as	");
sql.append("	 (select decode(substr(doc.device_sort_code,0,1),	");
sql.append("	                '1',	");
sql.append("	                '报告审核',	");
sql.append("	                '2',	");
sql.append("	                 '报告审核',	");
sql.append("	                '3',	");
sql.append("	                '审核电梯报告',	");
sql.append("	                '4',	");
sql.append("	                '审核起重机械报告',	");
sql.append("	                '5',	");
sql.append("	                '审核厂车报告',	");
sql.append("	                '6',	");
sql.append("	                '报告审核',	");
sql.append("	                '7',	");
sql.append("	                '报告审核',	");
sql.append("	                '8',	");
sql.append("	                '报告审核') text	");
sql.append("	    from TZSB_INSPECTION_INFO info,	");
sql.append("	         BASE_DEVICE_DOCUMENT doc	");
sql.append("	   where info.fk_tsjc_device_document_id = doc.id	");
sql.append("	     and info.id = '").append(infoId).append("')	");
sql.append("		");
sql.append("	select t1.employee_id code, t1.name text	");
sql.append("	  from sys_user t1	");
sql.append("	 where t1.id in (select s.user_id	");
sql.append("	                   from sys_user_role s, Sys_Role e	");
sql.append("	                  where e.id = s.role_id	");
sql.append("	                    and e.name = (select text from classify ))	");

String auditOp =  sql.toString();

String code = request.getParameter("code");
String pageName = request.getParameter("pageName");
String version = request.getParameter("version");
//String pagePath = "/rtbox/app/templates/"+code+"/"+version+"/"+pageName;

%>
	<%@include file="/rtbox/app/visual/base.jsp" %>
	 <link href="rtbox/app/visual/css/control.css" rel="stylesheet" />
 <link href="rtbox/app/templates/default/tpl.css" rel="stylesheet" />
 <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<style type="text/css">
body, table, tr, th, td, input { margin: 0; padding: 0;}
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
		#layout2 table input {height: 1.4em;padding: 0px; }
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
.container {
	margin: 0px 0;
	position: relative;
}

.a4-endwise { /* width: 1075px; */ /*height: 1565px;*/
	border: 0px #FF0000 solid; /*overflow: hidden;*/
	padding: 0;
	word-break: break-all;
	margin-left: auto;
	margin-right: auto;
}

.a4-broadwise {
	width: 100%; /*height: 1073px;*/
	border: 0px #FF0000 solid;
	overflow: hidden;
	padding: 0;
	word-break: break-all;
	margin-left: auto;
	margin-right: auto;
}

.a4-main {
	background: #ffffff;
	box-shadow: 0px 0px 10px 0px #8E8E8E;
}

.a4-line-main {
	position: absolute;
	width: 100%;
	border-bottom: 1px solid #0000FF;
	z-index: 111;
}

.a4-line-no {
	position: absolute;
	right: 10px;
	top: -30px;
}

.print {
	position: fixed;
	top: 1%;
	right: 10%;
}

@media print {
	body {
		background: #ffffff;
	}
	.a4-endwise, .a4-broadwise {
		border: 0px;
	}
	.a4-line-main {
		display: none;
	}
	.a4-main {
		box-shadow: none !important;
		border: 0px;
	}
}

@media screen and (max-width: 1000px) {
	.cont_center {
		width: 80% !important;
	}
	.a4-line-main {
		display: none;
	}
}

.pclick,.sheargrid{
	border-style: dashed ;
	border-width: 1px ;
	border-color: #000000;
}
 .pclickTable{
	border-style: dashed  !important;
	border-width: 2px !important;
	border-color: #000000 !important;
}
 .addtrcl{
	border-style: none;
	border-width: 1px;
	border-color: #000000;
}
.pclickd,.copygrid,.pclicktr,.pclicktd{
	border-style: none;
	border-width: 1px;
	border-color: #000000;
	background-color:#6495ED;
}

.seled{background-color:#D6DFF7!important;} 
.tableHead td{
border-right-style: solid;
border-right-color: white;
border-right-width: 1px;
}
.cent
{
text-align: justify;text-align-last: justify;
}
</style>
<script type="text/javascript" src="rtbox/public/js/main-k.js"></script>

<script type="text/javascript" src="rtbox/app/visual/js/inputEdit.js"></script>
<script type="text/javascript" src="rtbox/app/visual/js/a4.js"></script>
<script type="text/javascript" src="rtbox/app/visual/js/layout_design.js"></script>
<script type="text/javascript" src="rtbox/app/visual/js/seled.js"></script>

 <script type="text/javascript" src="rtbox/public/js/page-data.js"></script>
<script type="text/javascript">
	var fk_report_id = "${param.fk_report_id}";
	var input = "${param.input}";
	var code_ext = "${param.code_ext}";
	var info_id="${param.info_id}"; //ZQ EDIT 0827 
	var pageStatus = "${param.pageStatus}";
	var check_op ="${param.check_op}";
	var relColumn='fk_report_id=${param.fk_report_id}&fk_inspection_info_id=${param.fk_inspection_info_id}';
    var  menu ;
    var  menu2;
	//检验员选项
	var inspectOpData  = <u:dict sql="<%=inspectOp%>"></u:dict>;
	//审核人员选项
	var auditOpData  = <u:dict sql="<%=auditOp%>"></u:dict>;
	var modelType = "report";
	var pageCode = "${param.pageCode}";
	var rtboxCode = "${param.code}";
	var rtboxId = "${param.rtPageId}";
	
	$(function() {
		//先查询模板内容再渲染
		getPageData(rtboxId,pageCode,function(data){
				//console.log(res.data)
			$("#layout1").html(data);
			if("${param.print}"=='1'){
				printPreviewForm();
				return;
			}
			if(seledType!="span"){
				//文本选择编辑时不加拖动，不然太难用了
				 bindingFun(); //绑定功能
			}
	        
	         bindingEvent();
		     showNamed11();
			 initInput();
			var width = $("#layout2").width();
			$(".l-layout-center").css("width",width)
			
	        //阻止浏览器默认右键点击事件
	        document.oncontextmenu = function() {
	            return false;
	        }
	
	        menu = $.ligerMenu({
	            top : 100,
	            left : 100,
	            width : 160,
	            items : [
	            /*   {
	                  text : '边框',
					  children: [
	                            { text: '上框线', click: topLine },
	                            { line: true },
	                            { text: '下框线', click: bottomLine},
	                            { line: true },
	                            { text: '左框线', click: leftLine },
	                            { line: true },
	                            { text: '右框线', click: rightLine },
	                            { line: true },
	                            { text: '无框线', click: noneLine }
	                        ]
				  },  { line: true }, */ {
	                text : '添加一行（Ctrl+B）',
	                click : addtr,
	                icon : 'add'
	            }, { line: true },{
	                text : '删除该行（Delete）',
	                click : deltr,
	                icon : 'del'
	            }, { line: true },{
	                text : '拆分列',
	                click : splitGridColumn,
	                icon : 'del'
				}, { line: true },{
	                text : '拆分行',
	                click : splitGridLine,
	                icon : 'del'
	            }, { line: true },{
	                text : '复制（Ctrl+C）',
	                click : copyGrid,
	                icon : 'del'
	            }, { line: true },{
	                text : '剪切（Ctrl+X）',
	                click : shearGrid,
	                icon : 'del'
	            },  { line: true },{
	                text : '粘贴（Ctrl+V）',
	                click : pasteGrid,
	                icon : 'del'
	            }, { line: true }, {
					text : '合并标记表格',
					click : mergeGrid,
					icon : 'del'
				}
	            ]
	        });
	        menu2 = $.ligerMenu({
	            top : 100,
	            left : 100,
	            width : 160,
	            items : [
	            {
	                text : '合并文本框',
	                click : mergeSpan,
	                icon : 'del'
	            }
	
	            ]
	        });
		})
	});

    function saveFormContent(){
        //移除绑定功能和对应样式
        removeBindingFun();
        //影藏命名
        hideNamed();
        removeTableHead();
        removepclick();
        $( "input" ).removeAttr("placeholder");
        //   alert($("#layout2").html());
        var content = $("#layout1").html();
        $.post("/com/rt/page/saveIndexChangeReport.do",
        		{'path':"",'content':content,"rtboxId":parent.rtboxId,'pageCode':'${param.pageCode}','pageName':'${param.pageName}'},function(res){
            if(res.success){
                alert("保存成功");
                //保存成功后重新绑定功能
                bindingFun();
                showNamed11();
            }else{
                alert("保存失败");

            }
        })
    }
   
    /**
     * 
     * 布局样式调整相关代码
     * 
     * @returns
     */	

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
<script type="text/javascript" src="rtbox/app/visual/js/design-tpl02.js"></script>
<script type="text/javascript" src="rtbox/public/js/browser.js"></script>


</head>
<body>
	<div id="layout" style="width: 100%;height: 100%">
		<input type="hidden" id="inputFocus" name="inputFocus" />
		 <input type="hidden" id="code_ext" name="code_ext"  value="${param.code_ext}"/> 
		<div position="center" style="overflow-y:auto;width: 100%" align="center">
			<form id="formObj" action="reportItemValueAction/saveMap.do" class="input-mouse"
						getAction="reportItemValueAction/detailMap.do" style="height: 100%" >
				
				<div class="container a4-endwise;" style="width: 100%;height: 100%" >
					<div id="layout1"  class="a4-main " style="width: 100%;height: 100%" ><%-- <jsp:include page="<%=pagePath %>"></jsp:include> --%></div>
				</div>
				
			</form>
		</div>
	</div>
	
	 <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0"></OBJECT>
</body>
</html>
