package cn.bugstack.domain.activity.model.aggregate;

import cn.bugstack.domain.activity.model.entity.ActivityAccountDayEntity;
import cn.bugstack.domain.activity.model.entity.ActivityAccountEntity;
import cn.bugstack.domain.activity.model.entity.ActivityAccountMonthEntity;
import cn.bugstack.domain.activity.model.entity.UserRaffleOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luke
 * @date 2025年02月28日 16:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePartakeOrderAggregate {

    private String userId;
    private Long activityId;
    private ActivityAccountEntity activityAccountEntity;
    private boolean isExistAccountMonth = true;
    private ActivityAccountMonthEntity activityAccountMonthEntity;
    private boolean isExistAccountDay = true;
    private ActivityAccountDayEntity activityAccountDayEntity;
    private UserRaffleOrderEntity userRaffleOrderEntity;
}
