<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="pub/bpm/timeline/style/style.css" />
<script type="text/javascript" src="k/kui/frame/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="pub/eseal/bc/seal.js"></script>
<script type="text/javascript">
	$(function(){
		var isShowSeal = (null=="${param.isSeal}"||""=="${param.isSeal}"||"${param.isSeal}"==undefined)?false:true;
		$.getJSON("bpm/opinion/serviceOpinion.do",{serviceId:"${param.serviceId}"},function(res){
			if(res.success){
				if(res.data.length > 0){
					for(var temp in res.data){
						var idea = "<h2>"+res.data[temp].activityName+"</h2>"+
						"<p>"+res.data[temp].signerName+"</p>"+
						"<p>"+({"0":"不同意","1":"同意","-1":"退回"}[res.data[temp].result])+"</p>"+
						"<p>"+(res.data[temp].opinion||"")+"</p>";
						if(isShowSeal){
							if(res.data[temp].signature!=""&&res.data[temp].signature!=null&&res.data[temp].signature!=undefined){
								idea = idea+"<p><span id='signature"+temp+"'></span></p>";
								showSeal(res.data[temp].signature,"signature"+temp,1);
							}
							if(res.data[temp].seal!=""&&res.data[temp].seal!=null&&res.data[temp].seal!=undefined){
								idea = idea+"<p><span id='seal"+temp+"'></span></p>";
								showSeal(res.data[temp].seal,"seal"+temp,0);
							}
						}
						if(res.data[temp].attId!=undefined&&res.data[temp].attId!=null&&res.data[temp].attId!=""){
							idea += 	"<p><a target='_blank' href='fileupload/download.do?id=" + res.data[temp].attId + "'>"+res.data[temp].attName+"</a></p>";
						}
						var content = "<div class='cd-timeline-content'>"+idea+"<span class='cd-date'>"+res.data[temp].signDate+"</span></div>";
						var html = "<div class='cd-timeline-block'><div class='cd-timeline-img cd-picture'><img src='pub/bpm/timeline/img/cd-icon-location.svg' ></div>"+content+"</div>"
						$("#cd-timeline").append(html);
					}
				}else{
					$("#cd-timeline").remove();
					$("body").append("<center>没有数据</center>")
				}
			}
		});
	})
	function showSeal(obj,position,isseal){
		$.post("pub/signseal/getListByIds.do",{Ids:obj},function(sign){
	         if(sign.success){
	             for(var i in sign.data){
	            	 if(isseal==1){
	            		 showSingleSeal(position,sign.data[i],320,i*80+40,0);
	            	 }
	            	 else{
	            		 showSingleSeal(position,sign.data[i],160,i*80+40,0);
	            	 }
	             } 
	         }
	    },"json");
	}
</script>
</head>
<body>
<div id="cd-timeline" class="cd-container">
</div>
</body>
</html>
