package Lamport;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Lamport {
    
    private static final int N = 10;
    private volatile static int numeros[] = new int[N];
    private volatile static boolean seleccionNum[] = new boolean[N];
    
    public static Thread crearHilo(final int i){
        Thread thl = new Thread(new Runnable(){
            
            @Override
            public void run(){
                seleccionNum[i] = true;
                numeros[i] = 1 + maximo(numeros);
                seleccionNum[i] = false;
                
                for(int j = 0 ; j < N ; j++){
                 while(seleccionNum[j]);
                 while((((numeros[j] != 0) && (numeros[j] < numeros[i])) || (numeros[j]== numeros[i] ) && (j < i)));
                }
                
                System.out.println("("+i + ") proceso a: Hola");
                System.out.println("("+i + ") proceso b: Que bonito");
                System.out.println("("+i + ") proceso c: Alucinante");
                numeros[i] = 0;
                System.out.println("("+i + ") proceso a: Paseando voy...");
                System.out.println("("+i + ") proceso b: Paseando vengo...");
            }
            
        }, "Hilo " + i);
                return thl;
    } 
       
    public static int maximo(int[] numeros){
        int max = 0;
        
        for (int i = 0; i < N; i++) {
            max += numeros[i];
        }
        return max;
    }
    
    public static void main(String[] args) {
        System.out.println("===============================");
        System.out.println("            LAMPORT            ");
        System.out.println("===============================");
        List<Thread> lista = new ArrayList<Thread>();
        
        for (int i = 0; i < N; i++) {
            Thread th = crearHilo(i);
            lista.add(th);
        }
        
        for (int i = 0; i < N; i++) {
            Thread th = lista.get(i);
            th.start();
        }
    }    
}
