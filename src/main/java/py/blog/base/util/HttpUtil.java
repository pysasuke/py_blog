package py.blog.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Component
public class HttpUtil {

    public String executeGet(String url) {
        return this.executeGet(url, null);
    }

    public String executeGet(String url, List<NameValuePair> headers) {
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Accept-Charset", "utf-8");

        if(!CollectionUtils.isEmpty(headers)){
            for (NameValuePair pair : headers) {
                if(null != pair){
                    httpget.setHeader(pair.getName(), pair.getValue());
                }
            }
        }

        return this.execute(httpget);
    }

    public String executePost(String url, List<NameValuePair> params) {
        return this.executePost(url, params, null);
    }

    public String executePost(String url, List<NameValuePair> params, List<NameValuePair> headers) {
        HttpPost httppost = new HttpPost(url);

        if (CollectionUtils.isEmpty(params)) {
            return null;
        }

        httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));




        if(!CollectionUtils.isEmpty(headers)){
            for (NameValuePair pair : headers) {
                if(null != pair){
                    httppost.setHeader(pair.getName(), pair.getValue());
                }
            }
        }

        return this.execute(httppost);
    }

    private String execute(HttpUriRequest request) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    try {
                        EntityUtils.consume(entity);
                        entity.getContent().close();
                    } catch (IOException ee) {
                        e.printStackTrace();
                    }
                    e.printStackTrace();
                } finally {
                    reader.close();
                    EntityUtils.consume(entity);
                }
                return new String(sb.toString().getBytes(), "utf-8");

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            request.abort();
            e.printStackTrace();
        } catch (IllegalStateException e) {
            request.abort();
            e.printStackTrace();
        } finally {
        }

        return null;
    }

    /**
     * 获取客户端IP地址.<br>
     * 支持多级反向代理
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     */
    public String getRemoteAddr(final HttpServletRequest request) {
        try {
            String remoteAddr = request.getHeader("X-Forwarded-For");
            // 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
            if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
                String[] array = remoteAddr.split(",");
                for (String element : array) {
                    if (isEffective(element)) {
                        remoteAddr = element;
                        break;
                    }
                }
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getHeader("X-Real-IP");
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            return remoteAddr;
        } catch (Exception e) {
            log.error("get romote ip error,error message:" + e.getMessage());
            return null;
        }
    }

    /**
     * 远程地址是否有效.
     *
     * @param remoteAddr 远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private boolean isEffective(final String remoteAddr) {
        boolean isEffective = false;
        if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
                && (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
            isEffective = true;
        }
        return isEffective;
    }
}
