package cn.bugstack.domain.award.repository;

import cn.bugstack.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author luke
 * @date 2025年03月01日 10:14
 */
public interface IAwardRepository {


    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
