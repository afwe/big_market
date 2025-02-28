package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luke
 * @date 2025年02月28日 12:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockKeyVO {
    private Long sku;
    private Long activityId;
}
