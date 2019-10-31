<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">

var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[

				     {name: "name", compare: "like"},
				 	{name:'cert_type',compare:'like',logic:'or'},{name:'cert_type',compare:'like'},
				     {name: "cert_category", compare: "="},

				     {group: [
				  				{name: "cert_end_date", compare: ">="},
				  				{label: "到", name: "cert_end_date", compare: "<=", labelAlign: "center", labelWidth:20}
				  		 ]},
				  	 {group: [

			  				{name: "first_get_date", compare: ">="},
			  				{label: "到", name: "first_get_date", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 ]}

		] , 
		tbar: [ 
		         {text: '详情', id: 'detail', icon: 'detail', click: detail}
		  ,  "-",{text: '新增', id: 'add', icon: 'add', click: add } 
		  ,  "-",{text: '证件注销', id: 'cancellation', icon: 'bluebook', click: cancellation}
		  <sec:authorize access="hasRole('CZGL')">
		  ,  "-",{text: '证件变更', id: 'alteration', icon: 'modify', click: alteration} 
		  , "-", {text: '删除', id: 'del', icon: 'del', click: del}
		  , "-", {text: '批量处理', id: 'pl', icon: 'modify', click: pl}
		  </sec:authorize>
		  ],
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
	        	   var count = Qm.getSelectedCount();
		            Qm.setTbar({ 
		          	detail: count==1,
		          	alteration: count==1,
		          	cancellation : count>0,
		          	del:count>0,
		          	pl:count>0
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	 var blue =365;
		   		 	var yell = 180;
		   			var red = 0;
		      		    
		       		var fontColor="black";
		       		//到期30天之内10天之前 显示橙色
		       		
		       		if (rowData.waring <=blue & rowData.waring >yell){
		       			fontColor="blue";
		       		}
		               //到期10天之内 显示蓝色
		       		else if(rowData.waring <=yell & rowData.waring >red ) {
		       			fontColor="orange";
		       		}
		       		//到期 显示红色
		       		else if(rowData.waring <=red) {
		       			fontColor="red";
		       		}
	       		return "style='color:"+fontColor+"'";
	 				}

}
}
		
		
		
		function cancellation(){
     	var id=Qm.getValuesByName("id");
	   $.ligerDialog.confirm('是否注销？', function (yes){
        if(!yes){return false;}
        top.$.ajax({
                     url: "employee/cert/certCancellation.do?id="+id,
                     type: "GET",
                     dataType:'json',
                     async: false,
                     success:function (data) {
                        if(data.success){
                            $.ligerDialog.success("注销成功！");
                            Qm.refreshGrid();//刷新
                        }else{
                            $.ligerDialog.warn(data.msg);
                        }
                     },
                     error:function () {
                         $.ligerDialog.warn("注销失败！");
                     }
                 });
    });
         }
		function del(){
			      $.del("确定删除?", "employee/cert/deleteCert.do", {
			       	"id" : Qm.getValuesByName("id").toString()
			      })
		}
		
		function detail(){
	         top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/fwxm/certificate/certificate_permit.jsp?pageStatus=detail&id='+Qm.getValueByName("id")+"&empId="+Qm.getValueByName("employee_id")
	          });
	         
        }
		function add(){
			top.$.dialog({
		         width: 800,
		         height: 400,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增",
		         content: 'url:app/fwxm/certificate/certificate_permit.jsp?pageStatus=add'
	          });
		}
        function alteration(){
        	top.$.dialog({
		         width: 900,
		         height: 500 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "证件变更",
		         content: 'url:app/fwxm/certificate/certificate_permit.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&empId="+Qm.getValueByName("employee_id")
	          });
        	
        }
        function edit1(){
       	 top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改信息",
		         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&permission=apply&fkEmployee="+Qm.getValueByName("fk_employee")
	          });
       	
       }
        function  sendMsg(){
        	top.$.dialog({
		         width: 500,
		         height: 200,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "发送短信",
		         content: 'url:app/humanresources/message_send.jsp?ids='+Qm.getValuesByName("id")
	          });
        	
        }
        function loadGridData(treeId) {
    		if (treeId != null&&treeId !="100000") {
    			Qm.setCondition([ {
    				name : "work_department",
    				compare : "like",
    				value : treeId
    			} ]);
    		} else {
    			Qm.setCondition([]);
    		}
    		Qm.searchData();
    	}
        function pl(){
        	top.$.dialog({
		         width: 500,
		         height: 260,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "发送短信",
		         content: 'url:app/fwxm/certificate/certificate_permit_pl.jsp?id='+Qm.getValuesByName("id")+"&pageStatus=add"
	          });
        	
        }
</script>

</head>
<body>
<div class="item-tm" id="titleElement">
  <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表证书一年内到期，
				<font color="orange">“橙色”</font>代表证书半年内到期，
				<font color="red">“红色”</font>代表证书已超过有效日期。
				
			</div>
		</div>
	</div>
       <qm:qm pageid="employee_cert">
       </qm:qm>
</body>
</html>