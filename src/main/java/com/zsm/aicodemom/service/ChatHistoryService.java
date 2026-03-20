package com.zsm.aicodemom.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.zsm.aicodemom.model.dto.chathistory.ChatHistoryQueryRequest;
import com.zsm.aicodemom.model.entity.ChatHistory;
import com.zsm.aicodemom.model.entity.User;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author <a href="https://github.com/nanfei892">男妃</a>
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 新增历史对话（保存用户消息和 AI 消息）
     * @param appId 应用 ID
     * @param message 消息
     * @param messageType 消息类型（用户/ AI ）
     * @param userId 用户 ID
     * @return 结果
     */
    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    /**
     * 删除该应用的对话历史数据
     * @param appId 应用 ID
     * @return 结果
     */
    boolean deleteByAppId(Long appId);

    /**
     * 获取查询包装类
     * @param chatHistoryQueryRequest 查询对象
     * @return 查询条件
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    /**
     * 根据游标查询对话历史
     * @param appId 应用 ID
     * @param pageSize 每页条数
     * @param lastCreateTime 最后创建时间（游标）
     * @param loginUser 当前登录用户
     * @return 对话历史列表
     */
    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);
}
