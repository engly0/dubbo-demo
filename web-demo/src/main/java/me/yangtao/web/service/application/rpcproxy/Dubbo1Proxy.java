package me.yangtao.web.service.application.rpcproxy;

import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.OperatorService;
import me.yangtao.dubbo1.api.request.OperatorRequest;
import me.yangtao.dubbo1.api.response.OperatorResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Dubbo1Proxy {

    @DubboReference(check = false, group = "${dubbo.dubbo1.group}", interfaceClass = OperatorService.class, timeout = 3000)
    private OperatorService operatorService;

    public Result<OperatorResponse> selectOperator(OperatorRequest request) {
        return operatorService.selectOperator(request);
    }
}
