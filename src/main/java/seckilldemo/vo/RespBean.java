package seckilldemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhn
 * @version 1.0
 * @description: 公共返回对象
 * @date 2022/1/26 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /** 
     * @description: 成功返回结果
     * @param:
     * @return: seckilldemo.vo.RespBean 
     * @author zhn
     * @date: 2022/1/26 17:14
     */ 
    public static RespBean success() {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * @description: 成功返回结果
     * @param: obj
     * @return: seckilldemo.vo.RespBean
     * @author zhn
     * @date: 2022/1/26 17:14
     */
    public static RespBean success(Object obj) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), obj);
    }

    /**
     * @description: 失败返回结果
     * @param: respBeanEnum
     * @return: seckilldemo.vo.RespBean
     * @author zhn
     * @date: 2022/1/26 17:14
     */
    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMessage(),null);
    }

    /**
     * @description: 失败返回结果
     * @param: respBeanEnum
     * @return: seckilldemo.vo.RespBean
     * @author zhn
     * @date: 2022/1/26 17:14
     */
    public static RespBean error(RespBeanEnum respBeanEnum, Object obj) {
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMessage(),obj);
    }

}
