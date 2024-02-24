package Assignment1;

public class MakerSpace {
    private static ResinPrinter resinPrinter=new ResinPrinter();
    private static Airbrush airbrush=new Airbrush();
    private static SolderingIron solderingIron=new SolderingIron();
    private static FDMPrinter fdmPrinter=new FDMPrinter();
    private static ToasterOven toasterOven=new ToasterOven();
    private static Mill mill=new Mill();
    private static Lathe lathe=new Lathe();

    public MakerSpace(){
        // Create instances of knickknack makers
        FiguringsForMazesAndMonsters figurineMaker = new FiguringsForMazesAndMonsters(10,2, resinPrinter, airbrush);
        MotorControllers motorControllerMaker = new MotorControllers(5, 1, solderingIron, mill, toasterOven);
        ChessSet chessSetMaker = new ChessSet(3,1,resinPrinter, lathe, fdmPrinter);

        // Create and start threads for each knickknack maker
        Thread figurineThread = new Thread(figurineMaker);
        Thread motorControllerThread = new Thread(motorControllerMaker);
        Thread chessSetThread = new Thread(chessSetMaker);

        figurineThread.start();
        motorControllerThread.start();
        chessSetThread.start();

        try {
            // Wait for all threads to complete
            figurineThread.join();
            motorControllerThread.join();
            chessSetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All knickknacks produced.");
    }

    public static void main(String[] args){
        new MakerSpace();
    }

}
