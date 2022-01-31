package seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import seckilldemo.pojo.Order;
import seckilldemo.pojo.User;
import seckilldemo.vo.GoodsVo;
import seckilldemo.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
public interface OrderService extends IService<Order> {

    /** 
     * @description: 秒杀
     * @param: user
     * @param: goodsVo
     * @return: seckilldemo.pojo.Order 
     * @author zhn
     * @date: 2022/1/29 23:22
     */ 
    Order seckill(User user, GoodsVo goodsVo);
    
    /** 
     * @description: 订单详情
     * @param: orderId 
     * @return: seckilldemo.vo.OrderDetailVo 
     * @author zhn
     * @date: 2022/1/29 22:55
     */ 
    OrderDetailVo detail(Long orderId);

    /** 
     * @description: 获取秒杀地址
     * @param: user
     * @param: goodsId
     * @return: java.lang.String 
     * @author zhn
     * @date: 2022/1/31 11:35
     */ 
    String createPath(User user, Long goodsId);

    /**
     * @description: 校验秒杀地址
     * @param: user
     * @param: goodsId
     * @return: boolean
     * @author zhn
     * @date: 2022/1/31 11:44
     */
    boolean checkPath(User user, Long goodsId, String path);
}
