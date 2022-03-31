package me.yangtao.dubbo1.api.request;

import lombok.Data;
import me.yangtao.dubbo.common.request.BaseRequest;

import java.io.Serializable;

@Data
public class OperatorRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = -461427974311512931L;
    /**
     * 用户id
     */
    private Long userId = 0L;
}
