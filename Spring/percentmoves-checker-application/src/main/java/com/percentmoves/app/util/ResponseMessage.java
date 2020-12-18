package com.percentmoves.app.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage extends BaseMessage{
    private String response;
    private String responseMessage;
    private BaseMessage baseMessage;
}
