package Berkeley;

public class Salida {
    private long ServidorTiempo;
    private long[] diffTimes;
    private long sumDiffs;
    private final int numeroClientes = 3;
    private int countClientesOpered;
    
    public Salida(){
        this.ServidorTiempo = 0;
        this.countClientesOpered = this.numeroClientes;
        this.diffTimes = new long[this.numeroClientes];
        this.sumDiffs = 0;
    }
    
    public synchronized void setServerTime(long ServidorTiempo){
        this.ServidorTiempo = ServidorTiempo;
        try {
            notifyAll();
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void setDiffTimes(long time, int n){
        try {
            if(ServidorTiempo == 0){
                wait();
            }
            
            this.diffTimes[n] = (time - ServidorTiempo);
            this.sumDiffs += time;
            countClientesOpered--;
            
            if(countClientesOpered == 0){
                notify();
            }
            
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void CalculoSet(){
        long avg = (this.sumDiffs / (this.numeroClientes+1));
        for (int i = 0; i < this.numeroClientes; i++) {
            this.diffTimes[i] = (this.diffTimes[i] + avg);
        }
        notifyAll();
    }
    
    public synchronized long getSettingTime(int n){
        return this.diffTimes[n];
    }
    
    public synchronized long getAverage(){
        return this.sumDiffs / (this.numeroClientes+1);
    }
    
    public synchronized void restartProcess(){
        this.ServidorTiempo = 0;
        this.countClientesOpered = this.numeroClientes;
        this.sumDiffs = 0;
    }
}
