public class Conversion {

    public Conversion(){

    }

    public double KMToMiles(double distance){
        return 0.62137119*distance;
    }
    public double MilesToKM(double distance){
        return distance/0.62137119;
    }
    public double FeetToMetres(double distance){
        return  0.3048*distance;
    }
    public double MetresToFeet(double distance){
        return distance/0.3048;
    }
    public double KgToPounds(double weight){
        return 2.20462262*weight;
    }
    public double PoundsToKg(double weight){
        return weight/2.20462262;
    }
    public double CelciusToFahrenheit(double temperature){
        return (temperature*1.8)+32;
    }
    public double FahrenheightToCelcius(double temperature){
        return (temperature-32)/1.8;
    }

}
