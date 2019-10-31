<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <bpm:ifPer function="功能编号" activityId="环节id">
    //对应的功能权限操作
</bpm:ifPer>
    <%@include file="/k/kui-base-form.jsp" %>
    <%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>
<!--获取当前登录人  -->
<%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String user=useres.getName();
String userid= useres.getId();
%>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="app/finance/js/caculateTrainingFee.js"></script>
<script type="text/javascript">
var pageStatus = "${param.pageStatus}";
var businessPart="100020,100021,100022,100023,100024,100063,100034,100035,100037,100033,100065,100036,100049,100066,100067,100068"
var emails = [
              { name: "Peter Pan", to: "peter@pan.de" },
              { name: "Molly", to: "molly@yahoo.com" },
              { name: "Forneria Marconi", to: "live@japan.jp" },
              { name: "Master <em>Sync</em>", to: "205bw@samsung.com" },
              { name: "Dr. <strong>Tech</strong> de Log", to: "g15@logitech.com" },
              { name: "Don Corleone", to: "don@vegas.com" },
              { name: "Mc Chick", to: "info@donalds.org" },
              { name: "Donnie Darko", to: "dd@timeshift.info" },
              { name: "Quake The Net", to: "webmaster@quakenet.org" },
              { name: "Dr. Write", to: "write@writable.com" },
              { name: "GG Bond", to: "Bond@qq.com" },
              { name: "Zhuzhu Xia", to: "zhuzhu@qq.com" }
          ];

function selectCarNo(){
	var data_status = $("#statue").val();
	if(data_status=="SUBMIT"){
		$.ligerDialog.warn("数据已提交，此项目不能再修改！");
		return false;
	}
	var title = "派车单选择";
	var url = "url:app/car/car_used_list.jsp";
	var width = 800	;
	top.$.dialog({
		width : width,
		height : 460,
		lock : true,
		parent : api,
		id : "win98",
		title : title,
		content : url,
		cancel: true,
		ok : function() {
			
			var data = this.iframe.contentWindow.f_select();
			if(!data){
				return false;
			}
			var item_id = [],
			item_name =[];
			//remark = [];
			$("[name=clQdR1]").val("");
			$("[name=clQdR2]").val("");
			$("[name=clQdR3]").val("");
			$("[name=clQdR4]").val("");
			$("[name=clQdR5]").val("");
			
			$("[name=clZdR1]").val("");
			$("[name=clZdR2]").val("");
			$("[name=clZdR3]").val("");
			$("[name=clZdR4]").val("");
			$("[name=clZdR5]").val("");
			
			$("#clZdDd1").val("");
			$("#clZdDd2").val("");
			$("#clZdDd3").val("");
			$("#clZdDd4").val("");
			$("#clZdDd5").val("");
			for(var i=0;i<data.length;i++){
				//起点
				setDateFormatValues(data[i].out_date,'clQdR'+(i+1));
    			//终点
				setDateFormatValues(data[i].receive_date,'clZdR'+(i+1));
    			$("#clZdDd"+(i+1)).val(data[i].drive_route);
    			//派车单
    			item_id.push(data[i].id);
    			item_name.push(data[i].apply_sn);
    			//remark.push(data[i].apply_reason);
			}
			$("#carApplyIds").val(item_id.join(","));
			$("#carApplyNumbers").val(item_name.join(","));
			//$("#Remark").val(remark.join(","));
			return true;
		}
	});
} 
    function setDateFormatValues(value, objName){
    	if(value!=null){
       		if(value.length==10){
           		var date = value.substring(5,value.length);
           		var year = value.substring(0,4);
           		$("[name="+objName+"]").val(date);
           		//$("[name="+objName+"Year]").val(year);
           	}else{
           		$("[name="+objName+"]").val(value);
           	}
       	}
    }
    function openFile1(){
    	window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id=402883a265e6f8150165ea7d4a494ae6&proportion=0";
    }
    function openFile(){
    	 var url='url:app/finance/bxzd.jsp';
	   	 top.$.dialog({
		         width: 400,
		         height:240,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "费用报销",
		         content: url
	        });
    	 }
    
      //金額只能輸入正數
    //通过2步做到输入的为非负数
    //1.去掉多余的小数点
    //2.保证只能输入小数点或数字
    function onlyNonNegative(obj) {
     var inputChar = event.keyCode;
     //alert(event.keyCode);
     
     //1.判断是否有多于一个小数点
     if(inputChar==190 ) {//输入的是否为.
      var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
      var index2 = obj.value.indexOf(".",index1);
      while(index2!=-1) {
       //alert("有多个.");
       
       obj.value = obj.value.substring(0,index2);
       index2 = obj.value.indexOf(".",index1);
      }
     }
     //2.如果输入的不是.或者不是数字，替换 g:全局替换
     obj.value = obj.value.replace(/[^(\d|.)]/g,"");
    }
    
  //單據等只能輸入正整數
    //通过2步做到输入的为非负数
    //1.去掉小数点
    //2.保证只能数字
    function NonNegative(obj) {
     var inputChar = event.keyCode;
     //alert(event.keyCode);
     
     //1.判断是否有多于一个小数点
     if(inputChar==190 ) {//输入的是否为.
      var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
      var index2 = obj.value.indexOf(".",index1);
     
       
       
       obj.value = obj.value.substring(0,index2);
       index2 = obj.value.indexOf(".",index1);
     
     }
     //2.如果输入的不是.或者不是数字，替换 g:全局替换
     obj.value = obj.value.replace(/[^(\d|.)]/g,"");
    }
    
    
       
        
        function choosePerson(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#userId").val(p.id);
                    $("#user").val(p.name);
                }
            });
        }
