/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.webserver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * 
 * @author ceseg
 */
public class getImg {
    public static void getImage(PrintWriter out, OutputStream clientSocket, String element) throws IOException{
        try{
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir")+ "/resources" + element));
            ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
            DataOutputStream writeImg = new DataOutputStream(clientSocket);
            ImageIO.write(image, "PNG", ArrBytes);
            writeImg.writeBytes("HTTP/1.1 200 OK \r\n");
            writeImg.writeBytes("Content-Type: image/png \r\n");
            writeImg.writeBytes("\r\n");
            writeImg.write(ArrBytes.toByteArray());
            System.out.println(System.getProperty("user.dir") + element);
        }catch(IOException e){
        }
    }
}
