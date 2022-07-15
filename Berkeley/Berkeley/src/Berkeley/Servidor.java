package Berkeley;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{
    private Salida out;
    private final int SleepSeconds;
    private long ServidorTime;
    
    public Servidor(Salida out){
        this.out = out;
        this.SleepSeconds = 3000;
        this.ServidorTime = System.nanoTime();
    }
    
    public void run(){
        while(true){
            try {
                Thread.sleep(this.SleepSeconds);
                System.out.println("======================================================");
                System.out.println("Temporización(Servidor): " + this.ServidorTime);
                System.out.println("======================================================");
                this.out.setServerTime(this.ServidorTime);
                this.out.CalculoSet();
                this.ServidorTime += this.out.getAverage();
                System.out.println("Temporización Acordada(Servidor): " + this.ServidorTime);
                System.out.println("======================================================");
                this.out.restartProcess();
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }  
       }
    }
}
