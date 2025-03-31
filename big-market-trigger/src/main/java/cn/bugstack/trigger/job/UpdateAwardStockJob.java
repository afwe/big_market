package cn.bugstack.trigger.job;

import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.bugstack.domain.strategy.service.IRaffleAward;
import cn.bugstack.domain.strategy.service.IRaffleStock;
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
 * @date 2025年02月26日 12:35
 */
@Slf4j
@Component()
public class UpdateAwardStockJob {

    @Resource
    private IRaffleStock raffleStock;
    @Resource
    private IRedisService redisService;
    @Resource
    private IRaffleAward raffleAward;
    @Resource
    private ThreadPoolExecutor executor;
//    @Scheduled(cron = "0/5 * * * * ?")
    @XxlJob("updateAwardStockJob")
    public void exec() {
        RLock lock = redisService.getLock("big-market-updateAwardStockJob");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(3, 0, TimeUnit.SECONDS);
            if (!isLocked) return;
            List<StrategyAwardStockKeyVO> strategyAwardStockKeyVOS = raffleAward.queryOpenActivityStrategyAwardList();
            if (null == strategyAwardStockKeyVOS) return;
            for (StrategyAwardStockKeyVO strategyAwardStockKeyVO : strategyAwardStockKeyVOS) {
                executor.execute(() -> {
                    try {
                        StrategyAwardStockKeyVO queueStrategyAwardStockKeyVO = raffleStock.takeQueueValue(strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
                        if (null == queueStrategyAwardStockKeyVO) return;
                        log.info("定时任务，更新奖品消耗库存 strategyId:{} awardId:{}", queueStrategyAwardStockKeyVO.getStrategyId(), queueStrategyAwardStockKeyVO.getAwardId());
                        raffleStock.updateStrategyAwardStock(queueStrategyAwardStockKeyVO.getStrategyId(), queueStrategyAwardStockKeyVO.getAwardId());
                    } catch (InterruptedException e) {
                        log.error("定时任务，更新奖品消耗库存失败 strategyId:{} awardId:{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
                    }
                });
            }
        } catch (Exception e) {
            log.error("定时任务，更新奖品消耗库存失败", e);
        } if (isLocked) {
            lock.unlock();
        }
    }
}
