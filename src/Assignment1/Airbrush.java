package Assignment1;

public class Airbrush {
    public void brush(String s){
        System.out.println("Using the airbrush to "+s);

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
