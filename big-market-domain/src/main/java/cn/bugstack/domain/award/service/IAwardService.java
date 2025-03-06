package cn.bugstack.domain.award.service;

import cn.bugstack.domain.award.model.entity.DistributeAwardEntity;
import cn.bugstack.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author luke
 * @date 2025年03月01日 10:00
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);

    void distributeAward(DistributeAwardEntity distributeAwardEntity);
}
