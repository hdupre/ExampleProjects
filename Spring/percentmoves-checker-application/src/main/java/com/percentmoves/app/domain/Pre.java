package com.percentmoves.app.domain;

import lombok.Data;

@Data
public class Pre {
    public String timezone;
    public int start;
    public int end;
    public int gmtoffset;
}
