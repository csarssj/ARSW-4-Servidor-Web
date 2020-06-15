/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.webserver;

/**
 *
 * @author ceseg
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer implements Runnable  {
    private final Socket clientSock;
    /**
     * Creates a new ClientHandler thread for the socket provided.
     *
     * @param clientSocket the socket to the client.
     */
    public HttpServer (final Socket clientSocket) {
        this.clientSock = clientSocket;
    }
    @Override
    public void run() {
        try{
                PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                String inputLine, outputLine, element = null;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.matches("(GET)+.*")) {
                        element = inputLine.split(" ")[1];
                    }
                    if (!in.ready()) {
                        break;
                    }
                }
                if (element.contains("png") || element.contains("PNG")) {
                    getImg.getImage(out,clientSock.getOutputStream(), element);

                } else if (element.contains(".html")) {
                    getFile.getFiles(element, clientSock.getOutputStream());
                } else if (element.contains(".js")) {
                    getJs.getJs(element, clientSock.getOutputStream());
                } else {
                    outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>\n"
                            + "<html>\n"
                            + "<head>\n"
                            + "<meta charset=\"UTF-8\">\n"
                            + "<title>Title of the document</title>\n"
                            + "</head>\n"
                            + "<body>\n"
                            + "<h1>No existe el archivo seleccionado</h1>\n"
                            + "</body>\n"
                            + "</html>\n" + inputLine;
                    out.println(outputLine);
                }
                out.close();
                in.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
