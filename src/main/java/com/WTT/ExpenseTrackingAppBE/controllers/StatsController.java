package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.GraphDto;
import com.WTT.ExpenseTrackingAppBE.services.stats.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {

    private final StatService statService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDto> getChartDetails(){
        return ResponseEntity.ok(statService.getChartData());
    }



    @GetMapping("/all")
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(statService.getStats());
    }
}