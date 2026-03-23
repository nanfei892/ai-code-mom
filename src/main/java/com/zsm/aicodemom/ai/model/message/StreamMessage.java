package com.zsm.aicodemom.ai.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流式消息响应基类
 *
 * @author zsm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamMessage {

    private String type;

}
