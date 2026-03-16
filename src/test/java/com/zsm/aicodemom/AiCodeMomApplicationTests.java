package com.zsm.aicodemom;

import com.zsm.aicodemom.ai.AiCodeGeneratorService;
import com.zsm.aicodemom.ai.model.HtmlCodeResult;
import com.zsm.aicodemom.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeMomApplicationTests {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个男妃的工作记录小工具，不超过20行");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个男妃的留言板，不超过50行");
        Assertions.assertNotNull(multiFileCode);
    }
}
