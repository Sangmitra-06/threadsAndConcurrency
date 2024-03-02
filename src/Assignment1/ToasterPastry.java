package Assignment1;

/**
 * This class is an implementation of the Toaster Pastry Knickknack resource which implements the Runnable class.
 * This knickknack uses the toaster oven.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class ToasterPastry implements Runnable{
    // the toaster oven resource
    private ToasterOven toasterOven;
    // the number of toaster pastries produced
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of toaster pastries produced
    private int produced=0;

    /* Constructor of the class
     * @param toast is the toaster oven resource
     * @param n is the number of toaster pastries to produce
     * @param m is the makerspace
     */
    public ToasterPastry(ToasterOven toast, int n,MakerSpace m){
        toasterOven=toast;
        number=n;
        makerSpace=m;
        output=m.output;
    }
    /* The run method that is called when a thread for creating toaster pastries is called using the start() method.
     */
    @Override
    public void run() {
        produceToasterPastry();
    }

    /* The produceToasterPastry method that implements the creation of the toaster pastries while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the toaster pastries.
     */
    public void produceToasterPastry(){

        // to produce the required number of toaster pastries
        for(int i=0;i<number;i++) {

            // the order of usage to produce the toaster pastries
            toasterOven.toast();
            produced++;
            output.lock(); // locking the output to prevent errors in production
            try {
                // updating the total number of knickknacks produced so far
                makerSpace.producedSoFar++;
                if ((makerSpace.producedSoFar) % makerSpace.freReport == 0) {
                    System.out.println("Made " + makerSpace.producedSoFar + " knickknacks");
                    System.out.println("----------------------------------------");
                }
            } finally {
                // releasing the lock
                output.unlock();
            }

        }
        // locking the makerspace lock to update on the completion of the production of toaster pastries

        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Pastries produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{

            makerSpace.lock.unlock();
        }

    }
}
