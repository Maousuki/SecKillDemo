package seckilldemo.config;

import seckilldemo.pojo.User;

/**
 * @author zhn
 * @version 1.0
 * @description: 当前线程存放用户
 * @date 2022/1/31 16:03
 */
public class UserContext {
    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }
}
