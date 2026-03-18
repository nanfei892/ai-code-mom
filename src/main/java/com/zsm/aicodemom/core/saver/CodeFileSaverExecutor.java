package com.zsm.aicodemom.core.saver;

import com.zsm.aicodemom.ai.model.HtmlCodeResult;
import com.zsm.aicodemom.ai.model.MultiFileCodeResult;
import com.zsm.aicodemom.exception.BusinessException;
import com.zsm.aicodemom.exception.ErrorCode;
import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * 代码文件保存执行器
 * 根据代码生成类型执行相应的保存逻辑
 *
 * @author zsm
 */
public class CodeFileSaverExecutor {

    private static final HtmlCodeFileSaverTemplate htmlCodeeFileSaver = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeSaverTemplate multiFileCodeSaver = new MultiFileCodeSaverTemplate();

    /**
     * 执行代码保存
     *
     * @param codeResult 代码结果对象
     * @param codeGenType 代码生成类型
     * @param appId 应用 ID
     * @return 保存的目录
     */
    public static File executeSaver(Object codeResult, CodeGenTypeEnum codeGenType, Long appId) {
        return switch (codeGenType) {
            case HTML -> htmlCodeeFileSaver.saveCode((HtmlCodeResult) codeResult, appId);
            case MULTI_FILE -> multiFileCodeSaver.saveCode((MultiFileCodeResult) codeResult, appId);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型：" + codeGenType);
        };
    }
}
