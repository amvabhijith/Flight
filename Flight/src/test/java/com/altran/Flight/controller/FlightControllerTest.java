package com.altran.Flight.controller;

import com.altran.Flight.model.Flight;
import com.altran.Flight.model.Flights;
import com.altran.Flight.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    void getAllFlights() {
    }*/

    @Test
    void getFlightByDepartureTime() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        List<Flight>  flightList = Arrays.asList(
                new Flight(1, formatter.parse("2020-04-25 14:30:00"),formatter.parse("2020-04-25 12:30:00"),"go air flight","delhi","g8-120",11260,180,"kochi"),
                new Flight(2, formatter.parse("2020-04-25 14:40:00"),formatter.parse("2020-04-25 12:40:00"),"go air flight","delhi","g8-119",10860,180,"bangalore"));
        Date departureTimeStart = formatter.parse("2020-04-25 00:00:00");
        Flights flights = new Flights(flightList);

        when(flightService.getFlightByDepartureTime(departureTimeStart)).thenReturn(flights);

        // when
        Flights flights1 = flightController.getFlightByDepartureTime(departureTimeStart);

        // then
        assertAll(
                ()-> assertNotNull(flights1.getFlights()),
                ()-> assertEquals(2,flights1.getFlights().size()),
                ()-> assertAll(
                        ()->flights.getFlights().parallelStream().forEach(k ->
                        {
                            LocalDate localDate = k.getDepartureTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            assertTrue(localDate.getDayOfMonth()==25);
                            assertTrue(localDate.getMonthValue()==04);
                            assertTrue(localDate.getYear()==2020);
                            if(k.getNumber().equals("g8-120")){
                                assertTrue(k.getPrice() == 5630*2);
                            } else if(k.getNumber().equals("g8-119")){
                                assertTrue(k.getPrice() == 5430*2);
                            }
                        })
                )
        );
    }
}