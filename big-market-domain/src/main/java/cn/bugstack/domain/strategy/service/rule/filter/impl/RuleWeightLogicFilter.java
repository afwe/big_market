package cn.bugstack.domain.strategy.service.rule.filter.impl;

import cn.bugstack.domain.strategy.model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy.model.entity.RuleMatterEntity;
import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.annotation.LogicStrategy;
import cn.bugstack.domain.strategy.service.rule.filter.ILogicFilter;
import cn.bugstack.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author luke
 * @date 2025年02月24日 20:55
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_WIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {
    @Resource
    private IStrategyRepository strategyRepository;
    private Long userScore = 4500L;
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
//     rulevalue   4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
        log.info("规则过滤-权重userId:{} strategyId:{} ruleModel:{}",ruleMatterEntity.getUserId(),ruleMatterEntity.getStrategyId(),ruleMatterEntity.getRuleModel());
        String userId = ruleMatterEntity.getUserId();
        String ruleValue = strategyRepository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(),ruleMatterEntity.getAwardId(),ruleMatterEntity.getRuleModel());
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if(null==analyticalValueGroup||analyticalValueGroup.isEmpty()){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) (o2-o1);
            }
        });
        Long nextValue = analyticalSortedKeys.stream()
                .filter(key->userScore>=key)
                .findFirst()
                .orElse(null);
        if(null!=nextValue){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode())
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(ruleMatterEntity.getStrategyId())
                            .ruleWeightValueKey(analyticalValueGroup.get(nextValue)).build()
                    )
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

    private Map<Long, String> getAnalyticalValue(String ruleValue) {
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for (String ruleValueKey : ruleValueGroups) {
            // 检查输入是否为空
            if (ruleValueKey == null || ruleValueKey.isEmpty()) {
                return ruleValueMap;
            }
            // 分割字符串以获取键和值
            String[] parts = ruleValueKey.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]), ruleValueKey);
        }
        return ruleValueMap;
    }
}
