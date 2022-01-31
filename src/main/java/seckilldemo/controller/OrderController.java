package seckilldemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seckilldemo.pojo.User;
import seckilldemo.service.OrderService;
import seckilldemo.vo.OrderDetailVo;
import seckilldemo.vo.RespBean;
import seckilldemo.vo.RespBeanEnum;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /** 
     * @description: 订单详情
     * @param: user
     * @param: orderId
     * @return: seckilldemo.vo.RespBean 
     * @author zhn
     * @date: 2022/1/29 22:52
     */ 
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }

}
