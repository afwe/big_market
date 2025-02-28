package cn.bugstack.domain.activity.service.armory;

import java.util.Date;

/**
 * @author luke
 * @date 2025年02月28日 12:27
 */
public interface IActivityDispatch {
    boolean substractActivitySkuStock(Long sku, Date endDateTime);
}
