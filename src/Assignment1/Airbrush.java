package Assignment1;
/**
 * This class is an implementation of the Airbrush resource. This resource contains its own lock to allow only one user to access it at a given time, thus ensuring proper concurrency and lack of deadlocks.
 *
 * @author     Sangmitra Madhusudan
 * @course     COSC 2P13
 * @assignment #1
 * @student Id 7115900
 * @version    1.0
 * @since      February 24th, 2024
 */
import java.util.concurrent.locks.ReentrantLock;

public class Airbrush {

    private ReentrantLock lock;

    public Airbrush(){
        lock = new ReentrantLock();
    }

    /* Function that locks the resource and simulates an action occurring
     *
     */
    public void brush(){
        try{
            // locking the resource
            lock.lock();

            try{
                // just simulating some action occurring
                Thread.sleep(0);
            }catch(InterruptedException e){
                e.getMessage();
            }
        }finally{
            // unlocking the resource
            lock.unlock();
        }

    } // brush
}
