package cn.bugstack.domain.activity.service.quota.policy;

import cn.bugstack.domain.activity.model.aggregate.CreateQuotaOrderAggregate;

/**
 * @author luke
 * @date 2025年03月07日 13:46
 */
public interface ITradePolicy {
    void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate);
}
