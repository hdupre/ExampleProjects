package com.percentmoves.app.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessages {
    private String response;
    private String responseDescription;
    private Collection<? extends BaseMessage> baseMessages;
}
