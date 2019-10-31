<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
%> 
<%
String uId = SecurityUtil.getSecurityUser().getId();
System.out.print(userId+"===="+uId);
%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
			           {name:"file_name",compare:"like"},
			           {name:"extend_mark",compare: "like"},
			           {group: [
						{name: "use_time", compare: ">="},
						{label: "到", name: "use_time", compare: "<=", labelAlign: "center", labelWidth:20}
				 ],columnWidth:0.33}
			],
			
		tbar: [ {
			 text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '接收', id: 'edit', icon: 'modify', click: edit
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
					edit: count==1
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				//alert(count);
				var up_status = Qm.getValueByName("status");
				//alert(up_status);
				var sp_status = Qm.getValueByName("sp_status");
				//alert(sp_status);
				var status={};
				if(count==0){
					status={detail:false, edit:false};
				}else if(count==1){
					if("未接收"==up_status){
						if("undefined"==sp_status){
							status={detail:false, edit:true};
						}else{
							status={detail:false, edit:true};
						}
					}else if("已接收"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false};
						}else{
							status={detail:true, edit:false};
						}
					}else{
						status={detail:true, edit:true};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false};
					}else{
						status={detail:false, edit:false};
					}
				}
				Qm.setTbar(status);
			}/*,
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='审核通过'){
                    fontColor="green";
                }else if(rowData.status=='审核未通过') {
                    fontColor="red";
                }else if(rowData.status=='待审核') {
                    fontColor="orange";
                }else if(rowData.status=='审核中') {
                    fontColor="blue";
                }
                return "style='color:"+fontColor+"'";
            }*/
		}
	};

	function edit() {
		var id = Qm.getValueByName("id");
        if(!id){
        	$.ligerDialog.warn('请先选择要提交审核的数据！',3);
            return;
        }
        top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "接收",
			content: 'url:app/qualitymanage/quality_ffhs_detail.jsp?id=' + id + '&pageStatus=modify'+'&js='+'a'
		});
//         $.ligerDialog.confirm('是否要接收吗？', function (yes){
//         if(!yes){return false;}
//          $("body").mask("提交中...");
//          top.$.ajax({
//              url: "quality/ffhs/filejs.do?id="+id,
//              type: "POST",
//              dataType:'json',
//              async: false,
//              success:function (data) {
//                  if (data) {
//                     top.$.notice('数据接收成功！！！',3);
//                     Qm.refreshGrid();
//                     $("body").unmask();
//                  }
//              },
//              error:function () {
//             	 $.ligerDialog.error('出错了！请重试！！!',3);
//                  $("body").unmask();
//              }
//          });
//         });
	}
	

	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/quality_ffhs_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	
</script>
</head>
<body>
	<qm:qm pageid="TJY2_QUALITY_MY_FFHS" singleSelect="true">
<%-- 	<sec:authorize access="!hasRole('TJY2_ZL_GLY')"> --%>
		<qm:param name="use_name_id" value="<%=userId%>" compare="=" />
<%-- 	</sec:authorize> --%>
	</qm:qm>
</body>
</html>