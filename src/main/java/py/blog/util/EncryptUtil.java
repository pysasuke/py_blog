package py.blog.util;

import java.util.Calendar;

/**
 * Created by zczhao on 16/6/14.
 */
public class EncryptUtil {
    private static final int NUMBER_FOR_UNIQUE = 2222;  //一旦使用, 不能修改!否则第三方账号数据会乱掉.

    public static long camouflage(long number) {
        int millisecond = Calendar.getInstance().get(Calendar.MILLISECOND);

        long result = (number + millisecond) * 1000 + millisecond;
        return result;
    }

    public static long takeOffCamouflage(long number) {
        long result = number / 1000 - number % 1000;
        return result;
    }

    public static long camouflageUnique(long number) {
        return number + NUMBER_FOR_UNIQUE;
    }

    public static long takeOffCamouflageUnique(long number) {
        return number - NUMBER_FOR_UNIQUE;
    }

    public static void main(String[] args) {
        long temp = EncryptUtil.camouflageUnique(1289041424L);
        long source = EncryptUtil.takeOffCamouflageUnique(temp);
        System.out.println(source);
    }
}
