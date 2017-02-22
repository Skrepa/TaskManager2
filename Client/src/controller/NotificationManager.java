package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Task;
import Model.XML;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * класс для обработки нотификации
 *
 * @author Daniil
 */
public class NotificationManager extends Thread {

    /**
     *
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            InetAddress ipAddress = null;
            try {
                ipAddress = InetAddress.getByName("127.0.0.1");
            } catch (UnknownHostException ex) {
                Notification.getInstance().showException(ex);
                System.exit(1);
            }
            try (Socket s = new Socket(ipAddress, 4444)) {
                Notification.getInstance().showInfo("Соединение установлено");
                while (!s.isInputShutdown()) { // пока соединение открыто
                    Notification.getInstance().show((Task) XML.deserialize(s.getInputStream(), Task.class));
                }
            } catch (Exception ex) {
                Notification.getInstance().showException(ex);
            }
        }
    }

}
