package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import models.Event;
import models.EventType;

/**
 *
 * @author Firas.Alhawari
 * 
 */
@Named(value = "eventsBean")
@SessionScoped
public class EventsBean implements Serializable {
    private ArrayList<Event> events;
    private ArrayList<EventType> eventTypes;
    
    public EventsBean() {
        eventTypes = new ArrayList<>();
        events = new ArrayList<>();
        
        for(int i = 1; i <= 3; i++){
            switch(i){
                case 1: eventTypes.add(new EventType(i, "Match", "مباراة"));
                    break;
                case 2: eventTypes.add(new EventType(i, "Wedding", "عرس"));
                    break;
                case 3: eventTypes.add(new EventType(i, "Other", "غير ذلك"));
                    break;
            }
        }        
    }        

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    } 

    public ArrayList<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(ArrayList<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }        
}