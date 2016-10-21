package py.blog.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MailHttpUtil {
	private static final Logger logger = Logger.getLogger(MailHttpUtil.class);

	public static boolean send(String to, String subject, String content) {
		try {
			HttpClient client = new DefaultHttpClient();
			String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
			HttpPost post = new HttpPost(url);
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			// 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
			nvps.add(new BasicNameValuePair("api_user", "ibbpp_mail"));
			nvps.add(new BasicNameValuePair("api_key", "QiyZfOpvdAmbH6w5"));
			nvps.add(new BasicNameValuePair("from", "service@mail.ibbpp.com"));
			nvps.add(new BasicNameValuePair("fromname", "宝宝拍拍"));
			nvps.add(new BasicNameValuePair("to", to));
			nvps.add(new BasicNameValuePair("subject", subject));
			nvps.add(new BasicNameValuePair("html", content));

			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			// Send the post request and get the response
			HttpResponse response = client.execute(post);

			int status = response.getStatusLine().getStatusCode();
			logger.info("邮件发送内容 : " + nvps.toString());
			logger.info("邮件返回状态 : " + status);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			logger.info(result.toString());

			if (status == 200) {
				logger.info("邮件接口调用成功");
			} else {
				logger.info("邮件接口调用成功");
			}
			JSONObject obj = JSONObject.fromObject(result.toString());
			if (obj.containsKey("message")) {
				if ("success".equals(obj.getString("message"))) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
