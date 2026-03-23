package com.zsm.aicodemom.ai.model.message;

import lombok.Getter;

/**
 * 流式响应消息类型枚举
 *
 * @author zsm
 */
@Getter
public enum StreamMessageTypeEnum {

    AI_RESPONSE("ai_response", "Ai响应"),
    TOOL_REQUEST("tool_request", "工具请求"),
    TOOL_EXECUTED("tool_executed", "工具执行结果");

    private final String value;
    private final String text;

    /**
     * 枚举构造函数
     *
     * @param value 值
     * @param text  文本
     */
    StreamMessageTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 根据值获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static StreamMessageTypeEnum getEnumByValue(String value) {
        for (StreamMessageTypeEnum typeEnum : values()) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }
}
