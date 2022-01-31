package seckilldemo.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import seckilldemo.mapper.SeckillOrderMapper;
import seckilldemo.pojo.SeckillOrder;
import seckilldemo.pojo.User;
import seckilldemo.service.SeckillOrderService;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @description: 获取秒杀结果
     * @param: user
     * @param: goodsId
     * @return: java.lang.Long orderId:成功 -1:失败 0:排队中
     * @author zhn
     * @date: 2022/1/30 21:02
     */
    @Override
    public Long getResult(User user, Long goodsId) {
        SeckillOrder seckillOrder = seckillOrderMapper.selectOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
                .eq("goods_id", goodsId));
        if (seckillOrder != null) {
            return seckillOrder.getOrderId();
        } else if (redisTemplate.hasKey("isStockEmpty:" + goodsId)) {
            return -1L;
        } else {
            return 0L;
        }
    }
}
