<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资产借用列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
				{name:"identifier", compare:"like"},
				{name:"department_name", compare:"like"},
				{name:"loaner_name", compare:"like"},
				{group:[
						{name:"loan_date", compare:">=", value:""},
						{label:"到", name:"loan_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{group:[
						{name:"back_date", compare:">=", value:""},
						{label:"到", name:"back_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"backer", compare:"like"},
				{name:"receiver", compare:"like"},
				{name:"ppe_self_no", compare:"like"},
				{name:"status", compare:"="}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            	<sec:authorize access="hasRole('TJY2_EQ_MANAGER')">
            	,"-",
                { text:'增加', id:'add',icon:'add', click: add},
                "-",
                { text:'修改', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除', id:'del',icon:'delete', click:del},
                "-",
                { text:'归还', id:'giveBack',icon:'give-back', click:giveBack}
                </sec:authorize> 
                ,"-",
                { text:'借用/归还记录', id:'backDetail',icon:'table', click:backDetail}
            	
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		},
        		rowAttrRender : function(rowData, rowid) {
    	            var fontColor="black";
    	            if(rowData.status=='已借出'){
    	            	fontColor="#8B008B";
    	            }else if(rowData.status=='部分归还'){
    	            	fontColor="orange";
    	            }else if(rowData.status=='全部归还'){
    	            	fontColor="green";
    	            }
    	            return "style='color:"+fontColor+"'";
    	        }
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); //行选择个数
			Qm.setTbar({
				detail: count==1,
				modify: count==1,
				del: count>0,
				giveBack: count==1
			});
	   		if(count>0){
	   			//判断按钮可用情况
				var status =Qm.getValuesByName("status").toString();
				var strs= new Array(); //定义一数组
 				strs=status.split(","); //字符分割
 				for (i=0;i<count;i++){
 					if(strs[i] == '未借出'){
 						Qm.setTbar({
 							giveBack:false
						});
 	 				}else if(strs[i] == '已借出'){
 						Qm.setTbar({
 							del: false
						});
 	 				}else if(strs[i] == '部分归还'){
 						Qm.setTbar({
 							modify: false,
 							del: false
						});
 	 				}else if(strs[i] == '全部归还'){
 						Qm.setTbar({
 							modify: false,
 							del: false,
 							giveBack:false
						});
 	 				}else{
 	 					Qm.setTbar({
 							modify: false,
 							del: false,
 							giveBack:false
						});
 	 				}
 				}
	   		}
		}
        
		function detail(){
			var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:800,
				height:630,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/ppe/ppe_loan_detail.jsp?pageStatus=detail&id='+id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width:800,
				height:630,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_loan_detail.jsp?pageStatus=add'
			});
        }
        
        //修改
        function modify(){
        	var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:800,
				height:630,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_loan_detail.jsp?pageStatus=modify&id='+id
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除？删除后无法恢复！","PpeLoanAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
        //归还
        function giveBack(){
        	var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:800,
				height:630,
				lock:true,
				title:"归还",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_giveBack_detail.jsp?pageStatus=modify&id='+id
			});
        }
        //借用/归还记录
	    function backDetail(){
	    	var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:1100,
				height:500,
				lock:true,
				title:"借用/归还记录",
				data:{window:window},
				content: 'url:app/equipment/ppe/ppe_back_choose_list.jsp?backtype=backDetail'
			});
	      }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未借出，
                <font color="#8B008B">“紫色”</font>代表已借出，
                <font color="orange">“橙色”</font>代表部分归还，
                <font color="green">“绿色”</font>代表全部归还。
            </div>
        </div>
    </div>
		<qm:qm  pageid="TJY2_PPE_LOAN" singleSelect="false"></qm:qm>
	</body>
</html>
