package Assignment1;

public class Lathe {
    public void lathe(String s){
        System.out.println("Using the Lathe to "+s);

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
