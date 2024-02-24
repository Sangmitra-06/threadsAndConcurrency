package Assignment1;

public class ChessSet implements Runnable{
    private ResinPrinter resin;
    private Lathe lathe;
    private FDMPrinter fdm;
    private int number;
    private int progressInterval;

    public ChessSet(int no, int progress, ResinPrinter r, Lathe l, FDMPrinter f){
        number=no;
        progressInterval=progress;
        resin=r;
        lathe=l;
        fdm=f;
    }
    @Override
    public void run() {
        for(int i=0;i<number;i++){
            resin.print("white pieces");
            lathe.lathe("turn the white rooks");
            resin.print("black pieces");
            lathe.lathe("turn the black hooks");
            fdm.print("the board");
            if((i+1)%progressInterval==0){
                System.out.println("Made "+(i+1)+" chess sets");
            }
        }

    }
}
