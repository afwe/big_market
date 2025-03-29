package cn.bugstack.trigger.api;

import cn.bugstack.trigger.api.dto.*;
import cn.bugstack.types.model.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luke
 * @date 2025年03月02日 13:07
 */
public interface IRaffleActivityService {
    Response<Boolean> armory(Long activityId);

    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

    Response<Boolean> calendarSignRebate(String userId);

    Response<Boolean> isCalendarSignRebate(String userId);
    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO request);


    Response<BigDecimal> queryUserCreditAccount(String userId);
    Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request);
    Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId);


}
