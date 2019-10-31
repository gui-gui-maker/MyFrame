<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<link rel="stylesheet" type="text/css" href="pub/bpm/timeline/style/default.css" />
	<link rel="stylesheet" type="text/css" href="pub/bpm/timeline/style/component-mobile.css" />
	<script src="pub/bpm/timeline/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="k/kui/frame/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="pub/eseal/bc/seal.js"></script>
	<script type="text/javascript">
		$(function(){
			//手机端
			var isShowSeal = (null=="${param.isSeal}"||""=="${param.isSeal}"||"${param.isSeal}"==undefined)?false:true;
			$.getJSON("bpm/opinion/serviceOpinion.do?new="+Math.random(),{serviceId:"${param.serviceId}"},function(res){
				if(res.success){
					if(res.data.length > 0){
						var rmap = {"0":"不同意","1":"同意","-1":"退回"};
						for(var temp in res.data){
							var idea = "<h2 class='cbp_h2a'>"+res.data[temp].activityName+"</h2>"+
									"<div class='cbp_d1'>"+res.data[temp].signerName+"</div>"+
									"<div class='cbp_d2'>"+(rmap[res.data[temp].result]||"")+"</div>"+
									"<div class='cbp_d3'>"+(res.data[temp].opinion||"")+"</div>";
							var ise1=false;
							var ise2=false;
							if(isShowSeal){
								if(res.data[temp].signature!=""&&res.data[temp].signature!=null&&res.data[temp].signature!=undefined){
									idea = idea+"<div class='cbp_d4'><span id='signature"+temp+"'></span></div>";
									showSeal(res.data[temp].signature,"signature"+temp,1);
									ise1=true;
								}
								if(res.data[temp].seal!=""&&res.data[temp].seal!=null&&res.data[temp].seal!=undefined){
									idea = idea+"<div class='cbp_d5'><span id='seal"+temp+"'></span></div>";
									showSeal(res.data[temp].seal,"seal"+temp,0);
									ise2=true;
								}
							}
							if(res.data[temp].attId!=undefined&&res.data[temp].attId!=null&&res.data[temp].attId!=""){
								idea += 	"<div class='cbp_d6'><a style='text-decoration:underline' target='_blank' href='fileupload/download.do?id=" + res.data[temp].attId + "'>"+res.data[temp].attName+"</a></div>";
							}
							var content = idea;
							var he1=(ise1 || ise2) ?"isShowSeal":"";
							var html = "<li><time class='cbp_tmtime' ><span>"+res.data[temp].signDate.substring(0,10)+"</span> <span>"+res.data[temp].signDate.substring(11)+"</span> </time><div class='cbp_tmicon cbp_tmicon-screen'></div><div class='cbp_tmlabel "+he1+"'>"+content+"</div></li>";
							$("#cd-timeline").append(html);
						}
					}else{
						$("#cd-timeline").remove();
						$("body").append("<center>没有数据</center>")
					}
				}
				//var h1=$(document).height();
				//parent.$("#_frame").height(h1);
			});



		})
		function showSeal(obj,position,isseal){
			$.post("pub/signseal/getListByIds.do",{Ids:obj},function(sign){
				if(sign.success){
					for(var i in sign.data){
						if(isseal==1){
							showSingleSeal(position,sign.data[i],340,i*80+80,0);
						}
						else{
							showSingleSeal(position,sign.data[i],180,i*80+80,0);
						}
					}
				}
			},"json");
		}
	</script>
</head>
<body>
<div class="container">
	<div class="main">
		<ul class="cbp_tmtimeline" id='cd-timeline'>
		</ul>
	</div>
</div>
</body>
</html>



