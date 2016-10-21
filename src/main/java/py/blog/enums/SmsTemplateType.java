package py.blog.enums;

/**
 * Created by zczhao on 15/8/7.
 * 。
 */
public enum SmsTemplateType {
    REGIST_CHECK(1, "SMS_7870292", "注册验证"),
    CHANGE_CHECK(2, "SMS_7870289", "变更验证"),
    IDENTITY_CHECK(3, "SMS_7870296", "身份验证"),
    FAMILY_INVITE(4, "SMS_8195150", "宝宝拍拍家庭邀请");


    private final Integer value;
    private final String name;
    private final String code;

    SmsTemplateType(Integer value, String code, String name) {
        this.value = value;
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }
    public String getName() {
        return name;
    }


    public static boolean containValue(Integer value) {
        if (value == null) {
            return false;
        }
        for (SmsTemplateType code : SmsTemplateType.values()) {
            if(value == code.getValue()){
                return true;
            }
            continue;
        }
        return false;//错误的value
    }

    public static SmsTemplateType getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SmsTemplateType code : SmsTemplateType.values()) {
            if(value == code.getValue()){
                return code;
            }
            continue;
        }
        return null;//错误的value
    }
}
