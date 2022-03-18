import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {

    private int firstPositions;
    private int secondPositions;
    private int thirdPositions;
    private int driverPoints;
    private int racesParticipated;
    private int position;


    public Formula1Driver(){
    }

    public Formula1Driver(String team,String name,int age, String location,int first,int second,int third, int totalRaces,int points){
        setDriverTeam(team);
        setDriverName(name);
        setDriverAge(age);
        setDriverLocation(location);
        this.firstPositions=first;
        this.secondPositions=second;
        this.thirdPositions=third;
        this.racesParticipated=totalRaces;
        setDriverPoints(points);
    }


    @Override
    // clear all the driver details assigned previously
    public void clearDriverDetails(){
        setDriverName(null);
        setDriverAge(0);
        setDriverLocation(null);
        setDriverTeam(null);
        this.firstPositions=0;
        this.secondPositions=0;
        this.thirdPositions=0;
        this.racesParticipated=0;
    }

    @Override
    //displays all the driver details with the labels
    public void displayDriverDetails(){
        System.out.println("------------------------------------------------------");
        System.out.println("    "+getDriverTeam()+" - "+getDriverName());
        System.out.println("------------------------------------------------------");
        System.out.println("Driver Name                : "+getDriverName());
        System.out.println("Driver Age                 : "+getDriverAge());
        System.out.println("Driver Country             : "+getDriverLocation());
        System.out.println("Total first places         : "+getFirstPositions());
        System.out.println("Total second places        : "+getSecondPositions());
        System.out.println("Total third places         : "+getThirdPositions());
        System.out.println("Total Races Participated   : "+getRacesParticipated());
        System.out.println("Total Driver Points        : "+getDriverPoints());
        System.out.println("------------------------------------------------------");
    }

    //display selected statistics in a row for the F1 driver table
    public void driverDisplayForF1Table(){
        System.out.print(String.format("|   %-20s",getDriverTeam()));
        System.out.print(String.format("|  %-20s",getDriverName()));
        System.out.print(String.format("|  %-15s",getDriverLocation()));
        System.out.print(String.format("|  %-15d",getFirstPositions()));
        System.out.print(String.format("|  %-15d",getRacesParticipated()));
        System.out.println(String.format("|  %-10d|",getDriverPoints()));

    }

    //returns number of points scored when the position is passed as the parameter
    public int generateInstantPoints(int place) {
        int points = switch (place) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            case 5 -> 10;
            case 6 -> 8;
            case 7 -> 6;
            case 8 -> 4;
            case 9 -> 2;
            case 10 -> 1;
            default -> 0;
        };
        return points;
    }

    //calculates the total driver points according to the first second third positions
    public void calTotalPoints(){
        this.driverPoints= (firstPositions*generateInstantPoints(1))+(secondPositions*generateInstantPoints(2))
                           +(thirdPositions*generateInstantPoints(3));
    }

    //when a particular number of points is passed it will add to the total count of points
    public void addToTotalPoints(int p){
        this.driverPoints=this.driverPoints+p;
    }


    //getters and setters for all the variables

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

    public int getFirstPositions() {
        return firstPositions;
    }

    public void setFirstPositions(int firstPositions) {
        this.firstPositions = firstPositions;
    }

    public int getSecondPositions() {
        return secondPositions;
    }

    public void setSecondPositions(int secondPositions) {
        this.secondPositions = secondPositions;
    }

    public int getThirdPositions() {
        return thirdPositions;
    }

    public void setThirdPositions(int thirdPositions) {
        this.thirdPositions = thirdPositions;
    }

    public int getDriverPoints() {
        return driverPoints;
    }

    public void setDriverPoints(int driverPoints) {
        this.driverPoints = driverPoints;
    }

    public int getRacesParticipated() {
        return racesParticipated;
    }

    public void setRacesParticipated(int racesParticipated) {
        this.racesParticipated = racesParticipated;
    }


}
