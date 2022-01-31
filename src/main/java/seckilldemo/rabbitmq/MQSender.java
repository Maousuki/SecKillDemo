package seckilldemo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhn
 * @version 1.0
 * @description: 消息发送者
 * @date 2022/1/30 20:25
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /** 
     * @description: 发送秒杀信息
     * @param: msg 
     * @return: void 
     * @author zhn
     * @date: 2022/1/30 20:27
     */ 
    public void sendSeckillMessage(String msg) {
        log.info("发送消息：" + msg);
        rabbitTemplate.convertAndSend("seckillExchange","seckill.message",msg);
    }
}
