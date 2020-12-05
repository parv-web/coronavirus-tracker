package com.parv.Coronavirustracker.controllers;

import com.parv.Coronavirustracker.models.LocationStats;
import com.parv.Coronavirustracker.services.CoronaVirusDataSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataSevice coronaVirusDataSevice;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataSevice.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats" ,allStats);
        model.addAttribute("totalReportedCases" ,totalReportedCases);

        return "home";
    }
}
