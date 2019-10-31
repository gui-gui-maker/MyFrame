<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>

<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String userid = SecurityUtil.getSecurityUser().getId();
	String users=curUser.getName();
	
	%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.3,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 120
		},
		sp_fields : [ 
		              {name : "gysmc",compare : "like"}, 
		              {name : "warehousing_num",compare : "like"}, 
		              {name : "lxrbm",compare : "like"}, 
		              {name : "lxrmc",compare : "like"}, 
		              {name : "dh",compare : "like"}, 
		              {name : "create_user_name",compare : "like"}],
		tbar : [ 
		         {text : '详情',id : 'detail',icon : 'detail',click : detail},"-",
		         {text : '入库',id : 'add',icon : 'add',click : addclick},
		         {text : '修改',id : 'modify',icon : 'modify',click : modify},
		         {text : '提交',id : 'save',icon : 'save',click : save},
				 { text:'删除', id:'del',icon:'delete', click:del},'-',
				{text:'导出验收表',id:'outYs',icon:'export',click:outYs},
				 {text:'导出入库单',id:'outRk',icon:'export',click:outRk}
				 //,{text:'存货报销',id:'bx',icon:'consuming',click:bx}
				 ,{text:'导出台账',id:'outTz',icon:'export',click:outTz}
				 
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {

			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
	        	var state=Qm.getValuesByName("zt");
				Qm.setTbar({
					detail : count == 1,
					modify : count == 1,
					outYs : count > 0 && $.inArray("未提交", state)==-1,
					outRk : count > 0 && $.inArray("未提交", state)==-1,
					del : count > 0,
					bx:count>0,
					save:count>0
				});
		   		if(count>0){
		   		//选中数据全是已提交或未提交
					if($.inArray("已报销", state)==-1 && $.inArray("报销中",state)==-1){
						if($.inArray("未提交", state)==-1){
							Qm.setTbar({//全部是已提交
								bx : true,
//	 							outYs : true,
//	 							outRk : true,
								modify :count == 1 && true,
								del : false,
								save :false
							});
						}else{
							Qm.setTbar({//全部是未提交
								bx : false,
//	 							outYs : false,
//	 							outRk : false,
								modify :count == 1 && true,
								del : true
							});
						}
					}else{
						//全是已报销
						Qm.setTbar({
							bx : false,
//	 						outYs : false,
//	 						outRk : false,
							modify : false,
							del : false
						});
					}
					
			   		if($.inArray("未提交", state)>-1 && 	$.inArray("已提交", state)>-1 || 	$.inArray("已报销", state)>-1|| 	$.inArray("报销中", state)>-1){
			   			Qm.setTbar({
							bx : false,
//	 						outYs : false,
//	 						outRk : false,
							save : false,
							del : false,
							modify : false
						});
			   		}
		   		}
				
			},rowAttrRender:function(rowData, rowid) {
				 var fontColor="black";
				 if(rowData.zt=='已报销'){
					 fontColor="green";
				 }else if(rowData.zt=='已提交'){
					 fontColor='orange';
				 }else if(rowData.zt=='报销中'){
					 fontColor="violet";
				 }else if(rowData.zt=='报销作废'){
					 fontColor="red";
				 }
				 return "style='color:"+fontColor+"'";
			}
		}
	}
	//四舍五入
	 function decimal(num,v){
	    	var vv = Math.pow(10,v);
	    	return Math.round(num*vv)/vv;
	    	}
	
	//存货报销
	function bx(){
		var businessPart="100020,100021,100022,100023,100024,100063,100034,100035,100037,100033,100065,100036,100049,100066,100067,100068,100084"
		var fgld=false;//是否需要分管领导审核
		var dpId=<%=curUser.getDepartment().getId() %>
		//机电或承压
		if(businessPart.indexOf(dpId)>-1){
			fgld=true;
		}
        var zjes = Qm.getValuesByName("zje");
        var zje=0;
        $.each(zjes,function(n,v){
        	zje=parseFloat(zje)+parseFloat(v);
        });
        var bh=Qm.getValuesByName("warehousing_num");
        zje=decimal(zje,2);
        var ids=Qm.getValuesByName("id");
		winOpen({
			width: 900,
			height: 500,
            lock : true,
            title : "存货报销",
            content : 'url:app/supplies/warehousing/bx.jsp?zje='+zje+'&bh='+bh+'&ids='+ids+'&fgld='+fgld,
            data : {
                "window" : window,"ids":ids
            }
        });
	}
	//导出验收表
	function outYs(){
        var id = Qm.getValuesByName("id").toString();
        var url="com/tjy2/warehousings/outYs.do?id="+id;
        download(url,"post",id);
	}
	//导出台账
	function outTz(){
		 var id = Qm.getValuesByName("id").toString();
         top.$.dialog({
             width: 600,
             height: 400,
             lock: true,
             title: "导出",
             content: "url:app/supplies/warehousing/warehousing_tz.jsp?id="+id
         });
	}
	 function download(url, method, id){
         jQuery('<form action="'+url+'" method="'+(method||'post')+'">' +  // action请求路径及推送方法
             '<input type="text" name="id" value="'+id+'"/>' + // id
             '</form>')
             .appendTo('body').submit().remove();
     }
	 
	//导出入库单
	function outRk(){
        var id = Qm.getValuesByName("id");
        var url="com/tjy2/warehousings/outRk.do?id="+id;
        download(url,"post",id);
	}
	function del() {
		$.del("确定删除?", "com/tjy2/warehousings/deleteWarehoesing.do", {
			"ids" : Qm.getValuesByName("id").toString()
		})
	}

	function detail() {
		top.$.dialog({
			width : $(top).width()-300,
			height :  $(top).height()-300,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "详情",
					content : 'url:app/supplies/warehousing/warehousing_detail.jsp?pageStatus=detail&id='
							+ Qm.getValueByName("id")
				}).max();

	}
	function save(){
        var ids = Qm.getValuesByName("id").toString();

		 $("body").mask("正在保存数据，请稍后！");
		 $.ajax({
            url: "com/tjy2/warehousings/beanPlTj.do",
            type: "POST",
            data:{"ids":ids},
            success : function(data, stats) {
				$("body").unmask();
				if (data["success"]) {
					top.$.dialog.notice({
						content : '提交成功'
					});
					Qm.refreshGrid();
				} else {
					$.ligerDialog.error('提示：' + data.message);
				}
			},
            error : function(data) {
                $("body").unmask();
                $.ligerDialog.error('提交数据失败！');
            }
        });
		 
	 }
	
	function addclick() {
		$.ajax({
            url: "com/tjy2/warehousings/getzdbq.do",
            type: "POST",
            success : function(data, stats) {
            	
            	top.$.dialog({
        			width : $(top).width()-300,
        			height :  $(top).height()-300,
        			lock : true,
        			parent : api,
        			data : {
        				window : window,wpmc:data.wpmc,ggxh:data.ggxh
        			},
        			title : "入库",
        			content : 'url:app/supplies/warehousing/warehousing_detail.jsp?pageStatus=add'
        		}).max();
            	
            }
		})
		

	}
	function modify() {
		$.ajax({
            url: "com/tjy2/warehousings/getzdbq.do",
            type: "POST",
           // data:{"warehousing":},
            success : function(data, stats) {
//             var zt=Qm.getValueByName("zt")=='已提交'?1:0;
            	top.$.dialog({
        			width : $(top).width()-300,
        			height :  $(top).height()-300,
        			lock : true,
        			parent : api,
        			data : {
        				window : window,wpmc:data.wpmc,ggxh:data.ggxh
        			},
        			title : "修改",
        			content : 'url:app/supplies/warehousing/warehousing_detail.jsp?pageStatus=modify&id='
        				+ Qm.getValueByName("id")
        		}).max();
            	
            	
            }
		});
		
		
		
		
	}
</script>

</head>
<body>

 <div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表入库单未提交，
				<font color="violet">“紫色”</font>代表报销中，
				<font color="red">“红色”</font>代表报销作废，
				<font color="orange">“橙色”</font>代表入已提交，
                <font color="green">“绿色”</font>代表已报销。
			</div>
		</div>
	</div>
	<div class="lb_gys_list" id="titleElement">
		<qm:qm pageid="ch_rk_list">
		</qm:qm>
	</div>
	
</body>
</html>