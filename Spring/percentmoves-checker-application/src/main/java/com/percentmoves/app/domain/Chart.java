package com.percentmoves.app.domain;

import lombok.Data;

import java.util.List;


@Data
public class Chart {
    public List<Result> result;
    public Object error;
}
