package seckilldemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seckilldemo.service.UserService;
import seckilldemo.vo.LoginVo;
import seckilldemo.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zhn
 * @version 1.0
 * @description: 登录
 * @date 2022/1/26 14:23
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @description: 登录跳转
     * @param:
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/26 14:25
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /** 
     * @description: 登录功能
     * @param: loginVo 
     * @return: seckilldemo.vo.RespBean 
     * @author zhn
     * @date: 2022/1/26 17:23
     */ 
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        return userService.doLogin(loginVo, request, response);
    }
}
