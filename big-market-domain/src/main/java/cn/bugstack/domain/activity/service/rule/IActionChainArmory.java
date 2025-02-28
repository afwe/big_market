package cn.bugstack.domain.activity.service.rule;

/**
 * @author luke
 * @date 2025年02月27日 19:15
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);
}
