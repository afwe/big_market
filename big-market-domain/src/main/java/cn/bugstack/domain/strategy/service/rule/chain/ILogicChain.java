package cn.bugstack.domain.strategy.service.rule.chain;

import cn.bugstack.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @author luke
 * @date 2025年02月25日 16:17
 */
public interface ILogicChain extends IlogicChainArmory, Cloneable{
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);

}
