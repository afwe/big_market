package cn.bugstack.test.domain.strategy;

import cn.bugstack.domain.strategy.service.armory.IStrategyArmory;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author luke
 * @date 2025年02月24日 15:57
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IStrategyDispatch strategyDispatch;
    @Before
    public void test_strategyArmory(){
        strategyArmory.assembleLotteryStrategy(100001L);

    }

    @Test
    public void test_getAssembleRandomly(){

        log.info("随机抽奖4000：{}",strategyDispatch.getRandomAwardId(100001L,"4000:102,103,104,105"));
        log.info("随机抽奖5000：{}",strategyDispatch.getRandomAwardId(100001L,"5000:102,103,104,105,106,107"));
        log.info("随机抽奖6000：{}",strategyDispatch.getRandomAwardId(100001L,"6000:102,103,104,105,106,107,108,109"));
    }

    @Test
    public void test_getAssembleRandomly_ruleWeight(){

        log.info("随机抽奖：{}",strategyDispatch.getRandomAwardId(100001L));
        log.info("随机抽奖：{}",strategyDispatch.getRandomAwardId(100001L));
        log.info("随机抽奖：{}",strategyDispatch.getRandomAwardId(100001L));
    }
}
