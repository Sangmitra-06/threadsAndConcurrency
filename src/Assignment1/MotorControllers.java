package Assignment1;

public class MotorControllers implements Runnable {
    private SolderingIron iron;
    private Mill mill;
    private ToasterOven toaster;
    private int number;
    private int progressInterval;

    public MotorControllers(int no, int progress, SolderingIron soldering, Mill m, ToasterOven t){
        iron=soldering;
        mill=m;
        toaster=t;
        number=no;
        progressInterval=progress;
    }

    @Override
    public void run() {
        for(int i=0;i<number;i++){
            iron.solder("tin the through-hole components");
            mill.mill("cut out the traces");
            System.out.println("Brushing flux");
            System.out.println("Spreading the solder paste");
            toaster.toast("flow the solder");
            iron.solder("touch up any weak joints");
            System.out.println("Plugging in the motors");
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" motor controllers");
            }
        }
    }
}
