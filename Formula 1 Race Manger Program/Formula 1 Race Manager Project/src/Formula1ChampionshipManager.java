import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Formula1ChampionshipManager implements ChampionshipManager,Serializable {

    private static String teamName;
    private static int teamNumber=0;
    private static int replaceDNum;
    private static Date raceDate;

    //array list to store all the competitor details who finishes a race
    protected static ArrayList<String> CompletedRaces = new ArrayList<>();


    //method which controls the F1Championship by giving functionality to the menu
    public void ManageChampionship() {


        printProgramMenu();

        //initialisation
        for (int x = 0; x < 10; x++) {
            F1Array[x] = new Formula1Driver();
        }
        for (int x = 0; x < 10; x++) {
            F1ParticipatingTeams[x] = "";
        }

        //loads the program data from the F1.ser file
        deserializeData();


        while (true) {

            System.out.println("");
            System.out.print("Enter the choice : ");
            String userInput = input.next();
            System.out.println("");

            //adding a new driver with a new team to the F1Championship
            if (userInput.equals("001")) {

                System.out.println("------------------------------------------------------");
                System.out.println("                  Adding a new Driver                 ");
                System.out.println("------------------------------------------------------");
                printF1TeamsTable();
                System.out.print("Enter the team number : ");
                try {
                    teamNumber = input.nextInt();
                    if (teamNumber > 10 || teamNumber < 1) {
                        System.out.println("------------------------------------------------------");
                        System.out.println("                   Invalid Team !                     ");
                        System.out.println("------------------------------------------------------");
                    } else {
                        teamName = selectTeam(teamNumber);

                        //check whether the team already exists
                        if (!F1ParticipatingTeams[teamNumber - 1].equals(teamName)) {

                            F1ParticipatingTeams[teamNumber - 1] = teamName; //adding the team to existing teams array

                            setDriverDetails(teamNumber);

                            System.out.println("------------------------------------------------------");
                            System.out.println("   Successfully added " + F1Array[teamNumber - 1].getDriverName() + " to " + teamName);
                            System.out.println("------------------------------------------------------");

                        } else {
                            System.out.println("------------------------------------------------------");
                            System.out.println("                 Team Already Exists                  ");
                            System.out.println("------------------------------------------------------");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("                 Invalid Data Entered !               ");
                    System.out.println("------------------------------------------------------");
                    //clears previously half entered info
                    if (teamNumber != 0) {
                        F1ParticipatingTeams[teamNumber - 1] = "";
                        F1Array[teamNumber - 1].clearDriverDetails();
                    }
                    String skip = input.nextLine(); //to bypass the input skipping
                }

            //deleting a driver along with the constructor team
            } else if (userInput.equals("002")) {
                System.out.println("------------------------------------------------------");
                System.out.println("             Deleting Team and Driver                ");
                System.out.println("------------------------------------------------------");
                printF1TeamsTable();
                System.out.print("Enter the team number which you want to delete : ");
                try {
                    int delNum = input.nextInt();
                    if (delNum > 10 || delNum < 1) {
                        System.out.println("------------------------------------------------------");
                        System.out.println("                   Invalid Team !                     ");
                        System.out.println("------------------------------------------------------");
                    } else {
                        //checks if the particular team exist to proceed deleting
                        if (!F1ParticipatingTeams[delNum - 1].equals("")) {
                            F1Array[delNum - 1].clearDriverDetails();

                            System.out.println("------------------------------------------------------");
                            System.out.println("      Successfully deleted team " + F1ParticipatingTeams[delNum - 1] + "             ");
                            System.out.println("------------------------------------------------------");
                            F1ParticipatingTeams[delNum - 1] = "";
                        } else {
                            System.out.println("------------------------------------------------------");
                            System.out.println("                Team does not exist !                 ");
                            System.out.println("------------------------------------------------------");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("                 Invalid Data Entered !               ");
                    System.out.println("------------------------------------------------------");
                    String skip = input.nextLine(); //to bypass the input skipping
                }

            //changing the driver for an existing constructor team
            } else if (userInput.equals("003")) {
                System.out.println("------------------------------------------------------");
                System.out.println("          Changing Driver for existing team           ");
                System.out.println("------------------------------------------------------");
                printF1TeamsTable();
                System.out.print("Select the team to change the driver : ");
                try {
                    replaceDNum = input.nextInt();
                    //checks whether team exist or not to continue deleting
                    if (F1ParticipatingTeams[replaceDNum - 1].equals(selectTeam(replaceDNum))) {
                        System.out.println("Changing driver for team " + F1ParticipatingTeams[replaceDNum - 1] + " ........");

                        //runs the setDriverDetails method to input info of new driver
                        setDriverDetails(replaceDNum);

                        System.out.println("------------------------------------------------------");
                        System.out.println("   Successfully assigned " + F1Array[replaceDNum - 1].getDriverName() + " to " + teamName);
                        System.out.println("------------------------------------------------------");

                    } else {
                        System.out.println("------------------------------------------------------");
                        System.out.println("                Team does not exist !                 ");
                        System.out.println("------------------------------------------------------");
                    }
                } catch (Exception e) {

                    System.out.println("------------------------------------------------------");
                    System.out.println("                 Invalid Data Entered !               ");
                    System.out.println("------------------------------------------------------");
                    String skip = input.nextLine(); //to bypass the input skipping
                }

            //displays the driver statics for a particular driver
            } else if (userInput.equals("004")) {
                System.out.println("------------------------------------------------------");
                System.out.println("              Viewing statistics of Driver            ");
                System.out.println("------------------------------------------------------");
                printF1TeamsTable();
                System.out.print("Enter the team number which the driver races for : ");
                try {
                    teamNumber = input.nextInt();
                    if (teamNumber > 10 || teamNumber < 1) {
                        System.out.println("------------------------------------------------------");
                        System.out.println("                   Invalid Team !                     ");
                        System.out.println("------------------------------------------------------");
                    } else {
                        //check whether the team and driver exist or not to continue displaying statics
                        if (F1ParticipatingTeams[teamNumber - 1].equals(selectTeam(teamNumber))) {
                            F1Array[teamNumber - 1].displayDriverDetails();
                        } else {
                            System.out.println("------------------------------------------------------");
                            System.out.println("                 Team does not exist !                ");
                            System.out.println("------------------------------------------------------");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("                 Invalid Data Entered !               ");
                    System.out.println("------------------------------------------------------");
                    String skip = input.nextLine(); //to bypass the input skipping
                }


            //displays the F1 driver table
            } else if (userInput.equals("005")) {
                System.out.println("------------------------------------------------------");
                System.out.println("               Displaying F1 Driver Table            ");
                System.out.println("------------------------------------------------------");

                Formula1Driver[] F1TableArray = new Formula1Driver[10];
                // taking a copy of the original array
                for (int x = 0; x < 10; x++) {
                    F1TableArray[x] = F1Array[x];
                }

                Formula1Driver tempDriver;

                for (int x = 0; x < 10; x++) {
                    for (int j = x + 1; j < 10; j++) {
                        // Sort the array according to points
                        if (F1TableArray[x].getDriverPoints() < F1TableArray[j].getDriverPoints()) {
                            tempDriver = F1TableArray[x];
                            F1TableArray[x] = F1TableArray[j];
                            F1TableArray[j] = tempDriver;
                        }
                        // sort the array according to first positions only if drivers have same number of points
                        if (F1TableArray[x].getDriverPoints() == F1TableArray[j].getDriverPoints()) {
                            if (F1TableArray[x].getFirstPositions() < F1TableArray[j].getFirstPositions()) {
                                tempDriver = F1TableArray[x];
                                F1TableArray[x] = F1TableArray[j];
                                F1TableArray[j] = tempDriver;
                            }
                        }
                    }
                }
                System.out.println("|-----------------------|----------------------|-----------------|-----------------|-----------------|------------|");
                System.out.println("|       Team            |    Driver Name       |   Country       |   First Places  |  Total Races    |  Points    |");
                System.out.println("|-----------------------|----------------------|-----------------|-----------------|-----------------|------------|");
                //printing the rows only if the driver exist
                for (int a = 0; a < 10; a++) {
                    if (F1TableArray[a].getDriverTeam() != null) {
                        F1TableArray[a].driverDisplayForF1Table();
                    }
                }
                System.out.println("|-----------------------|----------------------|-----------------|-----------------|-----------------|------------|");

            //entering a completed race with the race date and all participant info
            } else if (userInput.equals("006")) {
                System.out.println("------------------------------------------------------");
                System.out.println("               Entering a Completed Race              ");
                System.out.println("------------------------------------------------------");
                System.out.println("Enter the race Date as \"dd-mm-yyyy\" : ");
                String newDate = input.next();

                try {
                    //string to date parsing
                    raceDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(newDate);

                    printF1TeamsTable();
                    while (true) {
                        System.out.print("Enter the team number to add participants or 99 to submit : ");
                        teamNumber = input.nextInt();
                        if (teamNumber == 99) {
                            System.out.println("------------------------------------------------------");
                            System.out.println("               Successfully added the RACE            ");
                            System.out.println("------------------------------------------------------");
                            break;

                        } else {
                            setRaceParticipant(teamNumber);
                            System.out.println("Successfully added participant ...");
                        }
                    }


                } catch (Exception e) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("                 Invalid Data Entered !               ");
                    System.out.println("------------------------------------------------------");
                    //clearing half entered data
                    if (teamNumber != 0) {
                        F1ParticipatingTeams[teamNumber - 1] = "";
                        F1Array[teamNumber - 1].clearDriverDetails();
                    }
                    String skip = input.nextLine(); //to bypass the input skipping
                }

            //writing organized data into the F1_DATA.txt fie
            } else if (userInput.equals("007")) {
                try {
                    FileWriter myWriter = new FileWriter("F1_DATA.txt");
                    myWriter.write("------------------------------------------------------\n");
                    myWriter.write("                 F1 Championship DATA \n");
                    myWriter.write("------------------------------------------------------\n");
                    myWriter.write(" \n");

                    //writes only the existing drivers info
                    for (int x = 0; x < 10; x++) {
                        if (F1Array[x].getDriverTeam() != null) {
                            myWriter.write(" \n");
                            myWriter.write("Driver Team      : " + F1Array[x].getDriverTeam() + " \n");
                            myWriter.write("Driver Name      : " + F1Array[x].getDriverName() + " \n");
                            myWriter.write("Driver Age       : " + F1Array[x].getDriverAge() + " \n");
                            myWriter.write("Driver Country   : " + F1Array[x].getDriverLocation() + " \n");
                            myWriter.write("First positions  : " + F1Array[x].getFirstPositions() + " \n");
                            myWriter.write("Second positions : " + F1Array[x].getSecondPositions() + " \n");
                            myWriter.write("Third positions  : " + F1Array[x].getThirdPositions() + " \n");
                            myWriter.write("Total Races      : " + F1Array[x].getRacesParticipated() + " \n");
                            myWriter.write("Driver Points    : " + F1Array[x].getDriverPoints() + " \n");
                            myWriter.write(" \n");
                        }
                    }
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

            //exiting the program by saving the current data into the F1.ser file
            } else if (userInput.equals("999")) {
                try {
                    serializeData();
                    System.out.println("------------------------------------------------------");
                    System.out.println("                     Thank you !                      ");
                    System.out.println("------------------------------------------------------");
                    break;
                } catch (IOException e) {
                    System.out.println("_________________Error in Storing DATA_________________");
                    System.out.println("Enter 99 to EXIT anyway or any other key to return : ");
                    String n = input.next();
                    if (n.equals("99")) {
                        System.out.println("------------------------------------------------------");
                        System.out.println("                     Thank you !                      ");
                        System.out.println("------------------------------------------------------");
                        break;
                    } else continue;

                }

            } else {
                System.out.println("------------------------------------------------------");
                System.out.println("                   Invalid Option !                   ");
                System.out.println("------------------------------------------------------");
            }
        }

    }

    @Override
    //prints the program menu
    public void printProgramMenu() {
        System.out.println("------------------------------------------------------");
        System.out.println("          FORMULA 1  CHAMPIONSHIP MANAGER             ");
        System.out.println("------------------------------------------------------");
        System.out.println("Create a new Driver                             001   ");
        System.out.println("Delete Driver and team                          002   ");
        System.out.println("Change Driver for existing team                 003   ");
        System.out.println("Display statistics for Driver                   004   ");
        System.out.println("Display the Formula 1 Driver table              005   ");
        System.out.println("Add a new Completed Race                        006   ");
        System.out.println("Save the Data to a File                         007   ");
        System.out.println("SAVE and EXIT                                   999   ");
        System.out.println("------------------------------------------------------");
    }

    @Override
    //prints the F1 teams table with the relevant team number
    public void printF1TeamsTable() {
        System.out.println("------------------------------------------------------");
        System.out.println("                   Formula 1 Teams                    ");
        System.out.println("------------------------------------------------------");
        System.out.println("     1. Mercedes                 2. RedBull Racing    ");
        System.out.println("     3. Ferrari                  4. McLaren           ");
        System.out.println("     5. Alpine                   6. AlphaTauri        ");
        System.out.println("     7. Aston Martin             8. Williams          ");
        System.out.println("     9. Alfa Romeo Racing        10.Haas F1 Team      ");
        System.out.println("------------------------------------------------------");
    }

    // takes the user inputs and sets all the driver details
    public void setDriverDetails(int teamNumber) {
        System.out.print("Enter the Driver Name : ");
        String skip = input.nextLine(); //to bypass the input skipping
        String name = input.nextLine();

        System.out.print("Enter the Country of Driver : ");
        String country = input.next();

        System.out.print("Enter the age of the Driver : ");
        int age = input.nextInt();

        System.out.print("Enter the total number of first places achieved : ");
        int first = input.nextInt();

        System.out.print("Enter the total number of second positions achieved : ");
        int second = input.nextInt();

        System.out.print("Enter the total number of third positions achieved : ");
        int totRaces = input.nextInt();

        System.out.print("Enter the total number of races participated : ");
        int races = input.nextInt();

        teamName = selectTeam(teamNumber);
        F1Array[teamNumber - 1].setDriverTeam(teamName);
        F1Array[teamNumber - 1].setDriverName(name);
        F1Array[teamNumber - 1].setDriverLocation(country);
        F1Array[teamNumber - 1].setDriverAge(age);
        F1Array[teamNumber - 1].setFirstPositions(first);
        F1Array[teamNumber - 1].setSecondPositions(second);
        F1Array[teamNumber - 1].setThirdPositions(totRaces);
        F1Array[teamNumber - 1].setRacesParticipated(races);
        F1Array[teamNumber - 1].calTotalPoints();
    }

    //sets all the information of completed race participants
    public void setRaceParticipant(int teamNumber) {
        String name = null;
        String country = null;
        int post;

        //takes some particular info of the driver if he does not exist
        if (F1ParticipatingTeams[teamNumber - 1].equals("")) {

            System.out.print("Enter the Driver Name : ");
            String skip = input.nextLine(); //to bypass the input skipping
            name = input.nextLine();
            System.out.print("Enter the Country of Driver : ");
            country = input.next();
            System.out.print("Enter the position achieved : ");

        } else {
            //else continue with the existing data of the driver
            name = F1Array[teamNumber - 1].getDriverName();
            country = F1Array[teamNumber - 1].getDriverLocation();
            System.out.println("Driver details already exist in the system ...");
            System.out.print("Enter the position achieved : ");
        }

        post = input.nextInt(); //position of the participant in the race

        F1Array[teamNumber - 1].setDriverName(name);
        F1Array[teamNumber - 1].setDriverLocation(country);
        F1Array[teamNumber - 1].setPosition(post);

        if (post == 1) {
            F1Array[teamNumber - 1].setFirstPositions(F1Array[teamNumber - 1].getFirstPositions() + 1);
        }

        F1Array[teamNumber - 1].setDriverPoints(

                F1Array[teamNumber - 1].getDriverPoints() + F1Array[teamNumber - 1].generateInstantPoints(post)
        );

        F1Array[teamNumber - 1].setDriverTeam(selectTeam(teamNumber));
        F1Array[teamNumber - 1].setRacesParticipated(F1Array[teamNumber - 1].getRacesParticipated() + 1);

        F1ParticipatingTeams[teamNumber - 1] = selectTeam(teamNumber);

        //adds the participant data along with the race date as one string
        CompletedRaces.add(post+","+selectTeam(teamNumber)+","+name+","+country+","+raceDate);

    }

    @Override
    //when the relevant team number is passed the relevant team name is returned
    public String selectTeam(int num) {
        String team = switch (num) {
            case 1 -> "Mercedes";
            case 2 -> "RedBull Racing";
            case 3 -> "Ferrari";
            case 4 -> "McLaren";
            case 5 -> "Alpine";
            case 6 -> "AlphaTauri";
            case 7 -> "Aston Martin";
            case 8 -> "Williams";
            case 9 -> "Alfa Romeo Racing";
            case 10 -> "Haas F1 Team";
            default -> null;
        };
        return team;
    }

    //serialize data and save it as a byte code to the F1.ser file
    public void serializeData() throws IOException {

            FileOutputStream fileOutputStream = new FileOutputStream("F1.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(int x=0; x<10; x++) {
                objectOutputStream.writeObject(F1Array[x]);
                objectOutputStream.writeObject(F1ParticipatingTeams[x]);
                objectOutputStream.writeObject(CompletedRaces);
            }
            objectOutputStream.close();

    }

    //deserialize the stored byte code and loads the data back into the program
    public void deserializeData() {
        try {
            FileInputStream fileInputStream = new FileInputStream("F1.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (int x=0; x<10; x++) {
                F1Array[x] = (Formula1Driver) objectInputStream.readObject();
                F1ParticipatingTeams[x] = (String) objectInputStream.readObject();
                CompletedRaces = (ArrayList<String>) objectInputStream.readObject();
            }
            objectInputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}