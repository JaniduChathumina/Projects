/*

------       References      -------

Referred to :   https://www.youtube.com/watch?v=7zDzzfEIN4Y
                https://www.generacodice.com/en/articolo/4774756/how-do-i-generate-random-letters-in-java-based-on-probability
                https://youtu.be/U5Sh0KDLXSc
Referred to the book "Head First Java" https://docs.google.com/file/d/0BwxUBHTpU9kCU0xubVhyYlp0bWc/edit?resourcekey=0-sk68B4dt12P8MPoLieNBBA
Taken small code parts from stackoverflow and joined together to achieve this code.

 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GUI implements ActionListener{

    private static JFrame frame;
    private static JPanel panel;
    private static JTabbedPane tabPane;
    private static JComponent tab1;
    private static JComponent tab2;
    private static JComponent tab3;
    private static JComponent tab4;
    private static JButton b1;
    private static JButton b2;
    private static JButton b3;
    private static JButton b4;
    private static JButton b5;
    private static DefaultTableModel model1;
    private static DefaultTableModel model2;
    private static DefaultTableModel model3;
    private static DefaultTableModel model4;
    private static TableRowSorter mySorter;
    private static TableRowSorter mySorter4;
    private static Object[][] data;
    private static Date date;
    private static JLabel dateLabel;
    private static JTextField search;
    private static JButton searchButton;
    private static ArrayList<Integer> finalPositions;
    private static int raceParticipants;


    private static final GUI F1GUI = new GUI();
    private static final Formula1ChampionshipManager f1Manager = new Formula1ChampionshipManager();


    public static void main(String[] args) {

        //deserialize the stored byte code and load the data
        f1Manager.deserializeData();


        //*****      Method to manually delete all the races recorded      *****
        //Formula1ChampionshipManager.CompletedRaces.clear();


        frame = new JFrame("F1 Championship Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,660);
        frame.setResizable(false);

        //sets the frame icon
        Image icon = Toolkit.getDefaultToolkit().getImage("Logo.png");
        frame.setIconImage(icon);

        //content panel
        panel =new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        panel.setBackground(Color.black);

        //sets the image to the panel
        ImageIcon image = new ImageIcon("benz.png");
        JLabel label = new JLabel(image);
        label.setBounds(940,15,313,200);
        panel.add(label);

        //title initialisation
        JLabel title = new JLabel("F o r m u l a   1      -     2 0 2 1");
        title.setFont(new Font("Tahoma", Font.ITALIC, 28));
        title.setBounds(180,15,550,50);
        title.setForeground(new Color(122, 208, 207));
        panel.add(title);


        F1GUI.initialiseTabPane(); // creates and initialise the tab panes to display the tables


        //creating and initialising the five buttons

        b1 = new JButton("Generate Random Race");
        b1.setBounds(1000,240,200,28);
        b1.setBackground(new Color(122, 208, 207));
        panel.add(b1);
        b1.addActionListener(new GUI());

        b2 = new JButton("Generate Probability Race");
        b2.setBounds(1000,310,200,28);
        b2.setBackground(new Color(122, 208, 207));
        panel.add(b2);
        b2.addActionListener(new GUI());

        b3 = new JButton("Display all F1 Races");
        b3.setBounds(1000,380,200,28);
        b3.setBackground(new Color(122, 208, 207));
        panel.add(b3);
        b3.addActionListener(new GUI());

        b4 = new JButton("Display Driver Standings");
        b4.setBounds(1000,450,200,28);
        b4.setBackground(new Color(122, 208, 207));
        panel.add(b4);
        b4.addActionListener(new GUI());

        b5 = new JButton("Search Drivers");
        b5.setBounds(1000,520,200,28);
        b5.setBackground(new Color(122, 208, 207));
        panel.add(b5);
        b5.addActionListener(new GUI());



        //Driver Standings Pane

        String [] driverStandingsColumnNames = {"Team","Driver","Country","First Places","Total Races","Points"};
        model1 = new DefaultTableModel(); // table model - to do all edits to the table
        JTable driverStandings = new JTable(model1);
        model1.setColumnIdentifiers(driverStandingsColumnNames); //sets the column names

        //row sorter to give the user the possibility to sort the data
        mySorter = new TableRowSorter(model1);
        driverStandings.setRowSorter(mySorter);

        //adds the driver standing table to a scroll pane
        //without scroll pane the table headers will not be visible
        JScrollPane scrollPane1 = new JScrollPane(driverStandings);
        scrollPane1.setBounds(20,20,850,430);
        tab1.add(scrollPane1);
        driverStandings.setRowHeight(30);

        //updating the driver standings table
        F1GUI.updateDriverStandingsTable();




        // Race Summary Pane for both Random Race and Probability Race.

        String [] raceSummaryColumnNames = {"Position","Team","Driver","Country","Start Position","Points"};
        model2 = new DefaultTableModel(); // table model - to do all edits to the table
        JTable raceSummary = new JTable(model2);
        model2.setColumnIdentifiers(raceSummaryColumnNames); //sets the column names

        //row sorter to give the user the possibility to sort the data
        mySorter = new TableRowSorter(model2);
        raceSummary.setRowSorter(mySorter);

        //adds the Race Summary table to a scroll pane
        //without scroll pane the table headers will not be visible
        JScrollPane scrollPane2 = new JScrollPane(raceSummary);
        scrollPane2.setBounds(20,70,850,380);
        tab2.add(scrollPane2);
        raceSummary.setRowHeight(30);

        JLabel dLabel = new JLabel("Race Date   : ");
        dLabel.setBounds(40,28,100,20);
        dateLabel = new JLabel();
        dateLabel.setBounds(130,28,200,20);
        tab2.add(dLabel);
        tab2.add(dateLabel);



        // All F1 Completed Races Pane

        String [] completedRacesColumnNames = {"Win Team","Winner","Country","Race Date"};
        model3 = new DefaultTableModel(); // table model - to do all edits to the table
        JTable F1RacesTable = new JTable(model3);
        model3.setColumnIdentifiers(completedRacesColumnNames); //sets the column names

        //row sorter to give the user the possibility to sort the data
        mySorter = new TableRowSorter(model3);
        F1RacesTable.setRowSorter(mySorter);

        //adds the F1 Races table to a scroll pane
        //without scroll pane the table headers will not be visible
        JScrollPane scrollPane3 = new JScrollPane(F1RacesTable);
        scrollPane3.setBounds(20,20,850,430);
        tab3.add(scrollPane3);
        F1RacesTable.setRowHeight(30);



        // Search Drivers Table

        String [] searchDriverColumnNames = {"Position","Team","Driver","Country","Race Date"};
        model4 = new DefaultTableModel(); // table model - to do all edits to the table
        JTable searchDriver = new JTable(model4);
        model4.setColumnIdentifiers(searchDriverColumnNames); //sets the column names

        //row sorter to give the user the possibility to sort the data
        mySorter4 = new TableRowSorter(model4);
        searchDriver.setRowSorter(mySorter4);

        //adds the Search Driver table to a scroll pane
        //without scroll pane the table headers will not be visible
        JScrollPane scrollPane4 = new JScrollPane(searchDriver);
        scrollPane4.setBounds(20,70,850,380);
        tab4.add(scrollPane4);
        searchDriver.setRowHeight(30);

        //search bar initialisation
        JLabel Label = new JLabel("Driver Name   : ");
        Label.setBounds(180,28,100,20);
        search = new JFormattedTextField();
        search.setBounds(280,28,320,23);
        search.setToolTipText("Enter Driver Name to be searched");

        searchButton = new JButton("Search");
        searchButton.setBounds(620,28,120,23);
        tab4.add(Label);
        tab4.add(search);
        tab4.add(searchButton);
        //adds action listener to capture the click event
        searchButton.addActionListener(new GUI());


        //updates both Completed Race table and Search Drive table
        //not called near model 3 because model 4 was not initialised at that time
        F1GUI.updateCompletedRaces();


        //captures the window closing event and serialize the data to save to F1.ser file
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    f1Manager.serializeData();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                super.windowClosing(e);
            }
        });


        frame.setVisible(true);

    }

    //updates the driver standing from new added data (Random races)
    public void updateDriverStandingsTable(){
        int a=model1.getRowCount();
        for (int x=0; x<a; x++) {
            model1.removeRow(0);
        }

        //copies necessary data From F1Array to another array
        data = new Object[10][6];
        for (int x=0; x<10; x++){
            data[x][0]= ChampionshipManager.F1Array[x].getDriverTeam();
            data[x][1]= ChampionshipManager.F1Array[x].getDriverName();
            data[x][2]= ChampionshipManager.F1Array[x].getDriverLocation();
            data[x][3]= ChampionshipManager.F1Array[x].getFirstPositions();
            data[x][4]= ChampionshipManager.F1Array[x].getRacesParticipated();
            data[x][5]= ChampionshipManager.F1Array[x].getDriverPoints();
        }

        //adds only the existing driver info
        int rowNum =0;
        for(int x=0; x<10; x++){
            if(data[x][0]!= null){
                Object [] obj = {data[x][0],data[x][1],data[x][2],data[x][3],data[x][4],data[x][5]};
                model1.insertRow(rowNum,obj);
                rowNum++;
            }
        }
    }


    //race type is given as a parameter and depending on it Random races and Probability races are created
    //both types of races are displayed on the Race Summary table
    public void newRandomRace(String raceType) throws ParseException {
        int a=model2.getRowCount();
        for (int x=0; x<a; x++) {
            model2.removeRow(0);
        }

        //generates a random date
        int day =randomNumBtw(31,1);
        int month = randomNumBtw(12,1);
        String d1 = day+"/"+month+"/2021";
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(d1);
        dateLabel.setText(day+" - "+month+" - 2021");

        //counts the number of race participants (number of existing drivers)
        raceParticipants=0;
        for(int z=0; z<10;z++){
            if (ChampionshipManager.F1Array[z].getDriverTeam()!=null){
                raceParticipants++;
            }
        }

        finalPositions = new ArrayList<>(); //end positions of drivers
        ArrayList<Integer> startPositions = new ArrayList<>(); //start positions of drivers
        int endPosition=0;
        int startPosition=0;
        int rowNum=0;

        for (int x=0;x<10;x++) {

            //The random races are generated only for the existing drivers
            if (ChampionshipManager.F1Array[x].getDriverTeam()!=null){
                int totalRaceParticipated = ChampionshipManager.F1Array[x].getRacesParticipated()+1;

                //each driver should have a unique start position
                startPosition = randomNumBtw(raceParticipants, 1);
                while ((startPositions.contains(startPosition))) {
                    startPosition = randomNumBtw(raceParticipants, 1);
                }
                startPositions.add(startPosition);

                //end position of the driver is decided according to the race type

                if(raceType.equals("RandomRace")) {
                    //a fully random position is declared to each driver
                    endPosition = randomNumBtw(raceParticipants, 1);
                    while (finalPositions.contains(endPosition)) {
                        endPosition = randomNumBtw(raceParticipants, 1);
                    }
                    finalPositions.add(endPosition);

                }else if(raceType.equals("ProbabilityRace")){
                    //a position depending on the starting position is declared
                    endPosition=probRandPositionBtw(raceParticipants,1,startPosition);
                }

                //updates the race summary table
                Formula1Driver driver = new Formula1Driver();
                int points = driver.generateInstantPoints(endPosition);
                String team = ChampionshipManager.F1Array[x].getDriverTeam();
                String name = ChampionshipManager.F1Array[x].getDriverName();
                String country = ChampionshipManager.F1Array[x].getDriverLocation();

                Object [] obj2 = {endPosition,team,name,country,startPosition,points};
                model2.insertRow(rowNum,obj2);
                rowNum++;

                //race statics are updated to the F1Array
                if(endPosition==1){
                    int f = ChampionshipManager.F1Array[x].getFirstPositions() + 1;
                    ChampionshipManager.F1Array[x].setFirstPositions(f);
                }
                ChampionshipManager.F1Array[x].setRacesParticipated(totalRaceParticipated);
                ChampionshipManager.F1Array[x].addToTotalPoints(points);

                //participant details are stored to completed races array list as one string
                Formula1ChampionshipManager.CompletedRaces.add(endPosition+","+team+","+name+","+country+","+date);
            }
        }
        //updates the drive standings after generating random race
        updateDriverStandingsTable();
    }


    //updates the completed races table and Search Driver Table
    public void updateCompletedRaces() {

        int totalRaces = Formula1ChampionshipManager.CompletedRaces.size();
        Object [][] array = new Object[totalRaces][5];
        //gets the driver details from CompletedRaces array list and stores it to another 2d array
        for(int y = 0; y< totalRaces; y++){
            String [] participant = Formula1ChampionshipManager.CompletedRaces.get(y).split(",");
            for(int z=0; z<5; z++){
                array[y][z]=participant[z];
            }
        }

        try {
            //sorting the 2d array according to ascending order of date
            for (int k = 0; k < totalRaces; k++) {
                for (int m = k; m < totalRaces; m++) {

                    //converting string to Date type
                    DateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    Date d1 ;
                    Date d2 ;
                    d1 = sdf.parse(array[k][4] + "");
                    d2 = sdf.parse(array[m][4] + "");

                    //swapping the second dimension arrays
                    if (d1.compareTo(d2) > 0) {
                        Object[] temp = new Object[5];
                        for (int x = 0; x < 5; x++) {
                            temp[x] = array[k][x];
                            array[k][x] = array[m][x];
                            array[m][x] = temp[x];
                        }
                    }

                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //checks for the winners and adds them to the Completed Races table
            int m3Rows=model3.getRowCount();
            for (int x=0; x<m3Rows; x++) {
                model3.removeRow(0);
            }

            int m3rowNum = 0;
            for (int i = 0; i < totalRaces; i++) {
                if (array[i][0].equals("1")) {
                    Object[] obj = {array[i][1], array[i][2], array[i][3], array[i][4]};
                    model3.insertRow(m3rowNum, obj);
                    m3rowNum++;
                }
            }


        //adds all the drivers who participated in all races with their positions and race dates in Search Driver table
            int m4Rows = model4.getRowCount();
            for (int x = 0; x < m4Rows; x++) {
                model4.removeRow(0);
            }

            int m4rowNum = 0;
            for (int i = 0; i < totalRaces; i++) {
                Object[] obj = {array[i][0], array[i][1], array[i][2], array[i][3], array[i][4]};
                model4.insertRow(m4rowNum, obj);
                m4rowNum++;
            }

    }



    //creates and initialise the tab panes
    public void initialiseTabPane(){
        tabPane = new JTabbedPane();
        tabPane.setBounds(30,90,899,500);
        panel.add(tabPane);

        tab1 = new JPanel();
        tab1.setLayout(null);
        tab1.setBackground(Color.lightGray);
        tab2 = new JPanel();
        tab2.setLayout(null);
        tab2.setBackground(Color.lightGray);
        tab3 = new JPanel();
        tab3.setLayout(null);
        tab3.setBackground(Color.lightGray);
        tab4 = new JPanel();
        tab4.setLayout(null);
        tab4.setBackground(Color.lightGray);

        tabPane.addTab(String.format("               %-30s","DRIVER STANDINGS"),tab1);
        tabPane.addTab(String.format("               %-30s","RACE SUMMARY"),tab2);
        tabPane.addTab(String.format("               %-30s","FORMULA1 RACES"),tab3);
        tabPane.addTab(String.format("              %-30s","Search Drivers"),tab4);

    }

    //returns a random number between a given range including max and min values
    public int randomNumBtw(int max,int min){
        int num =(int) (Math.random()*(max-min+1)+min);
        return num;
    }

    //returns a random position according to the start position and the win probability
    public int probRandPositionBtw(int max, int min, int startPosition){
        int endPosition;
        double winProbability;
        double randProbability= Math.random(); //random num between 1 and 0

        //printing the random probability, start and end position for reference
        System.out.print("Random Probability : "+randProbability+"      start : "+startPosition);

        if (startPosition>=5 & startPosition<=9) winProbability=0.20;
        else if (startPosition==3 | startPosition==4) winProbability=0.10;
        else if(startPosition==2) winProbability=0.30;
        else if(startPosition==1) winProbability=0.40;
        else winProbability=0.00;

        if(randProbability<winProbability & !finalPositions.contains(1)){
            endPosition=1;
        } else {
            //no one wins and if last participant also has probability greater than win probability he is given first place
            if(randProbability>winProbability & finalPositions.size()==raceParticipants-1 & !finalPositions.contains(1)){
                endPosition=1;
            }else {
                //random number generated without first position
                endPosition = randomNumBtw(max, min + 1);
                while (finalPositions.contains(endPosition)) endPosition = randomNumBtw(max, min+1);
            }
        }

        System.out.println("     end : "+endPosition);

        finalPositions.add(endPosition);
        return endPosition;
    }


    @Override
    //override the method to capture the events and add particular functionality
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1 | e.getSource()==b2) {

            tabPane.setSelectedComponent(tab2);
            try {

                if(e.getSource()==b1) {newRandomRace("RandomRace");}
                else if(e.getSource()==b2) {newRandomRace("ProbabilityRace");}

                updateCompletedRaces();

            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

        }else if(e.getSource()==b3){
            tabPane.setSelectedComponent(tab3);
        }else if(e.getSource()==b4){
            tabPane.setSelectedComponent(tab1);
        }else if(e.getSource()==b5){
            tabPane.setSelectedComponent(tab4);

        }else if(e.getSource()==searchButton){

            //gets the text entered in the search bar and filter the table according to the text
            String text = search.getText();
            mySorter4.setRowFilter(RowFilter.regexFilter("(?i)"+text));
            //"(?i)" was concatenated to the text because of case sensitive

        }
    }


}
