package com.altran.Flight.service;

import com.altran.Flight.model.Flight;
import com.altran.Flight.model.Flights;
import com.altran.Flight.repo.FlightRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class FlightServiceTest {

    @InjectMocks
    FlightService flightService;

    @Mock
    FlightRepository flightRepo;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Test method for getting flight details by departure time")
    class FlightByDepartureTimeTest{

        @Test
        @DisplayName("Test getFlightByDepartureTime weekdays")
        void getFlightByDepartureTimeWeekday() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            List<Flight>  flightList = Arrays.asList(
                    new Flight(1, formatter.parse("2020-04-23 14:30:00"),formatter.parse("2020-04-23 12:30:00"),"go air flight","delhi","g8-120",5630,180,"bangalore"),
                    new Flight(2, formatter.parse("2020-04-23 14:30:00"),formatter.parse("2020-04-23 12:40:00"),"go air flight","delhi","g8-119",5430,180,"kochi"));
            Date departureTimeStart = formatter.parse("2020-04-23 00:00:00");

            when(flightRepo.findByDepartureTimeBetween(eq(departureTimeStart),any(Date.class))).thenReturn(flightList);

            Flights flights = flightService.getFlightByDepartureTime(departureTimeStart);

            assertAll(
                    ()-> assertNotNull(flights.getFlights()),
                    ()-> assertEquals(2,flights.getFlights().size()),
                    ()-> assertAll(
                            ()->flights.getFlights().parallelStream().forEach(k ->
                            {
                                LocalDate localDate = k.getDepartureTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                assertEquals(localDate.getDayOfMonth(),23);
                                assertEquals(localDate.getMonthValue(),04);
                                assertEquals(localDate.getYear(),2020);
                                if(k.getNumber().equals("g8-120")){
                                    assertEquals(k.getPrice() , 5630);
                                } else if(k.getNumber().equals("g8-119")){
                                    assertEquals(k.getPrice() , 5430);
                                }
                            })
                    )
            );
        }

        @Test
        @DisplayName("Test getFlightByDepartureTime Negative")
        void getFlightByDepartureTimeNegative() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            List<Flight>  flightList = Arrays.asList(
                    new Flight(1, formatter.parse("2020-04-25 14:30:00"),formatter.parse("2020-04-25 12:30:00"),"go air flight","delhi","g8-120",5630,180,"bangalore"),
                    new Flight(2, formatter.parse("2020-04-25 14:30:00"),formatter.parse("2020-04-25 12:40:00"),"go air flight","delhi","g8-119",5430,180,"kochi"));
            Date departureTimeStart = formatter.parse("24-04-2020 00:00:00");

            when(flightRepo.findByDepartureTimeBetween(eq(formatter.parse("2020-04-25 14:30:00")),any(Date.class))).thenReturn(flightList);

            Flights flights = flightService.getFlightByDepartureTime(departureTimeStart);

            assertAll(
                    ()-> assertNotNull(flights.getFlights()),
                    ()-> assertEquals(0,flights.getFlights().size())
            );
        }

        @Test
        @DisplayName("Test getFlightByDepartureTime Positive")
        void getFlightByDepartureTimePositive() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            List<Flight>  flightList = Arrays.asList(
                    new Flight(1, formatter.parse("2020-04-25 14:30:00"),formatter.parse("2020-04-25 12:30:00"),"go air flight","delhi","g8-120",5630,180,"bangalore"),
                    new Flight(2, formatter.parse("2020-04-25 14:30:00"),formatter.parse("2020-04-25 12:40:00"),"go air flight","delhi","g8-119",5430,180,"kochi"));
            Date departureTimeStart = formatter.parse("2020-04-25 00:00:00");

            when(flightRepo.findByDepartureTimeBetween(eq(departureTimeStart),any(Date.class))).thenReturn(flightList);

            Flights flights = flightService.getFlightByDepartureTime(departureTimeStart);

            assertAll(
                    ()-> assertNotNull(flights.getFlights()),
                    ()-> assertEquals(2,flights.getFlights().size()),
                    ()-> assertAll(
                                ()->flights.getFlights().parallelStream().forEach(k ->
                                {
                                    LocalDate localDate = k.getDepartureTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    assertEquals(localDate.getDayOfMonth(),25);
                                    assertEquals(localDate.getMonthValue(),04);
                                    assertEquals(localDate.getYear(),2020);
                                    if(k.getNumber().equals("g8-120")){
                                        assertEquals(k.getPrice() , 5630*2);
                                    } else if(k.getNumber().equals("g8-119")){
                                        assertEquals(k.getPrice() , 5430*2);
                                    }
                                })
                    )
                    );
        }
    }
}