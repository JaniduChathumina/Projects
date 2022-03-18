public class Booth {

    private String patientName ="0";
    private String boothOccupation ="No" ;
    private String patientSurname = "0" ;

    public Booth (){
    }

    //sets the occupation of the booth(yes or No)
    public void setOccupation(String Occupation) {
        this.boothOccupation = Occupation;
    }

    //gets the occupation(yes or No)
    public String getBoothOccupation(){
        return boothOccupation;
    }
    //view the occupation of the booth with full name or if not occupied, it returns not occupied
    public void viewOccupation(int boothNum){
        if (this.boothOccupation =="yes") {
            System.out.println("Booth " + boothNum + " is occupied by "+this.patientName+" "+this.patientSurname);
        }else{System.out.println("Booth " + boothNum + " is not occupied");}
    }

    //returns "Booth not occupied"  only if booth is empty
    public void viewEmptyBooths(int boothNum){
        if(this.boothOccupation =="No"){
            System.out.println("Booth " + boothNum + " is not occupied");
        }
    }

    // adds current patient full name to booth
    public void addPatientToBooth(String name, String surname){
        this.patientName=name;
        this.patientSurname=surname;
    }

}
