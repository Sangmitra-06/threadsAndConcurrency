package Assignment1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FiguringsForMazesAndMonsters implements Runnable{
    private ResinPrinter resin;
    private Airbrush airbrush;
    private int number;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;

    private volatile int produced=0;
    public FiguringsForMazesAndMonsters(int no, ResinPrinter resin_print, Airbrush a,MakerSpace m){
        number=no;
        resin=resin_print;
        airbrush=a;
        makerSpace=m;
        lock=new ReentrantLock();
        output=m.output;
    }

    @Override
    public void run() {
        produceFigurines();
    }
    public int produceFigurines(){

        for(int i=0;i<number;i++){
            resin.print("figurine");
            airbrush.brush("paint figurine");
            produced++;
            output.lock();
            try{
                makerSpace.producedSoFar++;
                if((makerSpace.producedSoFar)% makerSpace.freReport==0){
                  System.out.println("Made "+makerSpace.producedSoFar+" knickknacks");
                  System.out.println("----------------------------------------");}
            }finally{
                output.unlock();
            }

        }
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Figurines produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }


        return produced;
    }
}
