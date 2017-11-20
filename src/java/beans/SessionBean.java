package beans;

import java.io.Serializable;
import java.sql.Connection;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @Author: Dr. Firas Al-Hawari
 *
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private String username;
    private String password;

    // Session attributes

    private Connection connection; 
    private int selectedItemId; 

    private int selectedBusID; 
    private int selectedStudentID;
    private int selectedDriverID;



    private int menuIndex = 0;

    public SessionBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(int selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public int getSelectedBusID() {
        return selectedBusID;
    }

    public void setSelectedBusID(int selectedBusID) {
        this.selectedBusID = selectedBusID;
    } 
    

    public int getSelectedStudentID() {

        return selectedStudentID;
    }

    public void setSelectedStudentID(int selectedStudentID) {
        this.selectedStudentID = selectedStudentID;
    }
    
             public int getSelectedDriverID() {
        return selectedDriverID;
    }

    public void setSelectedDriverID(int selectedDriverID) {
        this.selectedDriverID = selectedDriverID;
    }


    public int getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public void login() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean success = true;

        try {

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

        }

        if (success) {
            navigate("/first_page");
        }
    }

    public void logout() throws Exception {
        try {
            // Release and close database resources and connections 
            if (connection != null) {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }

                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            setPassword(null);
            setUsername(null);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().invalidateSession();
        }
    }

    public void bus_position() {
        navigate("/bus_reservation/student_pages/bus_position.xhtml");
    }

    public void bus_roadmap() {
        navigate("/bus_reservation/student_pages/road_map.xhtml");
    }

    public void bus_seatRes() {
        navigate("/bus_reservation/student_pages/seat_reservation.xhtml");
    }

    public void bus_checkin() {
        navigate("/bus_reservation/student_pages/check_in.xhtml");
    }

    public void bus_status() {
        navigate("/bus_reservation/driver_pages/bus_status.xhtml");
    }
    
        public void Contact_us() {
        navigate("/bus_reservation/bus_reservation.xhtml");
    }

    public void bus_std_info() {
        navigate("/bus_reservation/admin_pages/student_info.xhtml");
    }

    public void bus_info() {
        navigate("/bus_reservation/admin_pages/bus_info.xhtml");
    }

    public void bus_driver_info() {
        navigate("/bus_reservation/admin_pages/driver_info.xhtml");
    }

    public void bus_admin_position() {
        navigate("/bus_reservation/admin_pages/bus_position.xhtml");
    }

    public void navigate(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null) {
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
            navigationHandler.handleNavigation(facesContext, null, url + "?faces-redirect=true");
        }
    }
}
