package com.percentmoves.app.service;

import com.percentmoves.app.util.ResponseMessages;

public interface PercentMovesService {
    ResponseMessages percentMovesChecker(String tickers, String range);
}
