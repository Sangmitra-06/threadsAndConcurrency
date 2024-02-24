package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ToasterOven {
    private ReentrantLock lock;

    public ToasterOven(){
        lock=new ReentrantLock();
    }
    public void toast(String s){
        try{
            lock.lock();
            System.out.println("Using the toaster oven to " +s);
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
