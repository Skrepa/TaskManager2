/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.XML;
import Model.Task;
import View.View;
import java.io.IOException;
import java.util.HashMap;

/**
 * класс для обработки нотификации
 *
 * @author Daniil
 */
public class NotificationManager extends Thread {

    private HashMap<Integer, Task.Status> statuses = new HashMap<Integer, Task.Status>();

    private void show(ConnectionManager cm, Task task) {
        cm.forEach((in, out) -> {
            XML.serialize(task, out);
            out.write(10);
            out.flush();
        });
    }

    private void updateTask(ConnectionManager cm, int id) {
        Task task = Controller.getInstance().getJournal().getTask(id);
        if (statuses.containsKey(task.getId())) {
            if (statuses.get(task.getId()) != task.getStatus()) {
                Controller.getInstance().update();
            }
            if (task.getStatus() != Task.Status.ACTIVE && statuses.get(task.getId()) == Task.Status.ACTIVE) {
                show(cm, task);
            }
            statuses.replace(task.getId(), task.getStatus());
        } else {
            statuses.put(task.getId(), task.getStatus());
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        try (ConnectionManager cm = new ConnectionManager()) {
            while (!isInterrupted()) {
                for (int id : Controller.getInstance().getJournal().getIDs()) {
                    updateTask(cm, id);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        } catch (IOException ex) {
            View.showException(ex);
        }
    }
}
