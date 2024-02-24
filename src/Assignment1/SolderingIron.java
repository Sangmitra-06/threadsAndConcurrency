package Assignment1;

public class SolderingIron {
    public void solder(String s){
        System.out.println("Using the soldering iron to "+s);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
