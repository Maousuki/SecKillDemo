package seckilldemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seckilldemo.pojo.Goods;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhn
 * @version 1.0
 * @description: 商品返回对象
 * @date 2022/1/27 13:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {

    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
