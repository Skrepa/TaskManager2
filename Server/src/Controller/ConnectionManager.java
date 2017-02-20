/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.View;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Daniil
 */
public class ConnectionManager implements AutoCloseable {

    private ArrayList<Socket> clients = new ArrayList<>();
    private ServerSocket ss;
    Thread accepter;
    Thread remover;

    private class Accepter extends Thread {

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Socket client = ss.accept();
                    synchronized (clients) {
                        clients.add(client);
                    }
                } catch (IOException ex) {
                    View.showException(ex);
                }
            }
        }
    }

    private class Remover extends Thread {

        @Override
        public void run() {
            while (!isInterrupted()) {
                synchronized (clients) {
                    clients.removeIf((Socket t) -> t.isClosed());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
            synchronized (clients) {
                for (Socket s : clients) {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        View.showException(ex);
                    }

                }
                clients.clear();
            }
        }
    }

    public ConnectionManager() throws IOException {
        ss = new ServerSocket(4444);
        accepter = new Accepter();
        accepter.setDaemon(true);
        accepter.start();
        remover = new Remover();
        remover.setDaemon(true);
        remover.start();
    }
    
    @Override
    public void close(){
        accepter.interrupt();
        remover.interrupt();
    }

    public interface OnForEachListener {
        void onForEach(InputStream in, OutputStream out) throws IOException, JAXBException;
    }

    public void forEach(OnForEachListener callback) {
        synchronized (clients) {
            for (Socket s : clients) {//пробегаемся по клиентам
                if (!s.isClosed())//если можно писать то пишем таску
                {
                    try {
                        callback.onForEach(s.getInputStream(), s.getOutputStream());
                    } catch (Exception ex) {
                        try {
                            s.close();
                        } catch (IOException ex1) {
                            View.showException(ex1);
                        }
                    }
                }
            }
        }
    }
}
