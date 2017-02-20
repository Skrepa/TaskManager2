/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


/**
 * Главный класс для запуска программы
 *
 * @author Daniil
 */
public class Main {

    private static NotificationManager notificationManager;
   
    public static void main(String[] args) {
        Main mainObject = new Main();
        notificationManager = new NotificationManager();
        notificationManager.setDaemon(true);
        notificationManager.start();
        Controller.getInstance().openView();
        try {
            notificationManager.join();
        } catch (InterruptedException ex) {
        }
    }

}
