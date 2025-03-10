package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.*;

/**
 * @author luke
 * @date 2025年02月27日 16:55
 */
public interface IRaffleActivityAccountQuotaService {
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);

    String createOrder(SkuRechargeEntity skuRechargeEntity);

    void updateOrder(DeliveryOrderEntity deliveryOrderEntity);

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);

    ActivityAccountEntity queryActivityAccountEntity(Long activityId, String userId);

    Integer queryRaffleActivityAccountPartakeCount(Long activityId, String userId);
}
