package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class ChessSet implements Runnable{
    private ResinPrinter resin;
    private Lathe lathe;
    private FDMPrinter fdm;
    private int number;
    private MakerSpace makerSpace;
    private ReentrantLock lock;
    private ReentrantLock output;
    private volatile int produced=0;

    public ChessSet(int no, ResinPrinter r, Lathe l, FDMPrinter f, MakerSpace m){
        number=no;
        resin=r;
        lathe=l;
        fdm=f;

        makerSpace=m;
        lock=new ReentrantLock();
        output=m.output;
    }
    @Override
    public void run() {
        produceChessSet();

    }
    public int produceChessSet(){
        for(int i=0;i<number;i++){

            resin.print("white pieces");
            lathe.lathe("turn the white rooks");
            resin.print("black pieces");
            lathe.lathe("turn the black hooks");
            fdm.print("the board");
            produced++;
            output.lock();
            try{
                makerSpace.producedSoFar++;
                if((makerSpace.producedSoFar)% makerSpace.freReport==0){
                    System.out.println("Made "+makerSpace.producedSoFar+" knickknacks");
                    System.out.println("----------------------------------------");}
            }finally{
                output.unlock();
            }
        }

        makerSpace.lock.lock();
        try{
            System.out.println(produced+" Chess sets produced");
            System.out.println("----------------------------------------");
            makerSpace.totalProduced+=produced;
        }finally{

            makerSpace.lock.unlock();
        }

        return produced;
    }
}