function chooseOrg(){
	 var dw = $("#clDw-txt").val();
	 var parent_id="100000";
	  if(dw=="鼎盛公司"){
		  parent_id="100047";
	 }
	 if(dw=="四川省特种设备检验检测协会"){
		  parent_id="100042";
		 }
	 if(dw=="四川省特种设备检验研究院工会委员会"){
		  parent_id="100048";
		 }
	 if(dw=="基建办"){
		  parent_id="100043";
		 } 
	 if(dw=="中共四川省特种设备检验研究院委员会"){
		  parent_id="100079";
		 } 
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择部门",
                content: 'url:app/common/org_choose_new.jsp?par_id='+parent_id,
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#departmentId").val(p.id);
                    $("#department").val(p.name);
                    if("<%=useres.getDepartment().getId() %>"!=p.id){
                    	top.$.notice("<span style='color:red;'>所选部门与人员信息不符，请留意！</span>", 7, "k/kui/images/icons/dialog/32X32/hits.png");
                    }
                }
            });
        }
function save(){
	 
    var obj=$("#form1").validate().form();
    if(obj){
        if($("#department").val() != "" && $("#department").val() != undefined){
          if($("#departmentId").val() == "" || $("#departmentId").val() == undefined){
              $.ligerDialog.warn("部门id为空，请重新选择部门！");
              return;
          }else if($("#clCcid").val() == "" || $("#clCcid").val() == undefined ){
              $.ligerDialog.warn("培训人id为空，请重新选择培训人！");
              return;
          }
        }
     	var obj=$("#form1").validate().form();
		 if(obj){
			 var formData = $("#form1").getValues();
			 formData.clCcr=CCnames;
			 $("body").mask("正在提交，请稍后！");
         $.ajax({
             url: "finance/pxfbxd/save1.do",
             type: "POST",
             datatype: "json",
             contentType: "application/json; charset=utf-8",
             data: $.ligerui.toJSON(formData),
             success: function (data, stats) {
                 $("body").unmask();
             if(data.success){
                     top.$.dialog.notice({content:'操作成功！'});
                     setTimeout("close()","300");
             }else{
            	 $.ligerDialog.error('提示：' + data.ts);
             }
                 
             },
             error: function (data,stats) {
                 $("body").unmask();
                 $.ligerDialog.error('提示：' + data.msg);
             }
         });
         
			 
		 }else{
			 return;
		}
       api.data.window.Qm.refreshGrid();//api.close();
   }else{
       return;
  }
} 
function close(){
	 api.data.window.Qm.refreshGrid();
	 api.close();
}
        
        $(function () {
        //默认隐藏分管领导签字部分
        $("#fgld").hide();
        
        if(pageStatus=="add"){
            var orgId = <%=useres.getDepartment().getId() %>;
            //如果是新增，申请部门id设为当前登录者所在部门的id
            $("#departmentId").val(orgId);
        
            //如果是新增，并且当前登录人所在部门为业务部门（机电、承压），则将管领导签字部分显示出来
            if(businessPart.indexOf(orgId)>-1){
                $("#fgld").show();
            }
            $("#data_tr4").hide();
            $("#data_tr5").hide();
        }
        
        
        
        
             var tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
                        { text: '关闭', id: 'close', icon:'cancel', click:function(){ api.data.window.Qm.refreshGrid();api.close();}}];
                    
            if ("${param.pageStatus}"=="detail")
            var tbar=[
<sec:authorize access="hasRole('TJY2_CW_CHECK')">{ text: '打印', id: 'print', icon: 'print', click: print},</sec:authorize>
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
                     ];
            if ("${param.pageStatus}"=="detail" && "${param.a}"=="a"){
            	if("${param.bxstatus}"=="1"){
            		tbar=[{ text: '取消确认', id: 'submit', icon: 'submit', click: undirectChange},
                  	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
                  ];
            	}else if("${param.bxstatus}"=="0"){
            		tbar=[{ text: '确认', id: 'submit', icon: 'submit', click: directChange},
                  	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
                  ];
            	}
                }
            $("#form1").initForm({
                showToolbar: true,
                toolbarPosition: "bottom",
                toolbar: tbar,
                getSuccess: function(resp){
                    var readers = [];
                    var names = [];
                    var ids = [];
                    if(resp.data.clCcr){
                         names = resp.data.clCcr.split(",");
                         ids = resp.data.clCcid.split(",");
                         for(var i=0;i<names.length;i++){
                            readers.push({
                                types : "reader",
                                name: names[i],
                                id: ids[i]
                            });
                        } 
                        addReader(readers,false);
                    }
                    //如果申请部门id不为空，并且属于业务部门（机电、承压），则将管领导签字部分显示出来
                    if(resp.data.departmentId!="" && businessPart.indexOf(resp.data.departmentId)>-1){
                        $("#fgld").show();
                    }
                    var a=resp.data.handleTime;
                    if(a!=null){
                    	var handleTime=a.substring(a.indexOf("/")+1,a.lastIndexOf(" "));
                    	$("#handleTime").html(handleTime);
                    }
                	
                	if("${param.pageStatus}"=="detail" || "${param.pageStatus}"=="modify"){
                    	if(resp.data.clZdR4==null || resp.data.clZdR4=="null" || resp.data.clZdR4==""){
                    		$("#data_tr4").hide();
                    	}
                    	if(resp.data.clZdR5==null || resp.data.clZdR5=="null" || resp.data.clZdR5==""){
                    		$("#data_tr5").hide();
                    	}
                   	} 
                }
            });
            $("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
            
            $('#clCcr').autocomplete("employee/basic/searchEmployee.do", {
                    max: 12,    //列表里的条目数
                    minChars: 1,    //自动完成激活之前填入的最小字符
                    width: 200,     //提示的宽度，溢出隐藏
                    scrollHeight: 300,   //提示的高度，溢出显示滚动条
                    scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                    matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                    autoFill: false,    //自动填充
                    formatItem: function(row, i, max) {
                        return row.name + '   ' + row.mobileTel;
                    },
                    formatMatch: function(row, i, max) {
                        return row.name + row.mobileTel;
                    },
                    formatResult: function(row) {
                        return row.name;
                    }
                }).result(function(event, row, formatted) {
        //            alert(row.mobileTel);
                });
            //选择单位时 清空部门

          	

      
        });
        
        window.onload = function() { 
        	$('#clDw-txt').change(function() {
    			var clDw = $("#clDw-txt").val();
    			if("四川省特种设备检验研究院" == clDw || "中共四川省特种设备检验研究院委员会" == clDw || "四川省特种设备检验检测协会" == clDw){
    				//$("#departmentId").val("");
    				$('#department').val(""); 
    			}else if("鼎盛公司" == clDw){
    				$("#departmentId").val("100052");
        			$("#department").val(clDw);
    			}else if("司法鉴定中心" == clDw){
    				$("#departmentId").val("100044");
        			$("#department").val(clDw);
    			}else if("四川省特种设备检验研究院工会委员会" == clDw){
    				$("#departmentId").val("100059");
        			$("#department").val(clDw);
    			}else if("基建办" == clDw){
    				$("#departmentId").val("100050");
        			$("#department").val(clDw);
    			}
    		});
        }; 
        function selectReaders(){
        
            var readers = $("#form1").data("readers"); 
            selectUnitOrUser("5",1,"","",function(datas){
                if(!datas.code)return;
                var codeArr = datas.code.split(",");
                var nameArr = datas.name.split(",");
                var readers = [];
                var existReaders = $("#form1").data("readers")||[];
                for(var i in codeArr){
                    var exist = false;
                    for(var j in existReaders){
                        if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
                            exist=true;
                    }
                    if(exist) continue;
                    readers.push({
                        types : "reader",
                        name: nameArr[i],
                        id: codeArr[i]
                    });
                }
                addReader(readers,true);
            });
        }
       
       var CCnames='';//出差人姓名
       var CCids='';//出差人id
        function addReader(newReaders,isNew){
        var names = '';
        var ids = '';
        var repids='';
        var repNames='';
        //alert(names);
         for(var i in newReaders){
                if(CCids.indexOf(newReaders[i].id)==-1){
                    names = names+newReaders[i].name+",";
                    ids = ids+newReaders[i].id+",";
                    var mtext='<span class="label label-read" id="' + (isNew?newReaders[i].id:newReaders[i].id) + '"><span class="text">' + newReaders[i].name;
                    if(pageStatus!="detail"){
                       mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
                    }
                    if(pageStatus=="detail"){
                       mtext = mtext+',</span></span>'
                       }
                    $("#reader_td").prepend(mtext);
                } else if (newReaders[i].name){
                    repNames = repNames+newReaders[i].name+",";
                }
           }
           if(repNames != ""){
                    $.ligerDialog.warn("人员："+"<span style='color:red;'>"+repNames.substring(0,repNames.length-1)+"</span>"+" 已存在！");
           }
           CCnames = CCnames+names;
           CCids = CCids+ids;
           $("#clCcr").val(CCnames);
           $("#clCcid").val(CCids);
        }
        

        function deleteReader(id,isNew){
            $("#"+id).remove();
            var ids = CCids.split(",");
            var names = CCnames.split(",");
            for(var i in ids){
               if(id==ids[i]){
                   ids[i]="";
                   names[i]="";
               }
            }
            var cid="";
            var cname="";
            for(var i in ids){
               if(ids[i]!=""){
                   cid = cid+ids[i]+',';
                   cname = cname+names[i]+',';
               }
            }
            CCnames = cname;
            CCids = cid;
            $("#clCcr").val(CCnames);
           $("#clCcid").val(CCids);
        }
        function print(){
	    	 var html= $("#form1").html();
           var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
                        var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
                        LODOP.PRINT_INIT("打印材料费用报销单");
                        LODOP.ADD_PRINT_HTM('90px', '80px', "770px", "520px", strBodyStyle + html.replace($("#bx").html(),""));
                        //LODOP.SET_PRINT_PAGESIZE (2, 1760, 2500,"");
                        LODOP.SET_PRINT_PAGESIZE (2, 0, 0,"B5(ISO)");
                        LODOP.PRINT();
                  //      LODOP.PREVIEW();
        }
        
        
        function submitsh() {
            var id = $("#id").val();
            top.$.dialog({
                width: 600,
                height: 250,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "审核",
                content: 'url:app/finance/pxfbxd_yijian_detail.jsp?id=' + id + '&pageStatus=add'
            });
        }
        
   
        
        function directChange(){ 
//        	var repayment_man_id=$("#repayment_man_id").val();
//            var repayment_man=$("#repayment_man").val();
//            var repayment_time=$("#repayment_time").val();
           var id="${param.id}";

    			 $.ligerDialog.confirm('是否确认已报销？', function (yes){
    	        	if(!yes){return false;}
    	            top.$.ajax({
    	                 url: "finance/pxfbxd/bxsubmit.do?id="+id+"&check=1",//+"&repayment_man_id="+repayment_man_id+
    	                		 //"&repayment_time="+repayment_time,
    	                 type: "POST",
    	                 dataType:'json',
    	                // data:"&repayment_man="+repayment_man,
    	                 async: false,
    	                 success:function (data) {
//     	                	 if("还款已提交"==up_status){
//     	                		 $.ligerDialog.warn("此条还款已提交！");
//     	                	 }else{
    		                	 if(data.success){
    		                		 top.$.notice('确认已报销成功！',3);
    		                		api.close();
    		                		api.data.window.Qm.refreshGrid();//刷新
    		                     }else{
    		                         $.ligerDialog.warn(data.msg);
    		                     }
//    	                	 }
    	                 },
    	                 error:function () {
    	                     $.ligerDialog.warn("确认已报销失败！");
    	                 }
    	             });
    	        });

//    			 $("#form1").submit();
//    		 }else{
//    			 return;
//    		}
    	}
    	function undirectChange(){ 
//        	var repayment_man_id=$("#repayment_man_id").val();
//            var repayment_man=$("#repayment_man").val();
//            var repayment_time=$("#repayment_time").val();
           var id="${param.id}";

    			 $.ligerDialog.confirm('是否取消已报销？', function (yes){
    	        	if(!yes){return false;}
    	            top.$.ajax({
    	                 url: "finance/pxfbxd/bxsubmit.do?id="+id+"&check=0",//+"&repayment_man_id="+repayment_man_id+
    	                		 //"&repayment_time="+repayment_time,
    	                 type: "POST",
    	                 dataType:'json',
    	                // data:"&repayment_man="+repayment_man,
    	                 async: false,
    	                 success:function (data) {
//     	                	 if("还款已提交"==up_status){
//     	                		 $.ligerDialog.warn("此条还款已提交！");
//     	                	 }else{
    		                	 if(data.success){
    		                		 top.$.notice('取消已报销成功！',3);
    		                		api.close();
    		                		api.data.window.Qm.refreshGrid();//刷新
    		                     }else{
    		                         $.ligerDialog.warn(data.msg);
    		                     }
//    	                	 }
    	                 },
    	                 error:function () {
    	                     $.ligerDialog.warn("取消已报销失败！");
    	                 }
    	             });
    	        });

//    			 $("#form1").submit();
//    		 }else{
//    			 return;
//    		}
    	}    
       
    </script>
    <style>
    div .input{border-bottom:0px;}
    .l-detail-table1 td, .l-detail-table1, .l-table td1, .l-table1 {
    border-collapse: collapse;
    border: 1px solid #d2e0f1;
}

