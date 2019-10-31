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
				     {name: "org_name", compare: "like"},
				     {name: "train_unit", compare: "like"},
				     {name: "tratn_type", compare: "="},
				     {group: [
				  				{name: "tratn_time_start", compare: ">="},
				  				{label: "到", name: "tratn_time_end", compare: "<=", labelAlign: "center", labelWidth:20}
				  		 ]},
				  	  {name: "train_remark", compare: "like"},
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		  ,  "-",
		  {text: '新增', id: 'add', icon: 'add', click: add } 
		  , "-", {
				text: '修改', id: 'alteration', icon: 'modify', click: alteration
			} 
		, "-", {
			text: '删除', id: 'del', icon: 'del', click: del
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
		          	detail: count==1,
		          	alteration: count==1,
		          	cancellation : count>0,
		          	del:count>0
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	
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
			      $.del("确定删除?", "tjy2CertificateTrainAction/deleteTrain.do", {
			       	"ids" : Qm.getValuesByName("id").toString()
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
		         content: 'url:app/fwxm/certificate/certificate_train.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
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
		         content: 'url:app/fwxm/certificate/certificate_train.jsp?pageStatus=add'
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
		         title: "修改",
		         content: 'url:app/fwxm/certificate/certificate_train.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
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
</script>

</head>
<body>
       <qm:qm pageid="certificate_train">
       </qm:qm>
</body>
</html>