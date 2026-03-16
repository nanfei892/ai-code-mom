package com.zsm.aicodemom.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.zsm.aicodemom.ai.model.HtmlCodeResult;
import com.zsm.aicodemom.ai.model.MultiFileCodeResult;
import com.zsm.aicodemom.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author zsm
 * @date 2026/3/16 10:30
 * @projectName ai-code-mom
 * @description:
 */
@Deprecated
public class CodeFileSaver {

    // 文件保存根目录
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 保存 HTMLCodeResult
     */
    public static File saveHtmlCodeResult(HtmlCodeResult result) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeTiFile(baseDirPath, "index.html", result.getHtmlCode());
        return new File(baseDirPath);
    }

    /**
     * 保存 MultiFileCodeResult
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult result) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeTiFile(baseDirPath, "index.html", result.getHtmlCode());
        writeTiFile(baseDirPath, "style.css", result.getCssCode());
        writeTiFile(baseDirPath, "script.js", result.getJsCode());
        return new File(baseDirPath);
    }


    /**
     * 构建唯一目录路径：tmp/code_output/bizType_雪花ID
     */
    private static String buildUniqueDir(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.getSnowflakeNextIdStr());
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 写入单个文件
     */
    private static void writeTiFile(String dirPath, String filename, String content) {
        String filePath = dirPath + File.separator + filename;
        FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
    }


}
