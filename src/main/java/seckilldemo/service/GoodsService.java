package seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import seckilldemo.pojo.Goods;
import seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
public interface GoodsService extends IService<Goods> {

    /**
     * @description: 获取商品列表
     * @param:
     * @return: java.util.List<seckilldemo.vo.GoodsVo>
     * @author zhn
     * @date: 2022/1/27 13:40
     */
    List<GoodsVo> findGoodsVo();

    /** 
     * @description: 获取商品详情
     * @param: goodsId 
     * @return: java.lang.String 
     * @author zhn
     * @date: 2022/1/27 17:15
     */ 
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
