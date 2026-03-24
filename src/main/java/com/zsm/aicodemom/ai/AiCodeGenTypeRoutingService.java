package com.zsm.aicodemom.ai;

import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;
import dev.langchain4j.service.SystemMessage;

/**
 * AI 代码生成类型只能路由服务
 * 使用结构化输出直接返回枚举类型
 *
 * @author zsm
 */
public interface AiCodeGenTypeRoutingService {

    @SystemMessage(fromResource = "prompt/codegen-routing-system-prompt.txt")
    CodeGenTypeEnum routeCodeGenType(String userPrompt);

}
