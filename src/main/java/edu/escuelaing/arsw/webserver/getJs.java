/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * 
 * @author ceseg
 */
public class getJs {

    public static void getJs(String element, OutputStream  outputStream) throws IOException{
        try{
            String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/resources" + element));
            String infile = null;
            while ((infile = reader.readLine()) != null) {
                text = text + infile;
            }
            outputStream.write(("HTTP/1.1 201 FOUND  \r\n"
                        + "Content-Type: text/js; charset=\"UTF-8\" \r\n"
                        + "\r\n"
                        + text).getBytes());
        }catch (IOException e) {
                e.printStackTrace();
        }
    }
}
