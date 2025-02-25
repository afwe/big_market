package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author luke
 * @date 2025年02月24日 19:59
 */
public interface IRaffleStrategy {
    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
