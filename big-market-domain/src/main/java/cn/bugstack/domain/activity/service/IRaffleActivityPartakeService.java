package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.bugstack.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @author luke
 * @date 2025年02月28日 16:27
 */
public interface IRaffleActivityPartakeService {

    UserRaffleOrderEntity createOrder(String userId, Long activityId);
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);
}
