package com.percentmoves.app.domain;

import lombok.Data;

@Data
public class Regular {
    public String timezone;
    public int start;
    public int end;
    public int gmtoffset;
}
