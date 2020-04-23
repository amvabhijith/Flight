package com.altran.Flight.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.altran.Flight.model.Flight;


@Transactional
public interface FlightRepository extends JpaRepository<Flight, Long> {

	Flight findByFlightId(long flightId);
	
	List<Flight> findByDepartureTimeBetween(Date departureTimeStart,Date departureTimeEnd);
}
