/**
 * 
 */
package com.lsts.gis.cacti;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.khnt.base.Factory;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.ming.webreport.DataRecord;

/**
 * 
 * @author Guido
 *
 */
@Controller
@RequestMapping("cactiUtil")
public class CactiUtil {
	
	@RequestMapping(value="getData")
	@ResponseBody
	public HashMap<String, Object> getData(String local_graph_id,String graph_start,String graph_end)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringBuilder strResult= new StringBuilder();
		try
        {
	       	String strURL = "http://lasa.scsei.org.cn/cacti/graph_xport.php?local_graph_id="
	       			+local_graph_id+"&rra_id=0&format=table&graph_start="+graph_start+"&graph_end="+graph_end;
	       	
        	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        	
        	String data = "";
        	while(!StringUtil.isEmpty(data = reader.readLine())){
        		strResult.append(data); 
        	}
        	map.put("success", true);
        	map.put("data",strResult.toString());
        }
        catch(Exception e)
        {
        	System.out.println("系统出错"+e);
        }
		System.out.println(strResult.toString());
		return map;
	}
	
	
}
