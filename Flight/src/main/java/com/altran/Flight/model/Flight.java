package com.altran.Flight.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="FLIGHT")
public class Flight {
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long flightId;
	
	@Column(name = "flightNumber")
	private String number; 
    
	@Column(name = "price")
    private int price;
    
    @Column(name = "departureLocation")
    private String from;
    
    @Column(name = "destination")
    private String to;  
    
    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH")
    private Date departureTime;     
    
    @Column(name = "arrival_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH")
    private Date arrivalTime;
    
    @Column(name = "seats_left")
    private int seatsLeft; 
    
    private String description;
    
    @OneToOne(cascade={CascadeType.MERGE},fetch=FetchType.EAGER)
    private Plane plane;

	
	public Flight() {
		super();
	}
    
    public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	} 

}
