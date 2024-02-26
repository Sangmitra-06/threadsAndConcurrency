package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class Mill {
    private ReentrantLock lock;

    public Mill(){
        lock=new ReentrantLock();
    }
    public void mill(String s){
        try{
            lock.lock();
            //System.out.println("Using the mill to "+s);

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
