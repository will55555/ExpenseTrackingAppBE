package com.WTT.ExpenseTrackingAppBE.services.stats;

import com.WTT.ExpenseTrackingAppBE.dto.GraphDto;
import com.WTT.ExpenseTrackingAppBE.dto.StatsDto;

public interface StatService {
    GraphDto getChartData();
    StatsDto getStats();
}