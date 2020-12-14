package com.backEnd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Flights {

    private ObservableList<Flight> flights;

    private Long count = 1L;

    public Flights() {
        this.flights = FXCollections.observableList(new ArrayList<>());
    }

    public Flights(ObservableList<Flight> flights) {
        this.flights = flights;
    }

    public void clear(){
        this.count = 1L;
        this.flights.clear();
    }

    public ObservableList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ObservableList<Flight> flights) {
        this.flights = flights;
    }

    public Flight getById(Long id) {
        for (Flight el : flights) {
            if (el.getId() == id) return el;
        }
        return null;
    }

    public void add(Flight flight) {
        flight.setId(count++);
        if (getById(flight.getId()) != null) return;
        flights.add(flight);
    }

    public boolean remove(long id) {
        if (flights == null || getById(id) == null) return false;
        return flights.remove(getById(id));
    }

    public void sort() {
        Comparator<Flight> comparator = Comparator.comparing(Flight::getDestination).thenComparing(Flight::getDepartTime);
        flights.sort(comparator);
    }

    public void removeByCondition(String value) {
        this.getFlights().removeIf(n -> (n.getDestination().equals(value)));
    }

    public Flights filter(String value) {
        var flights = new Flights();
        for (var el : this.getFlights()) {
            if (el.getPlaneType().toLowerCase().startsWith(value.toLowerCase())) {
                flights.add(el);
            }
        }
        return flights;
    }

    public ObservableList<ResultOne> result1(){
        ObservableList<ResultOne> result = FXCollections.observableList(new ArrayList<>());
        List<String> flightsTypeList = new ArrayList<>();

        for (var flight : getFlights()) {
            boolean flag = false;
            for (var el : flightsTypeList) {
                if (flight.getPlaneType().equals(el)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) flightsTypeList.add(flight.getPlaneType());
        }

        for (var el : flightsTypeList) {
            var min = Integer.MAX_VALUE;
            var i = 0;
            for (var flight : getFlights()) {
                if (el.equals(flight.getPlaneType())) {
                    if (flight.getTotalTime() < min) min = flight.getTotalTime();
                    i++;
                }
            }
            result.add(new ResultOne(el, i, min));
        }

        return result;
    }

    public int result2() {
        List<String> destinationsList = new ArrayList<>();

        for (var flight : getFlights()) {
            boolean flag = false;
            for (var el : destinationsList) {
                if (flight.getDestination().equals(el)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) destinationsList.add(flight.getDestination());
        }

        return destinationsList.size();
    }
}
