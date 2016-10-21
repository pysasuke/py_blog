package py.blog.enums;

/**
 * Created by Administrator on 2016/8/19.
 */
 public enum EntityType {
        PICTURE(1, "图片"),
        VIDEO(2, "视频"),
        ALBUM(3, "图记");


        private final int value;
        private final String name;
        EntityType(int value, String name) {
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
            for (EntityType code : EntityType.values()) {
                if(value.equals(code.getValue())){
                    return true;
                }
                continue;
            }
            return false;//错误的value
        }

        public static EntityType valueOf(Integer value) {
            if (value == null) {
                return null;
            }
            for (EntityType code : EntityType.values()) {
                if(value.equals(code.getValue())){
                    return code;
                }
                continue;
            }
            return null;//错误的value
        }

}
