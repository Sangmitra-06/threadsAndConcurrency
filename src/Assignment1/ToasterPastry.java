package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ToasterPastry implements Runnable{
    private ToasterOven toasterOven;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;

    public ToasterPastry(ToasterOven toast, int n,MakerSpace m){
        toasterOven=toast;
        number=n;

        makerSpace=m;
        lock=new ReentrantLock();
        output=m.output;

    }

    @Override
    public void run() {
        produceToasterPastry();
    }

    public int produceToasterPastry(){
        for(int i=0;i<number;i++) {
            toasterOven.toast("toast a pastry");

                produced++;
                output.lock();
                try {
                    makerSpace.producedSoFar++;
                    if ((makerSpace.producedSoFar) % makerSpace.freReport == 0) {
                        System.out.println("Made " + makerSpace.producedSoFar + " knickknacks");
                        System.out.println("----------------------------------------");
                    }
                } finally {
                    output.unlock();
                }

        }

        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Pastries produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{

            makerSpace.lock.unlock();
        }

        return produced;
    }
}
