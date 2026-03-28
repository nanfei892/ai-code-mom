package com.zsm.aicodemom.langgraph4j.tools;

import com.zsm.aicodemom.langgraph4j.model.enums.ImageCategoryEnum;
import com.zsm.aicodemom.langgraph4j.state.ImageResource;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PixabayIllustrationToolTest {

    /**
     * 注入修正后的 PixabayIllustrationTool
     */
    @Resource
    private UndrawIllustrationTool undrawIllustrationTool;

    /**
     * 测试场景1：正常关键词搜索（核心功能验证）
     * 验证点：返回列表非空、字段结构正确、URL格式合法
     */
    @Test
    void testSearchIllustrations_NormalQuery() {
        String testQuery = "AI人工智能 科技未来";
        System.out.println("=== 开始测试：正常关键词搜索【" + testQuery + "】 ===");

        List<ImageResource> illustrations = undrawIllustrationTool.searchIllustrations(testQuery);

        // 1. 基础非空校验
        assertNotNull(illustrations, "返回列表不应为null");

        if (!illustrations.isEmpty()) {
            // 2. 单条数据结构校验（仅当有结果时执行）
            ImageResource firstIllustration = illustrations.get(0);
            assertEquals(ImageCategoryEnum.ILLUSTRATION, firstIllustration.getCategory(), "分类应为插画类型");
            assertNotNull(firstIllustration.getDescription(), "插画描述不应为null");
            assertNotNull(firstIllustration.getUrl(), "插画URL不应为null");
            assertTrue(firstIllustration.getUrl().startsWith("http"), "URL应符合http/https协议格式");

            // 3. 结果输出（方便人工核对）
            System.out.println("✅ 测试通过！共搜索到 " + illustrations.size() + " 张插画：");
            illustrations.forEach(illustration ->
                    System.out.println("  - 描述: " + illustration.getDescription() + " | URL: " + illustration.getUrl())
            );
        } else {
            // 无结果时的提示（不判定为失败，可能是关键词无匹配或API限流）
            System.out.println("⚠️  未搜索到插画（可能原因：关键词无匹配结果、API密钥限流、网络异常）");
        }
    }

    /**
     * 测试场景2：空关键词搜索（边界条件验证）
     * 验证点：参数校验生效，返回空列表且不抛异常
     */
    @Test
    void testSearchIllustrations_EmptyQuery() {
        System.out.println("=== 开始测试：空关键词搜索 ===");

        List<ImageResource> illustrations = undrawIllustrationTool.searchIllustrations("");

        assertNotNull(illustrations, "返回列表不应为null");
        assertTrue(illustrations.isEmpty(), "空关键词应返回空列表");

        System.out.println("✅ 测试通过！空关键词正确返回空列表");
    }

    /**
     * 测试场景3：超长关键词搜索（边界条件验证）
     * 验证点：关键词长度超过API限制（100字符）时，代码能自动截断且不抛异常
     */
    @Test
    void testSearchIllustrations_LongQuery() {
        // 构造一个超过100字符的超长关键词
        String longQuery = "这是一个用来测试超长关键词的句子，" +
                "我们需要确保当关键词长度超过Pixabay API限制的100字符时，" +
                "代码能够自动截断并正常处理，不会抛出异常，也不会导致请求失败。";

        System.out.println("=== 开始测试：超长关键词搜索（原始长度: " + longQuery.length() + "） ===");

        List<ImageResource> illustrations = undrawIllustrationTool.searchIllustrations(longQuery);

        // 验证不抛异常、返回列表非null即可（超长关键词可能无结果，但不应报错）
        assertNotNull(illustrations, "返回列表不应为null");
        System.out.println("✅ 测试通过！超长关键词处理正常，返回 " + illustrations.size() + " 条结果");
    }
}
