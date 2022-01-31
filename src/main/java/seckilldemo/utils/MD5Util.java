package seckilldemo.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zhn
 * @version 1.0
 * @description: MD5工具类
 * @date 2022/1/26 12:39
 */
public class MD5Util {

    /**
     * @description: MD5密码加密
     * @param: src
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/26 12:54
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * @description: 用户的明文密码进行第一次加密
     * @param: inputPass 用户的明文密码
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/26 12:55
     */
    public static String inputPassToFromPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * @description: 从前端传来的第一次加密的密码进行第二次加密后存到数据库
     * @param: fromPass 第一次加密后的密码
     * @param: salt 盐值
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/26 12:55
     */
    public static String fromPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * @description: 从明文密码直接二次加密到数据库
     * @param: inputPass 明文密码
     * @param: salt 盐值
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/26 12:56
     */
    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFromPass("123456"));
        System.out.println(fromPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9","1a2b3c4d"));
    }
}
