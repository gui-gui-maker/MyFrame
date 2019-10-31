
lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});

lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
  elem: '#test2' //指定元素
});

lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
  elem: '#test3' //指定元素
});

lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
  elem: '#test4' //指定元素
});

lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
elem: '#startDate' //指定元素
});

lay('#version').html('-v'+ laydate.v);
//执行一个laydate实例
laydate.render({
elem: '#endDate' //指定元素
});

$('.filter-box').selectFilter({
		callBack : function (val){
			//返回选择的值
			console.log(val+'-是返回的值')
		}
});
