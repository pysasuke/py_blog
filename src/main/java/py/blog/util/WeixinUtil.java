package py.blog.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 公众平台通用接口工具类
 * 
 * @date 2013-08-09
 */
public class WeixinUtil {

	// 获取access_token的接口地址（GET） 限200（次/天）
	private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private final static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=accessToken&type=jsapi";

	private static Logger log = Logger.getLogger(WeixinUtil.class);

	/**
	 * 获取access_token
	 * 
	 * @param appidO
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static String getAccessToken(String appid, String appsecret) {

		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl);

		String accessToken = null;

		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = jsonObject.getString("access_token");
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{" + jsonObject.getInt("errcode")
						+ "} errmsg:{" + jsonObject.getString("errmsg") + "}");
			}
		}
		return accessToken;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static String geApiTicket(String accessToken) {

		String requestUrl = ticket_url.replace("accessToken", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl);

		String ticket = null;
		// 如果请求成功
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (Exception e) {
				ticket = null;
				// 获取ticket失败
				log.error("获取ticket失败 errcode:{" + jsonObject.getInt("errcode")
						+ "} errmsg:{" + jsonObject.getString("errmsg") + "}");
			}
		}
		return ticket;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	private static JSONObject httpRequest(String requestUrl) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			// TrustManager[] tm = { new MyX509TrustManager() };
			// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			// sslContext.init(null, tm, new java.security.SecureRandom());
			// // 从上述SSLContext对象中得到SSLSocketFactory对象
			// SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			// httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("GET");

			httpUrlConn.connect();
			// // 当有数据需要提交时
			// if (null != outputStr) {
			// OutputStream outputStream = httpUrlConn.getOutputStream();
			// // 注意编码格式，防止中文乱码
			// outputStream.write(outputStr.getBytes("UTF-8"));
			// outputStream.close();
			// }

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
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

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		System.out.println("------jsapi_ticket:" + jsapi_ticket);
		System.out.println("------nonce_str:" + nonce_str);
		System.out.println("------timestamp:" + timestamp);
		System.out.println("------url:" + url);

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
