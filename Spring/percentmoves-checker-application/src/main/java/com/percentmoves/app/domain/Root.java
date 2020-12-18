package com.percentmoves.app.domain;

import com.percentmoves.app.util.BaseMessage;
import lombok.Data;

@Data
public class Root extends BaseMessage {
    public Chart chart;
}
