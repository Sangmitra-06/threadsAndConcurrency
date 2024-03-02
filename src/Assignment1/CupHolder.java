package Assignment1;
/**
 * This class is an implementation of the Cup Holder Knickknack resource which implements the Runnable class.
 * This knickknack uses the Toaster Oven, FDM Printer, and Airbrush.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class CupHolder implements Runnable {
    // the toaster Oven resource
    private ToasterOven toasterOven;
    // the fdm printer resource
    private FDMPrinter fdm;
    // the airbrush resource
    private Airbrush airbrush;
    // the number of cup holders to produce
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of cup holders produced
    private int produced=0;

    /* Constructor of the class
     * @param t is the toaster oven resource
     * @param f is the FDM Printer resource
     * @param a is the Airbrush
     * @param n is the number of Cup holders to produce
     * @param m is the makerspace
     */
    public CupHolder(ToasterOven t, FDMPrinter f, Airbrush a, int n,MakerSpace m){
        toasterOven=t;
        fdm=f;
        airbrush=a;
        number=n;
        makerSpace=m;
        output=m.output;
    }

    /* The run method that is called when a thread for creating Cup Holders is called using the start() method.
     */
    @Override
    public void run() {
        produceCupHolder();

    }

    /* The produceCupHolder method that implements the creation of the Cup holders while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the Cup Holders.
     */
    public void produceCupHolder(){
        // to produce the required number of Cup Holders
        for(int i=0;i<number;i++){

            // the order of usage to produce the Cup Holders
            toasterOven.toast();
            fdm.print();
            airbrush.brush();
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

        // locking the makerspace lock to update on the completion of the production of Cup Holders
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Cup Holders produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

    }
}
