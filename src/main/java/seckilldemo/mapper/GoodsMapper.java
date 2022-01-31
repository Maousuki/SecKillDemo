package seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import seckilldemo.pojo.Goods;
import seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * @description: 获取商品列表
     * @param:
     * @return: java.util.List<seckilldemo.vo.GoodsVo>
     * @author zhn
     * @date: 2022/1/27 17:50
     */
    List<GoodsVo> findGoodsVo();

    /**
     * @description: 获取商品详情
     * @param: goodsId
     * @return: seckilldemo.vo.GoodsVo
     * @author zhn
     * @date: 2022/1/27 17:50
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
