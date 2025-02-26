package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.infrastructure.persistent.po.Award;
import cn.bugstack.infrastructure.persistent.po.Strategy;
import cn.bugstack.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author luke
 * @date 2025年02月23日 20:10
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

    String queryStrategyAwardRuleModel(StrategyAward strategyAward);

    void updateStrategyAwardStock(StrategyAward strategyAward);
}
