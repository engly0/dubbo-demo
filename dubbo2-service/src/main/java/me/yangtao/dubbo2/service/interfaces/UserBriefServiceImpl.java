package me.yangtao.dubbo2.service.interfaces;

import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.UserBriefService;
import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo1.api.request.UserBriefRequest;
import me.yangtao.dubbo1.api.response.UserBriefInfoResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * 测试接口，模拟获取用户简要信息
 *
 * @author yangtao
 */
@DubboService(group = "${dubbo.group}", timeout = 5000)
@Slf4j
public class UserBriefServiceImpl implements UserBriefService {

    /**
     * 查询用户简要信息
     *
     * @param request
     * @return
     */
    @Override
    public Result<UserBriefInfoResponse> selectUserBriefInfo(UserBriefRequest request) {
        log.info("selectUserBriefInfo.request" + request);
        System.out.println("selectUserBriefInfo.request" + request);
        UserBriefInfoResponse response = new UserBriefInfoResponse(request.getUserId(), "小明" + request.getUserId());
        return Result.success(response);
    }
}
