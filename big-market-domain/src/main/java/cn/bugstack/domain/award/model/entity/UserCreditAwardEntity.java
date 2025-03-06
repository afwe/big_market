package cn.bugstack.domain.award.model.entity;

import cn.bugstack.domain.award.model.valobj.AwardStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luke
 * @date 2025年03月06日 13:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreditAwardEntity {
    /** 用户ID */
    private String userId;
    /** 积分值 */
    private BigDecimal creditAmount;
}
