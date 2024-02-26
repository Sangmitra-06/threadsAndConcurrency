package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class MotorControllers implements Runnable {
    private SolderingIron iron;
    private Mill mill;
    private ToasterOven toaster;
    private int number;
    private int progressInterval;
    private MakerSpace makerSpace;
    private ReentrantLock lock;

    public MotorControllers(int no, int progress, SolderingIron soldering, Mill m, ToasterOven t,MakerSpace ms){
        iron=soldering;
        mill=m;
        toaster=t;
        number=no;
        progressInterval=progress;

        makerSpace=ms;
        lock=new ReentrantLock();
    }

    @Override
    public void run() {
        produceControllers();
    }

    public int produceControllers(){
        int produced=0;
        for(int i=0;i<number;i++){
            iron.solder("tin the through-hole components");
            mill.mill("cut out the traces");
            //System.out.println("Brushing flux");
            //System.out.println("Spreading the solder paste");
            toaster.toast("flow the solder");
            iron.solder("touch up any weak joints");
            //System.out.println("Plugging in the motors");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" motor controllers");
            }
        }
        System.out.println(produced+" Motor Controllers produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
