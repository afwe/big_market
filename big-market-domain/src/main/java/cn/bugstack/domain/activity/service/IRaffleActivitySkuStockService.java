package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.List;

/**
 * @author luke
 * @date 2025年02月28日 13:03
 */
public interface IRaffleActivitySkuStockService {
    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;
    ActivitySkuStockKeyVO takeQueueValue(Long sku) throws InterruptedException;

    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);

    List<Long> querySkuList();
}
