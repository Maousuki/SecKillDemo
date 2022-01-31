package seckilldemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seckilldemo.pojo.Order;

/**
 * @author zhn
 * @version 1.0
 * @description: TODO
 * @date 2022/1/29 22:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    private Order order;
    private GoodsVo goodsVo;
}
