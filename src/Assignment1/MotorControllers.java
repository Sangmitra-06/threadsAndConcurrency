package Assignment1;
/**
 * This class is an implementation of the Motor Controllers Knickknack resource which implements the Runnable class.
 * This knickknack uses the Soldering Iron, mill, and toaster oven.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class MotorControllers implements Runnable {
    // the soldering iron resource
    private SolderingIron iron;
    // the mill resource
    private Mill mill;
    // the toaster oven resource
    private ToasterOven toaster;
    // the number of Motor Controllers produced
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of Motor Controllers produced
    private int produced=0;

    /* Constructor of the class
     * @param m is the mill resource
     * @param soldering is the Soldering iron resource
     * @param t is the toaster oven resource
     * @param no is the number of Motor Controllers to produce
     * @param ms is the makerspace
     */
    public MotorControllers(int no, SolderingIron soldering, Mill m, ToasterOven t,MakerSpace ms){
        iron=soldering;
        mill=m;
        toaster=t;
        number=no;
        makerSpace=ms;
        output=ms.output;

    }
    /* The run method that is called when a thread for creating Motor Controllers is called using the start() method.
     */
    @Override
    public void run() {
        produceControllers();
    }

    /* The produceControllers method that implements the creation of the motor controllers while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the motor controllers.
     */
    public void produceControllers(){
        // to produce the required number of motor controllers
        for(int i=0;i<number;i++){

            // the order of usage to produce the motor controllers
            iron.solder();
            mill.mill();
            toaster.toast();
            iron.solder();
            produced++;
            output.lock(); // locking the output to prevent errors in production
            try{
                // updating the total number of knickknacks produced so far
                makerSpace.producedSoFar++;
                if((makerSpace.producedSoFar)% makerSpace.freReport==0){
                    System.out.println("Made "+makerSpace.producedSoFar+" knickknacks");
                    System.out.println("----------------------------------------");}
            }finally{
                // releasing the lock
                output.unlock();
            }
        }
        // locking the makerspace lock to update on the completion of the production of motor controllers
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Motor Controllers produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

    }
}
