package com.altran.Flight.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.Flight.model.Flight;
import com.altran.Flight.model.Flights;
import com.altran.Flight.repo.FlightRepository;


@Service
public class FlightService{

	@Autowired
	private FlightRepository flightRepo;

//	public List<Flight> getAllFlights() {
//		return flightRepo.findAll();
//	}

	public Flights getFlightByDepartureTime(Date departureTime) throws ParseException {
		Date departureTimeStart = departureTime;
		Calendar c = Calendar.getInstance();
		c.setTime(departureTime);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		Date departureTimeEnd = c.getTime();
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
