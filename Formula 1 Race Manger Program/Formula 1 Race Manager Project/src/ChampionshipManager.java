import java.util.Scanner;

public interface ChampionshipManager{

    /*Array to hold all the drivers, index number refers to the team
    example: 0->mercedes, 1->RedBullRacing ...
    */
    Formula1Driver[] F1Array = new Formula1Driver[10];

    //array to add teams which are being added to F1 championship
    String[] F1ParticipatingTeams = new String[10];

    //scanner to take the inputs
    Scanner input = new Scanner(System.in);


    //Method which controls all the menu functions
    public void ManageChampionship();

    //prints the program menu
    public void printProgramMenu();

    //prints the F1 Teams with the team numbers
    public void printF1TeamsTable();

    //when the relevant team number is passed the relevant team name will be returned
    public String selectTeam(int num);

}
