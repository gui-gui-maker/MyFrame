<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.lsts.inspection.dao.InspectionDao"%>
<%@page import="java.util.List"%>
<%@ page import="com.lsts.inspection.bean.FlowInfoDTO"%>
<%@page import="com.khnt.utils.StringUtil" %>
<%@page import="com.lsts.employee.bean.EmployeeDTO"%>
<%@page import="com.lsts.employee.dao.EmployeeCertDao"%>
<%@page import="com.lsts.relevant.dao.RelevantPeopleCertDao"%>
<%@page import="com.lsts.relevant.bean.RelevantPeopleDTO"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="com.scts.payment.dao.InspectionPayInfoDao"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.scts.maintenance.dao.MaintenanceInfoDao"%>
<%@page import="com.scts.maintenance.bean.MaintenanceInfoDTO"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <title></title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
    <link href="app/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="app/js/desktop.js"></script>
<script type="text/javascript" src="app/js/jquery.min.js?v=1.4.4"></script>
    <script type="text/javascript" src="app/js/jquery-powerSwitch.js"></script>
    <script type="text/javascript">
    loadCoreLibrary({css:"main-desktop.css"});
    </script>
    <%
    CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String user=sessionUser.getSysUser().getName();
    String userBm= sessionUser.getDepartment().getId();
    Map<String,String> roles=sessionUser.getRoles();
    %>
    <script type="text/javascript">
    try{
    	parent.mPanelDispay({panel:'left',display:false,close:true});//隐藏父层菜单
    }catch (e){}
    
        $(function(){//jQuery页面载入事件
            var myDate = new Date(); 
            var str = myDate.toLocaleDateString();  
            var Week = ['日','一','二','三','四','五','六'];  
            str += ' 星期' + Week[myDate.getDay()];
            $("#nowDay").html(str);
            setTime();
            
            
            
        });

    	function openDialog(title,url){
			top.$.dialog({
				width : $(top).width() * 0.8,
				height : $(top).height() * 0.8,
				lock : true,
				title : title,
				data : {
					"window" : window
				},
				content : 'url:' + url
			});
		}
    </script>
    </head>
    <body>

     <div class="wrapper">
     <div class="gg_tz"> 
     
                   <!--  <ul id="wordSlide" class="word_slide" data-rel="#wordSlide li">
                        <li id="wordList1" style="display: list-item;" data-rel="wordList1"><a href="#">2015年第六期聚乙烯(PE)焊工培训考试的通知</a></li>
                        <li id="wordList2" data-rel="wordList2"><a href="#">关于举办《锅炉监督检验规则》和《锅炉定期检验规则》宣贯班的通知</a></li>
                        <li id="wordList3" data-rel="wordList3"><a href="#">四川省特种设备检验检测协会关于举办《四川省电梯安全监督管理办法》GB/T31821《电梯主要部件报废技术条件》解读与电梯安全评估、隐患排查培训班的通知</a></li>
                        <li id="wordList4" data-rel="wordList4"><a href="#">关于举办第二期电梯企业检验人员培训班的通知</a></li>
                    </ul> -->
 
    </div>
     <div class="col1 clearfix">
        <div class="wather"> 
           <div class="user"><span><%=user%></span><span>,　</span><span id="user"></span></div> 
           <div class="time" id="nowTime"><span class="subam"></span></div> 
           <div class="date" id="nowDay"></div>
           <div class="tianqi"><img src="" bordr="0"/></div>
        </div>
        <%if(!userBm.equals("100032") && !"100038".equals(userBm) && !"100039".equals(userBm)){ %>
        <div class="big ztrw">
          <a  href="javascript:void(0);" onclick="javascript:openDialog('重大任务','app/office/office_rv_fb_list2.jsp');">重大任务</a>
          <span class="numb"></span>
        </div>
        <%} %>
     </div>
      <%if(!userBm.equals("100032") && !"100038".equals(userBm) && !"100039".equals(userBm)){ %>
     <div class="col1 clearfix">

       <div class="big xxtz">
          <a href="javascript:void(0);" onclick="javascript:openDialog('系统建设台账列表',' app/maintenance/maintenance_info_list.jsp?data_status=4');">系统建设台账</a>
          <span class="numb"></span>
        </div>
        <div class="big rwtz">
          <a  href="javascript:void(0);" onclick="javascript:openDialog('任务台帐','app/office/ywhbsgz_fb_list2.jsp');">任务台帐</a>
          <span class="numb"></span>
        </div>
        <div class="big dcl">
          <a  href="javascript:void(0);" onclick="javascript:openDialog('待处理业务','department/basic/getFlowInfo.do');">待处理业务</a>
          <span class="numb"></span>
        </div>
        <div class="big pcl">
          <a href="javascript:void(0);" onclick="javascript:openDialog('待处理批量业务','inspection/zzjd/getFlowInfo.do');">批量待处理业务</a>
          <span class="numb"></span>
        </div>  
     </div>
   
     <div class="col2 clearfix">

        <div class="small fybx">
          <a href="javascript:void(0);" onclick="javascript:openDialog('费用报销申请','app/finance/fybxd_list.jsp?validPwd=1');">费用报销申请</a>
          <span class="numb"></span>
        </div>     
        
        <div class="small clf">
          <a href="javascript:void(0);" onclick="javascript:openDialog('差旅费用申请','app/finance/clfbxd_list.jsp?validPwd=1');">差旅费用申请</a>
          <span class="numb"></span>
        </div> 
        
        <div class="small hk">
          <a href="javascript:void(0);" onclick="javascript:openDialog('还款单填报','app/finance/finance_bills.jsp?validPwd=1');">还款单填报</a>
          <span class="numb"></span>
        </div> 
        
        <div class="small px">
          <a href="javascript:void(0);" onclick="javascript:openDialog('培训费用申请','app/finance/pxfbxd_list.jsp?validPwd=1');">培训费用申请</a>
          <span class="numb"></span>
        </div>  
        
        <div class="small lkd">
          <a href="javascript:void(0);" onclick="javascript:openDialog('领款单填报','app/finance/recipients_bill.jsp?validPwd=1');">领款单填报</a>
          <span class="numb"></span>
        </div>  
        
       <div class="small jk">
          <a href="javascript:void(0);" onclick="javascript:openDialog('借款单填报','app/finance/finance_borrow.jsp?validPwd=1');">借款单填报</a>
          <span class="numb"></span>
        </div>     
        
        <div class="big ly"> 	

          <a href="javascript:void(0);" onclick="javascript:openDialog('个人工资查询','app/finance/messageChecky_detail.jsp');">个人工资查询</a>
          <p>休假申请</p>
          <span class="numb"></span>
        </div>

     </div>
       <%} %>
     <div class="col3 clearfix">
      <%if(!userBm.equals("100032") && !"100038".equals(userBm) && !"100039".equals(userBm)){ %>
        <div class="small sbxx">
          <a  href="javascript:void(0);" onclick="javascript:openDialog('设备信息','app/device/device_index.jsp?type=1');">设备信息</a>
          <p>设备信息</p>
        </div>     
      <%} %> 
       <%  if((roles.get("2c90758150a1f8ad0150a29f15530003")!=null||roles.get("EA94849D4083D0A8E040007F020052CE")!=null)||userBm.equals("100032")||userBm.equals("100038")||userBm.equals("100039")){%>
    	    <div class="small sbyj">
        <a  href="javascript:void(0);" onclick="javascript:openDialog('设备预警','app/device/device_index.jsp?type=2');">设备预警</a>
          <p>设备预警</p>
          </div>
      <%  }%>
       
        <div class="small bgcx">
         <a  href="javascript:void(0);" onclick="javascript:openDialog('报告查询','app/query/report_query_list.jsp');">报告查询</a>
         <p>报告查询</p>
        </div>
     </div>
     
</div>

<script>
$("#wordSlide li").powerSwitch({
    autoTime: 2500,
    direction: "vertical",
    animation: "translate"
});
</script>
</body>
</html>
