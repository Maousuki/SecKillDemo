package seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import seckilldemo.pojo.Order;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhn
 * @since 2022-01-27
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
