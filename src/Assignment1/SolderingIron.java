package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class SolderingIron {
    private ReentrantLock lock;

    public SolderingIron(){
        lock=new ReentrantLock();
    }
    public void solder(String s){
        try{
            lock.lock();

            System.out.println("Using the soldering iron to "+s);
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
