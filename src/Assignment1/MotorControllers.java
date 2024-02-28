package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class MotorControllers implements Runnable {
    private SolderingIron iron;
    private Mill mill;
    private ToasterOven toaster;
    private int number;
    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;

    public MotorControllers(int no, SolderingIron soldering, Mill m, ToasterOven t,MakerSpace ms){
        iron=soldering;
        mill=m;
        toaster=t;
        number=no;

        makerSpace=ms;
        lock=new ReentrantLock();
        output=ms.output;

    }

    @Override
    public void run() {
        produceControllers();
    }

    public int produceControllers(){
        for(int i=0;i<number;i++){
            iron.solder("tin the through-hole components");
            mill.mill("cut out the traces");
            //System.out.println("Brushing flux");
            //System.out.println("Spreading the solder paste");
            toaster.toast("flow the solder");
            iron.solder("touch up any weak joints");
            //System.out.println("Plugging in the motors");
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
            System.out.println(produced+" Motor Controllers produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

        return produced;
    }
}
