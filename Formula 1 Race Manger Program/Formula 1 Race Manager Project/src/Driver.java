import java.io.Serializable;

public abstract class Driver implements Serializable {

    private String driverName;
    private String driverLocation;
    private int driverAge;
    private String driverTeam;

    public Driver() {

    }

    //can be used to display all statics with labels
    public abstract void displayDriverDetails();

    //can be used to clear all the details of a driver
    public abstract void clearDriverDetails();








    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }


}
