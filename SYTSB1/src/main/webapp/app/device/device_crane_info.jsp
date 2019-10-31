<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<body>
  
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       
       	<input name="id" type="hidden" />
		
	
       <tr> 
        <td class="l-t-td-left" style="width: 15%">工作级别：</td>
        <td class="l-t-td-right"><u:combo name="p40001001" code="BASE_PARA_P40001001" />
        </td>
        <td class="l-t-td-left" style="width: 15%">工作环境：</td>
        <td class="l-t-td-right"><u:combo name="p40001002" code="BASE_PARA_P40001002" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">门架型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001003" code="BASE_PARA_P40001003" /> 
        </td>
        <td class="l-t-td-left">取物装置：</td>
        <td class="l-t-td-right"><u:combo name="p40001004" code="BASE_PARA_P40001004" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">司机室型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001005" code="BASE_PARA_P40001005" /> 
        </td>
        <td class="l-t-td-left">操纵形式：</td>
        <td class="l-t-td-right"><u:combo name="p40001006" code="BASE_PARA_P40001006" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">导电方式：</td>
        <td class="l-t-td-right"><u:combo name="p40001007" code="BASE_PARA_P40001007" /> 
        </td>
        <td class="l-t-td-left">旋转支承装置型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001008" code="BASE_PARA_P40001008" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">旋转驱动装置型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001009" code="BASE_PARA_P40001009" /> 
        </td>
        <td class="l-t-td-left">变幅驱动型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001010" code="BASE_PARA_P40001019" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">臂架系统型式：</td>
        <td class="l-t-td-right"><u:combo name="p40001011" code="BASE_PARA_P40001011" /> 
        </td>
        <td class="l-t-td-left">出入库方式：</td>
        <td class="l-t-td-right"><input name="p40001012" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">升降驱动方式：</td>
        <td class="l-t-td-right"><input name="p40001013" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">横行驱动方式：</td>
        <td class="l-t-td-right"><input name="p40001014" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">设计规范：</td>
        <td class="l-t-td-right"><input name="p40001015" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">制造规范：</td>
        <td class="l-t-td-right"><input name="p40001016" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">设备新旧状况：</td>
        <td class="l-t-td-right"><input name="p40001017" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">监检形式：</td>
        <td class="l-t-td-right"><u:combo name="p40001018" code="BASE_PARA_P40001018" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">变幅方式：</td>
        <td class="l-t-td-right"><u:combo name="p40001019" code="BASE_PARA_P40001019" /> 
        </td>
        <td class="l-t-td-left">变幅平衡方式：</td>
        <td class="l-t-td-right"><input name="p40001020" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">轨矩：</td>
        <td class="l-t-td-right"><input name="p40002001" type="text" ltype='text' ligerui="{suffix:'m'}" validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">幅度：</td>
        <td class="l-t-td-right"><input name="p40002002" type="text" ltype='text' ligerui="{suffix:'度'}" validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">起升速度：</td>
        <td class="l-t-td-right"><input name="p40002003" type="text" ltype='text' ligerui="{suffixWidth:'40',suffix:'m/min'}" validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left"  >起升速度（副钩）：</td>
        <td class="l-t-td-right"><input name="p40002003_fg" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">载荷：</td>
        <td class="l-t-td-right"><input name="p40002004" type="text" ltype='text' ligerui="{suffix:'t'}" validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">载荷（副钩）：</td>
        <td class="l-t-td-right"><input name="p40002004_fg" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">提升力矩：</td>
        <td class="l-t-td-right"><input name="p40002005" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">提升高度：</td>
        <td class="l-t-td-right"><input name="p40002006" type="text" ltype='text' ligerui="{suffix:'m'}" validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">提升高度（副钩）：</td>
        <td class="l-t-td-right"><input name="p40002006_fg" type="text" ltype='text' ligerui="{suffix:'m'}" validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">工作半径：</td>
        <td class="l-t-td-right"><input name="p40002007" type="text" ltype='text' ligerui="{suffix:'m'}" validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">层站（层数）：</td>
        <td class="l-t-td-right"><input name="p_cz" type="text" ltype='text' ligerui="{suffix:'层'}" validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">驱动方式：</td>
        <td class="l-t-td-right"><input name="p_qdxs" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">变幅速度：</td>
        <td class="l-t-td-right"><input name="p_bfsd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">回转速度：</td>
        <td class="l-t-td-right"><input name="p_hzsd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">控制屏出行&nbsp;&nbsp;&nbsp;<br>驶速度厂编号：</td>
        <td class="l-t-td-right"><input name="p_xssd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">跨度：</td>
        <td class="l-t-td-right"><input name="p_kd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">吊笼工作行程：</td>
        <td class="l-t-td-right"><input name="p_drgzxc" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">提升绳直径：</td>
        <td class="l-t-td-right"><input name="p_tsszj" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">自由端高度：</td>
        <td class="l-t-td-right"><input name="p_zydgd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">安全器型号：</td>
        <td class="l-t-td-right"><input name="p_acqxh" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">安全器编号：</td>
        <td class="l-t-td-right"><input name="p_acqbh" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">安全器协作速度：</td>
        <td class="l-t-td-right"><input name="p_acqxzsd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">运行速度（大车）：</td>
        <td class="l-t-td-right"><input name="p_yxsd_dc" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left">运行速度（小车）：</td>
        <td class="l-t-td-right"><input name="p_yxsd_xc" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">轨道长度：</td>
        <td class="l-t-td-right"><input name="p_gdcd" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       	<td class="l-t-td-left">车位数：</td>
        <td class="l-t-td-right"><input name="p_cws" type="text" ltype='spinner' ligerui="{suffix:'个'}" validate="{required:false,maxlength:30}"/> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">适停车辆尺寸：</td>
        <td class="l-t-td-right">
        	<input name="p_stclcc" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
       	<td class="l-t-td-left">&nbsp;&nbsp;</td>
        <td class="l-t-td-right">&nbsp;&nbsp;</td>
       </tr>
      </table>
</body>
</html>
