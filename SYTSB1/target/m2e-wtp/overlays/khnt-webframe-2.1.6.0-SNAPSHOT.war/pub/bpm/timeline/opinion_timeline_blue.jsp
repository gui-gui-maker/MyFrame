<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/k/kui-base.jsp"%>
<link rel="stylesheet" type="text/css" href="pub/bpm/timeline/style/default.css">
<link rel="stylesheet" type="text/css" href="pub/bpm/timeline/style/component.css">
<link rel="stylesheet" type="text/css" href="k/kui/skins/ligerui-icons.css">
<link rel="stylesheet" type="text/css" href="pub/eseal/bc/context.standalone.css" />
<script src="pub/bpm/timeline/js/modernizr.custom.js"></script>
<script type="text/javascript" src="pub/eseal/bc/seal.js"></script>
<script type="text/javascript" src="pub/eseal/bc/context.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	$(function(){
		//电脑端
		var isShowSeal = (null=="${param.isSeal}"||""=="${param.isSeal}"||"${param.isSeal}"==undefined)?false:true;
		$.getJSON("bpm/opinion/serviceOpinion.do?new="+Math.random(),{serviceId:"${param.serviceId}"},function(res){
			if(res.success){
				if(res.data.length > 0){
					var rmap = {"0":"不同意","1":"同意","-1":"退回"};
					for (var temp in res.data) {
						var idea = "<h2 class='cbp_h2a'>" + res.data[temp].activityName + "</h2>" +
								"<div class='cbp_d1'>" + res.data[temp].signerName + "</div>" +
								"<div class='cbp_d2'>" + (rmap[res.data[temp].result] || "") + "</div>" +
								"<div class='cbp_d3'>" + (res.data[temp].opinion || "") + "</div>";
						var isShowSeal = false;
						if (isShowSeal) {
							if (res.data[temp].opinionSignature != "" && res.data[temp].opinionSignature != null && res.data[temp].opinionSignature != undefined) {
								idea += "<div style='width:100px;height:164px;' id='opinionSignature" + temp + "'></div>";
								showSeal(res.data[temp].opinionSignature, "opinionSignature" + temp, 1);
								isShowSeal = true;
							}
							idea += "<div class='cbp_d4'>";
							if (res.data[temp].signature != "" && res.data[temp].signature != null && res.data[temp].signature != undefined) {
								idea += "<div style='width:210px;height:120px;overflow:visible;display:inline-block;float:left;' id='signature" + temp + "'></div>";
								showSeal(res.data[temp].signature, "signature" + temp, 1);
								isShowSeal = true;
							}
							if (res.data[temp].seal != "" && res.data[temp].seal != null && res.data[temp].seal != undefined) {
								idea += "<div style='width:165px;height:165px;overflow:visible;display:inline-block;float:left;' id='seal" + temp + "'></div>";
								showSeal(res.data[temp].seal, "seal" + temp, 0);
								isShowSeal = true;
							}
							idea += "</div>";
						}
						if (res.data[temp].atts!=null && res.data[temp].atts.length>0) {
							idea += "<div class='cbp_d6' style='padding-top:10px;'><ul id='attview'></ul></div>";
						}
						var content = idea;
						var he1 = isShowSeal ? "isShowSeal" : "";
						var html = "<li><time class='cbp_tmtime' ><span>" + res.data[temp].signDate.substring(0, 10) + "</span> <span>" + res.data[temp].signDate.substring(11) 
								 + "</span> </time><div class='cbp_tmicon cbp_tmicon-screen'></div><div class='cbp_tmlabel " + he1 + "'>" + content + "</div></li>";
						$("#cd-timeline").append(html);
						$.each(res.data[temp].atts,function(){
							createFileViewItem({
						        fid:this.attId,fname:this.attName,edit:false,ctrId:'attview',preview:true
						    });
						});
					}
				}else{
					$("#cd-timeline").remove();
					$("body").append("<center>没有数据</center>")
				}
			}
			var h1=$(document).height();
			parent.$("#_frame").height(h1);
		});



	})
	function showSeal(obj,position,isseal){
		$.post("pub/signseal/getListByIds.do",{Ids:obj},function(sign){
	         if(sign.success){
	             for(var i in sign.data){
		             __showSingleSeal(sign.data[i],position,"verify");
	             }
	         }
	    },"json");
	}
</script>
<style type="text/css">
.tips-content > div {width:54px;height:26px;}
.upload-fname-tips{height:74px;}
.upload-file-list{height:60px;float:none;}
.upload-file-list li.preview{height:54px!important;}
</style>
</head>
<body>
<div class="container">
	<div class="main">
		<ul class="cbp_tmtimeline" id='cd-timeline'></ul>
	</div>
</div>
</body>
</html>