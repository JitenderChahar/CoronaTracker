package com.jsc.service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.jsc.models.CoronaReportedStats;

@Service
public class VirusDataService {

	private static final String REPORTED_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static final String REPORTED_DEATHS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private static final String REPORTED_RECOVERY_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

	private List<CoronaReportedStats> coronaStats = new ArrayList<>();
	private List<CoronaReportedStats> coronaDeathsStats = new ArrayList<>();
	private List<CoronaReportedStats> coronaRecoveryStats = new ArrayList<>();

	public List<CoronaReportedStats> getCoronaStats() {
		return coronaStats;
	}

	public void setCoronaStats(List<CoronaReportedStats> coronaStats) {
		this.coronaStats = coronaStats;
	}

	public List<CoronaReportedStats> getCoronaDeathsStats() {
		return coronaDeathsStats;
	}

	public void setCoronaDeathsStats(List<CoronaReportedStats> coronaDeathsStats) {
		this.coronaDeathsStats = coronaDeathsStats;
	}

	public List<CoronaReportedStats> getCoronaRecoveryStats() {
		return coronaRecoveryStats;
	}

	public void setCoronaRecoveryStats(List<CoronaReportedStats> coronaRecoveryStats) {
		this.coronaRecoveryStats = coronaRecoveryStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getData() throws URISyntaxException, IOException {
		RestTemplate client = new RestTemplate();

		List<CoronaReportedStats> stats = new ArrayList<>();
		URI uri = new URI(REPORTED_CASES_URL);

		ResponseEntity<String> result = client.getForEntity(uri, String.class);

		Reader in = new StringReader(result.getBody());
		for (CSVRecord record : CSVFormat.DEFAULT.withHeader().parse(in)) {
			CoronaReportedStats stat = new CoronaReportedStats();
			stat.setState(record.get("Province/State"));
			stat.setCountry(record.get("Country/Region"));
			int totalCases = 0;
			String lastRecord = record.get(record.size() - 1);
			if(!StringUtils.isEmpty(lastRecord)){
				totalCases = Integer.parseInt(lastRecord);
			}
			
			int casesDayBefore = 0;
			String secondLastRecord = record.get(record.size() - 2);
			if(!StringUtils.isEmpty(lastRecord)){
				casesDayBefore = Integer.parseInt(secondLastRecord);
			}
			
			stat.setReportedCases(totalCases);
			stat.setNewCasesFromYesterday(totalCases - casesDayBefore);
			stats.add(stat);
		}

		coronaStats = stats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getDeathData() throws URISyntaxException, IOException {
		RestTemplate client = new RestTemplate();

		List<CoronaReportedStats> stats = new ArrayList<>();
		URI uri = new URI(REPORTED_DEATHS_URL);

		ResponseEntity<String> result = client.getForEntity(uri, String.class);

		Reader in = new StringReader(result.getBody());
		for (CSVRecord record : CSVFormat.DEFAULT.withHeader().parse(in)) {
			CoronaReportedStats stat = new CoronaReportedStats();
			stat.setState(record.get("Province/State"));
			stat.setCountry(record.get("Country/Region"));
			int totalCases = 0;
			String lastRecord = record.get(record.size() - 1);
			if(!StringUtils.isEmpty(lastRecord)){
				totalCases = Integer.parseInt(lastRecord);
			}
			
			int casesDayBefore = 0;
			String secondLastRecord = record.get(record.size() - 2);
			if(!StringUtils.isEmpty(lastRecord)){
				casesDayBefore = Integer.parseInt(secondLastRecord);
			}
			
			stat.setReportedCases(totalCases);
			stat.setNewCasesFromYesterday(totalCases - casesDayBefore);
			stats.add(stat);
		}

		coronaDeathsStats = stats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getRecoveryData() throws URISyntaxException, IOException {
		RestTemplate client = new RestTemplate();

		List<CoronaReportedStats> stats = new ArrayList<>();
		URI uri = new URI(REPORTED_RECOVERY_URL);

		ResponseEntity<String> result = client.getForEntity(uri, String.class);

		Reader in = new StringReader(result.getBody());
		for (CSVRecord record : CSVFormat.DEFAULT.withHeader().parse(in)) {
			CoronaReportedStats stat = new CoronaReportedStats();
			stat.setState(record.get(0));
			stat.setCountry(record.get(1));
			int totalCases = 0;
			String lastRecord = record.get(record.size() - 1);
			if(!StringUtils.isEmpty(lastRecord)){
				totalCases = Integer.parseInt(lastRecord);
			}
			
			int casesDayBefore = 0;
			String secondLastRecord = record.get(record.size() - 2);
			if(!StringUtils.isEmpty(lastRecord)){
				casesDayBefore = Integer.parseInt(secondLastRecord);
			}
			
			stat.setReportedCases(totalCases);
			stat.setNewCasesFromYesterday(totalCases - casesDayBefore);
			stats.add(stat);
		}

		coronaRecoveryStats = stats;
	}

}
