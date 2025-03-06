package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.UserCreditOrder;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luke
 * @date 2025年03月06日 16:41
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserCreditOrderDao {
    void insert(UserCreditOrder userCreditOrder);
}
