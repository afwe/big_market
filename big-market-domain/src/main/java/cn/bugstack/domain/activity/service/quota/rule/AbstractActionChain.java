package cn.bugstack.domain.activity.service.quota.rule;

/**
 * @author luke
 * @date 2025年02月27日 19:16
 */
public abstract class AbstractActionChain implements IActionChain{
    private IActionChain next;

    @Override
    public IActionChain next() {

        return next;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }
}
