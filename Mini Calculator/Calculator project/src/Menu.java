public class Menu {
    public Menu(){

    }
    public void displayMainMenu(){
        System.out.println("---------------Menu----------------");
        System.out.println("Conversion Sub-Menu               1");
        System.out.println("Calculation Sub-Menu              2");
        System.out.println("EXIT                             00");
    }
    public void displayConversionSubMenu(){
        System.out.println("--------Conversion Sub-Menu--------");
        System.out.println("Length Conversion                 1");
        System.out.println("Weight Conversion                 2");
        System.out.println("Temperature Conversion            3");
        System.out.println("EXIT                              0");
    }
    public void displayCalculationSubMenu(){
        System.out.println("-------Calculation Sub-Menu--------");
        System.out.println("Sum of Series                     1");
        System.out.println("sum of Array                      2");
        System.out.println("Product of Series                 3");
        System.out.println("Product of Array                  4");
        System.out.println("EXIT                              0");
    }
    public void displayConversionLengthSubmenu(){
        System.out.println("--------Length Conversions---------");
        System.out.println("Kilometers to Miles               1");
        System.out.println("Miles to Kilometers               2");
        System.out.println("Feet to Meters                    3");
        System.out.println("Meters to Feet                    4");
        System.out.println("EXIT                              0");
    }
    public void displayConversionWeightSubmenu(){
        System.out.println("---------Weight Conversions--------");
        System.out.println("Kilograms to pounds               1");
        System.out.println("Pounds to Kilograms               2");
        System.out.println("EXIT                              0");
    }
    public void displayConversionTemperatureSubmenu(){
        System.out.println("---------Weight Conversions--------");
        System.out.println("Celsius to Fahrenheit             1");
        System.out.println("Fahrenheit to Celsius             2");
        System.out.println("EXIT                              0");
    }
}

