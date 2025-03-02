package cn.bugstack.trigger.listener;

import cn.bugstack.types.event.BaseEvent;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author luke
 * @date 2025年03月02日 10:48
 */
@Slf4j
@Component
public class SendAwardListener {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;
    @RabbitListener(queuesToDeclare = @Queue(value = "send_award"))
    public void listener(String message){
        try{
            log.info("监听用户奖品发送消息 topic: {} message: {}", topic, message);
        }catch (Exception e){
            log.error("监听用户奖品发送消息，消费失败 topic: {} message: {}", topic, message);
            throw e;
        }
    }
}
