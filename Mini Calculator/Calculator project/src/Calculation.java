public class Calculation {

    public Calculation(){

    }

    public int SumOfSeries(int start, int end, int incr){
        int result=0;
        for(int x=start; x<=end; x+=incr){
            result+=x;
        }
        return result;
    }
    public int SumOfArray(int[] data){
        int result=0;
        for(int x=0; x<data.length; x++){
            result+=data[x];
        }
        return result;
    }
    public int ProductOfSeries(int start, int end, int incr){
        int result=start;
        for(int x=start+incr; x<=end; x+=incr){
            result*=x;
        }
        return result;
    }
    public int ProductOfArray(int data[]){
        int result=data[0];
        for(int x=1; x< data.length; x++){
            result+=data[x];
        }
        return result;
    }
}
