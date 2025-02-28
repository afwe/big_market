package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.valobj.ActivitySkuStockKeyVO;

/**
 * @author luke
 * @date 2025年02月28日 13:03
 */
public interface ISkuStock {
    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;
    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);

}
