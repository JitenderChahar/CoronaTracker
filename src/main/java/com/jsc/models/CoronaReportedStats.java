package com.jsc.models;

public class CoronaReportedStats {

	private String state;
	private String country;
	private int reportedCases;
	private int newCasesFromYesterday;

	public int getNewCasesFromYesterday() {
		return newCasesFromYesterday;
	}

	public void setNewCasesFromYesterday(int newCasesFromYesterday) {
		this.newCasesFromYesterday = newCasesFromYesterday;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getReportedCases() {
		return reportedCases;
	}

	public void setReportedCases(int reportedCases) {
		this.reportedCases = reportedCases;
	}

	@Override
	public String toString() {
		return "CoronaReportedStats [state=" + state + ", country=" + country + ", reportedCases=" + reportedCases
				+ ", newCasesFromYesterday=" + newCasesFromYesterday + "]";
	}

	
	
}
