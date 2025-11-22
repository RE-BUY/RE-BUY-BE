package com.rebuy.controller;

import com.rebuy.dto.dashboard.DashboardResponse;
import com.rebuy.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse dashboard() {
        return dashboardService.getDashboard();
    }
}