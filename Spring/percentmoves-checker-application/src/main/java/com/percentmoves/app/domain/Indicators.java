package com.percentmoves.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class Indicators {
    public List<Quote> quote;
    public List<Adjclose> adjclose;
}
