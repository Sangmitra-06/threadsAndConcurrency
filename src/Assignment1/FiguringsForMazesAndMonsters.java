package Assignment1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FiguringsForMazesAndMonsters implements Runnable{
    private ResinPrinter resin;
    private Airbrush airbrush;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    public FiguringsForMazesAndMonsters(int no,int progress, ResinPrinter resin_print, Airbrush a,MakerSpace m){
        number=no;
        progressInterval=progress;
        resin=resin_print;
        airbrush=a;
        makerSpace=m;
        lock=new ReentrantLock();
    }

    @Override
    public void run() {
        produceFigurines();
    }
    public int produceFigurines(){
        int produced=0;
        for(int i=0;i<number;i++){
            resin.print("figurine");
            airbrush.brush("paint figurine");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" figurines");
            }


        }
        System.out.println(produced+" Figurines produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
