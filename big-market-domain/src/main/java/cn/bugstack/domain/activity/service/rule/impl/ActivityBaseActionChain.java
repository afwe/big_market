package cn.bugstack.domain.activity.service.rule.impl;

import cn.bugstack.domain.activity.model.entity.ActivityCountEntity;
import cn.bugstack.domain.activity.model.entity.ActivityEntity;
import cn.bugstack.domain.activity.model.entity.ActivitySkuEntity;
import cn.bugstack.domain.activity.model.valobj.ActivityStateVO;
import cn.bugstack.domain.activity.service.rule.AbstractActionChain;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 活动规则过滤【日期、状态】
 * @create 2024-03-23 10:23
 */
@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        log.info("活动责任链-基础信息【有效期、状态】校验开始。");
        if(!ActivityStateVO.open.equals(activityEntity.getState())){
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(),ResponseCode.ACTIVITY_STATE_ERROR.getInfo());

        }
        Date currentDate = new Date();
        if(currentDate.after(activityEntity.getEndDateTime()) || currentDate.before(activityEntity.getBeginDateTime())){
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(),ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }
        if(activitySkuEntity.getStockCountSurplus()<=0){
            throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getCode(),ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getInfo());
        }

        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
