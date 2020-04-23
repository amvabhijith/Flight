package com.altran.Flight.model;

import java.util.ArrayList;
import java.util.List;

public class Flights {
	
	private List<Flight> flights;

	public Flights(List<Flight> flightsList) {
		this.flights = new ArrayList(flightsList);
	}

	public Flights() {
		// TODO Auto-generated constructor stub
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
