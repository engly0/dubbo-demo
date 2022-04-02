package me.yangtao.web.service.interfaces;

import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo.common.utils.JsonUtil;
import me.yangtao.dubbo1.api.request.OperatorRequest;
import me.yangtao.dubbo1.api.request.UserBriefRequest;
import me.yangtao.dubbo1.api.response.OperatorResponse;
import me.yangtao.dubbo1.api.response.UserBriefInfoResponse;
import me.yangtao.web.service.application.rpcproxy.Dubbo1Proxy;
import me.yangtao.web.service.application.rpcproxy.Dubbo2Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController(value = "operatorApi")
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private Dubbo1Proxy dubbo1Proxy;

    @Autowired
    private Dubbo2Proxy dubbo2Proxy;

    @RequestMapping("/selectOne")
    public Result<OperatorResponse> selectOperator(OperatorRequest request) {
        log.info("operator.selectOperator:" + request);
        Result<OperatorResponse> result = dubbo1Proxy.selectOperator(request);
        log.debug("operator.result:" + result);
        return result;
    }

    @RequestMapping("/testHeader")
    public String testHeader(@RequestHeader Map<String, String> headers) {
        dubbo2Proxy.selectUserBriefInfo(new UserBriefRequest());
        return JsonUtil.GetJsonResult(headers);
    }

    @PostMapping("/testPost")
    public Result<Map<String, String>> testPost(@RequestHeader Map<String, String> headers) {
        return Result.success("testPost", headers);
    }

    @GetMapping("/testGet")
    public Result<Map<String, Object>> testGet(@RequestParam Long userId, @RequestHeader Map<String, String> headers) {
        Result<OperatorResponse> operatorResult = dubbo1Proxy.selectOperator(new OperatorRequest(userId));
        Result<UserBriefInfoResponse> userBriefResult = dubbo2Proxy.selectUserBriefInfo(new UserBriefRequest(userId));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("operatorResult", operatorResult);
        responseMap.put("userBriefResult", userBriefResult);
        responseMap.put("headers", headers);
        return Result.success("testGet", responseMap);
    }

    @DeleteMapping("/testDel")
    public Result<Map<String, String>> testDel(@RequestHeader Map<String, String> headers) {
        return Result.success("testDel", headers);
    }

    @PutMapping("/testPut")
    public Result<Map<String, String>> testPut(@RequestHeader Map<String, String> headers) {
        return Result.success("testPut", headers);
    }

    @PatchMapping("/testPatch")
    public Result<Map<String, String>> testPatch(@RequestHeader Map<String, String> headers) {
        return Result.success("testPatch", headers);
    }

}
