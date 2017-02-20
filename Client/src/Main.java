/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import snoozesoft.systray4j.SysTrayMenu;
import snoozesoft.systray4j.SysTrayMenuEvent;
import snoozesoft.systray4j.SysTrayMenuIcon;
import snoozesoft.systray4j.SysTrayMenuItem;
import snoozesoft.systray4j.SysTrayMenuListener;

/**
 * Главный класс для запуска программы
 *
 * @author Daniil
 */
public class Main  {

    private SysTrayMenu menu;
    private static NotificationManager notificationManager;
    public void createMenu() {
        SysTrayMenuItem itemExit = new SysTrayMenuItem("Exit", "Exit");
        itemExit.addSysTrayMenuListener(new SysTrayMenuListener() {
            @Override
            public void menuItemSelected(SysTrayMenuEvent stme) {
                System.exit(0);
            }

            @Override
            public void iconLeftClicked(SysTrayMenuEvent stme) {
                System.exit(0);
            }

            @Override
            public void iconLeftDoubleClicked(SysTrayMenuEvent stme) {
            }

        });

        menu = new SysTrayMenu(new SysTrayMenuIcon("View/icon.ico"), "SysTray for Java rules!");
        menu.addItem(itemExit);

    }

    public static void main(String[] args) {
        Main mainObject = new Main();
        notificationManager = new NotificationManager();
        notificationManager.setDaemon(true);
        notificationManager.start();
        mainObject.createMenu();
//        try {
//            notificationManager.join();
//        } catch (InterruptedException ex) {
//        }
    }

}
