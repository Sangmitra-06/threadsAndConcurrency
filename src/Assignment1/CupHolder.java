package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class CupHolder implements Runnable {
    private ToasterOven toasterOven;
    private FDMPrinter fdm;
    private Airbrush airbrush;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;


    public CupHolder(ToasterOven t, FDMPrinter f, Airbrush a, int n, int p,MakerSpace m){
        toasterOven=t;
        fdm=f;
        airbrush=a;
        number=n;
        progressInterval=p;
        makerSpace=m;
        lock=new ReentrantLock();
    }
    @Override
    public void run() {
        produceCupHolder();

    }
    public int produceCupHolder(){
        int produced=0;
        for(int i=0;i<number;i++){
            toasterOven.toast("dry a filament");
            fdm.print("produce the basic holder");
            airbrush.brush("pure acetone");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Created "+(i+1)+" cup holders");
            }
        }
        System.out.println(produced+" Cup Holders produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
