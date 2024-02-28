package Assignment1;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class MakerSpace {
    private static ResinPrinter resinPrinter=new ResinPrinter();
    private static Airbrush airbrush=new Airbrush();
    private static SolderingIron solderingIron=new SolderingIron();
    private static FDMPrinter fdmPrinter=new FDMPrinter();
    private static ToasterOven toasterOven=new ToasterOven();
    private static Mill mill=new Mill();
    private static Lathe lathe=new Lathe();
    public ReentrantLock lock=new ReentrantLock();
    public volatile int  totalProduced=0;
    public ReentrantLock output;
    private Scanner scanner;
    private int numFig, numChess, numFlash, numPastry, numMC, numScales, numCup;
    public int freReport;
    public volatile int producedSoFar;

    public MakerSpace(){
        output=new ReentrantLock();
        menu();
        // Create instances of knickknack makers
        FiguringsForMazesAndMonsters figurineMaker = new FiguringsForMazesAndMonsters(numFig, resinPrinter, airbrush,this);
        MotorControllers motorControllerMaker = new MotorControllers(numMC, solderingIron, mill, toasterOven, this);
        ChessSet chessSetMaker = new ChessSet(numChess,resinPrinter, lathe, fdmPrinter,this);
        SAKScales sakScales=new SAKScales(mill,toasterOven,numScales,this);
        Flashlight flash=new Flashlight(lathe,solderingIron,numFlash,this);
        CupHolder cupHolder=new CupHolder(toasterOven,fdmPrinter,airbrush,numCup,this);
        ToasterPastry pastry=new ToasterPastry(toasterOven,numPastry,this);

        // Create and start threads for each knickknack maker

        Thread figurineThread = new Thread(figurineMaker);
        Thread motorControllerThread = new Thread(motorControllerMaker);
        Thread chessSetThread = new Thread(chessSetMaker);
        Thread flashlightThread = new Thread(flash);
        Thread sakScalesThread=new Thread(sakScales);
        Thread cupholderThread=new Thread(cupHolder);
        Thread pastryThread=new Thread(pastry);

        if(numFig>0){
            figurineThread.start();
        }
        if(numMC>0){
            motorControllerThread.start();
        }
        if(numChess>0){

            chessSetThread.start();
        }
        if(numScales>0){
            sakScalesThread.start();
        }
        if(numFlash>0){
            flashlightThread.start();
        }
        if(numCup>0){
            cupholderThread.start();
        }
        if(numPastry>0){
            pastryThread.start();
        }


        try {
            // Wait for all threads to complete
            if(numFig>0){
                figurineThread.join();
            }
            if(numMC>0){
                motorControllerThread.join();
            }
            if(numChess>0){
                chessSetThread.join();
            }
            if(numScales>0){
                sakScalesThread.join();
            }
            if(numFlash>0){
                flashlightThread.join();
            }
            if(numCup>0){
                cupholderThread.join();
            }
            if(numPastry>0){
                pastryThread.join();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(totalProduced+" Produced");
        System.out.println("----------------------------------------");

        System.out.println("All knickknacks produced.");
        System.out.println("----------------------------------------");
    }

    private void menu(){
        scanner=new Scanner(System.in);
        System.out.println("Welcome to the MakerSpace");
        System.out.println("We have 7 knickknacks that you can prepare, please indicate 1 for yes and 0 for no for each of the following");
        System.out.println("Do you want to make Figurines?");
        int choiceFigurines=scanner.nextInt();
        System.out.println("Do you want to make Chess sets?");
        int choiceChessSet=scanner.nextInt();
        System.out.println("Do you want to make Flashlights?");
        int choiceFlashlights=scanner.nextInt();
        System.out.println("Do you want to make Motor Controllers?");
        int choiceMotorControllers=scanner.nextInt();
        System.out.println("Do you want to make SAK Scales?");
        int choiceSAKScales=scanner.nextInt();
        System.out.println("Do you want to toast Pastries?");
        int choicePastry=scanner.nextInt();
        System.out.println("Do you want to make Cup Holders?");
        int choiceCupHolder=scanner.nextInt();
        if(choiceFigurines==1){
            System.out.println("Please indicate the number of figurines you would like to prepare");
            numFig=scanner.nextInt();
        }
        if(choiceChessSet==1){
            System.out.println("Please indicate the number of Chess Sets you would like to prepare");
            numChess=scanner.nextInt();
        }if(choiceFlashlights==1){
            System.out.println("Please indicate the number of Flashlights you would like to prepare");
            numFlash=scanner.nextInt();
        }if(choiceMotorControllers==1){
            System.out.println("Please indicate the number of Motor Controllers you would like to prepare");
            numMC=scanner.nextInt();
        }if(choiceSAKScales==1){
            System.out.println("Please indicate the number of SAK Scales you would like to prepare");
            numScales=scanner.nextInt();
        }if(choicePastry==1){
            System.out.println("Please indicate the number of Pastries you would like to toast");
            numPastry=scanner.nextInt();
        }if(choiceCupHolder==1){
            System.out.println("Please indicate the number of Cup Holders you would like to prepare");
            numCup=scanner.nextInt();
        }
        System.out.println("Please specify the frequency of progress reporting");
        freReport=scanner.nextInt();

    }

    public static void main(String[] args){
        new MakerSpace();
    }

}
