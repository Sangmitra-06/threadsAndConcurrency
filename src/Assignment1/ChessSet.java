package Assignment1;

/**
 * This class is an implementation of the Chess Set Knickknack resource which implements the Runnable class. This knickknack uses the Resin Printer, Lathe, and the FDM Printer.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */

import java.util.concurrent.locks.ReentrantLock;

public class ChessSet implements Runnable{

    // the resin printer resource
    private ResinPrinter resin;
    // the lathe resource
    private Lathe lathe;
    // the FDM printer resource
    private FDMPrinter fdm;
    // the number of Chess Sets to produce
    private int number;
    // the instance of the makerspace
    private MakerSpace makerSpace;
    // the output lock
    private ReentrantLock output;
    // the number of Chess Sets produced
    private int produced=0;


    /* Constructor of the class
     * @param f is the fdm printer resource
     * @param r is the resin printer resource
     * @param no is the number of Cup holders to produce
     * @param m is the makerspace
     */
    public ChessSet(int no, ResinPrinter r, Lathe l, FDMPrinter f, MakerSpace m){
        number=no;
        resin=r;
        lathe=l;
        fdm=f;
        makerSpace=m;
        output=m.output;
    }

    /* The run method that is called when a thread for creating Chess Sets is called using the start() method.
     */
    @Override
    public void run() {
        produceChessSet();

    }

    /* The produceChessSet method that implements the creation of the chess sets while controlling the output for reporting
     * the number of knickknacks produced as well as the completion in production of all the Chess Sets.
     */
    public void produceChessSet(){

        // to produce the required number of Chess Sets
        for(int i=0;i<number;i++){

            // the order of usage to produce the Chess Sets
            resin.print();
            lathe.lathe();
            resin.print();
            lathe.lathe();
            fdm.print();
            produced++;
            output.lock(); // locking the output to prevent errors in production
            try{
                // updating the total number of knickknacks produced so far
                makerSpace.producedSoFar++;
                if((makerSpace.producedSoFar)% makerSpace.freReport==0){
                    // reporting the progress
                    System.out.println("Made "+makerSpace.producedSoFar+" knickknacks");
                    System.out.println("----------------------------------------");}
            }finally{
                // releasing the lock
                output.unlock();
            }
        }
        // locking the makerspace lock to update on the completion of the production of Chess Sets
        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Chess sets produced");
            System.out.println("----------------------------------------");
            // incrementing the total produced
            makerSpace.totalProduced+=produced;
        }finally{
            makerSpace.lock.unlock();
        }

    }
}
