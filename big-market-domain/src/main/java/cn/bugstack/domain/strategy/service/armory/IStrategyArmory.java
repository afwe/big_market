package cn.bugstack.domain.strategy.service.armory;

/**
 * @author luke
 * @date 2025年02月24日 15:05
 */
public interface IStrategyArmory {
    boolean assembleLotteryStrategyByActivityId(Long activityId);
    boolean assembleLotteryStrategy(Long StrategyId);

}
