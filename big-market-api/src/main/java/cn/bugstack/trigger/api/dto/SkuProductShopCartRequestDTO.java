package cn.bugstack.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luke
 * @date 2025年03月10日 15:02
 */
@Data
public class SkuProductShopCartRequestDTO implements Serializable {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * sku 商品
     */
    private Long sku;
}