h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}
    </style>
   <style type="text/css" media="print" id="pstyle">
* {
    font-family:"宋体";
    font-size:14px;
    letter-spacing:normal;
    
}
table{ margin:0 auto;}
table td{ height:30px;}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
.l-detail-table {
    padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
.check {
    width:770px;
}
.l-t-td-left{ text-align:center;}
.l-t-td-right{ padding-left:5px;}
.fybx2{   height:40px; line-height:20px; overflow: hidden;}

h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}

</style>
</head>
<body style="background:white;">
<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<form id="form1" action="finance/pxfbxd/save.do" getAction="finance/pxfbxd/detail.do?id=${param.id}">
    <input type="hidden"value="${param.id}" id="id" name="id">
    <input type="hidden" value="<%=userid %>" id="userId" name="userId"><!-- 报销人id -->
    <input type="hidden" value="<%=user %>" id="user" name="user" />
    <input type="hidden"  id="statue" name="statue">
    <input type="hidden" value="" id="departmentId" name="departmentId">
    <input type="hidden" value="" id="carApplyIds" name="carApplyIds">
    
            <h2>培&nbsp;训&nbsp;费/会&nbsp;议&nbsp;费&nbsp;报&nbsp;销&nbsp;单</h2>
            <div style="text-float: right;text-align: right;" id="bx">
            <a href="javascript:void(0);"  onclick="openFile1()"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 20px;color: #FF0000" >开具发票说明 </a>
            
			<a href="javascript:void(0);" onclick="openFile()"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 20px;" >报销制度</a>
			</div>
        <table class="check">
             <tr>
                    <td width="160px">审核日期:</td>
                    <td width="160px" class="l-t-td-right">
                    <input ltype='text' name="handleTime"  id="handleTime" readonly="readonly"/></td>
                    <td width="380px">&nbsp;</td>
                    <td width="80px" align="center">编号:</td>
                    <td  style="width: 130px;"   class="l-t-td-right"><input ltype='text'   name="identifier" type="text" id="identifier" readonly="readonly"/></td>
                    <td width="280px">&nbsp;</td>
                    <td width="80px" align="center">附件:</td>
                    <td width="60" class="l-t-td-right"><input onkeydown="NonNegative(this)" onkeydown="onlyNonNegative(this)"  validate="{required:false,maxlength:10}"ltype='text'  name="accessory" type="text" id="Recipients" /></td>
                    <td width="20">张</td>
                     </td>
                    
                   
                </tr>
            </table>
            <table  class="l-detail-table has-head-image" style="margin:0px;padding:0px" width="770px">
            <tr>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            <th style="border:0px;width:30px"></th>
            
            </tr>
              <tr>
                <td class="l-t-td-left" colspan="3" style="width:120px">单位</td>
                <td class="l-t-td-right" colspan="8" style="width:240px"><u:combo  attribute="initValue:'tjy'" validate="required:true"  name="clDw" code="TJY2_UNIT" /></td>
                <td class="l-t-td-left" colspan="3" style="width:120px">部门</td>
                <td colspan="5" class="l-t-td-right" style="width:160px"><input readonly="readonly" value="<%=useres.getDepartment().getOrgName() %>"   validate="{maxlength:50,required:true}" ltype="text"  name="department" id="department"  type="text" onclick="chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
                <td class="l-t-td-left" colspan="3" style="width:120px">报销日期</td>
                <td class="l-t-td-right" colspan="3" style="width:160px">
  <input  name="clSj" type="text" ltype="date" validate="{required:true}" 
                    ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="clSj"  
                    value="<%=nowTime%>" />               
                </td>
              </tr>
              <tr>
                <td class="l-t-td-left" colspan="3">培训项目</td>
                <td class="l-t-td-right" colspan="8">
                  <input  validate="{required:true,maxlength:200}" ltype='text' name="clSy" type="text" id="Remark" />
                </td>
                <td class="l-t-td-left" colspan="3">培训人</td>
                 <td colspan="11" class="l-t-td-right" id="reader_td">
                 <input name="clCcr" id="clCcr" type="hidden" />
                 <input name="clCcid" id="clCcid" type="hidden" />
                            <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加接收人">
                                    <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
                                   </c:if>
                       </td>
              </tr>
              <tr>
                <td class="l-t-td-left" colspan="3">派车单</td>
                <td class="l-t-td-right" colspan="22">
                <input validate="{maxlength:50}" ltype="text" id="carApplyNumbers"
						name="carApplyNumbers" type="text" readonly="readonly"
						onclick="selectCarNo()"
						ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCarNo()}}]}" />
                </td>
              </tr>
              
              <tr>
                <td class="l-t-td-left" colspan="4">起点</td>
                <td class="l-t-td-left" colspan="4">终点</td>
                <td class="l-t-td-left" colspan="3">交通费</td>
                <td class="l-t-td-left" colspan="3">住宿费</td>
                <td class="l-t-td-left" colspan="3">伙食补助</td>
                <td class="l-t-td-left" colspan="3">公杂费</td>
                <td class="l-t-td-left" colspan="3">培训费/会议费</td>
                <td class="l-t-td-left" colspan="2" rowspan="2">金额小计</td>
              </tr>
              <tr>
                
                <td class="l-t-td-left" colspan="2" width="6%">时间</td>
                <td class="l-t-td-left" colspan="2">地名</td>
                <td class="l-t-td-left" colspan="2" width="6%">时间</td>
                <td class="l-t-td-left" colspan="2">地名</td>
                <td class="l-t-td-left">单据<br/>(张)</td>
                <td class="l-t-td-left" colspan="2">金额</td>
                <td class="l-t-td-left">单据<br/>(张)</td>
                <td class="l-t-td-left" colspan="2">金额</td>
                <td class="l-t-td-left">天数</td>
                <td class="l-t-td-left" colspan="2">金额</td>
                <td class="l-t-td-left">天数</td>
                <td class="l-t-td-left" colspan="2">金额</td>
                <td class="l-t-td-left">单据<br/>(张)</td>
                <td class="l-t-td-left" colspan="2">金额</td>
              </tr>
              <tr id="data_tr1">
                <td class="l-t-td-right" colspan="2"><input  validate="{maxlength:32}"  name="clQdR1"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/></td>
                <td  class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clQdDm1"type="text" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}"  name="clZdR1" type="text" ltype="date" validate="{required:false}" ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/>
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text'   name="clZdDd1" type="text"  />
                </td>
                <td class="l-t-td-right">
                    <input  validate="{maxlength:32}" ltype='text' name="clJtfDj1" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clJtfDj1" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input onkeydown="onlyNonNegative(this)" validate="{maxlength:32}" ltype='text' name="clJtfJe1" onkeyup="SunAmount();" type="text" id="clJtfJe1" class="underlineinput" />
                </td>
                <td class="l-t-td-right">
                    <input  validate="{maxlength:32}" ltype='text' name="clZsfDj1" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfDj1" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clZsfJe1" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfJe1" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input  validate="{maxlength:32}" ltype='text' name="clHsbzTs1" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzTs1" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clHsbzJe1" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzJe1" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input  validate="{maxlength:32}" ltype='text' name="clGzfTs1" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfTs1" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clGzfJe1" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfJe1" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input  validate="{maxlength:32}" ltype='text' name="clQtDj1" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtDj1" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clQtJe1" onkeydown="onlyNonNegative(this)" type="text" onkeyup="SunAmount();" id="clQtJe1" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input  validate="{maxlength:32}" ltype='text' name="clJexj1" onfocus="this.blur()" type="text" onkeydown="SunAmount();" id="clJexj1" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" />
                </td>
              </tr>
              <tr id="data_tr2">
                <td class="l-t-td-right" colspan="2"><input  name="clQdR2"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/></td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clQdDm2"type="text" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input  name="clZdR2"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/>
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text'   name="clZdDd2" type="text"  />
                </td>
                <td class="l-t-td-right">
                    <input ltype='text' name="clJtfDj2" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clJtfDj2" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clJtfJe2" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clJtfJe2" class="underlineinput" />
                </td>
                <td class="l-t-td-right">
                    <input ltype='text' name="clZsfDj2" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfDj2" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clZsfJe2" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfJe2" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input ltype='text' name="clHsbzTs2" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzTs2" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clHsbzJe2" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzJe2" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input ltype='text' name="clGzfTs2" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfTs2" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clGzfJe2" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfJe2" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                    <input ltype='text' name="clQtDj2" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtDj2" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clQtJe2" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtJe2" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                    <input ltype='text' name="clJexj2" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clJexj2" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" />
                </td>
              </tr>
              <tr id="data_tr3">
                <td class="l-t-td-right" colspan="2"><input  name="clQdR3"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/></td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text' name="clQdDm3"type="text" />
                </td>
                <td class="l-t-td-right" colspan="2">
                <input  name="clZdR3"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/>
                </td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text'   name="clZdDd3" type="text"  />
                </td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clJtfDj3" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clJtfDj3" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' onkeyup="SunAmount();" name="clJtfJe3" onkeydown="onlyNonNegative(this)" type="text" id="clJtfJe3" class="underlineinput" />
                </td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clZsfDj3" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfDj3" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clZsfJe3" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfJe3" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHsbzTs3" onkeydown="NonNegative(this)" onkeyup="SunAmount();"  type="text" id="clHsbzTs3" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  name="clHsbzJe3" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzJe3" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clGzfTs3" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfTs3" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clGzfJe3" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfJe3" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clQtDj3" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtDj3" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clQtJe3" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtJe3" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clJexj3" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clJexj3" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" />
                </td>
              </tr>
              <tr id="data_tr4">
                <td class="l-t-td-right" colspan="2"><input  name="clQdR4"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/></td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text' name="clQdDm4"type="text" />
                </td>
                <td class="l-t-td-right" colspan="2">
                 <input  name="clZdR4"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/>
                </td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text'   name="clZdDd4" type="text"  />
                </td>
                <td class="l-t-td-right">
                  <input ltype='text' onkeyup="SunAmount();" name="clJtfDj4" onkeydown="NonNegative(this)" type="text" id="clJtfDj4" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' onkeyup="SunAmount();" name="clJtfJe4" onkeydown="onlyNonNegative(this)" type="text" id="clJtfJe4" class="underlineinput" />
                </td>
                <td class="l-t-td-right"> 
                  <input ltype='text' name="clZsfDj4" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfDj4" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clZsfJe4" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfJe4" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHsbzTs4" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzTs4" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clHsbzJe4" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzJe4" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clGzfTs4" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfTs4" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clGzfJe4" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfJe4" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clQtDj4" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtDj4" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clQtJe4" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtJe4" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clJexj4" type="text" onfocus="this.blur()" onkeydown="SunAmount();" id="clJexj4" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" />
                </td>
              </tr>
              <tr id="data_tr5">
                <td class="l-t-td-right" colspan="2">
                	<input  name="clQdR5"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/></td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text' name="clQdDm5"type="text" />
                </td>
                <td class="l-t-td-right" colspan="2">
                 <input  name="clZdR5"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'MM-dd'}" id="advance_time"/>
                </td>
                <td class="l-t-td-right" colspan="2">
                 <input ltype='text'   name="clZdDd5" type="text"  />
                </td>
                <td class="l-t-td-right">
                  <input ltype='text' onkeyup="SunAmount();" name="clJtfDj5" onkeydown="NonNegative(this)" type="text" id="clJtfDj5" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' onkeyup="SunAmount();" name="clJtfJe5" onkeydown="onlyNonNegative(this)" type="text" id="clJtfJe5" class="underlineinput" />
                </td>
                <td class="l-t-td-right"> 
                  <input ltype='text' name="clZsfDj5" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfDj5" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clZsfJe5" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clZsfJe5" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHsbzTs5" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzTs5" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clHsbzJe5" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clHsbzJe5" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clGzfTs5" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfTs5" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clGzfJe5" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clGzfJe5" title="只能输入数字" class="underlineinput" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clQtDj5" onkeydown="NonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtDj5" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clQtJe5" onkeydown="onlyNonNegative(this)" onkeyup="SunAmount();" type="text" id="clQtJe5" title="只能输入数字" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clJexj5" type="text" onfocus="this.blur()" onkeydown="SunAmount();" id="clJexj5" title="只能输入数字" class="underlineinput" onkeyup="sumAmount();" />
                </td>
              </tr>
              <tr>
                <td class="l-t-td-left" colspan="8" align="center">小计</td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHjJtfDj" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjJtfDj" class="underlineinput" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  name="clHjJtfJe" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjJtfJe" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right">
                  <input class="l-t-td-right" ltype='text' onfocus="this.blur()" onkeydown="SunAmount();" name="clHjZsfDj" type="text" id="clHjZsfDj" class="underlineinput" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clHjZsfJe" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjZsfJe" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHjHsbzTs" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjHsbzTs" class="underlineinput" /></td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clHjHsbzJe" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjHsbzJe" class="underlineinput" onkeyup="sumAmount();" /></td>
                <td class="l-t-td-right">
                  <input ltype='text' name="clHjGzfTs" onfocus="this.blur()" onkeydown="SunAmount();" type="text" id="clHjGzfTs" class="underlineinput" />
                  </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' name="clHjGzfJe" onfocus="this.blur()"onkeydown="SunAmount();" type="text" id="clHjGzfJe" class="underlineinput" onkeyup="sumAmount();" />
                  </td>
                <td class="l-t-td-right">
                  <input  ltype='text' name="clHjQtDj" onfocus="this.blur()" onkeyup="sumAmount();" onkeydown="SunAmount();" type="text" id="clHjQtDj" class="underlineinput" />
                  </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text' onfocus="this.blur()" onkeydown="SunAmount();" name="clHjQtJe" type="text" id="clHjQtJe" class="underlineinput" onkeyup="sumAmount();" />
                  </td>
                <td class="l-t-td-right" colspan="2">
                  <input onfocus="this.blur()"  ltype='text'  onkeydown="SunAmount();"   type="text"  class="underlineinput" />
                  </td>
              </tr>
               <tr >
                <td class="l-t-td-left"  colspan="8" align="center">合计</td>
                <td colspan="12" class="l-t-td-right">
                  <input  ltype='text'  onfocus="this.blur()" onkeydown="SunAmount();"  type="text" name="clXj1" id="clXj1" class="underlineinput" /></td>
                  </td>
                <td class="l-t-td-right" colspan="3">
                  <input ltype='text' onfocus="this.blur()"  name="xiaoji1" id="xiaoji1" onkeydown="SunAmount();"  type="text"  class="underlineinput" onkeyup="sumAmount();" />
                  </td>
                <td class="l-t-td-right" colspan="2">
                   <input onfocus="this.blur()"  ltype='text'  onkeydown="SunAmount();"   type="text"  class="underlineinput" />
                  </td>
              </tr>
              
               
              
              <tr>
                <td class="l-t-td-left" rowspan="2" colspan="4">合计人民币大写 </td>
                <td  class="l-t-td-right" colspan="7" rowspan="2"><span><input  readonly="readonly"  name="clHjRmbdx" ltype='text' id="clHjRmbdx" value="" /></span></td>
                <td class="l-t-td-left" rowspan="2" colspan="3">金额合计</td>
                <td class="l-t-td-right" colspan="3" rowspan="2">
                  <input onfocus="this.blur()"  ltype='text'   onkeydown="SunAmount();" name="clHjJexj"  type="text" id="clHjJexj" class="underlineinput" />
                </td>
                <td class="l-t-td-left" colspan="2">现金</td>
                <td class="l-t-td-left" colspan="2">公务卡</td>
                <td class="l-t-td-left" colspan="2">转账</td>
                <td class="l-t-td-left" colspan="2">冲账</td>
              </tr>
             <tr>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  validate="{maxlength:32}" name="clXj" type="text" id="Cash" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  validate="{maxlength:32}" name="clGwk" type="text" id="BusinessCard" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  validate="{maxlength:32}" name="clZz" type="text" id="Transfer" class="underlineinput" />
                </td>
                <td class="l-t-td-right" colspan="2">
                  <input ltype='text'  validate="{maxlength:32}" name="clCz" type="text" id="clCz" class="underlineinput" />
                </td>
              </tr>
            </table>
            
      <table class="check">
        <tr>
            <td>单位负责人：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
            <td><div id="fgld">分管领导：</div></td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
            <td>部门负责人：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
            <td>财务审核：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
            <td>报销人：</td>
            <td class="l-td-opinion"></td>
        </tr>
    </table>
</form>
</body>
</html>
