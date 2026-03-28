package com.zsm.aicodemom.langgraph4j.tools;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zsm.aicodemom.langgraph4j.model.enums.ImageCategoryEnum;
import com.zsm.aicodemom.langgraph4j.state.ImageResource;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UndrawIllustrationTool {

    /**
     * Pixabay API 密钥，从配置文件注入
     */
    @Value("${pixabay.api-key}")
    private String PIXABAY_API_KEY;

    /**
     * 插画搜索API地址
     * 占位符1: API_KEY
     * 占位符2: URL编码后的搜索关键词
     * 固定参数说明：
     * image_type=illustration  限定只返回插画类型
     * lang=zh                   搜索语言为中文
     * safesearch=true           开启安全搜索，返回全年龄段适配内容
     * per_page=%d               单页返回数量，动态传入
     */
    private static final String PIXABAY_ILLUSTRATION_API_URL = "https://pixabay.com/api/?key=%s&q=%s&image_type=illustration&lang=zh&safesearch=true&per_page=%d";

    /**
     * 单次搜索最大返回数量，符合API per_page 3-200的限制
     */
    private static final int DEFAULT_SEARCH_COUNT = 12;

    /**
     * 搜索关键词最大长度，API限制不超过100字符
     */
    private static final int MAX_QUERY_LENGTH = 100;

    @Tool("搜索插画图片，用于网站美化和装饰，支持中文关键词，返回合规的插画资源")
    public List<ImageResource> searchIllustrations(@P("搜索关键词，建议使用精准中文描述，不超过100字符") String query) {
        List<ImageResource> imageList = new ArrayList<>();

        // 1. 核心参数校验
        if (StrUtil.isBlank(PIXABAY_API_KEY)) {
            log.error("Pixabay API密钥未配置，请检查配置文件中的pixabay.api-key");
            return imageList;
        }
        if (StrUtil.isBlank(query)) {
            log.warn("搜索关键词为空，无法执行插画搜索");
            return imageList;
        }
        if (query.length() > MAX_QUERY_LENGTH) {
            log.warn("搜索关键词超过{}字符限制，已自动截断", MAX_QUERY_LENGTH);
            query = query.substring(0, MAX_QUERY_LENGTH);
        }

        // 2. 关键词URL编码（API强制要求）
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        // 3. 拼接最终请求地址
        String apiUrl = String.format(PIXABAY_ILLUSTRATION_API_URL, PIXABAY_API_KEY, encodedQuery, DEFAULT_SEARCH_COUNT);

        // 4. 发起HTTP请求，try-with-resources自动释放资源
        try (HttpResponse response = HttpRequest.get(apiUrl).timeout(10000).execute()) {
            // 响应状态异常处理
            if (!response.isOk()) {
                log.error("插画搜索请求失败，状态码：{}，响应内容：{}", response.getStatus(), response.body());
                return imageList;
            }

            // 解析响应JSON
            JSONObject result = JSONUtil.parseObj(response.body());
            // 校验返回结果是否命中数据
            Integer totalHits = result.getInt("totalHits", 0);
            if (totalHits <= 0) {
                log.info("关键词【{}】未搜索到匹配的插画资源", query);
                return imageList;
            }

            // 提取核心插画数组（API规范返回的hits字段）
            JSONArray hitsArray = result.getJSONArray("hits");
            if (hitsArray == null || hitsArray.isEmpty()) {
                return imageList;
            }

            // 遍历解析插画数据
            int actualCount = Math.min(DEFAULT_SEARCH_COUNT, hitsArray.size());
            for (int i = 0; i < actualCount; i++) {
                JSONObject illustration = hitsArray.getJSONObject(i);
                // 提取插画描述（使用API返回的tags字段，也可自定义）
                String description = illustration.getStr("tags", "精美插画");
                // 提取插画图片地址（webformatURL 640px宽，适配网站展示，有效期24小时）
                // 如需更大尺寸，可替换为 largeImageURL（1280px），fullHDURL需开通全量API权限
                String imageUrl = illustration.getStr("webformatURL", "");

                // 仅当图片地址有效时，加入结果集
                if (StrUtil.isNotBlank(imageUrl)) {
                    imageList.add(ImageResource.builder()
                            .category(ImageCategoryEnum.ILLUSTRATION)
                            .description(description)
                            .url(imageUrl)
                            // 可扩展补充字段：插画ID、预览图、作者信息等
                            // .id(illustration.getStr("id"))
                            // .previewUrl(illustration.getStr("previewURL"))
                            .build());
                }
            }
            log.info("关键词【{}】插画搜索完成，共获取{}条有效资源", query, imageList.size());

        } catch (Exception e) {
            log.error("Pixabay插画搜索异常，关键词：{}，异常信息：{}", query, e.getMessage(), e);
        }

        return imageList;
    }
}
