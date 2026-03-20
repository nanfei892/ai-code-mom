package com.zsm.aicodemom.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zsm
 */
@Data
public class AppDeployRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用id
     */
    private Long appId;
}
