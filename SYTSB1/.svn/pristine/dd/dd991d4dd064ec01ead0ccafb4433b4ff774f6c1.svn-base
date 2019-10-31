<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统用户</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<%
String startDate=request.getParameter("startDate");
String endDate=request.getParameter("endDate");
String org_id=request.getParameter("org_id");
String unitName=request.getParameter("unitName");
String sql="select r.* from "+
" ((select 'cl' type ,c.id,c.USER_ ,c.DEPARTMENT_ID,c.DEPARTMENT,c.CL_DW unit, c.CL_SJ bs_date ,c.IDENTIFIER idno,c.HANDLE_NAME,c.HANDLE_TIME,c.CL_HJ_JEXJ "+
      "   from TJY2_CLFBXD c where c.statue='CSTG') "+
        "  union all "+
" (select  'px' type ,p.id,p.USER_ ,p.DEPARTMENT_ID,p.DEPARTMENT ,p.CL_DW unit,p.CL_SJ  bs_date  ,p.IDENTIFIER idno,p.HANDLE_NAME,p.HANDLE_TIME,p.CL_HJ_JEXJ "+
    "     from TJY2_PXFBXD p where p.statue='CSTG')  "+
     "     union all "+
" (select  'fy' type ,f.id,f.PEOPLE_CONCERNED ,f.DEPARTMENT_ID,f.DEPARTMENT ,f.unit unit, f.BS_DATE bs_date  ,f.IDENTIFIER idno,f.HANDLE_NAME,f.HANDLE_TIME,f.TOTAL "+
     "    from TJY2_FYBXD f where f.status='CSTG')  "+
     "    union all "+
" (select  'lq' type ,d.id,d.DM_NAME, d.DEPARTMENT_ID,d.DEPARTMENT ,d.unit unit, d.DM_DATE bs_date  ,d.NUMBER_TAB idno,d.HANDLE_NAME,d.HANDLE_TIME,d.MONEY "+
       "  from TJY2_CW_DRAWMONEY d where d.status='CSTG')) r   "+
       " where bs_date>to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS') "+
		  " and bs_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
       "and  r.unit='"+unitName+"' and r.DEPARTMENT_ID='"+org_id+"'";


%>
<script type="text/javascript">

var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
		     {name: "type", compare: "like"},
		     {name: "department", compare: "like"},
		     {name: "user_", compare: "like"}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}],
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
	        	   var count = Qm.getSelectedCount();
		            Qm.setTbar({ 
		          	detail: count==1
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	 				}

}
}
 function  detail(){
	var type=Qm.getValueByName("type")
	var id=Qm.getValueByName("id");
	var url="";
	if(type=="差旅费报销"){
		url="url:app/finance/clfbxd_detail.jsp?id=" + id + "&pageStatus=detail";
	}else if(type=="培训费报销"){
		url="url:app/finance/pxfbxd_detail.jsp?id=" + id + "&pageStatus=detail";
	}else if(type=="费用报销"){
		url="url:app/finance/fybxd_detail.jsp?id=" + id + " &pageStatus=detail";
	}else if(type=="领款单"){
		url="url:app/finance/recipients_bill_detail.jsp?id=" + id + " &pageStatus=detail";
	}
	top.$.dialog({
		width: 900,
		height: 550,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "详情",
		content: url
	});
}
		
		
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_FYTJ_LIST"  sql="<%=sql %>">
	</qm:qm>
</body>
</html>
