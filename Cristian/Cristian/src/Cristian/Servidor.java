package Cristian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        System.out.println("============================================================");
        System.out.println("                          SERVIDOR                          ");
        System.out.println("============================================================");
        String port;//Se declaran un string para almacenar el puerto
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //Leer todo lo que se va a ingresar
        System.out.print("Ingresar el Número de Puerto: "); //Se envía un mensaje a la consola para que se ingrese un número de puerto
        port = stdIn.readLine(); //Se lee el puerto ingresado y se aloja en port
        int portNumber = Integer.parseInt(port); //Se convierte en int el puerto ingresado
        
        System.out.print("Ingresar el Nombre del Host: "); //Se envía un mensaje a la consola para que se ingrese el nombre del host
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber); //Se inicia un socket para iniciar la comunicación  
            Socket clientSocket = serverSocket.accept(); //Socket para aceptar la petición del servidor  
            PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true); //Clase para escribir   
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) //Clase para leer los datos
        {
            String inputLine; //Strig para la línea de entrada
            System.out.println("============================================================");
            System.out.println("Servidor Iniciado");
            System.out.println("============================================================");
            
            while (true) {
                inputLine = entrada.readLine();
                
                if (inputLine.equalsIgnoreCase("Exit")) {
                    System.out.println("Exiting");
                    System.out.println("Server Exiting");
                    break;
                }
                
                salida.println(System.currentTimeMillis() + 5000); //Se imprime el tiempo + 5 segundos para observar mejor cómo se sincroniza
            }
        } catch (Exception e) {
            System.out.println("Tiempo Agotado"); //En caso de algún error se muestra el mensaje
        }
        System.out.println("============================================================");
    }
}
