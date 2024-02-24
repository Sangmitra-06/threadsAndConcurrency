package Assignment1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FiguringsForMazesAndMonsters implements Runnable{
    private ResinPrinter resin;
    private Airbrush airbrush;
    private int number;
    private int progressInterval;
    private ReentrantLock lock;

    public FiguringsForMazesAndMonsters(int no,int progress, ResinPrinter resin_print, Airbrush a){
        number=no;
        progressInterval=progress;
        resin=resin_print;
        airbrush=a;
        lock=new ReentrantLock();
    }

    @Override
    public void run() {
        for(int i=0;i<number;i++){
            try{
                lock.lock();
                resin.print("figurine");
                airbrush.brush("paint figurine");
                if((i+1)%progressInterval==0){
                    System.out.println("Produced "+(i+1)+" figurines");
                }
            }finally{
                lock.unlock();

            }


        }
    }
}
