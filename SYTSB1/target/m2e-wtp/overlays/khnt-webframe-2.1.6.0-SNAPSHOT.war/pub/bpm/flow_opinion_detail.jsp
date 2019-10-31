<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="modify">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/eseal/bc/seal.js"></script>
<script type="text/javascript" src="pub/eseal/bc/context.js"></script>
<link rel="stylesheet" type="text/css" href="pub/eseal/bc/context.standalone.css" />
<sec:authentication property="principal.id" var="userId"/>
<script type="text/javascript">
	// 意见控制选项
	var _page_opts = $.extend({
		noSeal: false,
		noSignature: false,
		noReturnSeal: false,
		noReturnSignature: false
	},api.data);
	var pageRespData={};
	$(function() {
		$("#formObj").initForm({
			success : function(response) {
				if (response.success) {
					$("#oid").val(response.data.id);
					if (api.data.callback) {
						api.data.callback($("#formObj").getValues());
					}else{
					    top.$.notice("意见签署成功！", 3);
					}
					api.close();
				} else {
					var msg = "意见签失败!";
					if($.kh.isNull(response.msg)){
						$.ligerDialog.error(msg);
					}else{
						$.ligerDialog.error(msg+response.msg);
					}
				}
			},
			getSuccess: function(response){
				pageRespData = response;
			},
			toolbar: [
		        {text: "签署意见", icon: "myaccount", id: "signOpinionBtn", click: addOpinionSign},//邵林 2017年08月01日
			    {text: "签名", icon: "myaccount", id: "signBtn", click: addSign},
		        {text: "盖章", icon: "seal", id: "sealBtn", click: addSeal},
                {text: "提交", icon: "right", id: "app_ok", click: function(){
                	submitOpinion(1);
                }},
                {text: "终止", icon: "forbid", id: "app_rej", click: function(){
                	submitOpinion(0);
                }},
                {text: "退回", icon: "return", id: "app_back", click: function(){
                	submitOpinion(-1);
                }},
                {text: "提交", icon: "submit", id: "submit", click: function(){
                	submitOpinion(9);
                }},
                {text: "关闭", icon: "close", id: "close", click: function(){api.close();}}
		    ],
		    afterParse: function(){
		    	$("body").mask("正在加载，请稍候...");
		    	$.getJSON("bpm/common_opinion/user_opinions.do",function(resp){
		    		if(!resp.success)return;
					var ucolist = [{id:"init",text:"— — — — 使用常用意见  — — — —"}].concat(resp.data);
			    	var clmgr = $("#colist-txt").ligerGetComboBoxManager();
			    	clmgr.setData(ucolist);
			    	clmgr.selectValue("init");

					if(resp["defaultOpinion"]){
						$("#opinion").val(resp["defaultOpinion"]);
					}
		    	});
		    	setTimeout(pageInit,200);
		    }
		});
	});
	
	function pageInit(){
        var funcstr = $.kh.isNull(pageRespData.activity)||$.kh.isNull(pageRespData.activity['function'])?"":pageRespData.activity['function'];
        initPageOpts(funcstr);
        initPageView();
        initUpload();
        if(pageRespData.data){
	        if(pageRespData.data.signature){
        		if(!_page_opts.multiSignature){
	        		showSingleSeal(pageRespData.data.signature,"signature_area",null);
		        	setTimeout(function(){
        	        	$("#signBtn").ligerGetButtonManager().setDisabled();
		        	},200);
        		}
            }
	        if(pageRespData.data.seal){
        	    showSingleSeal(pageRespData.data.seal,"seal_area",null,function(){
        	    	$("#sealBtn").ligerGetButtonManager().setEnabled();
        	    });
	        	setTimeout(function(){
        	    	//$("#sealBtn").ligerGetButtonManager().setDisabled();
	        	},200);
            }
	        if(pageRespData.data.opinionSignature){
        	    showSingleSeal(pageRespData.data.opinionSignature,"opinion_signature_area",null);
	        	setTimeout(function(){
        	   		$("#signOpinionBtn").ligerGetButtonManager().setDisabled();
	        	},200);
	        }
        }
        $("body").unmask();
    }

	//页面选项初始化
	function initPageOpts(funcstr){
		// 是否审批
		if(funcstr.indexOf("pub_wf_approve")>=0 && $.kh.isNull(_page_opts['isApp'])){
			_page_opts['isApp'] = true;
		}else{
			_page_opts['isApp'] = false;
		}
		if($.kh.isNull(_page_opts['signature'])){
			if(funcstr.indexOf("pub_signature")>=0)
				_page_opts.signature = true;
			if(funcstr.indexOf("pub_multi_signature")>=0)
				_page_opts.multiSignature = true;
		}else{
			if(_page_opts['signature']=="2")
				_page_opts.multiSignature = true;
			else
				_page_opts.signature = true;
		}
		if(funcstr.indexOf("opinion_signature")>=0 && $.kh.isNull(_page_opts['opinion_signature'])){
			_page_opts.opinionSignature = true;
		}else{
			_page_opts.opinionSignature = false;
		}
		if(funcstr.indexOf("pub_seal")>=0 && $.kh.isNull(_page_opts['seal'])){
			_page_opts.seal = true;
		}else{
			_page_opts.seal = false;
		}
		if(funcstr.indexOf("pub_terminal")>=0 && $.kh.isNull(_page_opts['terminal'])){
			_page_opts.terminal = true;
		}
		if($.kh.isNull(_page_opts['noReturn'])){
			_page_opts.noReturn = funcstr.indexOf("pub_wf_turnback")==-1 && funcstr.indexOf("turnback")==-1;
		}
	}

    function initUpload(){
    	$("#upload-ctr").khUpload({
            fileSize: "100mb",
            fileNum: -1,
            useThirdDevice: false,
            extName : "doc,docx,rar,zip,png,jpg,gif,xls,xlsx,ppt,pptx,7z,pdf,epub,txt",
            businessId: $("#oid").val()
    	});
    }

	function initPageView(){
		// 非审批模式，只有提交按钮
		if(_page_opts.isApp){
			$("#submit").remove();
		}
	    else{// 审批模式，去掉提交按钮，出现同意、不同意按钮
			$("#app_ok").remove();
	    }
		// 退回按钮
		if(_page_opts.noReturn){
			$("#app_back").remove();
        }
		// 非审批并且没有终止选项，终止按钮隐藏
		if(!_page_opts.isApp && !_page_opts.terminal){
			$("#app_rej").remove();
        }
		if(_page_opts.seal){
	        $("#add_seal_tr").show();
		}else{
	        $("#sealBtn").remove();
		}
		if(_page_opts.signature || _page_opts.multiSignature){
	        $("#add_sign_tr").show();
		}else{
	        $("#signBtn").remove();
		}
		if(_page_opts.opinionSignature){
			$("#add_opinion_sign_tr").show();
		}else{
			$("#signOpinionBtn").remove();
		}
	}
	
    function submitOpinion(rst){
    	$("#result").val(rst);
    	if(rst != 9 && ($.kh.isNull(kui.needSealSign)||kui.needSealSign=='1')){
            if(_page_opts.seal && $("#seal").val()==""){
            	if(rst == 1 || (_page_opts.noSeal && rst == 0) || (_page_opts.noReturnSeal && rst == -1)){
	                $.ligerDialog.warn("请先盖章!");
	                return;
            	}
            }
            if(_page_opts.signature && $("#signature").val()==""){
            	if(rst == 1 || (_page_opts.noSignature && rst == 0) || (_page_opts.noReturnSignature && rst == -1)){
	                $.ligerDialog.warn("请先签字!");
	                return;
            	}
            }
    	}
    	var fileIdNames = $("#upload-ctr").khUpload().getUploadFileIdNames();
    	$("#attachments").val(fileIdNames.id);
        $("#formObj").submit();
    }
	
	function addSign(){
		if($("#opinion").val()==""){
			$.ligerDialog.alert("请先填写意见！");
	    	return;
		}
	    var signId = $("#signature").val();
	    var signNum = signId?(signId.replace(/,$|^,/gi,"").split(",").length):0;
	    configSetting(4,function(){
	    	return $("#opinion").val();
	    },"${param.serviceId}","",function(data){
	        $("#signature").val((signId==""?"":signId + ",") + data.id);
	        $("#signBtn").ligerGetButtonManager().setDisabled();
	    	$("body").unmask();//取消遮罩
	    },delSignature);
	    $("body").mask("正在处理，请稍后...");//添加遮罩层，避免数据未返回便作其它操作
	    window.setTimeout(function(){
	    	$("body").unmask();
	    },2000);
	    $("#signature_area").height("auto");
	    InsertSeal("signature_area",false,160,160,signNum * 180 + 10,10);
	} 

	function addOpinionSign(){
		if($("#opinion").val()==""){
			$.ligerDialog.alert("请先填写意见！");
	    	return;
		}
	    var signId = $("#opinion_signature").val();
	    var signNum = signId?(signId.replace(/,$|^,/gi,"").split(",").length):0;
	    configSetting(4,function(){
	    	return $("#opinion").val();
	    },"${param.serviceId}","",function(data){
	        $("#opinion_signature").val((signId==""?"":signId + ",") + data.id);
	        if(!_page_opts.multiSignature)
	        	$("#signOpinionBtn").ligerGetButtonManager().setDisabled();
	    	$("body").unmask();//取消遮罩
	    },delSignature);
	    $("body").mask("正在处理，请稍后...");//添加遮罩层，避免数据未返回便作其它操作
	    window.setTimeout(function(){
	    	$("body").unmask();
	    },2000);
	    $("#opinion_signature_area").height("auto");
	    InsertSeal("opinion_signature_area",false);
	}
	
	function delSignature(dataId){
		if($("#signature").val().indexOf(dataId) >=0){
	        $("#signBtn").ligerGetButtonManager().setEnabled();
	        $("#signature").val($("#signature").val().replace(dataId,"").replace(/,+/gi,",").replace(/,$|^,/gi,""));
		}
		else if($("#opinion_signature").val().indexOf(dataId) >= 0){
	        $("#signOpinionBtn").ligerGetButtonManager().setEnabled();
	        $("#opinion_signature").val($("#opinion_signature").val().replace(dataId,"").replace(/,+/gi,",").replace(/,$|^,/gi,""));
		}
	}
	
	function addSeal(){
		if($("#opinion").val()==""){
			$.ligerDialog.alert("请先填写意见！");
	    	return;
		}
	    configSetting(4,$("#opinion").val(),"${param.serviceId}","",function(data){
        	$("#seal").val(data.id);
        	$("#sealBtn").ligerGetButtonManager().setDisabled();
    		$("body").unmask();//取消遮罩
	    },function(id,proData){
	    	alert(id);
	        $("#sealBtn").ligerGetButtonManager().setEnabled();
	    });
	    $("body").mask("正在处理，请稍后...");//添加遮罩层，避免数据未返回便作其它操作
	    window.setTimeout(function(){
	    	$("body").unmask();
	    },2000);
	    $("#seal_area").height("auto");
	    InsertSeal("seal_area",true,160,160,10,10);
	}

	//显示印章或签名
	function showSeal(Ids,sealStationId){
	    $.post("pub/signseal/getListByIds.do",{Ids:Ids},function(sign){
	         if(sign.success){
	             //显示印章数据
	             var signData=sign.data;
	             for(var i in signData){
	                 showSingleSeal(sealStationId,signData[i],i * 180 + 10,"10",0);
	             }
	         }
	    },"json");
	}
	
	function chooseCommonOpinion(theid,thetxt){
		if(theid!="init"){
			$("#opinion").val($("#opinion").val() + thetxt);
    		this.selectValue("init");
		}
	}
	
	function editOpintion(){
		winOpen({
			title: "个人常用意见管理",
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			content: "url:pub/bpm/common_opinion_list.jsp",
			close: afterCommonOpinion,
			ok: afterCommonOpinion
		});
	}
	
	function afterCommonOpinion(){
		var allcos = this.iframe.contentWindow.getAllData();
		allcos = [{id:"init",text:"— — — — 使用常用意见  — — — —"}].concat(allcos);
		$("#colist-txt").ligerGetComboBoxManager().setData(allcos);
		$.each(allcos,function(){
			if(this.def=="1"){
				$("#opinion").val(this.text);
			}
		});
	}
