package Assignment1;

public class ResinPrinter {
    public void print(String s){
        System.out.println("Printing " +s+  " on Resin Printer");

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
