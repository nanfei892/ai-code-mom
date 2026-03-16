package com.zsm.aicodemom.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zsm
 * @date 2026/3/15 20:26
 * @projectName ai-code-mom
 * @description:
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
