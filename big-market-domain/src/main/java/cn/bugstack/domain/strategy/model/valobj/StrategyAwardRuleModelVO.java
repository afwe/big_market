package cn.bugstack.domain.strategy.model.valobj;

import cn.bugstack.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luke
 * @date 2025年02月25日 14:44
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyAwardRuleModelVO {
    private String ruleModels;
    public String[] raffleCenterRuleModelList(){
        List<String> ruleModelList = new ArrayList<>();
        String[] ruleModelValueList = ruleModels.split(Constants.SPLIT);
        for(String ruleModelValue : ruleModelValueList){
            if(DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)){
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }
}
