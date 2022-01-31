package seckilldemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhn
 * @version 1.0
 * @description: 公共返回对象枚举
 * @date 2022/1/26 17:07
 */
@ToString
@Getter
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    SESSION_ERROR(500213,"用户不存在"),
    //秒杀模块
    EMPTY_STOCK(500500,"库存不足"),
    REPEAT_ERROR(500501,"该商品每人限购一件"),
    REQUEST_ILLEGAL(500502,"请求非法，请重新尝试"),
    ACCESS_LIMIT_REACHED(500503,"请求过于频繁，请稍后再试！"),
    //订单模块
    ORDER_NOT_EXIT(500300,"订单信息不存在"),
    ;
    private final Integer code;
    private final String message;
}
