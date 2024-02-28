package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class SAKScales implements Runnable{
    private Mill mill;
    private ToasterOven toasterOven;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;
    public SAKScales(Mill m, ToasterOven t, int no,MakerSpace ms){
        mill=m;
        toasterOven=t;
        number=no;

        makerSpace=ms;
        lock=new ReentrantLock();
        output=ms.output;
    }

    @Override
    public void run() {
        produceSAKScales();

    }
    public int produceSAKScales(){
        for(int i=0;i<number;i++){
            mill.mill("Carve out scales");
            toasterOven.toast("Dry completely");
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
            System.out.println(produced+" SAK Scales produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

        return produced;
    }
}
