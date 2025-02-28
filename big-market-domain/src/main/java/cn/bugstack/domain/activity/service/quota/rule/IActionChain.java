package cn.bugstack.domain.activity.service.quota.rule;

import cn.bugstack.domain.activity.model.entity.ActivityCountEntity;
import cn.bugstack.domain.activity.model.entity.ActivityEntity;
import cn.bugstack.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author luke
 * @date 2025年02月27日 19:14
 */
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
