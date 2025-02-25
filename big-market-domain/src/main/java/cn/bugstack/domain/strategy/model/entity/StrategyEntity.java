package cn.bugstack.domain.strategy.model.entity;

import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;


/**
 * @author luke
 * @date 2025年02月24日 16:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {
    private Long strategyId;
    /** 奖品配置信息*/
    private String strategyDesc;
    /** 抽奖规则*/
    private String ruleModels;
    public String[] ruleModels(){
        if(StringUtils.isBlank(this.ruleModels)){
            return null;
        }
        return this.ruleModels.split(Constants.SPLIT);
    }
    public String getRuleWeight(){
        String[] ruleModels = this.ruleModels();
        if(null == ruleModels){
            return null;
        }
        for(String ruleModel: ruleModels){
            if("rule_weight".equals(ruleModel)){
                return ruleModel;
            }
        }
        return null;

    }
}
