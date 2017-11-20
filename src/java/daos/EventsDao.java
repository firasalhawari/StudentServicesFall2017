package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Event;
import models.EventType;

/**
 *
 * @author Dr. Firas Al-Hawari
 * 
 */
public class EventsDao extends ConnectionDao {     
    public ArrayList<Event> buildEvents(HashMap<Integer, EventType> eventTypes) 
            throws Exception {
        ArrayList<Event> list = new ArrayList<>();        
        
        try {   
            Connection conn = getConnection();
            
            String sql = "SELECT * FROM EVENTS";                        
            PreparedStatement ps = conn.prepareStatement(sql);            

            ResultSet rs = ps.executeQuery();           

            while (rs.next()) {
                list.add(populateEventWithType(rs, eventTypes));
            }
            
            rs.close();
            ps.close();
            
            return list;            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private Event populateEventWithType(ResultSet rs, HashMap<Integer, EventType> eventTypes) 
            throws SQLException {
        Event event = new Event();
        
        event.setEventId(rs.getInt("EVENT_ID"));
        event.setNameAr(rs.getString("NAME_AR"));
        event.setNameEn(rs.getString("NAME_EN"));
        event.setPlaceAr(rs.getString("PLACE_AR"));
        event.setPlaceEn(rs.getString("PLACE_EN"));
        event.setCapacity(rs.getInt("CAPACITY"));
        event.setDate(rs.getTimestamp("EVENT_DATE"));
        
        EventType eventType = eventTypes.get(rs.getInt("EVENT_TYPE_ID"));        
        event.setType(eventType);                
        
        return event;
    }
    
    private Event populateEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        
        event.setEventId(rs.getInt("EVENT_ID"));
        event.setNameAr(rs.getString("NAME_AR"));
        event.setNameEn(rs.getString("NAME_EN"));
        event.setPlaceAr(rs.getString("PLACE_AR"));
        event.setPlaceEn(rs.getString("PLACE_EN"));
        event.setCapacity(rs.getInt("CAPACITY"));
        event.setDate(rs.getTimestamp("EVENT_DATE"));
        
        EventType eventType = new EventType();
        eventType.setTypeId(rs.getInt("EVENT_TYPE_ID"));        
        event.setType(eventType);                
        
        return event;
    }
    
    public void insertEvent(Event event) throws Exception {                
        try {
            Connection conn = getConnection();
            
            String sql = "INSERT INTO EVENTS (EVENT_ID,"
                    + " NAME_AR,"
                    + " NAME_EN,"
                    + " PLACE_AR,"
                    + " PLACE_EN,"
                    + " EVENT_DATE,"
                    + " CAPACITY,"
                    + " EVENT_TYPE_ID)"
                    + " VALUES ((select max(EVENT_ID) from EVENTS)+1,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql); 
            
            ps.setString(1, event.getNameAr());
            ps.setString(2, event.getNameEn());
            ps.setString(3, event.getPlaceAr());
            ps.setString(4, event.getPlaceEn());
            ps.setTimestamp(5, event.getDate());
            ps.setInt(6, event.getCapacity());
            ps.setInt(7, event.getType().getTypeId());
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public void updateEvent(Event event) throws Exception {
        try {
            Connection conn = getConnection();

            String sql = "UPDATE EVENTS SET NAME_AR=?,"
                    + " NAME_EN=?,"
                    + " PLACE_AR=?,"
                    + " PLACE_EN=?,"
                    + " EVENT_DATE=?,"
                    + " CAPACITY=?,"
                    + " EVENT_TYPE_ID=?"
                    + " WHERE EVENT_ID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, event.getNameAr());
            ps.setString(2, event.getNameEn());
            ps.setString(3, event.getPlaceAr());
            ps.setString(4, event.getPlaceEn());
            ps.setTimestamp(5, event.getDate());
            ps.setInt(6, event.getCapacity());
            ps.setInt(7, event.getType().getTypeId());            
            ps.setInt(8, event.getEventId());

            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public void deleteEvent(int eventId) throws Exception {
        Connection conn = getConnection();
        
        try {
            String sql = "DELETE FROM EVENTS WHERE EVENT_ID=?";                               
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public Event getEvent(int eventId) throws Exception {
        try {   
            Event event = null;
            Connection conn = getConnection();
            
            String sql = "SELECT EVENTS.*, "
                    + " EVENT_TYPES.NAME_EN as TYPE_EN,"
                    + " EVENT_TYPES.NAME_AR as TYPE_AR "
                    + " FROM EVENTS, EVENT_TYPES "
                    + " WHERE EVENTS.EVENT_TYPE_ID=EVENT_TYPES.EVENT_TYPE_ID AND"
                    + " EVENT_ID=?";                        
            PreparedStatement ps = conn.prepareStatement(sql);            
            ps.setInt(1, eventId);
            
            ResultSet rs = ps.executeQuery();           

            while (rs.next()) {
                event = populateEvent(rs);
                event.getType().setNameEn(rs.getString("TYPE_EN"));
                event.getType().setNameAr(rs.getString("TYPE_AR"));
            }

            rs.close();
            ps.close();
            
            return event;            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
            
    public static void main(String [] args){        
        try {
            EventsDao dao = new EventsDao();                
            //ArrayList<Event> events = dao.buildEvents();
        } catch (Exception ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
