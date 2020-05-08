package com.jsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jsc.models.CoronaReportedStats;
import com.jsc.service.VirusDataService;

@Controller
public class HomeController {

	@Autowired
	VirusDataService virusDataService;

	@GetMapping("/")
	public String home(Model model) {
		List<CoronaReportedStats> coronaStats = virusDataService.getCoronaStats();
		int totalCasesInWorld = coronaStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();

		int totalCasesInIndia = coronaStats.stream().filter(p -> p.getCountry().equalsIgnoreCase("India"))
				.map(CoronaReportedStats::getReportedCases).mapToInt(Integer::intValue).sum();

		List<CoronaReportedStats> coronaDeathStats = virusDataService.getCoronaDeathsStats();
		int totalDeathsInWorld = coronaDeathStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();
		int totalDeathsInIndia = coronaDeathStats.stream().filter(p -> p.getCountry().equalsIgnoreCase("India"))
				.map(CoronaReportedStats::getReportedCases).mapToInt(Integer::intValue).sum();

		List<CoronaReportedStats> coronaRecoveryStats = virusDataService.getCoronaRecoveryStats();
		int totalRecoveryInWorld = coronaRecoveryStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();
		int totalRecoveryInIndia = coronaRecoveryStats.stream().filter(p -> p.getCountry().equalsIgnoreCase("India"))
				.map(CoronaReportedStats::getReportedCases).mapToInt(Integer::intValue).sum();

		model.addAttribute("totalCasesInWorld", totalCasesInWorld);
		model.addAttribute("totalDeathsInWorld", totalDeathsInWorld);
		model.addAttribute("totalRecoveryInWorld", totalRecoveryInWorld);
		
		model.addAttribute("totalCasesInIndia", totalCasesInIndia);
		model.addAttribute("totalDeathsInIndia", totalDeathsInIndia);
		model.addAttribute("totalRecoveryInIndia", totalRecoveryInIndia);

		return "home";
	}

	@GetMapping("/reportedCases")
	public String reportedCases(Model model) {
		List<CoronaReportedStats> coronaStats = virusDataService.getCoronaStats();
		int totalCasesInWorld = coronaStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();
		int totalCasesFromLastDay = coronaStats.stream().map(CoronaReportedStats::getNewCasesFromYesterday)
				.mapToInt(Integer::intValue).sum();

		model.addAttribute("coronaStats", coronaStats);
		model.addAttribute("totalCasesInWorld", totalCasesInWorld);
		model.addAttribute("totalCasesFromLastDay", totalCasesFromLastDay);

		return "reportedCases";
	}

	@GetMapping("/deaths")
	public String deathPage(Model model) {
		List<CoronaReportedStats> coronaStats = virusDataService.getCoronaDeathsStats();
		int totalCasesInWorld = coronaStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();
		int totalCasesFromLastDay = coronaStats.stream().map(CoronaReportedStats::getNewCasesFromYesterday)
				.mapToInt(Integer::intValue).sum();
		model.addAttribute("coronaStats", coronaStats);
		model.addAttribute("totalCasesInWorld", totalCasesInWorld);
		model.addAttribute("totalCasesFromLastDay", totalCasesFromLastDay);
		return "deaths";
	}

	@GetMapping("/recovery")
	public String recoveryPage(Model model) {
		List<CoronaReportedStats> coronaStats = virusDataService.getCoronaRecoveryStats();
		int totalCasesInWorld = coronaStats.stream().map(CoronaReportedStats::getReportedCases)
				.mapToInt(Integer::intValue).sum();
		int totalCasesFromLastDay = coronaStats.stream().map(CoronaReportedStats::getNewCasesFromYesterday)
				.mapToInt(Integer::intValue).sum();
		model.addAttribute("coronaStats", coronaStats);
		model.addAttribute("totalCasesInWorld", totalCasesInWorld);
		model.addAttribute("totalCasesFromLastDay", totalCasesFromLastDay);
		return "recovery";
	}

}
