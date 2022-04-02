package me.yangtao.web.service.application.rpcproxy;

import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.UserBriefService;
import me.yangtao.dubbo1.api.request.UserBriefRequest;
import me.yangtao.dubbo1.api.response.UserBriefInfoResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Dubbo2Proxy {

    @DubboReference(check = false, group = "${dubbo.dubbo2.group}", interfaceClass = UserBriefService.class, timeout = 3000)
    private UserBriefService userBriefService;

    public Result<UserBriefInfoResponse> selectUserBriefInfo(UserBriefRequest request) {
        return userBriefService.selectUserBriefInfo(request);
    }
}
