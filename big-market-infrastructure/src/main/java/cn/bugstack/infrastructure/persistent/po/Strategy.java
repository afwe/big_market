package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author luke
 * @date 2025年02月23日 16:29
 */
@Data
public class Strategy {
    /** 抽奖奖品ID*/
    private Long id;
    /** 奖品对接标识*/
    private Long strategyId;
    /** 奖品配置信息*/
    private String strategyDesc;
    /** 抽奖规则*/
    private String ruleModels;
    /** 奖品内容描述*/
    private Date createTime;
    /** 创建时间*/
    private Date updateTime;
}
