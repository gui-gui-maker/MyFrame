$(function(){
	//默认显示列表
	$("#listShow").show();
	$("#area_warp").hide();
});
$(document).keyup(function(event){
	if(event.keyCode==13){ //回车键
		
		 
	if($("#query").val()!=""){
		$("#tname").html("查询结果");
		 $("#listShow").hide();
		  $("#area_warp").show();
		  getReportNo();
	}else{
		$("#tname").html("统计数据");
		initList();
		$("#listShow").show();
		$("#area_warp").hide();
	}
	}
	});

$(document).ready(function(){
$("#getReportNo").click(function(){//点击查询切换报告部分显示
	  //$("#listShow").hide();
	  //$("#area_warp").show();
	  if($("#query").val()!=""&&$("#query").val()!="输入报告书编号或关键字查询"){
		  $("#tname").html("查询结果");
			 $("#listShow").hide();
			  $("#area_warp").show();
			  getReportNo();
		}else{
			$("#tname").html("统计数据");
			initList();
			$("#listShow").show();
			$("#area_warp").hide();
		}
	});
/*$("#query").click(function(){//点击查询切换报告部分显示
	alert($("#query").val()=="")
	if($("#query").val()==""){
		$("#listShow").show();
		  $("#area_warp").hide();
	}
	});*/

$("ul.year_list").on("click","li",function(){      //找到你点击的是哪个ul
	var classs=$(this).attr('class');
	var list=classs.split(",");
	top.$.dialog({
		width : 200, 
		height : 200, 
		lock : true, 
		title:"报告信息",
		content: 'url:app/gis/scjy/v3/select.jsp?id='+$(this).attr('id')+'&report_id='+list[0]+'&report_sn='+list[1]+'&advance_time='+list[2]+'&export_pdf='+list[3]+'&to_swf='+list[4],
		data : {"window" : window}
	}).max();
	
	/*var url = "report/query/showReport.do?id="+$(this).attr('id')+"&report_id="+$(this).attr('class');
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	top.$.dialog({
		width : w, 
		height : h, 
		lock : true, 
		title:"报告信息",
		content: 'url:'+url,
		data : {"window" : window}
	}).max();*/

});
});
function getReportNo(){
	var report_sn=$("#query").val();
	$.post("/qualityTjAction/tj/getByReportNo.do",{report_sn:report_sn},function(res){
		if(res.success){
			if(res.type=="2"){
				$("#listShow").hide();
				  $("#area_warp").show();
			 var data=res.list1;
			 var dul=$(".year_list");
				dul.html("");
				dul.append("<div class='yearline'><span class='line_l'></span><span class='line_r'></span><div class='line_m'></div></div>")
			 for (var i = 0; i < data.length; i++) {
				var yy=data[i].report_sn;
			
				var ele="<li id='"+data[i].id+"' class='"+data[i].report_id+","+data[i].report_sn+","+data[i].advance_time+","+data[i].export_pdf+","+data[i].to_swf+"'><div class=' y_list_box'> "+
						"		 <span class='yearnum'>"+yy.substring(5,9)+"</span> "+
						"			 <div class='y_l_box_cc'> "+
						"				<table width='100%' border='0' cellspacing='0' cellpadding='0' class='bgs_cc'> "+
						"					<tr> "+
						"						<td class='tr'>报告书编号:</td> "+
						"					<td>"+data[i].report_sn+"</td> "+
						"					<tr> "+
						"						<td class='tr'>检验日期:</td> "+
						"						<td>"+data[i].advance_time.substring(0,10)+"</td> "+
						"					</tr> "+
						"					<tr> "+
						"						<td class='tr'>下次检验日期:</td> "+
						"					<td>"+data[i].last_check_time.substring(0,10)+"</td> "+
						"					</tr>	 "+
						"				<tr> "+
						"						<td class='tr'>参加人员:</td> "+
						"						<td>"+data[i].check_op_name+"</td> "+
						"					</tr> "+
						"				<tr> "+
						"						<td class='tr'>检验结论:</td> "+
						"						<td>"+data[i].inspection_conclusion+"</td> "+
						"					</tr> ";
						if(data[i].is_cur_error!=""&&data[i].is_cur_error!="0"){
							ele=ele+"				<tr> "+
							"						<td class='tr' style='color:red'>报告纠错:</td> "+
							"						<td style='color:red'>"+data[i].is_cur_error+"</td> "+
							"					</tr> ";
						}
						ele=ele+"				</table> "+
						"			 	<div class='y_box_bg'></div> "+
						"			 </div> "+
						"			</div> "+
						"		</li>";
				dul.append(ele);
			 }
			 }else if(res.type=="1"){
				 $("#listShow").show();
				  $("#area_warp").hide();
				 var data1=res.list1;
				 var data2=res.list2;
				 var data3=res.list3;
				 var data4=res.list4;
				 var data5=res.list5;
				 var arr=new Array();
				if(data1.length>0){ //为数组添加元素
					arr.push(data1);
				}
				if(data2.length>0){ //为数组添加元素
					arr.push(data2);
				}
				if(data3.length>0){ //为数组添加元素
					arr.push(data3);
				}
				if(data4.length>0){ //为数组添加元素
					arr.push(data4);
				}
				if(data5.length>0){ //为数组添加元素
					arr.push(data5);
				}

				
	             //为列表添加数据
				 var dul=$("#demohq1 > ul");
					dul.html("");
					dul.css({"margin-top":"0px"});
				/*	$(".ta-list-cont").height($("#m-r-list-tab").height()-40);
					window.onresize = function(){
						$(".ta-list-cont").height($("#m-r-list-tab").height()-40);
					}*/
					for (var i = 0;i<arr.length; i++) {
						var data=arr[i];
						for (var int = 0; int < data.length; int++) {
							var type="";
							if(data[int].type=="rws"){
								type="任务书"
							}
							if(data[int].type=="nkdq"){
								type="内控到期"
							}
							if(data[int].type=="bhg"){
								type="不合格报告"
							}
							if(data[int].type=="rjrws"){
								type="软件任务书"
							}
							if(data[int].type=="jcbg"){
								type="纠错报告"
							}
							
							var ele=$("<li id='"+data[int].type+","+data[int].id+"'><div><div><span>"
									+data[int].no+"</span></div></div><div><div><span>"
									+data[int].name+"</span></div></div><div><div><span><span>"
									+type+"</span></span></div></div><div><div><span>"
									+data[int].date.substring(0,10)+"</span></div></div><div><div><span><span>"
									+data[int].status+"</span></span></div></div></li>");
							ele.click(function(e){ 
								var _this=$(this);
								var html=$("#detail1").html();
								var id=$(this).attr('id');
								var list=id.split(",");
								showDetail(list[0],list[1]);
								
								
							});
							dul.append(ele);
							//dul.append("<li><div><div><span>锦江</span></div></div><div><div><span>成都市城乡东锦房地产开发有限公司（绿岛筑）</span></div></div><div><div><span>2017-12-02</span></div></div><div><div><span><span style='color:#33FF99'>出具中</span></span></div></div></li>");	
						}
						
					}
				 
				 
			}
			 }else{
			
		}
		
	},"json")
}