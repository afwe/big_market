package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luke
 * @date 2025年02月24日 20:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    private Long strategyId;
    private Integer awardId;
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
}
