package cn.bugstack.domain.strategy.service.rule.chain;

/**
 * @author luke
 * @date 2025年02月25日 17:01
 */
public interface IlogicChainArmory {
    ILogicChain appendNext(ILogicChain chain);
    ILogicChain next();
}
