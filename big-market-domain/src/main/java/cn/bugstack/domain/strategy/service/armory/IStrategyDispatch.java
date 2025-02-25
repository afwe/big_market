package cn.bugstack.domain.strategy.service.armory;

/**
 * @author luke
 * @date 2025年02月24日 16:37
 */
public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);
}
