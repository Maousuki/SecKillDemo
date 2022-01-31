package seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import seckilldemo.pojo.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhn
 * @since 2022-01-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
