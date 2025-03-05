package cn.bugstack.domain.rebate.model.entity;

import cn.bugstack.domain.rebate.model.valobj.BehaviorTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luke
 * @date 2025年03月05日 10:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorEntity {
    private String userId;
    private BehaviorTypeVO behaviorTypeVO;
    private String outBusinessNo;
}
