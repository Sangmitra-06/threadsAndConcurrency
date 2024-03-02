package Assignment1;
/**
 * This class is an implementation of the SAK Scales Knickknack resource which implements the Runnable class.
 * This knickknack uses the Mill and Toaster Oven.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class SAKScales implements Runnable{
    // the mill resource
    private Mill mill;
    // the toaster oven resource
    private ToasterOven toasterOven;
    // the number of SAK Scales produced
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of SAK Scales produced
    private int produced=0;

    /* Constructor of the class that takes in the number of SAK Scales to produce, the mill resource,
     * the toaster oven resource, and the makerspace instance
     */
    public SAKScales(Mill m, ToasterOven t, int no,MakerSpace ms){
        mill=m;
        toasterOven=t;
        number=no;
        makerSpace=ms;
        output=ms.output;
    }

    /* The run method that is called when a thread for creating SAK Scales is called using the start() method.
     */
    @Override
    public void run() {
        produceSAKScales();

    }

    /* The produceSAKScales method that implements the creation of the SAK Scales while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the SAK Scales.
     */
    public void produceSAKScales(){

        // to produce the required number of SAK Scales
        for(int i=0;i<number;i++){

            // the order of usage to produce the SAK Scales
            mill.mill();
            toasterOven.toast();
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

        // locking the makerspace lock to update on the completion of the production of SAK Scales
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" SAK Scales produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

    }
}
