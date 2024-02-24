package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ResinPrinter {
    private ReentrantLock lock;

    public ResinPrinter(){
        lock=new ReentrantLock();
    }
    public void print(String s){
        try{
            lock.lock();
            System.out.println("Printing " +s+  " on Resin Printer");

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
