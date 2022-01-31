package seckilldemo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import seckilldemo.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author zhn
 * @version 1.0
 * @description: 登录参数
 * @date 2022/1/26 17:20
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
