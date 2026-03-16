package com.zsm.aicodemom.core.saver;

import cn.hutool.core.util.StrUtil;
import com.zsm.aicodemom.ai.model.HtmlCodeResult;
import com.zsm.aicodemom.exception.BusinessException;
import com.zsm.aicodemom.exception.ErrorCode;
import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;

/**
 * HTML 代码文件保存器
 *
 * @author zsm
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码内容不能为空");
        }
    }
}
