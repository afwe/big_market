package cn.bugstack.domain.rebate.service;

import cn.bugstack.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @author luke
 * @date 2025年03月05日 10:48
 */
public interface IBehaviorRebateService {
    List<String> createOrder(BehaviorEntity behaviorEntity);
}
