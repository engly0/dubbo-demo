package me.yangtao.dubbo1.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangtao
 */
@Data
@AllArgsConstructor
public class OperatorResponse implements Serializable {

    private static final long serialVersionUID = -1268104080630860366L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 操作员Title
     */
    private String operatorTitle;
}
