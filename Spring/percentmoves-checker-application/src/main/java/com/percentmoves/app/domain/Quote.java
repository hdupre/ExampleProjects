package com.percentmoves.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class Quote {
    public List<Double> open;
    public List<Double> high;
    public List<Integer> volume;
    public List<Double> low;
    public List<Double> close;
}
