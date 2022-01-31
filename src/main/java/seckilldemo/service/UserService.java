package seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import seckilldemo.pojo.User;
import seckilldemo.vo.LoginVo;
import seckilldemo.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhn
 * @since 2022-01-26
 */
public interface UserService extends IService<User> {

    /** 
     * @description: 登录
     * @param: loginVo 
     * @return: seckilldemo.vo.RespBean 
     * @author zhn
     * @date: 2022/1/26 17:29
     */ 
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /** 
     * @description: 根据cookie获取用户
     * @param: userTicket 
     * @return: seckilldemo.pojo.User 
     * @author zhn
     * @date: 2022/1/27 12:29
     */ 
    User getUserByCookie(String userTicket);
}
