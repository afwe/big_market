package cn.bugstack.domain.strategy.model.valobj;

import lombok.*;

/**
 * @author luke
 * @date 2025年02月26日 12:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyAwardStockKeyVO {
    private Long strategyId;
    private Integer awardId;
}
