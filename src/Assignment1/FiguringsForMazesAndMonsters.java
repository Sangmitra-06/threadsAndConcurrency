package Assignment1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FiguringsForMazesAndMonsters implements Runnable{
    private ResinPrinter resin;
    private Airbrush airbrush;
    private int number;
    private int progressInterval;

    public FiguringsForMazesAndMonsters(int no,int progress, ResinPrinter resin_print, Airbrush a){
        number=no;
        progressInterval=progress;
        resin=resin_print;
        airbrush=a;
    }

    @Override
    public void run() {
        for(int i=0;i<number;i++){
            resin.print("figurine");
            airbrush.brush("paint figurine");
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" figurines");
            }


        }
    }
}
