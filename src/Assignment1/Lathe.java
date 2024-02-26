package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class Lathe {
    private ReentrantLock lock;

    public Lathe(){
        lock=new ReentrantLock();
    }
    public void lathe(String s){
        try{
            lock.lock();
            //System.out.println("Using the Lathe to "+s);

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
