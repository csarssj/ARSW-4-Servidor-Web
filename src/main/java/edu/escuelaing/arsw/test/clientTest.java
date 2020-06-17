/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.test;

import edu.escuelaing.arsw.webserver.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hilo encargado de realizar las peticiones al servidor
 *  
 * @author ceseg
 */
public class clientTest  extends Thread {
    
    private static String url;
    private static int solicitudes;
    
    public clientTest(String temp){ 
      this.url=temp;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(this.url); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null; 
            while ((line = reader.readLine()) != null) {
                  //  System.out.println(line);
                }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Encargado de iniciar el cliente y de recibir los datos
     * 
     * @param args url y numero de peticiones para ingresar
     * @throws MalformedURLException 
     */
    public static void  main(String [] args) throws MalformedURLException, IOException{
        URL url = new URL(args[0]);
        solicitudes = Integer.parseInt(args[1]);
        clientTest[] threads =  new clientTest[solicitudes];
        for (int i = 0; i < solicitudes; i++) {
            threads[i] = new clientTest(args[0]);
            threads[i].start();
        }
        int contador = 0;
        for (Thread t : threads) {
            contador++;
        }
        System.out.println("Total:" + contador);
        
    }
}
