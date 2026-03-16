package com.zsm.aicodemom.core;

import com.zsm.aicodemom.ai.AiCodeGeneratorService;
import com.zsm.aicodemom.ai.model.HtmlCodeResult;
import com.zsm.aicodemom.ai.model.MultiFileCodeResult;
import com.zsm.aicodemom.core.parser.CodeParserExecutor;
import com.zsm.aicodemom.core.saver.CodeFileSaverExecutor;
import com.zsm.aicodemom.exception.BusinessException;
import com.zsm.aicodemom.exception.ErrorCode;
import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;

/**
 * AI 代码生成外观类，组合生成和保存功能 (门面模式)
 *
 * @author zsm
 */
@Slf4j
@Service
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    /**
     * 统一入口：根据类型生成并保存代码
     *
     * @param userMessage     用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 保存的目录
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
                yield  CodeFileSaverExecutor.executeSaver(result, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
                yield  CodeFileSaverExecutor.executeSaver(result, CodeGenTypeEnum.MULTI_FILE);
            }
            default -> {
                String errorMessage = "不支持的生成类型: " + codeGenTypeEnum.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 统一入口：根据类型生成并保存代码（流式）
     *
     * @param userMessage     用户提示词
     * @param codeGenTypeEnum 生成类型
     */
    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                Flux<String> codeStream = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
                yield  processCodeStream(codeStream, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                Flux<String> codeStream = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
                yield  processCodeStream(codeStream, CodeGenTypeEnum.MULTI_FILE);
            }
            default -> {
                String errorMessage = "不支持的生成类型: " + codeGenTypeEnum.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 通用流式代码处理方法
     *
     * @param codeStream 代码流
     * @param codeGenTypeEnum 代码生成类型
     * @return 流式响应
     */
    private Flux<String> processCodeStream(Flux<String> codeStream, CodeGenTypeEnum codeGenTypeEnum) {
        StringBuilder codeBuilder = new StringBuilder();
        return codeStream.doOnNext(chunk -> {
            // 实时收集代码片段
            codeBuilder.append(chunk);
        }).doOnComplete(() -> {
            // 流式返回完成后保存代码
            try {
                String completeCode = codeBuilder.toString();
                // 使用执行器解析代码
                Object parsedResult = CodeParserExecutor.executeParser(completeCode, codeGenTypeEnum);
                // 使用执行器保存代码
                File saveDir = CodeFileSaverExecutor.executeSaver(parsedResult, codeGenTypeEnum);
                log.info("保存成功，路径为： " + saveDir.getAbsolutePath());
            } catch (Exception e) {
                log.error("保存失败：{}", e.getMessage());
            }
        });
    }
}
