package seckilldemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seckilldemo.vo.RespBeanEnum;

/**
 * @author zhn
 * @version 1.0
 * @description: 全局异常
 * @date 2022/1/26 23:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {
    private RespBeanEnum respBeanEnum;
}
