package com.lsts.process.menu.util;

import com.lsts.process.menu.pojo.AccessToken;
import com.lsts.process.menu.pojo.Menu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	public static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			if (outputStr != null) {
				OutputStream outputStream = httpUrlConn.getOutputStream();

				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null)
				buffer.append(str);

			bufferedReader.close();
			inputStreamReader.close();

			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
				.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

		if (jsonObject != null)
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;

				log.error("获取token失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")),
						jsonObject.getString("errmsg"));
			}

		return accessToken;
	}

	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		try {
			String jsonMenu = JSONObject.fromObject(menu).toString();

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + jsonMenu);

			JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + jsonObject);

			if ((jsonObject == null) || (jsonObject.getInt("errcode") == 0))
				// break label143;
				result = jsonObject.getInt("errcode");
			log.error("创建菜单失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")),
					jsonObject.getString("errmsg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}