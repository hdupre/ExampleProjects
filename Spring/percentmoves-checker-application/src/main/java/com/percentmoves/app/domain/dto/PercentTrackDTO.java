package com.percentmoves.app.domain.dto;

import com.percentmoves.app.util.BaseMessage;
import lombok.Data;

import java.util.List;

@Data
public class PercentTrackDTO extends BaseMessage {
    private String ticker;
    private List<DateMoveDTO> dateMoveList;
}
