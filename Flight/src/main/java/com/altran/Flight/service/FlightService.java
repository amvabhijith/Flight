package com.altran.Flight.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.Flight.model.Flight;
import com.altran.Flight.model.Flights;
import com.altran.Flight.repo.FlightRepository;


@Service
public class FlightService{

	@Autowired
	private FlightRepository flightRepo;

	public Flight getFlightById(long flightId) {
		return flightRepo.findByFlightId(flightId);
	}

	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}

	public Flights getFlightByDepartureTime(String departureTime) throws ParseException {
		Date departureTimeStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(departureTime+" 00:00:00"); 
		Date departureTimeEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(departureTime+" 23:59:59"); 
		List<Flight> flightsList = flightRepo.findByDepartureTimeBetween(departureTimeStart,departureTimeEnd);
		List<Flight> resultFlightsList = new ArrayList<Flight>();
		flightsList.parallelStream().forEach(k -> 
								{	
									if((k.getDepartureTime().getDay()==0)||(k.getDepartureTime().getDay()==6)){
										k.setPrice(k.getPrice()*2);
									}
									resultFlightsList.add(k);
								});
		Flights flights = new Flights(resultFlightsList);
		return flights;
	}

}
