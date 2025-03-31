package cn.bugstack.trigger.job;

import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.bugstack.domain.strategy.service.IRaffleStock;
import cn.bugstack.domain.task.model.entity.TaskEntity;
import cn.bugstack.domain.task.service.ITaskService;
import cn.bugstack.infrastructure.persistent.redis.IRedisService;
import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luke
 * @date 2025年03月01日 21:09
 */
@Slf4j
@Component()
public class SendMessageTaskJob {

    @Resource
    private ITaskService taskService;
    @Resource
    private ThreadPoolExecutor executor;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private IRedisService redisService;

//    @Scheduled(cron = "0/5 * * * * ?")
    @XxlJob("SendMessageTaskJob_DB1")
    public void exec(){
        RLock lock = redisService.getLock("SendMessageTaskJob_DB1");
        boolean isLocked = false;
        try{
            isLocked = lock.tryLock(3,0, TimeUnit.SECONDS);
            if(!isLocked) return;
            int dbCount = dbRouter.dbCount();
            for(int dbIdx = 1;dbIdx<=dbCount;dbIdx++){
                int finalDbIdx = dbIdx;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            dbRouter.setDBKey(finalDbIdx);
                            dbRouter.setTBKey(0);
                            List<TaskEntity> taskEntities = taskService.querySendMessageTaskList();
                            if(taskEntities.isEmpty()){
                                return;
                            }
                            for(TaskEntity taskEntity: taskEntities){
                                executor.execute(()->{
                                   try{
                                       taskService.sendMessage(taskEntity);
                                       taskService.updateTaskSendMessage(taskEntity.getUserId(),taskEntity.getMessageId());
                                   } catch(Exception e){
                                       log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                                       taskService.updateTaskSendMessageFail(taskEntity.getUserId(),taskEntity.getMessageId());
                                   }
                                });
                            }
                        } finally{
                            dbRouter.clear();
                        }
                    }
                });
            }

        } catch(Exception e){
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        }finally {
            dbRouter.clear();
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
