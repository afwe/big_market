package cn.bugstack.domain.strategy.service;

import java.util.Map;

/**
 * @author luke
 * @date 2025年03月02日 20:01
 */
public interface IRaffleRule {
    Map<String,Integer> queryAwardRuleLockCount(String[] treeIds);
}
