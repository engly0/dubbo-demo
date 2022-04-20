package me.yangtao.dubbo1.api;

import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.request.UserBriefRequest;
import me.yangtao.dubbo1.api.response.UserBriefInfoResponse;

/**
 * 测试接口，模拟获取用户简要信息
 * @author yangtao
 */
public interface UserBriefService {


    /**
     *
     * 查询用户简要信息
     *
     * @param request
     * @return
     */
    Result<UserBriefInfoResponse> selectUserBriefInfo(UserBriefRequest request);
}
