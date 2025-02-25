package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author luke
 * @date 2025年02月24日 15:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {
    private Long strategyId;
    /** 抽奖奖品标题*/
    private Integer awardId;
    /** 奖品库存总量*/
    private Integer awardCount;
    /** 奖品库存剩余*/
    private Integer awardCountSurplus;
    /** 奖品中奖概率*/
    private BigDecimal awardRate;
}
