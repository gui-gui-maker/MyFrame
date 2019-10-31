<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-form.jsp" %>
    <title>印章展示</title>
    <script type="text/javascript">
    $(function(){
		getData();
	})
	function getData() {
        var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: ""};
        $.post("qm?_method=q&pageid=TJY2_YZ_YZZS",data,parseData,'json')
    }
    var index = 0;
    function parseData(data) {
        /* $("#content div").animate({opacity: 'hide'}, "slow"); */
        $("#content").empty();
        if (data.rows.length > 0) {
            index = data.rows.length;
            for (var j in data.rows) {
            	var src;
            	if(data.rows[j].id!=''&&data.rows[j].id!=null&&data.rows[j].id!=undefined){
            		src = "BgLeaveAction/yzImage.do?id="+ data.rows[j].id
            		/* src = "data:image/png;base64,"+ data.rows[j].sealpicture */
            	/*s="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAFYklEQVR42sVXfUyUdRz/8XKhMZQO"+  
                "7nmeuwMFwrBEVJqgDOcUHZvNuTarrdKam0zdKrckAy4goowxbaM5hE1yjVVzLjLRokBFz2qsQPGF"+  
                "F8vmamtkL4s4OHDy6ff5EQcXx91hf/jHB777fj8v3+d57p7nOQFA3EuoPxcLhV90SlxziOSeUrHd"+  
                "WRz5fv1uc1fl03OGizdGgmBd/5K5mzNyyO0M4EkEXqBAiI58Ed9WEFp1+MXYm1vW68hKM7AwJR5W"+  
                "mx3RZqsCa/Y4I4dcaqilx10t0PGqCOssEjuvH7H9vW1zEgzNgK5ZYVh02HUN86w65tvGwJo9zhRH"+  
                "cqmhlh70mtEC7a8Ie2ep6dPfW5eM3OnbhMo9S6GbLV6h04Eccqmhlh70omdQC3TsFbbLZaYL/W2Z"+  
                "wA+PYbQ9B780rUJu1nxoMZaAC5BDLjXU0oNe9KS33wXkqRKXXguv+as1HejOxYhzNdwS+G4NjlUs"+  
                "U0cX7+cscEYOudRQSw960ZPezPC9QIE69dv6Pk4exLX1GDm/Cu5z2Qojzmy4WrOxZUOSPELNz9Fr"+  
                "ikMuNR699KInvZkx/sH0WqB9rzCuH5j7Mzrk1uel8HQW3GcmgK+y4axOR0qCVX7gpoazxxk55E7W"+  
                "0oue9GYGs7wXKFCnP7+/cdEovs7GUHMm3C0rvHD7jMTZFSjYugAW89RvAXuckUPuf/X0pDczmMVM"+  
                "zwJyI8v3lVFX7pzLxMiXGXB/sdwncDoDvfXLkJFqh1WbuBSs2eOMnOn09GYGs5g5eYHcXw/HAWcz"+  
                "MfRZOtwSQz7APloexcHdD0GLnVhAlzV7nPnT8j8zmMVMzwKdjtCy/qPJuN0kSY1L/OL250tw61ga"+  
                "Nqy0Q7doCqzZ4yygXmYwi5meBa6Wmk4NNaRgpDEVQ8cX+YVbAk2L0VCcrL52BGv23AG0hMqQWcz0"+  
                "LNBTHtGFkw9D4UQQkLxR+X/LujiF0RMz0xIqc3yBb4sifmp+80G0lCcFhWYJZ0USDu6ar8C6OUit"+  
                "0sssZnoWaNhh+tFqGIg3dM9p9QvJM5t1vP1cvALrmWiZxUzPAidfMLWlJBjqZhKMiRarY+UiAzer"+  
                "5ymwZi8Y7dgNywAzPQuc3xNet3Ypv8+Bn3Y0sUqTQ3k24MM4BdZW3f9zYuKeoYNZzPQscNkRsv35"+  
                "1WbExhoBDXT5zN+4XMcfNVYMv2dTYM0eZ4H0zGAWMz0L9JaK1P1PRQ7EWa0Bn3aJdh2NL2vAEQ2u"+  
                "2jGwZo+zQHq7zJBZrusyc9IZEOEX8sM/yFxokafI8LO9jrx1GgaqY+CujYHr0BhYs5eXoynO9Kff"+  
                "ADOc+eEfyUyT19Owp0SsKd0U5eYrlU+xvMaLk3S0OcxAbTRcB73BHmdpSWNcn5dPejNDZq2d8jiW"+  
                "G4W07Q39JCctVr3T+br2ZY+bcac6CoPvzoGryhvscUaOr88CPenNDGb5fCPqKha2o3kRF1MTKfA+"+  
                "+uxHNNx4IwrDVZFwveMbnJFD7uSzQC960psZft8Je4rFkzXPzh5eME+HTTfUs94mbxx1W+cCVbMw"+  
                "uH82XNOAM3LIpUZppQe96EnvoN6K5TXaVfPMbCxO1BAVbeCJzBj89tYsDO+/DwOVEX5BDrmbpYZa"+  
                "etCLnkG/ll8qEqK7WOQe32lybs54AKd23I/RA+EYqDAFBXJPSg219KAXPWf8y+iKQxhXHaLyRklI"+  
                "363yUAzsC8OghGtfqE9wRg651FBLj7v/aTb+ul4oUi4VipIuh+i+WSKG+spC8Gd5CPr/BWv2OJOc"+  
                "Hsl9XWoW/v/fht5LhLUXiARZb71cJOquFIlv5BH2EqzZ40xyEsm9OJMfp/cS/wC6xRqbyM2GFQAA"+  
                "AABJRU5ErkJggg=="*/
            	} 
            	$("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getRoomInfo(this)'>" +
                        "<table>" +
                        "<tr><td rowspan='5'><input type='hidden' name='id' value='"+data.rows[j].id+"'>"+
                        <%-- "<img src ='"+<%
	                        is = blob.getBinaryStream();
	            			resp.setContentType("image/jpeg");
	            			os = resp.getOutputStream();
	            			int num = (int) blob.length();
	            			byte buf[] = new byte[num];
	            			while ((num = is.read(buf)) != -1) {
	            				os.write(buf);
	            			}
                        %>+"' width='160' height='130'/></td>"+ --%>
                        "<img src ='"+src+"' width='160' height='130'/></td>"+
                        "<td>印章序列号："+data.rows[j].seal_serial_number+"</td></tr>"+
                        "<tr><td>印章名称："+data.rows[j].title+"</td></tr>"+
                        "<tr><td>状态："+data.rows[j].status+"</td></tr>"+
                       
                        "<tr><td>启用时间："+data.rows[j].start_usingtime+"</td></tr>"+
                        "<tr><td>停止时间："+data.rows[j].stop_usingtime+"</td></tr>"+
                        "</table></div>");
                
            }
            animate(0);
        }else{
        	$("#content").html("暂无任何印章！")
        }
    }
    function animate(i) {
        if (i < index) {
            $("#div" + i).animate({opacity: 'show'}, "normal", function () {
                animate(++i)
            });
        }
    }
</script>
<style type="text/css">
    .tt {
        float: left;
        background-color: #ededed;
        margin: 5px;
        padding: 5px;
        width: 350px;
        height: 130px;
    }
</style>
</head>
<body>
<input type="hidden" id="selectedId"/>
<input type="hidden" id="roomName"/>
<div class="item-tm">
	<div class="div1" id="tbar" style=""></div>
</div>
<div id="content" class="scroll-tm">
</div>
</body>
</html>