package cn.bugstack.domain.strategy.service.rule.tree.factory.engine;

import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

import java.util.Date;

/**
 * @author luke
 * @date 2025年02月25日 19:39
 */
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId, Date endDateTime);
}
