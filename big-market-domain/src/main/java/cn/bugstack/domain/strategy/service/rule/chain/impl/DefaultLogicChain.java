package cn.bugstack.domain.strategy.service.rule.chain.impl;

import cn.bugstack.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author luke
 * @date 2025年02月25日 16:22
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {
    @Override
    public Integer logic(String userId, Long strategyId) {
        return 0;
    }
}
