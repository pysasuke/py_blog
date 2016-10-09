package py.blog.base.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by zczhao on 16/3/7.
 */

@Configuration
public class ConfigResource {

    @Value("${py.blog.netease.appKey}")
    private String neteaseAppKey;

    @Value("${py.blog.netease.appSecret}")
    private String neteaseAppSecret;

    @Value("${py.blog.domain}")
    private String comIbbppDomain;

    @Value("${py.blog.netease.prefix}")
    private String neteasePrefix;

    @PostConstruct
    public void init(){
        Constants.NETEASE_APP_KEY = neteaseAppKey;
        Constants.NETEASE_APP_SECRET = neteaseAppSecret;
        Constants.COM_IBBPP_DOMAIN = comIbbppDomain;
        Constants.NETEASE_ACCID_PREFIX = neteasePrefix;
    }

}
