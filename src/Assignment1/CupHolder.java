package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class CupHolder implements Runnable {
    private ToasterOven toasterOven;
    private FDMPrinter fdm;
    private Airbrush airbrush;
    private int number;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;


    public CupHolder(ToasterOven t, FDMPrinter f, Airbrush a, int n,MakerSpace m){
        toasterOven=t;
        fdm=f;
        airbrush=a;
        number=n;
        makerSpace=m;
        lock=new ReentrantLock();
        output=m.output;
    }
    @Override
    public void run() {
        produceCupHolder();

    }
    public int produceCupHolder(){
        for(int i=0;i<number;i++){
            toasterOven.toast("dry a filament");
            fdm.print("produce the basic holder");
            airbrush.brush("pure acetone");
            System.out.println("Created "+(i+1)+" cup holders");
            System.out.println("----------------------------------------");
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
            System.out.println(produced+" Cup Holders produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }


        return produced;
    }
}
