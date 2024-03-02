package Assignment1;

/**
 * This class is an implementation of the Figurines Knickknack resource which implements the Runnable class.
 * This knickknack uses the Resin Printer and Airbrush .
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */

import java.util.concurrent.locks.ReentrantLock;

public class FiguringsForMazesAndMonsters implements Runnable{
    // the resin printer resource
    private ResinPrinter resin;
    // the airbrush resource
    private Airbrush airbrush;
    // the number of figurines to produce
    private int number;
    // the makerspace instance
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of figurines produced
    private int produced=0;

    /* Constructor of the class
     * @param a is the airbrush resource
     * @param resin_print is the resin printer resource
     * @param no is the number of Figurines to produce
     * @param m is the makerspace
     */
    public FiguringsForMazesAndMonsters(int no, ResinPrinter resin_print, Airbrush a,MakerSpace m){
        number=no;
        resin=resin_print;
        airbrush=a;
        makerSpace=m;
        output=m.output;
    }
    /* The run method that is called when a thread for creating Figurines is called using the start() method.
     */
    @Override
    public void run() {
        produceFigurines();
    }

    /* The produceFigurines method that implements the creation of the cup holders while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the figurines.
     */
    public void produceFigurines(){

        // to produce the required number of Figurines
        for(int i=0;i<number;i++){

            // the order of usage to produce the Figurines
            resin.print();
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
        // locking the makerspace lock to update on the completion of the production of Figurines
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Figurines produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

    }
}
