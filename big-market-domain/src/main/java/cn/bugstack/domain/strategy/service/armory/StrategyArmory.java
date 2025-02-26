package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.types.common.Constants;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author luke
 * @date 2025年02月24日 15:06
 */
@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory, IStrategyDispatch{

    @Resource
    private IStrategyRepository strategyRepository;
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        List<StrategyAwardEntity> strategyAwardEntities = strategyRepository.queryStrategyAwardList(strategyId);
        for(StrategyAwardEntity strategyAwardEntity : strategyAwardEntities){
            Integer awardId = strategyAwardEntity.getAwardId();
            Integer awardCount = strategyAwardEntity.getAwardCount();
            cacheStrategyAwardCount(strategyId, awardId, awardCount);
        }

        //全量抽奖
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntities);
        StrategyEntity strategyEntity = strategyRepository.queryStrateguEntitiesByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if(null==ruleWeight){
            return true;
        }
        StrategyRuleEntity strategyRuleEntity = strategyRepository.queryStrategyRule(strategyId,ruleWeight);
        if(null==strategyRuleEntity){
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(),ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }
        Map<String, List<Integer>> ruleWeightValues = strategyRuleEntity.getRuleWeightValues();
        Set<String> keys = ruleWeightValues.keySet();
        for(String key:keys){
            List<Integer> ruleValues = ruleWeightValues.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity->!ruleValues.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat(key),strategyAwardEntitiesClone);
        }
        return true;

    }

    private void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId+Constants.UNDERLINE+awardId;
        strategyRepository.cacheStrategyAwardCount(cacheKey,awardCount);
    }

    private void assembleLotteryStrategy(String strategyId,List<StrategyAwardEntity> strategyAwardEntities){
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        BigDecimal total = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal rateRange = total.divide(minAwardRate,0, RoundingMode.CEILING);
        ArrayList<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward : strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for(int i=0;i < rateRange.multiply(awardRate).setScale(0,RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        Collections.shuffle(strategyAwardSearchRateTables);
        HashMap<Integer,Integer> shufflestrategyAwardSearchRateTables = new HashMap<>();
        for(int i=0;i<strategyAwardSearchRateTables.size();i++){
            shufflestrategyAwardSearchRateTables.put(i,strategyAwardSearchRateTables.get(i));
        }
        strategyRepository.storeStrategyAwardSearchRateTable(strategyId,shufflestrategyAwardSearchRateTables.size(),shufflestrategyAwardSearchRateTables);
    }
    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int randomRange = strategyRepository.getRandomRange(strategyId);
        return strategyRepository.getStrategyAwardAssemble(String.valueOf(strategyId),new SecureRandom().nextInt(randomRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        int randomRange = strategyRepository.getRandomRange(key);
        return strategyRepository.getStrategyAwardAssemble(key,new SecureRandom().nextInt(randomRange));
    }

    @Override
    public Boolean subtractionAwardStock(Long strategyId, Integer awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId+Constants.UNDERLINE+awardId;
        return strategyRepository.subtractionAwardStock(cacheKey);
    }
}
