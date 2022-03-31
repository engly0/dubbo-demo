package me.yangtao.dubbo1.api;

import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo1.api.request.OperatorRequest;
import me.yangtao.dubbo1.api.response.OperatorResponse;

/**
 * 测试接口，模拟获取操作员信息
 * @author yangtao
 */
public interface OperatorService {


    /**
     *
     * 模拟获取操作员信息
     *
     * @param request
     * @return
     */
    Result<OperatorResponse> selectOperator(OperatorRequest request);
}
