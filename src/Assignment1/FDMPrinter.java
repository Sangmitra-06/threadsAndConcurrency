package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class FDMPrinter {

    private ReentrantLock lock;

    public FDMPrinter(){
        lock=new ReentrantLock();
    }
    public void print(String s){
        try{
            lock.lock();

            //System.out.println("Producing " +s+  " on FDM Printer");

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.getMessage();
            }
        }finally{
            lock.unlock();
        }

    }
}
