package cn.bugstack.domain.activity.service.armory;

/**
 * @author luke
 * @date 2025年02月28日 12:15
 */
public interface IActivityArmory {
    boolean assembleActivitySku(Long sku);

    boolean assembleActivitySkuByActivityId(Long activityId);
}
