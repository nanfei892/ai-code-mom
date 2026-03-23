package com.zsm.aicodemom.service;


/**
 * @author zsm
 */
public interface ScreenshotService {

    /**
     * 生成并上传网页截图
     *
     * @param webUrl 网页 URL
     * @return 对象存储访问 URL
     */
    String generateAnUploadScreenshot(String webUrl);
}
