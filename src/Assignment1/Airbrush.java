package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class Airbrush {

    private ReentrantLock lock;

    public Airbrush(){
        lock = new ReentrantLock();
    }
    public void brush(String s){
        try{
            lock.lock();
            System.out.println("Using the airbrush to "+s);

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
