package me.yangtao.dubbo1.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangtao
 */
@Data
@AllArgsConstructor
public class UserBriefInfoResponse implements Serializable {

    private static final long serialVersionUID = -7103895811478745471L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;
}
