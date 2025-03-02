package cn.bugstack.domain.strategy.service.armory;

import java.util.Date;

/**
 * @author luke
 * @date 2025年02月24日 16:37
 */
public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);

    Boolean subtractionAwardStock(Long strategyId, Integer awardId,  Date endDateTime);
}
