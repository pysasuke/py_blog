
package py.blog.base.commons;


/**
 * Created by Tang_sihang on 2016/7/15 0015.
 */

/**
 * 常量
 */
public class Constants {
    /**
     * 网易IM 常量
     */
    public static String NETEASE_APP_KEY = "02e7e89baebf935ae5cd94e2854fafbe";  //云信appKey

    public static String NETEASE_APP_SECRET = "8872e35ce905"; //云信 appSecret

    public static String NETEASE_ACCID_PREFIX = "test";


    /**
     * 图记精选状态常量
     */

    public static int ALBUMTOP_STATUS_TO_PUBLISH = 0; // 待发布

    public static int ALBUMTOP_STATUS_IS_PUBLISH = 1; //已发布

    /**
     * 广告弹窗页面传值状态常量
     */

    public static int PAGE_RETURN_STATUS_NOTRELEASE = 0;//未发布
    public static int PAGE_RETURN_STATUS_ALREADYRELEASE = 1;//已发布
    public static int PAGE_RETURN_STATUS_DELETED = 2;//已删除
    public static int PAGE_RETURN_STATUS_ALL = -1;//全部

    /**
     * 聊天室状态常量
     */
    public static int IMGROUP_TO_CHECK = 0; //待审批
    /**
     * 查询限制条件
     */
    public static int PAGE_SIZE = 100;//最小限制条件100


    /**
     * 宝宝拍拍url
     */

    public static String COM_IBBPP_DOMAIN = "120.26.107.219"; //测试环境url

    /**
     * 阿里云常量
     */

    public static String ALIYUN_OSS_DOMAIN = "http://ibbpp-sysfile.oss-cn-shanghai.aliyuncs.com";
    public static String COM_IBBPP_SYSFILE_DOMAIN = "http://sysfile.ibbpp.com";

    public static String ALIYUN_OSS_ACCESSKEY_ID = "Rv1Dm9Cjaf7kNem3";
    public static String ALIYUN_OSS_ACCESSKEY_SECRET = "ZFUgqtyzGAfWut7fn2Xy1S2KAUiQh7";
    public static String ALIYUN_OSS_ACCESSKEY_REGION = "oss-cn-shanghai";
    public static String ALIYUN_OSS_ACCESSKEY_BUCKET = "ibbpp-sysfile";
}


