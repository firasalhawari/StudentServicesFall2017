package models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Firas.Alhawari
 * 
 */
public class Event implements Serializable{
    private int eventId;
    private String nameEn;
    private String nameAr;
    private EventType type;
    private int capacity;
    private String placeEn;
    private String placeAr;
    private Timestamp date;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }   

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPlaceEn() {
        return placeEn;
    }

    public void setPlaceEn(String placeEn) {
        this.placeEn = placeEn;
    }

    public String getPlaceAr() {
        return placeAr;
    }

    public void setPlaceAr(String placeAr) {
        this.placeAr = placeAr;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }            
}
