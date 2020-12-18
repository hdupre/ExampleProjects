package com.percentmoves.app.domain;

import lombok.Data;

@Data
public class CurrentTradingPeriod {
    public Pre pre;
    public Regular regular;
    public Post post;
}
