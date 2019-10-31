<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>机构查询列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	User user = (User)SecurityUtil.getSecurityUser().getSysUser();
	String userId = user.getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
			{name:"applicants", id:"applicants", compare:"like"}
        ],
        tbar:[
            { text:'详情', id:'detail',icon:'detail', click: detail},
            "-",
	        { text:'新增', id:'add',icon:'add',click:add},
	        "-",
			{ text:'修改', id:'modify',icon:'modify', click: modify},
			"-",
			{ text:'删除', id:'del',icon:'delete', click: del},
			"-",
			{ text:'提交审核', id:'submit',icon:'submit', click: submit}
        ],
        listeners: {
            selectionChange : function(rowData,rowIndex){
            	var count=Qm.getSelectedCount();//行选择个数
                Qm.setTbar({select:count==1,modify:count==1,detail:count==1,del:count>0,submit:count==1});
            },
	
            rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if (rowData.flowStep == '1'){
	            	fontColor="maroon";
	            }
	            if (rowData.flowStep == '2'){
	            	fontColor="blue";
	            }
	            if (rowData.flowStep == '3'){
	            	fontColor="green";
	            }
	            if (rowData.flowStep == '4'){
	            	fontColor="red";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};
	
	
	function add(){
		top.$.dialog({
			width : 1024, 
			height : 620, 
			lock : true, 
			title:"新增",
			parent:api,
			content: 'url:app/device/oldreport_apply_detail.jsp?status=add',
			data : {"window" : window}
		});
	}
	
	function close(){
		api.close();
	}
	function modify(){
		var step = Qm.getValueByName("flowStep");	// 数据状态
		if(step == '0'){
			top.$.dialog({
				width : 800, 
				height : 700, 
				lock : true, 
				title:"修改信息",
				content: 'url:app/device/oldreport_apply_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
		}else{
			$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能修改哦，请重新选择！");
			return;
		}
	}
	
	function del(){
		var flow_step = Qm.getValueByName("flowStep");
		if(flow_step!="0"){
			$.ligerDialog.warn('提示：已经提交审核的计划不能删除！');
			return;
		}
		  $.del("确定删除?",
				  "oldReportAction/del.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	
	function detail(){
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title:"详情",
			content: 'url:app/device/oldreport_apply_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
					
			data : {"window" : window}
		});
	}
	
	
	
	//提交审核
	function submit(){
			var flow_steps = Qm.getValuesByName("flowStep");
			var flow_step=0;
			for (var i = 0; i < flow_steps.length; i++) {
				if(flow_steps[i]!="0"){
					$.ligerDialog.warn('提示：已经提交审核的计划不能再次提交！');
					return;
				}
			}
			
			var nextStep = (flow_step-0)+1;
			top.$.dialog({
				width : 400,
				height : 300,
				lock : true,
				title : "提交审核",
				content : 'url:app/device/oldapply_step.jsp?status=add&id='
						+ Qm.getValueByName("id")+"&step="+flow_step+"&nextStep=1",
				data : {
					"window" : window
				},
				close:function(){
					submitAction();
				}
			});
	}
	
	function importData() {
		  //创建上传实例
		  khFileUploader = new KHFileUploader({
		      fileSize: "10mb",//文件大小限制
		      buttonId: "procufilesBtns",//上传按钮ID
		      container: "procufilesDIVs",//上传控件容器ID
		      title: "设备档案信息",//文件选择框提示
		      saveDB : false,
		      extName: "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",//文件扩展名限制
		      fileNum: 1,//限制上传文件数目
		      callback : function(files){
		                  //文件无误，执行保存
		                  saveData(files);
		      }
		  });
		}
	
	function submitAction() {
		Qm.refreshGrid();
	}
      
	
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
	
	
	
	</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：状态
               <font color="black">“黑色”</font>代表任务未提交；
					<font color="maroon">“褐红色”</font>代表申请已提交；
					<font color="blue">“蓝色”</font>代表部门负责人通过；
					<font color="green">“绿色”</font>代表质量监督管理部通过；
					<font color="red">“红色”</font>代表不通过；
			</div>
		</div>
	</div>
	<qm:qm pageid="report_apply" script="false" singleSelect="false"   >
	<qm:param name="data_status" value="1" compare="=" />
	
	</qm:qm>
	
	
</body>
</html>