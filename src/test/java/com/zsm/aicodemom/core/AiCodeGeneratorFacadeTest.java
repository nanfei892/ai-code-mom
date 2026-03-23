package com.zsm.aicodemom.core;

import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("任务记录网站，总体不超过20行代码", CodeGenTypeEnum.MULTI_FILE, 1L);
        Assertions.assertNotNull(file);
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站，总体不超过20行代码", CodeGenTypeEnum.MULTI_FILE, 1L);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }

    @Test
    void generatorVueProjectCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站，总体不超过200行代码", CodeGenTypeEnum.VUE_PROJECT, 1L);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }
}
/**
 # 1. 用户登录
 curl -X POST "http://localhost:8123/api/user/login" \
 -H "Content-Type: application/json" \
 -d '{
 "userAccount": "nanfei",
 "userPassword": "123456"
 }' \
 -c cookies.txt

 # 2. 调用生成代码接口（流式）
 curl -G "http://localhost:8123/api/app/chat/gen/code" \
 --data-urlencode "appId=391826619000033280" \
 --data-urlencode "message=帮我创建一个简单的家庭记账小工具，总代码行不要超过50行" \
 -H "Accept: text/event-stream" \
 -H "Cache-Control: no-cache" \
 -b cookies.txt \
 --no-buffer
 */
