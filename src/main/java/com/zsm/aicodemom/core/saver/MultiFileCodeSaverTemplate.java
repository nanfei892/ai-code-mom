package com.zsm.aicodemom.core.saver;

import cn.hutool.core.util.StrUtil;
import com.zsm.aicodemom.ai.model.MultiFileCodeResult;
import com.zsm.aicodemom.exception.BusinessException;
import com.zsm.aicodemom.exception.ErrorCode;
import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;

/**
 * 多文件代码保存器
 *
 * @author zsm
 */
public class MultiFileCodeSaverTemplate extends CodeFileSaverTemplate<MultiFileCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.MULTI_FILE;
    }

    @Override
    protected void saveFiles(MultiFileCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        // 保存 CSS 文件
        writeToFile(baseDirPath, "style.css", result.getCssCode());
        // 保存 JS 文件
        writeToFile(baseDirPath, "script.js", result.getJsCode());
    }

    @Override
    protected void validateInput(MultiFileCodeResult result) {
        super.validateInput(result);
        // 至少要有 HTML 代码、CSS 和 JS 可以为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码内容不能为空");
        }
    }
}
