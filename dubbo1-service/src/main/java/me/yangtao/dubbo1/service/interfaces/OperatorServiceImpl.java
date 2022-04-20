package me.yangtao.dubbo1.service.interfaces;

import me.yangtao.bz.tag.dubbo.TagContextHelper;
import me.yangtao.dubbo.common.Result;
import me.yangtao.dubbo.common.error.ErrorCodes;
import me.yangtao.dubbo1.api.OperatorService;
import lombok.extern.slf4j.Slf4j;
import me.yangtao.dubbo1.api.UserBriefService;
import me.yangtao.dubbo1.api.request.OperatorRequest;
import me.yangtao.dubbo1.api.request.UserBriefRequest;
import me.yangtao.dubbo1.api.response.OperatorResponse;
import me.yangtao.dubbo1.api.response.UserBriefInfoResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 测试接口，模拟获取用户简要信息
 *
 * @author yangtao
 */
@DubboService(group = "${dubbo.group}", timeout = 5000)
@Slf4j
public class OperatorServiceImpl implements OperatorService {

    @DubboReference(check = false, group = "${dubbo.dubbo2.group}", interfaceClass = UserBriefService.class, timeout = 3000)
    private UserBriefService userBriefService;

    /**
     * 模拟获取操作员信息
     *
     * @param request
     * @return
     */
    @Override
    public Result<OperatorResponse> selectOperator(OperatorRequest request) {
        log.info("selectOperator.request:" + request);
        System.out.println("selectOperator.request:" + request);
        Result<UserBriefInfoResponse> userBriefResult = userBriefService
                .selectUserBriefInfo(new UserBriefRequest().setUserId(request.getUserId()));
        if(userBriefResult.getCode() != ErrorCodes.OK.getCode()){
            return Result.fail(ErrorCodes.SERVER_DEPENDENT_ERROR,
                    "case selectUserBriefInfo code:" + userBriefResult.getCode());
        }
        UserBriefInfoResponse userBriefResponse = userBriefResult.getData();
        OperatorResponse response = new OperatorResponse(userBriefResponse.getUserId(),
                userBriefResponse.getNickName() , "大副");
        System.out.println("TagContextHelper::" + TagContextHelper.getAll());
        log.info("TagContextHelper::" + TagContextHelper.getAll());
        return Result.success(response);
    }
}
