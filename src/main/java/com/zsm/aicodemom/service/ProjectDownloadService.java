package com.zsm.aicodemom.service;

import jakarta.servlet.http.HttpServletResponse;

/**

* @author zsm
*/
public interface ProjectDownloadService {

    /**
     * 将项目打包下载为 zip 文件
     *
     * @param projectPath      项目路径
     * @param downloadFileName 下载文件名
     * @param response         HTTP 响应
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
