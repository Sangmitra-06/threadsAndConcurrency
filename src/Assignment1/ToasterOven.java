package Assignment1;

public class ToasterOven {
    public void toast(String s){
        System.out.println("Using the toaster oven to " +s);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
