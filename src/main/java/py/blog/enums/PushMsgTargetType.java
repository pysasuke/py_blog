package py.blog.enums;

/**
 * Created by zczhao on 16/4/21.
 */
public enum PushMsgTargetType {
    MSG_LIST(1, "消息列表页"),
    ALBUM_DETAIL(2, "精选影集详情页"),
    CAMERA(3, "拍照界面"),
    BROWSER(4, "第三方浏览器"),
    FAMILY_SHARE(5, "家庭共享页"),
    SCORE_CENTER(6, "积分中心页");

    private final int value;
    private final String name;

    PushMsgTargetType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    public static boolean containValue(Integer value) {
        if (value == null) {
            return false;
        }
        for (PushMsgTargetType code : PushMsgTargetType.values()) {
            if(value.equals(code.getValue())){
                return true;
            }
            continue;
        }
        return false;//错误的value
    }

    public static PushMsgTargetType valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (PushMsgTargetType code : PushMsgTargetType.values()) {
            if(value.equals(code.getValue())){
                return code;
            }
            continue;
        }
        return null;//错误的value
    }
}
