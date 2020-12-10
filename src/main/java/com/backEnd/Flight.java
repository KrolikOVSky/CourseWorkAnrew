/**
 * This single object
 */

package com.backEnd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
    private final String OUTPUT_FORMAT = "\nID =\t%d\nFlight code =\t%s\nPlane type =\t%s\nDestination =\t%s\nDepart time\t%s\nTotal time =\t%d\n";
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty flightCode = new SimpleStringProperty();
    private final SimpleStringProperty planeType = new SimpleStringProperty();
    private final SimpleStringProperty destination = new SimpleStringProperty();
    private final SimpleStringProperty departTime = new SimpleStringProperty();
    private final SimpleIntegerProperty totalTime = new SimpleIntegerProperty();

    public Flight(String flightCode, String planeType, String destination, String departTime, int totalTime) {
        this.flightCode.set(flightCode);
        this.planeType.set(planeType);
        this.destination.set(destination);
        this.departTime.set(departTime);
        this.totalTime.set(totalTime);
    }

    public Flight() {
        this.flightCode.set("");
        this.planeType.set("");
        this.destination.set("");
        this.departTime.set("");
        this.totalTime.set(0);
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getFlightCode() {
        return flightCode.get();
    }

    public void setFlightCode(String flightCode) {
        this.flightCode.set(flightCode);
    }

    public SimpleStringProperty flightCodeProperty() {
        return flightCode;
    }

    public String getPlaneType() {
        return planeType.get();
    }

    public void setPlaneType(String planeType) {
        this.planeType.set(planeType);
    }

    public SimpleStringProperty planeTypeProperty() {
        return planeType;
    }

    public String getDestination() {
        return destination.get();
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public String getDepartTime() {
        return departTime.get();
    }

    public SimpleStringProperty departTimeProperty() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime.set(departTime);
    }

    public int getTotalTime() {
        return totalTime.get();
    }

    public SimpleIntegerProperty totalTimeProperty() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime.set(totalTime);
    }

    @Override
    public String toString() {
        return String.format(OUTPUT_FORMAT, getId(), getFlightCode(), getPlaneType(), getDestination(), getDepartTime(), getTotalTime());
    }
}
