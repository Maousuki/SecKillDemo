package seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import seckilldemo.pojo.Order;
import seckilldemo.pojo.SeckillOrder;
import seckilldemo.pojo.User;
import seckilldemo.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
public interface SeckillOrderService extends IService<SeckillOrder> {

    /** 
     * @description: 获取秒杀结果
     * @param: user
     * @param: goodsId
     * @return: java.lang.Long orderId:成功 -1:失败 0:排队中
     * @author zhn
     * @date: 2022/1/30 21:02
     */ 
    Long getResult(User user, Long goodsId);
}
