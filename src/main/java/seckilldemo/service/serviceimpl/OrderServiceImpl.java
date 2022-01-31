package seckilldemo.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seckilldemo.exception.GlobalException;
import seckilldemo.mapper.OrderMapper;
import seckilldemo.mapper.SeckillOrderMapper;
import seckilldemo.pojo.Order;
import seckilldemo.pojo.SeckillGoods;
import seckilldemo.pojo.SeckillOrder;
import seckilldemo.pojo.User;
import seckilldemo.service.GoodsService;
import seckilldemo.service.OrderService;
import seckilldemo.service.SeckillGoodsService;
import seckilldemo.utils.MD5Util;
import seckilldemo.utils.UUIDUtil;
import seckilldemo.vo.GoodsVo;
import seckilldemo.vo.OrderDetailVo;
import seckilldemo.vo.RespBeanEnum;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @description: 秒杀
     * @param: user
     * @param: goodsVo
     * @return: seckilldemo.pojo.Order
     * @author zhn
     * @date: 2022/1/29 23:22
     */
    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goodsVo) {
        //秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        boolean result = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>().setSql("stock_count = stock_count - 1")
                .eq("goods_id", goodsVo.getId()).gt("stock_count", 0));
        if (goodsVo.getStockCount() < 1) {
            redisTemplate.opsForValue().set("isStockEmpty:" + goodsVo.getId(),"0");
            return null;
        }
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVo.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0); //创建未支付
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrderMapper.insert(seckillOrder);
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goodsVo.getId(), seckillOrder);
        return order;
    }

    /**
     * @description: 订单详情
     * @param: orderId
     * @return: seckilldemo.vo.OrderDetailVo
     * @author zhn
     * @date: 2022/1/29 22:57
     */
    @Override
    public OrderDetailVo detail(Long orderId) {
        if (orderId == null) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIT);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detail = new OrderDetailVo();
        detail.setOrder(order);
        detail.setGoodsVo(goodsVo);
        return detail;
    }

    /**
     * @description: 获取秒杀地址
     * @param: user
     * @param: goodsId
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/31 11:36
     */
    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisTemplate.opsForValue().set("seckillPath:" + user.getId() + ":" + goodsId,str,
                60, TimeUnit.SECONDS);
        return str;
    }

    /**
     * @description: 校验秒杀地址
     * @param: user
     * @param: goodsId
     * @return: boolean
     * @author zhn
     * @date: 2022/1/31 11:45
     */
    @Override
    public boolean checkPath(User user, Long goodsId, String path) {
        if (user == null || goodsId < 0 || StringUtils.isEmpty(path)) {
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("seckillPath:" + user.getId() + ":" + goodsId);
        return path.equals(redisPath);
    }
}
