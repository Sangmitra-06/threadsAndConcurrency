package Assignment1;

public class FDMPrinter {
    public void print(String s) throws InterruptedException {
        System.out.println("Producing " +s+  " on FDM Printer");

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
    }
}
