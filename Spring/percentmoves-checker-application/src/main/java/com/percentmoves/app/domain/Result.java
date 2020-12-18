package com.percentmoves.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    public Meta meta;
    public List<Integer> timestamp;
    public Indicators indicators;
}
