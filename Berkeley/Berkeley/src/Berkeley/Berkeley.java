package Berkeley;

public class Berkeley {

    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("                       TIEMPOS                        ");
        System.out.println("======================================================");
        Salida simulacion = new Salida();
        Servidor servidor = new Servidor(simulacion);
        
        servidor.start();
        
        Cliente cliente[] = new Cliente[3];
        for (int i = 0 ; i < 3 ; i++) {
            cliente[i] = new Cliente(i, simulacion);
            cliente[i].start();            
        }
    }
}
