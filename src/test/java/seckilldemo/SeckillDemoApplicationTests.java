package seckilldemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seckilldemo.mapper.GoodsMapper;
import seckilldemo.vo.GoodsVo;

import java.util.List;

@SpringBootTest
@Slf4j
class SeckillDemoApplicationTests {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
        log.info("hello");
    }

}
