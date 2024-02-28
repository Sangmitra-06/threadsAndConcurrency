package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class Flashlight implements Runnable{
    private Lathe lathe;
    private SolderingIron iron;
    private int number;

    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;

    public Flashlight(Lathe l, SolderingIron s, int n,MakerSpace m){
        lathe=l;
        iron=s;
        number=n;

        makerSpace=m;
        lock=new ReentrantLock();
        output=m.output;
    }
    @Override
    public void run() {
        produceFlashlights();

    }
    public int produceFlashlights(){
        for(int i=0;i<number;i++){
            lathe.lathe("hollow the stock");
            iron.solder("connect some batteries, an LED, and a switch");
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
            System.out.println(produced+" Flashlights produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally {

            makerSpace.lock.unlock();
        }

        return produced;
    }
}
