package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ToasterPastry implements Runnable{
    private ToasterOven toasterOven;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;

    public ToasterPastry(ToasterOven toast, int n, int p,MakerSpace m){
        toasterOven=toast;
        number=n;
        progressInterval=p;

        makerSpace=m;
        lock=new ReentrantLock();

    }

    @Override
    public void run() {
        produceToasterPastry();
    }

    public int produceToasterPastry(){
        int produced=0;
        for(int i=0;i<number;i++){
            toasterOven.toast("toast a pastry");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Toasted "+(i+1)+" pastries");
            }
        }
        System.out.println(produced+" Pastries produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
