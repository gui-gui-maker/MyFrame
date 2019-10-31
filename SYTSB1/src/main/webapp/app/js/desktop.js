 function setTime(){
      var day="";
      var month="";
      var ampm="";
      var ampmhour="";
      var myweekday="";
      var year="";
      var myHours="";
      var myMinutes="";
      var mySeconds="";
      mydate=new Date();
      myweekday=mydate.getDay();
      mymonth=parseInt(mydate.getMonth()+1)<10?"0"+(mydate.getMonth()+1):mydate.getMonth()+1;
      myday= mydate.getDate();
      myyear= mydate.getYear();
      myHours = mydate.getHours();
      if(myHours>6 && myHours<=10)
    	  $("#user").html("早上好！");
      if(myHours>10 && myHours<=12)
    	  $("#user").html("上午好！");
      if(myHours>12 && myHours<=18)
    	  $("#user").html("下午好！");
      if(myHours>18 || myHours<=5)
    	  $("#user").html("晚上好！");
      //myMinutes = mydate.getMinutes();
      myMinutes = parseInt(mydate.getMinutes())<10?"0"+(mydate.getMinutes()):mydate.getMinutes();
      mySeconds = parseInt(mydate.getSeconds())<10?"0"+mydate.getSeconds():mydate.getSeconds();
      year=(myyear > 200) ? myyear : 1900 + myyear;
      if(myweekday == 0)
      weekday=" 星期日 ";
      else if(myweekday == 1)
      weekday=" 星期一 ";
      else if(myweekday == 2)
      weekday=" 星期二 ";
      else if(myweekday == 3)
      weekday=" 星期三 ";
      else if(myweekday == 4)
      weekday=" 星期四 ";
      else if(myweekday == 5)
      weekday=" 星期五 ";
      else if(myweekday == 6)
      weekday=" 星期六 ";
      nowTime.innerText=myHours+":"+myMinutes+":"+mySeconds;
      setTimeout("setTime()",1000);
  }