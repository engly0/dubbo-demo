package me.yangtao.dubbo1.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yangtao.dubbo.common.request.BaseRequest;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserBriefRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 840006472033151417L;
    /**
     * 用户id
     */
    private Long userId = 0L;
}
