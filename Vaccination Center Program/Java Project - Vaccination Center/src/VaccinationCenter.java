import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class VaccinationCenter {


    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        String PatientName ;
        String patientSurname ;
        int patientAge ;
        String patientCity;
        String patientNIC;
        int patientVaccinationType;
        int boothNum = 0;

        final int[] vaccinations = {150};
        final int[] Pfizer = {50};
        final int[] AstraZeneca = {50};
        final int[] Sinopharm = {50};

        Patient [] patients = new Patient[6];
        for(int x=0; x<6; x++){
            patients[x]= new Patient();
        }
        Booth [] vaccinationBooths = new Booth[6];
        for(int x=0; x<6; x++){
            vaccinationBooths[x] = new Booth();
        }

        LinkedList<String> waitingList = new LinkedList<String>();

        class local {
            //adds a patient to a booth
            public void addingThePatient(int boothNum,String PatientName,String patientSurname,int patientAge,String patientCity,String patientNIC,int patientVaccinationType){
                patients[boothNum].addPatient(PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                vaccinationBooths[boothNum].setOccupation("yes");
                vaccinationBooths[boothNum].addPatientToBooth(PatientName, patientSurname);
                if(patientVaccinationType==1){
                    AstraZeneca[0]--;
                }else if(patientVaccinationType==2){
                    Sinopharm[0]--;
                }else{
                    Pfizer[0]--;
                }
                vaccinations[0]--;
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("   Successfully added " + PatientName + " " + patientSurname + " to Booth " + boothNum);
                System.out.println("------------------------------------------------------");
                System.out.println("");
            }
            //adds a patient to the waiting list
            public void addingToWaitingList(String PatientName,String patientSurname,int patientAge,String patientCity,String patientNIC,int patientVaccinationType){
                waitingList.addFirst(PatientName+" "+ patientSurname+" "+ patientAge+" "+patientCity+" "+patientNIC+" "+patientVaccinationType);
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println(" "+PatientName+" " +patientSurname+" was added to the Waiting List");
                System.out.println("------------------------------------------------------");
                System.out.println("");
            }
        }

        System.out.println("------------------------------------------------------");
        System.out.println("----                 initialised                  ----");
        System.out.println("------------------------------------------------------");
        System.out.println("");

        System.out.println("------------------------------------------------------");
        System.out.println("-----                Menu Options                -----");
        System.out.println("------------------------------------------------------");
        System.out.println("View all Vaccination Booths                100 or VVB");
        System.out.println("View all Empty Booths                      101 or VEB");
        System.out.println("Add Patient to a Booth                     102 or APB");
        System.out.println("Remove Patient from a Booth                103 or RPB");
        System.out.println("View Patient Sorted in alphabetical order  104 or VPS");
        System.out.println("Store Program Data into file               105 or SPD");
        System.out.println("Load Program Data from file                106 or LPD");
        System.out.println("View Remaining Vaccinations                107 or VRV");
        System.out.println("Add Vaccinations to the Stock              108 or AVS");
        System.out.println("Exit the Program                           999 or EXIT");
        System.out.println("------------------------------------------------------");

        while (true){
            System.out.print("Please enter the option : ");
            String option = input.next();

            if (option.equals("100") || option.equals("VVB") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----                  All Booths                -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
                for(int i =0 ; i<6 ; i++) {
                    vaccinationBooths[i].viewOccupation(i);
                }

                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("");
            }else if (option.equals("101") || option.equals("VEB") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----                Empty Booths                -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
                for(int i=0; i<6; i++) {
                    vaccinationBooths[i].viewEmptyBooths(i);
                }

                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("");

            } else if (option.equals("102") || option.equals("APB") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----         Adding Patient to a Booth          -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
                try {
                    System.out.print("Enter patient First Name  : ");
                    PatientName = input.next();
                    System.out.print("Enter patient surname  : ");
                    patientSurname = input.next();
                    System.out.print("Enter patient age  : ");
                    patientAge = input.nextInt();
                    System.out.print("Enter patient City  : ");
                    patientCity = input.next();
                    System.out.print("Enter patient NIC number : ");
                    patientNIC = input.next();
                    System.out.println("");
                    System.out.println(" AstraZeneca     1");
                    System.out.println(" Sinopharm       2");
                    System.out.println(" Pfizer          3");
                    System.out.println("");
                    System.out.print("Choose patient Vaccination Type (1,2 or 3) : ");
                    patientVaccinationType = input.nextInt();


                    if (patientVaccinationType == 1) {

                        if(vaccinationBooths[0].getBoothOccupation().equals("yes")){
                            if(vaccinationBooths[1].getBoothOccupation().equals("yes")){
                                new local().addingToWaitingList(PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }else{
                                boothNum = 1;
                                new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }

                        }else{
                            boothNum = 0;
                            new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                        }

                    } else if (patientVaccinationType == 2) {

                        if(vaccinationBooths[2].getBoothOccupation().equals("yes")){
                            if(vaccinationBooths[3].getBoothOccupation().equals("yes")){
                                new local().addingToWaitingList(PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }else{
                                boothNum = 3;
                                new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }
                        }else{
                            boothNum = 2;
                            new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                        }

                    } else if (patientVaccinationType == 3) {

                        if(vaccinationBooths[4].getBoothOccupation().equals("yes")){
                            if(vaccinationBooths[5].getBoothOccupation().equals("yes")){
                                new local().addingToWaitingList(PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }else{
                                boothNum = 5;
                                new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                            }
                        }else{
                            boothNum = 4;
                            new local().addingThePatient(boothNum,PatientName, patientSurname, patientAge, patientCity, patientNIC, patientVaccinationType);
                        }

                    } else {
                        System.out.println("");
                        System.out.println("------------------------------------------------------");
                        System.out.println("-----         Invalid Vaccination Type !         -----");
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        continue;
                    }


                }catch (Exception e){
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("-----             Invalid Data Entered !         -----");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");
                    String x = input.next(); // after catching the exception it skips the next input so string x input is skipped
                    continue;
                }



            }else if (option.equals("103") || option.equals("RPB") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----       Removing Patient from a Booth        -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
                try {
                    System.out.print("Enter booth number to remove the patient (0-5) : ");
                    int boothNumber = input.nextInt();
                    if (boothNumber > 5 || boothNumber < 0) {
                        System.out.println("");
                        System.out.println("------------------------------------------------------");
                        System.out.println("-----           Invalid Booth Number !           -----");
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        continue;
                    }
                    if(patients[boothNumber].getPatientName()=="0") {
                        System.out.println("");
                        System.out.println("------------------------------------------------------");
                        System.out.println("                   Booth "+boothNumber+" is Empty                  ");
                        System.out.println("------------------------------------------------------");
                        System.out.println("");

                    }else{
                        System.out.println("");
                        System.out.println("------------------------------------------------------");
                        System.out.println("  Successfully removed " + patients[boothNumber].getPatientFullName() + " from Booth " + boothNumber);
                        System.out.println("------------------------------------------------------");
                        System.out.println("");

                        vaccinationBooths[boothNumber].setOccupation("No");

                    }

                }catch (Exception e){
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("-----           Invalid Booth Number !           -----");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");
                    String x = input.next(); // after catching the exception it skips the next input so string x input is skipped
                    continue;
                }

                try {
                    String[] WListPatientInfo = waitingList.getLast().split(" ");

                    int Age = Integer.parseInt(WListPatientInfo[2]);
                    int VaccinationNo_ = Integer.parseInt(WListPatientInfo[5]);

                    if (WListPatientInfo[5].equals("1")) {
                        if (vaccinationBooths[0].getBoothOccupation() == "yes") {
                            if (vaccinationBooths[1].getBoothOccupation() == "No") {
                                waitingList.removeLast();
                                new local().addingThePatient(1, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                            }
                        } else {
                            waitingList.removeLast();
                            new local().addingThePatient(0, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                        }
                    } else if (WListPatientInfo[5].equals("2")) {
                        if (vaccinationBooths[2].getBoothOccupation() == "yes") {
                            if (vaccinationBooths[3].getBoothOccupation() == "No") {
                                waitingList.removeLast();
                                new local().addingThePatient(3, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                            }
                        } else {
                            waitingList.removeLast();
                            new local().addingThePatient(2, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                        }

                    } else if (WListPatientInfo[5].equals("3")) {

                        if (vaccinationBooths[4].getBoothOccupation() == "yes") {
                            if (vaccinationBooths[5].getBoothOccupation() == "No") {
                                waitingList.removeLast();
                                new local().addingThePatient(5, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                            }
                        } else {
                            waitingList.removeLast();
                            new local().addingThePatient(4, WListPatientInfo[0], WListPatientInfo[1], Age, WListPatientInfo[3], WListPatientInfo[4], VaccinationNo_);
                        }

                    }
                }catch(Exception e){
                    continue;
                }

            } else if (option.equals("104") || option.equals("VPS") ) {

                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----   Viewing Patients in Alphabetical order   -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");

                String [][] allDetails = new String[6][6];

                for(int y=0; y<6; y++) {
                    String PersonDetails = patients[y].patientDetails();
                    String[] patientInformation = PersonDetails.split(" ");
                    for (int x = 0; x < 6; x++) {
                        allDetails[y][x] = patientInformation[x];
                    }
                }
                String[] temp = new String[1];
                for (int i = 0; i < 6; i++) {
                    for (int j = i + 1; j < 6; j++) {

                        // to compare one string with other strings
                        if (allDetails[i][0].compareTo(allDetails[j][0]) > 0) {
                            // swapping
                            temp = allDetails[i];
                            allDetails[i] = allDetails[j];
                            allDetails[j] = temp;
                        }

                    }
                }

                for(int y=0; y<6; y++){
                    if(!allDetails[y][0].equals("0")) {
                        System.out.println("Patient Full Name         : "+allDetails[y][0]+" "+allDetails[y][1]);
                        System.out.println("Patient Age               : "+allDetails[y][2]);
                        System.out.println("Patient City              : "+allDetails[y][3]);
                        System.out.println("Patient NIC Number        : "+allDetails[y][4]);
                        System.out.println("Injected Vaccination      : "+allDetails[y][5]);
                        System.out.println("");
                    }

                }

                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("");

            }else if (option.equals("105") || option.equals("SPD") ) {
                try {
                    FileWriter myWriter = new FileWriter("C:\\Users\\Janidu Chathumina\\Desktop\\SD CW version 2\\Task 4\\WriteFile.txt");
                    myWriter.write("-------------------------------------------\n");
                    myWriter.write("    Occupation of Vaccination Booths \n");
                    myWriter.write("-------------------------------------------\n");
                    myWriter.write(" \n");

                    for (int x=0 ; x<6 ; x++) {
                        if (patients[x].getPatientName().equals("0")){ myWriter.write("    Booth " + x + " is not occupied \n");}
                        else{ myWriter.write("    Booth " + x + " is occupied by " + patients[x].getPatientFullName() +" \n");}
                    }
                    myWriter.write(" \n");
                    myWriter.write("-------------------------------------------\n");
                    myWriter.write("             Patient Details \n");
                    myWriter.write("-------------------------------------------\n");
                    for(int x=0; x<6; x++){
                        if(patients[x].getPatientName()!="0"){
                            myWriter.write(" \n");
                            myWriter.write( "Patient Name     : "+patients[x].getPatientFullName() +" \n");
                            myWriter.write( "Patient Age      : "+patients[x].getPatientAge() +" \n");
                            myWriter.write( "Patient City     : "+patients[x].getPatientCity() +" \n");
                            myWriter.write( "Patient NIC      : "+patients[x].getPatientNIC() +" \n");
                            myWriter.write( "Vaccination Type : "+patients[x].getVaccinationType() +" \n");
                            myWriter.write(" \n");
                        }
                    }
                    myWriter.write(" \n");
                    myWriter.write("-------------------------------------------\n");
                    myWriter.write("    "+ vaccinations[0] +" vaccinations are remaining \n");
                    myWriter.write("-------------------------------------------\n");
                    myWriter.write("  Pfizer-"+ Pfizer[0] +"  Sinopharm-"+ Sinopharm[0] +"  AstraZeneca-"+ AstraZeneca[0] +"\n");
                    myWriter.write("-------------------------------------------\n");

                    myWriter.flush();
                    myWriter.close();

                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("-----   Successfully wrote the Data into a File  -----");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");

                } catch (IOException e) {
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("       File not Found ! Please Try Again Later.       ");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");
                    continue;
                }


            }else if (option.equals("106") || option.equals("LPD") ) {
                System.out.println("------------------------------------------------------");
                try {
                    File myObj = new File("C:\\Users\\Janidu Chathumina\\Desktop\\SD CW version 2\\Task 4\\DataFile.txt");
                    Scanner inputReader = new Scanner(myObj);
                    while (inputReader.hasNextLine()){
                        String data = inputReader.nextLine();
                        System.out.println("     "+data);
                    }
                    inputReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("       File not Found ! Please Try Again Later.       ");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");
                    continue;


                }
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----     Successfully Loaded Data from File     -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");

            }else if (option.equals("107") || option.equals("VRV") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----      "+ vaccinations[0] +" vaccinations are remaining        -----");
                System.out.println("------------------------------------------------------");
                System.out.println("      Pfizer-"+ Pfizer[0] +"   Sinopharm-"+ Sinopharm[0] +"   AstraZeneca-"+ AstraZeneca[0]);
                System.out.println("------------------------------------------------------");
                System.out.println("");

            }else if (option.equals("108") || option.equals("AVS") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----      Adding Vaccinations to the stock      -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
                System.out.println(" Pfizer       1");
                System.out.println(" Sinopharm    2");
                System.out.println(" AstraZeneca  3");
                System.out.print("Choose Vaccination Type : ");
                try {
                    int vaccinationType = input.nextInt();
                    System.out.print("Enter the number of vaccinations to be added : ");
                    int addVaccination = input.nextInt();
                    String VaccinationName;
                    if (vaccinationType == 1) {
                        Pfizer[0] += addVaccination;
                        VaccinationName = "Pfizer";
                    } else if (vaccinationType == 2) {
                        Sinopharm[0] += addVaccination;
                        VaccinationName = "Sinopharm";
                    } else if (vaccinationType == 3) {
                        AstraZeneca[0] += addVaccination;
                        VaccinationName = "AstraZeneca";
                    } else {
                        System.out.println("");
                        System.out.println("------------------------------------------------------");
                        System.out.println("-----         Invalid Vaccination Type !         -----");
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        continue;
                    }
                    vaccinations[0] +=addVaccination;
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println(" Successfully added " + addVaccination + " " +VaccinationName + " to the stock");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");

                }catch (Exception e){
                    System.out.println("");
                    System.out.println("------------------------------------------------------");
                    System.out.println("-----           Invalid Data Entered !           -----");
                    System.out.println("------------------------------------------------------");
                    System.out.println("");
                    String x = input.next(); // after catching the exception it skips the next input so string x input is skipped
                    continue;
                }


            }else if (option.equals("999") || option.equals("EXIT") ) {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----                 THANK YOU !                -----");
                System.out.println("------------------------------------------------------");
                break;
            }else {
                System.out.println("");
                System.out.println("------------------------------------------------------");
                System.out.println("-----              Invalid Option !              -----");
                System.out.println("------------------------------------------------------");
                System.out.println("");
            }

            if (Pfizer[0] <=20 || Sinopharm[0] <=20 || AstraZeneca[0] <=20) {
                System.out.println("------------------------------------------------------");
                System.out.println("-----    Warning !   Low Vaccination Level       -----");
                System.out.println("------------------------------------------------------");
                System.out.println("-----          "+ vaccinations[0] +" Vaccines Remaining            -----");
                System.out.println("------------------------------------------------------");
                System.out.println("      Pfizer-"+ Pfizer[0] +"   Sinopharm-"+ Sinopharm[0] +"   AstraZeneca-"+ AstraZeneca[0]);
                System.out.println("------------------------------------------------------");
            }
        }

    }
}
