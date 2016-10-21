package py.blog.enums;

/**
 * Created by zczhao on 2015-9-14.
 * SQL选项。
 */
public enum SQLOption {
    EQUAL(0, "="),
    GREAT_THAN(1, ">"),
    LESS_THAN(2, "<"),
    GREAT_EQUAL(3, ">="),
    LESS_EQUAL(4, "<="),
    ADD(5, "增加"),
    DEDUCT(6, "减少"),
    NOT_EQUAL(7, "!=");


    private final int value;
    private final String name;

    SQLOption(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public static SQLOption valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (SQLOption os : SQLOption.values()) {
            if (value == os.getValue()) {
                return os;
            }
            continue;
        }
        return null;//错误的value
    }

    public static SQLOption nameOf(String name) {
        if (name == null) {
            return null;
        }
        for (SQLOption os : SQLOption.values()) {
            if (name.equals(os.getName())) {
                return os;
            }
            continue;
        }
        return null;//错误的value
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
