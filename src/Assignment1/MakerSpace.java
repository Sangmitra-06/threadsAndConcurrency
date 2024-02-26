package Assignment1;

import java.util.concurrent.locks.ReentrantLock;

public class MakerSpace {
    private static ResinPrinter resinPrinter=new ResinPrinter();
    private static Airbrush airbrush=new Airbrush();
    private static SolderingIron solderingIron=new SolderingIron();
    private static FDMPrinter fdmPrinter=new FDMPrinter();
    private static ToasterOven toasterOven=new ToasterOven();
    private static Mill mill=new Mill();
    private static Lathe lathe=new Lathe();
    private static ReentrantLock lock=new ReentrantLock();
    public int totalProduced=0;

    public MakerSpace(){
        // Create instances of knickknack makers
        FiguringsForMazesAndMonsters figurineMaker = new FiguringsForMazesAndMonsters(10,2, resinPrinter, airbrush,this);
        MotorControllers motorControllerMaker = new MotorControllers(5, 1, solderingIron, mill, toasterOven, this);
        ChessSet chessSetMaker = new ChessSet(3,1,resinPrinter, lathe, fdmPrinter,this);
        SAKScales sakScales=new SAKScales(mill,toasterOven,4,1,this);
        Flashlight flash=new Flashlight(lathe,solderingIron,10,2,this);
        CupHolder cupHolder=new CupHolder(toasterOven,fdmPrinter,airbrush,20,4,this);
        ToasterPastry pastry=new ToasterPastry(toasterOven,10,5,this);

        // Create and start threads for each knickknack maker
        Thread figurineThread = new Thread(figurineMaker);
        Thread motorControllerThread = new Thread(motorControllerMaker);
        Thread chessSetThread = new Thread(chessSetMaker);
        Thread sakScalesThread=new Thread(sakScales);
        Thread flashlightThread=new Thread(flash);
        Thread cupholderThread=new Thread(cupHolder);
        Thread pastryThread=new Thread(pastry);

        figurineThread.start();
        motorControllerThread.start();
        chessSetThread.start();
        sakScalesThread.start();
        flashlightThread.start();
        cupholderThread.start();
        pastryThread.start();

        try {
            // Wait for all threads to complete
            figurineThread.join();
            motorControllerThread.join();
            chessSetThread.join();
            sakScalesThread.join();
            flashlightThread.join();
            cupholderThread.join();
            pastryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(totalProduced+" Produced");

        System.out.println("All knickknacks produced.");
    }

    public static void main(String[] args){
        new MakerSpace();
    }

}
