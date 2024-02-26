package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class Flashlight implements Runnable{
    private Lathe lathe;
    private SolderingIron iron;
    private int number;
    private int progressInterval;

    private MakerSpace makerSpace;
    private ReentrantLock lock;

    public Flashlight(Lathe l, SolderingIron s, int n, int p,MakerSpace m){
        lathe=l;
        iron=s;
        number=n;
        progressInterval=p;

        makerSpace=m;
        lock=new ReentrantLock();
    }
    @Override
    public void run() {
        produceFlashlights();

    }
    public int produceFlashlights(){
        int produced=0;
        for(int i=0;i<number;i++){
            lathe.lathe("hollow the stock");
            iron.solder("connect some batteries, an LED, and a switch");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Created "+(i+1)+" flashlights");
            }
        }
        System.out.println(produced+" Flashlights produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
