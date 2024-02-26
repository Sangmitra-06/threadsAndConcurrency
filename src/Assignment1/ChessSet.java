package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ChessSet implements Runnable{
    private ResinPrinter resin;
    private Lathe lathe;
    private FDMPrinter fdm;
    private int number;
    private int progressInterval;
    private MakerSpace makerSpace;
    private ReentrantLock lock;

    public ChessSet(int no, int progress, ResinPrinter r, Lathe l, FDMPrinter f, MakerSpace m){
        number=no;
        progressInterval=progress;
        resin=r;
        lathe=l;
        fdm=f;

        makerSpace=m;
        lock=new ReentrantLock();
    }
    @Override
    public void run() {
        produceChessSet();

    }
    public int produceChessSet(){
        int produced=0;
        for(int i=0;i<number;i++){

            resin.print("white pieces");
            lathe.lathe("turn the white rooks");
            resin.print("black pieces");
            lathe.lathe("turn the black hooks");
            fdm.print("the board");
            produced++;
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" chess sets");
            }
        }
        System.out.println(produced+" Chess sets produced");
        lock.lock();
        makerSpace.totalProduced+=produced;
        lock.unlock();
        return produced;
    }
}
