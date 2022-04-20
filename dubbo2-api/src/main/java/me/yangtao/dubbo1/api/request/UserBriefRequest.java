package me.yangtao.dubbo1.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.yangtao.dubbo.common.request.BaseRequest;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserBriefRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 840006472033151417L;
    /**
     * 用户id
     */
    private Long userId = 0L;
}
