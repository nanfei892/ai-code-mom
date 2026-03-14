package com.zsm.aicodemom.controller;

import com.zsm.aicodemom.common.BaseResponse;
import com.zsm.aicodemom.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsm
 * @date 2026/3/12 23:03
 * @projectName ai-code-mom
 * @description:
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/")
    public BaseResponse<String> healthCheck() {
        return ResultUtils.success("ok");
    }

}
