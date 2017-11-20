package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import models.EventType;

/**
 *
 * @author Dr. Firas Al-Hawari
 * 
 */
public class EventTypesDao extends ConnectionDao {     
    public ArrayList<EventType> buildEventTypes() throws Exception {
        ArrayList<EventType> list = new ArrayList<>();
        Connection conn = getConnection();
        
        try {            
            String sql = "SELECT * FROM EVENT_TYPES ORDER BY EVENT_TYPE_ID";                        
            PreparedStatement ps = conn.prepareStatement(sql);            

            ResultSet rs = ps.executeQuery();           

            while (rs.next()) {
                list.add(populateEventType(rs));
            }
            
            rs.close();
            ps.close();

            return list;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public HashMap<Integer, EventType> buildEventTypesMap() throws Exception {
        HashMap<Integer, EventType> map = new HashMap<>();
        Connection conn = getConnection();
        
        try {            
            String sql = "SELECT * FROM EVENT_TYPES ORDER BY EVENT_TYPE_ID";                        
            PreparedStatement ps = conn.prepareStatement(sql);            

            ResultSet rs = ps.executeQuery();           

            while (rs.next()) {
                EventType eventType = populateEventType(rs);
                map.put(eventType.getTypeId(), eventType);
            }
            
            rs.close();
            ps.close();

            return map;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private EventType populateEventType(ResultSet rs) throws SQLException {
        EventType eventType = new EventType();
        
        eventType.setTypeId(rs.getInt("EVENT_TYPE_ID"));
        eventType.setNameAr(rs.getString("NAME_AR"));
        eventType.setNameEn(rs.getString("NAME_EN"));                    
        
        return eventType;
    }   
}
