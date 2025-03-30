package cn.bugstack.trigger.api;

import cn.bugstack.types.model.Response;

/**
 * @author luke
 * @date 2025年03月30日 15:49
 */
public interface IDCCService {

    Response<Boolean> updateCondig(String key, String value);
}
