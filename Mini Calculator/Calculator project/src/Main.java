import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Menu menu = new Menu();
        Conversion convert = new Conversion();
        Calculation calculation = new Calculation();

        while (true) {
            menu.displayMainMenu();
            String userInput;
            System.out.print("Enter the menu number : ");
            userInput = input.next();

            if (userInput.equals("1")) {
                boolean exit=false;
                while(!exit){
                    menu.displayConversionSubMenu();
                    String subInput;
                    System.out.print("Enter the menu number : ");
                    subInput=input.next();
                    if(subInput.equals("1")){
                        boolean exitSub1 = false;
                        while (!exitSub1){
                            double value;
                            double result;
                            menu.displayConversionLengthSubmenu();
                            System.out.print("Enter the menu number : ");
                            subInput=input.next();
                            if(subInput.equals("1")){

                                System.out.print("Enter Kilometers to be converted to miles : ");
                                value=input.nextDouble();
                                result=convert.KMToMiles(value);
                                System.out.println("Miles : "+result);

                            }else if(subInput.equals("2")){

                                System.out.print("Enter miles to be converted to Kilometers : ");
                                value=input.nextDouble();
                                result=convert.MilesToKM(value);
                                System.out.println("Kilometers : "+result);

                            }else if(subInput.equals("3")){

                                System.out.print("Enter Feet to be converted to Meters : ");
                                value=input.nextDouble();
                                result=convert.FeetToMetres(value);
                                System.out.println("Meters : "+result);

                            }else if(subInput.equals("4")){

                                System.out.print("Enter Meters to be converted to Feet : ");
                                value=input.nextDouble();
                                result=convert.MetresToFeet(value);
                                System.out.println("Feet : "+result);

                            }else if(subInput.equals("0")){
                                exitSub1=true;
                            }else{
                                System.out.println("------------Invalid Option-------------");
                            }

                        }

                    }else if(subInput.equals("2")){
                        boolean exitSub2 = false;
                        double value;
                        double result;
                        while (!exitSub2){
                            menu.displayConversionWeightSubmenu();
                            System.out.print("Enter the menu number : ");
                            subInput=input.next();
                            if(subInput.equals("1")){

                                System.out.print("Enter Kilograms to be converted to Pounds : ");
                                value=input.nextDouble();
                                result=convert.KgToPounds(value);
                                System.out.println("Pounds : "+result);

                            }else if(subInput.equals("2")){

                                System.out.print("Enter Pounds to be converted to Kilograms : ");
                                value=input.nextDouble();
                                result=convert.PoundsToKg(value);
                                System.out.println("Kilograms : "+result);

                            }else if(subInput.equals("0")){
                                exitSub2=true;
                            }else{
                                System.out.println("------------Invalid Option-------------");
                            }

                        }

                    }else if(subInput.equals("3")){
                        boolean exitSub3 = false;
                        double value;
                        double result;
                        while (!exitSub3){
                            menu.displayConversionTemperatureSubmenu();
                            System.out.print("Enter the menu number : ");
                            subInput=input.next();
                            if(subInput.equals("1")){

                                System.out.print("Enter Celsius to be converted to Fahrenheit : ");
                                value=input.nextDouble();
                                result=convert.CelciusToFahrenheit(value);
                                System.out.println("Fahrenheit : "+result);

                            }else if(subInput.equals("2")){

                                System.out.print("Enter Fahrenheit to be converted to Celsius : ");
                                value=input.nextDouble();
                                result=convert.FahrenheightToCelcius(value);
                                System.out.println("Celsius : "+result);

                            }else if(subInput.equals("0")){
                                exitSub3=true;
                            }else{
                                System.out.println("------------Invalid Option-------------");
                            }

                        }

                    }else if(subInput.equals("0")){
                        exit=true;
                    }else{
                        System.out.println("------------Invalid Option-------------");
                    }
                }

            } else if (userInput.equals("2")) {
                boolean exit=false;
                int result;
                while(!exit){
                    menu.displayCalculationSubMenu();
                    String subInput;
                    System.out.print("Enter the menu number : ");
                    subInput=input.next();
                    if(subInput.equals("1")){

                        System.out.print("Enter starting value : ");
                        int start=input.nextInt();
                        System.out.print("Enter ending value : ");
                        int end=input.nextInt();
                        System.out.print("Enter increment : ");
                        int incr=input.nextInt();
                        result=calculation.SumOfSeries(start,end,incr);
                        System.out.println("Sum of series : "+result);

                    }else if(subInput.equals("2")){

                        System.out.print("Enter size of array : ");
                        int size=input.nextInt();
                        int [] arr = new int[size];
                        for(int i=0; i< arr.length; i++){
                            System.out.print("Enter element "+(i+1)+" : ");
                            int element=input.nextInt();
                            arr[i]=element;
                        }
                        result=calculation.SumOfArray(arr);
                        System.out.println("Sum of Array : "+result);

                    }else if(subInput.equals("3")){

                        System.out.print("Enter starting value : ");
                        int start=input.nextInt();
                        System.out.print("Enter ending value : ");
                        int end=input.nextInt();
                        System.out.print("Enter increment : ");
                        int incr=input.nextInt();
                        result=calculation.ProductOfSeries(start,end,incr);
                        System.out.println("Product of series : "+result);

                    }else if(subInput.equals("4")){

                        System.out.print("Enter size of array : ");
                        int size=input.nextInt();
                        int [] arr = new int[size];
                        for(int i=0; i< arr.length; i++){
                            System.out.print("Enter element "+(i+1)+" : ");
                            int element=input.nextInt();
                            arr[i]=element;
                        }
                        result=calculation.ProductOfArray(arr);
                        System.out.println("Product of Array : "+result);

                    }else if(subInput.equals("0")){
                        exit=true;
                    }else{
                        System.out.println("------------Invalid Option-------------");
                    }
                }

            } else if (userInput.equals("00")) {
                System.out.println("------------Thank you-------------");
                break;
            } else {
                System.out.println("------------Invalid Option-------------");
            }

        }

    }
}
