package cn.bugstack.domain.strategy.service.rule.chain;

/**
 * @author luke
 * @date 2025年02月25日 16:19
 */
public abstract class AbstractLogicChain implements ILogicChain {

    private ILogicChain next;
    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    protected abstract String ruleModel();
}
