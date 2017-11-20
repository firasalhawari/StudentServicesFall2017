package beans;

import daos.EventTypesDao;
import daos.EventsDao;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import models.Event;
import models.EventType;

/**
 *
 * @author Firas.Alhawari
 * 
 */
@Named(value = "addEditEventBean")
@ViewScoped
public class AddEditEventBean implements Serializable{
    private ArrayList<EventType> eventTypes;
    private final EventTypesDao eventTypesDao = new EventTypesDao();
    private final EventsDao eventsDao = new EventsDao();
    private int eventId;
    private int eventTypeId;
    private String nameEn;
    private String nameAr;
    private String placeEn;
    private String placeAr;
    private int capacity;
    private Date date;   
    
    @Inject
    private SessionBean sessionBean;
    
    public AddEditEventBean() {        
    }
    
    @PostConstruct
    public void init(){                
        try {
            eventId = sessionBean.getSelectedItemId();
            //eventTypes = eventTypesDao.buildEventTypes();
            
            if(eventId > 0){
                Event event = eventsDao.getEvent(eventId);                
                nameEn = event.getNameEn();
                nameAr = event.getNameAr();
                placeEn = event.getPlaceEn();
                placeAr = event.getPlaceAr();
                capacity = event.getCapacity();
                date = event.getDate();
                eventTypeId = event.getType().getTypeId();
            }
        } catch (Exception ex) {
            Logger.getLogger(AddEditEventBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EventType> getEventTypes() {
        return eventTypes;
    }        

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }        
        
    public void saveEvent() {
        try {
            Event event = new Event();
            
            EventType eventType = eventTypes.get(eventTypeId - 1);
            event.setEventId(eventId);
            event.setType(eventType);
            event.setNameEn(nameEn);
            event.setNameAr(nameAr);
            event.setPlaceEn(placeEn);
            event.setPlaceAr(placeAr);
            event.setCapacity(capacity);
            event.setDate(new Timestamp(date.getTime()));
            
            if (sessionBean.getSelectedItemId() > 0) {
                eventsDao.updateEvent(event);
            } else {
                eventsDao.insertEvent(event);
            }
        } catch (Exception ex) {
            Logger.getLogger(AddEditEventBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        sessionBean.navigate("manage_events");
    }
}
