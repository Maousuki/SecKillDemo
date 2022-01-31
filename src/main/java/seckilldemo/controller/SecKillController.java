package seckilldemo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import seckilldemo.config.AccessLimit;
import seckilldemo.pojo.SeckillMessage;
import seckilldemo.pojo.SeckillOrder;
import seckilldemo.pojo.User;
import seckilldemo.rabbitmq.MQSender;
import seckilldemo.service.GoodsService;
import seckilldemo.service.OrderService;
import seckilldemo.service.SeckillOrderService;
import seckilldemo.vo.GoodsVo;
import seckilldemo.vo.RespBean;
import seckilldemo.vo.RespBeanEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * windows优化前QPS：1516
 * linux优化前QPS：603
 * windows缓存后QPS：2669
 * 优化后QPS：2301
 * @author zhn
 * @version 1.0
 * @description: 秒杀
 * @date 2022/1/27 22:37
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MQSender mqSender;

    private Map<Long,Boolean> EmptyStockMap = new HashMap<>();

    /**
     * @description: 秒杀
     * @param: model
     * @param:user
     * @param:goodsId
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/27 22:41
     */
    @RequestMapping(value = "/{path}/doSeckill",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSecKill(@PathVariable String path, User user, Long goodsId) {
        if (null == user) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        boolean check = orderService.checkPath(user,goodsId,path);
        if (!check) {
            return RespBean.error(RespBeanEnum.REQUEST_ILLEGAL);
        }

        //判断是否重复抢购
        SeckillOrder secKillOrder = (SeckillOrder) valueOperations.get("order:" + user.getId() + ":" + goodsId);
        if (null != secKillOrder) {
            return RespBean.error(RespBeanEnum.REPEAT_ERROR);
        }
        //内存标记，减少Redis访问
        if (EmptyStockMap.get(goodsId)) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //预减库存
        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        if (stock < 0) {
            EmptyStockMap.put(goodsId,true);
            valueOperations.increment("seckillGoods:" + goodsId); //只有0件商品时，此时为-1，加1变为0
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        SeckillMessage seckillMessage = new SeckillMessage(user, goodsId);
        mqSender.sendSeckillMessage(JSONObject.toJSONString(seckillMessage));
        return RespBean.success(0);
    }

    /** 
     * @description: 获取秒杀结果
     * @param: user
     * @param: goodsId
     * @return: seckilldemo.vo.RespBean 
     * @author zhn
     * @date: 2022/1/30 21:01
     */ 
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public RespBean getResult(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        Long orderId = seckillOrderService.getResult(user, goodsId);
        return RespBean.success(orderId);
    }

    /**
     * @description: 获取秒杀地址
     * @param: user
     * @param: goodsId
     * @return: seckilldemo.vo.RespBean
     * @author zhn
     * @date: 2022/1/31 11:34
     */
    @AccessLimit(second = 5, maxCount = 5)
    @RequestMapping(value = "/path",method = RequestMethod.GET)
    @ResponseBody
    public RespBean getPath(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        String str = orderService.createPath(user,goodsId);
        return RespBean.success(str);
    }

    /** 
     * @description: 系统初始化，把商品数量加载到redis里
     * @param:
     * @return: void 
     * @author zhn
     * @date: 2022/1/30 11:53
     */ 
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:" + goodsVo.getId(),goodsVo.getStockCount());
            EmptyStockMap.put(goodsVo.getId(),false);
        }
        );
    }
}
