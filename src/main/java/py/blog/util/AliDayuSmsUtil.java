package py.blog.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class AliDayuSmsUtil {

	private static String testUrl = "http://gw.api.taobao.com/router/rest";// 沙箱环境调用地址

	private static String appkey = "23281644";

	private static String secret = "13d79a894ebbebd2f9c11dadd60fd5f2";

	private static Map<String, String[]> tempType;

	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(AliDayuSmsUtil.class);

	static {

		tempType = new HashMap<String, String[]>();
		tempType.put("1", new String[] { "注册验证", "SMS_2830111" });
		tempType.put("2", new String[] { "变更验证", "SMS_2830109" });
		tempType.put("3", new String[] { "身份验证", "SMS_3470059" });
		tempType.put("4", new String[] { "活动验证", "SMS_7740040" });

	}

	public static String testUserGet(String paramStr, String type, String mobile) {

		String[] temp = tempType.get(type);

		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();

		apiparamsMap.put("format", "json");

		apiparamsMap.put("method", "alibaba.aliqin.fc.sms.num.send");

		apiparamsMap.put("sign_method", "md5");

		apiparamsMap.put("app_key", appkey);

		apiparamsMap.put("v", "2.0");

		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		apiparamsMap.put("timestamp", timestamp);

		apiparamsMap.put("sms_type", "normal");
		apiparamsMap.put("sms_free_sign_name", temp[0]);
		apiparamsMap.put("sms_param", paramStr);
		apiparamsMap.put("rec_num", mobile);
		apiparamsMap.put("sms_template_code", temp[1]);
		// 生成签名

		String sign = AliDayuUtil.md5Signature(apiparamsMap, secret);

		apiparamsMap.put("sign", sign);

		StringBuilder param = new StringBuilder();

		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()

		.iterator(); it.hasNext();) {

			Map.Entry<String, String> e = it.next();

			param.append("&").append(e.getKey()).append("=").append(e.getValue());

		}

		return param.toString().substring(1);

	}

	public static void send(String param, String type, String mobile) {

		String result = AliDayuUtil.getResult(testUrl, testUserGet(param, type, mobile));

		logger.info(result);

	}

}
