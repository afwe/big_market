package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.ActivityOrderEntity;
import cn.bugstack.domain.activity.model.entity.ActivityShopCartEntity;
import cn.bugstack.domain.activity.model.entity.SkuRechargeEntity;

/**
 * @author luke
 * @date 2025年02月27日 16:55
 */
public interface IRaffleActivityAccountQuotaService {
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);

    String createOrder(SkuRechargeEntity skuRechargeEntity);

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);
}
