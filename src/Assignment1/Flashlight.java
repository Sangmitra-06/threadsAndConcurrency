package Assignment1;

/**
 * This class is an implementation of the Flashlight Knickknack resource which implements the Runnable class.
 * This knickknack uses the Lathe and the Soldering Iron.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class Flashlight implements Runnable{
    // the lathe resource
    private Lathe lathe;
    // the soldering iron resource
    private SolderingIron iron;
    // the number of flashlights to produced
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of Flashlights produced
    private int produced=0;

    /* Constructor of the class
     * @param l is the lathe resource
     * @param s is the Soldering iron resource
     * @param n is the number of Flashlights to produce
     * @param m is the makerspace
     */
    public Flashlight(Lathe l, SolderingIron s, int n,MakerSpace m){
        lathe=l;
        iron=s;
        number=n;
        makerSpace=m;
        output=m.output;
    }

    /* The run method that is called when a thread for creating Flashlights is called using the start() method.
     */
    @Override
    public void run() {
        produceFlashlights();

    }

    /* The produceFlashlights method that implements the creation of the flashlights while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the Flashlights.
     */
    public void produceFlashlights(){

        // to produce the required number of Flashlights
        for(int i=0;i<number;i++){

            // the order of usage to produce the Flashlights
            lathe.lathe();
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
        // locking the makerspace lock to update on the completion of the production of Flashlights

        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Flashlights produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally {

            makerSpace.lock.unlock();
        }

    }
}
