package py.blog.util;

import lombok.extern.slf4j.Slf4j;
import py.blog.enums.SmsTemplateType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class DayuSmsProUtil {

	private static String urlOnline = "http://gw.api.taobao.com/router/rest";// 接口调用地址

	private static String appkey = "23350309";

	private static String secret = "136624006499c85982ce3e3a8bf82955";

	public static void send(String paramStr, SmsTemplateType templateType, String mobile) {
		String requestContent = getSmsRequestContent(paramStr, templateType, mobile);

		String result = AliDayuUtil.getResult(urlOnline, requestContent);

		log.info(result);
	}

	private static String getSmsRequestContent(String paramStr, SmsTemplateType templateType, String mobile) {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();

		apiparamsMap.put("format", "json");

		apiparamsMap.put("method", "alibaba.aliqin.fc.sms.num.send");

		apiparamsMap.put("sign_method", "md5");

		apiparamsMap.put("app_key", appkey);

		apiparamsMap.put("v", "2.0");

		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		apiparamsMap.put("timestamp", timestamp);
		apiparamsMap.put("sms_type", "normal");
		apiparamsMap.put("sms_free_sign_name", templateType.getName());
		apiparamsMap.put("sms_param", paramStr);
		apiparamsMap.put("rec_num", mobile);
		apiparamsMap.put("sms_template_code", templateType.getCode());
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

	public static void main(String[] args) {

		String param = "{\"name\":\"馨恬爸爸\",\"from\":\"妈妈\",\"code\":\"q4zJyy\"}";
		DayuSmsProUtil.send(param, SmsTemplateType.FAMILY_INVITE, "15376706333");
	}

}
