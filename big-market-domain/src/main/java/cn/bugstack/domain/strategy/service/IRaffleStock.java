package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

/**
 * @author luke
 * @date 2025年02月26日 12:33
 */
public interface IRaffleStock {
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    StrategyAwardStockKeyVO takeQueueValue(Long strategyId, Integer awardId) throws InterruptedException;


    void updateStrategyAwardStock(Long strategyId, Integer awardId);


}
