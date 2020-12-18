package com.percentmoves.app.controller;

import com.percentmoves.app.service.PercentMovesService;
import com.percentmoves.app.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sampleAPI")
public class PercentMovesController {

    @Autowired
    private PercentMovesService percentMovesService;

    @GetMapping(value = "/analysis", params = {"tickers", "range"})
    public ResponseEntity<ResponseMessages> getUserDetails(@RequestParam("tickers") String tickers, @RequestParam("range") String range){
        return ResponseEntity.accepted().body(percentMovesService.percentMovesChecker(tickers, range));
    }

    @GetMapping
    public String test(){
        return "Hello percent moves application";
    }

}
