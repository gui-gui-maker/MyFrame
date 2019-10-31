<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User) curUser.getSysUser();
%>
<head>
<title>车辆维保申请列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ group : [
					{ name : "apply_date", compare : ">=", value : "" },
					{ label : "到", name : "apply_date", compare : "<=",value:"",labelAlign:"center",labelWidth:20}
				],columnWidth:0.33
				}, 
				{ name : "data_status", compare : "=" } ],
		tbar : [ { text : '详情', id : 'detail', click : detail, icon : 'detail' }, '-', 
				 { text : '新增', id : 'add', click : add, icon : 'add' }, '-', 
				 { text : '修改', id : 'modify', click : modify, icon : 'modify' }, '-', 
				 { text : '删除', id : 'del', click : del, icon : 'del' }, '-', 
				 { text : '提交', id : 'submit', click : submit, icon : 'submit' }, '-', 
				 <sec:authorize ifAnyGranted="CAR_WB_OFFICE,CAR_WB_FLEET,CAR_WB_SEAL,sys_administrate">
				 { text : '处理', id : 'deal', click : deal, icon : 'dispose' }, '-', 
				 { text : '打印', id : 'print', click : print, icon : 'print' }, '-', 
				 </sec:authorize> 
				 <sec:authorize ifAnyGranted="CAR_WB_FLEET,sys_administrate">
				 { text : '标记已维保', id : 'done', click : done, icon : 'dispose' }, '-', 
				 </sec:authorize> 
				 { text : '流转过程', id : 'turnHistory', icon : 'follow', click : turnHistory } ],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if(rowData.data_status=="车队负责人待审核"){
					fontColor = "blue";
				}else if(rowData.data_status=="办公室负责人待审核"){
					fontColor = "maroon";
				}else if(rowData.data_status=="车队负责人待盖章"){
					fontColor = "orange";
				}else if(rowData.data_status=="已盖章"){
					fontColor = "green";
				}else if(rowData.data_status=="维保已完成"){
					fontColor = "#8FBC8F";
				}else if(rowData.data_status=="审核不通过"){
					fontColor = "red";
				}
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var data_status = Qm.getValueByName("data_status");
		if (count == 1) {
			if(data_status == "未提交"){
				Qm.setTbar({ modify : true, del : true, detail : true, submit : true, deal : false, print : true, done : false, turnHistory : false });
			}else if(data_status == "车队负责人待审核"){
				<sec:authorize access="hasRole('CAR_WB_FLEET')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : true, print : true, done : false, turnHistory : true });
				</sec:authorize>
				<sec:authorize access="!hasRole('CAR_WB_FLEET')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : false, turnHistory : true });
				</sec:authorize>
			}else if(data_status == "办公室负责人待审核"){
				<sec:authorize access="hasRole('CAR_WB_OFFICE')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : true, print : true, done : false, turnHistory : true });
				</sec:authorize>
				<sec:authorize access="!hasRole('CAR_WB_OFFICE')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : false, turnHistory : true });
				</sec:authorize>
			}else if(data_status == "车队负责人待盖章"){
				<sec:authorize access="hasRole('CAR_WB_SEAL')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : true, print : true, done : false, turnHistory : true });
				</sec:authorize>
				<sec:authorize access="!hasRole('CAR_WB_SEAL')">
					Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : false, turnHistory : true });
				</sec:authorize>
			}else if(data_status == "已盖章"){
				Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : true, turnHistory : true });
			}else if(data_status == "审核不通过"){
				Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : false, turnHistory : true });
			}else if(data_status == "维保已完成"){
				Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : true, done : false, turnHistory : true });
			}else{
				Qm.setTbar({ modify : false, del : false, detail : true, submit : false, deal : false, print : false, done : false, turnHistory : false });
			}
		} else {
			Qm.setTbar({ modify : false, del : false, detail : false, submit : false, deal : false, print : false,  done : false, turnHistory : false });
		}
	}
	//新增
	function add() {
		var width = 800;
		var height = 800;
		top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_apply_detail.jsp?pageStatus=add'
		});
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var width = 800;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_apply_detail.jsp?pageStatus=detail&id='+ selectedId
		});
	}
	//修改
	function modify() {
		var selectedId = Qm.getValueByName("id");
		var width = 800;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_apply_detail.jsp?pageStatus=modify&id=' + selectedId
		});
	}
	//删除
	function del() {
		var selectedId = Qm.getValueByName("id");
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "carWbAction/deleteInfo.do?id=" + selectedId,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}
	//提交
	function submit(){
		var id = Qm.getValueByName("id");
        if(!id){
       	 	top.$.notice('请先选择要提交审核的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false; }
			$("body").mask("正在提交，请稍后！");
	        getServiceFlowConfig("TJY2_CAR_WB","",function(result,data){
				if(result){
					top.$.ajax({
	                    url: "carWbAction/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_CAR_WB",
	                    type: "GET",
	                    dataType:'json',
	                    async: false,
	                    success:function (data) {
	                        if (data) {
	                       		$("body").unmask();
	                      	 	top.$.notice('提交成功！');
	                           	Qm.refreshGrid();
	                        }
	                    },
	                    error:function () {
							$("body").unmask();
	                   		$.ligerDialog.error('出错了！请重试！');
	                    }
	                });
				}else{
		           	$.ligerDialog.error('出错了！请重试！',3);
		            $("body").unmask();
				}
	        });
        });
	}
	//处理
	function deal() {
		var id = Qm.getValueByName("id");
		$.ajax({
        	url: "carWbAction/getWorktaskParam.do?id="+id,
            type: "POST",
            success: function (resp) {
				if(resp.success){
            		var config={
                	        width :800,
                	        height : 630,
                	        id:resp.id,
                	        title:resp.title
                	    }
            		// 调用流程的方法
               	    openWorktask(config);
            	}else{
            		$.ligerDialog.alert('参数错误，缺少任务标识！');
            	}
            },
            error: function (data,stats) {
            	$.ligerDialog.alert('获取任务参数出错！');
            }
        });
	}
	// 打印维保申请单
	function print(){
		var selectedId = Qm.getValueByName("id").toString();  // 业务信息ID
		$.getJSON("carWbAction/getPrintInfo.do?id="+selectedId, function(resp){
			if(resp.success){
				if(resp.data != null && resp.data.length !=0){
					//获取当前网址
	            	var curWwwPath=window.document.location.href;      
	        		//获取主机地址之后的目录    
	        		var pathName=window.document.location.pathname;     
	        		var pos=curWwwPath.indexOf(pathName);     
	        		//获取主机地址
	        		var localhostPaht=curWwwPath.substring(0,pos);
	        		var realPath=localhostPaht;
	        		
					var fleet_signPicture_url;
					var office_signPicture_url;
					var seal_signPicture_url;
					if(typeof(resp.fleet_signPicture)!="undefined"){
						fleet_signPicture_url = realPath+"/upload/"+resp.fleet_signPicture;
	        		}
	        		if(typeof(resp.office_signPicture)!="undefined"){
	        			office_signPicture_url = realPath+"/upload/"+resp.office_signPicture;
	        		}
	        		if(typeof(resp.seal_signPicture)!="undefined"){
	        			seal_signPicture_url = realPath+"/upload/"+resp.seal_signPicture;
	        		}
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "打印维保申请单",
						parent: api,
						content : 'url:app/car/car_wb/pc/docEditor.jsp?pageStatus=detail',	
						data: {pwindow: window, bean: resp.data,fleet_signPicture_url: fleet_signPicture_url,office_signPicture_url: office_signPicture_url,seal_signPicture_url: seal_signPicture_url}
					}).max();
				}else{
					$.ligerDialog.alert("未获取到打印信息！");
				}
			}
		})
	}
	//标记已维保完成
	function done() {
		var selectedId = Qm.getValueByName("id");
		$.ligerDialog.confirm("是否标记已完成维保？", function(yes) {
			if (yes) {
				$.ajax({
					url : "carWbAction/done.do?id=" + selectedId,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("操作成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}
	//查看流转过程
	function turnHistory() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流转过程",
			content : 'url:carWbAction/getFlowStep.do?id='+ Qm.getValueByName("id").toString(),
			data : {
				"window" : window
			}
		});
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">
				提示： <font color="black">“黑色”</font>代表未提交； <font color="blue">“蓝色”</font>代表车队负责人待审核；
				<font color="maroon">“褐红色”</font>代表办公室负责人待审核； <font color="orange">“橘色”</font>代表车队负责人待盖章；
				<font color="green">“绿色”</font>代表已盖章；<font color="#336666">“暗绿色”</font>代表维保已完成；
				<font color="red">“红色”</font>代表审核不通过。
			</div>
		</div>
	</div>
	<%
		StringBuffer sql = new StringBuffer();
		String query_sql = "select t.* from TJY2_CAR_WB t where t.data_status != '99' and t.create_user_id = '" + user.getId()
				+ "' order by t.apply_date desc";
	%>
	<sec:authorize ifAnyGranted="CAR_WB_OFFICE,CAR_WB_FLEET,CAR_WB_SEAL">
		<%
			query_sql = "select * from (select t.* from TJY2_CAR_WB t where  t.data_status != '99' and t.create_user_id = '" + user.getId()
						+ "' or t.fleet_deal_id = '" + user.getId() + "' or t.office_deal_id = '" + user.getId()
						+ "' or t.seal_user_id = '" + user.getId() + "' " + "union "
						+ "select w.* from TJY2_CAR_WB w, v_pub_worktask v where w.id = v.service_id and  w.data_status != '99' and v.status = '0' and v.handler_id = '"
						+ user.getId() + "') order by create_date desc";
				System.out.println(query_sql);
		%>
	</sec:authorize>
	<sec:authorize access="hasRole('sys_administrate')">
		<%
			query_sql = "select t.* from TJY2_CAR_WB t where t.data_status != '99' order by t.create_date desc";
				System.out.println(query_sql);
		%>
	</sec:authorize>
	<qm:qm pageid="TJY2_CAR_WB" script="false" singleSelect="true"
		sql="<%=query_sql.toString()%>">
	</qm:qm>
</body>
</html>