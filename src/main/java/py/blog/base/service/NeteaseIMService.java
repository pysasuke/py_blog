package py.blog.base.service;


import py.blog.base.commons.Constants;
import py.blog.base.enmu.ResponseCode;
import py.blog.base.util.CheckSumBuilder;
import py.blog.base.util.HttpUtil;
import py.blog.base.util.RandomUtils;
import py.blog.base.util.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NeteaseIMService {

	@Autowired
	private HttpUtil httpUtil;


	public Map<String, String> executePost(String url, List<NameValuePair> params, List<NameValuePair> keyValues) {
		HttpUtil httpUtil = new HttpUtil();
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("Invalid param!");
		}

		if (CollectionUtils.isEmpty(keyValues)) {
			keyValues = new ArrayList<>();
		}

		String romStr = RandomUtils.getRandomLong(100000000L) + "";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(Constants.NETEASE_APP_SECRET, romStr, curTime);

		//header
		keyValues.add(new BasicNameValuePair("AppKey", Constants.NETEASE_APP_KEY));
		keyValues.add(new BasicNameValuePair("Nonce", romStr));
		keyValues.add(new BasicNameValuePair("CurTime", curTime));
		keyValues.add(new BasicNameValuePair("CheckSum", checkSum));
		keyValues.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));

		String result = httpUtil.executePost(url,params,keyValues);
		if (StringUtils.isBlank(result)) {
			throw new ServiceException(ResponseCode.IM_REQUEST_FAIL,"请求参数为空!");
		}

		Map<String, String> map = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(result, Map.class);
			Object code = map.get("code");
			if (null == code || !code.equals(200)) {
				throw new ServiceException(ResponseCode.IM_REQUEST_FAIL,"IM请求失败");
			}
		} catch (Exception e) {

			throw new ServiceException(ResponseCode.IM_REQUEST_FAIL,"IM请求失败");
		}

		return map;
	}
}
