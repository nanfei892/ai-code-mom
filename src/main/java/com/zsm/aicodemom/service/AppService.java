package com.zsm.aicodemom.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.zsm.aicodemom.model.dto.app.AppQueryRequest;
import com.zsm.aicodemom.model.dto.app.AppVO;
import com.zsm.aicodemom.model.entity.App;
import com.zsm.aicodemom.model.entity.User;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 * @author <a href="https://github.com/nanfei892">男妃</a>
 */
public interface AppService extends IService<App> {

    /**
     * 查询应用详情
     * @param app 应用
     * @return 应用详情
     */
    AppVO getAppVO(App app);

    /**
     * 获取查询条件
     * @param appQueryRequest 查询条件
     * @return 查询条件
     */
    public QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 批量查询应用详情
     * @param appList 应用列表
     * @return 应用详情列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 调用门面生成代码
     * @param appId 应用 Id
     * @param message 提示词
     * @param loginUser 登录用户
     * @return 流式响应
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);
}
