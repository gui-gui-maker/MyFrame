<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-form.jsp" %>
    <title>部门选择</title>
    <script type="text/javascript">
	    var contentGrid;
    	$(function(){
    		var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "[{name:'parent_id',compare:'=',value='${param.par_id}'}]"};
    		$.post("qm?_method=q&pageid=TJY2_CW_ORG_LIST",data,parseData,"json");
        });
    	/* function parseData(data) {
            if (data.rows.length > 0) {
            	contentGrid=$("#content").ligerGrid({
        			columns: [ 
        	              {display: 'ID', name: 'id', hide:true} ,
        	              { display: '部门名称', name: 'org_name', minWidth: 60 },
        	              {display: 'ID', name: 'id', hide:true} ,
        	              { display: '部门名称', name: 'org_name', minWidth: 60 },
        	              {display: 'ID', name: 'id', hide:true} ,
        	              { display: '部门名称', name: 'org_name', minWidth: 60 },
        	              ],
        		    enabledEdit:false,
        		    clickToEdit: false,
        		    rownumbers: true,    
        		    width:"98%",
        		    frozenRownumbers: false,
        		    usePager: false,
        		    data:{Rows:eval(JSON.stringify(data.rows))}
        		})
            }else{
            	$("#content").html("没有数据！")
            }
        } */
        var index = 0;
        function parseData(data) {
            //$("#content div").animate({opacity: 'hide'}, "fast");
            //$("#content").empty();
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                    $("#content").append("<div class='tt' id='div" + j + "' style='cursor:pointer' onClick='getDepInfo(this)'>" +
                            "<table>" +
	                            "<tr>"+
	                            "<td><input type='hidden' name='id' value='"+data.rows[j].id+"'/></td>"+
	                            "<td><input type='hidden' name='org_name' value='"+data.rows[j].org_name+"'/></td>"+
	                            "<td>"+(parseInt(j)+1)+"</td>"+
	                            "<td>"+"&nbsp;&nbsp;"+data.rows[j].org_name+"</td>"+
	                            "</tr>"+
                            "</table>"+
                            "</div>");
                    
                }
                //animate(0);
            }else{
            	$("#content").html("没有数据！")
            }
        }
    	function getDepInfo(obj){
    		var selectedId = $(obj).find("input[name='id']").val();
    		var org_name = $(obj).find("input[name='org_name']").val();
    		$('.tt').filter(function(){
    			$(this).css("background-color","#FFF");
    		})
    		$(obj).css("background-color","#dfe8f6");
    		$("#selectedId").val(selectedId);
    		$("#org_name").val(org_name);
    	}
    	function getSelectedPerson(){
    		if(($("#selectedId").val()==null || $("#selectedId").val().length==0)|| 
    				($("#org_name").val()==null || $("#org_name").val().length==0)){
    			return null;
    		}else{
    			return {
                    id: $("#selectedId").val(),
                    name: $("#org_name").val(),
                };
    		}
        }
    	function animate(i) {
            if (i < index) {
                $("#div" + i).animate({opacity: 'show'}, "fast", function () {
                    animate(++i)
                });
            }
        }
    </script>
    <style type="text/css">
        .tt {
            float: left;
            background-color: #FFF;
            padding: 5px;
            width: 240px;
            height: 23px;
            /* border:1px solid #d2e0f1; */
            border-right: 1px solid #c9d6e9;
            border-bottom: 1px solid #c9d6e9;
        } 
    </style>
</head>
	<body>
		<input type="hidden" id="selectedId"/>
		<input type="hidden" id="org_name"/>
		<div id="content" class="scroll-tm">
		</div>
	</body>
</html>