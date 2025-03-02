package cn.bugstack.domain.activity.service.armory;

import cn.bugstack.domain.activity.model.entity.ActivitySkuEntity;
import cn.bugstack.domain.activity.repository.IActivityRepository;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author luke
 * @date 2025年02月28日 12:16
 */
@Slf4j
@Service
public class ActivityArmory implements IActivityArmory, IActivityDispatch {
    @Resource
    private IActivityRepository activityRepository;
    @Override
    public boolean assembleActivitySku(Long sku) {
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(sku);
        cacheActivitySkuStockCount(sku,activitySkuEntity.getStockCount());
        //预热活动
        activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        //预热活动次数
        activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());


        return true;
    }

    @Override
    public boolean assembleActivitySkuByActivityId(Long activityId) {
        List<ActivitySkuEntity> activitySkuEntities = activityRepository.queryActivitySkuListByActivityId(activityId);
        for(ActivitySkuEntity activitySkuEntity : activitySkuEntities){
            cacheActivitySkuStockCount(activitySkuEntity.getSku(),activitySkuEntity.getStockCount());
            activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        }
        activityRepository.queryRaffleActivityByActivityId(activityId);
        return true;
    }

    private void cacheActivitySkuStockCount(Long sku, Integer stockCount) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        activityRepository.cacheActivitySkuStockCount(cacheKey,stockCount);
    }

    @Override
    public boolean substractActivitySkuStock(Long sku, Date endDateTime) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        return activityRepository.substractActivitySkuStock(sku,cacheKey,endDateTime);
    }
}
