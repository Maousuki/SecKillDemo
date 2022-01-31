package seckilldemo.utils;

import java.util.UUID;

/**
 * @author zhn
 * @version 1.0
 * @description: UUID工具类
 * @date 2022/1/27 10:38
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