</script>
<style type="text/css">
.scroll-tm,form{background:#f0f0f0;}
.input-item{padding:0;margin:0;}
.input-item .tips{height:10mm;font-size:4mm;line-height:10mm;padding-left:1em;}
.input-item .content{background:#ffffff;}
.content .l-text,.content .l-text-focus,.content .l-text-over{border:none!important;background:none;box-shadow:none;}
.common-opinion{
	height: 28px;
	width: 100%;
	background: white;
}
</style>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="bpm/opinion/save.do" 
	   getAction="bpm/opinion/signOpinion.do?sid=${param.serviceId}&aid=${param.activityId}&item=${param.workitem}">
		<input name="id" type="hidden" id="oid" /> 
		<input name="activityId" type="hidden" value="${param.activityId}" /> 
		<input name="workitem" type="hidden" value="${param.workitem}" /> 
		<input name="serviceId" type="hidden" value="${param.serviceId}" />
        <input name="attachment" type="hidden" value="" id="attachments" />
        <input name="result" type="hidden" value="" id="result" />
        <input type="hidden" name="opinionSignature" id="opinion_signature" />
		<div class="input-item">
			<div class="tips">录入意见</div>
			<div class="common-opinion">
				<u:combo name="colist" data="[]" attribute="emptyOption:false,onSelected:chooseCommonOpinion,iconItems:[{icon:'edit',click:editOpintion}]" />
			</div>
			<div class="content">
				<textarea name="opinion" id="opinion"
					style="font-size:5mm;line-height:1.3em;height:5em;width:100%;border:none !important;"
					validate="{maxlength:255}" placeholder="请填写您的意见"></textarea>
			</div>
		</div>
		<div class="input-item" style="display:none;" id="add_opinion_sign_tr">
			<div class="tips">手写意见</div>
			<div id="opinion_signature_area" class="content" style="height:100px;min-height:100px;"></div>
		</div>
		<div class="input-item" style="display:none;" id="add_sign_tr">
			<div class="tips">签名<input type="hidden" name="signature" id="signature" /></div>
			<div id="signature_area" class="content" style="position:relative;height:100px;min-height:100px;"></div>
		</div>
        <div class="input-item" style="display:none;" id="add_seal_tr">
			<div class="tips">盖章<input type="hidden" name="seal" id="seal" /></div>
			<div id="seal_area" class="content" style="position:relative;height:180px;min-height:180px;"></div>
		</div>
		<div class="input-item">
			<div class="tips">附件</div>
			<div class="content" id="upload-ctr" style="padding:4mm;height:56px;"></div>
		</div>
	</form>
</body>
</html>