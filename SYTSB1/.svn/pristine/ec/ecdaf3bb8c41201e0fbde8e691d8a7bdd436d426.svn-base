<%@ page import="util.ReportUtil"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.khnt.pub.codetable.service.CodeTableCache" %>
<%@ page import = "com.khnt.pub.codetable.bean.CodeTable" %>
<%@ page import = "com.khnt.pub.codetable.bean.CodeTableValue" %>
<%@ page import = "org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import = "org.springframework.context.ApplicationContext" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% 
	ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	CodeTableCache ctc = (CodeTableCache)ctx.getBean("codeTableCache");
	CodeTable cgtb = null;//证书类别
	CodeTable leveltb = null;//证书级别
	CodeTable contracttb = null;//合同类别
	if(ctc != null){
		cgtb = ctc.getCodeTable("BASE_LETTER");
		leveltb = ctc.getCodeTable("BASE_CERT_LEVEL");
		contracttb = ctc.getCodeTable("TJY2_RL_CONTRACTTYPE");
	}
	/* List<CodeTable> tables = ctc.getAllCodeTableList();
	if(tables!=null){
		for(CodeTable ct : tables){
			if("BASE_LETTER".equals(ct.getCode())){
				tb = ct;
			}
		}
	} */
	List<CodeTableValue>  contractCode = null;
	List<CodeTableValue>  cgCode = null;
	List<CodeTableValue>  levelCode = null;
	if(contracttb!=null){
		contractCode = contracttb.getCodeTableValues();
	}
	if(cgtb!=null){
		cgCode = cgtb.getCodeTableValues();
	}
	if(leveltb!=null){
		levelCode = leveltb.getCodeTableValues();
	}
	request.setAttribute("contractCode",contractCode);
	request.setAttribute("cgCode",cgCode);
	request.setAttribute("levelCode",levelCode);
%>  
<!DOCTYPE html>
<html>
<head pageStatus="${param.status}">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    
<title>人员信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<link href="app/research/css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css"/>
<script type="text/javascript">

	$(function() {
		if("${name}"!=""){
			$("#search").val("${name}")
		}
	})
	
	function indexSerachClick(){
		if($("#search").val()==""){
			alert("请输入查询条件！")
			return;
		}
		
		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+$("#search").val();
		
	}
	
	function detailUser(){
		top.$.dialog({
			width : 800,
			height :600,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add',
			data : {
				"window" : window,view:'app/research/ser_user_indp_list'
			}
		});
	}
	
	function detailInsp(){
		top.$.dialog({
			width : 900,
			height :700,
			lock : true,
			title : "设备信息",
			content : 'url:enterSearchAction/searchAll.do?name=${name}&status=add'+
					"&view=app/research/ser_user_indp_list",
			data : {
				"window" : window
			}
		});
	}
	
	function showBxDetail(val){
		var value = val.substring(0,val.length-1);
		window.location.href= $("base").attr("href")+"app/weixin/research/app/w-fee_detail.jsp?years="+value+"&user=${emp.id}";
	}
