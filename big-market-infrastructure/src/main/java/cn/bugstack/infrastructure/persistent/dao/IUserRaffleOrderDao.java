package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.UserRaffleOrder;
import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 用户抽奖订单表
 * @create 2024-04-03 15:57
 */
@DBRouterStrategy(splitTable = true)
@Mapper
public interface IUserRaffleOrderDao {
// 向UserRaffleOrder表中插入数据
    void insert(UserRaffleOrder build);
    @DBRouter
    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrderReq);
}
