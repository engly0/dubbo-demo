package me.yangtao.web.service.interfaces;

import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.request.OperatorRequest;
import me.yangtao.dubbo1.api.response.OperatorResponse;
import me.yangtao.web.service.application.rpcproxy.Dubbo1Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "operatorApi")
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private Dubbo1Proxy dubbo1Proxy;

    //    @PostMapping("/selectOne")
    @RequestMapping("/selectOne")
    public Result<OperatorResponse> selectOperator(OperatorRequest request) {
        log.info("operator.selectOperator:" + request);
        Result<OperatorResponse> result = dubbo1Proxy.selectOperator(request);
        log.info("operator.result:" + result);
        return result;
    }

}
