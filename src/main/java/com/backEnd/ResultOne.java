package com.backEnd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResultOne {
    private final SimpleStringProperty planeType = new SimpleStringProperty();
    private final SimpleIntegerProperty flightsNum = new SimpleIntegerProperty();
    private final SimpleIntegerProperty minDepTime = new SimpleIntegerProperty();

    public ResultOne() {
        this.planeType.set("");
        this.flightsNum.set(0);
        this.minDepTime.set(0);
    }

    public ResultOne(String planeType, int flightsNum, int minDepTime) {
        this.planeType.set(planeType);
        this.flightsNum.set(flightsNum);
        this.minDepTime.set(minDepTime);
    }

    public String getPlaneType() {
        return planeType.get();
    }

    public SimpleStringProperty planeTypeProperty() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType.set(planeType);
    }

    public int getFlightsNum() {
        return flightsNum.get();
    }

    public SimpleIntegerProperty flightsNumProperty() {
        return flightsNum;
    }

    public void setFlightsNum(int flightsNum) {
        this.flightsNum.set(flightsNum);
    }

    public int getMinDepTime() {
        return minDepTime.get();
    }

    public SimpleIntegerProperty minDepTimeProperty() {
        return minDepTime;
    }

    public void setMinDepTime(int minDepTime) {
        this.minDepTime.set(minDepTime);
    }
}