</script>
</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">人员详细信息</h1>
	</header>
	<div class="mui-content mui-scroll">
			<div class="mui-card">
				<ul class="mui-table-view mui-table-view-chevron">
					<li class="mui-table-view-cell">人员详细信息
						<div id="M_Toggle" class="mui-switch mui-active">
							<div class="mui-switch-handle"></div>
						</div>
					</li>
					<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">基本信息</a>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">姓名：</a></label>
					                    <span style="display:block;float:right;">${emp.empName}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">照片：</a></label>
					                    <span style="display:block;float:right;"><img  style="width: 140px;height: 196px;"  src="upload/${emp.empPhoto}" /></span>
					                </div>
					            </div>
							</li>
							
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">职称：</a></label>
					                    <span style="display:block;float:right;">${emp.empTitle}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">籍贯：</a></label>
					                    <span style="display:block;float:right;">${emp.empNativePlace}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">政治面貌：</a></label>
					                    <span style="display:block;float:right;">${emp.empPolitical}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">身份证号：</a></label>
					                    <span style="display:block;float:right;">${emp.empIdCard}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">参加工作时间：</a></label>
					                    <span style="display:block;float:right;">${fn:substring(emp.joinWorkDate, 0, 10)}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">电话：</a></label>
					                    <span style="display:block;float:right;">${emp.mobilePhone}</span>
					                </div>
					            </div>
							</li>
						</ul>
					</li>
					<c:if test="${role=='true'}">
					<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">财务明细</a>
						<div class="mui-table-view mui-table-view-chevron tubiao">
							<div class="i_icon02 info_tit"></div>
							<div class="tb_bor">
							   	<div id="div1" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
									<chart:chart chartNum="fee_bx_chart" renderAt="div1"  paramValue="${emp.id}"/>
								</div>
							</div>
						</div>
					</li>
					</c:if>
					 <c:if test="${rsrole=='true'}">
					<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">培训时间</a>
						<div class="mui-table-view mui-table-view-chevron tubiao">
							<div class="i_icon03 info_tit"></div>
							<div class="tb_bor">
							    	<div id="div2" style="width:100%; height:300px; border:hidden;background-color:transparent;" ><br><br><font color="#999999">正在加载图形数据,请稍候...</font>
									<chart:chart chartNum="personal_px" renderAt="div2" paramValue="%${emp.id}%"/>
								</div>
							</div>
						</div>
					</li>
					</c:if>
					 <c:if test="${rsrole=='true'}">
					<c:if test="${contractsC0>0}">
					<c:forEach var="insp" items="${contracts0}" varStatus="status" begin="0" end="${contractsC0<5?contractstC0:4}">
						<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">合同信息[${status.index+1}]</a>
							<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">合同类型:</a></label>
					                    <span style="display:block;float:right;">
					                    	<c:if test="${not empty contractCode}">
							                    <c:forEach items="${contractCode}" var="code">
							                    	<c:if test="${code.value==insp.contractType}">
							                    		${code.name}
							                    	</c:if>
							                    </c:forEach>
						                    </c:if>
										</span>
					                </div>
					            </div>
							</li>
							
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">合同开始日期:</a></label>
					                    <span style="display:block;float:right;">${fn:substring(insp.contractStartDate, 0, 10)}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">合同结束日期：</a></label>
					                    <span style="display:block;float:right;">${fn:substring(insp.contractStopDate, 0, 10)}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">试用工资：</a></label>
					                    <span style="display:block;float:right;">${insp.trialMoney}元/月</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">转正工资：</a></label>
					                    <span style="display:block;float:right;">${insp.positiveMoney}元/月</span>
					                </div>
					            </div>
							</li>
						</ul>
						</li>
						</c:forEach>
					</c:if>
					</c:if>
					 <c:if test="${rsrole=='true'}">
					 <c:if test="${cartListC0>0}">
						 <c:forEach var="insp" items="${cartList0}" varStatus="status" begin="0" end="${cartListC0<5?cartListC0:4}">
							 <li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">持证信息[${status.index+1}]</a>
								<ul class="mui-table-view mui-table-view-chevron">
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">证书类型:</a></label>
						                    <span style="display:block;float:right;">
							                    <c:if test="${not empty cgCode}">
								                    <c:forEach items="${cgCode}" var="code">
								                    	<c:if test="${code.value==insp.cert_type}">
								                    		${code.name}
								                    	</c:if>
								                    </c:forEach>
							                    </c:if>
						                    </span>
						                </div>
						            </div>
								</li>
								
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">证书代码:</a></label>
						                    <span style="display:block;float:right;">${insp.cert_code}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">证书编号：</a></label>
						                    <span style="display:block;float:right;">${insp.cert_no}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">证书级别：</a></label>
						                    <span style="display:block;float:right;">
						                    	<c:if test="${not empty levelCode}">
								                    <c:forEach items="${levelCode}" var="code">
								                    	<c:if test="${code.value==insp.cert_level}">
								                    		${code.name}
								                    	</c:if>
								                    </c:forEach>
							                    </c:if>
							                 </span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">发证机关：</a></label>
						                    <span style="display:block;float:right;">${insp.cert_issue_org}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">初次取证日期：</a></label>
						                    <span style="display:block;float:right;">${fn:substring(insp.first_get_date, 0, 10)}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">发证日期：</a></label>
						                    <span style="display:block;float:right;">${fn:substring(insp.cert_begin_date, 0, 10)}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">证书有效截止日期：</a></label>
						                    <span style="display:block;float:right;">${fn:substring(insp.cert_end_date, 0, 10)}</span>
						                </div>
						            </div>
								</li>
							</ul>
							</li>
						 </c:forEach>
					 </c:if>
					 </c:if>
				</ul>
			</div>
</body>
<script src="app/weixin/research/js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
			window.addEventListener('toggle', function(event) {
				if (event.target.id === 'M_Toggle') {
					var isActive = event.detail.isActive;
					var table = document.querySelector('.mui-table-view');
					var card = document.querySelector('.mui-card');
					if (isActive) {
						card.appendChild(table);
						card.style.display = '';
					} else {
						var content = document.querySelector('.mui-content');
						content.insertBefore(table, card);
						card.style.display = 'none';
					}
				}
			});
		</script>
</html>