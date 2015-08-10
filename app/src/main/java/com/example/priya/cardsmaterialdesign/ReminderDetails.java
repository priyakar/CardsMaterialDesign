package com.example.priya.cardsmaterialdesign;

public class ReminderDetails {

    private DataObject dataObject;
    private int hour;

    public ReminderDetails(DataObject dataObject) {
        this.dataObject = dataObject;
    }
    public ReminderDetails(){

    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    private int min;

}

