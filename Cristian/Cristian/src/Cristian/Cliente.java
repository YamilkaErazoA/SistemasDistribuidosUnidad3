package Cristian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {

    public static void main(String[] args) throws IOException {
        System.out.println("============================================================");
        System.out.println("                          CLIENTE                           ");
        System.out.println("============================================================");
        String port, hostName; //Se declaran dos strings para el puerto y el host
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //Leer todo lo que se va a ingresar
        System.out.print("Ingresar el Número de Puerto: "); //Se envía un mensaje a la consola para que se ingrese un número de puerto
        port = stdIn.readLine(); //Se lee el puerto ingresado y se aloja en port
        int portNumber = Integer.parseInt(port); //Se convierte en int el puerto ingresado
        
        System.out.print("Ingresar Nombre del Host: "); 
        hostName = stdIn.readLine(); //Se lee el nombre ingresado y se aloja en hostName
        
        try (Socket echoSocket = new Socket(hostName, portNumber); //Se inicia un socket para iniciar la comunicación con el servidor  
            PrintWriter salida = new PrintWriter(echoSocket.getOutputStream(), true); //Clase para escribir  
            BufferedReader entrada = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));) //Clase para leer los datos
        {
            String userInput; //Se declara un String que va a ser la entrada del usuario
            System.out.println("============================================================");
            System.out.println("Cliente Iniciado"); //Se envía un mensaje a la consola para indicar que se inició el Cliente
            System.out.println("============================================================");
            
            long TiempoInicial; 
            long TiempoServidor;
            long TiempoUno;
            long TiempoFinal;
            
            salida.println(TiempoInicial = System.currentTimeMillis()); //Se toma el tiempo inicial y se guarda como el tiempo actual
            TiempoServidor = Long.parseLong(entrada.readLine()); //Se obtiene el tiempo que tiene el Servidor mediante una petición y se guara en TiempoServidor
            TiempoUno = System.currentTimeMillis(); //Se guarda el TiempoUno
            TiempoFinal = (TiempoServidor + (TiempoUno - TiempoInicial) / 2); //Para sincronizar se obtiene el tiempo final mediate la fórmula
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS"); //Se guarda en un formato deseado

            System.out.println("Tiempo del Cliente: " + formatter.format(new Date(TiempoUno))); //Se imprime el tiempo del cliente convertido al formato especificado
            System.out.println("Tiempo del Servidor: " + formatter.format(new Date(TiempoServidor))); //Se imprime el tiempo del servidor convertido al formato especificado
            System.out.println("Tiempo del Cliente después de la Sincroización: " + formatter.format(new Date(TiempoFinal))); //Se imprime el tiempo sincronizado convertido al formato especificado
            System.out.println("============================================================");

        } catch (Exception e) {
            System.out.println("Tiempo Agotado");
            System.out.println("============================================================");
        }

    }
}
