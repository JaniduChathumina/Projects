public class Patient {
    private String patientName ="0";
    private String patientSurname ;
    private int patientAge ;
    private String patientCity;
    private String patientNIC;
    private int patientVaccinationType;
    private String vaccination;

    public Patient(){}


    //method to add the patient to the particular booth
    public void addPatient(String name, String surname, int age, String city, String NICNumber, int vaccinationType){
        this.patientSurname = surname;
        this.patientName = name;
        this.patientAge = age;
        this.patientCity = city;
        this.patientNIC = NICNumber;
        this.patientVaccinationType = vaccinationType;
    }

    //gets patients full name
    public String getPatientFullName(){
        return this.patientName+" "+this.patientSurname;
    }

    //gets only patients first name
    public String getPatientName() {
        return this.patientName;
    }

    //gets the injected vaccination type
    public String getVaccinationType(){
        if(patientVaccinationType==1){
            vaccination="AstraZeneca";
        }else if(patientVaccinationType==2){
            vaccination="Sinopharm";
        }else if(patientVaccinationType==3){
            vaccination="Pfizer";
        }
        return vaccination;
    }

    //gets the patient age
    public int getPatientAge(){
        return patientAge;
    }

    //gets the patient city
    public String getPatientCity(){
        return patientCity;
    }

    //gets the patient NIC
    public String getPatientNIC(){
        return  patientNIC;
    }

    //returns all the patient details as a string
    public String patientDetails(){
        if(patientVaccinationType==1){
            vaccination="AstraZeneca";
        }else if(patientVaccinationType==2){
            vaccination="Sinopharm";
        }else if(patientVaccinationType==3){
            vaccination="Pfizer";
        }
        return patientName+" "+patientSurname+" "+patientAge+" "+patientCity+" "+patientNIC+" "+vaccination;
    }

}
