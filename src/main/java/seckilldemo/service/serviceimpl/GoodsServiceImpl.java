package seckilldemo.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seckilldemo.mapper.GoodsMapper;
import seckilldemo.pojo.Goods;
import seckilldemo.service.GoodsService;
import seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * @description: 获取商品列表
     * @param:
     * @return: java.util.List<seckilldemo.vo.GoodsVo>
     * @author zhn
     * @date: 2022/1/27 13:40
     */
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    /**
     * @description: 获取商品详情
     * @param: goodsId
     * @return: java.lang.String
     * @author zhn
     * @date: 2022/1/27 17:17
     */
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }


}
