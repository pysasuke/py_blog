package py.blog.base.enmu;

/**
 * Created by tangsihang on 16/7/18.
 */
public enum  ResponseCode {
    SUCCESS(1, "OK"),
    SYSTEM_EXCEPTION(98, "系统处理异常"),
    SERVICE_EXCEPTION(99, "业务处理异常"),
    ILLEGAL_REQUEST(100, "非法请求"),
    NO_RELATIONSHIP(101, "消息推送失败"),

    SCORE_LIMIT_MAX(103, "已达到积分上限"),
    IM_REQUEST_FAIL(104, "IM请求失败"),
    IM_REGIST_FAIL(105, "IM用户注册失败");

    private final int status;
    private final String message;
    ResponseCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public static boolean containValue(Integer value) {
        if (value == null) {
            return false;
        }
        for (ResponseCode code : ResponseCode.values()) {
            if(value.equals(code.getStatus())){
                return true;
            }
            continue;
        }
        return false;//错误的value
    }

    public static ResponseCode valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (ResponseCode code : ResponseCode.values()) {
            if(value.equals(code.getStatus())){
                return code;
            }
            continue;
        }
        return null;//错误的value
    }
}
