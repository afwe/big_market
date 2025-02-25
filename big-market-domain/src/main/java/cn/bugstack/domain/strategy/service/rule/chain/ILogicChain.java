package cn.bugstack.domain.strategy.service.rule.chain;

/**
 * @author luke
 * @date 2025年02月25日 16:17
 */
public interface ILogicChain {
    Integer logic(String userId, Long strategyId);
    ILogicChain appendNext(ILogicChain chain);
    ILogicChain next();
}
