package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class SAKScales implements Runnable{
    private Mill mill;
    private ToasterOven toasterOven;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    public SAKScales(Mill m, ToasterOven t, int no, int p,MakerSpace ms){
        mill=m;
        toasterOven=t;
        number=no;
        progressInterval=p;

        makerSpace=ms;
        lock=new ReentrantLock();
    }

    @Override
    public void run() {
        produceSAKScales();

    }
    public int produceSAKScales(){
        int produced=0;
        for(int i=0;i<number;i++){
            mill.mill("Carve out scales");
            toasterOven.toast("Dry completely");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Created "+(i+1)+" SAK scales");
            }
        }
        System.out.println(produced+" SAK Scales produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
