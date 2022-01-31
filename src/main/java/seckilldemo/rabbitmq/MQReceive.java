package seckilldemo.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import seckilldemo.pojo.SeckillMessage;
import seckilldemo.pojo.SeckillOrder;
import seckilldemo.pojo.User;
import seckilldemo.service.GoodsService;
import seckilldemo.service.OrderService;
import seckilldemo.vo.GoodsVo;

/**
 * @author zhn
 * @version 1.0
 * @description: 消息接收者
 * @date 2022/1/30 20:25
 */
@Service
@Slf4j
public class MQReceive {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    /**
     * @description: 下单操作
     * @param: msg
     * @return: void
     * @author zhn
     * @date: 2022/1/30 20:32
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String msg) {
        log.info("接受到的消息：" + msg);
        SeckillMessage seckillMessage = (SeckillMessage) JSONObject.parseObject(msg,SeckillMessage.class);
        User user = seckillMessage.getUser();
        Long goodsId = seckillMessage.getGoodsId();
        //判断库存
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        if (goodsVo.getStockCount() < 1) {
            return;
        }
        //判断是否重复抢购
        SeckillOrder secKillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (null != secKillOrder) {
            return;
        }
        orderService.seckill(user,goodsVo);
    }
}
