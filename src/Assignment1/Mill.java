package Assignment1;

public class Mill {
    public void mill(String s){
        System.out.println("Using the mill to "+s);

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
