package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.UserAwardRecord;
import cn.bugstack.infrastructure.persistent.po.UserCreditAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 用户积分账户
 * @create 2024-05-24 21:11
 */
@Mapper
public interface IUserCreditAccountDao {

    void insert(UserCreditAccount userCreditAccountReq);

    int updateAddAmount(UserCreditAccount userCreditAccountReq);

// 根据用户信用账户请求查询用户信用账户
    UserCreditAccount queryUserCreditAccount(UserCreditAccount userCreditAccountReq);
}
