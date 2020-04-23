package com.altran.Flight.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.altran.Flight.model.Flight;
import com.altran.Flight.model.Flights;
import com.altran.Flight.service.FlightService;


@RestController
@RequestMapping("flights")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	@GetMapping
	public @ResponseBody List<Flight> getAllFlights() {
		return flightService.getAllFlights();
	}
	
	/*
	 * @GetMapping("/{flight-id}") public @ResponseBody Flight
	 * getFlightById(@PathVariable("flight-id") long flightId) { return
	 * flightService.getFlightById(flightId); }
	 */
	
	@GetMapping("/{departureTime}")
	public @ResponseBody Flights getFlightByDepartureTime(@PathVariable("departureTime") String departureTime) {
		Flights flights;
		try {
			flights = flightService.getFlightByDepartureTime(departureTime);
		} catch (ParseException e) {
			flights = new Flights();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}
}
