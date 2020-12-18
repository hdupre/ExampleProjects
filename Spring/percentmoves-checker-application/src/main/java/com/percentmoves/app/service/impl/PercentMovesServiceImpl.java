package com.percentmoves.app.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.percentmoves.app.domain.Root;
import com.percentmoves.app.domain.dto.DateMoveDTO;
import com.percentmoves.app.domain.dto.PercentTrackDTO;
import com.percentmoves.app.service.PercentMovesService;
import com.percentmoves.app.util.Mapper;
import com.percentmoves.app.util.ResponseMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class PercentMovesServiceImpl implements PercentMovesService {

    private final Logger logger = LoggerFactory.getLogger(PercentMovesServiceImpl.class);

    @Override
    public ResponseMessages percentMovesChecker(String tickers, String range) {
        ResponseMessages responseMessage = null;
        String url;
        Root urlValues;
        DateMoveDTO dateMoveDTO;
        List<DateMoveDTO> dateMoveDTOS;
        PercentTrackDTO percentTrackDTO;
        List<PercentTrackDTO> percentTrackDTOS;

        try {
            if (!tickers.contains("null") && !range.contains("null")) {
                String[] tickerArry = tickers.split(",");
                percentTrackDTOS = new ArrayList<>();

                for (String ticker : tickerArry) {
                    url = "https://query1.finance.yahoo.com/v7/finance/chart/" + ticker + "?range="+ range +"&interval=1d&indicators=quote&includeTimestamps=true";
                    ObjectMapper mapper = new ObjectMapper();
                    urlValues = mapper.readValue(new URL(url), Root.class);
                    List<Integer> timestampList = urlValues.getChart().getResult().get(0).getTimestamp();
                    List<Double> adjCloseList = urlValues.getChart().getResult().get(0).getIndicators().getAdjclose().get(0).getAdjclose();
                    dateMoveDTOS = new ArrayList<>();
                    percentTrackDTO = new PercentTrackDTO();
                    percentTrackDTO.setTicker(ticker);
                    Map<Integer, Double> percentMap = new HashMap<>();

                    for (int i=0; i < timestampList.size()-1; i++){
//                        calculate the percentage and round off to two decimal places
                        double percentage = Math.round((((adjCloseList.get(i+1)/adjCloseList.get(i))-1)*100)*100)/100.0;
                        percentMap.put(i, percentage);
                    }
                    SortedMap sortedData = new TreeMap(new Mapper(percentMap));
                    sortedData.putAll(percentMap);

                    Iterator iterator = sortedData.keySet().iterator();
                    if (sortedData.size()>5) {
                        for (int j = 0; j < 5; j++) {
                            int key = (Integer) iterator.next();
                            String dateAsText = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestampList.get(key) * 1000L));
                            dateMoveDTO = new DateMoveDTO(dateAsText, (Double) sortedData.get(key));
                            dateMoveDTOS.add(dateMoveDTO);
                        }
                    } else {
                        for (int j = 0; j < sortedData.size(); j++) {
                            int key = (Integer) iterator.next();
                            String dateAsText = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestampList.get(key) * 1000L));
                            dateMoveDTO = new DateMoveDTO(dateAsText, (Double) sortedData.get(key));
                            dateMoveDTOS.add(dateMoveDTO);
                        }
                    }
                    percentTrackDTO.setDateMoveList(dateMoveDTOS);
                    percentTrackDTOS.add(percentTrackDTO);
                }

                responseMessage = new ResponseMessages("SUCCESS","Success response", percentTrackDTOS);
                logger.info("Success response");
            } else {
                responseMessage = new ResponseMessages("ERROR", "Tickers or range can not be null",null);
                logger.info("Tickers or range can not be null");
            }
        } catch (IOException e) {
            logger.error("ERROR", e);
            e.printStackTrace();
        } catch (Exception ex) {
            logger.error("ERROR", ex);
            ex.printStackTrace();
        }
        return responseMessage;
    }
}
