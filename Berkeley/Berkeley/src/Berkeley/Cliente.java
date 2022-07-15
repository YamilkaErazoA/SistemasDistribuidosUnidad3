package Berkeley;

import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{
    private int clienteID; 
    private long clienteTime;
    private Salida out;
    private final boolean addDelay = true;
    
    public Cliente(int clienteId, Salida out){
        this.out = out;
        this.clienteID = clienteId;
        this.clienteTime = clienteTime;
    }
    
    public void run(){
        while(true){
            long convert = TimeUnit.SECONDS.convert(clienteTime, TimeUnit.NANOSECONDS);
            this.clienteTime += (this.addDelay)?(this.clienteID+1)*2 :0;
            System.out.println("Cliente " + clienteID + " :" +this.clienteTime);
            this.out.setDiffTimes(this.clienteTime, this.clienteID);
            this.clienteTime += this.out.getSettingTime(this.clienteID);
            System.out.println("Cliente " + clienteID + " :" + this.clienteTime); 
       }
    }
}
