jnjy=0;//任务书
jnsk=0;//内控到期
jnbx=0;//不符合报告
jrsk=0;//软件任务书
jrjy=0;//体系文件
jrdy=0;//纠错报告
$(function(){
	$(".addnum").hide();
	initData();
	initList();
	setInterval(function(){
		initData();
		},60000);
	setInterval(function(){
		initList();
		},300000);
	setInterval(function(){
		$(".addnum").hide();
		},10000);
});

//加载统计数据
function initData(){
	//先保存上一次获取数据
	var jnjys=jnjy;//任务书
	var jnsks=jnsk;//内控到期
	var jnbxs=jnbx;//不符合报告
	var jrsks=jrsk;//软件任务书
	var jrjys=jrjy;//体系文件
	var jrdys=jrdy;//纠错报告
	
	$.ajaxSettings.async = false;
	$.getJSON("/qualityTjAction/tj/initCount.do",function(res){
		if(res.success){
			 var data=res.data;
			 $("#rws").html(""+data.rws_y+"(已完成)/"+data.rws_t+"(总数)");
			 $("#nkdq").html(""+data.nkdq_c+"(超期)/"+data.nkdq_t+"(总数)");
			 $("#bhg").html(""+data.bhg_b+"(不合格)/"+data.bhg_f+"(复检)");
			 $("#rjrws").html(""+data.rjrws_y+"(已完成)/"+data.rjrws_n+"(未完成)");
			 $("#txwj").html(data.txwj+"(份)");
			 $("#jcbg").html(""+data.jcbg_n+"(未完成)/ "+data.jcbg_t+"(总数)");
			 jnjy=data.rws_y;//任务书
			 jnsk=data.nkdq_c;//内控到期
			 jnbx=data.bhg_b;//不合格报告
			 jrjy=data.txwj;//体系文件
			 jrdy=data.jcbg_n;//纠错报告
			 jrsk=data.rjrws_y//软件任务书
			 }else{
			
		}
	})
	//查询是否有增加的
	var jnjyss=jnjys-jnjy;//任务书
	var jnskss=jnsks-jnsk;//内控到期
	var jnbxss=jnbxs-jnbx;//不合格报告
	var jrskss=jrsks-jrsk;//软件任务书
	var jrjyss=jrjys-jrjy;//体系文件
	var jrdyss=jrdys-jrdy;//纠错报告
	/*jnjyss=10;
	jnskss=10;
	jnbxss=10;
	jrskss=10;
	jrjyss=10;
	jrdyss=10;*/
	if(jnjyss>0){
		$("#addnum0").show();
		$("#anum0").html("+"+jnjyss);
	}
	if(jnskss>0){
		$("#addnum1").show();
		$("#anum1").html("+"+jnskss);
	}
	if(jnbxss>0){
		$("#addnum2").show();
		$("#anum2").html("+"+jnbxss);
	}
	if(jrjyss>0){
		$("#addnum4").show();
		$("#anum4").html("+"+jrjyss);
	}
	if(jrskss>0){
		$("#addnum3").show();
		$("#anum3").html("+"+jrskss);
	}
	if(jrdyss>0){
		$("#addnum5").show();
		$("#anum5").html("+"+jrdyss);
	}

}
//加载列表数据
function initList(){
	$.getJSON("/qualityTjAction/tj/getList.do",function(res){//获取全部列表数据
		if(res.success){
			 var data1=res.list1;
			 var data2=res.list2;
			 var data3=res.list3;
			 var data4=res.list4;
			 var data5=res.list5;
			 var arr=[];
			 for (var int = 0; int < 10; int++) {
				 var index = Math.floor((Math.random()*20));//获取20里面的随机数字
				 arr.push(data1[index]);//为数组添加随机数的元素
				 arr.push(data2[index]);
				 arr.push(data3[index]);
				 arr.push(data4[index]);
				 arr.push(data5[index]);
				 
			}
             //为列表添加数据
			 var dul=$("#demohq1 > ul");
				dul.html("");
				$(".ta-list-cont").height($("#m-r-list-tab").height()-40);
				window.onresize = function(){
					$(".ta-list-cont").height($("#m-r-list-tab").height()-40);
				}
				for (var i = 0;i<arr.length; i++) {
					var type="";
					if(arr[i].type=="rws"){
						type="任务书"
					}
					if(arr[i].type=="nkdq"){
						type="内控到期"
					}
					if(arr[i].type=="bhg"){
						type="不合格报告"
					}
					if(arr[i].type=="rjrws"){
						type="软件任务书"
					}
					if(arr[i].type=="jcbg"){
						type="纠错报告"
					}
					
					var ele=$("<li id='"+arr[i].type+","+arr[i].id+"'><div><div><span>"
							+arr[i].no+"</span></div></div><div><div><span>"
							+arr[i].name+"</span></div></div><div><div><span><span>"
							+type+"</span></span></div></div><div><div><span>"
							+arr[i].date.substring(0,10)+"</span></div></div><div><div><span><span>"
							+arr[i].status+"</span></span></div></div></li>");
					ele.click(function(e){
						var _this=$(this);
						var html=$("#detail1").html();
						//listDetail(listId,this.id);
						var id=$(this).attr('id');
						var list=id.split(",");
						showDetail(list[0],list[1]);
						
					});
					dul.append(ele);
					//dul.append("<li><div><div><span>锦江</span></div></div><div><div><span>成都市城乡东锦房地产开发有限公司（绿岛筑）</span></div></div><div><div><span>2017-12-02</span></div></div><div><div><span><span style='color:#33FF99'>出具中</span></span></div></div></li>");
				}
			 }else{
		}
	})
	var h1=$(window).height()*0.5;
    $(".ta-list-cont").height($(".cxnews").height()-90);
        $("#area_warp").height(h1-170);

    var liHeight = $('.ta-list-cont').find('li').eq(0).outerHeight(true);
    $("#demohq1").myScroll({
      speed: 100,
      //数值越大，速度越慢
      rowHeight:liHeight  //li的高度
    });
	
}

function showDig(name,url){
	top.$.dialog({
		width : 1000, 
		height : 800, 
		lock : true, 
		title:name,
		content: 'url:'+url,
		data : {"window" : window}
	}).max();
}
function showDetail(type,id){
	var url="";
	var name="";
	if(type=="rws"){
		url='url:app/qualitymanage/task_allot_fb_datail.jsp?pageStatus=detail&id='+ id+"&status=1";
		name="任务书";
	}
	if(type=="nkdq"){
		url='url:app/flow/info_detail.jsp?status=detail&id='+ id;
		name="内控到期";
	}
	if(type=="jcbg"){
		url= 'url:app/report/report_error_detail.jsp?status=detail&id='+id;
		name="纠错报告";
	}
	if(type=="rjrws"){
		url= 'url:app/func_task/func_task_detail.jsp?status=detail&id='+ id;
		name="软件任务书";
	}
	if(url==""){
		return;
	}
	if(top.$.dialog){
		top.$.dialog({
			width: 1000,
			height: 600,
			lock: true,
			title: name,
			content: url,
			data: {
				window: window
			}
		});
	}else{
		var dialog = $.dialog({
			title: '欢迎',
			content: '欢迎使用lhgdialog对话框组件！',
			icon: 'succeed',
			ok: function(){
		        this.title('警告').content('请注意lhgdialog两秒后将关闭！').lock().time(2);
		        return false;
		    }
		});
	}
	
}

